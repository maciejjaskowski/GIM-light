package com.syncron.rebind;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;

class SourceWriter {

	private String str = "";
	private String indent = "";
	private boolean newLine = true;

	public SourceWriter(OutputStreamWriter outputStreamWriter) {
	}

	public void openBlock(String string) {
		println(string + " {");
		indent();
	}

	public void doReturn(String string) {
		println("return " + string + ";");
	}

	public void closeBlock() {
		outdent();
		println("}");		
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
	
	public void println() {
		println("");
	}

	public void println(String string) {
		try {
			print(string);
			newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	void printList(Iterable<?> iterable) {
		printList(iterable.iterator());
	}
	
	void printList(Iterator<?> iterator) {
		while(iterator.hasNext()) {
			Object method = iterator.next();
			print("\"" + method.toString() + "\"");
			if (iterator.hasNext()) {
				print(", ");
			}
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