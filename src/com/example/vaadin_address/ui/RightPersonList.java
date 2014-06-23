package com.example.vaadin_address.ui;

import com.example.vaadin_address.Vaadin_addressUI;
import com.example.vaadin_address.data.PersonContainer;
import com.vaadin.data.Container;
import com.vaadin.ui.Table;

/**
 * 右侧存放用户信息的表格
 * 
 * @author lililiu
 * 
 */
public class RightPersonList extends Table {

	public RightPersonList() {
		// 创建一个默认的数据
		// 定义两列 Property
		// this.addContainerProperty("first", String.class, "姓名");
		// this.addContainerProperty("second", String.class, "年龄");
		// 增加两行数据 Item
		// addItem();//数据为空
		// addItem();
		// this.setSizeFull();

		
	}

	public RightPersonList(String caption) {
		super(caption);
		// TODO Auto-generated constructor stub
	}

	public RightPersonList(String caption, Container dataSource) {
		super(caption, dataSource);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过 Vaadin_addressUI 类传递 数据 对象
	 * 
	 * @param ui
	 */
	public RightPersonList(Vaadin_addressUI ui) {
		this.setSizeFull();
		this.setContainerDataSource(ui.getDataSource());
		
		this.setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
		//this.setColumnHeader(PersonContainer.COL_HEADERS_ENGLISH, null);
		this.setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);
	}

}
