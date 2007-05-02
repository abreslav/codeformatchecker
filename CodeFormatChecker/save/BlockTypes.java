package ru.amse.smartlang.format.parsers.pascal;

import ru.amse.smartlang.format.IBlockType;

public enum BlockTypes implements IBlockType {
	STATEMENT,
	IF,
	IF_STATEMENT,
	OP,
	THEN,
	BEGIN,
	END,
	ELSE,
	EXPRESSION,
	INT,
	ID,
	COMPOUND_STATEMENT () {
		@Override
		public IBlockType getSuperType() {
			return STATEMENT;
		}
	}
	;

	public IBlockType getSuperType() {
		return ROOT;
	}

	public boolean isSynonim() {
		return false;
	}
	
}
