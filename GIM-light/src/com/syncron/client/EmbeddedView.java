package com.syncron.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class EmbeddedView implements IsWidget {

	private final Button label;
	private final Object element;

	public EmbeddedView(final Object element) {
		this.element = element;
		label = new Button(this.element.toString(), new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new View(element).show();
			}
		});
	}

	@Override
	public Widget asWidget() {
		return label;
	}

}
