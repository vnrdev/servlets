package be.kdg.repaircafemodel.dom.users;

import be.kdg.repaircafemodel.dom.repairs.Repair;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a user of the system. Every user is associated
 * with a Person. Every user has to have a unique username. This username takes
 * the form of an email address.
 *
 * A user has list of repairs associated with it.
 * For a Client User this list contains all his open and closed repairs.
 * For a Repairer User this list contains all his *assigned* repairs
 * 
 * @see(be.kdg.repaircafemodel.service.api.RepairService)
 *
 * @author wouter
 */
public abstract class User
{
    private final String username;
    private Person person;
    private String password;
    protected final List<Repair> repairs;

    public User(Person person, String username, String password)
    {
        this.person = person;
        this.username = username;
        this.password = password;
        this.repairs = new ArrayList<>();
    }
    
    /**
     * Add repair to this user's list
     * @param repair 
     */
    public synchronized void addRepair(Repair repair)
    {
        this.repairs.add(repair);
    }
    
    /**
     * Remove a repair from the user's list
     * @param repair 
     */
    public synchronized void removeRepair(Repair repair)
    {
        this.repairs.remove(repair);
    }
    
    /**
     * Return list of associated repairs.
     * 
     * If User is a Client then this list contains his submitted repairs
     * If User us a Repaier then this list contains assigned repairs.
     * 
     * @return List of repairs
     */
    public List<Repair> getRepairs()
    {
        return repairs;
    }

    /**
     * Check password
     *
     * @param password
     * @return
     */
    public boolean checkPassword(char[] password)
    {
        //String encrypted = encrypt(password);
        return this.password.equals(String.valueOf(password));
    }

    /**
     * Get username for this user
     *
     * @return
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Return Person associated with this user
     *
     * @return Person object
     * 
     * @see(be.kdg.repaircafemodel.dom.users.Person)     
     */
    public Person getPerson()
    {
        return person;
    }

    public synchronized void setPerson(Person person)
    {
        this.person = person;
    }

    /**
     * Change password for user
     * @param oldPassword
     * @param newPassword
     * @throws UserServiceException 
     */
    public synchronized void changePassword(String oldPassword, String newPassword) throws UserServiceException
    {
        // check oldpassword throws UserServiceException
        if (!this.checkPassword(oldPassword.toCharArray()))
        {
            throw new UserServiceException("Password incorrect");
        }
        this.password = newPassword;
    }

    @Override
    public String toString()
    {
        return "User{" + username + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username))
        {
            return false;
        }
        return true;
    }
}
