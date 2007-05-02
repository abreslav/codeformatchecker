package ru.amse.smartlang.format.formatter;

import java.io.PrintWriter;
import java.io.Writer;

import ru.amse.smartlang.format.AbstractPatternIterator;
import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockType;
import ru.amse.smartlang.format.IRuleSet;
import ru.amse.smartlang.format.Pattern;
import ru.amse.smartlang.format.Whitespace;

public class Formatter {
	private final IRuleSet ruleSet;
	
	private PrintWriter ps;

	private StringBuffer sb = new StringBuffer(128);
	
	private String eol = "\r\n";
	
	private int indentSize = 2;
	
	private String indent = "  ";
	
	private final PatternIterator ITERATOR = new PatternIterator();
	
	private void recalculateIndent() {
		sb.setLength(indentSize);
		for(int i = 0; i != indentSize; i++) {
			sb.setCharAt(i, ' ');
		}
		indent = sb.toString();
	}
	
	private String textPresentation(int newLines, int ind, int spaces) {
		sb = new StringBuffer();
		for(int i = 0; i < newLines; i++) {
			sb.append(eol);
		}
		for(int i = 0; i < ind; i++) {
			sb.append(indent);
		}
		for(int i = 0; i < spaces; i++) {
			sb.append(' ');
		}
		return sb.toString();
		                                           
	}
	
	private class PatternIterator extends AbstractPatternIterator {
		private int indent = 0;
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			Whitespace use = thiz.getWhitespace();
			Pattern p = new Pattern(parent, left, thiz.getType());
			if(!ruleSet.isValidWhitespace(p, thiz.getWhitespace())) {
				use = ruleSet.findNearestFor(p, thiz.getWhitespace());					
			}
			indent += use.getIndents();
			int useI = use.getNewLines() > 0 ? indent : 0;
			ps.print(textPresentation(use.getNewLines(), useI, use.getSpaces()));
			if(thiz.getText() != null) {
				ps.print(thiz.getText());
			}
		}
	}
	
	public Formatter(IRuleSet ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	
	public String getEol() {
		return eol;
	}

	public void setEol(String eol) {
		this.eol = eol;
	}

	public int getIndentSize() {
		return indentSize;
	}

	public void setIndentSize(int indentSize) {
		this.indentSize = indentSize;
		recalculateIndent();
	}

	public void format(IBlock what, Writer dest) {
		ps = new PrintWriter(dest);
		what.accept(ITERATOR);
		ps.close();
	}
}
