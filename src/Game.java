import java.util.ArrayList;

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
    private int highScore;
    private Tweet tweet;
    private String correctTeam;
    private String incorrectTeam;

    // ~ Constructor ...........................................................
    /**
     * New game object.
     *
     * @param score
     *            Current score of player
     * @param hScore
     *            High score of game
     * @param tweets
     *            ArrayList of tweets
     * @param over
     *            Boolean representing the ending of the game
     */
    public Game(int score, int hScore, Tweet twee, String right, String wrong){
        this.currentScore = score;
        this.highScore = hScore;
        this.tweet = twee;
        this.correctTeam = right;
        this.incorrectTeam = wrong;
    }

    // ~ Methods ...........................................................

    /*
     * When a quesiton is answered correctly, this method adds a point 
     * to the current score and compares it with the high score. The
     * high score is changed if current score is greater than it.
     */
    public void addScore(){
        this.currentScore += 1;
        if (this.currentScore > this.highScore)
            this.highScore = this.currentScore;
    }

    /*
     * Resets all the variables after the game ends
     */
    public void resetGame(){
        this.currentScore = 0;
        getNewTeams();
    }

    /*
     * Gets a new incorrect and correct team to use for the game
     */
    public void getNewTeams(){
        correctTeam = "";
        incorrectTeam = "";
    }

    /*
     * Gets a new list of tweets to use for the game
     */
    public void getNewTweet(){
        Tweet newTweet = null;
        //newTweet = new Tweet(String contentIn, String rightIn, String wrongIn);
        this.tweet.setContent(newTweet.getContent());
    }
}

