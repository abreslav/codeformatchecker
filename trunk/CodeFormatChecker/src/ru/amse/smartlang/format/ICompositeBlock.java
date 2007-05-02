package ru.amse.smartlang.format;

import java.util.List;

public interface ICompositeBlock extends IBlock {
	List<IBlock> getChildren();	
}
