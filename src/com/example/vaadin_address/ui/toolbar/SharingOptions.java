package com.example.vaadin_address.ui.toolbar;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class SharingOptions extends Window {

	public SharingOptions() {
		/*
		 * Make the window modal, which will disable all other components while
		 * it is visible
		 */
		setModal(true);
		/* Make the sub window 50% the size of the browser window */
		setWidth("50%");
		/*
		 * Center the window both horizontally and vertically in the browser
		 * window
		 */
		center();

		setCaption("分享页面");
		setContent(new Label("你可以把你的数据通过以下方式分享 "
				+ " (non-functional, example of modal dialog)"));
		setContent(new CheckBox("Gmail"));
		setContent(new CheckBox(".Mac"));

		Button close = new Button("OK");
		close.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// 关闭窗口
				SharingOptions.this.close();
			}
		});

		setContent(close);
	}

	public SharingOptions(String caption) {
		super(caption);
		// TODO Auto-generated constructor stub
	}

	public SharingOptions(String caption, Component content) {
		super(caption, content);
		// TODO Auto-generated constructor stub
	}

}
