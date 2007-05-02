package ru.amse.smartlang.format.tests;

import java.util.Collections;

import org.junit.Test;

import ru.amse.smartlang.format.Block;
import ru.amse.smartlang.format.CompositeBlock;
import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockVisitor;
import ru.amse.smartlang.format.ICompositeBlock;
import ru.amse.smartlang.format.Region;


public class BlockTest {
	@Test(expected = OKException.class)
	public void testPrimitiveAccept() {
		IBlock b1 = new Block(BlockTypes.ROOT, WhitespaceTest.SPACE, new Region(0, 0));
		b1.accept(new IBlockVisitor() {
			public void visitComposite(ICompositeBlock block) {
			}

			public void visitPrimitive(IBlock block) {
				throw new OKException();
			}
		});
	}
	
	@Test
	public void testCompositeAccept() {
		IBlock b1 = new CompositeBlock(BlockTypes.ROOT, WhitespaceTest.SPACE, new Region(0, 0), Collections.<IBlock>emptyList());
		b1.accept(new IBlockVisitor() {
			public void visitComposite(ICompositeBlock block) {
			}

			public void visitPrimitive(IBlock block) {
				throw new OKException();
			}
		});
	}
}
