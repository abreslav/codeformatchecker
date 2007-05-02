package ru.amse.smartlang.format;


/**
 * 
 */
public interface IBlockVisitor {
	void visitComposite(ICompositeBlock block);

	void visitPrimitive(IBlock block);
}
