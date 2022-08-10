package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.Player;
import hk.edu.polyu.comp.comp2021.monopoly.Property;
import hk.edu.polyu.comp.comp2021.monopoly.STATUS;
import hk.edu.polyu.comp.comp2021.monopoly.Square;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class PropertyTest {
    private Player p;
    @Before
    public void setUp() throws Exception {
        p=new Player(1);
    }
    @Test
    public void doSomething() throws Exception {
        Square property = new Property("test",200,10);//first test player p buy a property
        p.setStatus(STATUS.MANUAL);
        p.setMoney(500);
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        property.doSomething(p,1);
        assertEquals(((Property)property).getOwner(),p);
        assertEquals(((Property)property).getPossessStatus(),true);
        assertEquals((int)p.getMoney(),300);

        property.doSomething(p,(int)1);//test player p enter to property owned by itself

        Square property2 = new Property("test2",400,10);//test player p can not buy property2
        property2.doSomething(p,1);

        p.setMoney(400);//test player p didn't buy property2
        ByteArrayInputStream in2 = new ByteArrayInputStream("0".getBytes());
        System.setIn(in2);
        property2.doSomething(p,1);
        assertEquals(((Property)property2).getOwner(), null);
        assertEquals(((Property)property2).getPossessStatus(),false);
        assertEquals((int)p.getMoney(),400);

        Player p2 = new Player(2);// test player p2 enter a property owned by p
        p2.setStatus(STATUS.MANUAL);
        property.doSomething(p2,2);

    }

    @Test
    public void getOwner() throws Exception {
        Square property = new Property("test",100,10);
        assertEquals(((Property) property).getOwner(),null);
    }

    @Test
    public void setOwner() throws Exception {
        Square property = new Property("test",100,10);
        ((Property)property).setOwner(p);
        assertEquals(((Property)property).getOwner(), p);
    }

    @Test
    public void printInfo() throws Exception {
        Square square = new Property("test",100,10);
        square.printInfo();
        //nothing to test
    }

    @Test
    public void setPossessStatus() throws Exception {
        Square property = new Property("test",100,10);
        ((Property)property).setPossessStatus(true);
        assertEquals(((Property)property).getPossessStatus(),true);
    }

    @Test
    public void getPossessStatus() throws Exception {
        Square property = new Property("test",100,10);
        assertEquals(((Property)property).getPossessStatus(), false);
    }

}