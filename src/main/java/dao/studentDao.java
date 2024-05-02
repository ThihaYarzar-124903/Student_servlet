package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.StudentRequestDTO;
import dto.StudentResponseDTO;

public class studentDao {
	public static Connection con = null;
	
	static {
		con = MyConnection.getConnection();
	}

	public int rowCountStudent() {
		int i = 0;
		String sql = "select count(*) from student";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;

	}

	public int insertStudent(StudentRequestDTO req) {
		int result = 0;
		String sql = "insert into student(sid,  name, dob, gender, phone, edu, attend, photo ,id)values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, req.getSid());

			ps.setString(2, req.getName());
			ps.setString(3, req.getDob());
			ps.setString(4, req.getGender());
			ps.setString(5, req.getPhone());
			ps.setString(6, req.getEdu());
			String attendString = String.join(",", req.getAttend());
			ps.setString(7, attendString);
			ps.setString(8, req.getPhoto());
			ps.setString(9, req.getId());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return result;
	}

	public ArrayList<StudentResponseDTO> selectAllStudent() {
		ArrayList<StudentResponseDTO> slist = new ArrayList<>();
		String sql = "select * from student";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				StudentResponseDTO res = new StudentResponseDTO();
				res.setSid(rs.getInt("sid"));
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEdu(rs.getString("edu"));
				res.setAttend(rs.getString("attend"));
				res.setPhoto(rs.getString("photo"));
				slist.add(res);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return slist;
	}

	public StudentResponseDTO selectOneStudent(StudentRequestDTO student) {
		StudentResponseDTO res = new StudentResponseDTO();
		String sql = "select * from student where name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, student.getName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEdu(rs.getString("edu"));
				res.setAttend(rs.getString("attend"));
				res.setPhoto(rs.getString("photo"));
				res.setSid(rs.getInt("sid"));

			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Retrieved Student: " + res.toString());
		return res;
	}

	public StudentResponseDTO selectOne(StudentRequestDTO student) {
		StudentResponseDTO res = new StudentResponseDTO();
		String sql = "select * from student where sid=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, student.getSid());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				
				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEdu(rs.getString("edu"));
				res.setAttend(rs.getString("attend"));
				res.setPhoto(rs.getString("photo"));
				res.setSid(rs.getInt("sid"));

			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Retrieved Student: " + res.toString());
		return res;
	}

	public List<StudentResponseDTO> searchStudent(String name, String attend) {
		List<StudentResponseDTO> list = new ArrayList<>();
		String sql = "select * from student where name LIKE ? and attend LIKE ?";
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ps.setString(2, "%" + attend + "%");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();

				res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEdu(rs.getString("edu"));
				res.setAttend(rs.getString("attend"));
				res.setPhoto(rs.getString("photo"));

				list.add(res);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	// update
	public int updateData(StudentRequestDTO student) {
		int result = 0;
		String sql = "UPDATE student SET name=?,dob=?,gender=?,phone=?,edu=?,attend=?,photo=? WHERE id=?";

		try {
			
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, student.getName());
			ps.setString(2, student.getDob());
			ps.setString(3, student.getGender());
			ps.setString(4, student.getPhone());
			ps.setString(5, student.getEdu());
			String attend = String.join(",", student.getAttend());
			ps.setString(6, attend);
			ps.setString(7,  student.getPhoto());
			ps.setString(8,  student.getId());
			
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update student error " + e);
		}
		return result;
	}
	
	public StudentResponseDTO selectPhone(StudentRequestDTO student) {
	    StudentResponseDTO res = null; // Initialize the result to null
	    String sql = "SELECT * FROM student WHERE phone=?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, student.getPhone());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            res = new StudentResponseDTO();
	            res.setId(rs.getString("id"));
				res.setName(rs.getString("name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEdu(rs.getString("edu"));
				res.setAttend(rs.getString("attend"));
	        }
	    } catch (SQLException e) {
	        // Handle or log the exception appropriately
	        e.printStackTrace();
	    }
	    return res;
	}

}