package ru.amse.smartlang.format.parsers.tools;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BlockTypesDescription {
	
	private final Map<String, BlockTypeDescription> blockTypes;
	private String genPackage;
	
	public BlockTypesDescription() {
		blockTypes = new HashMap<String, BlockTypeDescription>();
	}
	
	public BlockTypeDescription getDescription(String typeName) {
		return blockTypes.get(typeName);
	}

	public Collection<BlockTypeDescription> getBlockTypes() {
		return blockTypes.values();
	}	
	
	public String getPackage() {
		return genPackage;
	}

	/* package */ void addDef(BlockTypeDescription descr) {
		blockTypes.put(descr.getName(), descr);
	}
	/* package */ void setPackage(String p) {
		genPackage = p;
	}
}
