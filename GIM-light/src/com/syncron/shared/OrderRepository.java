package com.syncron.shared;

import java.util.EmptyStackException;
import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import static com.syncron.shared.Order.Status.*;


public class OrderRepository implements OrderRepositoryAsync {

	LinkedList<Order> all = new LinkedList<Order>(Order.orders());
	@Override
	public void unconfirmed(AsyncCallback<Order> callback) {
		
		while (!all.isEmpty()) {
			Order top = all.get(0);
			all.remove(0);
			if (top.is(NEW)) {
				callback.onSuccess(top);
				return;
			}
		}
		callback.onFailure(new EmptyStackException());	 
	}
}
