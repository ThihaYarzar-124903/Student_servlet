package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.userDao;
import dto.UserRequestDTO;
import dto.UserResponseDTO;
import model.UserBean;


/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRequestDTO dto=new UserRequestDTO();
		dto.setId(Integer.parseInt(request.getParameter("id")));
		userDao dao=new userDao();
		UserResponseDTO res=dao.selectOneUser(dto);
		request.setAttribute("res", res);
		request.getRequestDispatcher("USR002.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UserBean user=new UserBean();
	user.setId(Integer.parseInt(request.getParameter("id")));
	user.setUserid(request.getParameter("userid"));
	user.setUseremail(request.getParameter("useremail"));
	user.setUserpassword(request.getParameter("userpassword"));
	user.setUserconpassword(request.getParameter("userconpassword"));
	
	if(user.getUserid().equals("")||user.getUseremail().equals("")||user.getUserpassword().equals("")||user.getUserconpassword().equals("")) {
		request.setAttribute("error", "Field can't be blank");
		request.getRequestDispatcher("USR002.jsp").forward(request, response);
	}else {
		userDao dao=new userDao();
		UserRequestDTO dto=new UserRequestDTO();
		dto.setId(user.getId());
		dto.setUserid(user.getUserid());
		dto.setUseremail(user.getUseremail());
		dto.setUserpassword(user.getUserpassword());
		dto.setUserconpassword(user.getUserconpassword());
		int i=dao.updateUser(dto);
		if(i>0) {
			response.sendRedirect("DisplayUserServlet");
		}else {
			request.setAttribute("error", "Update Failed");
			request.getRequestDispatcher("USR002.jsp").forward(request, response);
		}
	}
	
	}

}
