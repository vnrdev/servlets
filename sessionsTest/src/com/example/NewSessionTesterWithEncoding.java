package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class NewSessionTesterWithEncoding extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("test session attributes<br>");
		
		HttpSession session = request.getSession();
		out.println("<a href=\"" + response.encodeURL("NewSessionTesterWithEncoding.do") + "\">click me</a>");
		
		if (session.isNew()){
			out.println("This is a new session.");
		} else {
			out.println("Welcome back!");
		}
	}

}