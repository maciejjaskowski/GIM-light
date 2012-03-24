package com.syncron.client;

import java.util.Collection;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class EmbeddedListView implements IsWidget {
	
	FlowPanel panel = new FlowPanel();
	private final Collection<?> elements;
	private final String fieldName;
	
	public EmbeddedListView(String fieldName, Collection<?> collection) {
		this.fieldName = fieldName;
		this.elements = collection;
		panel.setWidth("100");
		createContents();
	}

	private void createContents() {
		panel.add(new Label(fieldName+": "));
		for (Object element : elements) {
			EmbeddedView child = new EmbeddedView(element);
			child.asWidget().setStyleName("embeddedListElement", true);
			panel.add(child);
		}
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

}
