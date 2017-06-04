package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ELServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Person p = new Person();
		p.setName("Leelu");
		
		Dog d = new Dog();
		d.setName("Clyde");
		
		Toy t1 = new Toy();
		t1.setName("stick");
		
		Toy t2 = new Toy();
		t2.setName("neighbor's cat");
		
		Toy t3 = new Toy();
		t3.setName("Barbie TM doll head");
		
		d.setToys(new Toy[]{t1,t2,t3});
		p.setDog(d);
		request.setAttribute("person",p);
		
		RequestDispatcher view = request.getRequestDispatcher("/ELTest.jsp");
		view.forward(request,response);
	}
}