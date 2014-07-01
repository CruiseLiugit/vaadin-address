package com.example.vaadin_address.ui;

import com.example.vaadin_address.Vaadin_addressUI;
import com.example.vaadin_address.data.Person;
import com.example.vaadin_address.data.PersonContainer;
import com.vaadin.data.Container;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

/**
 * 右侧存放用户信息的表格
 * 
 * @author lililiu
 * 
 */
public class RightPersonList extends Table {

	/**
	 * 基本模拟时使用
	 */
	public RightPersonList() {
		// 创建一个默认的数据
		// 定义两列 Property
		 this.addContainerProperty("姓名", String.class, "李平");
		 this.addContainerProperty("年龄", String.class, "28");
		 //增加两行数据 Item
		 addItem();//数据为空
		 addItem();
		 this.setSizeFull();
	}
	
	/**
	 * 通过 Vaadin_addressUI 类传递 数据 对象
	 * 
	 * @param ui
	 */
	public RightPersonList(Vaadin_addressUI app) {
		this.setSizeFull();
		this.setContainerDataSource(app.getDataSource());
		
		this.setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
		this.setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);
		
		this.setColumnCollapsingAllowed(true);
		this.setColumnReorderingAllowed(true);
		
		/*
		 *  Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		this.setSelectable(true);
		this.setImmediate(true);
		this.addValueChangeListener((ValueChangeListener)app);
		
		/* We don't want to allow users to de-select a row */
		this.setNullSelectionAllowed(false);
		
		//customize email column to have mailto: links using column generator
		//调整邮箱使用体验，点击邮箱   打开 邮件App
		this.addGeneratedColumn("email", new ColumnGenerator(){
			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				Person p = (Person)itemId;
				Link lk = new Link();
				lk.setResource(new ExternalResource("收件人:"+p.getEmail()));
				lk.setCaption(p.getEmail());
				return lk;
			}
			
		});
		
	}

	public RightPersonList(String caption) {
		super(caption);
		// TODO Auto-generated constructor stub
	}

	public RightPersonList(String caption, Container dataSource) {
		super(caption, dataSource);
		// TODO Auto-generated constructor stub
	}

	

}
