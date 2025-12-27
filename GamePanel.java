import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements KeyListener {

    private final int panelWidth = 400;
   private final int panelHeight= 400;
   private final int cellSize= 40;

   private final Timer timer;

    private final Snake snake;
    private final Apple apple;
    private boolean gameOver = false;
    private boolean won = false;
    private int score;


    public GamePanel(){

        this.addKeyListener(this);
        snake = new Snake(200, 200, cellSize);
        apple = new Apple(0, 0, cellSize);
        apple.respawn(panelWidth, panelHeight, cellSize, snake);
        
        score = 0;

        timer = new Timer(175, e -> gameLoop());
       System.out.println("GamePanel constructed");
       setFocusable(true);
       requestFocusInWindow();
    }

    public void startGame() {
        timer.start();
        this.requestFocusInWindow();
    }


    private void gameLoop() {

        int maxCells = (panelHeight/cellSize) * (panelWidth/cellSize);

        if (!gameOver && !won) {
              snake.update();
         }
            
        if (snake.getHeadX() == apple.getX() && snake.getHeadY() == apple.getY()) {
            snake.grow();
            apple.respawn(panelWidth, panelHeight, cellSize, snake);
            score ++;
        }
        if (snake.getHeadX() < 0 || snake.getHeadX() >= panelWidth || snake.getHeadY() < 0 || snake.getHeadY() >= panelHeight) {
            gameOver = true;
            timer.stop();
            System.out.println("Game Over! Your score: " + score);
            EndingPanel();
        }
        if (snake.checkSelfCollision()) {
            gameOver = true;
            timer.stop();
            EndingPanel();
            System.out.println("Game Over! Your score: " + score);
        }  if (snake.getSize() == maxCells){
            timer.stop();
            won = true;
            System.out.println("Won! Your score: " + score);
            EndingPanel();

        }
            repaint();
        } 
    


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0,0,0,140));
        

        for (int i=0; i <= panelWidth; i+= cellSize){
            g.drawLine(i, 0, i, panelHeight);
        }

        for (int ii = 0; ii<= panelHeight; ii+=cellSize){
            g.drawLine(0, ii, panelWidth, ii);
        }

        apple.draw(g);
        snake.draw(g);

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.drawString("Game Over", panelWidth / 2 - 30, panelHeight / 2);
            g.drawString("Score: " + score, 10, 15);
        }
       

    }

    //ending panel
    public void EndingPanel(){
        
        JFrame gp = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog EndPanel = new JDialog(gp, won ? "YOU WIN!" : "GAME OVER", true );
        EndPanel.setSize(200,200);
        EndPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.insets= new Insets(10, 10, 10, 10);

        //Message

        gbc.gridy=0;
        JLabel tittle = new JLabel(won ? "YOU WON\n CONGRATS!!" : "GAME OVER");
        EndPanel.add(tittle,gbc);

        gbc.gridy=1;
        JLabel scoreTittle = new JLabel("Apples ate: " + score);
        scoreTittle.setFont(scoreTittle.getFont().deriveFont(java.awt.Font.BOLD));
        EndPanel.add(scoreTittle,gbc);

        gbc.gridy=2;
        JButton tryAgain = new JButton("Try Again");
        tryAgain.setFont(tryAgain.getFont().deriveFont(java.awt.Font.BOLD));
        tryAgain.setForeground(Color.MAGENTA);
        EndPanel.add(tryAgain, gbc);

        tryAgain.addActionListener(e -> {
            EndPanel.dispose();
            resetGame();
            requestFocusInWindow();
        
        });

        gbc.gridy=3;
        JButton Leave = new JButton("Leave");
        EndPanel.add(Leave, gbc);
        Leave.setForeground(Color.RED);
        Leave.setFont(Leave.getFont().deriveFont(java.awt.Font.BOLD));
        Leave.addActionListener(e -> {
            System.exit(0);
        });

       
        EndPanel.setLocationRelativeTo(this);
       EndPanel.setResizable(false);
        EndPanel.setVisible(true);

    }

    public void resetGame(){
        snake.Sreset(200, 200);
        apple.respawn(panelWidth, panelHeight, cellSize, snake);
        score=0;
        gameOver=false;
        timer.restart();
        repaint();
        requestFocusInWindow();
    }

    @Override 
    public void keyTyped(KeyEvent e){
        
    }

    @Override 
    public void keyPressed(KeyEvent e){
        char c = e.getKeyChar();

        if(c=='a') { snake.setDirection(-40, 0);}
        if(c=='w') { snake.setDirection(0, -40);}
        if(c=='s') { snake.setDirection(0, 40);}
        if(c=='d') { snake.setDirection(40, 0);}

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: snake.setDirection(0, -40);
            break;
    
            case KeyEvent.VK_DOWN: 
                snake.setDirection(0, 40);
                break;
            case KeyEvent.VK_LEFT: 
                snake.setDirection(-40,0);
                break;
    
            case KeyEvent.VK_RIGHT:
                snake.setDirection(40, 0);
                break;
                
            default:
                break;
        }
        

        repaint();

    }
    @Override 
    public void keyReleased(KeyEvent e){
    }
        
}
