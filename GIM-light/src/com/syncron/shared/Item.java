package com.syncron.shared;

public class Item {

	String name;
	String code;

	public Item(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
