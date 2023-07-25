package com.sri.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sri.dto.Student2;

public class StudentDao2 {
public  boolean addStudent(Student2 dto) {
	boolean res=false;
	
	Connection con = null;
	PreparedStatement pstmt=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cfje3?user=root&password=root");
		pstmt=con.prepareStatement("insert into student values(?,?,?)");
		pstmt.setInt( 1,dto.getSid());
		pstmt.setString(2, dto.getSname());
		pstmt.setDouble(3, dto.getSper());
		int f=pstmt.executeUpdate();
		if (f==1) {
			res=true;
		} 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return res;
}

public  List<Student2> displayStudent() {
	List<Student2> l1=new ArrayList<Student2>();
	Connection con = null;
	PreparedStatement prstm = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cfje3?user=root&password=root");
		prstm = con.prepareStatement("select *  from student ");
		ResultSet rs = prstm.executeQuery();
		while (rs.next()) {
			Student2 student=new Student2();
			student.setSid(rs.getInt("sid"));
			student.setSname(rs.getString(2));
			student.setSper(rs.getDouble(3));
			l1.add(student);
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (prstm != null) {
			try {
				prstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	return l1;
	
}
public  boolean deleteStudent(int sid) {
	boolean rs = false;
	Connection con = null;
	PreparedStatement prstm = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cfje3?user=root&password=root");
		prstm = con.prepareStatement("delete from student where sid=?");
		prstm.setInt(1, sid);
		int f = prstm.executeUpdate();
		if (f == 1) {
			rs = true;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (prstm != null) {
			try {
				prstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	return rs;
	
}
public boolean updateStudent(int sid,String sname,double sper) {
	Student2 student=new Student2();
	Connection con = null;
	PreparedStatement prstm = null; 
	boolean flag=false;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cfje3?user=root&password=root");
		prstm = con.prepareStatement("update student set sname=?,sper=? where sid=?");
		prstm.setString(1, sname);
		prstm.setDouble(2, sper);
		prstm.setInt(3, sid);
		int rs = prstm.executeUpdate();
		if (rs == 1) {
			flag = true;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch bloc
		e.printStackTrace();
	} finally {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (prstm != null) {
			try {
				prstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return flag;
}
public Student2 searchStudent(int sid) {
	Student2 student=new Student2();
	Connection con = null;
	PreparedStatement prstm = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cfje3?user=root&password=root");
		prstm = con.prepareStatement("select *  from student where sid=?");
		prstm.setInt(1, sid);
		ResultSet rs = prstm.executeQuery();
		if (rs.next()) {
			student.setSid(rs.getInt("sid"));
			student.setSname(rs.getString(2));
			student.setSper(rs.getDouble(3));
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (prstm != null) {
			try {
				prstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return student;
}
}

