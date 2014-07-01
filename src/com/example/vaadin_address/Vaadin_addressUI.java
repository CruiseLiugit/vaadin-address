package com.example.vaadin_address;

import com.example.vaadin_address.data.PersonContainer;
import com.example.vaadin_address.data.SearchFilter;
import com.example.vaadin_address.ui.LeftNavigationTree;
import com.example.vaadin_address.ui.RightListView;
import com.example.vaadin_address.ui.RightPersonForm;
import com.example.vaadin_address.ui.RightPersonList;
import com.example.vaadin_address.ui.mainview.SearchView;
import com.example.vaadin_address.ui.toolbar.HelpWindow;
import com.example.vaadin_address.ui.toolbar.SharingOptions;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.annotations.Title;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 按照 教程一步步完成得一个 地址簿 管理程序 内容由下面组件组成 Window VerticalLayout(window own)
 * HorizontalLayout(ToolBar) Button(Add Contact) Button(Search) Button(Share)
 * Button(Help) SplitPanel(Horizontal) Tree(Navigation tree on left side) Main
 * view area(ont the right side,根据程序状态显示不同内容)
 * 
 * @author lililiu
 * 
 */
@Title("地址簿实例")
@SuppressWarnings("serial")
// @Theme("vaadin_address") //前期设计页面，把样式去掉
public class Vaadin_addressUI extends UI implements ClickListener,
		ValueChangeListener {
	// 定义界面使用到得组件
	// --------------顶部工具栏组件-----------------------------
	private Button newContact = new Button("Add contact");
	private Button search = new Button("Search");
	private Button share = new Button("Share");
	private Button help = new Button("帮助");

	private HelpWindow helpWindow = null; // 创造子窗口
	private SharingOptions sharingWindow = null; // 分享子窗口

	// ---------------中间分割面板--------------------------------
	private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
	private LeftNavigationTree tree = new LeftNavigationTree(); // 左侧
	private RightListView listView = null; // 这里数据较多，延迟创建
	private RightPersonList personList = null; // 列表
	private RightPersonForm personForm = null; // 延迟创建

	// ---------------表格数据绑定--------------------------------
	// 该对象通过 UI 类传递给 表格类 RightPersonList
	private PersonContainer dataSource = PersonContainer.createWithTestData();

	// 表单字段
	private PropertysetItem form_item = new PropertysetItem();

	// -----------------导航控制的页面--------------------------
	private SearchView searchView = null; // 搜索页面

	@Override
	protected void init(VaadinRequest request) {
		this.buildMainLayout();
	}

	/**
	 * 创建主界面
	 */
	private void buildMainLayout() {
		// 默认创建垂直布局
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull(); // 满屏

		layout.addComponent(createToolbar()); // 工具栏
		layout.addComponent(horizontalSplit); // 下面的左右分割面板

		// 让水平布局面板占满空间
		layout.setExpandRatio(horizontalSplit, 1);
		// 设置左侧宽度
		horizontalSplit.setSplitPosition(200, Unit.PIXELS);
		// 增加左侧树状菜单
		horizontalSplit.setFirstComponent(tree); // 左侧
		// 增加右侧列表视图
		this.setMainComponent(getListView()); // 右侧

		// 绑定左侧 tree 的监听器
		tree.addItemClickListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("菜单点击......");
				if (event.getSource() == tree) {
					Object itemid = event.getItemId();
					if (itemid != null) {
						if (LeftNavigationTree.SHOW_ALL.equals(itemid)) {
							// clear previous filters
							getDataSource().removeAllContainerFilters();
							System.out.println("点击  显示所有 菜单");
							showListView();
						} else if (LeftNavigationTree.SEARCH.equals(itemid)) {
							System.out.println("点击  查询  菜单");
							showSearchView();
						} else if (itemid instanceof SearchFilter) {
							search((SearchFilter) itemid);
						}
					}
				}
			}
		});

		// 375

		// 把默认得 垂直布局添加到当前 UI 中
		this.setContent(layout);
	}

	/**
	 * 创建简单的工具栏
	 * 
	 * @return
	 */
	public HorizontalLayout createToolbar() {
		HorizontalLayout lo = new HorizontalLayout();

		lo.addComponent(newContact);
		lo.addComponent(search);
		lo.addComponent(share);
		lo.addComponent(help);

		// 绑定按钮监听器
		help.addClickListener(this);
		share.addClickListener(this);
		search.addClickListener(this);
		newContact.addClickListener(this);

		// notif.setIcon(new ThemeResource("img/store.png"));
		// 美化按钮
		// search.setIcon(new ThemeResource("icons/32/folder-add.png"));
		// share.setIcon(new ThemeResource("icons/32/users.png"));
		// help.setIcon(new ThemeResource("icons/32/help.png"));
		// newContact.setIcon(new ThemeResource("icons/32/document-add.png"));
		//
		// lo.setMargin(true); //Margin
		// lo.setSpacing(true); //Padding
		//
		// lo.setStyleName("toolbar");
		// lo.setWidth(100,Unit.PERCENTAGE);
		//
		//
		//
		// Embedded em = new Embedded("",new ThemeResource("images/logo.png"));
		// lo.addComponent(em);
		// lo.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
		// lo.setExpandRatio(em, 1);

		return lo;
	}

	/**
	 * 根据主界面状态，切换 分割面板，右侧内容组件的方法 右侧
	 * 
	 * @param c
	 */
	private void setMainComponent(Component c) {
		horizontalSplit.setSecondComponent(c);
	}

	/**
	 * Lazy initialization 右侧列表视图
	 * 
	 * @return
	 */
	private RightListView getListView() {
		if (listView == null) {
			// personList = new RightPersonList(); //不绑定数据源
			personList = new RightPersonList(this); // 绑定数据源

			form_item.addItemProperty("firstName", new ObjectProperty<String>(
					"Qi"));
			form_item.addItemProperty("lastName", new ObjectProperty<String>(
					"Zhang"));
			form_item.addItemProperty("phoneNumber",
					new ObjectProperty<String>("15922304938"));
			form_item.addItemProperty("email", new ObjectProperty<String>(
					"Zaphod@323.com"));
			form_item.addItemProperty("streetAddress",
					new ObjectProperty<String>("花园路200号"));
			form_item.addItemProperty("postalCode", new ObjectProperty<String>(
					"215330"));
			form_item.addItemProperty("city", new ObjectProperty<String>(
					"请选择城市"));
			// personForm = new RightPersonForm(); //简单表单
			personForm = new RightPersonForm(this, form_item); // 复杂表单

			// 到 listView 中绑定表单数据
			listView = new RightListView(this, personList, personForm);
		}
		return listView;
	}

	public HelpWindow getHelpWindow() {
		if (helpWindow == null) {
			helpWindow = new HelpWindow();
		}
		return helpWindow;
	}

	public SharingOptions getSharingWindow() {
		if (sharingWindow == null) {
			sharingWindow = new SharingOptions();
		}
		return sharingWindow;
	}

	private void showHelpWindow() {
		// this.setContent(this.getHelpWindow());
		UI.getCurrent().addWindow(getHelpWindow());
	}

	private void showShareWindow() {
		// this.setContent(this.getSharingWindow());
		UI.getCurrent().addWindow(getSharingWindow());
	}

	private void showListView() {
		// personList = new RightPersonList(); //不绑定数据源
		personList = new RightPersonList(this); // 绑定数据源

		form_item
				.addItemProperty("firstName", new ObjectProperty<String>("Qi"));
		form_item.addItemProperty("lastName", new ObjectProperty<String>(
				"Zhang"));
		form_item.addItemProperty("phoneNumber", new ObjectProperty<String>(
				"15922304938"));
		form_item.addItemProperty("email", new ObjectProperty<String>(
				"Zaphod@323.com"));
		form_item.addItemProperty("streetAddress", new ObjectProperty<String>(
				"花园路200号"));
		form_item.addItemProperty("postalCode", new ObjectProperty<String>(
				"215330"));
		form_item.addItemProperty("city", new ObjectProperty<String>("请选择城市"));
		// personForm = new RightPersonForm(); //简单表单
		personForm = new RightPersonForm(this, form_item); // 复杂表单

		
		
		listView.setFirstComponent(personList);
		listView.setSecondComponent(personForm);
	}

	private void addNewContanct() {
		showListView();
		personForm.addContact();
	}

	// -------------绑定数据方法---------------------------
	public PersonContainer getDataSource() {
		return dataSource;
	}

	// -------------导航到搜索页面的方法--------------------
	private void showSearchView() {
		// setMainComponent(getSearchView());
		listView.setSecondComponent(getSearchView());
		// this.setContent(getSearchView());
	}

	// lazy init searchView
	public SearchView getSearchView() {
		if (searchView == null) {
			searchView = new SearchView(this);
		}
		return searchView;
	}

	public void saveSearch(SearchFilter searchFilter) {

		tree.addItem(searchFilter);
		tree.setParent(searchFilter, LeftNavigationTree.SEARCH);
		// make the saved search as a leaf(cannot have children)
		tree.setChildrenAllowed(searchFilter, false);
		// make sure "Search" is expanded
		tree.expandItem(LeftNavigationTree.SEARCH);
		// select the saved search
		tree.setValue(searchFilter);
	}

	public void search(SearchFilter searchFilter) {
		// clear previous filters
		this.getDataSource().removeAllContainerFilters();
		// filter contacts with given filter
		this.getDataSource().addContainerFilter(searchFilter.getPropertyId(),
				searchFilter.getTerm(), true, false);
		this.showListView();
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == personList) {
			Item item = personList.getItem(personList.getValue());
			// 这里没有写完
			// if (item != personForm.geti) {}

		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		//
		final Button source = event.getButton();
		System.out.println("按钮点击事件.........");
		if (source == search) {
			this.showSearchView();
		} else if (source == help) {
			this.showHelpWindow();
		} else if (source == share) {
			this.showShareWindow();
		} else if (source == newContact) {
			this.addNewContanct();
		}

	}

}