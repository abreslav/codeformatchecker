package ru.amse.smartlang.format.model;

import java.util.List;

public interface ICompositeBlock extends IBlock {
	/**
	 * Return subblocks.
	 */
	List<IBlock> getChildren();
}
