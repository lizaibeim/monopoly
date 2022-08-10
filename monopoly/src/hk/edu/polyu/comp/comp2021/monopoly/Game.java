package hk.edu.polyu.comp.comp2021.monopoly;

import java.util.*;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

/**
 * the interface of game
 */
public class Game{
    private static final int maxRound = 99;
    private  final int roundMoney = 1500;
    private int totalPlayerNum;
    private int rounds;
    private Player[] players;
    private Square[] squares;


    /**
     * this is for the test
     */
    private String testflag;

    /**
     * constructor of game
     */
    public Game(){
        totalPlayerNum=0;
        rounds=0;
    }

    /**
     * init squares;
     */
    public void initSquares() {
        try {
            String pathname = "./PropertySource.txt";
            File filename = new File(pathname);
            InputStreamReader reader1 = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader1);

            squares = new Square[Integer.parseInt(br.readLine())];
            int positionIndex = 0;

            squares[positionIndex++] = new Go();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new IncomeTax();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new InJail();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new Chance();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new FreeParking();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new Chance();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new GoToJail();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
            squares[positionIndex++] = new Chance();
            squares[positionIndex++] = new Property(br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
        }
         catch(Exception e){System.out.printf(e.getLocalizedMessage());}
    }
		/**
		 * init players
		 */
    public void initPlayers(){
        System.out.println("Please input the number of players.");
        boolean inputSuccess=false;
        Scanner reader=new Scanner(System.in);
        while(!inputSuccess){
            try{
                totalPlayerNum=Integer.parseInt(reader.next());
            }
            catch (RuntimeException e){
                System.out.println("Sorry, wrong input,Please input the number of players [2,6].");
                continue;
            }
            if(totalPlayerNum>1 && totalPlayerNum<=6)inputSuccess = true;
            else System.out.println("Sorry, wrong input,Please input the number of players [2,6].");
        }
        players=new Player[totalPlayerNum];
        for(int i=0;i<totalPlayerNum;i++){
            System.out.println("Is player "+i+" controlled by human or computer?");
            inputSuccess = false;
            while(!inputSuccess){
                System.out.println("Input 0 to make it controlled by human, input 1 to make it controlled by computer.");
                try{
                    String input=reader.next();
                    if(Integer.parseInt(input)==0){
                        inputSuccess=true;
                        players[i]=new Player(i);
                        players[i].setStatus(STATUS.MANUAL);
                    }
                    if(Integer.parseInt(input)==1){
                        inputSuccess=true;
                        players[i]=new Player(i);
                        players[i].setStatus(STATUS.AUTO);
                    }
                }catch(Exception e){
                    inputSuccess=false;
                }
            }
        }
    }

    /**
     * to get test flag
     * @return testflag
     */
    public String getTestflag(){
        return testflag;
    }

    /**
     * the continue command
     * @param p the player
     * @param No the no of player
     */
    public void ContinueCommand(Player p,int No){
        if(p.isInJail()){
            squares[p.getLocation()].doSomething(p,No);
            if (p.getMoney() < 0){System.out.println(p.getName() +"have no money!"); RetireCommand(p,No);return;}
            if(!p.isInJail()){
                int step = p.getThisDiceNum()[0]+p.getThisDiceNum()[1];
                System.out.println("\nPlayer " + No + " moves " + step + " step forward and reach Square. " + p.getLocation());
                squares[p.getLocation()].doSomething(p, No);
            }

            return;
        }

        int[] dices=p.RollDice();
        int step = dices[0]+dices[1];
        boolean hasFinishOneRound = p.move(step);
        System.out.println("\nPlayer " + No + " moves " + step + " step forward and reach Square " + p.getLocation()+".");
        if (hasFinishOneRound) {//the player has pass the GO square
            p.changeMoney(roundMoney);
        }
        if (p.getStatus() != STATUS.RETIRED) {
            testflag="do something";
            squares[p.getLocation()].doSomething(p, No);
        }
        if (p.getMoney() < 0){System.out.println(p.getName() +"have no money!"); RetireCommand(p,No);}
    }

    /**
     * the auto command
     * @param p the player
     * @param No the no of player
     */
    public void AutoCommand(Player p,int No){
        p.setStatus(STATUS.AUTO);
        System.out.println("Player "+No+" is in auto mode now.");
        ContinueCommand(p,No);
    }
    /**
     * the retire command
     * @param p the player
     * @param No the no of player
     */
    public void RetireCommand(Player p,int No){
        p.getRetired();
        System.out.println("Player "+No+" leaves the game.");
        for (Square cur : squares) {
            if (cur instanceof Property && ((Property) cur).getOwner() == p) {
                ((Property) cur).setOwner(null);
                ((Property) cur).setPossessStatus(false);
            }
        }
    }
    /**
     * the report command
     * @param p the player
     * @param No the no of player
     */
    public void ReportCommand(Player p,int No){
        for(int i=0;i<squares.length;i++){
            System.out.print("Square "+i+": ");
            squares[i].printInfo();
        }
        for(int i=0;i<totalPlayerNum;i++){
            if(players[i].getStatus()==STATUS.RETIRED){
                System.out.print("Player "+i+" has retired.");
            }
            else {
                if(players[i].getStatus()==STATUS.AUTO){
                    System.out.print("Player "+i+" is being controlled by computer. ");
                }
                if(players[i].getStatus()==STATUS.MANUAL){
                    System.out.print("Player "+i+" is being controlled by human. ");
                }
                System.out.print("Location: "+players[i].getLocation());
                System.out.println(" Money: "+players[i].getMoney());
            }
        }
        askforCommand(p,No);
    }
    /**
     * ask a command from player
     * @param p the player
     * @param No the no of player
     */
    public void askforCommand(Player p,int No){
        System.out.println("Player "+No+": ");
        boolean successInput=false;
        Scanner reader=new Scanner(System.in);
        String command;
        while(!successInput){
            try{
                System.out.println("Pleaser input your command. You can only input Continue or Report or Auto or Retire");
                command=reader.next();
                switch(command){
                    case "Continue":ContinueCommand(p,No);successInput=true;testflag="ContinueCommand";break;
                    case "Auto":AutoCommand(p,No);successInput=true;testflag="AutoCommand";break;
                    case "Report":ReportCommand(p,No);successInput=true;testflag="ReportCommand";break;
                    case "Retire":RetireCommand(p,No);successInput=true;testflag="RetireCommand";break;
                }
            }catch(Exception e){
                successInput=false;
            }
        }
    }

    /**
     * the main process of game
     * @return the winner no
     */
    public boolean[] runGame(){
        boolean[] winner;
        winner = new boolean[players.length];
        while(true){
            if(rounds>maxRound){
                double maxMoney=-1;
                for(int i=0;i<totalPlayerNum;i++){
                    if(players[i].getStatus()!=STATUS.RETIRED){
                        if(players[i].getMoney()>maxMoney){
                            maxMoney=players[i].getMoney();
                        }
                    }
                }
                for(int i=0;i<totalPlayerNum;i++){
                    if(players[i].getStatus()!=STATUS.RETIRED){
                        if(players[i].getMoney()>=maxMoney){
                            winner[i] = true;
                        }
                    }
                }
                return winner;
            }
            if(Player.getAlivePlayerNum()==1){
                int i;
                for(i=0;i<totalPlayerNum;i++){
                    if(players[i].getStatus()!=STATUS.RETIRED){
                        winner[i] = true;
                        break;
                    }
                }
                return winner;
            }

            System.out.println("\nRounds "+rounds+":");
            rounds++;
            for(int i=0;i<totalPlayerNum;i++){
                if(players[i].getStatus()==STATUS.AUTO){
                    ContinueCommand(players[i],i);
                }
                else if(players[i].getStatus()==STATUS.MANUAL){
                    askforCommand(players[i],i);
                }
            }
        }
    }

    /**
     * the main function of game
     * @param args args
     */
    public static void main(String[] args){
        Game game=new Game();
        System.out.println("This is a template for the Monopoly project.");
        System.out.println("Welcome to the Monopoly Game!");
        game.initSquares();
        game.initPlayers();
        boolean[] winner=game.runGame();
        System.out.println("Game Over!");
        for(int i = 0 ; i <winner.length; i++){
            if(winner[i])
                System.out.println("Player "+i+" win!");
        }
    }

    /**
     * to get total player num
     * @return the total player num
     */
    public int getTotalPlayerNum() {
        // TODO Auto-generated method stub
        return totalPlayerNum;
    }

    /**
     * to get rounds
     * @return the number rounds
     */
    public int getRounds(){
        return rounds;
    }

    /**
     * to set rounds
     * @param r rounds to be set
     */
    public void setRounds(int r){
        rounds=r;
    }

    /**
     * to get the players
     * @return the players
     */
    public Player[] getPlayers(){
        return players;
    }

    /**
     * to get squares
     * @return the squares
     */
    public Square[] getSquares(){
        return squares;
    }



}
