package ru.amse.smartlang.format;

public interface IBlockType {
	IBlockType NULL = new IBlockType() {

		public IBlockType getSuperType() {
			return ROOT;
		}

		public boolean isSynonim() {
			return false;
		}
		
		@Override
		public String toString() {
			return "NULL_TYPE";
		}
		
	};
	
	IBlockType ROOT = new IBlockType() {

		public IBlockType getSuperType() {
			return null;
		}

		public boolean isSynonim() {
			return false;
		}
		
		@Override
		public String toString() {
			return "ROOT_TYPE";
		}
	};
	
	IBlockType getSuperType();
	
	boolean isSynonim();
}
