package be.kdg.repaircafe.listeners;

import be.kdg.repaircafe.beans.ServiceInitBean;
import be.kdg.repaircafe.helpers.CustomLogger;
import javax.servlet.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MyContextListener implements ServletContextListener {        
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        CustomLogger.doLog(sc, "De applicatie werd geladen.");
        
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        ((ServiceInitBean)appContext.getBean("serviceInitBean")).initCafe();
        CustomLogger.doLog(sc,"Het model werd gereset en geïnitialiseerd.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        CustomLogger.doLog(event.getServletContext(), "De applicatie wordt afgebouwd.");
    }
}
