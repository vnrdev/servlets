package be.kdg.repaircafe.helpers;

import javax.servlet.ServletContext;
import org.springframework.stereotype.Component;

@Component
public class CustomLogger {
    final static String TEMPLATE = "#################### %s ####################";
    
    public static void doLog(ServletContext sc, String text){
        sc.log(String.format(TEMPLATE,text));
    }
}
