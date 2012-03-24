package com.syncron.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Address implements IsSerializable {
	
	public Address() { }

	public Address(String address) {
		this.address = address;
	}

	String address;

	@Override
	public String toString() {
		return address;
	}
}
