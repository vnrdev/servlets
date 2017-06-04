package be.kdg.repaircafe.classicservlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/appinfo")
public class ApplicationInfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> authors = new ArrayList<String>();
        authors.add("V R (student)");
        authors.add("W D (d)");
        request.setAttribute("auteurs", authors);
        
        RequestDispatcher rd = request.getRequestDispatcher("appinfo.jsp");
        rd.forward(request, response);
    }
}
