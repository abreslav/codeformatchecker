package ru.amse.smartlang.format.parsers.tools;

public class BlockTypeDescription {
	private final String name;

	private final String superTypeName;

	private final boolean isBogus;

	private final boolean isSynonim;
	
	private final boolean isTerminal;

	public boolean isTerminal() {
		return isTerminal;
	}

	public BlockTypeDescription(final String name, final String superTypeName,
			final boolean isBogus, final boolean isSynonim, final boolean isTerminal) {
		this.name = name;
		this.superTypeName = superTypeName;
		this.isBogus = isBogus;
		this.isSynonim = isSynonim;
		this.isTerminal = isTerminal;
	}

	public boolean isBogus() {
		return isBogus;
	}

	public boolean isSynonim() {
		return isSynonim;
	}

	public String getName() {
		return name;
	}

	public String getSuperTypeName() {
		return superTypeName;
	}
	
	@Override
	public String toString() {
		return name + ", super=" + superTypeName;
	}
}
