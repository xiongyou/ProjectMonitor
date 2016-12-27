package com.productMonitor.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="taskdata")
public class TaskData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TaskDataID")
	private int taskDataID;
	
	@Column(name="TaskInfoID")
	private int taskInfoID;	

	
	@Column(name="FailedInfo")
	private String failedInfo;
	
	@Column(name="IsRedistribute")
	private int isRedistribute;
	
	public int getTaskDataID() {
		return taskDataID;
	}

	public void setTaskDataID(int taskDataID) {
		this.taskDataID = taskDataID;
	}

	public int getTaskInfoID() {
		return taskInfoID;
	}

	public void setTaskInfoID(int taskInfoID) {
		this.taskInfoID = taskInfoID;
	}

	public String getFailedInfo() {
		return failedInfo;
	}

	public void setFailedInfo(String failedInfo) {
		this.failedInfo = failedInfo;
	}

	public int getIsRedistribute() {
		return isRedistribute;
	}

	public void setIsRedistribute(int isRedistribute) {
		this.isRedistribute = isRedistribute;
	}
	
	
}
