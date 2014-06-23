package com.example.vaadin_address.ui;

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
		this.addItem(SHOW_ALL);
		addItem(SEARCH);
	}
	
			
}
