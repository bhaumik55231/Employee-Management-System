package Controller;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.directoryDAO;
import DAO.employeeDAO;
import Model.directory;
import Model.registration;

/**
 * Servlet implementation class assignManagerController
 */
@WebServlet("/assignManagerController")
public class assignManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public assignManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
				
		if(flag.equals("fetch_employees"))
		{
			List<registration> fetch_employee=new ArrayList<registration>();
			List<registration> fetch_manager=new ArrayList<registration>();
			registration registration=new registration();
			employeeDAO employeeDAO=new employeeDAO();
			fetch_employee=employeeDAO.fetch_inactive_employee(registration);
			fetch_manager=employeeDAO.fetch_manager1(registration);
			if(fetch_manager.size()>=1 && fetch_manager!=null ){
			if(fetch_employee.size()>=1 && fetch_employee!=null){
				
			request.setAttribute("fetch_employee", fetch_employee);
			request.setAttribute("fetch_manager", fetch_manager);
			RequestDispatcher rd=request.getRequestDispatcher("admin/assignManager.jsp");
			rd.forward(request, response);
			//response.sendRedirect("admin/assignManager.jsp");
			}
			else{
				request.setAttribute("error", "No Inactive Employees");
				RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
				rd.forward(request, response);
			}
			}
			else
			{
				request.setAttribute("error", "No Manager");
				RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("makeManager")){
			String first_name=request.getParameter("first_name");
			String user_id=request.getParameter("user_id");
			
			directory directory=new directory();
			directory.setDirectory_access_permissions("public");
			directory.setDirectory_description("Default Public Directory");
			directory.setDirectory_name(first_name +"_Auto_directory");
			directory.setUser_id(user_id);
			directoryDAO directoryDAO=new directoryDAO();
			String manager_hierarchy=directoryDAO.getHierarchy(directory);
			directory.setManager_hierarchy(manager_hierarchy);
			directoryDAO.createDirectory(directory);
			
			registration registration=new registration();
			registration.setUser_id(user_id);
			employeeDAO employeeDAO=new employeeDAO();
			employeeDAO.makeManager(registration);
			List<registration> fetch_active_employee=new ArrayList<registration>();
			fetch_active_employee=employeeDAO.promote_employee(registration);
			if(fetch_active_employee.size()>=1){
			request.setAttribute("fetch_active_employee", fetch_active_employee);
			RequestDispatcher rd=request.getRequestDispatcher("admin/promoteEmployee.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("admin/promoteEmployee.jsp");
			}
			else{
				request.setAttribute("error", "No data found");
				RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
				rd.forward(request, response);
			}
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
					
		if(flag.equals("manager_assigned")){
		String user_id=request.getParameter("employee");
		String manager_id=request.getParameter("manager");
		registration registration=new registration();
		employeeDAO employeeDAO=new employeeDAO();
		registration.setUser_id(manager_id);
		String user_type=employeeDAO.getUser_type(registration);
		if(user_type.equals("employee")){
			registration.setUser_type("manager");
			
			directory directory=new directory();
			directory.setDirectory_access_permissions("public");
			directory.setDirectory_description("Auto Created Public Directory");
			directory.setDirectory_name(manager_id +"_Auto_directory");
			directory.setUser_id(manager_id);
			directoryDAO directoryDAO=new directoryDAO();
			String manager_hierarchy=directoryDAO.getHierarchy(directory);
			directory.setManager_hierarchy(manager_hierarchy);
			directoryDAO.createDirectory(directory);
			
			
			employeeDAO.updateUserType(registration);//Updating status of employee to manager if another employee is assigned to this manager.
		}
		
		registration.setImmediate_manager_id(manager_id);
		String hierarchy=employeeDAO.findHierarchy(registration);
		if(hierarchy==null){
			registration.setUser_id(manager_id);//Checking that manager has any hierarchy or not
			String fetch_manager_hierarchy=employeeDAO.fetchManagerHierarchy(registration);
			if(fetch_manager_hierarchy==null){
			registration.setUser_id(user_id);
			String manager_hierarchy=manager_id;
			registration.setManager_hierarchy(manager_hierarchy);
			employeeDAO.assignManager(registration);
			request.setAttribute("error", "Manager Assigned Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
			rd.forward(request, response);
			}
			else{
				
				registration.setUser_id(user_id);
				String manager_hierarchy=fetch_manager_hierarchy+user_id +"/";
				registration.setManager_hierarchy(manager_hierarchy);
				employeeDAO.assignManager(registration);
				request.setAttribute("error", "Manager Assigned Successfully");
				RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
				rd.forward(request, response);
			}
		}
		else{
			registration.setUser_id(user_id);
			String manager_hierarchy=hierarchy +user_id +"/";
			registration.setManager_hierarchy(manager_hierarchy);
			employeeDAO.assignManager1(registration);
			request.setAttribute("error", "Manager Assigned Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
			rd.forward(request, response);
			}
		}
		
	}

}
