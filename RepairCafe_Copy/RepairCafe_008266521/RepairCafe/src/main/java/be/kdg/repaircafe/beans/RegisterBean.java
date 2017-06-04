package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.dom.users.Address;
import be.kdg.repaircafemodel.dom.users.Client;
import be.kdg.repaircafemodel.dom.users.Person;
import be.kdg.repaircafemodel.dom.users.Repairer;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("registerBean")
@Scope("session")
public class RegisterBean implements Serializable {

    // attributen
    @Autowired
    UserService userService;
    @Autowired
    UserBean userBean;

    // eventueel andere methoden

    public void valueChanged(ValueChangeEvent event) {
        System.out.println("valuechanging");

        if (userBean.isRepairer() == false) {
            userBean.setRepairer(true);
            System.out.println("repairer is false");
        } else {
            userBean.setRepairer(false);
            System.out.println("repairer is true");
        }
    }

    public String register() {
        try {
            Address address1 = new Address(userBean.getStreet(), userBean.getStreetno(), userBean.getPostalcode(), userBean.getTown());
            Person person1 = new Person(userBean.getFirstname(), userBean.getLastname(), address1);
            if(userBean.isRepairer()){
                // Maak een repairer
                Repairer repairGuy1 = new Repairer(person1, userBean.getUsername(), userBean.getPassword(), userBean.getDegree());
                userService.addUser(repairGuy1);
            } else {
                // Maak een gewone gebruiker
                Client client1 = new Client(person1, userBean.getUsername(), userBean.getPassword());
                userService.addUser(client1);
            }
            
            return "welcome";
        } catch (UserServiceException ex) {
            System.out.println("UserServiceException: "+ex.getMessage());
            
            //FacesMessage msg = new FacesMessage("Username entry failed.", "Username already exists.");
            //msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            //throw new ValidatorException(msg);
            return "register";
        } 
    }
}
