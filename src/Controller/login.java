package Controller;

import java.io.IOException;    
import java.util.Calendar;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import serviceLayer.validation;
import DAO.leaveRequestDAO;
import DAO.loginDAO;
import DAO.registrationDAO;
import Model.leaveRemainingVO;
import Model.loginModel;
import Model.registration;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String flag=request.getParameter("flag");
		
		if(flag.equals("login")){ 
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		loginModel login = new loginModel();
		login.setUser_id(userId);
		login.setPassword(password);
		
		validation val = new validation();
		String validationMandatory=val.mandatory_login(login);
		if(validationMandatory.equals("All fields are Mandatory") || validationMandatory.equals("User Id is mandatory!") || validationMandatory.equals("Password is mandatory!")){
			request.setAttribute("error", validationMandatory);
			RequestDispatcher rd=request.getRequestDispatcher("employee/signin.jsp");
			rd.forward(request, response);
		}
		String matchFields=val.parameters_login(login);
		if( matchFields.equals("Password Should be alphabetic only!") || matchFields.equals("User Id Should be numeric only!")){
			request.setAttribute("error", matchFields);
			RequestDispatcher rd=request.getRequestDispatcher("employee/signin.jsp");
			rd.forward(request, response);
		}
		loginDAO dao = new loginDAO();
		String value=dao.loginUser(login);
		String value1=dao.loginUser1(login);
		
		if(value.equals("Invalid User Credentials")){
			request.setAttribute("error", "Invalid UserId and Password");
			RequestDispatcher rd=request.getRequestDispatcher("employee/signin.jsp");
			rd.forward(request, response);
		}
		else{
			
			session.setAttribute("userId", userId);
			session.setAttribute("firstName", value);
			session.setAttribute("lastName", value1);
			String type=dao.getUserType(login);
			session.setAttribute("type", type);
			
				if(type.equals("employee")){
					
					registrationDAO registrationDAO=new registrationDAO();
					String status=registrationDAO.activeOrInactive(login);
					
					if(status.equals("inactive")){
						session.setAttribute("status", status);
						response.sendRedirect("employee/allContentEmployee.jsp");
					}
					else{
					response.sendRedirect("employee/allContentEmployee.jsp");
					}
				}
				else if(type.equals("manager")){
					response.sendRedirect("manager/allContentManager.jsp");
				}
				else if(type.equals("admin")){
					response.sendRedirect("admin/allContentAdmin.jsp");
				}
			
			
		}
	}

		if(flag.equals("register")){
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			Long phone = Long.parseLong(request.getParameter("phone"));
			String emailId = request.getParameter("emailId");
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			String user_type = "employee";
			String employee_status = "inactive";
			registration reg = new registration();
			reg.setFirst_name(firstName);
			reg.setLast_name(lastName);
			reg.setEmail_id(emailId);
			reg.setUser_id(userId);
			reg.setPassword(password);
			reg.setConfirmpassword(confirmPassword);
			reg.setUser_type(user_type);
			reg.setEmployee_status(employee_status);
			reg.setAddress(address);
			reg.setPhone(phone);
			
			
			leaveRemainingVO leaveRemainingVO=new leaveRemainingVO();
			leaveRemainingVO.setUser_id(userId);
			leaveRemainingVO.setLeave_available(4);
			
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			
			
			validation val = new validation();
			String validationMandatory=val.mandatory(reg);
			if(validationMandatory.equals("All fields are Mandatory") || validationMandatory.equals("Address is mandatory!") || validationMandatory.equals("Phone No. should be 10 digits long!") || validationMandatory.equals("First name is mandatory!") || validationMandatory.equals("Last name is mandatory!") || validationMandatory.equals("Email Id is mandatory!") || validationMandatory.equals("User Id is mandatory!") || validationMandatory.equals("Password is mandatory!") || validationMandatory.equals("Confirm Password is mandatory!")){
				request.setAttribute("error", validationMandatory);
				RequestDispatcher rd=request.getRequestDispatcher("employee/register.jsp");
				rd.forward(request, response);
			}
			else
			{
				String matchFields=val.parameters(reg);
				if(matchFields.equals("Password and Confirm - Password Should be Same!") || matchFields.equals("Password Should be alphabetic only!") || matchFields.equals("User Id Should be numeric only!")){
					request.setAttribute("error", matchFields);
					RequestDispatcher rd=request.getRequestDispatcher("employee/register.jsp");
					rd.forward(request, response);
				}
				else{
					registrationDAO regdao=new registrationDAO();
					String validateRegistration=regdao.validate(reg);
					
					if(validateRegistration.equals("Email-Id is already registered!")){
						request.setAttribute("error", validateRegistration);
						RequestDispatcher rd=request.getRequestDispatcher("employee/register.jsp");
						rd.forward(request, response);
					}
					else if(validateRegistration.equals("User-Id is already registered!")){
						request.setAttribute("error", validateRegistration);
						RequestDispatcher rd=request.getRequestDispatcher("employee/register.jsp");
						rd.forward(request, response);
					}
					else{
						
						regdao.register(reg);
						leaveRequestDAO.leaveAvailable(leaveRemainingVO);
						request.setAttribute("first_name", firstName);
						request.setAttribute("last_name", lastName);
						RequestDispatcher rd=request.getRequestDispatcher("employee/registered.jsp");
						rd.forward(request, response);
					}
				}	
			}
		}
		
		
	}
}
