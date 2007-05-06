package ru.amse.smartlang.format.parsers.tools;

import java.io.PrintWriter;
import java.io.Writer;

public class BlockTypesEnumGenerator {

	public void generateEnumClass(BlockTypesDescription descr, Writer writer) {
		PrintWriter ps = new PrintWriter(writer);
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
