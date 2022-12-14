
/*
 * This class contains the Tweet object to be used in the game.
 * @author James Wallace
 * @version 2022.11.11
 * @author Preston Edwards
 * @version 2022.11.12
 */
public class Tweet {
    // ~ Fields........................................................
    private String content;
    private String right;
    private String wrong;

    // ~ Default Constructor...................................................
    
    /*
     * Default Constructor for the Tweet class.
     * 
     */
    public Tweet(){
        this.content = "";
        this.right = "";
        this.wrong = "";
    }

    // ~ Methods...........................................................

    /*
     * Getter for the tweets content.
     */

    public String getContent(){
        return this.content;
    }

    /*
     * Getter for the tweets correct answer.
     */
    public String getRight(){
        return this.right;
    }

    /*
     * Getter for the tweets incorrect answer.
     */
    public String getWrong(){
        return this.wrong;
    }

    /*
     * Setter for the tweets content.
     */
    public void setContent(String contentIn){
        this.content = contentIn;
    }

    /*
     * Setter for the tweets correct answer.
     */
    public void setRight(String rightIn){
        this.right = rightIn;
    }

    /*
     * Setter for the tweets incorrect answer.
     */
    public void setWrong(String wrongIn){
        this.wrong = wrongIn;
    }

    /*
     * toString method
     */
    public String toString(){
        String str = "The content of the tweet is: " + this.content;
        str += "\nThe correct team is: " + this.right;
        str += "\nThe incorrect team is: " + this.wrong;

        return str;
    }

}
