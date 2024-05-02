package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserRequestDTO;
import dto.UserResponseDTO;
import model.LoginBean;



public class userDao {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		}
	public int rowCountUser() {
		int i=0;
		String sql="select count(*) from user";
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
	
	public int insertUser(UserRequestDTO req) {
		int result=0;
		String sql="insert into user(userid,useremail,userpassword,userconpassword,id)values(?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, req.getUserid());
			ps.setString(2, req.getUseremail());
			ps.setString(3, req.getUserpassword());
			ps.setString(4, req.getUserconpassword());
			ps.setInt(5, req.getId());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int updateUser(UserRequestDTO user) {
		int result=0;
		String sql="update user set userid=?, useremail=?, userpassword=?, userconpassword=? where id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUseremail());
			ps.setString(3, user.getUserpassword());
			ps.setString(4, user.getUserconpassword());
			ps.setInt(5, user.getId());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public UserResponseDTO userLogin(LoginBean lb) {
		UserResponseDTO res=new UserResponseDTO();
		String sql="select * from user where useremail=? and userpassword=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, lb.getEmail());
			ps.setString(2, lb.getPassword());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res.setUseremail(rs.getString("useremail"));
				res.setUserpassword(rs.getString("userpassword"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public ArrayList<UserResponseDTO> selectAllUser(){
		ArrayList<UserResponseDTO> list=new ArrayList<>();
		String sql="select * from user";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserResponseDTO res=new UserResponseDTO();
				res.setUserid(rs.getString("userid"));
				res.setUseremail(rs.getString("useremail"));
				res.setUserpassword(rs.getString("userpassword"));
				res.setUserconpassword(rs.getString("userconpassword"));
				res.setId(rs.getInt("id"));
				list.add(res);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public UserResponseDTO selectOneUser(UserRequestDTO user) {
		UserResponseDTO res=new UserResponseDTO();
		String sql="select * from user where id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1, user.getId());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res.setUserid(rs.getString("userid"));
				res.setUseremail(rs.getString("useremail"));
				res.setUserpassword(rs.getString("userpassword"));
				res.setUserconpassword(rs.getString("userconpassword"));
				res.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public UserResponseDTO selectEmailUser(UserRequestDTO user) {
		UserResponseDTO res= new UserResponseDTO();
		String sql="select * from user where useremail=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, user.getUseremail());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res.setUserid(rs.getString("userid"));
				res.setUseremail(rs.getString("useremail"));
				res.setUserpassword(rs.getString("userpassword"));
				res.setUserconpassword(rs.getString("userconpassword"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	public int userDelete(UserResponseDTO dto) {

	int result=0;
	String sql="DELETE FROM user WHERE id=?";
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
	 public List<UserResponseDTO> searchUser(String useremail){
		 List<UserResponseDTO> list=new ArrayList<>();
		 String sql="select * from user where useremail LIKE ?";
		 try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%" +useremail+ "%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserResponseDTO res=new UserResponseDTO();
				res.setUserid(rs.getString("userid"));
				res.setUseremail(rs.getString("useremail"));
				res.setUserpassword(rs.getString("userpassword"));
				res.setUserconpassword(rs.getString("userconpassword"));
				res.setId(rs.getInt("id"));
				list.add(res);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	 }
	
}
