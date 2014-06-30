package com.example.vaadin_address.ui;

import com.example.vaadin_address.bean.Person;
import com.example.vaadin_address.ui.mainview.SearchView;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
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
	
	public SearchView getSearchView() {
		if (searchView == null) {
			//searchView = new SearchView(this);
		}
		return searchView;
	}

	public RightListView(){}

	public RightListView(RightPersonList personlist , RightPersonForm personform) {
		//绑定表单数据
		//FieldGroup fgroup = new FieldGroup(new BeanItem<Person>(new Person("刘强","18944372839","客户")));
		//fgroup.bindMemberFields(personform);
		
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
