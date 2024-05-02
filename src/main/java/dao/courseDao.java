package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CourseRequestDTO;
import dto.CourseResponseDTO;


public class courseDao {
	
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		}
	public int rowCountCourse() {
		int i=0;
		String sql="select count(*) from course";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	
	public int insertCourse(CourseRequestDTO req) {
		int result=0;
		String sql="insert into course(courseid, coursename, id)values(?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, req.getCourseid());
			ps.setString(2, req.getCoursename());
			ps.setInt(3, req.getId());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<CourseResponseDTO> selectAllCourse(){
		ArrayList<CourseResponseDTO> list1=new ArrayList<>();
		String sql="select * from course";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				CourseResponseDTO res=new CourseResponseDTO();
				res.setCourseid(rs.getString("courseid"));
				res.setCoursename(rs.getString("coursename"));
				res.setId(rs.getInt("id"));
				list1.add(res);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list1;
	}
	
	
	public int updateCourse(CourseRequestDTO course) {
		int result=0;
		String sql="update course set coursename=? where courseid=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
		
			ps.setString(1, course.getCoursename());
			ps.setString(2,course.getCourseid());
		
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public CourseResponseDTO selectOneCourse(CourseRequestDTO course) {
		CourseResponseDTO res=new CourseResponseDTO();
		String sql="select * from course where id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1, course.getId());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res.setCourseid(rs.getString("courseid"));
				res.setCoursename(rs.getString("coursename"));
				res.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	public int courseDelete(CourseResponseDTO dto) {

	int result=0;
	String sql="DELETE FROM course WHERE id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, dto.getId());
			result =ps.executeUpdate();
			System.out.println("deleting...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return result;
	
	}
	
	public CourseResponseDTO selectNameCourse(CourseRequestDTO course) {
		CourseResponseDTO res = null; // Initialize the result to null
	    String sql = "SELECT * FROM course WHERE coursename=?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, course.getCoursename());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            res = new CourseResponseDTO();
	            res.setCourseid(rs.getString("courseid"));
	            res.setCoursename(rs.getString("coursename"));
	         
	        }
	    } catch (SQLException e) {
	        // Handle or log the exception appropriately
	        e.printStackTrace();
	    }
	    return res;
	}

	
}
