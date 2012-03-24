package com.syncron.shared;


public class Address implements Model {
	
	public Address() { }

	public Address(String address) {
		this.address = address;
	}

	String address;

	@Override
	public String toString() {
		return address;
	}

	@Override
	public ReflectsObject getProperties() {
		return new Address$Properties(this);
	}
}
