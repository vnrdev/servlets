package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.dom.repairs.Repair;
import be.kdg.repaircafemodel.dom.users.Repairer;
import be.kdg.repaircafemodel.service.api.RepairService;
import be.kdg.repaircafemodel.service.api.UserService;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("repairSearchBean")
@Scope("session")
public class RepairSearchBean implements Serializable {
    @Autowired
    RepairService repairService;
    
    @Autowired
    WishListBean wishListBean;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserBean userBean;
    
    @Autowired
    BidSummaryBean bidSummaryBean;
    
    private String keyword;
    private Repair repair;
    private boolean editable;
    private int bidprice;

    public int getBidprice() {
        return bidprice;
    }

    public void setBidprice(int bidprice) {
        this.bidprice = bidprice;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
    
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String search(String keyword){
        getOpenRepairsList(keyword);
        return "repair_search";
    }
    
    public List<Repair> getOpenRepairsList(String keyword) {
        return repairService.findRepairsByKeyword(keyword);
    }
    
    public void addToWishList(Repair repair){
        wishListBean.addToWishList(repair);
    }    
    
    public void placeBidAction(){
        setEditable(true);
    }
    
    public String placeBid(Repair repair, int price){
        try {
            repairService.placeBid((Repairer)userService.getUser(userBean.getUsername()), repair, price);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bid placed."));
            System.out.println("Bid placed.");
            editable=false;
            return null;
        } catch (UserServiceException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            System.out.println("Could not place bid.");
            return "repair_search";
        }
    }
    
    public String addRepairForBidSummary(Repair r){
        bidSummaryBean.addRepairForBidSummary(r);
        return "bids_summary";
    }
    
}
