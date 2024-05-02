package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class CourseRegisterServlet
 */
@WebServlet("/CourseRegisterServlet")
public class CourseRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseid = "";
		CourseBean cb = new CourseBean();
		courseDao dao = new courseDao();
        int i = dao.rowCountCourse();
        if (i == 0) {
        	courseid = "CR-001";
        } else if (i < 9) {
            courseid = "CR-00" + (i + 1);
        } else if (i < 99) {
            courseid = "CR-0" + (i + 1);
        } else {
            courseid = "CR-" + (i + 1);
        }
        
        cb.setCourseid(courseid);       

        request.setAttribute("course", cb);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BUD003.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    CourseBean cb = new CourseBean();
	    courseDao dao1= new  courseDao();
	    cb.setCourseid(request.getParameter("courseid"));
	    cb.setCoursename(request.getParameter("coursename"));
	    
	    // Check for blank fields
	    if (cb.getCourseid().isEmpty() || cb.getCoursename().isEmpty()) {
	        request.setAttribute("blank", "Field Can't be blank!!");
	        request.setAttribute("course", cb);
	        request.getRequestDispatcher("BUD003.jsp").forward(request, response);
//	        return;
	    }
	    
	    // Check for duplicate course name
	 
	    CourseRequestDTO req = new CourseRequestDTO();
	   
	    req.setCoursename(cb.getCoursename());
	    CourseResponseDTO existingCourse = dao1.selectNameCourse(req);
	    if (existingCourse != null) {
	        request.setAttribute("error", "Course name already exists!!");
	        request.setAttribute("course", cb);
	        request.getRequestDispatcher("BUD003.jsp").forward(request, response);
//	        return;
	    }
	    
	    // Proceed with course insertion
	    courseDao dao = new courseDao();
	    CourseRequestDTO courseReq = new CourseRequestDTO();
	    courseReq.setCourseid(cb.getCourseid());
	    courseReq.setCoursename(cb.getCoursename());
	    int result = dao.insertCourse(courseReq);
	    
	    if (result > 0) {
	        request.setAttribute("success", "Successfully Course Registration");
	        request.getRequestDispatcher("success1.jsp").forward(request, response);
	    } else {
	        String errorMessage = "Register Failed";
	        request.setAttribute("error", errorMessage);
	        request.setAttribute("course", cb);
	        request.getRequestDispatcher("BUD003.jsp").forward(request, response);
	    }
	
		}
	
}
