package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.Go;
import hk.edu.polyu.comp.comp2021.monopoly.Player;
import hk.edu.polyu.comp.comp2021.monopoly.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class GoTest {
    private Square square;
    private Player p;
    @Before
    public void setUp(){
        square = new Go();
        p = new Player(0);
    }
    @Test
    public void doSomething() throws Exception {
        square.doSomething(p,0);
    }

}