package com.syncron.shared;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	public static enum Status {
		NEW,
		ACCEPTED,
		REJECTED
	}

	String orderNumber;
	Date date;
	Address buyerAddress;
	Status status;
	List<OrderLine> lines;

	public static Order specialOrder() {
		Order order = new Order();
		order.orderNumber = "1";
		order.date = new Date();
		order.buyerAddress = new Address("Świętokrzyska 36/36-37");
		order.status = Status.NEW;
		order.lines = new ArrayList<OrderLine>();
		Item item = new Item();
		order.lines.add(new OrderLine(item, new BigDecimal("15.20"), 1));
		return order;
	}

}
