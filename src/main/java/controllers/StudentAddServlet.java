package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import dao.courseDao;
import dao.studentDao;
import dto.CourseResponseDTO;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import model.StudentBean;


/**
 * Servlet implementation class StudentAddServlet
 */

@WebServlet("/StudentAddServlet")
@MultipartConfig
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String studentid = "";
		StudentBean student=new StudentBean();
        studentDao dao = new studentDao();
        int i = dao.rowCountStudent();
        if (i == 0) {
        	studentid = "STU-001";
        } else if (i < 9) {
            studentid = "STU-00" + (i + 1);
        } else if (i < 99) {
            studentid = "STU-0" + (i + 1);
        } else {
            studentid = "STU-" + (i + 1);
        }
        student.setId(studentid);
        request.setAttribute("student", student);
   
    	courseDao dao1=new courseDao();
		ArrayList<CourseResponseDTO> list1=dao1.selectAllCourse();
		if(list1.size()==0) {
			request.setAttribute("msg", "No data");
		}else {
			request.setAttribute("list1", list1);
			
			
		}
		
		request.getRequestDispatcher("STU001.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentBean student=new StudentBean();
	
		student.setId(request.getParameter("id"));
		student.setName(request.getParameter("name"));
		student.setDob(request.getParameter("dob"));
		student.setGender(request.getParameter("gender"));
		student.setPhone(request.getParameter("phone"));
		student.setEdu(request.getParameter("edu"));
		student.setAttend(request.getParameterValues("attend"));
		
		if(student.getId().equals("")||student.getName().equals("")||student.getDob().equals("")||
				student.getGender().equals("")||student.getPhone().equals("")||student.getEdu().equals(""))
			{
			request.setAttribute("error", "Field Can't be blank!!");
			request.setAttribute("student", student);
			request.getRequestDispatcher("STU001.jsp").forward(request, response);
		}
		
		StudentRequestDTO dto1 = new StudentRequestDTO();
		studentDao dao1 = new studentDao();
	    dto1.setPhone(student.getPhone());
	    StudentResponseDTO existingStudent = dao1.selectPhone(dto1);
	    if (existingStudent != null) {
	        request.setAttribute("error", "Phone number already exists!!");
	        request.setAttribute("student", student);
	        request.getRequestDispatcher("STU001.jsp").forward(request, response);
	        return;
	    }

		
		Part file = request.getPart("photo");
		
		if(file == null ) {
			request.setAttribute("error", "Photo required!!");		
			request.setAttribute("bean", student);		
			request.getRequestDispatcher("STU001.jsp").forward(request, response);
			return;
		}
		
	
		try {			
			
			String imageFileName = file.getSubmittedFileName();
			//System.out.println("Selected Image File Name:" + imageFileName);
			
			String uploadPath = "C:\\Thiha-Home\\ojt batch-13Workspace\\Student_Servlet\\src\\main\\webapp\\resource\\image\\"+imageFileName;
			//System.out.println("Upload Path :"+ uploadPath);
			
			
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();
			
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}	
		
		String imageFileName = file.getSubmittedFileName();
		    
		    studentDao dao = new studentDao();
		    StudentRequestDTO dto = new StudentRequestDTO();
		    dto.setId(student.getId());
		    dto.setName(student.getName());
		    dto.setDob(student.getDob());
		    dto.setGender(student.getGender());
		    dto.setPhone(student.getPhone());
		    dto.setEdu(student.getEdu());
		    dto.setAttend(student.getAttend());
		    dto.setPhoto(imageFileName); // Set photo data directly
		    
		    int a = dao.insertStudent(dto);
		    if(a > 0) {
		        request.getRequestDispatcher("success.jsp").forward(request, response);
		    } else {
		        request.setAttribute("error", "Insert Failed!!");
		        request.setAttribute("student", student);
		        request.getRequestDispatcher("STU001.jsp").forward(request, response);
		    }
		
	}
}

