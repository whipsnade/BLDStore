package com.bld.object;

/**
 * Waiter entity. @author MyEclipse Persistence Tools
 */

public class Waiter implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String pass;
	private String phone;
	private short accept;
	private short stock;
	private String storeId;

	// Constructors

	/** default constructor */
	public Waiter() {
	}

	/** minimal constructor */
	public Waiter(String id) {
		this.id = id;
	}

	/** full constructor */
	public Waiter(String id, String name, String pass, String phone,
			short accept, short stock, String storeId) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.phone = phone;
		this.accept = accept;
		this.stock = stock;
		this.storeId = storeId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public short getAccept() {
		return this.accept;
	}

	public void setAccept(short accept) {
		this.accept = accept;
	}

	public short getStock() {
		return this.stock;
	}

	public void setStock(short stock) {
		this.stock = stock;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}