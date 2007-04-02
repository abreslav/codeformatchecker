package ru.amse.smartlang.format.model;

public interface IBlockVisitor {
	void visitPrimitive(IBlock block);
	void visitComposite(ICompositeBlock block);
}
