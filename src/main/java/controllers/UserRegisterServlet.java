package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = "";
		UserBean user = new UserBean();
		userDao dao = new userDao();
		int i = dao.rowCountUser();
		if (i == 0) {
			userid = "USR-001";
		} else if (i < 9) {
			userid = "USR-00" + (i + 1);
		} else if (i < 99) {
			userid = "USR-0" + (i + 1);
		} else {
			userid = "USR-" + (i + 1);
		}
		
		 user.setUserid(userid);

		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("USR001.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = new UserBean();

		user.setUserid(request.getParameter("userid"));
		user.setUseremail(request.getParameter("useremail"));
		user.setUserpassword(request.getParameter("userpassword"));
		user.setUserconpassword(request.getParameter("userconpassword"));
		if (user.getUserid().equals("") || user.getUseremail().equals("") || user.getUserpassword().equals("")
				|| user.getUserconpassword().equals("")) {
			request.setAttribute("blank", "Field Can't be blank!!");
			request.setAttribute("user", user);
			request.getRequestDispatcher("USR001.jsp").forward(request, response);
		} else if (!user.getUserpassword().equals(user.getUserconpassword())) {
			request.setAttribute("passcheck", "Check Your Password!!");
			request.getRequestDispatcher("USR001.jsp").forward(request, response);
		}

		else {
			userDao dao = new userDao();

			// Check if the email already exists
			UserRequestDTO req = new UserRequestDTO();
			req.setUseremail(user.getUseremail());
			UserResponseDTO existingUser = dao.selectEmailUser(req);
			if (existingUser != null) {
				request.setAttribute("error", "Email already exists!!");
				request.getRequestDispatcher("USR001.jsp").forward(request, response);
			}

			else {

				req.setUserid(user.getUserid());
				req.setUseremail(user.getUseremail());
				req.setUserpassword(user.getUserpassword());
				req.setUserconpassword(user.getUserconpassword());
				int a = dao.insertUser(req);
				
				if (a > 0) {
					request.setAttribute("success", "Successful Register");
					request.getRequestDispatcher("LGN001.jsp").forward(request, response);

				}

				else {
					request.setAttribute("error", "Insert Failed!!");
					request.setAttribute("user", user);
					request.getRequestDispatcher("USR001.jsp").forward(request, response);
				}
			}
		}
	}

}
