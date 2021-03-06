/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.repaircafemodel.dom.users;

import be.kdg.repaircafemodel.dom.repairs.Bid;
import be.kdg.repaircafemodel.dom.repairs.Repair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A Repairer searches for repairs he/she can mend. A repairer places bids on
 * repairs
 *
 * @author wouter
 */
public class Repairer extends User
{
    private final List<Bid> bids;
    private double overalRating;
    private boolean rated = false;
    private String degree;

    public Repairer(Person person, String username, String password, String degree)
    {
        super(person, username, password);
        this.degree = degree;
        this.bids = new ArrayList<>();
    }

    /**
     * Rating is calculated based on ratings of past repairs done by this
     * repairer
     *
     * @{link Repairer#rate}
     * @return
     */
    public int getOveralRating()
    {
        return (int) Math.round(overalRating);
    }

    public String getDegree()
    {
        return degree;
    }

    public void setDegree(String degree)
    {
        this.degree = degree;
    }

    public void addBid(Bid bid)
    {
        this.bids.add(bid);
    }

    public List<Bid> getBids()
    {
        return bids;
    }

    /**
     * Every call to this method will update the overall rating of a repairer.
     * Averaging out all ratings.
     *
     * @param rating
     */
    public void rate(int rating)
    {
        if (rating >= 0)
        {
            if (!rated)
            {
                overalRating = rating;
                rated = true;
            }
            else
            {
                overalRating = (overalRating + rating) / 2.0;
            }
        }
    }

    /**
     * Removing a repair for a repairer also removes associated bid(s) from
     * his bid list.
     * 
     * @param repair 
     */
    @Override
    public synchronized void removeRepair(Repair repair)
    {
        List<Bid> allBids = repair.getBids();
        
        // find intersection between repairers bid and bids of repair
        List<Bid> intersection = allBids.stream()
                .filter(b -> bids.contains(b))
                .collect(Collectors.toList());

        // delete all found bids from my bids 
        intersection.stream()
                .forEach(bid -> bids.remove(bid));
        
        // remove repair from assigned list
        repairs.remove(repair);

    }
}
