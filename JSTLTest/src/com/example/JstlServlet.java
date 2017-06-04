package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class JstlServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Person p = new Person();
		p.setName("Johnny");
		request.setAttribute("person",p);
		
		String[] movies1 = {"Fight Club","Alice in Wonderland"};
		String[] movies2 = {"Neverland", "You've got mail."};
		List movieList = new ArrayList();
		movieList.add(movies1);
		movieList.add(movies2);
		request.setAttribute("movieList",movieList);
		
		RequestDispatcher view = request.getRequestDispatcher("/JSTLTest.jsp");
		view.forward(request,response);
	}
}