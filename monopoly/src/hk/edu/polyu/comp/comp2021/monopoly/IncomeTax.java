package hk.edu.polyu.comp.comp2021.monopoly;

/**
 * The class of IncomeTax
 */
public class IncomeTax extends Square{
    private final double rate = 0.1;

    /**
     * The constructor of IncomeTax
     */
    public IncomeTax(){
        type = SquareType.INCOMETAX;
        typeName = "IncomeTax";

    }

    /**
     * deduct player's money to pay tax
     * @param p the player
     * @param No the no of player
     */

    @Override
    public void doSomething(Player p, int No) {
        System.out.println("You have enter the tax area, you need to pay 10% tax");
        int tax = (int)(p.getMoney()*rate);
        p.changeMoney(-tax);
    }
}
