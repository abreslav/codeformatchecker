header {
package ru.amse.smartlang.format.parsers.tools.parsers;
}

class BlockGrammarParser extends Parser;
options {
        buildAST = true;        // uses CommonAST by default
}

all     : (block | option_block) *
        ;
option_block : OPTION_NAME^ LCURLY^ (~(EOF|RCURLY))* RCURLY^
        ;

block   : ID^ COLON^ expr_0 SEMI!
        ;
expr_0  : (expr_1)*
        ;

expr_1  : expr_2 (OR expr_1)?
        ;
expr_2  : expr_3 (MAY^ | MULTI^ | PLUS^ | NOT_INCL^)?
        ;
expr_3  : ID ^
        | STRING^
        | RANGE^
        | LPAREN! expr_0 RPAREN!
        ;

class BlockGrammarLexer extends Lexer;

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

STRING
        :       '"' (~('"'|'\\'|'\n'|'\r'))* '"'
        |       "'" (~('\''|'\\'|'\n'|'\r'))* "'"
        ;

OPTION_NAME : '@' (LETTER)*
            ;
RANGE   : STRING ".." STRING
        ;
/*
class BlockGrammarWalker extends TreeParser;

expr returns [float r]
{
        float a,b;
        r=0;
}
        :       #(PLUS a=expr b=expr)   {r = a+b;}
        |       #(STAR a=expr b=expr)   {r = a*b;}
        |       i:INT                   {r = (float)Integer.parseInt(i.getText());}
        ;

*/