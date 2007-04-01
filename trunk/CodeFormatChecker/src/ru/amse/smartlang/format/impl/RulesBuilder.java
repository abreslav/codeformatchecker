package ru.amse.smartlang.format.impl;

import ru.amse.smartlang.format.model.AbstractBlockWalker;
import ru.amse.smartlang.format.model.IBlock;
import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.ICompositeBlock;
import ru.amse.smartlang.format.model.IRuleSet;
import ru.amse.smartlang.format.model.IRulesBuilder;

public class RulesBuilder implements IRulesBuilder {
	private IRuleSet rules = new RuleSet();

	private class BuilderWalker extends AbstractBlockWalker {
		@Override
		public void visit(ICompositeBlock parent, IBlock left, IBlock thiz) {
			if(rules.containsExactRule(parent.getType(), left.getType(), thiz.getType())) {
				//TODO Make rule decent
			} else {
				rules.addRule(new Rule(left.getType(), parent.getType(), thiz.getType(), thiz.getWhitespace()));
			}
		}
	}
	
	public IRuleSet buildRules(IBlock rootBlock) {
		rootBlock.visit(new BuilderWalker());
		return rules;
	}
}
