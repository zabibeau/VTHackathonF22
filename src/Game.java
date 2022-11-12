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
    private String correctTeam;
    private String incorrectTeam;
    private Leaderboard board;
    private String playerName;

    // ~ Default Constructor ...........................................................
    /**
     * New game object.
     *
     */
    public Game(){
        this.currentScore = 0;
        playerName = "";
        getNewTweet();
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

    /*
     * Resets all the variables after the game ends
     */
    public void resetGame(){
        this.board.update(this.currentScore, playerName);
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
        Tweet newTweet = null; //randomize a new tweet
        this.tweet.setContent(newTweet.getContent());
        this.tweet.setRight(newTweet.getRight());
        this.tweet.setWrong(newTweet.getWrong());
    }

}

