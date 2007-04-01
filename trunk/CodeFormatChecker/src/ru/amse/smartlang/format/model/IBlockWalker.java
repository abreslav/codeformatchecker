package ru.amse.smartlang.format.model;

public interface IBlockWalker {
	void visitPrimitive(IBlock block);
	void visitComposite(ICompositeBlock block);
}
