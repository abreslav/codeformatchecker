package ru.amse.smartlang.format.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import ru.amse.smartlang.format.Whitespace;

public class WhitespaceTest {
	/* package */ static final Whitespace SPACE = Whitespace.getInstance(0, 0, 1);
	/* package */ static final Whitespace INDENT = Whitespace.getInstance(0, 1, 0);
	/* package */ static final Whitespace NEW_LINE = Whitespace.getInstance(1, 0, 0);
	
	@Test
	public void testFabric() {
		Whitespace w1 = Whitespace.getInstance(2, 2, 2);
		Whitespace w2 = Whitespace.getInstance(1, 2, 2);
		Whitespace w3 = Whitespace.getInstance(2, 2, 2);
		assertTrue(w1 != w2);
		assertTrue(!w1.equals(2));
		assertTrue(w1 == w3);
		assertEquals(w1, w3);
	}
}
