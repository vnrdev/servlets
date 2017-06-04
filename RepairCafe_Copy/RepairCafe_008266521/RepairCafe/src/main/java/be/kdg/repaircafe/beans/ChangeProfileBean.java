package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.dom.users.Person;
import be.kdg.repaircafemodel.dom.users.User;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("changeProfileBean")
@Scope("session")
public class ChangeProfileBean implements Serializable {

    @Autowired
    UserBean userBean;
    @Autowired
    UserService userService;
    
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public ChangeProfileBean() {
        System.out.println("changeprofilebean created");
    }

    public String submitChanges() {
        try {
            User user = userService.getUser(userBean.getUsername());
            Person person = user.getPerson();
            userService.updateUser(user, person);
            userService.updatePassword(user,userBean.getPassword(),newPassword);
            //FacesMessage msg = new FacesMessage("Changes saved.");
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Changes saved."));
            return "changeprofile";
        } catch (UserServiceException ex) {
            System.out.println("SubmitChanges: UserServiceException: " + ex.getMessage());
            return "changeprofile";
        }

    }

}
