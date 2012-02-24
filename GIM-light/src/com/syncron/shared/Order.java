package com.syncron.shared;

import java.math.BigDecimal;
import java.util.Arrays;
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
	
	

	public Order(String orderNumber, Date date, Address buyerAddress,
			Status status, OrderLine ...lines) {
		super();
		this.orderNumber = orderNumber;
		this.date = date;
		this.buyerAddress = buyerAddress;
		this.status = status;
		this.lines = Arrays.asList(lines);
	}



	public static Order specialOrder() {		
		Order order = new Order("1", new Date(),
				new Address("Świętokrzyska 36/36-37"), Status.NEW, 
				new OrderLine(new Item(), new BigDecimal("15.20"), 1));
		return order;
	}
	
	public static List<Order> orders() {		
		Order[] orders = new Order[] {
			new Order("1", new Date(), new Address("Świętokrzyska 36/36-37"), Status.NEW, 
				new OrderLine(new Item(), new BigDecimal("1.20"), 1),
				new OrderLine(new Item(), new BigDecimal("3.20"), 5),
				new OrderLine(new Item(), new BigDecimal("5.20"), 6)),
			new Order("2", new Date(),
				new Address("Maszynowa 12"), Status.NEW, 
				new OrderLine(new Item(), new BigDecimal("1.21"), 1),
				new OrderLine(new Item(), new BigDecimal("3.22"), 5),
				new OrderLine(new Item(), new BigDecimal("5.23"), 6)),
			new Order("3", new Date(),
				new Address("Iwicka 24"), Status.NEW, 
					new OrderLine(new Item(), new BigDecimal("1.26"), 1),
					new OrderLine(new Item(), new BigDecimal("3.27"), 5),
					new OrderLine(new Item(), new BigDecimal("5.28"), 6))
		};
		return Arrays.asList(orders);
	}

}
