package com.productMonitor.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="taskinfo")
public class TaskInfo implements Serializable{
	
	/**
	 * 
	 
	private static final long serialVersionUID = -5150784180264978966L;
*/
	@Id
	@Column(name="TaskInfoID")
	private int taskID;
	
	@Column(name="ProjectID")
	private int projectID;
	
	@Column(name="URL")
	private String URL;
	
	@Column(name="Status") //0:初始    1:正在执行     2:完成    3:达到最大服务器连接超时次数，被放弃   4：达到最大页面访问超时次数    99：新一轮任务发布，被放弃
	private int status;
	
	@Column(name="ClientID")
	private String UserID;
	
	@Column(name="Website")
	private String website;
	
	@Column(name="DataObj")
	private String dataobj;
	
	@Column(name="Keyword")
	private String keyword;
	
	@Column(name="DistributedTime")//任务下发的时间，即客户领取任务的时间
	private Date distributedTime;
	

	
	@Column(name="TaskTimeoutCount")
	private int taskTimeoutCount;
	
	@Column(name="URLTimeoutCount")
	private int uRLTimeoutCount;
	
	@Column(name="Project_Task_ID")
	private int projectTaskId;
	
	@Column(name="Add_Task_Time")//到一个周期时添加任务的时间，与project中一致
	private Date addTaskTime;
	
	@Column(name="FinishedTime")//
	private Date finishedTime;
	
	@Column(name="productInnerId")
	private int productInnerId;
	
	public Date getDistributedTime() {
		return distributedTime;
	}
	public void setDistributedTime(Date distributedTime) {
		this.distributedTime = distributedTime;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website1) {
		website = website1;
	}

	
	
	public String getDataobj() {
		return dataobj;
	}
	public void setDataobj(String dataobj) {
		this.dataobj = dataobj;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	

	public int getTaskTimeoutCount() {
		return taskTimeoutCount;
	}
	public void setTaskTimeoutCount(int taskTimeoutCount) {
		this.taskTimeoutCount = taskTimeoutCount;
	}
	public int getuRLTimeoutCount() {
		return uRLTimeoutCount;
	}
	public void setuRLTimeoutCount(int uRLTimeoutCount) {
		this.uRLTimeoutCount = uRLTimeoutCount;
	}
	public int getProjectTaskId() {
		return projectTaskId;
	}
	public void setProjectTaskId(int projectTaskId) {
		this.projectTaskId = projectTaskId;
	}
	public Date getAddTaskTime() {
		return addTaskTime;
	}
	public void setAddTaskTime(Date addTaskTime) {
		this.addTaskTime = addTaskTime;
	}
	public Date getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	public int getProductInnerId() {
		return productInnerId;
	}
	public void setProductInnerId(int productInnerId) {
		this.productInnerId = productInnerId;
	}
	
}
