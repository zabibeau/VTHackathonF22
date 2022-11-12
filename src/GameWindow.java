import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * This class contains the GUI for the project.
 */

public class GameWindow {
    
    private static String teamNameA;
    private static String teamNameB;

    public static void newWindow(){
        JFrame window = new JFrame("Rivals");
        window.getContentPane().setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        tweetLabel.setText("If you run a QB draw on third-and-7, presumably to set up a manageable fourth-down distance, why take a high-risk shot at the end zone?Mind-boggling. #Hokies");
        tweetLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        
        beans.add(tweetLabel);
        

        JButton one = new JButton("Team A for Now");
        JButton two = new JButton("Team B for Now");

        one.setBorderPainted(true);
        two.setBorderPainted(true);
        one.setPreferredSize(new Dimension((int)tweetDimension.getWidth()/3, (int)tweetDimension.getHeight()/3*2));
        two.setPreferredSize(new Dimension((int)tweetDimension.getWidth()/3, (int)tweetDimension.getHeight()/3*2));
        one.setFont(new Font("Arial", Font.PLAIN, 40));
        two.setFont(new Font("Arial", Font.PLAIN, 40));
        

        one.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            pressed();
            }
        });

        two.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
             pressed();
            }
        });
        JPanel east = new JPanel( new BorderLayout());
        JPanel buttons = new JPanel( new GridLayout(1,1));
        east.add(buttons, BorderLayout.EAST);
        buttons.add(one);
        buttons.add(two);
        window.add(buttons, BorderLayout.SOUTH);
        //one.setHorizontalAlignment((int)dimension.getWidth()/10);
        //one.setVerticalAlignment((int)tweetDimension.getHeight()*5/2);

        window.getContentPane().add(beans, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }
    
    public static void pressed(){
        System.out.println("Pressed one bitch");
    }





    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                newWindow();
            }
        });
    }
}
