package ru.amse.smartlang.format.parsers.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParserTokenTypes;
import antlr.collections.AST;

public class BlockTypesClassesGenerator {
	public static Map<String, String> buildBlockTypesInfo(AST ast) {
		Map<String, String> map = new HashMap<String, String>();
		for(AST cur = ast; cur.getNextSibling() != null; cur = cur.getNextSibling()) {
			if(cur.getType() == BlockGrammarParserTokenTypes.COLON) {
				AST block = cur.getFirstChild();
				AST chld = block.getNextSibling();
				if(chld != null) {
					for(; chld != null; chld = chld.getNextSibling()) {
						if(chld.getType() == BlockGrammarParserTokenTypes.ID && chld.getFirstChild() == null) {
							map.put(chld.getText(), block.getText());
						}
					}
				}
				if(!map.containsKey(block.getText())) {
					map.put(block.getText(), null);
				}
			}
		}
		return map;
	}
	
	public static void generate(BlockTypesDef def, String header, OutputStream stream) throws FileNotFoundException {
		PrintStream ps = new PrintStream(stream);
		Map<String, String> attr = def.getAttributes();
		if(attr.containsKey("package")) {
			ps.println("package " + attr.get("package") + ";");
		}
		ps.println("import ru.amse.smartlang.format.model.IBlockType; ");
		ps.println("public enum BlockTypes implements IBlockType {");
		for(Iterator<BlockTypesDef.BlockTypeDef> i = def.getBlockTypes().iterator(); i.hasNext();) {
			BlockTypesDef.BlockTypeDef e = i.next();
			String blockTypeName = e.BLOCK_TYPE_NAME.toUpperCase();
			ps.print("    " + blockTypeName + "()");
			if(e.BLOCK_TYPE_SUPER_NAME != null) {
				ps.println("    {");
				ps.println("        @Override");
				ps.println("        public IBlockType getSuperType() {");
				ps.println("            return " + e.BLOCK_TYPE_SUPER_NAME.toUpperCase() + ";");
				ps.println("        }");
				ps.println("    }");
			} else {
				ps.println();
			}
			if(i.hasNext()) {
				ps.println("    ,");
			} else {
				ps.println("    ;");
			}
		}
		ps.println("    public IBlockType getSuperType() {");
		ps.println("        return IBlockType.ROOT;");
		ps.println("    }");
		ps.println("}");
	}
	
	public static void main(String[] args) throws IOException {
		BlockTypesDef def = Utils.parseBlockTypesDef(new FileInputStream(args[0]));
		generate(def, "", new FileOutputStream("D:\\BlockTypes.java"));
	}
}
