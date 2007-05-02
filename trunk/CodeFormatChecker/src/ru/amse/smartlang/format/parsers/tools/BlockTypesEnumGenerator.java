package ru.amse.smartlang.format.parsers.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class BlockTypesEnumGenerator {

	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		BlockTypesDescription descr = Utils.loadBlockTypesDef(new FileInputStream("./gentest/BlockTypes.xml"));
		PrintStream ps = new PrintStream(new FileOutputStream("./gentest/BlockTypes.java"));
		if(descr.getPackage() != null) {
			ps.println("package " + descr.getPackage() + ";");
		}
		ps.println("import ru.amse.smartlang.format.*;");
		ps.println("public enum BlockTypes implements IBlockType {");
		for(BlockTypeDescription d : descr.getBlockTypes()) {
			ps.println("    " + d.getName().toUpperCase() + " {");
			if(d.getSuperTypeName() != null) {
				ps.println("        public IBlockType getSuperType() { return " + d.getSuperTypeName().toUpperCase() + "; }");
			}
			ps.println("    },");
		}
		ps.print("    NONE;\n");
		ps.println("    public IBlockType getSuperType() { return IBlockType.ROOT; }");
		ps.println("    public boolean isSynonim() { return false; }\n}");
		ps.close();
	}

}
