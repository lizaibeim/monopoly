package hk.edu.polyu.comp.comp2021.monopoly;

/**
 * The class of Square
 */
public abstract class Square{

    /**
     * The type of Square
     */
    protected SquareType type;

    /**
     * The name of the SquareType
     */
    protected String typeName;

    /**
     * return the type of sqaure
     * @return return the type of sqaure
     */
    public  SquareType getType(){return type;}

    /**
     * player will do something when he or she enters a square
     * @param p the player
     * @param No the no of player
     */
    public abstract void doSomething(Player p,int No);

    /**
     * print out the information of square
     */
    public  void printInfo(){System.out.println("Type: " + typeName);}
}

