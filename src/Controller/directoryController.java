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

import serviceLayer.validation;
import DAO.directoryDAO;
import DAO.employeeDAO;
import DAO.registrationDAO;
import DAO.uploadDAO;
import Model.directory;
import Model.registration;
import Model.retrieveFile;


/**
 * Servlet implementation class directoryController
 */
@WebServlet("/directoryController")
public class directoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public directoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		HttpSession session=request.getSession();
		if(flag.equals("getPublicDirectories")){
			List<directory> all_public_directories=new ArrayList<directory>();
			directory directory=new directory();
			directory.setUser_id((String)session.getAttribute("userId"));
			directoryDAO directoryDAO=new directoryDAO();
			all_public_directories=directoryDAO.getAllPublicDirectories(directory);
			if(all_public_directories.size()>0){
			request.setAttribute("all_public_directories", all_public_directories);
			RequestDispatcher rd=request.getRequestDispatcher("manager/updateDirectoryAccess.jsp");
			rd.forward(request, response);
			}
			else{
				request.setAttribute("error", "you don't have any public directory");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
		}
		else if(flag.equals("fetchDirectoryData")){
			int directory_id=Integer.parseInt(request.getParameter("directory_id"));
			String directory_name=request.getParameter("directory_name");
			List<directory> particular_directory=new ArrayList<directory>();
			directory directory=new directory();
			directory.setDirectory_id(directory_id);
			directory.setDirectory_name(directory_name);
			directoryDAO directoryDAO=new directoryDAO();
			String permission=directoryDAO.getParticularPermission(directory);
			particular_directory=directoryDAO.particular_directory(directory);
			request.setAttribute("permission", permission);
			request.setAttribute("particular_directory", particular_directory);
			RequestDispatcher rd=request.getRequestDispatcher("manager/DirectoryAccess.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("anotherManagerDirectory")){
			String user_id=request.getParameter("user_id");
			List<directory> materiallist = new ArrayList<directory>();
			directory directory=new directory();
			
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			employeeDAO employeeDAO=new employeeDAO();
			String immediate_manager_id=employeeDAO.getImmediateManager1(registration);
			
			directory.setUser_id(immediate_manager_id);
			
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			uploadDAO dao1= new uploadDAO();
			registration.setUser_id(user_id);
			
			
			String hierarchy_array=registrationDAO.getHierarchyArray(registration);
			String [] manager_id=hierarchy_array.split("/");
			session.setAttribute("manager_id", manager_id);
			materiallist=dao1.getMaterialFileList5(directory);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/anotherManagerDirectory.jsp");
			rd.forward(request, response);
			
		}
		else if(flag.equals("viewMedia")){
			String user_id=request.getParameter("user_id");
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			List<directory> materiallist = new ArrayList<directory>();
			directory directory=new directory();
			directory.setUser_id(user_id);
			
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getMaterialFileListMedia_change(directory);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/anotherManagerDirectory.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("changePermissionOfManager")){
			int directory_id=Integer.parseInt(request.getParameter("directory_id"));
			List<directory> directoryList = new ArrayList<directory>();
			directory directory= new directory();
			directory.setDirectory_id(directory_id);
			directoryDAO directoryDAO=new directoryDAO();
			String permission=directoryDAO.getPermission(directory);
			
			directoryList=directoryDAO.getDirectory(directory);
			request.setAttribute("permission", permission);
			request.setAttribute("directoryList", directoryList);
			RequestDispatcher rd=request.getRequestDispatcher("manager/changePermissionOfManager.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String flag=request.getParameter("flag");
		if(flag.equals("create")){
			String access=request.getParameter("access");
			String user_id=request.getParameter("user_id");
			String name=request.getParameter("name");
			String description=request.getParameter("description");
			
			directory directory=new directory();
			directory.setDirectory_access_permissions(access);
			directory.setDirectory_name(name);
			directory.setDirectory_description(description);
			directory.setUser_id(user_id);
			validation val = new validation();
			String validationMandatory=val.mandatoryDirectory(directory);
			if(validationMandatory.equals("Directory Name is Mandatory!") || validationMandatory.equals("Directory Description is Mandatory!")){
				request.setAttribute("error", validationMandatory);
				RequestDispatcher rd=request.getRequestDispatcher("manager/createDirectory.jsp");
				rd.forward(request, response);
			}
			else{
				directoryDAO directoryDAO=new directoryDAO();
				String validateDirectoryName=directoryDAO.validate(directory);
				if(validateDirectoryName.equals("Directory with same name is already present")){
					System.out.println("checking");
					request.setAttribute("error", validateDirectoryName);
					RequestDispatcher rd=request.getRequestDispatcher("manager/createDirectory.jsp");
					rd.forward(request, response);
				}
				else{
				String manager_hierarchy=directoryDAO.getHierarchy(directory);
				directory.setManager_hierarchy(manager_hierarchy);	
				directoryDAO.createDirectory(directory);
				request.setAttribute("error", "Directory Created");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
				}
			}
		}
		else if(flag.equals("updateDirectoryPermission")){
			int directory_id=Integer.parseInt(request.getParameter("directory_id"));
			String directory_name=request.getParameter("directory_name");
			String permission=request.getParameter("permission");
			
			directory directory=new directory();
			directory.setDirectory_id(directory_id);
			directory.setDirectory_name(directory_name);
			directory.setDirectory_access_permissions(permission);
			directoryDAO directoryDAO=new directoryDAO();
			directoryDAO.updateAccessPermission(directory);
			request.setAttribute("error", "Directory Permission Updated");
			RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
			rd.forward(request, response);
		}
		else if(flag.equals("updateDirectoryPermissionOfSuperManager")){
			int directory_id=Integer.parseInt(request.getParameter("directory_id"));
			String directory_name=request.getParameter("directory_name");
			String user_id=(String)session.getAttribute("userId");
			String permission=request.getParameter("permission");
			directory directory=new directory();
			directory.setDirectory_id(directory_id);
			directory.setNew_permission_given_by(user_id);
			directory.setNew_permission(permission);
			directory.setDirectory_name(directory_name);
			directoryDAO directoryDAO=new directoryDAO();
			directoryDAO.updateAccessPermission1(directory);
			request.setAttribute("error", "Directory Permission of Super Manager is Updated");
			RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
			rd.forward(request, response);
		}
		
	}

}
