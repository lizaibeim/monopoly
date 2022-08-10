package hk.edu.polyu.comp.comp2021.monopoly;

/**
 * The class of FreeParking
 */
public class FreeParking extends Square{
    /**
     * The constructor of FreeParking
     */
    public FreeParking(){
        type=SquareType.FREEPARKING;
        typeName="FreeParking";
    }
    @Override
    public void doSomething(Player p, int No){
        System.out.println("Player "+No+" enters Free Parking area.");
    }
}
