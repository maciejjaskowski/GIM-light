package com.syncron.shared;

import java.math.BigDecimal;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OrderLine implements IsSerializable {

	Item item;
	int quantity;
	BigDecimal price;
	
	public OrderLine() { }

	public OrderLine(Item item, BigDecimal price, int quantity) {
		this.item = item;
		this.price = price;
		this.quantity = quantity;

	}

	@Override
	public String toString() {
		return item.toString() + " x " + quantity;
	}
}
