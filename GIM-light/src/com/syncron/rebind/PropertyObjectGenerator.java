package com.syncron.rebind;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Iterator;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.syncron.shared.Order;

public class PropertyObjectGenerator extends Generator {
	/** Simple name of class to be generated */
	private String className = null;
	/** Package name of class to be generated */
	private String packageName = null;
	/** Fully qualified class name passed into GWT.create() */
	private String typeName = null;

	// inherited generator method
	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName)
			throws UnableToCompleteException {
		this.typeName = typeName;
		TypeOracle typeOracle = context.getTypeOracle();
		try {
			// get classType and save instance variables
			JClassType classType = typeOracle.getType(typeName);
			packageName = classType.getPackage().getName();
			className = classType.getSimpleSourceName() + "$Properties";
			// Generate class source code
			generateClass(logger, context);
		} catch (Exception e) {
			// record to logger that Map generation threw an exception
			logger.log(TreeLogger.ERROR, "PropertyMap ERROR!!!", e);
		}
		// return the fully qualifed name of the class generated
		return packageName + "." + className;
	}

	/**
	 * Generate source code for new class. Class extends <code>HashMap</code>.
	 * 
	 * @param logger
	 *            Logger object
	 * @param context
	 *            Generator context
	 */
	private void generateClass(TreeLogger logger, GeneratorContext
			context) {
		// get print writer that receives the source code
		PrintWriter printWriter = null;
		printWriter = context.tryCreate(logger, packageName, className);
		// print writer if null, source code has ALREADY been generated, return
		if (printWriter == null)
			return;
		// init composer, set class properties, create source writer
		ClassSourceFileComposerFactory composer = null;
		composer = new ClassSourceFileComposerFactory(packageName,
				className);

		SourceWriter sourceWriter = null;
		sourceWriter = composer.createSourceWriter(context, printWriter);
		// generator constructor source code
		generateConstructor(sourceWriter);
		generateGet(sourceWriter);
		generateFieldNames(sourceWriter);
		// close generated class
		sourceWriter.outdent();
		sourceWriter.println("}");
		// commit generated class
		context.commit(logger, printWriter);
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
		sourceWriter.println("public " + className + "(" + className() + " object) { ");
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

		Iterable<Field> fields = (Iterable<Field>) (Object) declaredFields;
		Iterator<Field> iterator = fields.iterator();

		while (iterator.hasNext()) {
			Field field = iterator.next();
			sourceWriter.print("\"" + field.getName() + "\"");
			if (iterator.hasNext()) {
				sourceWriter.print(",");
			}
		}
		sourceWriter.println(");");
	}

	private void generateGet(SourceWriter sourceWriter) {
		sourceWriter.println("public String get(String fieldName) {");
		sourceWriter.indent();

		Field[] declaredFields = Order.class.getDeclaredFields();

		Iterable<Field> fields = (Iterable<Field>) (Object) declaredFields;
		Iterator<Field> iterator = fields.iterator();
		sourceWriter.println("if (0 == 1) {");
		while (iterator.hasNext()) {
			Field field = iterator.next();
			String name = field.getName();
			sourceWriter.println("} else if (\"" + name + "\".equals(" + name + ")) {");
			sourceWriter.print("return object." + name + ".toString();");
		}
		sourceWriter.println("}");
		sourceWriter.println(");");
	}

}