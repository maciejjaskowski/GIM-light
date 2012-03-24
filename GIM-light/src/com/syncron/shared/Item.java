package com.syncron.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Item implements IsSerializable {

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
