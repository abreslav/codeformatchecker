package ru.amse.smartlang.format.checker;

import java.util.ArrayList;
import java.util.Collection;

import ru.amse.smartlang.format.model.AbstractBlockWalker;
import ru.amse.smartlang.format.model.IBlock;
import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IRegion;
import ru.amse.smartlang.format.model.IRule;
import ru.amse.smartlang.format.model.IRuleSet;
import ru.amse.smartlang.format.model.Whitespace;

public class FormatChecker {
	private IRuleSet ruleSet;
	private Collection<IFormatErrorListner> listners = new ArrayList<IFormatErrorListner>();
	private boolean ok;
	private BlockWalker BLOCK_WALKER = new BlockWalker();
	
	private class BlockWalker extends AbstractBlockWalker{
		
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			IRule rule = ruleSet.getRule(parent, left, thiz.getType());
			if(thiz.getWhitespace() != rule.getWhitespace()) {
				notifyListners(thiz.getWhitespaceRegion(), thiz.getWhitespace(), rule.getWhitespace());
			}
		}
	}

	public FormatChecker(IRuleSet ruleSet) {
		super();
		this.ruleSet = ruleSet;
	}
	
	public synchronized void addListner(IFormatErrorListner listner) {
		listners.add(listner);
	}
	
	public synchronized void removeListner(IFormatErrorListner listner) {
		listners.remove(listner);
	}
	
	private void notifyListners(IRegion region, Whitespace found, Whitespace expected) {
		for(IFormatErrorListner l : listners) {
			l.notify(region, found, expected);
		}
		ok = false;
	}
	
	public synchronized boolean check(IBlock rootBlock) {
		ok = true;
		rootBlock.visit(BLOCK_WALKER);
		return ok;
	}

	public IRuleSet getRuleSet() {
		return ruleSet;
	}
}
