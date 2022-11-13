import java.io.*;
/*
 * This class contains the structure for the game.
 * @author James Wallace
 * @version 2022/11.11
 * @author Preston Edwards
 * @version 2022/11.12
 */



public class Game {
    // ~ Fields.......................................................
    private int currentScore;
    private Tweet tweet;
    private Leaderboard board;
    private String playerName;

    // ~ Default Constructor ...........................................................
    /**
     * New game object.
     * @throws IOException
     *
     */
    public Game()  {
        this.currentScore = 0;
        playerName = "";
        tweet = new Tweet();
        try {
            board = new Leaderboard();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    // ~ Methods ...........................................................

    /*
     * When a quesiton is answered correctly, this method adds a point 
     * to the current score and compares it with the high score. The
     * high score is changed if current score is greater than it.
     */
    public void addScore(){
        this.currentScore += 1;
    }

    public Tweet getTweet(){
        return this.tweet;
    }

    public int getScore(){
        return this.currentScore;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public Leaderboard getLeaderboard(){
        return this.board;
    }

    public void setTweet(Tweet tweet){
        this.tweet = tweet;
    }

    public void setScore(int score){
        this.currentScore = score;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }


    /*
     * Resets all the variables after the game ends
     */
    public void resetGame() throws IOException{
        this.board.update(this.currentScore, playerName);
        this.currentScore = 0;
        getNewTweet();
    }

    /*
     * Gets a new list of tweets to use for the game
     */
    public void getNewTweet() throws IOException{
        int randNum = (int) (Math.random() * 4);
        //int randNum = 1;
        String numString = Integer.toString(randNum);
        String filePath = "C:\\Users\\James Wallace\\Documents\\JAVA_HOME\\VTHackathonF22\\GameTweets\\" 
        + numString + "\\gameTweets.txt";

        FileReader in = new FileReader(new File(filePath));

        int tweetNum = (int) (Math.random() * 400);
        //int tweetNum = 0;

        BufferedReader buffRead = new BufferedReader(in);  
        String line;  

        for (int i = 0; i < tweetNum-1; i++)
            buffRead.readLine();
        line = buffRead.readLine();
        in.close();    

        String arr[] = line.split(" ");
    
        this.tweet.setRight(arr[0]);
        this.tweet.setWrong(arr[1]);

        StringBuffer stringBuff = new StringBuffer();
        for (int i = 2; i < arr.length; i++){
            stringBuff.append(arr[i] + " ");
        }
        this.tweet.setContent(stringBuff.toString());
    }
}

