package com.springapp.mvc.model;

public class Person {
	private int id;
	private String NameLast;
	private String NameFirst;
	private String Phone;
	private String Username;
	private String Password;
	private String Street;
	private String City;
	private String Province;
	private String PostalCode;
	private int RoleID;

	public Person() {

	}

	public Person(String nameLast, String nameFirst, String phone,
			String username, String password, String street, String city,
			String province, String postalCode, int roleID) {
		this.NameLast = nameLast;
		this.NameFirst = nameFirst;
		this.Phone = phone;
		this.Username = username;
		this.Password = password;
		this.Street = street;
		this.City = city;
		this.Province = province;
		this.PostalCode = postalCode;
		this.RoleID = roleID;
	}

	public Person(int id, String nameLast, String nameFirst, String phone,
			String username, String password, String street, String city,
			String province, String postalCode, int roleID) {
		this.id = id;
		this.NameLast = nameLast;
		this.NameFirst = nameFirst;
		this.Phone = phone;
		this.Username = username;
		this.Password = password;
		this.Street = street;
		this.City = city;
		this.Province = province;
		this.PostalCode = postalCode;
		this.RoleID = roleID;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nameLast
	 */
	public String getNameLast() {
		return NameLast;
	}

	/**
	 * @param nameLast
	 *            the nameLast to set
	 */
	public void setNameLast(String nameLast) {
		NameLast = nameLast;
	}

	/**
	 * @return the nameFirst
	 */
	public String getNameFirst() {
		return NameFirst;
	}

	/**
	 * @param nameFirst
	 *            the nameFirst to set
	 */
	public void setNameFirst(String nameFirst) {
		NameFirst = nameFirst;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return Phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
        Password = password;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return Street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		Street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		City = city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return Province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		Province = province;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return PostalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return RoleID;
	}

	/**
	 * @param roleID
	 *            the roleID to set
	 */
	public void setRoleID(int roleID) {
		RoleID = roleID;
	}
}