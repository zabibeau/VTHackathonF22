
/*
 * This class contains the Tweet object to be used in the game.
 * @author James Wallace
 * @version 2022.11.11
 */
public class Tweet {
    // ~ Fields........................................................
    private String content;
    private String right;
    private String wrong;

    // ~ Constructor...................................................
    
    /*
     * Constructor for the Tweet class.
     * @param content
     *      The content of the tweet.
     * @param right
     *      The correct answer for the question.
     * @param wrong
     *      The incorrect answer for the question.
     */
    public Tweet(String contentIn, String rightIn, String wrongIn){
        content = contentIn;
        right = rightIn;
        wrong = wrongIn;
    }

    // ~ Methods...........................................................

    /*
     * Getter for the tweets content.
     */

    public String getContent(Tweet in){
        return in.content;
    }

    /*
     * Getter for the tweets correct answer.
     */
    public String getRight(Tweet in){
        return in.right;
    }

    /*
     * Getter for the tweets incorrect answer.
     */
    public String getWrong(Tweet in){
        return in.wrong;
    }


    /*
     * Setter for the tweets content.
     */
    public void setContent(String contentIn, Tweet tweet){
        tweet.content = contentIn;
    }

    /*
     * Setter for the tweets correct answer.
     */
    public void setRight(String rightIn, Tweet tweet){
        tweet.right = rightIn;
    }

    /*
     * Setter for the tweets incorrect answer.
     */
    public void setWrong(String wrongIn, Tweet tweet){
        tweet.wrong = wrongIn;
    }




}
