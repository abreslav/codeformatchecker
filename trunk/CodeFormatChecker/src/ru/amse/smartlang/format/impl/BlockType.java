package ru.amse.smartlang.format.impl;

import ru.amse.smartlang.format.model.IBlockType;

public class BlockType implements IBlockType {
	private IBlockType superType;
	
	public BlockType(IBlockType superType) {
		super();
		this.superType = superType;
	}

	public IBlockType getSuperType() {
		return superType;
	}
}
