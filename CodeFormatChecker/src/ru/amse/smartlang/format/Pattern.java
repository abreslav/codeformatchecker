package ru.amse.smartlang.format;



public final class Pattern {
	private final IBlockType leftBlockType;

	private final IBlockType parentBlockType;

	private final IBlockType thisBlockType;

	public Pattern(IBlockType parentBlockType, IBlockType leftBlockType,
			IBlockType thisBlockType) {
		assert(leftBlockType != null);
		assert(parentBlockType != null);
		assert(thisBlockType != null);
		
		this.leftBlockType = leftBlockType;
		this.parentBlockType = parentBlockType;
		this.thisBlockType = thisBlockType;
	}

	public IBlockType getLeftBlockType() {
		return leftBlockType;
	}

	public IBlockType getParentBlockType() {
		return parentBlockType;
	}

	public IBlockType getThisBlockType() {
		return thisBlockType;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + leftBlockType.hashCode();
		result = PRIME * result + parentBlockType.hashCode();
		result = PRIME * result + thisBlockType.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Pattern other = (Pattern) obj;
		return parentBlockType == other.parentBlockType &&
		       thisBlockType == other.thisBlockType &&
		       leftBlockType == other.leftBlockType;
	}
	
	@Override
	public String toString() {
		return parentBlockType + "(" + leftBlockType + ", " + thisBlockType + ")";
	}
}
