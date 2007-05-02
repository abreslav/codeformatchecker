package ru.amse.smartlang.format.parsers.pascal;
import ru.amse.smartlang.format.*;
public enum BlockTypes implements IBlockType {
    COMMA {
    },
    TYPE {
    },
    OP_EXPRESSION {
        public IBlockType getSuperType() { return EXPRESSION; }
    },
    PTR {
    },
    IF_STATEMENT {
        public IBlockType getSuperType() { return STATEMENT; }
    },
    COMPOUND_STATEMENT {
        public IBlockType getSuperType() { return STATEMENT; }
    },
    TYPE_DECL {
    },
    ARRAY {
    },
    END {
    },
    SINGLE_TYPE_DECL {
    },
    EXPRESSION {
    },
    WHILE_STATEMENT {
        public IBlockType getSuperType() { return STATEMENT; }
    },
    THEN {
    },
    STATEMENT {
    },
    PROGRAM_BLOCK {
    },
    LBRACKET {
    },
    VARS_DECLARE {
    },
    SINGLE_VAR_DECLARE {
    },
    PROGRAM {
    },
    TYPES_DECLARE {
    },
    RCURLY {
    },
    TO {
    },
    LESS {
        public IBlockType getSuperType() { return OP; }
    },
    LCURLY {
    },
    INT {
        public IBlockType getSuperType() { return PRIM_EXPRESSION; }
    },
    ASSIGN {
        public IBlockType getSuperType() { return OP; }
    },
    RBRACKET {
    },
    UNTIL {
    },
    GREATER {
        public IBlockType getSuperType() { return OP; }
    },
    PLUS {
        public IBlockType getSuperType() { return OP; }
    },
    RECORD {
    },
    MULT {
        public IBlockType getSuperType() { return OP; }
    },
    EXPRESSION_STATEMENT {
        public IBlockType getSuperType() { return STATEMENT; }
    },
    ID {
        public IBlockType getSuperType() { return PRIM_EXPRESSION; }
    },
    OP {
    },
    STATEMENTS {
    },
    INT_RANGE {
    },
    DO {
    },
    SLASH {
        public IBlockType getSuperType() { return OP; }
    },
    WHILE {
    },
    OF {
    },
    REPEAT_STATEMENT {
        public IBlockType getSuperType() { return STATEMENT; }
    },
    REPEAT {
    },
    BEGIN {
    },
    DOWNTO {
    },
    SEMI {
    },
    VAR {
    },
    EQUALS {
    },
    ELSE {
    },
    IF {
    },
    CALL_EXPRESSION {
        public IBlockType getSuperType() { return OP_EXPRESSION; }
    },
    FOR_STATEMENT {
        public IBlockType getSuperType() { return STATEMENT; }
    },
    FOR {
    },
    COLON {
    },
    DOTDOT {
    },
    PRIM_EXPRESSION {
        public IBlockType getSuperType() { return OP_EXPRESSION; }
    },
    NONE;
    public IBlockType getSuperType() { return IBlockType.ROOT; }
    public boolean isSynonim() { return false; }
}
