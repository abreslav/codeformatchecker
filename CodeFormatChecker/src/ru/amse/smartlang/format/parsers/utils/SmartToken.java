package ru.amse.smartlang.format.parsers.utils;

import ru.amse.smartlang.format.IRegion;
import ru.amse.smartlang.format.Whitespace;
import antlr.CommonToken;

public class SmartToken extends CommonToken {
        private Whitespace whitespace;

        private IRegion whitespaceRegion;

        public Whitespace getWhitespace() {
                return whitespace;
        }

        public void setWhitespace(Whitespace whitespace) {
                this.whitespace = whitespace;
        }

		public IRegion getWhitespaceRegion() {
			return whitespaceRegion;
		}

		public void setWhitespaceRegion(IRegion whitespaceRegion) {
			this.whitespaceRegion = whitespaceRegion;
		}
}
