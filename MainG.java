import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;


public class MainG {
 
    public static void main(String[] args) {

        // the image for the front page
        ImageIcon icon =new ImageIcon(MainG.class.getResource("SG.png"));
        Image img = icon.getImage();
        Image scaleimg = img.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

        //JFrame
        JFrame newFrame= new JFrame("Snake Game");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setPreferredSize(new Dimension(400, 430));
        newFrame.setBackground(Color.GREEN);
    
        //Game panel
        GamePanel gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(400, 400));
        gamePanel.setBackground(new Color(51,153, 255));
     

        //JPanel
        
        JPanel MPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        MPanel.setBackground(new Color(51,157, 255));

        //Tittle label
        JLabel tittleL = new JLabel(new ImageIcon(scaleimg));
        gbc.gridy = 0;
        gbc.weighty=0;
        MPanel.add(tittleL,gbc);


        //button
        JButton B = new JButton("Start");
        B.setForeground(Color.BLACK);
        B.setFont(B.getFont().deriveFont(java.awt.Font.BOLD));
        B.setSize(200, 120);

        MPanel.setLayout(new GridBagLayout());
        gbc.gridy = 2;
        gbc.weighty=0;
        gbc.anchor= GridBagConstraints.SOUTH;
      

        B.setPreferredSize(new Dimension(150, 50));  
        gbc.insets= new Insets(0, 0,150, 0);
     
        MPanel.add(B, gbc);

      
        //start button 
       B.addActionListener(e-> {


        newFrame.remove(MPanel); //remove start panel
        newFrame.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setVisible(true);
        newFrame.pack();
        newFrame.revalidate();
        newFrame.repaint();
        gamePanel.startGame();
        gamePanel.requestFocusInWindow();
       
    
    });


   
    newFrame.add(MPanel);
    newFrame.pack();
    newFrame.setLocationRelativeTo(null);
    newFrame.setVisible(true);

    
  }

}
