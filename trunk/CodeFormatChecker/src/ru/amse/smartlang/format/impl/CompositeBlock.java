package ru.amse.smartlang.format.impl;

import java.util.ArrayList;
import java.util.List;

import ru.amse.smartlang.format.model.IBlock;
import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IBlockWalker;
import ru.amse.smartlang.format.model.ICompositeBlock;
import ru.amse.smartlang.format.model.IRegion;
import ru.amse.smartlang.format.model.Whitespace;

public class CompositeBlock extends Block implements ICompositeBlock {
	private List<IBlock> children;
	
	public CompositeBlock(IBlockType blockType, Whitespace whitespace, IRegion wRegion) {
		super(blockType, whitespace, wRegion);
		children = new ArrayList<IBlock>();
	}

	public List<IBlock> getChildren() {
		return children;
	}
	
	@Override
	public void visit(IBlockWalker walker) {
		walker.visitComposite(this);
	}

}
