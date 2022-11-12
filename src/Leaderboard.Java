import java.io.*;
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
    public Leaderboard() throws IOException{
        this.scores = new ArrayList<Integer>();
        this.names = new ArrayList<String>();

        String filePath = "C:\\Users\\17038\\VSCode\\Java\\VTHackathonF22\\src\\Leaderboard.txt";

        FileReader in = new FileReader(new File(filePath));

        BufferedReader buffRead = new BufferedReader(in);  
        String line;  

        String[] arr;

        for (int i = 0; i < 5; i++){
            line = buffRead.readLine();
            arr = line.split(" ");
            this.scores.add(Integer.valueOf(arr[1]));
            this.names.add(arr[0]);
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