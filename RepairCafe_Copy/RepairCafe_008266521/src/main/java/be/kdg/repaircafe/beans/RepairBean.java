package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.service.api.RepairService;
import be.kdg.repaircafemodel.service.api.UserService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

public class RepairBean implements Serializable {
    //injecties
    @Autowired
    private UserService userService;
    
    @Autowired
    private RepairService repairService;

    //@Autowired
    //LoginBean
	
    //properties
	
    //Repair evalueren: is dat goed gerepareerd of niet?
    //selectedRepair(id) //id wordt gebruikt om de repair op te zoeken
    //setSelectedRepair()
    //getSelectedRepair()
	
    //gaat via service dat ophalen en gaat dat omzetten naar een lijst
    //getOpenRepairs()
	
    //zelfde principe als hierboven
    //getGeslotenRepairs()
}
