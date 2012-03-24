package com.syncron.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.syncron.shared.Order;


/**
 * The client side stub for the RPC service.
 */
public interface OrderRepositoryAsync {
	void unconfirmed(AsyncCallback<Order> callback);
}
