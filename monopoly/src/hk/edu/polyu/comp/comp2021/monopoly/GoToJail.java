package hk.edu.polyu.comp.comp2021.monopoly;

/**
 * The class of GoToJail
 */
public class GoToJail extends Square{
    /**
     * The constructor of GoToJail
     */
    public GoToJail(){
        type = SquareType.GOTOJAIL;
        typeName = "GoToJail";
    }
    @Override
    public void doSomething(Player p, int No){
        System.out.println("Player "+No+" enters the GoToJail Square and go to jail");
        p.setInjail(3);
        p.setLocation(5);
    }
}
