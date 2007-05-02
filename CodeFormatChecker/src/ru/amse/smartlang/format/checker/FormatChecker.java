package ru.amse.smartlang.format.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.amse.smartlang.format.AbstractPatternIterator;
import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockType;
import ru.amse.smartlang.format.IRegion;
import ru.amse.smartlang.format.IRuleSet;
import ru.amse.smartlang.format.Pattern;
import ru.amse.smartlang.format.Whitespace;

public class FormatChecker {
	private IRuleSet ruleSet;
	private Collection<IFormatErrorListner> listners = new ArrayList<IFormatErrorListner>();
	private boolean ok;
	private PatternIterator PATTERN_ITERATOR = new PatternIterator();
	
	private class PatternIterator extends AbstractPatternIterator {
		
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			Pattern p = new Pattern(parent, left, thiz.getType());
			if(!ruleSet.isValidWhitespace(p, thiz.getWhitespace())) {
				notifyListners(p, thiz.getWhitespaceRegion(), thiz.getWhitespace(), ruleSet.getWhitespacesFor(p));
			}
		}
	}

	public FormatChecker(IRuleSet ruleSet) {
		super();
		this.ruleSet = ruleSet;
	}
	
	public void notifyListners(Pattern p, IRegion r,  Whitespace w, List<Whitespace> exp) {
		for(IFormatErrorListner l : listners) {
			l.invalidWhitespace(p, r,  w, exp);
		}
		ok = false;
	}

	public void addListner(IFormatErrorListner listner) {
		listners.add(listner);
	}
	
	public void removeListner(IFormatErrorListner listner) {
		listners.remove(listner);
	}
	
	public boolean check(IBlock rootBlock) {
		ok = true;
		rootBlock.accept(PATTERN_ITERATOR);
		return ok;
	}

	public IRuleSet getRuleSet() {
		return ruleSet;
	}
}
