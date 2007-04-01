header {
package ru.amse.smartlang.format.parsers.tools.parsers;
}

class BlockTypeGrammarParser extends Parser;
options {
        buildAST = true;        // uses CommonAST by default
}

all     : 
          (preferences_block)? (blocktype_def) *
        ;

preferences_block: LCURLY^ (preference_block) * RCURLY!
                 ;
preference_block : ID^ ASSIGN! ID^ SEMI!
                 ;
blocktype_def   : ID^ (LPAREN ID^ RPAREN!)? SEMI!
        ;
class BlockTypeGrammarLexer extends Lexer;

WS      :       (' '
        |       '\t'
        |       '\n'
        |       '#' (~('\n' | '\r'))*
        |       '\r')
                { _ttype = Token.SKIP; }
        ;

ASSIGN : '='
       ;
LPAREN: '('
        ;

RPAREN: ')'
        ;

SEMI:   ';'
        ;
COLON  :':'
       ;
LCURLY : '{'
       ;
RCURLY : '}'
       ;

ID     : LETTER (LETTER | DIGIT | '.')*
       ;
INT    : (DIGIT)+
       ;
protected
LETTER : 'a'..'z' | 'A'..'Z' | '_'
       ;
DIGIT
        :       '0'..'9'
        ;


