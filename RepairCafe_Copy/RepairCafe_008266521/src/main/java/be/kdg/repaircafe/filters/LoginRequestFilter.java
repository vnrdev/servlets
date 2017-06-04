package be.kdg.repaircafe.filters;

import be.kdg.repaircafe.beans.ServiceInitBean;
import be.kdg.repaircafe.beans.UserBean;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class LoginRequestFilter implements Filter {
    private FilterConfig fc; 

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         this.fc = filterConfig;
         
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String TIME_STRING = new SimpleDateFormat("E HH:mm:ss").format(new Date());
        fc.getServletContext().log(String.format("%s %s", TIME_STRING, " :LoginFilter" ));
        
        ApplicationContext appContext;
        ServletContext sc = request.getServletContext();
        appContext = WebApplicationContextUtils.getWebApplicationContext(sc);
        UserService userService = (UserService) appContext.getBean("userService");
        UserBean userBean = (UserBean) appContext.getBean("userBean");
        
        try {
            userService.checkLogin(userBean.getUsername(), userBean.getPassword());
            chain.doFilter(request, response);
        } catch (UserServiceException ex) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("index.xhtml");
        }
    }
    
    @Override
    public void destroy() {
        System.out.println("Requestfilter wordt vernietigd.");
    }
}
