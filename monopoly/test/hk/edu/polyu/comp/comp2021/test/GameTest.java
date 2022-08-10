package hk.edu.polyu.comp.comp2021.test;

import hk.edu.polyu.comp.comp2021.monopoly.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

/**
 * Created by Vennwen on 2016/12/4.
 */
public class GameTest {
    private Game game;
    private Player p;
    @Before
    public void setUp() throws Exception {
        game=new Game();
        p=new Player(1);
    }

    @Test
    public void initSquares() throws Exception {
        game.initSquares();
        assertEquals(game.getSquares().length,20);
        Assert.assertEquals(game.getSquares()[0].getType(), SquareType.GO);
        assertEquals(game.getSquares()[1].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[2].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[3].getType(),SquareType.INCOMETAX);
        assertEquals(game.getSquares()[4].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[5].getType(),SquareType.INJAIL);
        assertEquals(game.getSquares()[6].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[7].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[8].getType(),SquareType.CHANCE);
        assertEquals(game.getSquares()[9].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[10].getType(),SquareType.FREEPARKING);
        assertEquals(game.getSquares()[11].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[12].getType(),SquareType.CHANCE);
        assertEquals(game.getSquares()[13].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[14].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[15].getType(),SquareType.GOTOJAIL);
        assertEquals(game.getSquares()[16].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[17].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[18].getType(),SquareType.CHANCE);
        assertEquals(game.getSquares()[19].getType(),SquareType.PROPERTY);
    }

    @Test(timeout=1000)
    public void initPlayers() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("3 errorinput 0 1 1".getBytes());
        System.setIn(in);
        game.initPlayers();
        assertEquals(game.getPlayers().length,3);
        Assert.assertEquals(game.getPlayers()[0].getStatus(), STATUS.MANUAL);
        assertEquals(game.getPlayers()[1].getStatus(),STATUS.AUTO);
        assertEquals(game.getPlayers()[2].getStatus(),STATUS.AUTO);
        System.setIn(System.in);
    }

    @Test
    public void getTestflag() throws Exception {
        assertEquals(game.getTestflag(),null);
    }

    @Test
    public void continueCommand() throws Exception {
        game.initSquares();
        p.setInjail(2);//put the player in jail
        p.setLocation(5);
        p.setStatus(STATUS.MANUAL);
        p.setMoney(50);
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        game.ContinueCommand(p,1);
        System.setIn(System.in);
        System.out.println(p.getLocation());
        if(p.getThisDiceNum()[0] != p.getThisDiceNum()[1]){
            assertEquals(p.getLocation(),5);//test whether the player is still in jail
        }
        p.setInjail(0);
        p.setLocation(3);
        p.setStatus(STATUS.AUTO);
        game.ContinueCommand(p, 1);
        assertEquals(p.getLocation(),3+p.getThisDiceNum()[0]+p.getThisDiceNum()[1]);
        assertEquals(game.getTestflag(),"do something");
        p.setLocation(2);
        p.setMoney(-1000);
        game.ContinueCommand(p,1);
        assertEquals(p.getLocation(), (2+p.getThisDiceNum()[0]+p.getThisDiceNum()[1])%20);
        assertEquals(STATUS.RETIRED,p.getStatus());
    }

    @Test
    public void autoCommand() throws Exception {
        game.initSquares();
        p.setLocation(6);
        p.setMoney(10000);//make sure that this player will not retire
        p.setStatus(STATUS.MANUAL);
        game.AutoCommand(p,1);
        assertEquals(p.getStatus(),STATUS.AUTO);
    }

    @Test
    public void retireCommand() throws Exception {
        game.initSquares();
        p.setStatus(STATUS.AUTO);
        ((Property)game.getSquares()[1]).setOwner(p);
        game.RetireCommand(p,1);
        assertEquals(p.getStatus(),STATUS.RETIRED);
        assertNull(((Property)game.getSquares()[1]).getOwner());
    }

    @Test
    public void reportCommand() throws Exception {
        game.initSquares();
        ByteArrayInputStream in = new ByteArrayInputStream("4 1 0 1 1".getBytes());
        System.setIn(in);
        game.initPlayers();
        game.RetireCommand(game.getPlayers()[0],0);
        in = new ByteArrayInputStream("Retire".getBytes());
        System.setIn(in);
        game.ReportCommand(p,1);

        //seems that nothing needs to be tested...
    }

    @Test(timeout = 1000)
    public void askforCommand() throws Exception {
        game.initSquares();
        ByteArrayInputStream in = new ByteArrayInputStream("c con continue Continue".getBytes());
        System.setIn(in);
        p.setStatus(STATUS.AUTO);
        p.setLocation(6);
        p.setMoney(10000);
        game.askforCommand(p,1);
        assertEquals(game.getTestflag(),"ContinueCommand");

        p.setStatus(STATUS.MANUAL);
        in = new ByteArrayInputStream("a aa Auto".getBytes());
        System.setIn(in);
        game.askforCommand(p,1);
        assertEquals(game.getTestflag(),"AutoCommand");

        in = new ByteArrayInputStream("wef Report/n Retire".getBytes());
        System.setIn(in);
        game.askforCommand(p,1);
        assertEquals(game.getTestflag(),"RetireCommand");//Report first and then retire, so the correct string is retirecommand.

        System.setIn(System.in);
    }

    @Test
    public void runGame() throws Exception {
        Player.setAlivePlayerNum(0);
        game.setRounds(101);
        ByteArrayInputStream in = new ByteArrayInputStream("3 1 1 1".getBytes());
        System.setIn(in);
        game.initPlayers();
        game.initSquares();
        //case that more than 101 round and  ties
        game.getPlayers()[0].setMoney(10000);
        game.getPlayers()[1].setMoney(30000);
        game.getPlayers()[2].setMoney(30000);
        assertEquals(game.runGame()[0], false);
        assertEquals(game.runGame()[1], true);
        assertEquals(game.runGame()[2], true);

        //case that only one player left
        game.setRounds(30);
        Player.setAlivePlayerNum(3);
        game.getPlayers()[1].getRetired();
        game.getPlayers()[2].getRetired();
        assertEquals(game.runGame()[0], true);
        assertEquals(game.runGame()[1], false);
        assertEquals(game.runGame()[2], false);

        game=new Game();
        in = new ByteArrayInputStream("2 1 1".getBytes());
        System.setIn(in);
        game.initPlayers();
        game.initSquares();
        Player.setAlivePlayerNum(2);
        game.getPlayers()[0].setMoney(10000);
        game.getPlayers()[1].setMoney(-100);
        game.setRounds(30);
        assertEquals(game.runGame()[0], true);
        assertEquals(game.runGame()[1], false);
        System.setIn(System.in);
    }

    @Test(timeout = 1000)
    public void main() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("2 1 1".getBytes());
        System.setIn(in);
        game.main(null);
        // seems nothing to be test
    }

    @Test
    public void getTotalPlayerNum() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("3 1 1 1".getBytes());
        System.setIn(in);
        game.initPlayers();
        assertEquals(3,game.getTotalPlayerNum());
    }

    @Test
    public void getRounds() throws Exception {
        assertEquals(game.getRounds(),0);
    }

    @Test
    public void setRounds() throws Exception {
        game.setRounds(2);
        assertEquals(game.getRounds(),2);
    }

    @Test
    public void getPlayers() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("3 errorinput 0 1 1".getBytes());
        System.setIn(in);
        game.initPlayers();
        assertEquals(game.getPlayers().length,3);
        assertEquals(game.getPlayers()[0].getStatus(), STATUS.MANUAL);
        assertEquals(game.getPlayers()[1].getStatus(),STATUS.AUTO);
        assertEquals(game.getPlayers()[2].getStatus(),STATUS.AUTO);
    }

    @Test
    public void getSquares() throws Exception {
        game.initSquares();
        assertEquals(game.getSquares().length,20);
        assertEquals(game.getSquares()[0].getType(), SquareType.GO);
        assertEquals(game.getSquares()[1].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[2].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[3].getType(),SquareType.INCOMETAX);
        assertEquals(game.getSquares()[4].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[5].getType(),SquareType.INJAIL);
        assertEquals(game.getSquares()[6].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[7].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[8].getType(),SquareType.CHANCE);
        assertEquals(game.getSquares()[9].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[10].getType(),SquareType.FREEPARKING);
        assertEquals(game.getSquares()[11].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[12].getType(),SquareType.CHANCE);
        assertEquals(game.getSquares()[13].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[14].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[15].getType(),SquareType.GOTOJAIL);
        assertEquals(game.getSquares()[16].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[17].getType(),SquareType.PROPERTY);
        assertEquals(game.getSquares()[18].getType(),SquareType.CHANCE);
        assertEquals(game.getSquares()[19].getType(),SquareType.PROPERTY);
    }

}