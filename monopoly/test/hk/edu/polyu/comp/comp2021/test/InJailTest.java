package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class InJailTest {
    Player p;
    @Before
    public void setUp(){
        p = new Player(1);
    }
    @Test
    public void doSomething() throws Exception {
        p.setStatus(STATUS.MANUAL);
        Square GoToJail = new GoToJail();
        Square InJail = new InJail();
        // case that the player pay 50HKD and leave
        GoToJail.doSomething(p,1);
        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        InJail.doSomething(p,1);
        assertFalse(p.isInJail());
        assertTrue(p.getMoney()==1450);

        //case that the player roll the dice but not double
        GoToJail.doSomething(p,1);
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        InJail.doSomething(p,1);
        if(p.getThisDiceNum()[0] == p.getThisDiceNum()[1]){
            assertFalse(p.isInJail());
        }
        else {
            assertTrue(p.isInJail());
        }

        //casse that the player roll the dice in the last round
        p.setMoney(1500);
        GoToJail.doSomething(p,1);
        p.setInjail(1);
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        InJail.doSomething(p,1);
        if(p.getThisDiceNum()[0] == p.getThisDiceNum()[1]){
            assertEquals((int)p.getMoney(),1500 );
        }
        else{
            assertEquals((int)p.getMoney(),1450);
        }
        assertFalse(p.isInJail());

        //case the the player is control by AI
        p.setStatus(STATUS.AUTO);
        p.setMoney(1500);
        GoToJail.doSomething(p,1);
        InJail.doSomething(p,1);
        if(p.getThisDiceNum()[0] == p.getThisDiceNum()[1] || p.getMoney() != 1500){
            assertFalse(p.isInJail());
        }
        else{
            assertTrue(p.isInJail());
        }

        //case that the player visit the jail
        p.setInjail(0);
        InJail.doSomething(p,1);

    }

}