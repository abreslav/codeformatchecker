package ru.amse.smartlang.format.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBlockWalker implements IBlockWalker{
	private List<ICompositeBlock> parents = new ArrayList<ICompositeBlock>();
	
	public abstract void visit(ICompositeBlock parent, IBlock left, IBlock thiz);
	
	public void visitComposite(ICompositeBlock block) {
		parents.add(block);
		List<IBlock> children = block.getChildren();
		IBlock left = IBlock.NULL;
		for(int i = 0; i < children.size(); i++) {
			IBlock thiz = children.get(i);
			visit(block, left, thiz);
			thiz.visit(this);
			left = thiz;
		}
		parents.remove(parents.size() - 1);
	}

	public void visitPrimitive(IBlock block) {
	}
}
