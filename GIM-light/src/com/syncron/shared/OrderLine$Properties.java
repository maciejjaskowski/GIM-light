package com.syncron.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderLine$Properties implements ReflectsObject {

	private final OrderLine orderLine;
	
	
	public OrderLine$Properties(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	@Override
	public List<String> fieldNames() {
		return Arrays.asList("item", "quantity", "price");
	}

	@Override
	public Object get(String fieldName) {
		if ("item".equals(fieldName)) {
			return orderLine.item;
		} else if ("quantity".equals(fieldName)) {
			return orderLine.quantity;
		} else if ("price".equals(fieldName)) {
			return orderLine.price;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<String> actions() {
		return new ArrayList<String>();
	}

	@Override
	public void action(String actionName) {
		throw new IllegalArgumentException();
	}

}
