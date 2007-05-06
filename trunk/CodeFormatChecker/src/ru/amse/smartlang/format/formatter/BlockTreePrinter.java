package ru.amse.smartlang.format.formatter;

import java.io.PrintWriter;
import java.io.Writer;

import ru.amse.smartlang.format.AbstractPatternIterator;
import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockType;
import ru.amse.smartlang.format.Whitespace;

public class BlockTreePrinter {
	private String eol = "\r\n";
	
	private int indentSize = 2;
	
	private String indent = "  ";

	private StringBuilder sb = new StringBuilder();
	
	private PrintWriter ps;
	
	private final PatternIterator ITERATOR = new PatternIterator();
	
	public String getEol() {
		return eol;
	}

	public void setEol(String eol) {
		this.eol = eol;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(String indent) {
		this.indent = indent;
	}

	public int getIndentSize() {
		return indentSize;
	}

	public void setIndentSize(int indentSize) {
		this.indentSize = indentSize;
		sb.setLength(indentSize);
		for(int i = 0; i != indentSize; i++) {
			sb.setCharAt(i, ' ');
		}
		indent = sb.toString();
	}

	private void printWhitespace(Whitespace wh) {
		for(int i = 0; i < wh.getNewLines(); i++) {
			ps.print(eol);
		}
		if(wh.getNewLines() > 0) {
			for(int i = 0; i < wh.getIndents(); i++) {
				ps.print(indent);
			}
		}
		for(int i = 0; i < wh.getSpaces(); i++) {
			ps.print(' ');
		}
	}
	
	private class PatternIterator extends AbstractPatternIterator {
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			printWhitespace(thiz.getWhitespace());
			if(thiz.getText() != null) {
				ps.print(thiz.getText());
			}
		}
	}
	
	public void print(IBlock root, Writer w) {
		ps = new PrintWriter(w);
		root.accept(ITERATOR);
	}
}
