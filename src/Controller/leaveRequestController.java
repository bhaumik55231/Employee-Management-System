package Controller;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.leaveRequestDAO;
import DAO.registrationDAO;
import Model.leaveRemainingVO;
import Model.leaveRequest;
import Model.registration;

/**
 * Servlet implementation class leaveRequestController
 */
@WebServlet("/leaveRequestController")
public class leaveRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public leaveRequestController() {
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
		
		if(flag.equals("availability")){
			String user_id=request.getParameter("user_id");
			
			List<leaveRequest> list_of_leaves=new ArrayList<leaveRequest>();
			leaveRequest leaveRequest=new leaveRequest();
			leaveRequest.setUser_id(user_id);
			
			leaveRemainingVO leaveRemainingVO=new leaveRemainingVO();
			leaveRemainingVO.setUser_id(user_id);
			
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			int check=leaveRequestDAO.checkAvailability(leaveRemainingVO);
			if(check>0){
				response.sendRedirect("employee/leaveRequestEmployee.jsp");
			}
			else{
				request.setAttribute("error", "You Don't have Enough leaves");
				RequestDispatcher rd=request.getRequestDispatcher("employee/allContentEmployee.jsp");
				rd.forward(request, response);
			}
		}
		if(flag.equals("viewStatus")){
			String user_id=request.getParameter("user_id");
			
			List<leaveRequest> list_of_leaves=new ArrayList<leaveRequest>();
			leaveRequest leaveRequest=new leaveRequest();
			leaveRequest.setUser_id(user_id);
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			list_of_leaves=leaveRequestDAO.list_of_leaves(leaveRequest);
			if(list_of_leaves.size()>=1){
			request.setAttribute("list_of_leaves",list_of_leaves);
			RequestDispatcher rd=request.getRequestDispatcher("employee/viewLeaveStatus.jsp");
			rd.forward(request, response);
			//response.sendRedirect("employee/viewLeaveStatus.jsp");
			}
			else{
				request.setAttribute("error", "You Don't Requested for any leaves yet");
				RequestDispatcher rd=request.getRequestDispatcher("employee/allContentEmployee.jsp");
				rd.forward(request, response);
			}
		}
		if(flag.equals("viewStatusManager")){
			String user_id=request.getParameter("user_id");
			
			List<leaveRequest> list_of_leaves=new ArrayList<leaveRequest>();
			leaveRequest leaveRequest=new leaveRequest();
			leaveRequest.setUser_id(user_id);
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			list_of_leaves=leaveRequestDAO.list_of_leaves(leaveRequest);
			if(list_of_leaves.size()>=1){
			session.setAttribute("list_of_leaves",list_of_leaves);
			response.sendRedirect("manager/viewLeaveStatusManager.jsp");
			}
			else{
				request.setAttribute("error", "You Don't request for leaves yet");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("availabilityManager")){
			String user_id=request.getParameter("user_id");
			leaveRemainingVO leaveRemainingVO=new leaveRemainingVO();
			leaveRemainingVO.setUser_id(user_id);
			
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			int check=leaveRequestDAO.checkAvailability(leaveRemainingVO);
			if(check>0){
				response.sendRedirect("manager/leaveRequestManager.jsp");
			}
			else{
				request.setAttribute("error", "You Don't have Enough leaves");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("fetchLeaveRequest")){
			String immediate_manager_id=request.getParameter("user_id");
			List<leaveRequest> leaveRequestList = new ArrayList<leaveRequest>();
			leaveRequest leaveRequest=new leaveRequest();
			leaveRequest.setImmediate_manager_id(immediate_manager_id);
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			leaveRequestList=leaveRequestDAO.fetchLeaveRequest(leaveRequest);
			if(leaveRequestList.size()>=1){
				session.setAttribute("leaveRequestList",leaveRequestList);
				response.sendRedirect("manager/approveDeclineRequest.jsp");
			}
			else{
				request.setAttribute("error", "No Leave Request Pending");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
			
		}
		
		else if(flag.equals("approve")){
			int leave_id=Integer.parseInt(request.getParameter("leave_id"));
			int days=Integer.parseInt(request.getParameter("days"));
			String immediate_manager_id=request.getParameter("user_id");
			leaveRequest leaveRequest = new leaveRequest();
			leaveRequest.setLeave_id(leave_id);
			
			leaveRequestDAO leaveRequestDAO = new leaveRequestDAO();
			leaveRequestDAO.approveLeave(leaveRequest);
			String user_id=leaveRequestDAO.getUserId(leaveRequest);
			
			leaveRemainingVO leaveRemainingVO=new leaveRemainingVO();
			leaveRemainingVO.setUser_id(user_id);
			int leave_available=leaveRequestDAO.checkAvailability(leaveRemainingVO);
			leave_available=leave_available-days;
			leaveRemainingVO.setLeave_available(leave_available);
			leaveRequestDAO.updateLeaveAvailibility(leaveRemainingVO);
			
			
			List<leaveRequest> leaveRequestList = new ArrayList<leaveRequest>();
			leaveRequest.setImmediate_manager_id(immediate_manager_id);
			leaveRequestList=leaveRequestDAO.fetchLeaveRequest(leaveRequest);
			session.setAttribute("leaveRequestList",leaveRequestList);
			request.setAttribute("error", "Leave Request Approved");
			RequestDispatcher rd=request.getRequestDispatcher("manager/approveDeclineRequest.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("decline")){
			int leave_id=Integer.parseInt(request.getParameter("leave_id"));
			leaveRequest leaveRequest = new leaveRequest();
			leaveRequest.setLeave_id(leave_id);
			leaveRequestDAO leaveRequestDAO = new leaveRequestDAO();
			leaveRequestDAO.declineLeave(leaveRequest);
			String immediate_manager_id=request.getParameter("user_id");
			List<leaveRequest> leaveRequestList = new ArrayList<leaveRequest>();
			leaveRequest.setImmediate_manager_id(immediate_manager_id);
			leaveRequestList=leaveRequestDAO.fetchLeaveRequest(leaveRequest);
			session.setAttribute("leaveRequestList",leaveRequestList);
			request.setAttribute("error", "Leave Request Declined");
			RequestDispatcher rd=request.getRequestDispatcher("manager/approveDeclineRequest.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("makeManager")){
			
			Timer timer1 = new Timer();
		    Calendar date = Calendar.getInstance();
		    date.set(Calendar.DAY_OF_MONTH, 29);
		    date.set(Calendar.HOUR_OF_DAY, 20);
		    date.set(Calendar.MINUTE, 26);
		    date.set(Calendar.SECOND, 0);
		    date.set(Calendar.MILLISECOND, 0);
		    System.out.println("testing");
		    // Schedule to run every Sunday in midnight
		    timer1.schedule(
		      new leaveRequestDAO(),
		      date.getTime(),
		      1000 * 60 * 60 * 24 * 7
		    );
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		if(flag.equals("add")){
			String user_id=request.getParameter("user_id");
			String first_name=request.getParameter("first_name");
			String last_name=request.getParameter("last_name");
			String date=request.getParameter("date");
			int days=Integer.parseInt(request.getParameter("days"));
			String type=request.getParameter("type");
			String description=request.getParameter("description");
			
			leaveRequest leaveRequest=new leaveRequest();
			leaveRequest.setDate(date);
			leaveRequest.setFirst_name(first_name);
			leaveRequest.setLast_name(last_name);
			leaveRequest.setDays(days);
			leaveRequest.setLeave_type(type);
			leaveRequest.setLeave_description(description);
			leaveRequest.setUser_id(user_id);
			
			leaveRemainingVO leaveRemainingVO=new leaveRemainingVO();
			leaveRemainingVO.setUser_id(user_id);
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			int check=leaveRequestDAO.checkAvailability(leaveRemainingVO);
			
			if(check>=days){
			
			
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String immediate_manager_id=registrationDAO.getImmediateManager(registration);
			
			leaveRequest.setImmediate_manager_id(immediate_manager_id);
			leaveRequestDAO.insertLeaveRequest(leaveRequest);
			
			request.setAttribute("error", "Your Leave Request is Submitted");
			RequestDispatcher rd=request.getRequestDispatcher("employee/allContentEmployee.jsp");
			rd.forward(request, response);
			
			}
			else{
				request.setAttribute("error", "You don't have this many leaves remaining");
				RequestDispatcher rd=request.getRequestDispatcher("employee/allContentEmployee.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("addManager")){
			String user_id=request.getParameter("user_id");
			String first_name=request.getParameter("first_name");
			String last_name=request.getParameter("last_name");
			String date=request.getParameter("date");
			int days=Integer.parseInt(request.getParameter("days"));
			String type=request.getParameter("type");
			String description=request.getParameter("description");
			
			leaveRequest leaveRequest=new leaveRequest();
			leaveRequest.setDate(date);
			leaveRequest.setFirst_name(first_name);
			leaveRequest.setLast_name(last_name);
			leaveRequest.setDays(days);
			leaveRequest.setLeave_type(type);
			leaveRequest.setLeave_description(description);
			leaveRequest.setUser_id(user_id);
			
			leaveRemainingVO leaveRemainingVO=new leaveRemainingVO();
			leaveRemainingVO.setUser_id(user_id);
			leaveRequestDAO leaveRequestDAO=new leaveRequestDAO();
			int check=leaveRequestDAO.checkAvailability(leaveRemainingVO);
			
			if(check>=days){
			
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String immediate_manager_id=registrationDAO.getImmediateManager(registration);
			
			leaveRequest.setImmediate_manager_id(immediate_manager_id);
			leaveRequestDAO.insertLeaveRequest(leaveRequest);
			
			request.setAttribute("error", "Your Leave Request is Submitted");
			RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
			rd.forward(request, response);
			
			}
			else{
				request.setAttribute("error", "Your don't have this many leaves remaining");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
			
		}
		
		/*else if(flag.equals("carryOn")){
			SchedulerFactory sf = new StdSchedulerFactory();
			try{
				public static List<CronExpression> getCronExpressionList(int seconds, int minutes,int hours, int dayInMonth, Month month,DayOfWeek dayOfWeek) {
			    final String monthsWith30Days = Month.APR + "," + Month.JUN + ","
			                    + Month.SEP + "," + Month.NOV;
			    List<CronExpression> crons = new LinkedList<CronExpression>();

			    String timeString = String.format(("%s %s %s "), seconds, minutes,
			                    hours, 0, 0, 0);
			    String dateString = "%s %s %s";
			    String cron = null;

			    cron = timeString + String.format(dateString, dayInMonth, "*", "?");
			    crons.add(new CronExpression(cron));
			    if (dayInMonth > 28) {
			        String febCron = timeString + getFebruarLastDayDateString(dateString);
			        crons.add(new CronExpression(febCron));
			        if (dayInMonth == 31) {
			            String monthsWithThirtyDaysCron = timeString + String.format(dateString,
			                    "L", monthsWith30Days, "?");
			            crons.add(new CronExpression(monthsWithThirtyDaysCron));
			        }
			    }
			    return crons;
			}

			private static String getFebruarLastDayDateString(String initialCron)throws ParseException {
			    return String.format(initialCron, "L", Month.FEB, "?");
			}
			}
			catch(Exception e){
				
			}
		}*/
	}

}
