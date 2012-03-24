package com.syncron.client;

import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.syncron.shared.Order;
import com.syncron.shared.Order$Properties;
import com.syncron.shared.OrderLine;
import com.syncron.shared.OrderLine$Properties;
import com.syncron.shared.ReflectsObject;

public class View implements IsWidget {

	Object object;
	DialogBox dialogBox;

	public View(Object object) {
		this.object = object;
		dialogBox = createDialogBox();
	}

	private DialogBox createDialogBox() {
		// Create a dialog box and set the caption text
		final DialogBox dialogBox = new ClosableDialogBox();
		dialogBox.ensureDebugId("cwDialogBox");
		dialogBox.setText(object.toString());

		dialogBox.setGlassEnabled(false);
		dialogBox.setModal(false);
		dialogBox.setAnimationEnabled(true);

		// Create a table to layout the content
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(4);
		dialogBox.setWidget(dialogContents);

		// Add some text to the top of the dialog

		createContent(dialogContents);
		
		createActions(dialogContents);


		// Add an image to the dialog
		// Image image = new Image(Showcase.images.jimmy());
		// dialogContents.add(image);
		// dialogContents.setCellHorizontalAlignment(
		// image, HasHorizontalAlignment.ALIGN_CENTER);

		// Add a close button at the bottom of the dialog
		Button closeButton = new Button(
				"close", new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
					}
				});
		dialogContents.add(closeButton);
		if (LocaleInfo.getCurrentLocale().isRTL()) {
			dialogContents.setCellHorizontalAlignment(
					closeButton, HasHorizontalAlignment.ALIGN_LEFT);

		} else {
			dialogContents.setCellHorizontalAlignment(
					closeButton, HasHorizontalAlignment.ALIGN_RIGHT);
		}

		// Return the dialog box
		return dialogBox;
	}

	private void createContent(VerticalPanel dialogContents) {
		try {
			if (object.getClass() == Order.class) {
				ReflectsObject properties = new Order$Properties((Order) object);
				addPropertiesTo(dialogContents, properties);				
			} else if (object.getClass() == OrderLine.class) {
				ReflectsObject properties = new OrderLine$Properties((OrderLine) object);
				addPropertiesTo(dialogContents, properties);
			} else {
				Label label = new Label(object.getClass().getName() + ": " + object.toString());
				dialogContents.add(label);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void addPropertiesTo(VerticalPanel dialogContents,
			ReflectsObject properties) {
		for (String fieldName : properties.fieldNames()) {
			if (properties.get(fieldName) instanceof Collection){
				dialogContents.add(new EmbeddedListView(fieldName, (Collection<?>) properties.get(fieldName)));
			} else {
				Label label = new Label(fieldName + ": " + properties.get(fieldName));
				dialogContents.add(label); 
			}
		}
	}
	
	private void createActions(VerticalPanel dialogContents) {
		if (object.getClass() == Order.class) {
			final ReflectsObject properties = new Order$Properties((Order) object);
			
			for (final String actionName : properties.actions()) {
				Button action = new Button(actionName, new ClickHandler() {
					public void onClick(ClickEvent event) {
						properties.action(actionName);
					}
				});
				dialogContents.add(action);
			}
			
		} else {
		}
		
	}

	@Override
	public Widget asWidget() {
		return dialogBox;
	}

	public void show() {
		dialogBox.center();
		dialogBox.show();
	}
	
	class ClosableDialogBox extends DialogBox {
		@Override
	    protected void onPreviewNativeEvent(NativePreviewEvent event) {
	        super.onPreviewNativeEvent(event);
	        switch (event.getTypeInt()) {
	            case Event.ONKEYDOWN:
	                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
	                    hide();
	                }
	                break;
	        }
	    }
	}

}
