package ru.amse.smartlang.format.parsers.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarLexer;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParser;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParserTokenTypes;
import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;
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
	
	public static void main(String[] args) throws FileNotFoundException {
		BlockGrammarLexer lexer = new BlockGrammarLexer(new FileInputStream(
				"./gentest/pascal.blocks"));
		BlockGrammarParser parser = new BlockGrammarParser(lexer);
		Map<String, BlockTypeInfo> map = new HashMap<String, BlockTypeInfo>();
		List<String> blocks = new ArrayList<String>();
		try {
			parser.all();
		} catch (RecognitionException e) {
			e.printStackTrace();
			return;
		} catch (TokenStreamException e) {
			e.printStackTrace();
			return;
		}
		BlockTypesDescription oldBTD = new BlockTypesDescription();
		try {
			oldBTD = Utils.loadBlockTypesDef(new FileInputStream("./gentest/BlockTypes.xml"));
		} catch (SAXException e) {
		} catch (IOException e) {
		} catch (ParserConfigurationException e) {
		}
		AST ast = (CommonAST) parser.getAST();
		visitAll(ast, map);
		PrintStream ps = new PrintStream(new FileOutputStream(
				"./gentest/BlockTypes.xml"));
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
