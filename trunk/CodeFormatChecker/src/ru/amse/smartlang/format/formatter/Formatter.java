package ru.amse.smartlang.format.formatter;

import ru.amse.smartlang.format.AbstractPatternIterator;
import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockType;
import ru.amse.smartlang.format.IRuleSet;
import ru.amse.smartlang.format.Pattern;
import ru.amse.smartlang.format.Whitespace;

public class Formatter {
	private final IRuleSet ruleSet;
	
	private final PatternIterator ITERATOR = new PatternIterator();
	
	private class PatternIterator extends AbstractPatternIterator {
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			Whitespace use = thiz.getWhitespace();
			Pattern p = new Pattern(parent, left, thiz.getType());
			if(!ruleSet.isValidWhitespace(p, thiz.getWhitespace())) {
				use = ruleSet.findNearestFor(p, thiz.getWhitespace());					
			}
			thiz.setWhitespace(use);
		}
	}
	
	public Formatter(IRuleSet ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	
	public void format(IBlock what) {
		what.accept(ITERATOR);
	}
}
