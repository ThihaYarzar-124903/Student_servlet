package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.AdminResponseDTO;
import model.LoginBean;


public class adminDao {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	public AdminResponseDTO adminLogin(LoginBean lb) {
		AdminResponseDTO res=new AdminResponseDTO();
		String sql="select * from admin where adminemail=? and adminpassword=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, lb.getEmail());
			ps.setString(2, lb.getPassword());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res.setAdminemail(rs.getString("adminemail"));
				res.setAdminpassword(rs.getString("adminpassword"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}
