package be.kdg.repaircafe.validators;

import be.kdg.repaircafe.beans.UserBean;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@FacesValidator("userNameCustomValidator")
public class UsernameCustomValidator implements Validator {

    ApplicationContext appContext;
    private UserBean userBean;
    private UserService userService;

    public UsernameCustomValidator() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        userBean = (UserBean) appContext.getBean("userBean");
        userService = (UserService) appContext.getBean("userService");
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (checkUserExists(component).equals("exists")) {
            System.out.println("validate: the user exists!");
            FacesMessage msg = new FacesMessage("Username entry failed.", "Username already exists.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else {
            System.out.println("has been properly validated");
        }
    }

    private String checkUserExists(UIComponent component) {
        String email="";
        try {        
            email = userBean.getUsername();
            if (userService.getUser(email) != null) {
                throw new UserServiceException("The user is already in the database!");
            }
            
            return "exists";
        } catch (UserServiceException ex) {
            //if (email != null){
                removeSessionScopedBean("userBean");
                removeSessionScopedBean("registerBean");
            //((UIInput)component).setSubmittedValue(null);
            //}
            System.out.println("Chk: UserServiceException: "+ex.getMessage());
            System.out.println("Chk: email: "+email);
            return "exists";
        } catch (NullPointerException ex) {
            System.out.println("Chk: NullPointerExceptoin: "+ex.getMessage());
            System.out.println("Chk: NPE: email: "+email);
            return "npe";
        }
    }
    
    public static void removeSessionScopedBean(String beanName) 
    {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(beanName);
    }

}
