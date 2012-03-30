package com.syncron.client;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class EmbeddedView implements IsWidget {

	private final Label label;
	private final Object element;

	public EmbeddedView(final Object element) {
		this.element = element;
		label = new Label(
				this.element.toString());
		label.addDoubleClickHandler(new DoubleClickHandler() {

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				new View(element).show();

			}
		});
	}

	@Override
	public Widget asWidget() {
		return label;
	}

}
