// $ANTLR 2.7.7 (20060930): "blocks.g" -> "BlockGrammarParser.java"$

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

public class BlockGrammarParser extends antlr.LLkParser       implements BlockGrammarParserTokenTypes
 {

protected BlockGrammarParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BlockGrammarParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected BlockGrammarParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public BlockGrammarParser(TokenStream lexer) {
  this(lexer,1);
}

public BlockGrammarParser(ParserSharedInputState state) {
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
			_loop3:
			do {
				switch ( LA(1)) {
				case ID:
				{
					block();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case OPTION_NAME:
				{
					option_block();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					break _loop3;
				}
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
	
	public final void block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST block_AST = null;
		
		try {      // for error handling
			AST tmp1_AST = null;
			tmp1_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp1_AST);
			match(ID);
			AST tmp2_AST = null;
			tmp2_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp2_AST);
			match(COLON);
			expr_0();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEMI);
			block_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = block_AST;
	}
	
	public final void option_block() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST option_block_AST = null;
		
		try {      // for error handling
			AST tmp4_AST = null;
			tmp4_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp4_AST);
			match(OPTION_NAME);
			AST tmp5_AST = null;
			tmp5_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp5_AST);
			match(LCURLY);
			{
			_loop7:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					{
					AST tmp6_AST = null;
					tmp6_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp6_AST);
					match(_tokenSet_2);
					}
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			AST tmp7_AST = null;
			tmp7_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp7_AST);
			match(RCURLY);
			option_block_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = option_block_AST;
	}
	
	public final void expr_0() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_0_AST = null;
		
		try {      // for error handling
			{
			_loop11:
			do {
				if ((_tokenSet_3.member(LA(1)))) {
					expr_1();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop11;
				}
				
			} while (true);
			}
			expr_0_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
		returnAST = expr_0_AST;
	}
	
	public final void expr_1() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_1_AST = null;
		
		try {      // for error handling
			expr_2();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case OR:
			{
				AST tmp8_AST = null;
				tmp8_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp8_AST);
				match(OR);
				expr_1();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case ID:
			case SEMI:
			case STRING:
			case RANGE:
			case LPAREN:
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expr_1_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
		returnAST = expr_1_AST;
	}
	
	public final void expr_2() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_2_AST = null;
		
		try {      // for error handling
			expr_3();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case MAY:
			{
				AST tmp9_AST = null;
				tmp9_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp9_AST);
				match(MAY);
				break;
			}
			case MULTI:
			{
				AST tmp10_AST = null;
				tmp10_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp10_AST);
				match(MULTI);
				break;
			}
			case PLUS:
			{
				AST tmp11_AST = null;
				tmp11_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp11_AST);
				match(PLUS);
				break;
			}
			case NOT_INCL:
			{
				AST tmp12_AST = null;
				tmp12_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp12_AST);
				match(NOT_INCL);
				break;
			}
			case ID:
			case SEMI:
			case OR:
			case STRING:
			case RANGE:
			case LPAREN:
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			expr_2_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
		returnAST = expr_2_AST;
	}
	
	public final void expr_3() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expr_3_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				AST tmp13_AST = null;
				tmp13_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp13_AST);
				match(ID);
				expr_3_AST = (AST)currentAST.root;
				break;
			}
			case STRING:
			{
				AST tmp14_AST = null;
				tmp14_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp14_AST);
				match(STRING);
				expr_3_AST = (AST)currentAST.root;
				break;
			}
			case RANGE:
			{
				AST tmp15_AST = null;
				tmp15_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp15_AST);
				match(RANGE);
				expr_3_AST = (AST)currentAST.root;
				break;
			}
			case LPAREN:
			{
				match(LPAREN);
				expr_0();
				astFactory.addASTChild(currentAST, returnAST);
				match(RPAREN);
				expr_3_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
		returnAST = expr_3_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"OPTION_NAME",
		"LCURLY",
		"RCURLY",
		"ID",
		"COLON",
		"SEMI",
		"OR",
		"MAY",
		"MULTI",
		"PLUS",
		"NOT_INCL",
		"STRING",
		"RANGE",
		"LPAREN",
		"RPAREN",
		"WS",
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
		long[] data = { 146L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 4194226L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 229504L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 262656L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 492160L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 493184L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 523904L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	
	}
