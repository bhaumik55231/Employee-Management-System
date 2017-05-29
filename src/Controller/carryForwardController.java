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

import DAO.leaveRequestDAO;

/**
 * Servlet implementation class carryForwardController
 */
@WebServlet("/carryForwardController")
public class carryForwardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public carryForwardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		if(flag.equals("carryForward")){
			Timer timer1 = new Timer();
		    Calendar date = Calendar.getInstance();
		    date.set(Calendar.DAY_OF_MONTH, 1);
		    date.set(Calendar.HOUR_OF_DAY, 0);
		    date.set(Calendar.MINUTE, 0);
		    date.set(Calendar.SECOND, 0);
		    date.set(Calendar.MILLISECOND, 0);
		    System.out.println("testing");
		    // Schedule to run on 1st of each month 
		    timer1.schedule(
		      new leaveRequestDAO(),
		      date.getTime(),
		      1000 * 60 * 60 * 24 * 7
		    );
		    request.setAttribute("error", "Salary will be Generated and leaves will be carry Forwarded On First of Each Month");
			RequestDispatcher rd=request.getRequestDispatcher("admin/allContentAdmin.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
