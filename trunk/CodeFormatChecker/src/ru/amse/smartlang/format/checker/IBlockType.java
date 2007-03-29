package ru.amse.smartlang.format.checker;

public interface IBlockType {
	public static final IBlockType ROOT = new IBlockType() {
		public IBlockType getSuperType() {
			return ROOT;
		}
	};
	
	/**
	 * To specify no left context 
	 */
	public static final IBlockType NULL = new IBlockType() {
		public IBlockType getSuperType() {
			return ROOT;
		}
	};
	
	IBlockType getSuperType();
}
