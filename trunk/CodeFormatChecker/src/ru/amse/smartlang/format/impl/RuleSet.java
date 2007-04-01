package ru.amse.smartlang.format.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IRule;
import ru.amse.smartlang.format.model.IRuleSet;

public class RuleSet implements IRuleSet {
	private static final long serialVersionUID = 4233403819187326656L;

	private Map<Key, IRule> map = new HashMap<Key, IRule>();
	
	private class Key {
		public final IBlockType [] TYPES;
		private int hashCode;
		
		public Key(IBlockType parent, IBlockType left, IBlockType thiz) {
			TYPES = new IBlockType[] {parent, left, thiz };
			hashCode = calcHashCode();
		}
		public  int calcHashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + hashCode;
			result = PRIME * result + Arrays.hashCode(TYPES);
			return result;
		}
		
		@Override
		public int hashCode() {
			return hashCode;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Key other = (Key) obj;
			if (hashCode != other.hashCode)
				return false;
			if (!Arrays.equals(TYPES, other.TYPES))
				return false;
			return true;
		}
	}

	private IRule findRule(Key key) {
		IRule r;
		for(int i = 0; i != key.TYPES.length; i++) {
			r = map.get(key);
			if(r == null) {
				while(i < key.TYPES.length && key.TYPES[i] != IBlockType.ROOT) {
					i++;
				}
				if(i < key.TYPES.length) {
					key.TYPES[i] = key.TYPES[i].getSuperType();
				} else {
					return null;
				}
			} else {
				return r;
			}
		}
		return null;
	}
	
	public IRule getRule(IBlockType parent, IBlockType left, IBlockType thiz) {
		return findRule(new Key( parent, left, thiz ));
	}
	
	/**
	 * Checks is rule set contains rule for execact paramaters
	 * @param parent
	 * @param left
	 * @param thiz
	 * @return if rule conatins specified rul
	 */
	public boolean containsExactRule(IBlockType parent, IBlockType left, IBlockType thiz) {
		return map.containsKey(new Key(parent, left, thiz));
	}

	public void addRule(IRule rule) {
		map.put(new Key( rule.getParentBlockType(),
				rule.getLeftBlockType(), rule.getThisBlockType()), rule);
	}
	
	public Collection<IRule> rules() {
		return map.values();
	}
}
