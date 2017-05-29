package Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.uploadDAO;
import Model.retrieveFile;

/**
 * Servlet implementation class viewController
 */
@WebServlet("/viewController")
public class viewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Blob image = null;
		byte[ ] imgData = null ;
		try {
		retrieveFile rff = uploadDAO.getMaterial(Integer.parseInt(request.getParameter("materialid")));	
		image = rff.getFile();
		imgData = image.getBytes(1,(int)image.length());
		response.setContentType(rff.getFile_type());
		OutputStream o = response.getOutputStream();
		o.write(imgData);
		o.flush();
		o.close();
		} catch (Exception e) {
		System.out.println("Unable To Display image");
		System.out.println("Image Display Error=" + e.getMessage());
		return;

		} 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
