header {
package ru.amse.smartlang.format.parsers.pascal;
}
class PascalLexer extends Lexer("ru.amse.smartlang.format.parsers.utils.SmartLexer");
options {
  exportVocab=Pascal;
  k = 4;
}
{
}
WS      :       (' ' { startWhitespace(); addSpace(); } 
        |       '\t' { startWhitespace(); addTab(); }
        |       '\n' { startWhitespace(); addNewLine(); }
        |       '\r' {  }
                )
                { _ttype = Token.SKIP; }
        ;

LPAREN: '(' 
        ;

RPAREN: ')'
        ;
        
LBRACKET : '[';
RBRACKET : ']';
PTR      : '^';
COMMA    : ',';

MULT : '*';
PLUS : '+';
SLASH : '/';
ASSIGN : ":=";
LESS : '<';
GREATER : '>';
COLON    : ':';
EQUALS   : '=';
SEMI     : ';' ;
IF    : "if";
ELSE  : "else";
THEN  : "then";
BEGIN : "begin";
END   : "end";
WHILE : "while";
DO    : "do";
REPEAT : "repeat";
UNTIL : "until";
FOR   : "for";
TO    : "to";
DOWNTO : "downto";
PROGRAM : "program";
VAR     : "var";
TYPE    : "type";
ARRAY   : "array";
OF      : "of";
CASE    : "case";
RECORD  : "record";

DOTDOT  : "..";


protected
DIGIT
        :       '0'..'9'
        ;

INT     :       (DIGIT)+
        ;
ID    : ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9')*;

