package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.Chance;
import hk.edu.polyu.comp.comp2021.monopoly.FreeParking;
import hk.edu.polyu.comp.comp2021.monopoly.Player;
import hk.edu.polyu.comp.comp2021.monopoly.Square;
import org.junit.Before;
import org.junit.Test;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class FreeParkingTest {
    private Square square;
    private Player p;
    @Before
    public void setUp() throws Exception {
        square = new FreeParking();
        p = new Player(0);
    }
    @Test
    public void doSomething() throws Exception {
        square.doSomething(p,0);
        // nothing to be test
    }

}