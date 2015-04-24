package com.icecub3.lawinorder;

public class Users {

	int _id;
	String _email;
	String _password;
	String _name;
	String _company;
	String _phone;

	public Users() {

	}

	public Users(int id, String email, String password, String name,
			String company, String phone) {
		this._id = id;
		this._email = email;
		this._password = password;
		this._name = name;
		this._company = company;
		this._phone = phone;
	}

	public Users(String email, String password, String name, String company,
			String phone) {
		this._email = email;
		this._password = password;
		this._name = name;
		this._company = company;
		this._phone = phone;
	}

	public int getId() {
		return this._id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getEmail() {
		return this._email;
	}

	public void setEmail(String email) {
		this._email = email;
	}

	public String getPassword() {
		return this._password;
	}

	public void setPassword(String password) {
		this._password = password;
	}

	public String getName() {
		return this._name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getCompany() {
		return this._company;
	}

	public void setCompany(String company) {
		this._company = company;
	}

	public String getPhone() {
		return this._phone;
	}

	public void setPhone(String phone) {
		this._phone = phone;
	}

}
