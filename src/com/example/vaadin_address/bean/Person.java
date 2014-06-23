package com.example.vaadin_address.bean;

/**
 * 表单中需要使用的字段
 * @author lililiu
 *
 */
public class Person {
	String name;
	String phone;
	String message;
	

	public Person() {
		// TODO Auto-generated constructor stub
	}

	

	public Person(String name, String phone, String message) {
		super();
		this.name = name;
		this.phone = phone;
		this.message = message;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
