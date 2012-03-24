package com.syncron.server;

import static com.syncron.shared.Order.Status.NEW;

import java.util.LinkedList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.syncron.shared.Order;


public class OrderRepository extends RemoteServiceServlet implements com.syncron.client.OrderRepository {

	private static final long serialVersionUID = 1249702302231024506L;
	
	LinkedList<Order> all = new LinkedList<Order>(Order.orders());

	@Override
	public Order unconfirmed() {
		while (!all.isEmpty()) {
			Order top = all.get(0);
			
			if (top.is(NEW)) {
			} else {
				all.remove(0);
			}
			return top;
		}
		return null;
	}
}
