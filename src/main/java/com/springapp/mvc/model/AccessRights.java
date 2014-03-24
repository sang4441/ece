package com.springapp.mvc.model;

public class AccessRights {

	private int permission_id;
	private int role_id;


	public AccessRights() {

	}

	public AccessRights(int permission_id, int role_id){

		this.permission_id = permission_id;
		this.role_id = role_id;

	}


	/**
	 * Get the value of permission_id
	 * 
	 * @return the value of permission_id
	 */
	public int getPermissionID() {
		return permission_id;
	}

	public void setPermissionID(int permission_id) {

		this.permission_id = permission_id;

	}

	/**
	 * Get the value of RoleID
	 * 
	 * @return the value of RoleID
	 */
	public int getRoleID() {
		return role_id;
	}

	public void setRoleID(int role_id) {

		this.role_id = role_id;
		
	}




}