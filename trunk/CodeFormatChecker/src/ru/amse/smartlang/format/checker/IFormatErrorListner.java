package ru.amse.smartlang.format.checker;

import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IRegion;
import ru.amse.smartlang.format.model.Whitespace;

public interface IFormatErrorListner {
	/**
	 * Notified when invalid whitespace found
	 * @param region
	 * @param found
	 * @param expected
	 */
	void notify(IRegion region, Whitespace found, Whitespace expected);
	/**
	 * Notified when no there was not found any rule to specified block sequence
	 * @param parent
	 * @param left
	 * @param thiz
	 */
	void notify(IBlockType parent, IBlockType left, IBlockType thiz);
}
