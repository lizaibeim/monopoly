package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.Player;
import hk.edu.polyu.comp.comp2021.monopoly.STATUS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class PlayerTest {
    Player p;

    @Before
    public void setUp() {
        p = new Player(1);
    }

    @After
    public void tearDown() {
        p.setAlivePlayerNum(0);
    }

    @Test
    public void setAlivePlayerNum() throws Exception {
        p.setAlivePlayerNum(2);
        assertEquals(p.getAlivePlayerNum(), 2);
    }

    @Test
    public void getAlivePlayerNum() throws Exception {
        assertEquals(p.getAlivePlayerNum(), 1);
    }

    @Test
    public void rollDice() throws Exception {
        int[] dices = p.RollDice();
        assertTrue(dices[0] > 0 && dices[0] < 5 && dices[1] > 0 && dices[1] < 5);
    }

    @Test
    public void getThisDiceNum() throws Exception {
        int[] dices = p.RollDice();
        assertEquals(p.getThisDiceNum(), dices);
    }

    @Test
    public void getName() throws Exception {
        assertEquals(p.getName(), "Player 1 ");
    }

    @Test
    public void getMoney() throws Exception {
        assertTrue(p.getMoney() == 1500);
    }

    @Test
    public void getNo() throws Exception {
        assertEquals(p.getNo(), 1);
    }

    @Test
    public void getLocation() throws Exception {
        assertEquals(p.getLocation(), 0);
    }

    @Test
    public void getStatus() throws Exception {
        assertEquals(p.getStatus(), STATUS.AUTO);
    }

    @Test
    public void isInJail() throws Exception {
        assertEquals(p.isInJail(), false);
    }

    @Test
    public void changeMoney() throws Exception {
        p.changeMoney(50);
        assertTrue(p.getMoney() == 1550);
    }

    @Test
    public void setMoney() throws Exception {
        p.setMoney(2);
        assertTrue(p.getMoney() == 2);
    }

    @Test
    public void setLocation() throws Exception {
        p.setLocation(3);
        assertEquals(p.getLocation(), 3);
    }

    @Test
    public void setStatus() throws Exception {
        p.setStatus(STATUS.MANUAL);
        assertEquals(p.getStatus(), STATUS.MANUAL);
    }

    @Test
    public void setInjail() throws Exception {
        p.setInjail(3);
        assertEquals(p.getInjail(), 3);
    }

    @Test
    public void getInjail() throws Exception {
        assertEquals(p.getInjail(), 0);
    }

    @Test
    public void getRetired() throws Exception {
        p.getRetired();
        assertEquals(p.getStatus(), STATUS.RETIRED);
    }

    @Test
    public void move() throws Exception {
        p.move(2);
        assertEquals(p.getLocation(), 2);
        p.setLocation(18);
        p.move(3);
        assertEquals(p.getLocation(), 1);
    }

}