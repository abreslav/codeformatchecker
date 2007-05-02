package ru.amse.smartlang.format.parsers.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarLexer;
import ru.amse.smartlang.format.parsers.tools.parsers.BlockGrammarParser;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

public final class Utils {
	public static BlockTypesDescription loadBlockTypesDef(InputStream is) throws SAXException, IOException, ParserConfigurationException {
		final BlockTypesDescription d = new BlockTypesDescription();
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SchemaFactory sfactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = sfactory.newSchema(new File("./schema/BlockTypes.xsd"));
        factory.setSchema(schema);
        SAXParser parser = factory.newSAXParser();
        parser.parse(is, new DefaultHandler() {
        	@Override
        	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        		if(qName == "blockTypes") {
        			d.setPackage(attributes.getValue("package"));
        		} else if(qName == "blockType" && attributes.getValue("name") != null){
        			d.addDef(new BlockTypeDescription(
        					attributes.getValue("name"),
        					attributes.getValue("superType"),
        					Boolean.parseBoolean(attributes.getValue("isBogus")),
        					Boolean.parseBoolean(attributes.getValue("isSynonim")),
        					Boolean.parseBoolean(attributes.getValue("isTerminal"))
        					)
        			);
        		}
        	}
        });
        return d;
	
	}
	
	public static AST parseBlocksDef(InputStream is) {
		BlockGrammarLexer lexer = new BlockGrammarLexer(is);
		BlockGrammarParser parser = new BlockGrammarParser(lexer);
		try {
			parser.all();
		} catch (RecognitionException e) {
			e.printStackTrace();
		} catch (TokenStreamException e) {
			e.printStackTrace();
		}
		return parser.getAST();
	}
	
	
}
