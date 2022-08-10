package hk.edu.polyu.comp.comp2021.monopoly;

/**
 * The class of Go
 */
public class Go extends Square{
    /**
     * The constructor of Go
     */
    public Go(){
        type=SquareType.GO;
        typeName="Go";
    }
    @Override
    public void doSomething(Player p, int No){
        System.out.println("Player "+No+" enters the Go Square");
    }
}
