import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.HashMap;
import java.util.Scanner;

public class BasketballTeam{
    
    HashMap<String, BasketballPlayer> myTeam = new HashMap<String, BasketballPlayer>();
    String teamName;
    
    // reads contents of file
    public void readFile(String fileName){
        File statFile = new File(fileName);
        String name;
        String position;
        double points;
        double assists;
        double rebounds;
        int minutesPerGame;
        int consistency;
        int defensiveRating;
        
        try{
            Scanner scan = new Scanner(statFile);
            teamName = scan.nextLine();
            while(scan.hasNextLine()){
                name = scan.nextLine();
                position = scan.nextLine();
                points = Double.valueOf(scan.nextLine());
                assists = Double.valueOf(scan.nextLine());
                rebounds = Double.valueOf(scan.nextLine());
                consistency = Integer.valueOf(scan.nextLine());
                minutesPerGame = Integer.valueOf(scan.nextLine());
                defensiveRating = Integer.valueOf(scan.nextLine());
                myTeam.put(name, new BasketballPlayer(position, points, assists, rebounds, minutesPerGame, consistency, defensiveRating));
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Could not locate the file.");
        }
        
    }
    
    public String getTeamName(){
        return teamName;
    }
    
    // total rebounds of all players in team
    public int getTotalRebounds(){
        double totalRebounds = 0;
        for(String name: myTeam.keySet()){
            BasketballPlayer player = myTeam.get(name);
            totalRebounds += player.getRebounds();
        }    
        // rounded so we won't have to deal with floats
        return ((int)Math.round(totalRebounds));
    }
    
    // total assists of all players in team
    public int getTotalAssists(){
        double totalAssists = 0;
        for(String name: myTeam.keySet()){
            BasketballPlayer player = myTeam.get(name);
            totalAssists += player.getAssists();
        }    
        // rounded so we won't have to deal with floats
        return ((int)totalAssists);
    }
    
    // the list will always contain 3 elements. the order goes
    // as follows: G, F, and C for the respective positions of
    // each element.
    public int[] getDefensiveRating(){
        int[] defensiveTotal = new int[3];
        int guardTotal = 0;
        int forwardTotal = 0;
        int centerTotal = 0;
        
        for(String name: myTeam.keySet()){
            BasketballPlayer player = myTeam.get(name);
            String position = player.getPosition();
            
            switch(position){
                case "G" :
                    defensiveTotal[0] += player.getDefense();
                    guardTotal++;
                    break;
                case "F" :
                    defensiveTotal[1] += player.getDefense();
                    forwardTotal++;
                    break;
                case "C" :
                    defensiveTotal[2] += player.getDefense();
                    centerTotal++;
                    break;
                default:
                    System.err.println("Incorrect position given.");
            }
        }    
        
        try{
        
            defensiveTotal[0] = defensiveTotal[0]/guardTotal;
            defensiveTotal[1] = defensiveTotal[1]/forwardTotal;
            defensiveTotal[2] = defensiveTotal[2]/centerTotal;
        }
        catch (ArithmeticException e){ System.err.println("Cannot divide by 0.");}
        
        return defensiveTotal;
    }
    
    public HashMap<String, BasketballPlayer> getHash(){
        return myTeam;
    }
  
    // for testing purposes
    public void outputData(){
        for(String name: myTeam.keySet()){
            BasketballPlayer p = myTeam.get(name);
            System.out.println("" + name + " (" + p.getPosition() + ") : " + p.getPoints() + ", " + p.getAssists() + ", " + p.getRebounds()
                    + ", "  + p.getMPG() + ", " + p.getConsistency() + ", " + p.getDefense());
        }
    }
     
}



