package com.productMonitor.project;

import java.util.Date;
import java.util.List;

import com.productMonitor.data.Project;
import com.productMonitor.data.ProjectTask;
import com.productMonitor.data.TaskInfo;
import com.productMonitor.manage.ProjectManage;
import com.productMonitor.manage.ProjectTaskManage;
import com.productMonitor.manage.TaskManage;

public class ProjectMonitor implements Runnable {
	private int waitSeconds;

	ProjectMonitor(int seconds) {
		this.waitSeconds = seconds;
	}

	public void run() {
		while (true)
		// 1. 项目监测
		{
			ProjectManage projectManage = new ProjectManage();
			ProjectTaskManage projTaskMan = new ProjectTaskManage();
			TaskManage taskMan = new TaskManage();
			// 1.1 从项目表中获取项目
			List<Project> projList = projectManage.findAllProjects();
			// 1.2 判断项目是否到新的执行周期(找出项目状态为0的,判断是否到开始执行时间)
			for (Project tempProj : projList) {
				// 1.3 判断项目是否到期
				if (tempProj.getEndedTime().getTime() <= (new Date()).getTime() && tempProj.getStatus() != 2) { // getTime()返回毫秒
					// 1.3.1 将项目状态改为2
					tempProj.setStatus(2);
					projectManage.updateProject(tempProj);
					continue;
				}
				// 1.2.1 如果项目状态为0,判断其是否到执行时间，执行后则将其改为1
				if (tempProj.getStatus() == 0 && tempProj.getStartTime().getTime() <= (new Date()).getTime()) {
					tempProj.setStatus(1);
				}
				long seconds=0;
				if(tempProj.getLastTime()!=null){
				long secondLast=tempProj.getLastTime().getTime();
				long periodSec=((long)tempProj.getExecutePeriod()) * 1000;
				seconds=secondLast
						+  periodSec;
				int i=0;
				}
				long nowSeconds=(new Date()).getTime();
				
				if (tempProj.getStatus() == 1 && (tempProj.getLastTime() == null ||seconds <= nowSeconds)) {

					// 1.2.2 发布新的任务:从任务基本信息列表 到任务执行列表
					//List<ProjectTask> projTaskList = projTaskMan.findProjectTaskByProjectId(tempProj.getProjectID());
					// 在发布之前，需要将以前的未完成的任务全部处理为过期,设置其状态为99					
					
					taskMan.batSetStatus(tempProj.getProjectID(), 99);
					// 有可能任务插入到一半断开了，怎么处理？
					//直接执行一条批量插入的SQL语句，交由数据库服务器进行处理。而不需要将数据从服务器拖下来，然后又写回去，这样很麻烦，绕了一圈
					taskMan.insertTaskInfoFromProjTask(tempProj.getProjectID());

					// 1.2.3 修改项目最后一次执行的时间
					Date now = new Date();
					tempProj.setLastTime(now);
					// 1.2.4 记录项目被执行的次数
					int releaseCount = tempProj.getReleaseCount() + 1;
					tempProj.setReleaseCount(releaseCount);
					projectManage.updateProject(tempProj);
				}

			}
			try {
				Thread.sleep(this.waitSeconds*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
