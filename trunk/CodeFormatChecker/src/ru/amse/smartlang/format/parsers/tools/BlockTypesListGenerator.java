package ru.amse.smartlang.format.parsers.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarLexer;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParser;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParserTokenTypes;
import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

public class BlockTypesListGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		BlockGrammarLexer lexer = new BlockGrammarLexer(new FileInputStream(
				args[0]));
		BlockGrammarParser parser = new BlockGrammarParser(lexer);
		Map<String, String> map = new HashMap<String, String>();
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
		AST ast = (CommonAST) parser.getAST();
		PrintStream ps = new PrintStream(new FileOutputStream(args[1]));
		for (AST cur = ast; cur.getNextSibling() != null; cur = (CommonAST) cur
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
								&& (chld.getNextSibling() == null 
										|| chld.getNextSibling().getType() == BlockGrammarParserTokenTypes.OR)) {
							map.put(chld.getText(), block.getText());
						}
						leftChild = chld;
					}
				}
				blocks.add(block.getText());
			}
		}
		for (String block : blocks) {
			ps.print(block);
			if (map.containsKey(block)) {
				ps.println("(" + map.get(block) + ");");
			} else {
				ps.println(";");
			}
		}
		ps.close();
	}

}
