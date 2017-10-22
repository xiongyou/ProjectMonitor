package com.productMonitor.project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Monitor {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub  
		//加参数，可设置具体运行某一个进程，进程时间也进行配置
		
		//1. 项目是否达到执行周期
		ProjectMonitor projMon=new ProjectMonitor(6000);		
		
		//2. 对失败任务进行重新发布
		TaskMonitor taskMon=new TaskMonitor(60);
		
		Thread tProj=new Thread(projMon);
		Thread tTask=new Thread(taskMon);
		
		tProj.start();
		tTask.start();
	}
	
}
