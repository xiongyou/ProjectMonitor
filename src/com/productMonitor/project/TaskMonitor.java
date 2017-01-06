package com.productMonitor.project;

import java.util.Date;
import java.util.List;

import com.productMonitor.data.Project;
import com.productMonitor.data.ProjectTask;
import com.productMonitor.data.TaskData;
import com.productMonitor.data.TaskInfo;
import com.productMonitor.manage.ProjectManage;
import com.productMonitor.manage.ProjectTaskManage;
import com.productMonitor.manage.TaskDataManage;
import com.productMonitor.manage.TaskManage;

public class TaskMonitor implements Runnable {
	private int waitSeconds;

	TaskMonitor(int seconds) {
		this.waitSeconds = seconds;
	}

	public void run() {
		while (true) {
			ProjectManage projectManage = new ProjectManage();
			TaskManage taskMan = new TaskManage();
			int i=0;//任务发布计数
			// 2.任务监测(主要是任务超时处理)
			// 2.1 取出处于执行状态的任务
			System.out.println("服务器连接超时任务重新发布：");
			List<TaskInfo> taskInfoList = taskMan.findTaskInfoByStatus(1);
			for (TaskInfo taskInfo : taskInfoList) {
				// 2.2判断任务下发时间+项目中任务执行的最大时间<=当前时间,且未到达最大超时次数
				int projectId = taskInfo.getProjectID();
				Project proj = projectManage.findProjectById(projectId);
				if (taskInfo.getDistributedTime().getTime() + proj.getMaxExecutingTime() * 1000 < (new Date())
						.getTime()) {
					// 2.2 如果超时,修改其初始状态为0,同时超时次数+1,另外ClientnID与DistributeTime也设为空

					taskInfo.setStatus(0);
					
					// 是否需要将用户ID与发布时间设置为null ？
					int timeoutCount = taskInfo.getTaskTimeoutCount();
					taskInfo.setTaskTimeoutCount(timeoutCount + 1);
					taskInfo.setUserID(null);
					taskInfo.setDistributedTime(null);

				} else {
					// 如果没有超时则继续
					continue;
				}
				// 2.3 如果超时达到最大超时次数，则修改其状态为3
				if (taskInfo.getTaskTimeoutCount() == 5) {
					taskInfo.setStatus(3);
				}
				// 2.4 更新任务
				taskMan.updateTaskInfo(taskInfo);
				i++;
				System.out.println("已经重新发布"+i+"条任务");
			}

			// 3.页面访问超时处理
			System.out.println("页面访问超时任务重新发布：");
			TaskDataManage taskDataMan = new TaskDataManage();
			
			// 3.1 查询出未重新发布的TaskData中的taskInfoID
			List<TaskData> taskDataList = taskDataMan.findTaskDataByIsRedistribute(0);// 查询未重新发布的任务
			for (TaskData taskData : taskDataList) {
				// 3.2 去查找TaskInfo
				TaskInfo taskInfo = taskMan.findTaskInfoById(taskData.getTaskInfoID());
				// 3.2.1
				// 将TaskData中的isRedistribute设为1，同时设置taskInfo的状态为初始0，URLTimeoutCount+1；
				taskInfo.setStatus(0);
				// 是否需要将用户ID与发布时间设置为null ？
				taskInfo.setUserID(null);
				taskInfo.setDistributedTime(null);
				//访问超时还需要重新设置结束时间为null
				taskInfo.setFinishedTime(null);
				int timeoutCount = taskInfo.getuRLTimeoutCount();
				taskInfo.setuRLTimeoutCount(timeoutCount + 1);
				taskData.setIsRedistribute(1);
				// 3.3 判断URLTimeoutCount是否达到最大
				// 3.3.1 如果达到最大，则将其状态设置为4，TaskData中的isRedistribute设为99
				if (taskInfo.getuRLTimeoutCount() == 5) {
					taskInfo.setStatus(4);
					taskData.setIsRedistribute(99);
				}
				// 3.4 更新任务
				taskMan.updateTaskInfo(taskInfo);
				taskDataMan.updateTaskData(taskData);
				i++;
				System.out.println("已经重新发布"+i+"条任务");
			}

			try {
				Thread.sleep(this.waitSeconds * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
