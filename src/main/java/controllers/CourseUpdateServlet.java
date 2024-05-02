package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.courseDao;
import dto.CourseRequestDTO;
import dto.CourseResponseDTO;
import model.CourseBean;

/**
 * Servlet implementation class CourseUpdateServlet
 */
@WebServlet("/CourseUpdateServlet")
public class CourseUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseRequestDTO dto=new CourseRequestDTO();
		dto.setId(Integer.parseInt(request.getParameter("id")));
		courseDao dao=new courseDao();
		CourseResponseDTO res=dao.selectOneCourse(dto);
		request.setAttribute("res", res);
		request.getRequestDispatcher("updateCourse.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseBean course=new CourseBean();
	
		course.setCourseid(request.getParameter("courseid"));
		course.setCoursename(request.getParameter("coursename"));
		
		
		if(course.getCourseid().equals("")||course.getCoursename().equals("")) {
			request.setAttribute("blank", "Filed Can't be blank!!");
			request.setAttribute("course", course);
			request.getRequestDispatcher("updateCourse.jsp").forward(request, response);
		}else {
			courseDao dao=new courseDao();
			CourseRequestDTO dto=new CourseRequestDTO();
		
			dto.setCourseid(course.getCourseid());
			dto.setCoursename(course.getCoursename());
		
			int i=dao.updateCourse(dto);
			if(i>0) {
				response.sendRedirect("DisplayCourseServlet");
			}else {
				request.setAttribute("error", "Update Failed");
				request.getRequestDispatcher("updateCourse.jsp").forward(request, response);
			}
		}
	}

}
