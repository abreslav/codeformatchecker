package ru.amse.smartlang.format.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.amse.smartlang.format.model.IBlock;
import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IBlockWalker;
import ru.amse.smartlang.format.model.ICompositeBlock;
import ru.amse.smartlang.format.model.IRegion;
import ru.amse.smartlang.format.model.IRule;
import ru.amse.smartlang.format.model.IRuleSet;
import ru.amse.smartlang.format.model.Whitespace;

public class FormatChecker {
	private IRuleSet ruleSet;
	private Collection<IFormatErrorListner> listners = new ArrayList<IFormatErrorListner>();
	private boolean ok;
	private BlockWalker BLOCK_WALKER = new BlockWalker();
	
	private class BlockWalker implements IBlockWalker {
		private ArrayList<IBlockType> parentStack = new ArrayList<IBlockType>();
		
		public void init() {
			parentStack.clear();
			parentStack.add(IBlockType.NULL);
		}
		
		private IBlockType getParent() {
			return parentStack.get(parentStack.size() - 1);
		}
		
		public void visitComposite(ICompositeBlock block) {
			List<IBlock> children = block.getChildren();
			IBlockType parent = getParent();
			parentStack.add(block.getType());
			IBlockType left = children.get(0).getType();
			for(int i = 1; i < children.size(); i++) {
				IBlock thiz = children.get(i); 
				IBlockType thizType = thiz.getType();
				IRule rule = ruleSet.getRule(parent, left, thizType);
				if(thiz.getWhitespace() != rule.getWhitespace()) {
					notifyListners(block.getWhitespaceRegion(), thiz.getWhitespace(), rule.getWhitespace());
				}
				thiz.visit(this);
				left = thizType;
			}
			parentStack.remove(parentStack.size() - 1);
		}

		public void visitPrimitive(IBlock block) {
			IRule rule = ruleSet.getRule(getParent(), IBlockType.NULL, block.getType());
			if(block.getWhitespace() != rule.getWhitespace()) {
				notifyListners(block.getWhitespaceRegion(), block.getWhitespace(), rule.getWhitespace());
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
