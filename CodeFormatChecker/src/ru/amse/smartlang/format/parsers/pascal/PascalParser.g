header {
  package ru.amse.smartlang.format.parsers.pascal;  
  import ru.amse.smartlang.format.*;
  import java.util.*;
}
class MyParser extends Parser("ru.amse.smartlang.format.parsers.utils.SmartParser");

options {
  importVocab=Pascal;
  k = 2;
}
program_block returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : (i0 : PROGRAM {c.add(pblock(BlockTypes.PROGRAM, i0));}i1 : ID {c.add(pblock(BlockTypes.EXPRESSION, i1));res = returnBlock(BlockTypes.PROGRAM_BLOCK, c); })?(a1 = types_declare{c.add(a1); res = returnBlock(BlockTypes.PROGRAM_BLOCK, c); }
        | a1 = vars_declare{c.add(a1); res = returnBlock(BlockTypes.PROGRAM_BLOCK, c); })*a1 = compound_statement{c.add(a1); res = returnBlock(BlockTypes.PROGRAM_BLOCK, c); }
        ;

types_declare returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : TYPE {c.add(pblock(BlockTypes.TYPE, i0));}(a1 = single_type_decl{c.add(a1); }i1 : SEMI {c.add(pblock(BlockTypes.SEMI, i1));res = returnBlock(BlockTypes.TYPES_DECLARE, c); })*{res = returnBlock(BlockTypes.TYPES_DECLARE, c); }
        ;

vars_declare returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : VAR {c.add(pblock(BlockTypes.VAR, i0));}(a1 = single_var_declare{c.add(a1); }i1 : SEMI {c.add(pblock(BlockTypes.SEMI, i1));res = returnBlock(BlockTypes.VARS_DECLARE, c); })*{res = returnBlock(BlockTypes.VARS_DECLARE, c); }
        ;

single_var_declare returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : ID {c.add(pblock(BlockTypes.EXPRESSION, i0));}(i1 : COMMA {c.add(pblock(BlockTypes.COMMA, i1));}i2 : ID {c.add(pblock(BlockTypes.EXPRESSION, i2));res = returnBlock(BlockTypes.SINGLE_VAR_DECLARE, c); })*i3 : COLON {c.add(pblock(BlockTypes.COLON, i3));}a1 = type_decl{c.add(a1); res = returnBlock(BlockTypes.SINGLE_VAR_DECLARE, c); }
        ;

int_range returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : INT {c.add(pblock(BlockTypes.EXPRESSION, i0));}i1 : DOTDOT {c.add(pblock(BlockTypes.DOTDOT, i1));}i2 : INT {c.add(pblock(BlockTypes.EXPRESSION, i2));res = returnBlock(BlockTypes.INT_RANGE, c); }
        ;

single_type_decl returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : ID {c.add(pblock(BlockTypes.EXPRESSION, i0));}i1 : EQUALS {c.add(pblock(BlockTypes.EQUALS, i1));}a1 = type_decl{c.add(a1); res = returnBlock(BlockTypes.SINGLE_TYPE_DECL, c); }
        ;

type_decl returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : ID {c.add(pblock(BlockTypes.EXPRESSION, i0));res = returnBlock(BlockTypes.TYPE_DECL, c); }
        | i1 : RECORD {c.add(pblock(BlockTypes.RECORD, i1));}(a1 = single_var_declare{c.add(a1); }i2 : SEMI {c.add(pblock(BlockTypes.SEMI, i2));res = returnBlock(BlockTypes.TYPE_DECL, c); })*i3 : END {c.add(pblock(BlockTypes.END, i3));res = returnBlock(BlockTypes.TYPE_DECL, c); }
        | i4 : PTR {c.add(pblock(BlockTypes.PTR, i4));}a1 = type_decl{c.add(a1); res = returnBlock(BlockTypes.TYPE_DECL, c); }
        | a1 = int_range{c.add(a1); res = returnBlock(BlockTypes.TYPE_DECL, c); }
        | i5 : ARRAY {c.add(pblock(BlockTypes.ARRAY, i5));}i6 : LBRACKET {c.add(pblock(BlockTypes.LBRACKET, i6));}a1 = int_range{c.add(a1); }i7 : RBRACKET {c.add(pblock(BlockTypes.RBRACKET, i7));}i8 : OF {c.add(pblock(BlockTypes.OF, i8));}a1 = type_decl{c.add(a1); res = returnBlock(BlockTypes.TYPE_DECL, c); }
        | i9 : RCURLY {c.add(pblock(BlockTypes.RCURLY, i9));}i10 : ID {c.add(pblock(BlockTypes.EXPRESSION, i10));}(i11 : COMMA {c.add(pblock(BlockTypes.COMMA, i11));}i12 : ID {c.add(pblock(BlockTypes.EXPRESSION, i12));res = returnBlock(BlockTypes.TYPE_DECL, c); })*i13 : LCURLY {c.add(pblock(BlockTypes.LCURLY, i13));res = returnBlock(BlockTypes.TYPE_DECL, c); }
        ;

statement returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        :  { res = IBlock.NULL; }
        | a1 = expression_statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        | a1 = if_statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        | a1 = compound_statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        | a1 = for_statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        | a1 = while_statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        | a1 = repeat_statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        ;

statements returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : a1 = statement{c.add(a1); }(i0 : SEMI {c.add(pblock(BlockTypes.SEMI, i0));}a1 = statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENTS, c); })*{res = returnBlock(BlockTypes.STATEMENTS, c); }
        ;

expression_statement returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : a1 = expression{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        ;

compound_statement returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : BEGIN {c.add(pblock(BlockTypes.BEGIN, i0));}a1 = statements{c.add(a1); }i1 : END {c.add(pblock(BlockTypes.END, i1));res = returnBlock(BlockTypes.STATEMENT, c); }
        ;

if_statement returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : IF {c.add(pblock(BlockTypes.IF, i0));}a1 = expression{c.add(a1); }i1 : THEN {c.add(pblock(BlockTypes.THEN, i1));}a1 = statement{c.add(a1); }(i2 : ELSE {c.add(pblock(BlockTypes.ELSE, i2));}a1 = statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); })*{res = returnBlock(BlockTypes.STATEMENT, c); }
        ;

while_statement returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : WHILE {c.add(pblock(BlockTypes.WHILE, i0));}a1 = expression{c.add(a1); }i1 : DO {c.add(pblock(BlockTypes.DO, i1));}a1 = statement{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        ;

repeat_statement returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : REPEAT {c.add(pblock(BlockTypes.REPEAT, i0));}a1 = statements{c.add(a1); }i1 : UNTIL {c.add(pblock(BlockTypes.UNTIL, i1));}a1 = expression{c.add(a1); res = returnBlock(BlockTypes.STATEMENT, c); }
        ;

for_statement returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : FOR {c.add(pblock(BlockTypes.FOR, i0));}a1 = expression{c.add(a1); }i1 : TO {c.add(pblock(BlockTypes.TO, i1));res = returnBlock(BlockTypes.FOR_STATEMENT, c); }
        | i2 : DOWNTO {c.add(pblock(BlockTypes.DOWNTO, i2));}a1 = expression{c.add(a1); }i3 : DO {c.add(pblock(BlockTypes.DO, i3));}a1 = statement{c.add(a1); res = returnBlock(BlockTypes.FOR_STATEMENT, c); }
        ;

op returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : MULT {c.add(pblock(BlockTypes.OP, i0));res = returnBlock(BlockTypes.OP, c); }
        | i1 : PLUS {c.add(pblock(BlockTypes.OP, i1));res = returnBlock(BlockTypes.OP, c); }
        | i2 : SLASH {c.add(pblock(BlockTypes.OP, i2));res = returnBlock(BlockTypes.OP, c); }
        | i3 : ASSIGN {c.add(pblock(BlockTypes.OP, i3));res = returnBlock(BlockTypes.OP, c); }
        | i4 : LESS {c.add(pblock(BlockTypes.OP, i4));res = returnBlock(BlockTypes.OP, c); }
        | i5 : GREATER {c.add(pblock(BlockTypes.OP, i5));res = returnBlock(BlockTypes.OP, c); }
        | i6 : EQUALS {c.add(pblock(BlockTypes.EQUALS, i6));res = returnBlock(BlockTypes.OP, c); }
        ;

expression returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : a1 = prim_expression{c.add(a1); }(a1 = op{c.add(a1); }a1 = expression{c.add(a1); res = returnBlock(BlockTypes.EXPRESSION, c); })*{res = returnBlock(BlockTypes.EXPRESSION, c); }
        ;

call_expression returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : ID {c.add(pblock(BlockTypes.EXPRESSION, i0));}i1 : LCURLY {c.add(pblock(BlockTypes.LCURLY, i1));}(a1 = expression{c.add(a1); }(i2 : COMMA {c.add(pblock(BlockTypes.COMMA, i2));}a1 = expression{c.add(a1); res = returnBlock(BlockTypes.EXPRESSION, c); })*{res = returnBlock(BlockTypes.EXPRESSION, c); })?i3 : RCURLY {c.add(pblock(BlockTypes.RCURLY, i3));res = returnBlock(BlockTypes.EXPRESSION, c); }
        ;

op_expression returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : a1 = call_expression{c.add(a1); res = returnBlock(BlockTypes.EXPRESSION, c); }
        | a1 = prim_expression{c.add(a1); }(a1 = op{c.add(a1); }a1 = expression{c.add(a1); res = returnBlock(BlockTypes.EXPRESSION, c); }
        | a1 = prim_expression{c.add(a1); res = returnBlock(BlockTypes.EXPRESSION, c); }
        | a1 = call_expression{c.add(a1); res = returnBlock(BlockTypes.EXPRESSION, c); })*{res = returnBlock(BlockTypes.EXPRESSION, c); }
        ;

prim_expression returns[IBlock res = null]
{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }
        : i0 : INT {c.add(pblock(BlockTypes.EXPRESSION, i0));res = returnBlock(BlockTypes.EXPRESSION, c); }
        | i1 : ID {c.add(pblock(BlockTypes.EXPRESSION, i1));res = returnBlock(BlockTypes.EXPRESSION, c); }
        ;

