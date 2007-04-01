package ru.amse.smartlang.format.checker;

import ru.amse.smartlang.format.model.IRegion;
import ru.amse.smartlang.format.model.Whitespace;

public interface IFormatErrorListner {
	void notify(IRegion region, Whitespace found, Whitespace expected);
}
