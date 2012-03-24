package com.syncron.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Model extends IsSerializable {

	ReflectsObject getProperties();
}
