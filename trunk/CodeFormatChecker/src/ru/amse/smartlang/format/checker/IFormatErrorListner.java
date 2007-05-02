package ru.amse.smartlang.format.checker;

import java.util.List;

import ru.amse.smartlang.format.IRegion;
import ru.amse.smartlang.format.Pattern;
import ru.amse.smartlang.format.Whitespace;

public interface IFormatErrorListner {
	/**
	 * Notified when invalid whitespace found
	 * @param region
	 * @param found
	 * @param expected
	 */
	void invalidWhitespace(Pattern p, IRegion region, Whitespace found, List<Whitespace> expected);
}
