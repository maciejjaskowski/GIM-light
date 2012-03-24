package com.syncron.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.syncron.shared.Order;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("orderRepository")
public interface OrderRepository extends RemoteService {
	Order unconfirmed();
}
