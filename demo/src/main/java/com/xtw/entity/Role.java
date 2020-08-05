package com.xtw.entity;
/**
 * 角色实体类
 * @author Mr.Chen
 * 2020年7月27日
 */
public class Role {

	private int roleId;
	private String roleName;	//角色名称
	private String roleDesc;	//角色描述
	public Role() {
		super();
	}
	public Role(int roleId, String roleName, String roleDesc) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + "]";
	}
	
}
