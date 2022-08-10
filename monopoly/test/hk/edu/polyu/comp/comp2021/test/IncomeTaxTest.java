package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.IncomeTax;
import hk.edu.polyu.comp.comp2021.monopoly.Player;
import hk.edu.polyu.comp.comp2021.monopoly.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class IncomeTaxTest {
    private Square square;
    private Player p;
    @Before
    public void setUp() throws Exception {
        square = new IncomeTax();
        p = new Player(0);
    }
    @Test
    public void doSomething() throws Exception {
        double money = p.getMoney();
        double remain = money*(0.9);
        square.doSomething(p,0);
        assertEquals((int)p.getMoney(),(int)remain);
    }

}