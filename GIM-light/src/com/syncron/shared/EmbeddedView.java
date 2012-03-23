package com.syncron.shared;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class EmbeddedView implements IsWidget {

	private final Button label;
	private final Object element;

	public EmbeddedView(Object element) {
		this.element = element;
		label = new Button(this.element.toString());
	}

	@Override
	public Widget asWidget() {
		return label;
	}

}
