package com.syncron.shared;

public class Address {

	public Address(String address) {
		this.address = address;

	}

	String address;

	@Override
	public String toString() {
		return address;
	}
}
