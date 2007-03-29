package ru.amse.smartlang.format.checker;

import java.util.List;

public interface ICompositeBlock extends IBlock {
	List<IBlock> getChildren();
}
