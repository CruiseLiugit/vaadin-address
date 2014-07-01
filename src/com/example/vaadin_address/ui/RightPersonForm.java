package com.example.vaadin_address.ui;

import java.util.Iterator;

import com.example.vaadin_address.Vaadin_addressUI;
import com.example.vaadin_address.data.Person;
import com.example.vaadin_address.data.PersonContainer;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

//FieldGroup
public class RightPersonForm extends CustomComponent implements ClickListener{
	private Button save = new Button("Save",(ClickListener)this);
	private Button cancel = new Button("Cancel",(ClickListener)this);
	private Button edit = new Button("Edit",(ClickListener)this);
	private HorizontalLayout footer;  //底部
	
	private Vaadin_addressUI app;
	
	// Member that will bind to the "name" property
	@PropertyId("firstName")
    TextField firstName = new TextField("姓");
	@PropertyId("lastName")
    TextField lastName = new TextField("名");
	@PropertyId("phoneNumber")
    TextField phoneNumber = new TextField("电话");
	@PropertyId("email")
    TextField email = new TextField("邮箱");
	@PropertyId("streetAddress")
    TextField streetAddress = new TextField("地址");
	@PropertyId("postalCode")
    TextField postalCode = new TextField("邮编");
	//@PropertyId("city")
	ComboBox city = new ComboBox("城市");
	
	
	FormLayout layout = new FormLayout();
	 
	// Now use a binder to bind the members
    FieldGroup binder = null;
    

	//第一步看到的简单 Demo 
	public RightPersonForm() {
		layout.setWidth(100,Unit.PERCENTAGE);
		layout.setSpacing(true);
		
		layout.addComponent(new TextField("First Name"));
		layout.addComponent(new TextField("Last Name"));
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		layout.addComponent(footer);
		 this.setCompositionRoot(layout);
	}

	//第二步复杂的 Demo
	public RightPersonForm(Vaadin_addressUI app,Item item) {
		this.app = app;
		/*
		 * Enable buffering so that commit() must be called for the form before
         * input is written to the data. (Form input is not written immediately
         * through to the underlying object.)
		 */
		//this.setBuffered(false);
		layout.setWidth(100,Unit.PERCENTAGE);
		layout.setSpacing(true);
		
		

		//允许用户输入新的城市
		city.setNewItemsAllowed(true);
		//不允许输入空值
		city.setNullSelectionAllowed(false);
		//添加一个默认值
		city.addItem("");
		
		//Populate cities select using the cities in the data container
		PersonContainer ds = app.getDataSource();
		for (Iterator<Person> it = ds.getItemIds().iterator(); it.hasNext();) {
			String cityName = (it.next()).getCity();
			city.addItem(cityName);
		}
	
		layout.addComponent(firstName);
		layout.addComponent(lastName);
		layout.addComponent(phoneNumber);
		layout.addComponent(email);
		layout.addComponent(streetAddress);
		layout.addComponent(postalCode);
		layout.addComponent(city);
		
		footer = new HorizontalLayout();
		footer.setSpacing(true);
		footer.addComponent(save);
		footer.addComponent(cancel);
		footer.addComponent(edit);
		//footer.setVisible(false); //隐藏按钮

		layout.addComponent(footer);
		
		
		//Field factory for overriding how the component for city selection is created
		//可以使用 FileGroup 非组件类，绑定 Field 和 Data
		binder = new FieldGroup(item);
        binder.bindMemberFields(this);
        this.setCompositionRoot(layout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button source = event.getButton();
		if (source == save) {
			//If the given input is not valid there is no point in continuing
			//if(如果没有通过验证{return;}
			//通过验证
			try {
				if (!binder.isValid()) {
					return;
				}
				binder.commit();
				binder.setReadOnly(true);
				footer.setVisible(false);  //隐藏掉底部按钮布局
			} catch (CommitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(source == cancel){
			binder.discard();
			binder.setReadOnly(true);
			cancel.setVisible(false);   //隐藏掉 cancel 按钮
		}else if(source == edit){
			binder.setReadOnly(false);
		}
	}

}
