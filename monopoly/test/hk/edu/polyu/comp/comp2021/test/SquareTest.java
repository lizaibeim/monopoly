package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.Go;
import hk.edu.polyu.comp.comp2021.monopoly.Player;
import hk.edu.polyu.comp.comp2021.monopoly.Square;
import hk.edu.polyu.comp.comp2021.monopoly.SquareType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class SquareTest {
    private Square square;
    private Player p;
    @Before
    public void setUp() throws Exception {
        square = new Go();//square is abstract so using go as a sample here
        p=new Player(1);
    }
    @Test
    public void getType() throws Exception {
        Assert.assertEquals(square.getType(), SquareType.GO);
    }

    @Test
    public void doSomething() throws Exception {
        square.doSomething(p,1);
        // nothing to be test
    }

    @Test
    public void printInfo() throws Exception {
        square.printInfo();
        // nothing to be test
    }

}