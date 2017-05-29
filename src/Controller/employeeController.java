package Controller;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.directoryDAO;
import DAO.employeeDAO;
import Model.directory;
import Model.registration;
import Model.retrieveFile;
import Model.salaryModel;

/**
 * Servlet implementation class employeeController
 */
@WebServlet("/employeeController")
public class employeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public employeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		
	
		if(flag.equals("inactive")){
			List<registration> fetch_inactive_employee=new ArrayList<registration>();
			registration registration=new registration();
			employeeDAO employeeDAO=new employeeDAO();
			registration.setEmployee_status("inactive");
			registration.setUser_type("employee");
			fetch_inactive_employee=employeeDAO.inactive_employee(registration);
			if(fetch_inactive_employee.size()>=1){
			request.setAttribute("fetch_inactive_employee", fetch_inactive_employee);
			RequestDispatcher rd=request.getRequestDispatcher("admin/inactiveEmployee.jsp");
			rd.forward(request, response);
			//response.sendRedirect("admin/inactiveEmployee.jsp");
			}
			else{
				request.setAttribute("error", "No-Inactive employees to view");
				RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("active")){
			List<registration> fetch_active_employee=new ArrayList<registration>();
			registration registration=new registration();
			employeeDAO employeeDAO=new employeeDAO();
			registration.setEmployee_status("active");
			fetch_active_employee=employeeDAO.active_employee(registration);
			request.setAttribute("fetch_active_employee", fetch_active_employee);
			RequestDispatcher rd=request.getRequestDispatcher("admin/activeEmployee.jsp");
			rd.forward(request, response);
			//response.sendRedirect("admin/activeEmployee.jsp");
			
		}
		else if(flag.equals("promoteEmployee")){
			List<registration> fetch_active_employee=new ArrayList<registration>();
			registration registration=new registration();
			employeeDAO employeeDAO=new employeeDAO();
			registration.setEmployee_status("active");
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
		else if(flag.equals("organizationalEmployee")){
			List<registration> fetch_active_employee=new ArrayList<registration>();
			registration registration=new registration();
			employeeDAO employeeDAO=new employeeDAO();
			fetch_active_employee=employeeDAO.promote_employee1(registration);
			if(fetch_active_employee.size()>=1){
			request.setAttribute("organization_profile_employee", fetch_active_employee);
			RequestDispatcher rd=request.getRequestDispatcher("admin/organizationalProfile.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("admin/organizationalProfile.jsp");
			}
			else{
				request.setAttribute("error", "No data found");
				RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("generatePaycheck")){
			List<registration> fetch_all_employee=new ArrayList<registration>();
			registration registration=new registration();
			employeeDAO employeeDAO=new employeeDAO();
			fetch_all_employee=employeeDAO.fetch_all_employee(registration);
			if(fetch_all_employee.size()>=1){
			request.setAttribute("fetch_all_employee", fetch_all_employee);
			RequestDispatcher rd=request.getRequestDispatcher("admin/generatePaycheck.jsp");
			rd.forward(request, response);
			//response.sendRedirect("admin/generatePaycheck.jsp");
			}
			else{
				request.setAttribute("error", "No data found");
				RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("updateSalary")){
			List<salaryModel> fetch_all_employee=new ArrayList<salaryModel>();
			
			
			Date date=new Date();
			java.text.SimpleDateFormat sdf1 =new java.text.SimpleDateFormat("MM");
			String currentTime1 = sdf1.format(date);
			
			employeeDAO employeeDAO=new employeeDAO();
			fetch_all_employee=employeeDAO.fetch_all_employee1(currentTime1);
			if(fetch_all_employee.size()>=1){
			request.setAttribute("fetch_all_employee", fetch_all_employee);
			RequestDispatcher rd=request.getRequestDispatcher("admin/updateSalary.jsp");
			rd.forward(request, response);
			}
			else{
				request.setAttribute("error", "No data found");
				RequestDispatcher rd=request.getRequestDispatcher("admin/updateSalary.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("update_employee_salary")){
			
			int salary_id=Integer.parseInt(request.getParameter("salary_id"));
			List<salaryModel> fetch_all_employee=new ArrayList<salaryModel>();
			salaryModel salaryModel=new salaryModel();
			salaryModel.setSalary_id(salary_id);
			employeeDAO employeeDAO=new employeeDAO();
			fetch_all_employee=employeeDAO.fetch_all_employee2(salaryModel);
			if(fetch_all_employee.size()>=1){
			request.setAttribute("fetch_all_employee", fetch_all_employee);
			RequestDispatcher rd=request.getRequestDispatcher("admin/updatePayroll.jsp");
			rd.forward(request, response);
			}
			else{
				request.setAttribute("error", "No data found");
				RequestDispatcher rd=request.getRequestDispatcher("admin/updatePayroll.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("fetch_user_paycheck")){
			String user_id = request.getParameter("user_id");
			List<registration> fetch_all_employee_payroll=new ArrayList<registration>();
			registration registration=new registration();
			registration.setUser_id(user_id);
			employeeDAO employeeDAO=new employeeDAO();
			fetch_all_employee_payroll=employeeDAO.fetch_all_employee_for_payroll(registration);
			request.setAttribute("fetch_all_employee_payroll", fetch_all_employee_payroll);
			RequestDispatcher rd=request.getRequestDispatcher("admin/generatePayroll.jsp");
			rd.forward(request, response);
			//response.sendRedirect("admin/generatePayroll.jsp");
		}
		else if(flag.equals("assignBonus")){
			String user_id = request.getParameter("user_id");
			List<registration> list_immediate_employees = new ArrayList<registration>();
			registration registration=new registration();
			registration.setUser_id(user_id);
			employeeDAO employeeDAO=new employeeDAO();
			list_immediate_employees=employeeDAO.list_immediate_employees(registration);
			if(list_immediate_employees.size()>=1){
				request.setAttribute("list_immediate_employees", list_immediate_employees);
				RequestDispatcher rd=request.getRequestDispatcher("manager/assignBonus.jsp");
				rd.forward(request, response);
				
				//response.sendRedirect("manager/assignBonus.jsp");
			}
			else{
				request.setAttribute("error", "Please assign Salary first");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("viewPayStatement")){
			String user_id = request.getParameter("user_id");
			List<salaryModel> salary_statement=new ArrayList<salaryModel>();
			salaryModel salaryModel=new salaryModel();
			salaryModel.setUser_id(user_id);
			employeeDAO employeeDAO=new employeeDAO();
			salary_statement=employeeDAO.getSalaryStatement(salaryModel);
			if(salary_statement.size()>0){
				request.setAttribute("salary_statement", salary_statement);
				RequestDispatcher rd=request.getRequestDispatcher("employee/viewPayStatement.jsp");
				rd.forward(request, response);
				//response.sendRedirect("employee/viewPayStatement.jsp");
			}
			else{
				request.setAttribute("error", "No Pay Statement generated yet");
				RequestDispatcher rd=request.getRequestDispatcher("employee/allContentEmployee.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("viewPayStatementManager")){
			String user_id = request.getParameter("user_id");
			List<salaryModel> salary_statement=new ArrayList<salaryModel>();
			salaryModel salaryModel=new salaryModel();
			salaryModel.setUser_id(user_id);
			employeeDAO employeeDAO=new employeeDAO();
			salary_statement=employeeDAO.getSalaryStatement(salaryModel);
			if(salary_statement.size()>0){
				request.setAttribute("salary_statement", salary_statement);
				RequestDispatcher rd=request.getRequestDispatcher("manager/viewPayStatementManager.jsp");
				rd.forward(request, response);
				//response.sendRedirect("employee/viewPayStatement.jsp");
			}
			else{
				request.setAttribute("error", "No Pay Statement generated yet");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("fetchProtectedDirectory")){
			String user_id = request.getParameter("user_id");
			List<directory> get_all_protected_directory=new ArrayList<directory>();
			List<registration> get_all_employee=new ArrayList<registration>();
			registration registration=new registration();
			registration.setUser_id(user_id);
			directory directory=new directory();
			directory.setUser_id(user_id);
			employeeDAO employeeDAO=new employeeDAO();
			String immediate_manager_id=employeeDAO.getImmediateManager(registration);
			
			registration registration1=new registration();
			registration1.setUser_id(immediate_manager_id);
			String immediate_manager_id1=employeeDAO.getImmediateManager(registration1);
			
			
			registration registration2=new registration();
			registration2.setUser_id(immediate_manager_id1);
			String immediate_manager_id2=employeeDAO.getImmediateManager(registration2);
			
			registration registration3=new registration();
			registration3.setUser_id(immediate_manager_id2);
			String immediate_manager_id3=employeeDAO.getImmediateManager(registration3);
			
			registration registration4=new registration();
			registration4.setUser_id(immediate_manager_id3);
			String immediate_manager_id4=employeeDAO.getImmediateManager(registration4);
			
			
			registration registration5=new registration();
			registration5.setUser_id(immediate_manager_id4);
			String immediate_manager_id5=employeeDAO.getImmediateManager(registration5);
			
			
			
			get_all_protected_directory=employeeDAO.getProtectedDirectories(directory,immediate_manager_id,immediate_manager_id1,immediate_manager_id2,immediate_manager_id3,immediate_manager_id4,immediate_manager_id5);
			if(get_all_protected_directory.size()>0){
				get_all_employee=employeeDAO.getAllEmployee(registration);
				request.setAttribute("get_all_employee", get_all_employee);
				request.setAttribute("get_all_protected_directory", get_all_protected_directory);
				RequestDispatcher rd=request.getRequestDispatcher("manager/viewProtectedDirectories.jsp");
				rd.forward(request, response);
				//response.sendRedirect("employee/viewPayStatement.jsp");
			}
			else{
				request.setAttribute("error", "No Protected Directories Available");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
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
		if(flag.equals("assignSalary")){
			String user_id=request.getParameter("user_id");
			String first_name=request.getParameter("first_name");
			String last_name=request.getParameter("last_name");
			String email_id=request.getParameter("email_id");
			String division_name=request.getParameter("division_name");
			String user_type=request.getParameter("user_type");
			double salary=Double.parseDouble(request.getParameter("salary"));
			
			Date date=new Date();
			java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(date);
			salaryModel salaryModel=new salaryModel();
			salaryModel.setUser_id(user_id);
			salaryModel.setFirst_name(first_name);
			salaryModel.setLast_name(last_name);
			salaryModel.setEmail_id(email_id);
			salaryModel.setDivision_name(division_name);
			salaryModel.setUser_type(user_type);
			salaryModel.setSalary_per_month(salary);
			salaryModel.setDate(currentTime);
			 
			employeeDAO employeeDAO=new employeeDAO();
			employeeDAO.setEmployeeSalary(salaryModel);
			request.setAttribute("error", "Salary Assigned Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("assignBonusEmployee")){
			Date date=new Date();
			java.text.SimpleDateFormat sdf1 =new java.text.SimpleDateFormat("MM");
			String currentTime1 = sdf1.format(date);
			String user_id=request.getParameter("manager");
			Double bonus= Double.parseDouble(request.getParameter("bonus"));
			
			salaryModel salaryModel=new salaryModel();
			salaryModel.setUser_id(user_id);
			salaryModel.setBonus(bonus);
			
			employeeDAO employeeDAO=new employeeDAO();
			employeeDAO.allocateBonus(salaryModel,currentTime1);
			request.setAttribute("error", "Bonus Assigned Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("updateSalaryEmployee")){
			int salary_id=Integer.parseInt(request.getParameter("salary_id"));
			float salary=Float.parseFloat(request.getParameter("salary"));
			salaryModel salaryModel=new salaryModel();
			salaryModel.setSalary_per_month(salary);
			salaryModel.setSalary_id(salary_id);
			employeeDAO.updateSalary(salaryModel);
			request.setAttribute("error", "Salary Updated Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("giveAccess")){
			String ate_id=request.getParameter("ate_id");
			int get_directory_id=Integer.parseInt(request.getParameter("get_directory_id"));
			directory directory = new directory();
			directory.setDirectory_id(get_directory_id);
			
			directoryDAO directoryDAO=new directoryDAO();
			String directory_name=directoryDAO.getDirectoryName(directory);
			directory.setDirectory_name(directory_name);
			String getAllAte=directoryDAO.getAllAte(directory);
			if(getAllAte.equals("N/A")){
				directory.setDirectory_name(directory_name);
				directory.setAte_id(ate_id);
				//System.out.println("1"+directory_name+ate_id);

				directoryDAO.updateATEinUploadFile(directory_name,ate_id);
				directoryDAO.enterAte(directory);
			}
			else{
				//System.out.println("2");
				retrieveFile retrieveFile=new retrieveFile();
				retrieveFile.setAte_id(getAllAte);
				directory.setDirectory_name(directory_name);
				getAllAte=getAllAte+"/"+ate_id;
				directoryDAO.updateUploadAte(retrieveFile,getAllAte);
				directory.setAte_id(getAllAte);

				directoryDAO.updateATEinUploadFile1(directory_name,getAllAte);
				directoryDAO.enterAte(directory);
			}
			request.setAttribute("error", "Access given to Another Team Employee");
			RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
			rd.forward(request, response);
		}
	}

}
