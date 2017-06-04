package be.kdg.repaircafe.beans;

import be.kdg.repaircafemodel.dom.repairs.Bid;
import be.kdg.repaircafemodel.dom.repairs.Repair;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bidSummaryBean")
@Scope("session")
public class BidSummaryBean implements Serializable {
    private List<Bid> bids;

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void addRepairForBidSummary(Repair r){
        bids=r.getBids();
    }
    
}
