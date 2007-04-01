// $ANTLR 2.7.7 (20060930): "blocktypesdef.g" -> "BlockTypeGrammarParser.java"$

package ru.amse.smartlang.format.parsers.tools.parsers;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class BlockTypeGrammarParser extends antlr.LLkParser       implements BlockTypeGrammarParserTokenTypes
 {

protected BlockTypeGrammarParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BlockTypeGrammarParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected BlockTypeGrammarParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BlockTypeGrammarParser(TokenStream lexer) {
  this(lexer,1);
}

public BlockTypeGrammarParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void all() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST all_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LCURLY:
			{
				preferences_block();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			_loop4:
			do {
				if ((LA(1)==ID)) {
					blocktype_def();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			all_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = all_AST;
	}
	
	public final void preferences_block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST preferences_block_AST = null;
		
		try {      // for error handling
			AST tmp1_AST = null;
			tmp1_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp1_AST);
			match(LCURLY);
			{
			_loop7:
			do {
				if ((LA(1)==ID)) {
					preference_block();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			match(RCURLY);
			preferences_block_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = preferences_block_AST;
	}
	
	public final void blocktype_def() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST blocktype_def_AST = null;
		
		try {      // for error handling
			AST tmp3_AST = null;
			tmp3_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp3_AST);
			match(ID);
			{
			switch ( LA(1)) {
			case LPAREN:
			{
				AST tmp4_AST = null;
				tmp4_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp4_AST);
				match(LPAREN);
				AST tmp5_AST = null;
				tmp5_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp5_AST);
				match(ID);
				match(RPAREN);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			blocktype_def_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = blocktype_def_AST;
	}
	
	public final void preference_block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST preference_block_AST = null;
		
		try {      // for error handling
			AST tmp8_AST = null;
			tmp8_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp8_AST);
			match(ID);
			match(ASSIGN);
			AST tmp10_AST = null;
			tmp10_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp10_AST);
			match(ID);
			match(SEMI);
			preference_block_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = preference_block_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"LCURLY",
		"RCURLY",
		"ID",
		"ASSIGN",
		"SEMI",
		"LPAREN",
		"RPAREN",
		"WS",
		"COLON",
		"INT",
		"LETTER",
		"DIGIT"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 66L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 96L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	
	}
