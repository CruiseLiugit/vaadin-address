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
	private static final String HELP_HTML_SNIPPET = "这是程序帮助页面"
			+ "为<strong><a href=\""
			+ "http://www.liufuya.com/\">留夫鸭</a></strong> "
			+ "公司创建的后台管理程序.";

	public HelpWindow() {
		setModal(true);  //遮罩层，不允许操作底部视图中的组件
		setWidth("50%");
		setCaption("地址簿帮助页面");
		this.setContent(new Label(HELP_HTML_SNIPPET));
		 center();  //居中显示
	        
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
