package com.example.web;
import com.example.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class BeerSelect extends HttpServlet {
	public void doPost(HttpServletRequest request,
					   HttpServletResponse response)
			   throws IOEXception, ServletException {
			   
		String c = request.getParameter("color");
		
		BeerExpert be = new BeerExpert();
		List result = be.getBrands(c);
		
		request.setAttribute("styles",result);
		RequestDispatcher disp = 
			request.getRequestDispatcher("result.jsp");
		disp.forward(request,response);
	}
}