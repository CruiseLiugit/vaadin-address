package com.example.vaadin_address.ui.mainview;

import com.example.vaadin_address.Vaadin_addressUI;
import com.example.vaadin_address.data.PersonContainer;
import com.example.vaadin_address.data.SearchFilter;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 点击左侧 树状菜单中的  “查询” 出现的 面板
 * 该组件 ，将会在 RightListView.java 中使用
 *   
 * @author lililiu
 *
 */
public class SearchView extends Panel {
	//组件
	private TextField tf;
	private NativeSelect fieldToSearch;
	private CheckBox saveSearch;
	private TextField searchName;
	private Vaadin_addressUI app;
	

	public SearchView() {
		// TODO Auto-generated constructor stub
	}
	
	public SearchView(Vaadin_addressUI ui) {
		this.app = ui;
		addStyleName("view");
		
		// 可以获取主界面的数据，并且能够与主界面管理的组件进行交互
		this.setCaption("查询数据");
		this.setSizeFull();
		
		//使用 FormLayout 作为本 Panel 的主布局
		FormLayout formLayout = new FormLayout();
		setContent(formLayout);
		
		//创建 UI Components
		tf = new TextField("查询");
		fieldToSearch = new NativeSelect("查询字段");
		saveSearch = new CheckBox("保存查询");
		searchName = new TextField("查询名称");
		Button search = new Button("查询");
		search.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				performSearch();
			}
		});
		
		//init fieldToSearch
		for (int i = 0; i < PersonContainer.NATURAL_COL_ORDER.length; i++) {
			fieldToSearch.addItem(PersonContainer.NATURAL_COL_ORDER[i]);
			fieldToSearch.setItemCaption(PersonContainer.NATURAL_COL_ORDER[i], PersonContainer.COL_HEADERS_ENGLISH[i]);
		}
		
		fieldToSearch.setValue("lastName");   //placeholder 占位符
		fieldToSearch.setNullSelectionAllowed(false); //不允许为空
		
		//init save checkbox
		saveSearch.setValue(true);
		saveSearch.setImmediate(true);
		//CheckBox 事件监听处理
		saveSearch.addFocusListener(new FocusListener(){
			@Override
			public void focus(FocusEvent event) {
				searchName.setVisible(((CheckBox)event.getComponent()).getValue());
			}
		});
		
		
		//把所有的组件，添加到 表单中
		formLayout.addComponent(tf);
		formLayout.addComponent(fieldToSearch);
		formLayout.addComponent(saveSearch);
		formLayout.addComponent(searchName);
		formLayout.addComponent(search);
	}

	private void performSearch(){
		String searchTerm = (String)tf.getValue();
		if (searchTerm == null || searchTerm.equals("")) {
			Notification.show("搜索添加不能为空", Notification.Type.WARNING_MESSAGE);
			return;
		}
		
		SearchFilter searchFilter = new SearchFilter((String)fieldToSearch.getValue(),searchTerm,(String)searchName.getValue());
		
		if (saveSearch.getValue()) {
			if (searchName.getValue() == null || searchName.getValue().equals("")) {
				Notification.show("请输入搜索名称", Notification.Type.WARNING_MESSAGE);
				return;
			}
			app.saveSearch(searchFilter);
		}
		
		app.search(searchFilter);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public SearchView(Component content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

	public SearchView(String caption) {
		super(caption);
		// TODO Auto-generated constructor stub
	}

	public SearchView(String caption, Component content) {
		super(caption, content);
		// TODO Auto-generated constructor stub
	}
	*/
	
	
	
	

}
