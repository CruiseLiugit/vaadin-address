package com.example.vaadin_address.ui;

import com.example.vaadin_address.Vaadin_addressUI;
import com.example.vaadin_address.bean.Person;
import com.example.vaadin_address.ui.mainview.SearchView;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * 右侧 上下分割的面板
 * 
 * @author lililiu
 *
 */
public class RightListView extends VerticalSplitPanel {
	
	//这里会根据 左侧 树状菜单中的选项，动态切换  组件
	private SearchView searchView = null;
	private Vaadin_addressUI app =null;
	
	public SearchView getSearchView() {
		if (searchView == null) {
			searchView = new SearchView(app);
		}
		return searchView;
	}

	public RightListView(){}

	public RightListView(Vaadin_addressUI app,RightPersonList personlist , RightPersonForm personform) {
		this.app = app;
		//把右边界面组装好
		this.setFirstComponent(personlist);
		this.setSecondComponent(personform);
		this.setSplitPosition(40);
	}

	public RightListView(Component firstComponent, Component secondComponent) {
		super(firstComponent, secondComponent);
		// TODO Auto-generated constructor stub
	}

}
