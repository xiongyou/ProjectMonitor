package com.productMonitor.manage;

import java.util.Date;
import java.util.List;


import com.productMonitor.data.ProjectTask;
import com.productMonitor.hibernate.BaseDAO;

public class ProjectTaskManage {

	
	//创建一个BaseDAO数据处理对象
		BaseDAO dao=new BaseDAO();
		
		/**
		 * 插入		 
		 * @param projectTask
		 * @return
		 * @throws Exception
		 */
		public boolean insertProjectTask(ProjectTask projectTask) throws Exception{		
			return dao.create(projectTask);
		}
		
		/**查找所有
		 * @return
		 */
		public List<ProjectTask> findAllProjectTask() {
			return (List<ProjectTask>) dao.list("from ProjectTask");
		}
	    
		/**根据ID查找(主键)
		 * @param productID
		 * @return
		 */
		public ProjectTask findProjectTaskById(int project_TaskID){
			return dao.find(ProjectTask.class, project_TaskID);
		}
		/**
		 * 根据任务状态查找任务
		 * 
		 * @param status
		 * @return
		 */
		public List<ProjectTask> findProjectTaskByStatus(int status) {
			return (List<ProjectTask>) dao.list("from ProjectTask where status=" + status);
		}
		
		/**
		 * 根据项目id查找任务
		 * @param projectId
		 * @return
		 */
		public List<ProjectTask> findProjectTaskByProjectId(int projectId) {
			return (List<ProjectTask>) dao.list("from ProjectTask where projectID=" + projectId);
		}
		
		
		/**更新任务信息
		 * @param product
		 */
		public void updateProjectTask(ProjectTask projectTask){
			dao.update(projectTask);
		}		
		
		/**
		 * 更新任务的状态
		 * @param project
		 * @param status
		 
		public void updateStatus(ProjectTask projectTask,int status){
			projectTask.setStatus(status);
			dao.update(projectTask);
		}
		*/
		
}
