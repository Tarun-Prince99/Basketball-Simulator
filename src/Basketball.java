import java.util.ArrayList;
import java.util.HashMap;

public class Basketball{
    
    static BasketballTeam teamOne = new BasketballTeam();
    static BasketballTeam teamTwo = new BasketballTeam();
    static BasketballTeam teamThree = new BasketballTeam();
    static BasketballTeam teamFour = new BasketballTeam();
    static ArrayList<BasketballTeam> tournamentList = new ArrayList<BasketballTeam>();
         
public static void main(String[] args){
    readData();
    startTournament();
//  testOutput();
}

private static void readData(){
    // fix the paths to these if compiling the project on your pc
    teamOne.readFile("C:\\Users\\Tarun\\Documents\\NetBeansProjects\\Basketball\\src\\gsw.txt");
    teamTwo.readFile("C:\\Users\\Tarun\\Documents\\NetBeansProjects\\Basketball\\src\\hou.txt");
    teamThree.readFile("C:\\Users\\Tarun\\Documents\\NetBeansProjects\\Basketball\\src\\cle.txt");
    teamFour.readFile("C:\\Users\\Tarun\\Documents\\NetBeansProjects\\Basketball\\src\\bos.txt");
    tournamentList.add(teamOne);
    tournamentList.add(teamTwo);
    tournamentList.add(teamThree);
    tournamentList.add(teamFour);
}

private static void startTournament(){

    for(int x = tournamentList.size()-1; x >=0; x-=2){
        tournamentList.set(x-1, obtainWinner(tournamentList.get(x), tournamentList.get(x-1)));
    }
    
    System.out.println("FINAL");
    System.out.println("-------------------------------------");
    System.out.println(tournamentList.get(0).getTeamName() + " vs. " + tournamentList.get(2).getTeamName());
    System.out.println("-------------------------------------");
    System.out.println("WINNING TEAM: " + obtainWinner(tournamentList.get(0), tournamentList.get(2)).getTeamName());

}

private static BasketballTeam obtainWinner(BasketballTeam myTeam, BasketballTeam otherTeam){

    int teamOneTotal = 0;
    int teamTwoTotal = 0;
    
    Statistics myStat = new Statistics(myTeam, otherTeam);
    HashMap<String, Integer> finalPoints = myStat.obtainFinalScore();
    for(String name: finalPoints.keySet()){
     //       System.out.println(name + " : " + finalPoints.get(name));
            teamOneTotal+=finalPoints.get(name);
    }

    Statistics myStat2 = new Statistics(otherTeam, myTeam);
    HashMap<String, Integer> finalPoints2 = myStat2.obtainFinalScore();
    for(String name: finalPoints2.keySet()){
    //        System.out.println(name + " : " + finalPoints2.get(name));
            teamTwoTotal+=finalPoints2.get(name);
    }
    
    if(teamOneTotal==teamTwoTotal)
        obtainWinner(myTeam,otherTeam);
    
    BasketballTeam result = (teamOneTotal > teamTwoTotal) ? myTeam : otherTeam;
    System.out.println("Team: " + myTeam.getTeamName() + ": " + teamOneTotal + " vs. " + "Team: " + otherTeam.getTeamName() + ": " + teamTwoTotal);
    return result; 
}

private static void testOutput(){
    System.out.println("---------------------------------------------");
    teamOne.outputData();
    System.out.println("---------------------------------------------");
    teamTwo.outputData();
    System.out.println("---------------------------------------------");     
    teamThree.outputData();
    System.out.println("---------------------------------------------"); 
    teamFour.outputData();
    System.out.println("---------------------------------------------"); 
}


}

