package ru.amse.smartlang.format.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBlockVisitor implements IBlockVisitor {
	private List<IBlockType> parents = new ArrayList<IBlockType>();

	public abstract void visit(IBlockType parent, IBlockType left, IBlock thiz);

	public final void visitComposite(ICompositeBlock block) {
		IBlockType parentType = block.getType();
		parents.add(parentType);
		List<IBlock> children = block.getChildren();
		IBlockType left = IBlockType.NULL;
		for (int i = 0; i < children.size(); i++) {
			IBlock thiz = children.get(i);
			visit(parentType, left, thiz);
			thiz.accept(this);
			left = thiz.getType();
		}
		parents.remove(parents.size() - 1);
	}

	public final void visitPrimitive(IBlock block) {
	}
}
