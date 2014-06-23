package com.example.vaadin_address.ui;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RightPersonForm extends VerticalLayout {
	private Button save = new Button("保存");
	private Button cancel = new Button("取消");
	
	private TextField name = new TextField("姓名");
	private TextField phone = new TextField("电话");
	
	@PropertyId("message")
	private TextArea message = new TextArea("备注");
	

	public RightPersonForm() {
		//
		this.setSpacing(true);
		
		//添加组件到当前布局中
		this.addComponent(name);
		this.addComponent(phone);
		this.addComponent(message);
		
		//按钮
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		
		this.addComponent(footer);
	}

	public RightPersonForm(Component... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

}
