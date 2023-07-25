package com.sri.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.sri.dao.StudentDao2;
import com.sri.dto.Student2;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class SearchStudent extends GenericServlet{
	private StudentDao2 dao=new StudentDao2();

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
   int sid=Integer.parseInt(req.getParameter("sid"));	
  Student2 dto=dao.searchStudent(sid);
  if (dto!=null) {
	pw.println("<h1> Student fetching.....! </h1>");
	pw.println("<table border =5>"
			+ "<tr><th>SID</th><th>SNAME</th><th>SPER</th></tr>");
	pw.println("<tr><td>"+dto.getSid()+"</td><td>"+dto.getSname()+"</td><td>"+dto.getSper()+"</td></tr>");
	pw.println("</table>");
} 
  else {
	  pw.println("<h1> Oops something wronng <a href='index.html'></a> </h1>"); 
  }
  pw.println("<a href='Home.html'>Home</a>");
	}
	
}
