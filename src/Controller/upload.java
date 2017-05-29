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
 * Servlet implementation class upload
 */
@WebServlet("/upload")
@MultipartConfig

public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
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
		if(flag.equals("upload")){
		String description=request.getParameter("description");
		String userId=(String)session.getAttribute("userId");
		Part filePart = request.getPart("fileName");
		String file_name=filePart.getSubmittedFileName();
		String file_type=filePart.getContentType();
		InputStream inputStream = null;
		
		if (filePart != null) {
            inputStream = filePart.getInputStream();
		}
		
		
		uploadFile uf=new uploadFile();
		retrieveFile rf=new retrieveFile();
		uf.setFile(inputStream);
		uf.setFile_name(file_name);
		uf.setFile_type(file_type);
		uf.setDescription(description);
		uf.setUser_id(userId);
		rf.setUser_id(userId);
		
		
		
		validation validation=new validation(); 
		String validatefile=validation.fileupload(uf);
		if(validatefile.equals("Please Upload File") || validatefile.equals("Please enter file Description!")){
			request.setAttribute("error", validatefile);
			RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
		}
		else{
		uploadDAO dao= new uploadDAO();
		
		
		dao.fileupload(uf);
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		materiallist=dao.getMaterialFileList(rf);
		session.setAttribute("materiallist", materiallist);
		response.sendRedirect("main.jsp");
		}
		}
		else if(flag.equals("uploadMediaAdmin")){
			String description=request.getParameter("description");
			String userId=(String)session.getAttribute("userId");
			Part filePart = request.getPart("fileName");
			String file_name=filePart.getSubmittedFileName();
			String file_type=filePart.getContentType();
			InputStream inputStream = null;
			
			if (filePart != null) {
	            inputStream = filePart.getInputStream();
			}
			uploadFile uf=new uploadFile();
			retrieveFile rf=new retrieveFile();
			uf.setFile(inputStream);
			uf.setFile_name(file_name);
			uf.setFile_type(file_type);
			uf.setDescription(description);
			uf.setUser_id(userId);
			rf.setUser_id(userId);
			
			
			
			validation validation=new validation(); 
			String validatefile=validation.fileupload(uf);
			if(validatefile.equals("Please Upload File") || validatefile.equals("Please enter file Description!")){
				request.setAttribute("error", validatefile);
				RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
				rd.forward(request, response);
			}
			else{
			uploadDAO dao= new uploadDAO();
			
			uf.setUploaded_by(userId);
			
			dao.fileupload(uf);
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			materiallist=dao.getMaterialFileList(rf);
			request.setAttribute("materiallistAdmin", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("admin/adminMain.jsp");
			rd.forward(request, response);
			//response.sendRedirect("admin/adminMain.jsp");
			}
		}
		else if(flag.equals("uploadMediaEmployee")){
			String description=request.getParameter("description");
			String userId=(String)session.getAttribute("userId");
			Part filePart = request.getPart("fileName");
			String file_name=filePart.getSubmittedFileName();
			String file_type=filePart.getContentType();
			InputStream inputStream = null;
			
			if (filePart != null) {
	            inputStream = filePart.getInputStream();
			}
			uploadFile uf=new uploadFile();
			retrieveFile rf=new retrieveFile();
			
			registration registration=new registration();
			registration.setUser_id(userId);
			registrationDAO registrationDAO=new registrationDAO();
			String manager_hierarchy=registrationDAO.getManagerHierarchy(registration);
			uf.setManager_hierarchy(manager_hierarchy);
			uf.setFile(inputStream);
			uf.setFile_name(file_name);
			uf.setFile_type(file_type);
			uf.setDescription(description);
			uf.setUser_id(userId);
			
			rf.setUser_id(userId);
			
			
			
			validation validation=new validation(); 
			String validatefile=validation.fileupload(uf);
			if(validatefile.equals("Please Upload File") || validatefile.equals("Please enter file Description!")){
				request.setAttribute("error", validatefile);
				RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
				rd.forward(request, response);
			}
			else{
			uploadDAO dao= new uploadDAO();
			String access_permission=dao.getAccessPermission(uf);
			uf.setAccess_permission(access_permission);
			uf.setUploaded_by(userId);
			dao.fileupload(uf);
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			materiallist=dao.getMaterialFileList(rf);
			List<directory> directoryList=new ArrayList<directory>();
			directory directory=new directory();
			directory.setUser_id(userId);
			directoryDAO directoryDAO=new directoryDAO();
			directoryList=directoryDAO.getDirectoryList(directory);
			request.setAttribute("directoryList", directoryList);
			request.setAttribute("materiallistEmployee", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("employee/employeeMain.jsp");
			rd.forward(request, response);
			
			//response.sendRedirect("employee/employeeMain.jsp");
			}
		}  

		else if(flag.equals("uploadMediaManager")){
			String director_name=request.getParameter("director_name");
			if(director_name.equals("Select Directory")){
				request.setAttribute("error", "Please create a Directory, before uploading files");
				RequestDispatcher rd=request.getRequestDispatcher("manager/allContentManager.jsp");
				rd.forward(request, response);
			}
			else{
			String description=request.getParameter("description");
			String userId=(String)session.getAttribute("userId");
			Part filePart = request.getPart("fileName");
			String file_name=filePart.getSubmittedFileName();
			String file_type=filePart.getContentType();
			InputStream inputStream = null;
			if (filePart != null) {
	            inputStream = filePart.getInputStream();
			}
			uploadFile uf=new uploadFile();
			retrieveFile rf=new retrieveFile();
			registration registration=new registration();
			registration.setUser_id(userId);
			registrationDAO registrationDAO=new registrationDAO();
			String manager_hierarchy=registrationDAO.getManagerHierarchy(registration);
			
			uf.setManager_hierarchy(manager_hierarchy);
			uf.setFile(inputStream);
			uf.setFile_name(file_name);
			uf.setFile_type(file_type);
			uf.setDescription(description);
			uf.setUser_id(userId);
			uf.setDirector_name(director_name);
			
			directory directory=new directory();
			directory.setDirectory_name(director_name);
			directoryDAO directoryDAO=new directoryDAO();
			String ate=directoryDAO.getAte(directory);
			System.out.println("fetching ate" +ate);
			rf.setUser_id(userId);
			validation validation=new validation();
			String validatefile=validation.fileupload(uf);
			if(validatefile.equals("Please Upload File") || validatefile.equals("Please enter file Description!")){
				request.setAttribute("error", validatefile);
				RequestDispatcher rd=request.getRequestDispatcher("manager/managerMain.jsp");
				rd.forward(request, response);
			}
			else{
			uploadDAO dao= new uploadDAO();
			String access_permission=dao.getAccessPermission(uf);
			uf.setAccess_permission(access_permission);
			uf.setUploaded_by(userId);
			dao.fileupload1(uf,ate);
			
			
			
			List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
			materiallist=dao.getMaterialFileList(rf);
			
			
			List<directory> directoryList=new ArrayList<directory>();
			
			directory.setUser_id(userId);
			
			directoryList=directoryDAO.getDirectoryList(directory);
			request.setAttribute("directoryList", directoryList);
			request.setAttribute("materiallistManager", materiallist);
			RequestDispatcher rd=request.getRequestDispatcher("manager/managerMain.jsp");
			rd.forward(request, response);
			//response.sendRedirect("manager/managerMain.jsp");
			}
			}
		}  
	}
}
