package com.productMonitor.project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Monitor {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//ProjectMonitor projMon=new ProjectMonitor(60);		
		TaskMonitor taskMon=new TaskMonitor(60);
		
		//Thread tProj=new Thread(projMon);
		Thread tTask=new Thread(taskMon);
		
		//tProj.start();
		tTask.start();
	}
	
}
