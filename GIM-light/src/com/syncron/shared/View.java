package com.syncron.shared;

import java.lang.reflect.Field;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class View implements IsWidget {

	Object object;
	DialogBox dialogBox;

	public View(Object object) {
		this.object = object;
		dialogBox = createDialogBox();
	}

	private DialogBox createDialogBox() {
		// Create a dialog box and set the caption text
		final DialogBox dialogBox = new DialogBox();
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
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				Label label = new Label(field.getName() + ": " + field.get(object));
				dialogContents.add(label);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
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

}
