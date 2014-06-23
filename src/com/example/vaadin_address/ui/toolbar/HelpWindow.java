package com.example.vaadin_address.ui.toolbar;

import com.vaadin.server.AbstractErrorMessage.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * Vaadin 7 中 window 只是当作 子窗口使用
 * 
 * @author lililiu
 * 
 */
public class HelpWindow extends Window {
	private static final String HELP_HTML_SNIPPET = "This is "
			+ "an application built during <strong><a href=\""
			+ "http://dev.vaadin.com/\">Vaadin</a></strong> "
			+ "tutorial. Hopefully it doesn't need any real help.";

	public HelpWindow() {
		setCaption("Address Book help");
		this.setContent(new Label(HELP_HTML_SNIPPET));
	}

	public HelpWindow(String caption) {
		super(caption);
		// TODO Auto-generated constructor stub
	}

	public HelpWindow(String caption, Component content) {
		super(caption, content);
		// TODO Auto-generated constructor stub
	}

}
