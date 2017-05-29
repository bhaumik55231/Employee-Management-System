package filter;
import java.io.IOException; 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@WebFilter("/*")
public class sessionFilter implements Filter {
	
	public sessionFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session =(((HttpServletRequest) request).getSession());
		String uri = ((HttpServletRequest)request).getRequestURI();
		if(uri.contains("/css") || uri.contains("/js") && !uri.contains(".jsp") || uri.contains("/img") || uri.contains("/fonts") || uri.contains("assignManagerController") || uri.contains("downloadController") || uri.contains("carryForwardController") || uri.contains("directoryController") || uri.contains("employeeController") || uri.contains("leaveRequestController") || uri.contains("login") || uri.contains("logoutController") || uri.contains("mediaController") || uri.contains("updateProfileController") || uri.contains("upload") || uri.contains("viewController") || uri.contains("employee/signin.jsp") || uri.contains("employee/register.jsp") || uri.contains("employee/registered.jsp"))
		{
			//System.out.println("filter - 1");
			chain.doFilter(request,response);	
		}

		else if(session.getAttribute("userId") != null)
		{
			String h = (String)session.getAttribute("type");
			if(h!=null && h.equals("admin") && uri.contains("/admin"))
			{
				//System.out.println("filter - 2");
				chain.doFilter(request,response);
			}
			else if(h!=null && h.equals("employee") && uri.contains("/employee"))
			{
				//System.out.println("filter - 3");
				chain.doFilter(request, response);
			}
			else if(h!=null && h.equals("manager") && uri.contains("/manager"))
			{
				//System.out.println("filter - 4");
				chain.doFilter(request, response);	
			}
			else
			{
				if(session.getAttribute("type").equals("admin")){
					RequestDispatcher rd = request.getRequestDispatcher("/admin/allContentAdmin.jsp");
					rd.forward(request,response);
				}
				else if(session.getAttribute("type").equals("employee")){
					RequestDispatcher rd = request.getRequestDispatcher("/employee/allContentEmployee.jsp");
					rd.forward(request,response);
				} 
				else{
					RequestDispatcher rd = request.getRequestDispatcher("/manager/allContentManager.jsp");
					rd.forward(request,response);
				}
			}
		}
		else
		{
			//System.out.println("Filter - 5");

			RequestDispatcher rd = request.getRequestDispatcher("/employee/signin.jsp");
			rd.forward(request,response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
