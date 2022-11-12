import java.util.ArrayList;

/*
 * This class contains code for a leaderboard
 * @author Preston Edwards
 * @version 2022.11.12
 */


 public class Leaderboard {
    // ~ Fields.......................................................
    private ArrayList<Integer> scores;
    private ArrayList<String> names;



    // ~ Constructor ...........................................................
    /*
     * sets initial values of the arraylists to 0 and "N/A" representing
     * the score and player name
     */
    public Leaderboard(){
        this.scores = new ArrayList<Integer>();
        this.names = new ArrayList<String>();

        for (int i = 0; i < 5; i++){
            this.scores.add(0);
            this.names.add("N/A");
        }
    }


    // ~ Methods ...........................................................

    /*
     * updates the leaderboard, which contains the top 5 scores
     */
    public void update(int score, String name){        
        for (int i = 0; i < this.scores.size(); i++)
            if (score > this.scores.get(i)){
                this.scores.add(i, score);
                this.names.add(i, name);
                break;
            }

        while (this.scores.size() > 5){
            this.scores.remove(scores.size()-1);
            this.names.remove(names.size()-1);
        }
    }

    /*
     * 
     */
    public String toString(){
        String str = "Leaderboard\n";
        for (int i = 0; i < this.scores.size(); i++){
            str += (i+1) + ")\t" + this.names.get(i) + ", score: " + this.scores.get(i) + "\n";
        }
        return str;
    }
 } 