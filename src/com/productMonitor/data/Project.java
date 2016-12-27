package com.productMonitor.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "_crawler_project")
public class Project {

	@Id
	@Column(name="_project_id")
	private int projectID;
	
	@Column(name="_project_name")
	private String projectName;

	@Column(name="_project_description")
	private String projectDescription;

	
	@Column(name="_start_time")
	private Date startTime;
	
	@Column(name="_end_time")
	private Date endedTime;
	
	@Column(name="_maxexecuting_time")
	private int maxExecutingTime;
	
	@Column(name="_execute_period")
	private int ExecutePeriod;
	
	@Column(name="_status")	//0:初始（未开始）    1：正常    2：到期     
	private int status;
	
	@Column(name="_last_time")
	private Date lastTime;
	
	@Column(name="_release_count")
	private int releaseCount;
	
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndedTime() {
		return endedTime;
	}

	public void setEndedTime(Date endedTime) {
		this.endedTime = endedTime;
	}

	public int getMaxExecutingTime() {
		return maxExecutingTime;
	}

	public void setMaxExecutingTime(int maxExecutingTime) {
		this.maxExecutingTime = maxExecutingTime;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getExecutePeriod() {
		return ExecutePeriod;
	}

	public void setExecutePeriod(int executePeriod) {
		ExecutePeriod = executePeriod;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public int getReleaseCount() {
		return releaseCount;
	}

	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}
	
	
	
}
