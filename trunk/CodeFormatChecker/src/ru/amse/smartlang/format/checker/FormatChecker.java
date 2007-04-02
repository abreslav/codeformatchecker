package ru.amse.smartlang.format.checker;

import java.util.ArrayList;
import java.util.Collection;

import ru.amse.smartlang.format.model.AbstractBlockVisitor;
import ru.amse.smartlang.format.model.IBlock;
import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IRegion;
import ru.amse.smartlang.format.model.IRule;
import ru.amse.smartlang.format.model.IRuleSet;
import ru.amse.smartlang.format.model.Whitespace;

public class FormatChecker {
	private IRuleSet ruleSet;
	private Collection<IFormatErrorListner> listners = new ArrayList<IFormatErrorListner>();
	private boolean errorOccured;
	private BlockWalker BLOCK_WALKER = new BlockWalker();
	
	private class BlockWalker extends AbstractBlockVisitor{
		
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			IRule rule = ruleSet.getRule(parent, left, thiz.getType());
			if(rule == null) {
				notifyListners(parent, left, thiz);
			} else {
				if(thiz.getWhitespace() != rule.getWhitespace()) {
					notifyListners(thiz.getWhitespaceRegion(), thiz.getWhitespace(), rule.getWhitespace());
				}
			}
		}
	}

	public FormatChecker(IRuleSet ruleSet) {
		super();
		this.ruleSet = ruleSet;
	}
	
	public void notifyListners(IBlockType parent, IBlockType left, IBlock thiz) {
		for(IFormatErrorListner l : listners) {
			l.notify(parent, left, thiz.getType());
		}
	}

	public void addListner(IFormatErrorListner listner) {
		listners.add(listner);
	}
	
	public void removeListner(IFormatErrorListner listner) {
		listners.remove(listner);
	}
	
	private void notifyListners(IRegion region, Whitespace found, Whitespace expected) {
		for(IFormatErrorListner l : listners) {
			l.notify(region, found, expected);
		}
		errorOccured = false;
	}
	
	public boolean check(IBlock rootBlock) {
		errorOccured = true;
		rootBlock.accept(BLOCK_WALKER);
		return errorOccured;
	}

	public IRuleSet getRuleSet() {
		return ruleSet;
	}
}
