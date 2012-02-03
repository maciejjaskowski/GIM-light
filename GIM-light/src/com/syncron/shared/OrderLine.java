package com.syncron.shared;

import java.math.BigDecimal;

public class OrderLine {

	public OrderLine(Item item, BigDecimal price, int quantity) {
		this.item = item;
		this.price = price;
		this.quantity = quantity;

	}
	Item item;
	int quantity;
	BigDecimal price;

}
