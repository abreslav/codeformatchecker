package ru.amse.smartlang.format.impl;

import ru.amse.smartlang.format.model.AbstractBlockWalker;
import ru.amse.smartlang.format.model.IBlock;
import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IRuleSet;
import ru.amse.smartlang.format.model.IRulesBuilder;

public class RulesBuilder implements IRulesBuilder {
	private IRuleSet rules = new RuleSet();

	private class BuilderWalker extends AbstractBlockWalker {
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			if(rules.containsExactRule(parent, left, thiz.getType())) {
				Rule rule = (Rule) rules.getRule(parent, left, thiz.getType());
				rule.setDecent(true);
			} else {
				rules.addRule(new Rule(left, parent, thiz.getType(), thiz.getWhitespace()));
			}
		}
	}
	
	public IRuleSet buildRules(IBlock rootBlock) {
		rootBlock.visit(new BuilderWalker());
		return rules;
	}
}
