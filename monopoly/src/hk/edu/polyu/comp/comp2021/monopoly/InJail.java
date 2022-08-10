package hk.edu.polyu.comp.comp2021.monopoly;

import java.util.Random;
import java.util.Scanner;

/**
 * The class of InJail
 */
public class InJail extends Square{
    private final int fee = 50;

    /**
     * The constructor of InJail
     */
    public InJail(){
        type = SquareType.INJAIL;
        typeName="InJail";
    }
    @Override
    public void doSomething(Player p, int No){
        if(!p.isInJail()){
            System.out.println(p.getName()+" visting the jail.");
        }
        if(p.isInJail()){
            System.out.println("\n"+p.getName()+" have stayed in jail for "+(3 - p.getInjail())+" turn(s).");
            Scanner reader=new Scanner(System.in);
            boolean inputSuccess=false;
            while(!inputSuccess){
                try{
                    String input;
                    if(p.getStatus()== STATUS.AUTO){
                        inputSuccess = true;
                        input = new Random().nextBoolean()?"1":"0";
                    }
                    else{
                        System.out.println("Input 0 to pay "+fee+" HKD to leave the jail, input 1 to row the dice.");
                        input=reader.next();
                    }
                    if(Integer.parseInt(input)==0 && p.getMoney() < fee) {System.out.println("Sorry,you have no enough money for paying.");input = "1";}
                    if(Integer.parseInt(input)==0){
                        inputSuccess = true;
                        System.out.println(p.getName()+" pay "+fee+" HKD and leave the jail.");
                        p.setInjail(0);
                        p.changeMoney(-fee);
                        int []dices =p.RollDice();
                        int step=dices[0]+dices[1];

                    }
                    if(Integer.parseInt(input)==1){
                        inputSuccess = true;
                        int[] dices = p.RollDice();
                        int step=dices[0]+dices[1];
                        if(p.getThisDiceNum()[0] == p.getThisDiceNum()[1]){
                            p.setInjail(0);
                            System.out.println("Lucky, you roll the doubles. ");
                            return;
                        }
                        else if(p.getInjail() == 1) {
                            System.out.println("Sorry, you did not roll doubles. You pay "+fee+" HKD and leave the jail, moving " + step + " steps.");
                            p.changeMoney(-fee);
                            p.setInjail(0);
                            return;
                        }
                        else{
                            p.setInjail(p.getInjail()-1);
                            System.out.println("Sorry, you did not roll doubles, so you have to stay for another "+p.getInjail()+ " turn(s).");
                        }
                        }
                    }
                catch(Exception e){inputSuccess=false;}
            }
        }
    }
}
