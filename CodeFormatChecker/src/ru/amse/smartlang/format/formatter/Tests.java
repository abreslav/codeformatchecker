package ru.amse.smartlang.format.formatter;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
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
import ru.amse.smartlang.format.RuleSet;
import ru.amse.smartlang.format.RuleSetConstructor;
import ru.amse.smartlang.format.parsers.pascal.MyParser;
import ru.amse.smartlang.format.parsers.pascal.PascalLexer;
import ru.amse.smartlang.format.parsers.utils.WhitespaceConverter;
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
		File dir = new File("tests/formatter");
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
	
	private File file;
	
	public Tests(File file) {
		this.file = file;
	}

	private static IBlock parse(String s) throws RecognitionException, TokenStreamException, FileNotFoundException {
		IBlock res = new MyParser(new PascalLexer(new FileReader(s))).program_block();
		WhitespaceConverter.convert(res);
		return res;
	}

	boolean equalFiles(Reader r1, Reader r2) throws IOException {
		int c1, c2;
		do { 
			c1 = r1.read();
			c2 = r2.read();
		} while(c1 == c2 && c1 != -1 && c2 != -1);
		r1.close();
		r2.close();
		return c1 == c2;
	}

	@Test
	public void main() throws IOException, RecognitionException, TokenStreamException {
		IBlock root;
		root = parse(file.getPath() + "/sample.pas");
		root.accept(new BlockPrinter(new PrintStream(new File(file, "sample.blocks"))));
		
		RuleSet rs = new RuleSet();
		new RuleSetConstructor(rs).construct(root);
		IBlock parse = parse(file.getPath() + "/input.pas");
		parse.accept(new BlockPrinter(new PrintStream(new File(file, "input.blocks"))));
		new Formatter(rs).format(parse, new FileWriter(file.getPath() + "/result.pas"));
		assertTrue(equalFiles(new FileReader(file.getPath() + "/result.pas"), new FileReader(file.getPath() + "/shouldbe.pas")));				
	}
	
}
