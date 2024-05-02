package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.userDao;
import dto.UserResponseDTO;


/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String id= request.getParameter("id");
		
		//System.out.println("from param id->"+ id);
		
		UserResponseDTO dto=new UserResponseDTO();
		dto.setId(Integer.parseInt(id));
		
		//System.out.println(dto.getId());
        userDao dao=new userDao();
        int i=dao.userDelete(dto);
       
        if(i>0) {
    	   //System.out.println(i+"i>0");
    	   response.sendRedirect("DisplayUserServlet");
       }else {
    	   //System.out.println(1+"i<0");
    	   request.getRequestDispatcher("MNU001.jsp").forward(request, response);
       }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
