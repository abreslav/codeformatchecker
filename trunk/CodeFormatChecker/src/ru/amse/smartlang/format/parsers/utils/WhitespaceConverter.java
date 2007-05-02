package ru.amse.smartlang.format.parsers.utils;

import java.util.List;

import ru.amse.smartlang.format.IBlock;
import ru.amse.smartlang.format.IBlockVisitor;
import ru.amse.smartlang.format.ICompositeBlock;

public class WhitespaceConverter {
	private static IBlockVisitor converter = new IBlockVisitor() {
		public void visitComposite(ICompositeBlock block) {
			List<IBlock> children = block.getChildren();
			if(children.isEmpty()) {
				return;
			}
			if(children.size() == 1) {
				children.get(0).accept(this);
				children.get(0).clearWhitespace();
				return;
			} 
			for(int i = children.size() - 1; i >= 0; i--) {
				IBlock left;
				if(i > 0) {
					left = children.get(i - 1);
				} else {
					left = IBlock.NULL;
				}
				IBlock c = children.get(i);
				c.accept(this);
				c.recalculateWhitespace(block, left);
			}
			children.get(0).clearWhitespace();
		}

		public void visitPrimitive(IBlock block) {
		}
	};

	public static void convert(IBlock root) {
		root.accept(converter);
	}
}
