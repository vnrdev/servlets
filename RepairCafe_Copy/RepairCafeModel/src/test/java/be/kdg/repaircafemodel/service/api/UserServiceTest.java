/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.kdg.repaircafemodel.service.api;

import be.kdg.repaircafemodel.dom.users.Address;
import be.kdg.repaircafemodel.dom.users.Client;
import be.kdg.repaircafemodel.dom.users.Person;
import be.kdg.repaircafemodel.service.exceptions.UserServiceException;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author wouter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/be/kdg/repaircafemodel/spring/root-context.xml")
public class UserServiceTest
{
    @Autowired
    private UserService instance;
    
    public UserServiceTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    
    /**
     * Test of addUser method, of class UserService.
     */
    @Test
    public void testAddUser()
    {
        System.out.println("addUser");
        // Create a client
        Address address1 = new Address("n", "5", "2", "a");
        Person person1 = new Person("Jan", "Peeters", address1);
        Client client1 = new Client(person1, "jan.peeters@s.k.d", "jan");
        Client client2 = new Client(person1, "jan.peeters@s.k.d", "jef");
        
        boolean notReached = true;
        try
        {
            instance.addUser(client1);
            instance.addUser(client2);
            notReached = false;            
        }
        catch (UserServiceException ex)
        {
            TestCase.assertTrue(notReached);
        }
    }
    
}
