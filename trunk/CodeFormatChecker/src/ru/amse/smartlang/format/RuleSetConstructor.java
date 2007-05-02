package ru.amse.smartlang.format;


public class RuleSetConstructor {
	private IRuleSet ruleSet;
	
	public RuleSetConstructor(IRuleSet ruleSet) {
		this.ruleSet = ruleSet;
	}

	private class BuilderWalker extends AbstractPatternIterator {
		@Override
		public void visit(IBlockType parent, IBlockType left, IBlock thiz) {
			ruleSet.addPattern(new Pattern(parent, left, thiz.getType()), thiz.getWhitespace());
		}
	}
	
	public IRuleSet construct(IBlock root) {
		root.accept(new BuilderWalker());
		return ruleSet;
	}
}
