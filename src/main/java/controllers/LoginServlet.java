package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.adminDao;
import dao.userDao;
import dto.AdminResponseDTO;
import dto.UserResponseDTO;
import model.LoginBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginBean login = new LoginBean();
		login.setRole(request.getParameter("role"));
		
		if (login.getRole().equals("User")) {
			login.setEmail(request.getParameter("email"));
			login.setPassword(request.getParameter("password"));
			userDao dao = new userDao();
			UserResponseDTO res = dao.userLogin(login);
			
			if (login.getEmail().equals(res.getUseremail()) && login.getPassword().equals(res.getUserpassword())) {
				login.setRole(request.getParameter("role"));
				request.setAttribute("role", login.getRole());
				request.setAttribute("email", login.getEmail());
				request.getRequestDispatcher("MNU002.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Login Failed!!");
				request.getRequestDispatcher("LGN001.jsp").forward(request, response);
			}
			
		} else {
			
			login.setEmail(request.getParameter("email"));
			login.setPassword(request.getParameter("password"));
			adminDao dao = new adminDao();
			AdminResponseDTO res = dao.adminLogin(login);
			
			if (login.getEmail().equals(res.getAdminemail()) && login.getPassword().equals(res.getAdminpassword())) {
				login.setRole(request.getParameter("role"));
				request.setAttribute("role", login.getRole());
				request.setAttribute("email", login.getEmail());
				request.getRequestDispatcher("MNU001.jsp").forward(request, response);

			} else {
				request.setAttribute("error", "Login Failed!!");
				request.getRequestDispatcher("LGN001.jsp").forward(request, response);
			}

		}
	}

}
