package com.syncron.shared;

import java.util.List;

import org.joda.time.LocalDate;

public class Order {

	public static enum Status {
		NEW,
		ACCEPTED,
		REJECTED
	}

	String orderNumber;
	LocalDate date;
	Address buyerAddress;
	Status status;
	List<OrderLine> lines;

}
