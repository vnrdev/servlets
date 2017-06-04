package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.dom.repairs.Item;
import be.kdg.repaircafemodel.dom.repairs.Repair;
import be.kdg.repaircafemodel.dom.repairs.RepairDetails;
import be.kdg.repaircafemodel.dom.repairs.RepairDetails.PriceModel;
import be.kdg.repaircafemodel.dom.users.Client;
import be.kdg.repaircafemodel.service.api.RepairService;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("submitRepairBean")
@Scope("session")
public class SubmitRepairBean implements Serializable {

    @Autowired
    private RepairService repairService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBean userBean;

    private String productname;
    private String brand;
    private String defect;
    private String category;

    private String description;
    private PriceModel pricemodel;
    private DateTime dueDate;

    public PriceModel getPricemodel() {
        return pricemodel;
    }

    public void setPricemodel(PriceModel pricemodel) {
        this.pricemodel = pricemodel;
    }
    private static List<String> pricemodelList;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPricemodelList() {
        return pricemodelList;
    }

    public void setPricemodelList(List<String> pricemodelList) {
        this.pricemodelList = pricemodelList;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getAllCategories() {
        return new ArrayList(new HashSet(repairService.getAllCategories()));
    }

    public List<String> getAllBrands() {
        return new ArrayList(new HashSet(repairService.getAllBrands()));
    }

    public List<String> getAllDefects() {
        return new ArrayList(new HashSet(repairService.getAllDefects()));
    }

    public List<String> getPriceModels() {
        return pricemodelList;
    }

    static {
        pricemodelList = new ArrayList<String>();
        pricemodelList.add(RepairDetails.PriceModel.FIXED.toString());
        pricemodelList.add(RepairDetails.PriceModel.PER_HOUR.toString());
    }

    public String submitRepair() {
        Repair theRepair = new Repair(new Item(productname, brand, category), new RepairDetails(
                defect,
                description,
                pricemodel,
                dueDate));
        try {
            Client theClient = (Client) userService.getUser(userBean.getUsername());
            repairService.submitRepair(theClient, theRepair);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Changes saved."));
            return "repair_summary";
        } catch (UserServiceException ex) {
            System.out.println("Submit repair: " + ex.getMessage());
            return "repair_entry";
        }
    }

}
