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

import serviceLayer.updateValidation;
import DAO.leaveRequestDAO;
import DAO.registrationDAO;
import DAO.uploadDAO;
import Model.leaveRequest;
import Model.registration;
import Model.uploadFile;

/**
 * Servlet implementation class updateProfileController
 */
@WebServlet("/updateProfileController")
public class updateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			HttpSession session=request.getSession();
			String flag=request.getParameter("flag");
			
			if(flag.equals("updateProfile")){
			String user_id=request.getParameter("user_id");
			List<registration> upd_profile=new ArrayList<registration>();
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			upd_profile=registrationDAO.fetchProfile(registration);
			request.setAttribute("upd_profile", upd_profile);
			RequestDispatcher rd=request.getRequestDispatcher("employee/updateProfile.jsp");
			rd.forward(request, response);
			//response.sendRedirect("employee/updateProfile.jsp");
		}
			else if(flag.equals("updateProfileManager")){
				String user_id=request.getParameter("user_id");
				List<registration> upd_profile=new ArrayList<registration>();
				registration registration=new registration();
				registration.setUser_id(user_id);
				registrationDAO registrationDAO=new registrationDAO();
				upd_profile=registrationDAO.fetchProfile(registration);
				request.setAttribute("upd_profile", upd_profile);
				RequestDispatcher rd=request.getRequestDispatcher("manager/updateProfileManager.jsp");
				rd.forward(request, response);
				//response.sendRedirect("manager/updateProfileManager.jsp");
			}
			else if(flag.equals("organizationalProfile")){
				String user_id=request.getParameter("user_id");
				registration registration=new registration();
				registration.setUser_id(user_id);
				registrationDAO registrationDAO=new registrationDAO();
				List<registration> upd_profile=new ArrayList<registration>();
				List<registration> manager_list=new ArrayList<registration>();
				upd_profile=registrationDAO.fetchOrgzProfile(registration);
				String immediate_manager_id=registrationDAO.getImmediateManager(registration);
				session.setAttribute("immediate_manager_id", immediate_manager_id);
				registration registration1=new registration();
				registration1.setUser_id(immediate_manager_id);
				String name=registrationDAO.getManagerName(registration1);
				registration1.setUser_id(user_id);
				registration1.setImmediate_manager_id(immediate_manager_id);
				
				manager_list=registrationDAO.getManagerList(registration1);
				session.setAttribute("manager_name", name);
				request.setAttribute("upd_org_profile", upd_profile);
				request.setAttribute("manager_list", manager_list);
				RequestDispatcher rd=request.getRequestDispatcher("admin/updateOrganizationalProfile.jsp");
				rd.forward(request, response);
				
				//response.sendRedirect("admin/updateOrganizationalProfile.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
			String flag=request.getParameter("flag");
		
			if(flag.equals("updateUserProfile")){
			String userId=request.getParameter("userId");
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");
			String address=request.getParameter("address");
			Long phone=Long.parseLong(request.getParameter("phone"));
			String emailId=request.getParameter("emailId");
			String password=request.getParameter("password");
			String confirmPassword=request.getParameter("confirmPassword");
			
			registration registration=new registration();
			registration.setFirst_name(firstName);
			registration.setLast_name(lastName);
			registration.setEmail_id(emailId);
			registration.setUser_id(userId);
			registration.setPassword(password);
			registration.setConfirmpassword(confirmPassword);
			registration.setAddress(address);
			registration.setPhone(phone);
			
			leaveRequest leaveRequest=new leaveRequest();
			leaveRequest.setUser_id(userId);
			leaveRequest.setFirst_name(firstName);
			leaveRequest.setLast_name(lastName);
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			
			updateValidation val = new updateValidation();
			String validationMandatory=val.mandatory(registration);
			
			if(validationMandatory.equals("All fields are Mandatory") || validationMandatory.equals("Address is mandatory!") || validationMandatory.equals("Phone No. should be 10 digits long!") || validationMandatory.equals("First name is mandatory!") || validationMandatory.equals("Last name is mandatory!") || validationMandatory.equals("Email Id is mandatory!") || validationMandatory.equals("User Id is mandatory!") || validationMandatory.equals("Password is mandatory!") || validationMandatory.equals("Confirm Password is mandatory!")){
				List<registration> upd_profile=new ArrayList<registration>();
				registrationDAO registrationDAO=new registrationDAO();
				upd_profile=registrationDAO.fetchProfile(registration);
				request.setAttribute("upd_profile", upd_profile);
				request.setAttribute("error", validationMandatory);
				RequestDispatcher rd=request.getRequestDispatcher("employee/updateProfile.jsp");
				rd.forward(request, response);
			}
			else
			{
				String matchFields=val.parameters(registration);
				if(matchFields.equals("Password and Confirm - Password Should be Same!") || matchFields.equals("Password Should be alphabetic only!") || matchFields.equals("User Id Should be numeric only!")){
					List<registration> upd_profile=new ArrayList<registration>();
					registrationDAO registrationDAO=new registrationDAO();
					upd_profile=registrationDAO.fetchProfile(registration);
					request.setAttribute("upd_profile", upd_profile);
					request.setAttribute("error", matchFields);
					RequestDispatcher rd=request.getRequestDispatcher("employee/updateProfile.jsp");
					rd.forward(request, response);
				}
				else{
					registrationDAO registrationDAO=new registrationDAO();
					registrationDAO.updateUserProfile(registration);
					leaveRequestDAO.updateName(leaveRequest);
					request.setAttribute("error", "User Profile Updated");
					RequestDispatcher rd=request.getRequestDispatcher("employee/allContentEmployee.jsp");
					rd.forward(request, response);
				}
			}
		}
			else if(flag.equals("updateUserProfileManager")){
				String userId=request.getParameter("userId");
				String firstName=request.getParameter("firstName");
				String lastName=request.getParameter("lastName");
				String address=request.getParameter("address");
				Long phone=Long.parseLong(request.getParameter("phone"));
				String emailId=request.getParameter("emailId");
				String password=request.getParameter("password");
				String confirmPassword=request.getParameter("confirmPassword");
				
				registration registration=new registration();
				registration.setFirst_name(firstName);
				registration.setLast_name(lastName);
				registration.setEmail_id(emailId);
				registration.setUser_id(userId);
				registration.setPassword(password);
				registration.setConfirmpassword(confirmPassword);
				registration.setAddress(address);
				registration.setPhone(phone);
				updateValidation val = new updateValidation();
				String validationMandatory=val.mandatory(registration);
				
				if(validationMandatory.equals("All fields are Mandatory") || validationMandatory.equals("Address is mandatory!") || validationMandatory.equals("Phone No. should be 10 digits long!") || validationMandatory.equals("First name is mandatory!") || validationMandatory.equals("Last name is mandatory!") || validationMandatory.equals("Email Id is mandatory!") || validationMandatory.equals("User Id is mandatory!") || validationMandatory.equals("Password is mandatory!") || validationMandatory.equals("Confirm Password is mandatory!")){
					List<registration> upd_profile=new ArrayList<registration>();
					registrationDAO registrationDAO=new registrationDAO();
					upd_profile=registrationDAO.fetchProfile(registration);
					request.setAttribute("upd_profile", upd_profile);
					request.setAttribute("error", validationMandatory);
					RequestDispatcher rd=request.getRequestDispatcher("manager/updateProfileManager.jsp");
					rd.forward(request, response);
				}
				else
				{
					String matchFields=val.parameters(registration);
					if(matchFields.equals("Password and Confirm - Password Should be Same!") || matchFields.equals("Password Should be alphabetic only!") || matchFields.equals("User Id Should be numeric only!")){
						List<registration> upd_profile=new ArrayList<registration>();
						registrationDAO registrationDAO=new registrationDAO();
						upd_profile=registrationDAO.fetchProfile(registration);
						request.setAttribute("upd_profile", upd_profile);
						request.setAttribute("error", matchFields);
						RequestDispatcher rd=request.getRequestDispatcher("manager/updateProfileManager.jsp");
						rd.forward(request, response);
					}
					else{
						registrationDAO registrationDAO=new registrationDAO();
						registrationDAO.updateUserProfile(registration);
						request.setAttribute("error", "User Profile Updated");
						RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
						rd.forward(request, response);
					}
				}
			}
			else if(flag.equals("updateOrgProfile")){
				String userId=request.getParameter("userId");
				String division=request.getParameter("division");
				String role=request.getParameter("role");
				String supervisor_id=request.getParameter("supervisor_id");
				
				registration registration=new registration();
				registration.setUser_id(supervisor_id);
				registrationDAO registrationDAO=new registrationDAO();
				String manager_hierarchy=registrationDAO.getManagerHierarchy(registration);
				if(manager_hierarchy!=null){
					registration.setUser_id(userId);
					registration.setDivision_name(division);
					registration.setUser_type(role);
					manager_hierarchy=manager_hierarchy+userId+"/";
					registration.setManager_hierarchy(manager_hierarchy);
					registration.setImmediate_manager_id(supervisor_id);
					
					uploadFile uploadFile=new uploadFile();
					uploadDAO uploadDAO=new uploadDAO();
					uploadFile.setUser_id(userId);
					uploadFile.setManager_hierarchy(manager_hierarchy);
					
					uploadDAO.updateDirectory(uploadFile);
					registrationDAO.updateManagerHierarchy(registration);
					String new_manager_hierarchy=registrationDAO.newManagerHierarchy(registration);
					registration.setImmediate_manager_id(userId);
					String new_user_id=registrationDAO.getUser(registration);
					
					
					
					if(new_user_id!=null && new_manager_hierarchy!=null){
					new_manager_hierarchy=new_manager_hierarchy+new_user_id+"/";
					registration.setUser_id(new_user_id);
					registration.setManager_hierarchy(new_manager_hierarchy);
					uploadFile.setUser_id(new_user_id);
					uploadFile.setManager_hierarchy(new_manager_hierarchy);
					uploadDAO.updateDirectory(uploadFile);
					registrationDAO.updateSubManagerHierarchy(registration);
					}
					request.setAttribute("error", "Employee's Organizational Profile updated");
					RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
					rd.forward(request, response);
				}
				else{
					manager_hierarchy="/"+userId+"/";
					registration.setUser_id(userId);
					registration.setManager_hierarchy(manager_hierarchy);
					registration.setImmediate_manager_id(supervisor_id);
					registrationDAO.updateManagerHierarchy1(registration);
					request.setAttribute("error", "Employee's Organizational Profile updated");
					RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
					rd.forward(request, response);
				}
			}
	}

}
