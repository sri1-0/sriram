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

public class DeleteStudent extends GenericServlet{
	private StudentDao2 dao=new StudentDao2();

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   int sid=Integer.parseInt(req.getParameter("sid"));
 if (dao.deleteStudent(sid)) {
	pw.println("<h1> Student deleted sussesfully </h1>");
} 
  else {
	  pw.println("<h1> Oops something wronng <a href='index.html'></a> </h1>"); 
  }
 pw.println("<a href='Home.html'>Home</a>");
	}
	
	
}
