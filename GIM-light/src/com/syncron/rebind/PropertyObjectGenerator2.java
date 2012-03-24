package com.syncron.rebind;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;

import com.syncron.shared.Order;

public class PropertyObjectGenerator2 {
	private String typeName = null;
	private String packageName = null;

	// inherited generator method
	public String generate(Class<?> type) {
		this.typeName = type.getSimpleName() + "$Properties";
		this.packageName = type.getPackage().getName();
			// Generate class source code
		generateClass();
		
		// return the fully qualifed name of the class generated
		return packageName + "." + typeName;
	}

	/**
	 * Generate source code for new class. Class extends <code>HashMap</code>.
	 * 
	 * @param logger
	 *            Logger object
	 * @param context
	 *            Generator context
	 */
	private void generateClass() {
		// get print writer that receives the source code
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		SourceWriter writer = new SourceWriter(new OutputStreamWriter(byteArrayOutputStream));
		
		generateHeader(writer);
		writer.indent();
			generateConstructor(writer);
			writer.println("");
			generateGet(writer);
			writer.println("");
			generateFieldNames(writer);
		writer.outdent();
		writer.println("}");
		
		
//		InputStreamReader dataInputStream = new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		System.out.println(writer);
	}


	private void generateHeader(SourceWriter writer) {
		writer.println("public class " + typeName + " {");
		
	}

	/**
	 * Generate source code for the default constructor. Create default
	 * constructor, call super(), and insert all key/value pairs from the
	 * resoruce bundle.
	 * 
	 * @param sourceWriter
	 *            Source writer to output source code
	 */
	private void generateConstructor(SourceWriter sourceWriter) {
		sourceWriter.println("private final " + className() + " object;");
		sourceWriter.println("public " + typeName + "(" + className() + " object) { ");
		sourceWriter.indent();

		sourceWriter.println("super();");
		sourceWriter.println("this.object = object;");

		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private String className() {
		return Order.class.getName();
	}

	private void generateFieldNames(SourceWriter sourceWriter) {
		sourceWriter.println("public List<String> fieldNames() {");
		sourceWriter.indent();

		sourceWriter.print("return Arrays.asList(");
		Field[] declaredFields = Order.class.getDeclaredFields();

		Iterable<Field> fields = Arrays.asList(declaredFields);
		Iterator<Field> iterator = fields.iterator();

		while (iterator.hasNext()) {
			Field field = iterator.next();
			sourceWriter.print("\"" + field.getName() + "\"");
			if (iterator.hasNext()) {
				sourceWriter.print(",");
			}
		}
		sourceWriter.println(");");
		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private void generateGet(SourceWriter sourceWriter) {
		sourceWriter.println("public Object get(String fieldName) {");
		sourceWriter.indent();

		Field[] declaredFields = Order.class.getDeclaredFields();

		Iterable<Field> fields = Arrays.asList(declaredFields);
		Iterator<Field> iterator = fields.iterator();
		sourceWriter.println("if (0 == 1) {");
		while (iterator.hasNext()) {
			Field field = iterator.next();
			String name = field.getName();
			sourceWriter.println("} else if (\"" + name + "\".equals(" + name + ")) {");
			sourceWriter.indent();
			sourceWriter.println("return object." + name + ";");
			sourceWriter.outdent();
		}
		sourceWriter.println("}");
		sourceWriter.outdent();
		sourceWriter.println("};");
	}
	
	public static void main(String[] args) {
		new PropertyObjectGenerator2().generate(Order.class);
	}

}

class SourceWriter {

	private String str = "";
	private String indent = "";
	private boolean newLine = true;

	public SourceWriter(OutputStreamWriter outputStreamWriter) {
	}

	public void print(String string) {
		if (newLine) {
			str += indent;
		}
		str += string;
		newLine = false;
	}

	public void indent() {
		indent  += "  ";
	}

	public void println(String string) {
		try {
			print(string);
			newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void newLine() throws IOException {
		str += "\n";
		newLine = true;
	}

	public void outdent() {
		indent = indent.substring(0, indent.length() - 2);		
	}
	
	@Override
	public String toString() {
		return str;
	}
	
}