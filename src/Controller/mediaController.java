package Controller;

import java.io.IOException;  
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import serviceLayer.validation;
import DAO.directoryDAO;
import DAO.registrationDAO;
import DAO.uploadDAO;
import Model.directory;
import Model.registration;
import Model.retrieveFile;
import Model.uploadFile;

/**
 * Servlet implementation class mediaController
 */
@WebServlet("/mediaController")
@MultipartConfig
public class mediaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mediaController() {
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
		
		if(flag.equals("fetchMediaEmployee")){
		String user_id=request.getParameter("user_id");	
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		retrieveFile rf=new retrieveFile();
		rf.setUser_id(user_id);
		
		registration registration=new registration();
		uploadDAO dao1= new uploadDAO();
		
		registration.setUser_id(user_id);
		registrationDAO registrationDAO=new registrationDAO();
		String hierarchy_array=registrationDAO.getHierarchyArray(registration);
		
		String [] manager_id=hierarchy_array.split("/");
		
		session.setAttribute("manager_id", manager_id);
		materiallist=dao1.getMaterialFileList(rf);
		request.setAttribute("materiallistEmployee", materiallist);
		RequestDispatcher rd=request.getRequestDispatcher("employee/employeeMain.jsp");
		rd.forward(request, response);
		
		}
		
		
		
		else if(flag.equals("fetchMediaEmployee1")){
			String user_id=request.getParameter("user_id");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(user_id);
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			uploadDAO dao1= new uploadDAO();
			registration.setUser_id(user_id);
			
			String ate_id=dao1.getAteId(rf);
			if(ate_id!=null && !ate_id.equals("N/A")){
				session.setAttribute("ATE", "ATE");
			}
			String hierarchy_array=registrationDAO.getHierarchyArray(registration);
			String [] manager_id=hierarchy_array.split("/");
			session.setAttribute("manager_id", manager_id);
			materiallist=dao1.getMaterialFileList(rf);
			
			request.setAttribute("materiallistEmployee", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
			rd.forward(request, response);
			//response.sendRedirect("employee/viewMediaEmployee.jsp");
			}
		else if(flag.equals("fetchMediaManager1")){
			String user_id=request.getParameter("user_id");
			retrieveFile rf=new retrieveFile();
			
			registrationDAO registrationDAO=new registrationDAO();
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			
			rf.setUser_id(user_id);
			registration registration=new registration();
			registration.setUser_id(user_id);
			
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			uploadDAO dao1= new uploadDAO();
			registration.setUser_id(user_id);
			
			String ate_id=dao1.getAteId(rf);
			if(ate_id!=null && !ate_id.equals("N/A")){
				//System.out.println("ATE");
				session.setAttribute("ATE", "ATE");
			}
			String hierarchy_array=registrationDAO.getHierarchyArray(registration);
			String [] manager_id=hierarchy_array.split("/");
			session.setAttribute("manager_id", manager_id);
			materiallist=dao1.getMaterialFileList1(rf);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
			rd.forward(request, response);

			}
		else if(flag.equals("findMedia")){
			String user_id=request.getParameter("manager_id");
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(user_id);
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getMaterialFileList(rf);
			request.setAttribute("materiallistEmployee", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("employee/viewMediaEmployee.jsp");
		}
		else if(flag.equals("findMedia1")){
			String user_id=request.getParameter("manager_id");
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(user_id);
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getMaterialFileList(rf);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
			rd.forward(request, response);
			//response.sendRedirect("manager/viewMediaManager.jsp");
		}
		else if(flag.equals("viewMedia")){
			
			String user_id=request.getParameter("manager_id");
			
			retrieveFile rf=new retrieveFile();
			registration registration=new registration();
			registration.setUser_id(user_id);
			
			registrationDAO registrationDAO=new registrationDAO();
			String get_new_permission=registrationDAO.getAllPermission(registration);
			
			if(get_new_permission!=null){
				String[] array=get_new_permission.split(" ");
				//System.out.println("No Permission 1" +array.length);
				rf.setUser_id(user_id);
				rf.setNew_permission(array[1]);
				rf.setNew_permission_given_by(array[2]);
				String name=registrationDAO.getManagerName(registration);
				session.setAttribute("Owner", name);
				List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
				
				rf.setUser_id(user_id);
				if(array[2].equals(session.getAttribute("userId"))){
					System.out.println("No Permission 2");
					uploadDAO dao1= new uploadDAO();
					materiallist=dao1.getMaterialFileList6(rf);
					request.setAttribute("materiallistManager", materiallist);
					RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
					rd.forward(request, response);
					
				//response.sendRedirect("manager/viewMediaManager.jsp");
				}else if(!array[2].equals(session.getAttribute("userId"))){
					System.out.println("testing...........");
					uploadDAO dao1= new uploadDAO();
					materiallist=dao1.getMaterialFileList6(rf);
					request.setAttribute("materiallistManager", materiallist);
					RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
					rd.forward(request, response);
				}
				else{
					rf.setUser_id(user_id);
					rf.setNew_permission_given_by(array[2]);
					System.out.println("No Permission 3"+array[1]);
					uploadDAO dao1= new uploadDAO();
					materiallist=dao1.getMaterialFileListMedia3(rf);
					request.setAttribute("materiallistManager", materiallist);
					RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
					rd.forward(request, response);
				}
			}
			else{
			
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			
			rf.setUser_id(user_id);
			if(user_id.equals(session.getAttribute("userId"))){
				uploadDAO dao1= new uploadDAO();
				materiallist=dao1.getMaterialFileList(rf);
				request.setAttribute("materiallistManager", materiallist);
				RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
				rd.forward(request, response);
				
			//response.sendRedirect("manager/viewMediaManager.jsp");
			}
			else{
				uploadDAO dao1= new uploadDAO();
				materiallist=dao1.getMaterialFileListMedia(rf);
				request.setAttribute("materiallistManager", materiallist);
				RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
				rd.forward(request, response);
			}
			}
		}
		else if(flag.equals("viewMediaEmployee")){
			String user_id=request.getParameter("manager_id");
			retrieveFile rf=new retrieveFile();
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			
			String get_new_permission=registrationDAO.getAllPermission(registration);

			if(get_new_permission!=null){
				String[] array=get_new_permission.split(" ");
				//System.out.println("No Permission 1");
				rf.setUser_id(user_id);
				rf.setNew_permission(array[1]);
				rf.setNew_permission_given_by(array[2]);
				String name=registrationDAO.getManagerName(registration);
				session.setAttribute("Owner", name);
				List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
				
				rf.setUser_id(user_id);
				if(array[2].equals(session.getAttribute("userId"))){
					//System.out.println("No Permission 2");
					uploadDAO dao1= new uploadDAO();
					materiallist=dao1.getMaterialFileList6(rf);
					request.setAttribute("materiallistEmployee", materiallist);
					RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
					rd.forward(request, response);
					
				//response.sendRedirect("manager/viewMediaManager.jsp");
				}
				else{
					//System.out.println("No Permission 3");
					uploadDAO dao1= new uploadDAO();
					materiallist=dao1.getMaterialFileListMedia3(rf);
					request.setAttribute("materiallistEmployee", materiallist);
					RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
					rd.forward(request, response);
				}
			}else{
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			
			rf.setUser_id(user_id);
			if(user_id.equals(session.getAttribute("userId"))){
				uploadDAO dao1= new uploadDAO();
				materiallist=dao1.getMaterialFileList(rf);
				request.setAttribute("materiallistEmployee", materiallist);
				RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
				rd.forward(request, response);
				
			//response.sendRedirect("manager/viewMediaManager.jsp");
			}
			else{
				uploadDAO dao1= new uploadDAO();
				materiallist=dao1.getMaterialFileListMedia(rf);
				request.setAttribute("materiallistEmployee", materiallist);
				RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
				rd.forward(request, response);
			}
		}
		}
		else if(flag.equals("findMedia2")){
			String user_id=request.getParameter("manager_id");
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String name=registrationDAO.getManagerName(registration);
			session.removeAttribute("Owner");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(user_id);
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getMaterialFileList2(rf);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
			rd.forward(request, response);
			//response.sendRedirect("manager/viewMediaManager.jsp");
		}
		
		else if(flag.equals("findMedia2Employee")){
			String user_id=request.getParameter("manager_id");
			registration registration=new registration();
			registration.setUser_id(user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String name=registrationDAO.getManagerName(registration);
			session.removeAttribute("Owner");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(user_id);
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getMaterialFileList2(rf);
			request.setAttribute("materiallistEmployee", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
			rd.forward(request, response);
			//response.sendRedirect("manager/viewMediaManager.jsp");
		}

		else if(flag.equals("fetchMediaForAdmin")){
			String user_id=request.getParameter("user_id");	
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(user_id);
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getMaterialFileList(rf);
			request.setAttribute("materiallistAdmin", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("admin/adminMain.jsp");
			rd.forward(request, response);
			//response.sendRedirect("admin/adminMain.jsp");
		}
		else if(flag.equals("fetchMediaManager")){
			String user_id=request.getParameter("user_id");	
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			List<directory> directoryList=new ArrayList<directory>();
			retrieveFile rf=new retrieveFile();
			rf.setUser_id(user_id);
			
			directory directory=new directory();
			directory.setUser_id(user_id);
			
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getMaterialFileList(rf);
			directoryDAO directoryDAO=new directoryDAO();
			directoryList=directoryDAO.getDirectoryList(directory);
			request.setAttribute("directoryList", directoryList);
			request.setAttribute("materiallistManager", materiallist);
			
			RequestDispatcher rd=request.getRequestDispatcher("manager/managerMain.jsp");
			rd.forward(request, response);
			//response.sendRedirect("manager/managerMain.jsp");
		}
		else if(flag.equals("allPublic")){
			session.removeAttribute("Owner");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getAllPublicMedia(rf);
			request.setAttribute("materiallistEmployee", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("employee/viewMediaEmployee.jsp");
		}
		else if(flag.equals("allAteFiles")){
			session.removeAttribute("Owner");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			String user_id=(String)session.getAttribute("userId");
			rf.setAte_id(user_id);
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.allAteFiles(rf);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("employee/viewMediaEmployee.jsp");
		}
		else if(flag.equals("allAteFilesEmployee")){
			session.removeAttribute("Owner");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			String user_id=(String)session.getAttribute("userId");
			rf.setAte_id(user_id);
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.allAteFiles(rf);
			request.setAttribute("materiallistEmployee", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("employee/viewMediaEmployee.jsp");
		}
		else if(flag.equals("allPublicManager")){
			session.removeAttribute("Owner");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			retrieveFile rf=new retrieveFile();
			
			uploadDAO dao1= new uploadDAO();
			materiallist=dao1.getAllPublicMedia(rf);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("employee/viewMediaEmployee.jsp");
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String flag=request.getParameter("flag");
		if(flag.equals("uploadMediaManager")){

			String director_name=request.getParameter("director_name");
			String description=request.getParameter("description");
			String userId=(String)session.getAttribute("userId");
			Part filePart = request.getPart("fileName");
			String file_name=filePart.getSubmittedFileName();
			String file_type=filePart.getContentType();
			InputStream inputStream = null;
			if (filePart != null) {
	            inputStream = filePart.getInputStream();
			}
			directory directory=new directory();
			directory.setDirectory_name(director_name);
			directoryDAO directoryDAO=new directoryDAO();
			String ate=directoryDAO.getAte(directory);
			String directory_user_id=directoryDAO.getUser_id(directory);
			uploadFile uf=new uploadFile();
			retrieveFile rf=new retrieveFile();
			registration registration=new registration();
			registration.setUser_id(directory_user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String manager_hierarchy=registrationDAO.getManagerHierarchy(registration);
			
			uf.setManager_hierarchy(manager_hierarchy);
			uf.setFile(inputStream);
			uf.setFile_name(file_name);
			uf.setFile_type(file_type);
			uf.setDescription(description);
			uf.setUser_id(directory_user_id);
			uf.setDirector_name(director_name);
			
			/*directory.setDirectory_name(directory_name);
			String ate=registrationDAO.getAte(directory);*/
			
			rf.setUser_id(directory_user_id);
			validation validation=new validation();
			String validatefile=validation.fileupload(uf);
			if(validatefile.equals("Please Upload File") || validatefile.equals("Please enter file Description!")){
				request.setAttribute("error", validatefile);
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
			else{
			uploadDAO dao= new uploadDAO();
			String access_permission=dao.getAccessPermission(uf);
			uf.setAccess_permission(access_permission);
			uf.setUploaded_by(userId);
			dao.fileupload1(uf,ate);
			
			//String user_id=request.getParameter("user_id");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			rf.setUser_id(userId);
			registration.setUser_id(userId);
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			uploadDAO dao1= new uploadDAO();
			registration.setUser_id(userId);
			String ate_id=dao1.getAteId(rf);
			if(ate_id!=null && !ate_id.equals("N/A")){
				session.setAttribute("ATE", "ATE");
			}
			String hierarchy_array=registrationDAO.getHierarchyArray(registration);
			String [] manager_id=hierarchy_array.split("/");
			session.setAttribute("manager_id", manager_id);
			materiallist=dao1.getMaterialFileList1(rf);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/viewMediaManager.jsp");
			rd.forward(request, response);
			//response.sendRedirect("manager/viewMediaManager.jsp");
			}
			
		}
		else if(flag.equals("uploadMediaEmployee")){
			String director_name=request.getParameter("director_name");
			String description=request.getParameter("description");
			String userId=(String)session.getAttribute("userId");
			Part filePart = request.getPart("fileName");
			String file_name=filePart.getSubmittedFileName();
			String file_type=filePart.getContentType();
			InputStream inputStream = null;
			if (filePart != null) {
	            inputStream = filePart.getInputStream();
			}
			directory directory=new directory();
			directory.setDirectory_name(director_name);
			directoryDAO directoryDAO=new directoryDAO();
			String ate=directoryDAO.getAte(directory);
			String directory_user_id=directoryDAO.getUser_id(directory);
			uploadFile uf=new uploadFile();
			retrieveFile rf=new retrieveFile();
			registration registration=new registration();
			registration.setUser_id(directory_user_id);
			registrationDAO registrationDAO=new registrationDAO();
			String manager_hierarchy=registrationDAO.getManagerHierarchy(registration);
			
			uf.setManager_hierarchy(manager_hierarchy);
			uf.setFile(inputStream);
			uf.setFile_name(file_name);
			uf.setFile_type(file_type);
			uf.setDescription(description);
			uf.setUser_id(directory_user_id);
			uf.setDirector_name(director_name);
			
			
			rf.setUser_id(directory_user_id);
			validation validation=new validation();
			String validatefile=validation.fileupload(uf);
			if(validatefile.equals("Please Upload File") || validatefile.equals("Please enter file Description!")){
				request.setAttribute("error", validatefile);
				RequestDispatcher rd=request.getRequestDispatcher("employee/allContentEmployee.jsp");
				rd.forward(request, response);
			}
			else{
			uploadDAO dao= new uploadDAO();
			String access_permission=dao.getAccessPermission(uf);
			uf.setAccess_permission(access_permission);
			uf.setUploaded_by(userId);
			
			dao.fileupload1(uf,ate);
			
			//String user_id=request.getParameter("user_id");
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			rf.setUser_id(userId);
			registration.setUser_id(userId);
			String name=registrationDAO.getManagerName(registration);
			session.setAttribute("Owner", name);
			uploadDAO dao1= new uploadDAO();
			registration.setUser_id(userId);
			String ate_id=dao1.getAteId(rf);
			if(ate_id!=null && !ate_id.equals("N/A")){
				session.setAttribute("ATE", "ATE");
			}
			String hierarchy_array=registrationDAO.getHierarchyArray(registration);
			String [] manager_id=hierarchy_array.split("/");
			session.setAttribute("manager_id", manager_id);
			materiallist=dao1.getMaterialFileList1(rf);
			request.setAttribute("materiallistEmployee", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("employee/viewMediaEmployee.jsp");
			rd.forward(request, response);
			//response.sendRedirect("manager/viewMediaManager.jsp");
			}
			
		}
	}
}


