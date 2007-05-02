package ru.amse.smartlang.format;



public final class Region implements IRegion {
	private final int offset;

	private final int length;

	public Region(final int offset, final int length) {
		super();
		this.offset = offset;
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public int getOffset() {
		return offset;
	}
}
