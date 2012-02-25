package com.syncron.shared;

import static com.syncron.shared.Order.Status.NEW;
import static com.syncron.shared.Order.Status.CONFIRMED;
import static com.syncron.shared.Order.Status.REJECTED;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
public class Order {

	public static enum Status {
		NEW,
		CONFIRMED,
		REJECTED
	}

	String number;
	Date date;
	Address buyerAddress;
	Status status;
	List<OrderLine> lines;
	
	private Order() { }

	@Deprecated
	public Order(String orderNumber, Date date, Address buyerAddress,
			Status status, OrderLine ...lines) {
		super();
		this.number = orderNumber;
		this.date = date;
		this.buyerAddress = buyerAddress;
		this.status = status;
		this.lines = Arrays.asList(lines);
	}
	
	public void confirm() {
		this.status = CONFIRMED;
	}

	public static Order specialOrder() {		
		Order order = new Order("1", new Date(),
				new Address("Świętokrzyska 36/36-37"), Status.NEW, 
				new OrderLine(new Item(), new BigDecimal("15.20"), 1));
		return order;
	}
	
	public static List<Order> orders() {		
		Order[] orders = new Order[] {
		Order.create("1").at(new Date()).to(new Address("Świętokrzyska 36/36-37"))
			.status(NEW)
			.consistingOf(new OrderLine(new Item(), new BigDecimal("3.26"), 1),
						new OrderLine(new Item(), new BigDecimal("1.27"), 5),
						new OrderLine(new Item(), new BigDecimal("7.28"), 6)).write()
		,Order.create("2").at(new Date()).to(new Address("Świętokrzyska 36/36-37"))
			.status(NEW)
			.consistingOf(new OrderLine(new Item(), new BigDecimal("9.26"), 1),
						new OrderLine(new Item(), new BigDecimal("11.27"), 5),
						new OrderLine(new Item(), new BigDecimal("13.28"), 6)).write()
		,Order.create("3").at(new Date()).to(new Address("Świętokrzyska 36/36-37"))
			.status(NEW)
			.consistingOf(new OrderLine(new Item(), new BigDecimal("15.26"), 1),
						new OrderLine(new Item(), new BigDecimal("17.27"), 5),
						new OrderLine(new Item(), new BigDecimal("19.28"), 6)).write()
		};
		return Arrays.asList(orders);
	}

	private static OrderBuilder create(String number) {
		return new OrderBuilder(number);
	}

	public static class OrderBuilder {

		private final Order builded = new Order();

		public OrderBuilder(String number) {
			builded.number = number;
		}

		public Order write() {
			return builded;
		}

		public OrderBuilder consistingOf(OrderLine orderLine, OrderLine ...orderLines) {
			ArrayList<OrderLine> orderLinesAsList = new ArrayList<OrderLine>();
			orderLinesAsList.add(orderLine);
			orderLinesAsList.addAll(Arrays.asList(orderLines));
			builded.lines = orderLinesAsList;
			return this;
		}

		public OrderBuilder status(Status status) {
			builded.status = status;
			return this;
		}

		public OrderBuilder to(Address address) {
			builded.buyerAddress = address;
			return this;
		}

		public OrderBuilder at(Date date) {
			builded.date = date;
			return this;
		}
		
	}
}