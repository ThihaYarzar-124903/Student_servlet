package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.courseDao;
import dao.studentDao;
import dto.CourseResponseDTO;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;



/**
 * Servlet implementation class SeeMoreServlet
 */
@WebServlet("/SeeMoreServlet")
public class SeeMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeMoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		courseDao sDao = new courseDao();
	    ArrayList<CourseResponseDTO> list1 = sDao.selectAllCourse();
	    request.setAttribute("list1", list1);

	    StudentRequestDTO dto=new StudentRequestDTO();
	    dto.setName(request.getParameter("name"));
	    studentDao dao1=new studentDao();
	    StudentResponseDTO res=dao1.selectOneStudent(dto);
	    List<String> stuCourse = null;
	    
	    if (res != null && res.getAttend() != null)
	        stuCourse = List.of(res.getAttend().split(","));

	    request.setAttribute("stuCourse", stuCourse);
	    request.setAttribute("res", res);

	
	
	    System.out.println(res);
	    request.getRequestDispatcher("STU002.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
