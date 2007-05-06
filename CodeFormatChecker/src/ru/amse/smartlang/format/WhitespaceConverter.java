package ru.amse.smartlang.format;

import java.util.List;


public class WhitespaceConverter {
	static Whitespace toRelative(IBlock b, IBlock parent, IBlock left) {
		Whitespace whitespace = b.getWhitespace();
		if(left == IBlock.NULL) {
			return Whitespace.getInstance(whitespace.getNewLines(), whitespace.getIndents() - parent.getWhitespace().getIndents(), whitespace.getSpaces());
		}
		return Whitespace.getInstance(whitespace.getNewLines(), whitespace.getIndents() - left.getWhitespace().getIndents(), whitespace.getSpaces());
	}
	
	private static IBlockVisitor toRelConverter = new IBlockVisitor() {
		public void visitComposite(ICompositeBlock block) {
			List<IBlock> children = block.getChildren();
			if(children.isEmpty()) {
				return;
			}
			if(children.size() == 1) {
				children.get(0).accept(this);
				children.get(0).setWhitespace(Whitespace.EMPTY);
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
				c.setWhitespace(toRelative(c, block, left));
			}
			children.get(0).setWhitespace(Whitespace.EMPTY);
		}

		public void visitPrimitive(IBlock block) {
		}
	};

	static Whitespace toAbsolute(IBlock b, IBlock parent, IBlock left) {
		Whitespace whitespace = b.getWhitespace();
		if(left == IBlock.NULL) {
			return Whitespace.getInstance(whitespace.getNewLines(), whitespace.getIndents() + parent.getWhitespace().getIndents(), whitespace.getSpaces());
		}
		return Whitespace.getInstance(whitespace.getNewLines(), whitespace.getIndents() + left.getWhitespace().getIndents(), whitespace.getSpaces());
	}
	
	private static IBlockVisitor toAbsConverter = new IBlockVisitor() {
		public void visitComposite(ICompositeBlock block) {
			List<IBlock> children = block.getChildren();
			if(children.isEmpty()) {
				return;
			}
			for(int i = 0; i < children.size(); i++) {
				IBlock left;
				if(i > 0) {
					left = children.get(i - 1);
				} else {
					left = IBlock.NULL;
				}
				IBlock c = children.get(i);
				c.setWhitespace(toAbsolute(c, block, left));
				c.accept(this);
			}
		}

		public void visitPrimitive(IBlock block) {
		}
	};

	public static void toRelativeWhitespaces(IBlock root) {
		root.accept(toRelConverter);
	}
	
	public static void toAbsoluteWhitespaces(IBlock root) {
		root.accept(toAbsConverter);
	}
}
