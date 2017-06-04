package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.service.api.RepairService;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.event.ValueChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("userBean")
@Scope("session")
public class UserBean implements Serializable
{
    @Autowired
    private UserService userService;

    @Autowired
    private RepairService repairService;

    private String username;
    private String password;
    
    private boolean repairer;
    private String degree;

    private String firstname;
    private String lastname;
    private String street;
    private String streetno;
    private String postalcode;
    private String town;

    /**
     * !!! ATTENTIE: BAD CODE ALERT !!!
     *
     * Je moet het initialiseren op een andere manier realiseren Deze code zou
     * je best ook van op je website moeten kunnen aanroepen zodat je het model
     * kan resetten.
     *
     */
    //@PostConstruct
    //Deze code is verhuisd naar ServiceInitBean

    public String checkCredentials()
    {
        try
        {
            userService.checkLogin(username, password);
            return "welcome.xhtml";
        }
        catch (UserServiceException ex)
        {
            return "index.xhtml";
        }
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
  

    private static Map<String, String> degrees;

    // getters en setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetno() {
        return streetno;
    }

    public void setStreetno(String streetno) {
        this.streetno = streetno;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public boolean isRepairer() {
        return repairer;
    }

    public void setRepairer(boolean repairer) {
        this.repairer = repairer;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setTheRepairer(boolean repairer) {
        this.repairer = repairer;
    }

    public Map<String, String> getDegrees() {
        return degrees;
    }

    public static void setDegrees(Map<String, String> degrees) {
        UserBean.degrees = degrees;
    }

    // eventueel andere methoden
    static {
        degrees = new LinkedHashMap<String, String>();
        degrees.put("Bachelor", "Bachelor");
        degrees.put("Master", "Master");
        degrees.put("Doctorate", "Doctorate");
    }

}
