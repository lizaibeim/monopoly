package hk.edu.polyu.comp.comp2021.monopoly;

/**
 *The class of Chance
 */
public class Chance extends Square{
    private final int x = 51, y = 30;

    /**
     * The constructor of Chance
     */
    public Chance(){
        type=SquareType.CHANCE;
        typeName="Chance";
    }


    /**
     *Player will have chance to gain or lose money
     * @param p the player
     * @param No the no of player
     */
    @Override
    public void doSomething(Player p, int No){
        int randNum=(int)(Math.random()*x)-y;
        p.changeMoney(randNum*10);
        System.out.println(p.getName()+" enters Chance Square and"+ (randNum < 0 ?("loses "+ -randNum*10):("gains "+randNum*10)) +" HKD. Now he has "+p.getMoney()+" HKD.");
    }

}
