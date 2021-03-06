/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.repaircafemodel.persistence.impl;

import be.kdg.repaircafemodel.dom.repairs.Repair;
import be.kdg.repaircafemodel.dom.repairs.RepairDetails.Status;
import be.kdg.repaircafemodel.dom.users.Client;
import be.kdg.repaircafemodel.dom.users.Repairer;
import be.kdg.repaircafemodel.persistence.api.RepairDAO;
import be.kdg.repaircafemodel.service.exceptions.RepairServiceException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wouter
 */
@Repository("repairDAO")
public class RepairDAOLambdaImpl implements RepairDAO
{
    private final Set<Repair> repairs;

    public RepairDAOLambdaImpl()
    {
        this.repairs = new HashSet<>();
    }
    
    @Override
    public synchronized void saveRepair(Repair repair)
    {
        if(!repairs.contains(repair))
            this.repairs.add(repair);
    }

    @Override
    public synchronized void deleteRepair(Repair repair)
    {
        if(repairs.contains(repair))
            this.repairs.remove(repair);
    }

    @Override
    public Repair getRepairByID(int id)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getRepairId() == id)
                .findFirst().get();
    }

    /**
     * Search specific repairs by keyword
     * @param keyword
     * @return list of matching repairs
     */
    @Override
    public List<Repair> getRepairsByKeyword(String keyword)
    {
        String query = keyword.trim().toLowerCase();
        return this.repairs
                .stream()
                .filter(r ->                         
                        (r.getItem().getBrand().toLowerCase().contains(query) || 
                        r.getItem().getCategory().toLowerCase().contains(query) || 
                        r.getItem().getProductName().toLowerCase().contains(query) || 
                        r.getDetails().getDefect().toLowerCase().contains(query) || 
                        r.getDetails().getDescription().toLowerCase().contains(query)) 
                        && r.getDetails().isAssigned() == false)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Repair> getAllRepairs() throws RepairServiceException
    {
        return repairs.stream().collect(Collectors.toList());
    }

    @Override
    public List<Repair> getAllOpenRepairs() throws RepairServiceException
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getDetails().isAssigned() == false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> getAllClosedRepairs()
    {
        return this.repairs
                .stream()
                .filter(repair -> (repair.getDetails().getStatus().equals(Status.Fixed) 
                        || repair.getDetails().getStatus().equals(Status.Irreparable)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> getRepairsByClient(Client client)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getClient().equals(client))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Repair> getRepairsByRepairer(Repairer repairer)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getRepairer() != null)
                .filter(repair -> repair.getRepairer().equals(repairer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> getRepairsByBrand(String brand)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getItem().getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> getRepairsByCategory(String category)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getItem().getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> getRepairsByDefect(String defect)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getDetails().getDefect().equals(defect))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCategories()
    {
        return this.repairs
                .stream()
                .map(repair -> repair.getItem().getCategory())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllDefects()
    {
        return this.repairs
                .stream()
                .map(repair -> repair.getDetails().getDefect())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBrands()
    {
        return this.repairs
                .stream()
                .map(repair -> repair.getItem().getBrand())
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> getClosedRepairsByUser(Client client)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getClient() == client)
                .filter(repair -> repair.getDetails().getStatus() == Status.Fixed ||
                        repair.getDetails().getStatus() == Status.Irreparable)
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> getOpenRepairsByUser(Client client)
    {
        return this.repairs
                .stream()
                .filter(repair -> repair.getClient() == client)
                .filter(repair -> repair.getDetails().getStatus() == Status.Broken)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized void removeAllRepairs()
    {
        this.repairs.clear();
    }

}
