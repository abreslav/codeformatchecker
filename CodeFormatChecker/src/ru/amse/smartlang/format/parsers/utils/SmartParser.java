package ru.amse.smartlang.format.parsers.utils;

import java.util.List;

import ru.amse.smartlang.format.Block;
import ru.amse.smartlang.format.CompositeBlock;
import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockType;
import ru.amse.smartlang.format.IRegion;
import ru.amse.smartlang.format.Whitespace;
import antlr.LLkParser;
import antlr.ParserSharedInputState;
import antlr.Token;
import antlr.TokenBuffer;
import antlr.TokenStream;

public abstract class SmartParser extends LLkParser {

	public SmartParser(int k_) {
		super(k_);
	}

	public SmartParser(ParserSharedInputState state, int k_) {
		super(state, k_);
	}

	public SmartParser(TokenBuffer tokenBuf, int k_) {
		super(tokenBuf, k_);
	}

	public SmartParser(TokenStream lexer, int k_) {
		super(lexer, k_);
	}

	protected IBlock returnBlock(IBlockType type, List<IBlock> c) {
		return returnBlock(type, c, false);
	}

	protected IBlock returnBlock(IBlockType type, List<IBlock> c, boolean bogus) {
		if (bogus && c.size() == 1) {
			return c.get(0);
		}
		if(c.size() == 1) {
			if(c.get(0) == IBlock.NULL) {
				return IBlock.NULL;
			}
		}
		Whitespace wh = Whitespace.EMPTY;
		IRegion r = null;
		if (!c.isEmpty()) {
			wh = c.get(0).getWhitespace();
			r = c.get(0).getWhitespaceRegion();
		}
		return new CompositeBlock(type, wh, r, c);
	}

	protected IBlock pblock(IBlockType type, Token t) {
		SmartToken st = (SmartToken) t;
		return new Block(type,t.getText(), st.getWhitespace(), st.getWhitespaceRegion());
	}
}
