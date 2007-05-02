header {
package ru.amse.smartlang.format.parsers.pascal;
import ru.amse.smartlang.format.*;
import antlr.*;
import java.util.*;
}

class CalcParser extends Parser("ru.amse.smartlang.format.parsers.utils.SmartParser");
{
}



statement returns [IBlock res = null] 
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
          : a1 = expression_statement { c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c, true); }
          | a1 = compound_statement { c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c, true); }
          | a1 = if_statement { c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c, true); }
          | { res = IBlock.NULL; }
          ;


expression_statement returns [IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
          : a1 = expression { c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
          ;
expression  returns [IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1;}
           : a1 = prim_expression { c.add(a1); } (i : OP {c.add(pblock(BlockTypes.OP, i)); } a1 = expression {c.add(a1); })* { res = returnBlock(BlockTypes.EXPRESSION, c, true); }
           ;
prim_expression returns [IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
           : i : INT { res = pblock(BlockTypes.EXPRESSION, i); }        
           | i1 : ID { res = pblock(BlockTypes.EXPRESSION, i1); }
           ;

compound_statement returns [IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
          : i : BEGIN { c.add(pblock(BlockTypes.BEGIN, i));} 
            a1 = statement { c.add(a1); } 
            (SEMI a1 = statement { c.add(a1 ); })* 
            i1 : END { c.add(pblock(BlockTypes.END, i1)); res = returnBlock(BlockTypes.COMPOUND_STATEMENT, c); }
          ;
if_statement  returns [IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
          : i : IF { c.add(pblock(BlockTypes.IF, i)); }
            a1 = expression { c.add(a1); }
            i1 : THEN { c.add(pblock(BlockTypes.THEN, i)); }
            a1 = statement { c.add(a1); }
            (i2 : ELSE  {c.add(pblock(BlockTypes.ELSE, i)); } a1 = statement {c.add(a1); } )*
            { res = returnBlock(BlockTypes.IF_STATEMENT, c); }
          ;

class CalcLexer extends Lexer("ru.amse.smartlang.format.parsers.utils.SmartLexer");
options {
  k = 3;
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

OP : '*' | '+' | '/' | ":=" | '=' | '<' | '>';
BRACES   : '(' | ')' | '[' | ']';
SEMI     : ';' ;
IF    : "if";
ELSE  : "else";
THEN  : "then";
BEGIN : "begin";
END   : "end";
protected
DIGIT
        :       '0'..'9'
        ;

INT     :       (DIGIT)+
        ;
ID    : ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9')*;

