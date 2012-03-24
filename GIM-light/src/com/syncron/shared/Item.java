package com.syncron.shared;


public class Item implements Model {

	String name;
	String code;
	
	public Item() { }

	public Item(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
