package ru.amse.smartlang.format.tests;


import ru.amse.smartlang.format.IBlockType;
public enum BlockTypes implements IBlockType {
    IF_BLOCK()    {
        @Override
        public IBlockType getSuperType() {
            return STATEMENT;
        }
    }
    ,
    COMPOUND_STATEMNT()    {
        @Override
        public IBlockType getSuperType() {
            return STATEMENT;
        }
    }
    ,
    EXPRESSION()
    ,
    OP()
    ,
    PROCEDURE_BLOCK()
    ,
    NUMBER()    {
        @Override
        public IBlockType getSuperType() {
            return EXPRESSION;
        }
    }
    ,
    TYPE_BLOCK()
    ,
    ID()    {
        @Override
        public IBlockType getSuperType() {
            return EXPRESSION;
        }
    }
    ,
    VAR_BLOCK()
    ,
    STATEMENT()
    ,
    FUNCTION_BLOCK()
    ,
    CONST_BLOCK()
    ,
    FUNCTION_CALL()    {
        @Override
        public IBlockType getSuperType() {
            return EXPRESSION;
        }
    }
    ,
    PROGRAM()
    ,
    STRING()    {
        @Override
        public IBlockType getSuperType() {
            return EXPRESSION;
        }
    }, ELSE() {
    	@Override
    	public IBlockType getSuperType() {
    		return STATEMENT;
    	}
    }, BEGIN() {
    	@Override
    	public IBlockType getSuperType() {
    		return STATEMENT;
    	}
    }, END() {
    	@Override
    	public IBlockType getSuperType() {
    		return STATEMENT;
    	}
    }
    ;
    public IBlockType getSuperType() {
        return IBlockType.ROOT;
    }

	public boolean isSynonim() {
		return false;
	}
}
