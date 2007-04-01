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
import antlr.debug.misc.ASTFrame;

public class BlockTypesListGenerator {
	
	public static void main(String[] args) throws FileNotFoundException {
		BlockGrammarLexer lexer = new BlockGrammarLexer(new FileInputStream(args[0]));
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
		CommonAST ast = (CommonAST) parser.getAST();
		PrintStream ps = new PrintStream(new FileOutputStream(args[1]));
		for(CommonAST cur = ast; cur.getNextSibling() != null; cur = (CommonAST) cur.getNextSibling()) {
			if(cur.getType() == BlockGrammarParserTokenTypes.COLON) {
				CommonAST block = (CommonAST) cur.getFirstChild();
				CommonAST chld = (CommonAST) block.getNextSibling();
				if(chld != null) {
					for(; chld != null; chld = (CommonAST) chld.getNextSibling()) {
						if(chld.getType() == BlockGrammarParserTokenTypes.ID && chld.getFirstChild() == null) {
							map.put(chld.getText(), block.getText());
						}
					}
				}
				blocks.add(block.getText());
			}
		}
		CommonAST r = new CommonAST();
		r.addChild(parser.getAST());
		new ASTFrame("", r).setVisible(true);
		for(String block : blocks) {
			ps.print(block);
			if(map.containsKey(block)) {
				ps.println("(" + map.get(block) + ");");
			} else {
				ps.println(";");
			}
		}
		ps.close();
	}

}
