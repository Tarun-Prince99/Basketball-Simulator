import java.util.HashMap;

public class Statistics{
    
    BasketballTeam myTeam;
    BasketballTeam otherTeam;
  
 
public Statistics (BasketballTeam m, BasketballTeam o){

    myTeam = m;
    otherTeam = o;

}

public HashMap<String, Integer> obtainFinalScore(){
    
    HashMap<String, BasketballPlayer> currentTeam = myTeam.getHash();
    HashMap<String, Integer> finalTeam = new HashMap<String, Integer>();
    
    for(String name: currentTeam.keySet()){
            BasketballPlayer p = currentTeam.get(name);
            int[] defensiveRating = otherTeam.getDefensiveRating();
            int initialTotal;
            // compares players to players on other team relative to position played
            if(p.getPosition().equalsIgnoreCase("G")){
                initialTotal = getScore(p.getConsistency(), defensiveRating[0], p);
            }
            else if(p.getPosition().equalsIgnoreCase("F")){
                initialTotal = getScore(p.getConsistency(), defensiveRating[1], p);
            }
            else {
                initialTotal = getScore(p.getConsistency(), defensiveRating[2], p);
            }
            
            finalTeam.put(name, initialTotal);
        }

    return finalTeam;
}

// based on confidence interval formula
// mean +|- z-score(standard deviation / sqrt(sample size))
// adapted to
// expected points * ((expected consistency + expected defense) + (factor +|- greater value in assists/rebounds))
private int getScore(int consistency, int defense, BasketballPlayer player){
    final double ASSIST_ADVANTAGE = 0.15;
    final double REBOUND_ADVANTAGE = 0.10;
    double variability = 0;
    double MAX_VARIABILITY = .80;
    double MIN_VARIABILITY = 0.40;
    
    if(returnAssistDifference()>0)
        MAX_VARIABILITY+=ASSIST_ADVANTAGE;
    
    if(returnReboundsDifference()>0)
        MIN_VARIABILITY-=REBOUND_ADVANTAGE;
    
    int totalCombined = consistency + defense;
    double finalBound = 1/totalCombined;
    int expectedPoints = (int) Math.round(player.getPoints());
    // use this to average in multiple tests in order to make results more reliable (less anomalies)
    for(int x = 0; x < 5; x++){
        variability += (Math.random()*(((finalBound + MAX_VARIABILITY) + 1 - (finalBound - MIN_VARIABILITY)))) + (finalBound - MIN_VARIABILITY);
    }
    variability/=5;
    int result = (int) Math.round(variability*expectedPoints);
    return result;
}

// adjusts score based on total assists differences
private int returnAssistDifference(){
    return myTeam.getTotalAssists() - otherTeam.getTotalAssists();
}

// adjusts score based on total rebounds differences
private int returnReboundsDifference(){
    return myTeam.getTotalRebounds() - otherTeam.getTotalRebounds();
}

}