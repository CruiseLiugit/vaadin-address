package com.example.vaadin_address.ui;

import com.example.vaadin_address.Vaadin_addressUI;
import com.example.vaadin_address.data.Person;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

//extends FieldGroup
public class RightPersonForm extends VerticalLayout implements ClickListener{
	private Button save = new Button("保存",(ClickListener)this);
	private Button cancel = new Button("取消",(ClickListener)this);
	private Button edit = new Button("Edit",(ClickListener)this);
	private final ComboBox cities = new ComboBox("city");
	
	private Vaadin_addressUI app;
	private boolean newContactMode = false;
	private Person newPerson = null;     //封装表格数据的 实体对象
	
	@PropertyId("message")
	private TextArea message = new TextArea("备注");
	

	public RightPersonForm() {
		this.addComponent(new TextField("First Name"));
		this.addComponent(new TextField("Last Name"));
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		this.addComponent(footer);
	}

	public RightPersonForm(Vaadin_addressUI app) {
		this.app = app;
		/*
		 * Enable buffering so that commit() must be called for the form before
         * input is written to the data. (Form input is not written immediately
         * through to the underlying object.)
		 */
		//this.setBuffered(false);
		
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		footer.addComponent(edit);
		footer.setVisible(false);

		//setFooter(footer);
		
		//允许用户输入新的城市
		cities.setNewItemsAllowed(true);
		//不允许输入空值
		cities.setNullSelectionAllowed(false);
		//添加一个默认值
		cities.addItem("");
		
		//Populate cities select using the cities in the data container
		//PersonContainer ds = 
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

}
