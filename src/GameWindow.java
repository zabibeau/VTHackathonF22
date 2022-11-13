import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
/*
 * This class contains the GUI for the project.
 */

public class GameWindow {
    
    static JButton one = new JButton();
    static JButton two = new JButton();
    static Game local = new Game();
    static JFrame localWindow = new JFrame();
    

    public static void newWindow(Game in) throws IOException{
        JFrame window = new JFrame("Rivals");
        window = localWindow;
        local = in;
        window.getContentPane().setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        in.getNewTweet();
        JLayeredPane beans = new JLayeredPane(); 
        beans.setPreferredSize(dimension);
        beans.setBackground(new Color(36, 52, 71));
        beans.setBounds(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
        beans.setForeground(Color.BLACK);
        
        
        JTextArea tweetLabel = new JTextArea();
        tweetLabel.setOpaque(true);
        tweetLabel.setBackground(new Color(8, 146, 208));
        Dimension tweetDimension = new Dimension();
        tweetDimension.setSize(dimension.getWidth()/3*2, dimension.getHeight()/4);
        tweetLabel.setPreferredSize(tweetDimension);
        tweetLabel.setBounds((int)dimension.getWidth()/10, (int)tweetDimension.getHeight(), (int)tweetDimension.getWidth(), (int)tweetDimension.getHeight());;
        tweetLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        tweetLabel.setLineWrap(true);
        tweetLabel.setText(in.getTweet().getContent());
        tweetLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        JTextArea scoreBlock = new JTextArea();
        scoreBlock.setOpaque(true);
        scoreBlock.setBackground(new Color(8, 146, 208));
        Dimension scoreDimension = new Dimension();
        scoreDimension.setSize(dimension.getWidth()/5, dimension.getHeight()/5);
        scoreBlock.setPreferredSize(scoreDimension);

        
        beans.add(tweetLabel);
        
        int rand = (int)(Math.random() * 100);
        if (rand >= 50){
            one.setText(in.getTweet().getRight());
            two.setText(in.getTweet().getWrong());
        }
        else{
            two.setText(in.getTweet().getRight());
            one.setText(in.getTweet().getWrong());
        }

        addListeners();

        one.setBorderPainted(true);
        two.setBorderPainted(true);
        one.setPreferredSize(new Dimension((int)tweetDimension.getWidth()/3, (int)tweetDimension.getHeight()/3*2));
        two.setPreferredSize(new Dimension((int)tweetDimension.getWidth()/3, (int)tweetDimension.getHeight()/3*2));
        one.setFont(new Font("Arial", Font.PLAIN, 40));
        two.setFont(new Font("Arial", Font.PLAIN, 40));
        

        
        JPanel east = new JPanel( new BorderLayout());
        JPanel buttons = new JPanel( new GridLayout(1,1));
        east.add(buttons, BorderLayout.EAST);
        buttons.add(one);
        buttons.add(two);
        window.add(buttons, BorderLayout.SOUTH);

        window.getContentPane().add(beans, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }
    

    public static void addListeners(){
        one.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    onePressed();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                }
            });
    
        two.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 try {
                    twoPressed();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                }
            });
    }

    public static void onePressed() throws IOException{
        if (one.getText().equals(local.getTweet().getRight())){
            update();
            
        }
        else if(one.getText().equals(local.getTweet().getWrong())){
            System.out.println(local.getLeaderboard());
            localWindow.dispatchEvent(new WindowEvent(localWindow, WindowEvent.WINDOW_CLOSING));
            
        }
    }

    public static void twoPressed() throws IOException{
        if (two.getText().equals(local.getTweet().getRight())){
            update();
            
        }
        else if(two.getText().equals(local.getTweet().getWrong())){
            System.out.println(local.getLeaderboard());
            localWindow.dispatchEvent(new WindowEvent(localWindow, WindowEvent.WINDOW_CLOSING));
            
        }
    }

    public static void update() throws IOException{
        local.addScore();
        local.getNewTweet();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        JLayeredPane beans = new JLayeredPane(); 
        beans.setPreferredSize(dimension);
        beans.setBackground(new Color(36, 52, 71));
        beans.setBounds(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
        beans.setForeground(Color.BLACK);

        JTextArea tweetLabel = new JTextArea();
        tweetLabel.setOpaque(true);
        tweetLabel.setBackground(new Color(8, 146, 208));
        Dimension tweetDimension = new Dimension();
        tweetDimension.setSize(dimension.getWidth()/3*2, dimension.getHeight()/4);
        tweetLabel.setPreferredSize(tweetDimension);
        tweetLabel.setBounds((int)dimension.getWidth()/10, (int)tweetDimension.getHeight(), (int)tweetDimension.getWidth(), (int)tweetDimension.getHeight());;
        tweetLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        tweetLabel.setLineWrap(true);
        tweetLabel.setText(local.getTweet().getContent());
        tweetLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        int rand = (int)(Math.random() * 100);
        if (rand >= 50){
            one.setText(local.getTweet().getRight());
            two.setText(local.getTweet().getWrong());
            System.out.println("One" + one.getText());
        }
        else{
            two.setText(local.getTweet().getRight());
            one.setText(local.getTweet().getWrong());
            System.out.println("Two" + two.getText());
        }



        one.setBorderPainted(true);
        two.setBorderPainted(true);
        one.setPreferredSize(new Dimension((int)tweetDimension.getWidth()/3, (int)tweetDimension.getHeight()/3*2));
        two.setPreferredSize(new Dimension((int)tweetDimension.getWidth()/3, (int)tweetDimension.getHeight()/3*2));
        one.setFont(new Font("Arial", Font.PLAIN, 40));
        two.setFont(new Font("Arial", Font.PLAIN, 40));
        

        JPanel east = new JPanel( new BorderLayout());
        JPanel buttons = new JPanel( new GridLayout(1,1));
        east.add(buttons, BorderLayout.EAST);
        buttons.add(one);
        buttons.add(two);
        localWindow.add(buttons, BorderLayout.SOUTH);
        localWindow.pack();
        localWindow.setVisible(true);
        
    }





    public static void main(String[] args) throws IOException {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run(){
                try {
                    newWindow(new Game());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
    }
}
