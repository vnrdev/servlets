/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.repaircafemodel.persistence.impl;

import be.kdg.repaircafemodel.dom.users.User;
import be.kdg.repaircafemodel.persistence.api.UserDAO;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wouter
 */
@Repository("userDAO")
public class UserDAOMapImpl implements UserDAO
{
    private final ConcurrentHashMap<String, User> users;
    
    public UserDAOMapImpl()
    {
        this.users = new ConcurrentHashMap<>();
    }

    /**
     * Geef de User horende bij deze gebruikersnaam
     * @param username
     * @return 
     */
    @Override
    public User getUser(String username)
    {
        return users.get(username);
    }

    /**
     * Voeg een gebruiker toe aan 
     * @param user 
     * @throws be.kdg.repaircafemodel.service.exceptions.UserServiceException 
     */
    @Override
    public synchronized void addUser(User user) throws UserServiceException
    {
        if(users.containsKey(user.getUsername()))
            throw new UserServiceException("User already exists");
        users.put(user.getUsername(), user);
    }
    
    /**
     * Verwijder een gebruiker met deze gebruikersnaam
     * @param username 
     */
    @Override
    public void removeUser(String username)
    {
        if(users.containsKey(username))
            users.remove(username);
    }

    /**
     * Geef een lijst van alle gekende gebruikers
     * @return 
     */
    @Override
    public Map<String, User> getUsers()
    {
        return users;
    }    
}
