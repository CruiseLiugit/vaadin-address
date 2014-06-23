package com.example.vaadin_address;

import com.example.vaadin_address.data.PersonContainer;
import com.example.vaadin_address.ui.LeftNavigationTree;
import com.example.vaadin_address.ui.RightListView;
import com.example.vaadin_address.ui.RightPersonForm;
import com.example.vaadin_address.ui.RightPersonList;
import com.example.vaadin_address.ui.toolbar.HelpWindow;
import com.example.vaadin_address.ui.toolbar.SharingOptions;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
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
@SuppressWarnings("serial")
@Theme("vaadin_address")
public class Vaadin_addressUI extends UI {
	// 定义界面使用到得组件
	// --------------顶部工具栏组件-----------------------------
	private Button newContact = new Button("新增");
	private Button search = new Button("查询");
	private Button share = new Button("分享");
	private Button help = new Button("帮助");
	
	private HelpWindow helpWindow = null;        //创造子窗口
	private SharingOptions sharingWindow = null; //分享子窗口
	
	// ---------------中间分割面板--------------------------------
	private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
	private LeftNavigationTree tree = new LeftNavigationTree(); // 左侧
	private RightListView listView = null; // 这里数据较多，延迟创建
	private RightPersonList personList = null; // 列表
	private RightPersonForm personForm = null; // 延迟创建
	
	// ---------------表格数据绑定--------------------------------
	//该对象通过 UI 类传递给 表格类 RightPersonList
	private PersonContainer dataSource = PersonContainer.createWithTestData();
	
	
	
	@Override
	protected void init(VaadinRequest request) {
		this.buildMainLayout();

		// final VerticalLayout layout = new VerticalLayout();
		// layout.setMargin(true);
		// setContent(layout);
		//
		// Button button = new Button("Click Me");
		// button.addClickListener(new Button.ClickListener() {
		// public void buttonClick(ClickEvent event) {
		// layout.addComponent(new Label("Thank you for clicking"));
		// }
		// });
		// layout.addComponent(button);
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

		// 工具栏按钮事件
		help.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(getHelpWindow());
			}
			public HelpWindow getHelpWindow() {
				if (helpWindow == null) {
					helpWindow = new HelpWindow();
				}
				return helpWindow;
			}
		});
		
		share.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(this.getSharingWindow());
			}
			
			public SharingOptions getSharingWindow() {
				if (sharingWindow == null) {
					sharingWindow = new SharingOptions();
				}
				return sharingWindow;
			}
		});

		lo.addComponent(newContact);
		lo.addComponent(search);
		lo.addComponent(share);
		lo.addComponent(help);
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
			personList = new RightPersonList(this);
			personForm = new RightPersonForm();
			// 到 listView 中绑定表单数据
			listView = new RightListView(personList, personForm);

		}
		return listView;
	}
	

	//-------------绑定数据方法---------------------------
	public PersonContainer getDataSource() {
		return dataSource;
	}

	
	
	
	

	
	
	

	
	
	
	
	
	
}