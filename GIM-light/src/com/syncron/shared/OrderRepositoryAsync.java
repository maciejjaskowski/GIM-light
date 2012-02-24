package com.syncron.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The client side stub for the RPC service.
 */
//@RemoteServiceRelativePath("order")
public interface OrderRepositoryAsync {
	void unconfirmed(AsyncCallback<Order> callback);
}
