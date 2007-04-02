package ru.amse.smartlang.format.model;

import ru.amse.smartlang.format.impl.RulesConstructor;

public class Builders {
	public IRulesConstructor newRulesBuilder() {
		return new RulesConstructor();
	}
}
