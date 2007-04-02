// $ANTLR 2.7.7 (20060930): "blocktypesdef.g" -> "BlockTypeGrammarLexer.java"$

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

public class BlockTypeGrammarLexer extends antlr.CharScanner implements BlockTypeGrammarParserTokenTypes, TokenStream
 {
public BlockTypeGrammarLexer(final InputStream in) {
	this(new ByteBuffer(in));
}
public BlockTypeGrammarLexer(final Reader in) {
	this(new CharBuffer(in));
}
public BlockTypeGrammarLexer(final InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public BlockTypeGrammarLexer(final LexerSharedInputState state) {
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
				case '=':
				{
					mASSIGN(true);
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
				default:
					if ((((LA(1) >= '0') && (LA(1) <= '9')))) {
						mDIGIT(true);
					}
					else if ((((LA(1) >= '0') && (LA(1) <= '9')))) {
						mINT(true);
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
			_loop15:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					{
					match(_tokenSet_0);
					}
				}
				else {
					break _loop15;
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
	
	public final void mASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = ASSIGN;
		match('=');
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
	
	public final void mID(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = ID;
		mLETTER(false);
		{
		_loop25:
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
			case '.':
			{
				match('.');
				break;
			}
			default:
			{
				break _loop25;
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
	
	public final void mINT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; final int _begin=text.length();
		_ttype = INT;
		{
		int _cnt28=0;
		_loop28:
		do {
			if ((((LA(1) >= '0') && (LA(1) <= '9')))) {
				mDIGIT(false);
			}
			else {
				if ( _cnt28>=1 ) { break _loop28; } else {throw new NoViableAltForCharException(LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt28++;
		} while (true);
		}
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
	
	}
