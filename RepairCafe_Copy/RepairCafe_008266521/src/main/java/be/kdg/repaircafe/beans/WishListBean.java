package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.dom.repairs.Repair;
import be.kdg.repaircafemodel.service.api.RepairService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("wishListBean")
@Scope("session")
public class WishListBean implements Serializable {

    @Autowired
    RepairService repairService;

    private LinkedHashSet<Repair> wishList;

    public WishListBean(){
        wishList = new LinkedHashSet<>();
    }
    
    public LinkedHashSet<Repair> getWishList() {
        return wishList;
    }

    public void setWishList(LinkedHashSet<Repair> wishList) {
        this.wishList = wishList;
    }

    public void addToWishList(Repair repair) {
        wishList.add(repair);
    }

    public void removeFromWishList(Repair repair) {
        wishList.remove(repair);
    }

}
