package ru.amse.smartlang.format.parsers.tools;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarLexer;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParser;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockTypeGrammarLexer;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockTypeGrammarParser;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockTypeGrammarParserTokenTypes;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

public final class Utils {
	public static BlockTypesDef parseBlockTypesDef(InputStream is) {
		BlockTypesDef result = new BlockTypesDef();
		BlockTypeGrammarLexer lexer = new BlockTypeGrammarLexer(is);
		BlockTypeGrammarParser parser = new BlockTypeGrammarParser(lexer);
		Map<String, String> map = new HashMap<String, String>();
		try {
			parser.all();
		} catch (RecognitionException e) {
			return null;
		} catch (TokenStreamException e) {
			return null;
		}
		AST a = parser.getAST();
		if(a.getType() == BlockTypeGrammarParserTokenTypes.LCURLY) {
			for(AST p = a.getFirstChild(); p != null; p = p.getNextSibling()) {
				result.setAttribute(p.getFirstChild().getText(), p.getText());
			}
			a = a.getNextSibling();
		}
		for(; a != null; a = a.getNextSibling()) {
			if(a.getFirstChild() == null) {
				map.put(a.getText(), null);
			} else {
				map.put(a.getFirstChild().getText(), a.getText());
			}
		}
		for(Map.Entry<String, String> e : map.entrySet()) {
			result.addDef(e.getKey(), e.getValue());
		}
		return result;
	}
	
	public static AST parseBlocksDef(InputStream is) {
		BlockGrammarLexer lexer = new BlockGrammarLexer(is);
		BlockGrammarParser parser = new BlockGrammarParser(lexer);
		try {
			parser.all();
		} catch (RecognitionException e) {
			return null;
		} catch (TokenStreamException e) {
			return null;
		}
		return parser.getAST();
	}
	
	
}
