package com.productMonitor.manage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.productMonitor.data.TaskInfo;
import com.productMonitor.hibernate.BaseDAO;

public class TaskManage {

	
	//创建一个BaseDAO数据处理对象
		BaseDAO dao=new BaseDAO();
		
		/**插入产品
		 * @param product
		 * @return
		 * @throws Exception
		 */
		public boolean insertTaskInfo(TaskInfo taskInfo) throws Exception{		
			return dao.create(taskInfo);
		}
		
		public int batSetStatus(int projectId,int status){
			return dao.excuteBySql("update taskinfo "
					+" set status=" + status
					+" where ProjectID="+projectId);
		}
		
		/**
		 * 批量插入任务
		 * 从任务基本列表中插入到任务执行列表（包括项目与任务的优先级）
		 * @param projectId
		 * @return
		 */
		public int insertTaskInfoFromProjTask(int projectId){
			return dao.excuteBySql("insert into taskinfo "
					+"(URL,ProjectID,Website,Keyword,DataObj,Add_Task_Time,Project_Task_ID,productInnerId,project_priority,task_priority)"
					+" select _url,_project_id,_website,_keyword,_dataobj,'"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
					+"',_project_task_id,_product_inner_id,_project_priority,_task_priority from _crawler_project_task where _status=0 and _project_id="+projectId+ " ORDER BY RAND()");
		}
		
		
		/**查找所有的任务
		 * @return
		 */
		public List<TaskInfo> findAllTaskInfo() {
			return (List<TaskInfo>) dao.list("from TaskInfo");
		}
	    
		/**根据TaskInfoID查找(主键)
		 * @param productID
		 * @return
		 */
		public TaskInfo findTaskInfoById(int taskInfoID){
			return dao.find(TaskInfo.class, taskInfoID);
		}
		/**
		 * 根据项目状态查找
		 * 
		 * @param status
		 * @return
		 */
		public List<TaskInfo> findTaskInfoByStatus(int status) {
			return (List<TaskInfo>) dao.list("from TaskInfo where status=" + status);
		}
		

		/**
		 * 查找超时的任务（任务下发时间+项目中任务执行的最大时间<=当前时间）
		 * @return
		 */
		public List<TaskInfo> findTaskInfoTimeout(int maxTime) {
			return (List<TaskInfo>) dao.list(
					"from TaskInfo where status=1 and UNIX_TIMESTAMP(distributedTime)+"
		+maxTime+"<UNIX_TIMESTAMP(NOW())");
		}
		
		/**
		 * 查找某个项目中新一轮任务发布，但还未完成的任务
		 * @param projectId
		 * @return
		 */
		public List<TaskInfo> findExpireTaskInfo(int projectId) {
			return (List<TaskInfo>) dao.list(
					"from TaskInfo where (status=0 or status=1) and projectID="
		+projectId);
		}
		
		/**更新任务信息
		 * @param product
		 */
		public void updateTaskInfo(TaskInfo taskInfo){
			dao.update(taskInfo);
		}		
		
		/**
		 * 更新任务的状态
		 * @param project
		 * @param status
		 */
		public void updateStatus(TaskInfo taskInfo,int status){
			taskInfo.setStatus(status);
			dao.update(taskInfo);
		}
		
}
