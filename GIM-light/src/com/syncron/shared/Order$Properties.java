package com.syncron.shared;

import java.util.Arrays;
import java.util.List;

public class Order$Properties {

	private final Order object;

	public Order$Properties(Order object) {
		this.object = object;
	}

	public List<String> fieldNames() {
		return Arrays.asList("orderNumber", "date", "buyerAddress", "status", "lines");
	}

	public String get(String fieldName) {
		if ("orderNumber".equals(fieldName)) {
			return object.number.toString();
		} else if ("date".equals(fieldName)) {
			return object.date.toString();
		} else if ("buyerAddress".equals(fieldName)) {
			return object.buyerAddress.toString();
		} else if ("lines".equals(fieldName)) {
			return object.lines.toString();
		} else if ("status".equals(fieldName)) {
			return object.status.toString();
		}
		throw new IllegalArgumentException();
	}
	
	public List<String> actions() {
		return Arrays.asList("confirm");
	}

	public void action(String actionName) {
		if ("confirm".equals(actionName)) {
			object.confirm();
			return;
		}
		throw new IllegalArgumentException();
	}

}
