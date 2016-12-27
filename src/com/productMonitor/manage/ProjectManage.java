package com.productMonitor.manage;

import java.util.Date;
import java.util.List;

import com.productMonitor.data.Project;
import com.productMonitor.hibernate.BaseDAO;

public class ProjectManage {

	
	//创建一个BaseDAO数据处理对象
		BaseDAO dao=new BaseDAO();
		
		/**插入产品
		 * @param product
		 * @return
		 * @throws Exception
		 */
		public boolean insertProject(Project project) throws Exception{		
			return dao.create(project);
		}
		
		/**查找所有的产品
		 * @return
		 */
		public List<Project> findAllProjects() {
			return (List<Project>) dao.list("from Project");
		}
	    
		/**根据ProjectID查找(主键)
		 * @param productID
		 * @return
		 */
		public Project findProjectById(int projectID){
			return dao.find(Project.class, projectID);
		}
		/**
		 * 根据项目状态查找
		 * 
		 * @param status
		 * @return
		 */
		public List<Project> findProjectByStatus(int status) {
			return (List<Project>) dao.list("from Project where status=" + status);
		}
		

		/**更新项目信息
		 * @param product
		 */
		public void updateProject(Project project){
			dao.update(project);
		}		
		
		
		
		
}
