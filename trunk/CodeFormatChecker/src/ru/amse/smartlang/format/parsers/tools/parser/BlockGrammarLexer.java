// $ANTLR 2.7.7 (20060930): "blocks.g" -> "BlockGrammarLexer.java"$

package ru.amse.smartlang.format.parsers.tools.parsers;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.TokenStream;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;

public class BlockGrammarLexer extends antlr.CharScanner implements BlockGrammarParserTokenTypes, TokenStream
 {
public BlockGrammarLexer(final InputStream in) {
	this(new ByteBuffer(in));
}
public BlockGrammarLexer(final Reader in) {
	this(new CharBuffer(in));
}
public BlockGrammarLexer(final InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public BlockGrammarLexer(final LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = true;
	setCaseSensitive(true);
	literals = new Hashtable();
}

public Token nextToken() throws TokenStreamException {
	tryAgain:
	for (;;) {
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '\t':  case '\n':  case '\r':  case ' ':
				case '#':
				{
					mWS(true);
					break;
				}
				case '(':
				{
					mLPAREN(true);
					break;
				}
				case ')':
				{
					mRPAREN(true);
					break;
				}
				case '*':
				{
					mMULTI(true);
					break;
				}
				case '+':
				{
					mPLUS(true);
					break;
				}
				case ';':
				{
					mSEMI(true);
					break;
				}
				case ':':
				{
					mCOLON(true);
					break;
				}
				case 'A':  case 'B':  case 'C':  case 'D':
				case 'E':  case 'F':  case 'G':  case 'H':
				case 'I':  case 'J':  case 'K':  case 'L':
				case 'M':  case 'N':  case 'O':  case 'P':
				case 'Q':  case 'R':  case 'S':  case 'T':
				case 'U':  case 'V':  case 'W':  case 'X':
				case 'Y':  case 'Z':  case '_':  case 'a':
				case 'b':  case 'c':  case 'd':  case 'e':
				case 'f':  case 'g':  case 'h':  case 'i':
				case 'j':  case 'k':  case 'l':  case 'm':
				case 'n':  case 'o':  case 'p':  case 'q':
				case 'r':  case 's':  case 't':  case 'u':
				case 'v':  case 'w':  case 'x':  case 'y':
				case 'z':
				{
					mID(true);
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					mDIGIT(true);
					break;
				}
				case '|':
				{
					mOR(true);
					break;
				}
				case '?':
				{
					mMAY(true);
					break;
				}
				case '!':
				{
					mNOT_INCL(true);
					break;
				}
				case '{':
				{
					mLCURLY(true);
					break;
				}
				case '}':
				{
					mRCURLY(true);
					break;
				}
				case '@':
				{
					mOPTION_NAME(true);
					break;
				}
				default:
					if (((LA(1)=='"')||(LA(1)=='\''))) {
						mSTRING(true);
					}
					else if (((LA(1)=='"')||(LA(1)=='\''))) {
						mRANGE(true);
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException(LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) {
					continue tryAgain; // found SKIP token
				}
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (final RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (final CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mWS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = WS;
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		case '\n':
		{
			match('\n');
			break;
		}
		case '#':
		{
			match('#');
			{
			_loop21:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					{
					match(_tokenSet_0);
					}
				}
				else {
					break _loop21;
				}
				
			} while (true);
			}
			break;
		}
		case '\r':
		{
			match('\r');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException(LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		_ttype = Token.SKIP;
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLPAREN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = LPAREN;
		match('(');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRPAREN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = RPAREN;
		match(')');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMULTI(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = MULTI;
		match('*');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPLUS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = PLUS;
		match('+');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSEMI(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = SEMI;
		match(';');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOLON(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = COLON;
		match(':');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mID(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = ID;
		mLETTER(false);
		{
		_loop30:
		do {
			switch ( LA(1)) {
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':  case '_':  case 'a':
			case 'b':  case 'c':  case 'd':  case 'e':
			case 'f':  case 'g':  case 'h':  case 'i':
			case 'j':  case 'k':  case 'l':  case 'm':
			case 'n':  case 'o':  case 'p':  case 'q':
			case 'r':  case 's':  case 't':  case 'u':
			case 'v':  case 'w':  case 'x':  case 'y':
			case 'z':
			{
				mLETTER(false);
				break;
			}
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				mDIGIT(false);
				break;
			}
			default:
			{
				break _loop30;
			}
			}
		} while (true);
		}
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mLETTER(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = LETTER;
		switch ( LA(1)) {
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':
		{
			matchRange('a','z');
			break;
		}
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':
		{
			matchRange('A','Z');
			break;
		}
		case '_':
		{
			match('_');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException(LA(1), getFilename(), getLine(), getColumn());
		}
		}
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIGIT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = DIGIT;
		matchRange('0','9');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mOR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = OR;
		match('|');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMAY(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = MAY;
		match('?');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNOT_INCL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = NOT_INCL;
		match('!');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLCURLY(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = LCURLY;
		match('{');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRCURLY(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = RCURLY;
		match('}');
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTRING(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = STRING;
		switch ( LA(1)) {
		case '"':
		{
			match('"');
			{
			_loop41:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					{
					match(_tokenSet_1);
					}
				}
				else {
					break _loop41;
				}
				
			} while (true);
			}
			match('"');
			break;
		}
		case '\'':
		{
			match("'");
			{
			_loop44:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					{
					match(_tokenSet_2);
					}
				}
				else {
					break _loop44;
				}
				
			} while (true);
			}
			match("'");
			break;
		}
		default:
		{
			throw new NoViableAltForCharException(LA(1), getFilename(), getLine(), getColumn());
		}
		}
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mOPTION_NAME(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = OPTION_NAME;
		match('@');
		{
		_loop47:
		do {
			if ((_tokenSet_3.member(LA(1)))) {
				mLETTER(false);
			}
			else {
				break _loop47;
			}
			
		} while (true);
		}
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRANGE(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = RANGE;
		mSTRING(false);
		match("..");
		mSTRING(false);
		if ( _createToken && (_token==null) && (_ttype!=Token.SKIP) ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		final long[] data = { -9217L, -1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		final long[] data = { -17179878401L, -268435457L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		final long[] data = { -549755823105L, -268435457L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		final long[] data = { 0L, 576460745995190270L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
