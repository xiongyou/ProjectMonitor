package com.productMonitor.manage;

import java.util.List;

import com.productMonitor.data.TaskData;
import com.productMonitor.data.TaskInfo;
import com.productMonitor.hibernate.BaseDAO;

/**
 * TaskDataManage类主要用于对访问超时进行监控
 * 
 * @author DengPan
 *
 */
public class TaskDataManage {

	// 创建一个BaseDAO数据处理对象
	BaseDAO dao = new BaseDAO();

	/**
	 * 根据taskDataID查找(主键)
	 * 
	 * @param productID
	 * @return
	 */
	public TaskData findTaskInfoById(int taskDataID) {
		return dao.find(TaskData.class, taskDataID);
	}
	
	/**
	 * 查找未重新发布的任务
	 * 
	 * @param status
	 * @return
	 */
	public List<TaskData> findTaskDataByIsRedistribute(int isRedistribute) {
		return (List<TaskData>) dao.list("from TaskData where isRedistribute=" + isRedistribute);
	}
	
	/**更新TaskData信息
	 * @param product
	 */
	public void updateTaskData(TaskData taskData){
		dao.update(taskData);
	}		
	
	
	

}
