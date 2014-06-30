package com.example.vaadin_address.ui;

import com.example.vaadin_address.Vaadin_addressUI;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 * 左侧导航树状菜单
 * @author lililiu
 *
 */
public class LeftNavigationTree extends Tree {
	//定义菜单名称
	public static final Object SHOW_ALL = "显示所有";
	public static final Object SEARCH = "查询";
	
	public LeftNavigationTree(){
		addItem(SHOW_ALL);
		addItem(SEARCH);
	}
	
	/**
	 * 主界面可以直接调用的组件，通过把主界面作为参数传递数据
	 * @param app
	 */
	public LeftNavigationTree(Vaadin_addressUI app){
		addItem(SHOW_ALL);
		addItem(SEARCH);
		
		setChildrenAllowed(SHOW_ALL, false);
		/*
		 *We want items to be selectable but do not want the user to be able to de-select an item. 
		 */
		setSelectable(true);
		setNullSelectionAllowed(false);
		
		//Make application handle item click events
		this.addItemClickListener((ItemClickListener)app);
	}
	
			
}
