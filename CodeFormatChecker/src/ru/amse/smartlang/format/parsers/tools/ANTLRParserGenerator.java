package ru.amse.smartlang.format.parsers.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ru.amse.smartlang.format.IBlockType;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParserTokenTypes;
import antlr.CommonAST;
import antlr.collections.AST;

public class ANTLRParserGenerator {
	private int iindex = 0;
	
	private String curDef;
	
//	private Set<String> terminals = new HashSet<String>();
	
	private Map<String, String> realTypes = new HashMap<String, String>();
	
	private BlockTypesDescription blockTypesDescription;
	
	public void generate(AST lang, IBlockType [] blocktypes, Writer out) {
	}
	
	private boolean isTerminal(String s) {
		return blockTypesDescription.getDescription(s).isTerminal();
	}
	
	private void buildRealTypes() {
		for(BlockTypeDescription d : blockTypesDescription.getBlockTypes()) {
			BlockTypeDescription p = d;
			while(p.isSynonim()) {
				p = blockTypesDescription.getDescription(p.getSuperTypeName());
			}
			realTypes.put(d.getName(), p.getName());
		}
		
	}
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		new ANTLRParserGenerator().generate(new FileInputStream("./gentest/pascal.blocks"), new FileOutputStream("./gentest/PascalParser.g"), Utils.loadBlockTypesDef(new FileInputStream("./gentest/BlockTypes.xml")));
	}
	
	public void generate(InputStream is, OutputStream os, BlockTypesDescription descr) {
		blockTypesDescription = descr;
		buildRealTypes();
		Map<String, String> attrs = new HashMap<String, String>();
		AST ast = Utils.parseBlocksDef(is);
		PrintStream ps = new PrintStream(os);
		AST a = new CommonAST();
		a.addChild(ast);
		for(AST attr = ast; attr != null; attr = attr.getNextSibling()) {
			if(attr.getType() == BlockGrammarParserTokenTypes.ANY_TEXT) {
				attrs.put(attr.getFirstChild().getText(), attr.getText());
			}
		}
		if(attrs.containsKey("@header")) {
			ps.println("header " + attrs.get("@header") + "}");
		}
		ps.println("class MyParser extends Parser(\"ru.amse.smartlang.format.parsers.utils.SmartParser\");\n");
		if(attrs.containsKey("@options")) {
			ps.println("options " + attrs.get("@options") + "}");
		}
//		new ASTFrame("AST", a).setVisible(true);
		for(; ast != null; ast = ast.getNextSibling()) {
			if(ast.getType() == BlockGrammarParserTokenTypes.COLON) {
				parseDef(ast.getFirstChild(), ps);
			}
		}
	
		ps.close();
	}
	
	
	private void parseDef(AST def, PrintStream ps) {
		iindex = 0;
		ps.println(def.getText() + " returns[IBlock res = null]");
		curDef = def.getText();
		ps.println("{ List<IBlock> c = new ArrayList<IBlock>(); IBlock a1; }");
		ps.print("        : ");
		parseBlock(def.getNextSibling(), ps);
		ps.println("\n        ;\n");
	}

	private void putReturnCode(PrintStream ps) {
		if(blockTypesDescription.getDescription(curDef).isBogus()) {
			ps.print("res = returnBlock(BlockTypes." + realTypes.get(curDef).toUpperCase() + ", c, true); }");
		} else {
			ps.print("res = returnBlock(BlockTypes." + realTypes.get(curDef).toUpperCase() + ", c); }");
		}
	}
	
	private void parseBlock(AST firstChild, PrintStream ps) {
		boolean empty = true;
		for(AST chld = firstChild; chld != null; chld = chld.getNextSibling()) {
			switch(chld.getType()) {
			case BlockGrammarParserTokenTypes.OR:
				if(empty) {
					ps.print(" { res = IBlock.NULL; }");
				}
				ps.println();
				ps.print("        | ");
				break;
			case BlockGrammarParserTokenTypes.NOT_INCL:
				ps.print(chld.getFirstChild());
				ps.print(" ");
				break;
			case BlockGrammarParserTokenTypes.MULTI:
			case BlockGrammarParserTokenTypes.MAY:
			case BlockGrammarParserTokenTypes.PLUS:
				empty = false;
				ps.print("(");
				parseBlock(chld.getFirstChild(), ps);
				ps.print(")");
				ps.print(chld.getText());
				if(chld.getNextSibling() == null || chld.getNextSibling().getType() == BlockGrammarParserTokenTypes.OR) {
					ps.print("{");
					putReturnCode(ps);
				}
				break;
			case BlockGrammarParserTokenTypes.ID:
				if(isTerminal(chld.getText())) {
					ps.print("i" + iindex + " : " + chld.getText() + " {");
					ps.print("c.add(pblock(BlockTypes." + realTypes.get(chld.getText()).toUpperCase() + ", i" + iindex + "));");
					iindex++;
				} else {
					ps.print("a1 = ");
					ps.print(chld.getText());
					ps.print("{c.add(a1); ");
				}
				if(chld.getNextSibling() == null || chld.getNextSibling().getType() == BlockGrammarParserTokenTypes.OR) {
					putReturnCode(ps);
				} else {
					ps.print('}');
				}
				empty = false;
				break;
			
			default:
				empty = false;
				ps.print(chld.getText());
				ps.print(" ");
			}
		}
	}
}
