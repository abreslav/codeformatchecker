header {
package ru.amse.smartlang.format.parsers.tools.parsers;
}

class BlockGrammarParser extends Parser;
options {
        k = 5;
        buildAST = true;       
}

all     : (block | option_block) *
        ;

option_block : OPTION_NAME^  ANY_TEXT^ RCURLY!
        ;

block   : ID^ COLON^ expr_1 SEMI!
        ;

expr_1  : expr_1_5 (OR expr_1)*
        ;
expr_1_5  : (expr_2)*
          ;
expr_2  : expr_3 (MAY^ | MULTI^ | PLUS^ | NOT_INCL^)?
        ;
expr_3  : ID ^
        | STRING^
        | RANGE^
        | LPAREN! expr_1 RPAREN!
        ;

class BlockGrammarLexer extends Lexer;
options {
        k = 5;
        charVocabulary = '\3'..'\377';
}

WS      :       (' '
        |       '\t'
        |       '\n'
        |       '#' (~('\n' | '\r'))*
        |       '\r')
                { _ttype = Token.SKIP; }
        ;

LPAREN: '('
        ;

RPAREN: ')'
        ;

MULTI:   '*'
        ;

PLUS:   '+'
        ;

SEMI:   ';'
        ;
COLON  :':'
       ;

ID     : LETTER (LETTER | DIGIT)*
       ;
OR     : '|'
       ;
MAY    : '?'
       ;
NOT_INCL : '!' 
         ;

ANY_TEXT :  '{'(~'}')* 
         ;

LCURLY  : '{'
        ;
RCURLY  : '}'
        ;

protected
LETTER : 'a'..'z' | 'A'..'Z' | '_'
       ;
DIGIT
        :       '0'..'9'
        ;

OPTION_NAME : '@' (LETTER)*
            ;
