package hk.edu.polyu.comp.comp2021.monopoly;

import java.util.Random;
import java.util.Scanner;

/**
 * The class of Property
 */
public class Property extends Square {
    private String name;
    private int price;
    private int rent;
    private Player owner;
    private boolean PossessStatus;

    /**
     * The constructor of Property
     * @param name the name of the property
     * @param price the price of the property
     * @param rent the rent of the property
     */
    public Property(String name, int price, int rent) {
        this.price = price;
        this.name = name;
        this.rent = rent;
        this.PossessStatus = false;
        type = SquareType.PROPERTY;
        typeName = "Property";
    }

    @Override
    public void doSomething(Player p, int No) {
        System.out.println("Player " + No + " enters " + this.name+".");
        if (!this.PossessStatus  && this.price <= p.getMoney()) {
            if(p.getStatus() == STATUS.MANUAL) {
                Scanner reader = new Scanner(System.in);
                boolean inputSuccess = false;
                while (!inputSuccess) {
                    System.out.println("You can buy this property in " + price + " dollors,input 0 to do nothing or 1 to buy this property.");
                    try {
                        String input = reader.next();
                        if (Integer.parseInt(input) == 0) {
                            System.out.println();
                            inputSuccess = true;
                        }
                        if (Integer.parseInt(input) == 1) {
                            inputSuccess = true;
                            this.owner = p;
                            this.PossessStatus = true;
                            p.changeMoney(-this.price);
                            System.out.println(p.getName() + " buy the " +this.name+".");

                        }
                    } catch (Exception e) {inputSuccess = false;}
                }
            }

            else{
                Random random = new Random();
                if(random.nextBoolean()) {
                    this.owner = p;
                    this.PossessStatus = true;
                    p.changeMoney(-this.price);
                    System.out.println(p.getName() + " buy the " + this.name+" with "+this.price+" dollors.");
                }
            }
        }

        else if(!this.PossessStatus && this.price > p.getMoney()){
            System.out.println("Sorry, you have no enough money to buy this property.");
        }
        else if (this.PossessStatus && this.owner == p) {
            System.out.println("This is your property, you don't need to pay anything.");
        }
        else {
            System.out.println("You have to pay " + this.rent + " dollors to " + this.owner.getName()+".");
            p.changeMoney(-this.rent);
            owner.changeMoney(this.rent);
        }
    }

    /**
     * to get the owner of the property
     * @return return the owner of the Property
     */
    public Player getOwner(){
        return owner;
    }

    /**
     * set the owner of the property to player p
     * @param p the player
     */
    public void setOwner(Player p){
        this.owner = p;
    }

    @Override
    public void printInfo(){
        System.out.println("Type: "+this.typeName + " Name: "+ this.name + " Owner: "+ (this.owner == null?"Null":this.owner.getName()) + " Price: " + this.price + " Rent: " + this.rent+".");
    }

    /**
     * set the possess status
     * @param s the status to be set
     */
    public void setPossessStatus(boolean s){
        PossessStatus = s;
    }

    /**
     * get the status of property
     * @return the status of property
     */
    public boolean getPossessStatus(){
        return PossessStatus;
    }

}
