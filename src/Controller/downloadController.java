package Controller;

import java.io.IOException; 
import java.io.InputStream;
import java.sql.Blob;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.uploadDAO;
import Model.retrieveFile;

/**
 * Servlet implementation class downloadController
 */
@WebServlet("/downloadController")
public class downloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		if(flag.equals("download")){
			
			Blob image = null;
			byte[ ] imgData = null ;
			try {
			retrieveFile rff = uploadDAO.getMaterial(Integer.parseInt(request.getParameter("materialid")));	
			image = rff.getFile();
			imgData = image.getBytes(1,(int)image.length());
			response.setContentType(rff.getFile_type());
			
			InputStream is = image.getBinaryStream();
			float bytesRead = is.read(imgData);
			response.getOutputStream().write(imgData, 0, (int) bytesRead);
			
			} catch (Exception e) {
			System.out.println("Unable To Display image");
			System.out.println("Image Display Error=" + e.getMessage());
			return;

			} 
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
