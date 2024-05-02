package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/StudentUpdateServlet")
@MultipartConfig
public class StudentUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentUpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            courseDao courseDao = new courseDao();
            ArrayList<CourseResponseDTO> courseList = courseDao.selectAllCourse();
            request.setAttribute("list1", courseList);

            StudentRequestDTO dto = new StudentRequestDTO();
            dto.setSid(Integer.parseInt(request.getParameter("sid")));

            studentDao studentDao = new studentDao();
            StudentResponseDTO res = studentDao.selectOne(dto);

            List<String> stuCourse = null;
            if (res != null && res.getAttend() != null)
                stuCourse = List.of(res.getAttend().split(","));

            request.setAttribute("stuCourse", stuCourse);
            request.setAttribute("res", res);
            request.getRequestDispatcher("studentUpdate.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

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
			request.getRequestDispatcher("studentUpdate.jsp").forward(request, response);
		}
		
		Part file = request.getPart("photo");
		
		if(file == null ) {
			request.setAttribute("error", "Photo required!!");		
			request.setAttribute("bean", student);		
			request.getRequestDispatcher("studentUpdate.jsp").forward(request, response);
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
		dto.setPhoto(imageFileName);
		
		int i = dao.updateData(dto);
		if (i > 0) {
			response.sendRedirect("DisplayStudentServlet");
		} else {
			request.setAttribute("error", "Update Failed");
			request.getRequestDispatcher("studentUpdate.jsp").forward(request, response);
		}
    }
}
