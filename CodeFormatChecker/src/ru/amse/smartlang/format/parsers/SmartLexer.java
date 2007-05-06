package ru.amse.smartlang.format.parsers;

import ru.amse.smartlang.format.IRegion;
import ru.amse.smartlang.format.Region;
import ru.amse.smartlang.format.Whitespace;
import antlr.CharScanner;
import antlr.CharStreamException;
import antlr.InputBuffer;
import antlr.LexerSharedInputState;
import antlr.Token;

public abstract class SmartLexer extends CharScanner {
	private int spaces = 0;

	private int newLines = 0;

	private int lastNonZeroAbsoluteIndent = 0;

	private int offset;

	private boolean lineStart = true;

	private int whitespaceLength = -1;

	private int whitespaceOffset = 0;

	protected int indentSize = 2;

	public SmartLexer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SmartLexer(InputBuffer cb) {
		super(cb);
		// TODO Auto-generated constructor stub
	}

	public SmartLexer(LexerSharedInputState sharedState) {
		super(sharedState);
		// TODO Auto-generated constructor stub
	}

	protected Whitespace getCurrentWhitespace() {
		return Whitespace.getInstance(newLines, lastNonZeroAbsoluteIndent, spaces);
	}

	protected IRegion getCurrentWhitespaceRegion() {
		return new Region(whitespaceOffset, whitespaceLength);
	}

	protected Token makeToken(int type) {
		Token token = super.makeToken(type);
		SmartToken t = new SmartToken();
		t.setLine(token.getLine());
		t.setColumn(token.getColumn());
		t.setType(type);
		t.setWhitespaceRegion(new Region(offset, 0));
		t.setWhitespace(Whitespace.getInstance(newLines, lastNonZeroAbsoluteIndent,
				spaces));
		whitespaceLength = -1;
		newLines = 0;
		spaces = 0;
		lineStart = false;
		return t;
	}

	public void consume() throws CharStreamException {
		super.consume();
		offset++;
	}

	protected void startWhitespace() {
		if (whitespaceLength < 0) {
			whitespaceLength = 1;
			whitespaceOffset = offset;
			newLines = 0;
			spaces = 0;
		}
	}

	protected void endWhitespace() {
		whitespaceLength = 0;
		whitespaceOffset = -1;
	}

	protected void addSpace() {
		spaces++;
		if (spaces == indentSize && lineStart) {
			spaces = 0;
			lastNonZeroAbsoluteIndent++;
		}
	}

	protected void addTab() {
		if(lineStart) {
			lastNonZeroAbsoluteIndent++;
		} else {
			spaces += indentSize;
		}
	}

	protected void addNewLine() {
		newLines++;
		lastNonZeroAbsoluteIndent = 0;
		spaces = 0;
		lineStart = true;
	}
}
