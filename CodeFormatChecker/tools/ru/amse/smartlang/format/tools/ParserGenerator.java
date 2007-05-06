package ru.amse.smartlang.format.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import antlr.collections.AST;

import ru.amse.smartlang.format.parsers.tools.ANTLRParserGenerator;
import ru.amse.smartlang.format.parsers.tools.BlockTypesDescription;
import ru.amse.smartlang.format.parsers.tools.BlockTypesEnumGenerator;
import ru.amse.smartlang.format.parsers.tools.BlockTypesListGenerator;
import ru.amse.smartlang.format.parsers.tools.Utils;

public class ParserGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Parser generator. Firstly, run 'java -jar pgen <grammar>' so  parser and grammar.xml will be generated. Edit this xml file to tune parser and then re-run it.");
			return;
		}
		if(args.length == 1) {
			Reader reader;
			try {
				reader = new FileReader(args[0]);
				AST ast = Utils.parseBlocksDef(reader);
				BlockTypesDescription btd = null;
				try {
					btd = Utils.loadBlockTypesDef(new FileInputStream("BlockTypes.xml"));
				} catch (SAXException e) {
					System.out.println("Warning : bad BlockTypes.xml file. Will be overwritten");
				}
				BlockTypesListGenerator.generateBlockTypesList(ast, btd, new FileWriter("BlockTypes.xml"));
				try {
					btd = Utils.loadBlockTypesDef(new FileInputStream("BlockTypes.xml"));
				} catch (SAXException e) {
					// Never should be there
				}				reader.close();
				reader = new FileReader(args[0]);
				new BlockTypesEnumGenerator().generateEnumClass(btd, new FileWriter("BlockTypes.java"));
				new ANTLRParserGenerator().generate(reader, new FileWriter(args[0] + ".java"), btd);
			} catch (FileNotFoundException e) {
				System.out.println("Error : " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Error : " + e.getMessage());
			} catch (ParserConfigurationException e) {
				System.out.println("Error : " + e.getMessage());
			}
		}
	}

}
