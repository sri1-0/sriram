package com.sri.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sri.dao.StudentDao2;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import com.sri.dto.Student2;

public class DisplayStudents extends GenericServlet{
	private StudentDao2 dao=new StudentDao2();

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();	
  List<Student2> dto=dao.displayStudent();
	pw.println("<h1> Student fetching.....! </h1>");
	pw.println("<table border =5>"
			+ "<tr><th>SID</th><th>SNAME</th><th>SPER</th></tr>");
	for (Student2 student2 : dto) {
		pw.println("<tr><td>"+student2.getSid()+"</td><td>"+student2.getSname()+"</td><td>"+student2.getSper()+"</td></tr>");
	}
	pw.println("</table>");
	pw.println("<a href='Home.html'>Home</a>");

  
	}
	
}
