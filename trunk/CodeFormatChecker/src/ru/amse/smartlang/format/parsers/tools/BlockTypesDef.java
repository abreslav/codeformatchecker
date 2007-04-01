package ru.amse.smartlang.format.parsers.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockTypesDef {
	public static final class BlockTypeDef {
		public final String BLOCK_TYPE_NAME;
		public final String BLOCK_TYPE_SUPER_NAME;
		
		public BlockTypeDef(final String block_type_name, final String block_type_super_name) {
			super();
			BLOCK_TYPE_NAME = block_type_name;
			BLOCK_TYPE_SUPER_NAME = block_type_super_name;
		}
	}
	
	private final List<BlockTypeDef> blockTypes;
	private final Map<String, String> attributes;
	
	public BlockTypesDef() {
		blockTypes = new ArrayList<BlockTypeDef>();
		attributes = new HashMap<String, String>();
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public List<BlockTypeDef> getBlockTypes() {
		return blockTypes;
	}	
	
	/* package */ void addDef(String typeName, String superName) {
		blockTypes.add(new BlockTypeDef(typeName, superName));
	}
	/* package */ void setAttribute(String key, String val) {
		attributes.put(key, val);
	}
}
