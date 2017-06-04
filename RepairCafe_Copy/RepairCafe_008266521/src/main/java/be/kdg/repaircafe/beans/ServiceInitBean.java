package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.TestRepairCafe;
import be.kdg.repaircafemodel.dom.repairs.Item;
import be.kdg.repaircafemodel.dom.repairs.Repair;
import be.kdg.repaircafemodel.dom.repairs.RepairDetails;
import be.kdg.repaircafemodel.dom.users.Address;
import be.kdg.repaircafemodel.dom.users.Client;
import be.kdg.repaircafemodel.dom.users.Person;
import be.kdg.repaircafemodel.dom.users.Repairer;
import be.kdg.repaircafemodel.service.api.RepairService;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("serviceInitBean")
public class ServiceInitBean implements Serializable {
    @Autowired
    private UserService userService;
    
    @Autowired
    private RepairService repairService;
    
    public void initCafe()
    {   
        userService.removeAllUsers();
        repairService.removeAllRepairs();

        // Maak een gewone gebruiker
        Address address1 = new Address("b", "5", "2", "m");
        Person person1 = new Person("Jan", "Peeters", address1);
        Client client1 = new Client(person1, "jan.peeters@s.k.d", "jan");

        // Maak een gewone gebruiker
        Address address2 = new Address("z", "23", "3", "l");
        Person person2 = new Person("m", "vdb", address2);
        Client client2 = new Client(person2, "m.vdb@gmail.com", "m");

        // Maak een repairer
        Address address3 = new Address("n", "5", "2", "a");
        Person person3 = new Person("W", "D", address3);
        Repairer repairGuy1 = new Repairer(person3, "w.d@k.d", "jef", "Master");
        repairGuy1.rate(4);

        // Maak een repairer
        Address address4 = new Address("k", "1", "2", "l");
        Person person4 = new Person("h", "Man", address4);
        Repairer repairGuy2 = new Repairer(person4, "h.man@hman.com", "moeilijk", "Bachelor");
        repairGuy2.rate(3);
        // add users and update een gebruiker met een nieuw wachtwoord
        
        try
        {
            userService.addUser(client1);
            userService.addUser(client2);
            userService.addUser(repairGuy1);
            userService.addUser(repairGuy2);
            userService.updatePassword(repairGuy1, "jef", "wouter");
            userService.checkLogin(repairGuy1.getUsername(), "wouter");
        }
        catch (UserServiceException ex)
        {
            Logger.getLogger(TestRepairCafe.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            userService.checkLogin("w.d@.d", "wouter");
        }
        catch (UserServiceException ex)
        {
            Logger.getLogger(TestRepairCafe.class.getName()).log(Level.SEVERE, null, ex);
        }

        Repair repair1 = new Repair(new Item("G 4210 i", "Miele", "Vaatwasser"), new RepairDetails(
                "Elektrisch",
                "Toestel stopt niet meer",
                RepairDetails.PriceModel.FIXED,
                new DateTime().plusWeeks(2)));

        repairService.submitRepair(client1, repair1);

        Repair repair2 = new Repair(new Item("ALOA 150", "Logicom", "Telefoon"), new RepairDetails(
                "Elektrisch",
                "Laadt niet meer op",
                RepairDetails.PriceModel.PER_HOUR,
                new DateTime().plusWeeks(2)));
        repairService.submitRepair(client2, repair2);

        System.out.println(repairService.getAllCategories());
        repairService.placeBid(repairGuy1, repair1, 200.0);
        repairService.placeBid(repairGuy2, repair1, 150.0);

        System.out.println(repairService.findAllRepairsByClient(client1));
        repairService.placeBid(repairGuy1, repair2, 15);
        repairService.placeBid(repairGuy2, repair2, 12);
    }
}
