package ru.amse.smartlang.format.model;

import ru.amse.smartlang.format.impl.RulesBuilder;

public class Builders {
	public IRulesBuilder newRulesBuilder() {
		return new RulesBuilder();
	}
}
