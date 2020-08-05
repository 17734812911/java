package com.xtw.entity;

/**
 * 权限实体类
 * @author Mr.Chen
 * 2020年7月27日
 */
public class Action {

	private int actionId;
	private String actionName;	//权限名称	
	private String actionUrl;	//权限路由
	private int parentId;		//父级权限ID
	public Action() {
		super();
	}
	public Action(int actionId, String actionName, String actionUrl, int parentId) {
		super();
		this.actionId = actionId;
		this.actionName = actionName;
		this.actionUrl = actionUrl;
		this.parentId = parentId;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Action [actionId=" + actionId + ", actionName=" + actionName + ", actionUrl=" + actionUrl
				+ ", parentId=" + parentId + "]";
	}
	
}
