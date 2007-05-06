package ru.amse.smartlang.format.parsers.pascal;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockVisitor;
import ru.amse.smartlang.format.ICompositeBlock;
import ru.amse.smartlang.format.IRegion;
import ru.amse.smartlang.format.Pattern;
import ru.amse.smartlang.format.RuleSet;
import ru.amse.smartlang.format.RuleSetConstructor;
import ru.amse.smartlang.format.Whitespace;
import ru.amse.smartlang.format.WhitespaceConverter;
import ru.amse.smartlang.format.checker.FormatChecker;
import ru.amse.smartlang.format.checker.IFormatErrorListner;
import antlr.RecognitionException;
import antlr.TokenStreamException;

@RunWith(Parameterized.class)
public class Tests {
	static class BlockPrinter implements IBlockVisitor {
		int indent = 0;
		private PrintStream out;
		
		public BlockPrinter(PrintStream out) {
			this.out = out;
		}

		public void visitComposite(ICompositeBlock block) {
			for(int i = 0; i != indent; i++) {
				out.print(' ');
			}
			out.println(block);
			indent += 2;
			for(IBlock b : block.getChildren()) {
				b.accept(this);
			}
			indent -= 2;
		}

		public void visitPrimitive(IBlock block) {
			for(int i = 0; i != indent; i++) {
				out.print(' ');
			}
			out.println(block);
		}
	}
	
	@Parameters
	public static Collection<Object[]> parameters() {
		List<Object[]> result = new ArrayList<Object[]>();
		File dir = new File("tests/pascal");
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				result.add(new Object[] {file});			
			}
		}
		Collections.sort(result, new Comparator<Object []>() {
			public int compare(Object[] o1, Object[] o2) {
				File f1 = (File)o1[0];
				File f2 = (File)o2[0];
				return f1.getName().compareTo(f2.getName());
			}
		});
		return result;
	}
	
	private static IBlock parse(String s) throws RecognitionException, TokenStreamException, FileNotFoundException {
		IBlock res = new MyParser(new PascalLexer(new FileReader(s))).program_block();
		WhitespaceConverter.toRelativeWhitespaces(res);
		return res;
	}

	private File file;
	
	public Tests(File file) {
		this.file = file;
	}

	
	/**
	 * @throws IOException
	 * @throws RecognitionException
	 * @throws TokenStreamException
	 */
	@Test
	public void main() throws IOException, RecognitionException, TokenStreamException {
		IBlock root = parse(file.getPath() + "/sample.pas");
		root.accept(new BlockPrinter(new PrintStream(new File(file, "sample.blocks"))));
		
		RuleSet rs = new RuleSet();
		new RuleSetConstructor(rs).construct(root);
		IBlock parse = parse(file.getPath() + "/my.pas");
		parse.accept(new BlockPrinter(new PrintStream(new File(file, "my.blocks"))));
		
		int i = new FileReader(file.getPath() + "/result").read();

		FormatChecker formatChecker = new FormatChecker(rs);
		final StringBuilder sb = new StringBuilder("\n");
		formatChecker.addListner(new IFormatErrorListner() {
			public void invalidWhitespace(Pattern p, IRegion region, Whitespace found, List<Whitespace> expected) {
				sb.append(p.toString() + "\n    found:" + found + "\n    expected: " + expected + "\n");
			}
		});
		boolean check = formatChecker.check(parse);
		
		assertEquals(sb.toString(), i == '1', check);				
	}
}
