package com.xtw.entity;

/**
 * 用户关联角色实体类
 * @author Mr.Chen
 * 2020年7月27日
 */
public class UserRole {

	private int userRoleId;	//用户关联角色ID
	private int roleId;	//角色ID
	private int usetId;	//用户ID
	public UserRole() {
		super();
	}
	public UserRole(int userRoleId, int roleId, int usetId) {
		super();
		this.userRoleId = userRoleId;
		this.roleId = roleId;
		this.usetId = usetId;
	}
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUsetId() {
		return usetId;
	}
	public void setUsetId(int usetId) {
		this.usetId = usetId;
	}
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", roleId=" + roleId + ", usetId=" + usetId + "]";
	}
	
}
