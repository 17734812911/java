package com.xtw.entity;

/**
 * 角色关联权限实体类
 * @author Mr.Chen
 * 2020年7月27日
 */
public class RoleAction {

	private int roleActionId;	//角色关联权限ID
	private int roleId;		//角色ID
	private int actionId;	//权限ID
	public RoleAction() {
		super();
	}
	public RoleAction(int roleActionId, int roleId, int actionId) {
		super();
		this.roleActionId = roleActionId;
		this.roleId = roleId;
		this.actionId = actionId;
	}
	public int getRoleActionId() {
		return roleActionId;
	}
	public void setRoleActionId(int roleActionId) {
		this.roleActionId = roleActionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	@Override
	public String toString() {
		return "RoleAction [roleActionId=" + roleActionId + ", roleId=" + roleId + ", actionId=" + actionId + "]";
	}
	
}
