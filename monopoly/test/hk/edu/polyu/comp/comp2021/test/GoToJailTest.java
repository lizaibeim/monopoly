package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.GoToJail;
import hk.edu.polyu.comp.comp2021.monopoly.Player;
import hk.edu.polyu.comp.comp2021.monopoly.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class GoToJailTest {
    private Square square;
    private Player p;
    @Before
    public void setUp() throws Exception {
        square = new GoToJail();
        p = new Player(0);
    }
    @Test
    public void doSomething() throws Exception {
        Square GoToJail = new GoToJail();
        GoToJail.doSomething(p,1);
        assertEquals(p.getLocation(),5);
        assertEquals(p.getInjail(),3);
    }

}