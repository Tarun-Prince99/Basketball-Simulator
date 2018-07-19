// STATS TXT FILE FORMAT
// ---------------------
// (TEAM NAME, ONLY OCCURS ONCE AT VERY TOP)
// NAME
// POSITION
// PPG
// APG
// RPG
// CONSISTENCY
// MPG
// DEFENSE
public class BasketballPlayer{
        String position;
        double avgPoints;
        double avgAssists;
        double avgRebounds;
        int minutesPerGame;
        // measured from 1-5, with 5 being the most consistent
        int consistency;
        // measured from 1-5, with 5 being the best at defending
        int defensiveRating;
        
        public BasketballPlayer(String pos, double p, double a, double r, int mpg, int c, int d){
            position = pos;
            avgPoints = p;
            avgAssists = a;
            avgRebounds = r;
            minutesPerGame = mpg;
            consistency = c;   
            defensiveRating = d;
        }
        
        public String getPosition() { return position; }
        public double getPoints(){ return avgPoints; }
        public double getAssists(){ return avgAssists; }
        public double getRebounds(){ return avgRebounds; }
        public int getMPG(){ return minutesPerGame; }
        public int getConsistency(){ return consistency; }
        public int getDefense(){ return defensiveRating; }
        
}