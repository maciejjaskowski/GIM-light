package com.syncron.shared;

import java.util.EmptyStackException;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;


public class OrderRepository implements OrderRepositoryAsync {

	List<Order> all = Order.orders();
	@Override
	public void unconfirmed(AsyncCallback<Order> callback) {
		if (all.isEmpty()) {
			callback.onFailure(new EmptyStackException());
		}
		callback.onSuccess(all.get(0));
	}
}
