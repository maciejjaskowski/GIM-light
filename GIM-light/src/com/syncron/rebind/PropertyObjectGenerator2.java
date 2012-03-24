package com.syncron.rebind;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.syncron.shared.Action;

public class PropertyObjectGenerator2 {
	private final String typeName;
	private final String packageName;
	private final Class<?> type;
	private final String result;

	public PropertyObjectGenerator2(String typeName, String suffix) throws ClassNotFoundException {
		this(Class.forName(typeName), suffix);
	}
	
	public PropertyObjectGenerator2(Class<?> type, String suffix) {
		this.type = type;
		this.typeName = type.getSimpleName() + suffix;
		this.packageName = type.getPackage().getName();
			// Generate class source code
		this.result = generateClass();
		
	}
	
	public String getResult() {
		return result;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Generate source code for new class. Class extends <code>HashMap</code>.
	 * 
	 * @param logger
	 *            Logger object
	 * @param context
	 *            Generator context
	 */
	private String generateClass() {
		// get print writer that receives the source code
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		SourceWriter writer = new SourceWriter(new OutputStreamWriter(byteArrayOutputStream));
		
		generateHeader(writer);
			generateConstructor(writer);
			writer.println("");
			generateFieldNames(writer);
			writer.println("");
			generateGet(writer);
			writer.println("");
			generateActions(writer);
			writer.println("");
			generateAction(writer);
		writer.closeBlock();
		
		return writer.toString();
	}


	private void generateAction(SourceWriter writer) {
		writer.openBlock("@Override public void action(String actionName)");
		
		for (String actionName : getActionNames()) {
			writer.openBlock("if (\"" + actionName + "\".equals(actionName))");
				writer.println("object." + actionName + "();");
				writer.doReturn("");
			writer.closeBlock();
		}
			
		writer.println("throw new IllegalArgumentException();");
		writer.closeBlock();
		
	}

	private void generateActions(SourceWriter writer) {
		writer.openBlock("@Override public List<String> actions()");
		
		writer.print("return Arrays.asList(");
		Iterator<String> iterator = getActionNames().iterator();
		writer.printList(iterator);
		writer.println(");");
		writer.closeBlock();
	}

	private Iterable<String> getActionNames() {
		Method[] methods = type.getDeclaredMethods();
		List<String> actions = new ArrayList<String>();
		
		for (Method method : Arrays.asList(methods)) {
			if (method.getAnnotation(Action.class) != null) {
				actions.add(method.getName());
			}
		}
		return actions;
	}

	private void generateHeader(SourceWriter writer) {
		writer.println("package " + packageName + ";");
		writer.println("import java.util.Arrays;");
		writer.println("import java.util.List;");
		writer.println("");
		writer.openBlock("public class " + typeName + " implements ReflectsObject");
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
		sourceWriter.println("private final " + type.getSimpleName() + " object;");
		sourceWriter.println("public " + typeName + "(" + type.getSimpleName() + " object) { ");
		sourceWriter.indent();

			sourceWriter.println("super();");
			sourceWriter.println("this.object = object;");

		sourceWriter.closeBlock();
	}

	private void generateFieldNames(SourceWriter sourceWriter) {
		sourceWriter.openBlock("@Override public List<String> fieldNames()");

		sourceWriter.print("return Arrays.asList(");

		sourceWriter.printList(getProperties(type.getDeclaredFields()));

		sourceWriter.println(");");
		sourceWriter.closeBlock();
	}

	private List<String> getProperties(Field[] declaredFields) {
		Iterable<Field> fields = Arrays.asList(declaredFields);
		List<String> result = new ArrayList<String>();
		for (Field field : fields) {
			result.add(field.getName());
		}
		return result;
	}

	private void generateGet(SourceWriter sourceWriter) {
		sourceWriter.openBlock("@Override public Object get(String fieldName)");

		Field[] declaredFields = type.getDeclaredFields();

		Iterator<String> iterator = getProperties(declaredFields).iterator();
		sourceWriter.println("if (0 == 1) {");
		while (iterator.hasNext()) {
			String name = iterator.next();
			sourceWriter.println("} else if (\"" + name + "\".equals(fieldName)) {");
			sourceWriter.indent();
				sourceWriter.doReturn("object." + name);
			sourceWriter.outdent();
		}
		sourceWriter.println("}");
		sourceWriter.println("throw new IllegalArgumentException();");
		sourceWriter.closeBlock();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File dirToLookForClasses = new File("src/com/syncron/shared/");
		final String suffix = "$Properties";
		
		System.out.println("I'm here: " + dirToLookForClasses.getAbsolutePath());
		System.out.println("I'm going to filter these: " + Arrays.toString(dirToLookForClasses.list()));
		
		ArrayList<Class<?>> names = findClasses(dirToLookForClasses);
		
		for (Class<?> type : names) {
			PropertyObjectGenerator2 generator = new PropertyObjectGenerator2(type, suffix);
			String newClassPath = dirToLookForClasses.getAbsolutePath() + "/" + type.getSimpleName() + suffix + ".java";
			System.out.println(newClassPath);
			FileWriter fileWriter = new FileWriter(new File(newClassPath));
			fileWriter.write(generator.getResult());
			fileWriter.close();
		}
	}

	private static ArrayList<Class<?>> findClasses(File dirToLookForClasses)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Class<?>> names = new ArrayList<Class<?>>(); 
		for (String fileName : dirToLookForClasses.list()) {
			File file = new File(dirToLookForClasses + "/" + fileName);
			
			if ( ! file.isFile()) { continue; }
			
			if (implementsModel(file)) {
				String className = "com.syncron.shared." + fileName.substring(0, fileName.length() - 5);
				names.add(Class.forName(className));
			}
		}
		return names;
	}

	private static boolean implementsModel(
			File file) throws IOException,
			ClassNotFoundException {
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = fileReader.readLine()) != null) {
			if (line.contains("implements Model")) {
				return true;
			}
		}
		return false;
	}

}