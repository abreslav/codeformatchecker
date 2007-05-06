package ru.amse.smartlang.format.parsers.tools;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParserTokenTypes;
import antlr.CommonAST;
import antlr.collections.AST;

public class BlockTypesListGenerator {

	private static class BlockTypeInfo {
		public String superType = null;
		public boolean isTerminal = true;
	}
	
	private static void visitAll(AST a, Map<String, BlockTypeInfo> m) {
		if(a.getType() == BlockGrammarParserTokenTypes.ID) {
			m.put(a.getText(), new BlockTypeInfo());
		}
		if(a.getNextSibling() != null) {
			visitAll(a.getNextSibling(), m);
		}
		if(a.getFirstChild() != null) {
			visitAll(a.getFirstChild(), m);
		}
			
	}
	
	public static void generateBlockTypesList(AST ast, BlockTypesDescription oldBTD, Writer writer) {
		Map<String, BlockTypeInfo> map = new HashMap<String, BlockTypeInfo>();
		List<String> blocks = new ArrayList<String>();
		visitAll(ast, map);
		PrintWriter ps = new PrintWriter(writer);
		for (AST cur = ast; cur != null; cur = (CommonAST) cur
				.getNextSibling()) {
			if (cur.getType() == BlockGrammarParserTokenTypes.COLON) {
				AST block = cur.getFirstChild();
				AST chld = block.getNextSibling();
				if (chld != null) {
					AST leftChild = null;
					for (; chld != null; chld = chld.getNextSibling()) {
						if (chld.getType() == BlockGrammarParserTokenTypes.ID
								&& chld.getFirstChild() == null
								&& (leftChild == null || leftChild.getType() == BlockGrammarParserTokenTypes.OR)
								&& (chld.getNextSibling() == null || chld
										.getNextSibling().getType() == BlockGrammarParserTokenTypes.OR)) {
							map.get(chld.getText()).superType =  block.getText();
						} 
						leftChild = chld;
					}
				}
				blocks.add(block.getText());
				map.get(block.getText()).isTerminal = false;
			}
		}
		ps.println("<blockTypes package='" + oldBTD.getPackage() + "'>");
		for (String block : map.keySet()) {
			ps.print("  <blockType name='" + block + "'");
			if (map.containsKey(block)) {
				BlockTypeDescription btd = oldBTD.getDescription(block);
				if(btd != null) {
					if(btd.getSuperTypeName() != null) {
						ps.print(" superType='" + btd.getSuperTypeName() + "' ");
					}
				    ps.print(" isSynonim='" + btd.isSynonim() + "' ");
				} else {
					if (map.get(block).superType != null) {
						ps.print(" superType='" + map.get(block).superType
								+ "' isSynonim='true'");
					}
				}
			}
			ps.println(" isTerminal='" + map.get(block).isTerminal + "' />");
		}
		ps.print("</blockTypes>");
		ps.close();
	}
	
}
