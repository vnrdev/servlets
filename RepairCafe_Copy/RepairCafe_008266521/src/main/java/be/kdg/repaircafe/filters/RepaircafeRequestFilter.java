package be.kdg.repaircafe.filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RepaircafeRequestFilter  implements Filter {
    private FilterConfig fc; 

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         this.fc = filterConfig;
         
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String resource = req.getRequestURI();
        
        if(resource != null){
            String TIME_STRING = new SimpleDateFormat("E HH:mm:ss").format(new Date());
            String REQUEST_STRING = String.format(" REQUEST: Resource %s werd opgevraagd.",resource);
            fc.getServletContext().log(String.format("%s %s", TIME_STRING, REQUEST_STRING));
        } 
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Requestfilter wordt vernietigd.");
    }
    
}
