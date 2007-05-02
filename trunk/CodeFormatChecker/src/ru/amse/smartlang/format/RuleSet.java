package ru.amse.smartlang.format;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RuleSet implements IRuleSet {
	
	private class Rule {
		List<Whitespace> whitespaces;
		boolean firm = true;
		
		public Rule(Whitespace wh) {
			whitespaces = Collections.singletonList(wh); 
		}
		
		public Rule(Whitespace wh, boolean firm) {
			whitespaces = Collections.singletonList(wh);
			this.firm = firm;
		}
		
		public void add(Whitespace wh) {
			if(whitespaces.size() == 1) {
				whitespaces = new ArrayList<Whitespace>(whitespaces);
			} 
			int i;
			for(i = 0; i < whitespaces.size() && whitespaces.get(i).compareTo(wh) < 0; i++) {
			}
			whitespaces.add(i, wh);
		}
		
		public Whitespace findNearest(Whitespace wh) {
			int i;
			for(i = 0; i < whitespaces.size() && whitespaces.get(i).compareTo(wh) < 0; i++) {
			}
			if(i > whitespaces.size()) {
				return whitespaces.get(whitespaces.size() - 1);
			}
			return whitespaces.get(i);
		}
		
		public boolean validWhitespace(Whitespace wh) {
			return whitespaces.contains(wh);
		}
	}
	
	private static IBlockType commonType(IBlockType l, IBlockType r) {
		Set<IBlockType> ls = new HashSet<IBlockType>();
		for(IBlockType p = l; p != IBlockType.ROOT; p = p.getSuperType()) {
			ls.add(p);
		}
		for(IBlockType p = r; p != IBlockType.ROOT; p = p.getSuperType()) {
			if(ls.contains(p)) {
				return p;
			}
		}
		return IBlockType.ROOT;
	}
	
	private HashMap<Pattern, Rule> rules = new HashMap<Pattern, Rule>();
	
	public Collection<Map.Entry<Pattern, List<Whitespace>>> getMultipatterns() {
//		List<Map.Entry<Pattern, List<Whitespace>>> res = new ArrayList<Map.Entry<Pattern,List<Whitespace>>>();
//		for (Map.Entry<Pattern, List<Whitespace>> entry : patterns.entrySet()) {
//			if(entry.getValue().size() > 1) {
//				res.add(entry);
//			}
//		}
//		return res;
		return null;
	}
	
	public void setMultipattern(Pattern p, List<Whitespace> lw) {
//		patterns.put(p, lw);
	}
	
	public void addPattern(Pattern pattern, Whitespace w) {
		Rule rule = rules.get(pattern);
		if(rule == null || !rule.firm) {
			rules.put(pattern, new Rule(w));
		} else {
			rule.add(w);
		}
		IBlockType common = commonType(pattern.getLeftBlockType(), pattern.getThisBlockType());
		/*if(common != IBlockType.ROOT) */{
			Pattern cp = new Pattern(pattern.getParentBlockType(), common, common);
			Rule r2 = rules.get(cp);
			if(r2 == null) {
				rules.put(cp, new Rule(w, false));
			} else {
				if(!r2.firm) {
					r2.add(w);
				}
			}
		}
		
	}

	public boolean isValidWhitespace(Pattern p, Whitespace w) {
		Rule rule = rules.get(p);
		return rule == null || rule.validWhitespace(w);
	}
	
	public List<Whitespace> getWhitespacesFor(Pattern pattern) {
		Rule rule = rules.get(pattern);
		if(rule == null) {
			return Collections.emptyList();
		}
		return rule.whitespaces;
	}

	public Whitespace findNearestFor(Pattern p, Whitespace sp) {
		return rules.get(p).findNearest(sp);
	}
}
