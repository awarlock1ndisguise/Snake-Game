import java.awt.Graphics;
import java.awt.Image;
import java.util.random.*;

import javax.swing.ImageIcon;

public class Apple {
    //Fields
    private int x;
    private int y;
    private final RandomGenerator RGen = RandomGenerator.getDefault();
    private int size;
    private Image Aimage;
//Constructor
    public Apple(int x, int y, int size) {
      
        this.x=x;
        this.y=y;
        this.size = size;

        Aimage = new ImageIcon(getClass().getResource("New Piskel-4.gif")).getImage();

    }

    //Getters
    public int getX() {
        return x; }
    public int getY() {
        return y; }
    public int getSize() { 
        return size; }

    //Setter
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Draw method
    public void draw(Graphics g) {
    
       g.drawImage(Aimage, x, y, 40,40, null);
    }

    //generate random position method
    public int randomPosition(int panelSize, int cellSize) {
        int maxCells = panelSize / cellSize;
        return RGen.nextInt(0, maxCells) * cellSize;
    } 
    
    //it checks not to respawn on hwere snake is
    public void respawn(int panelWidth, int panelHeight, int cellSize, Snake snake){
        int newX, newY;
        
        do {
        newX= randomPosition(panelWidth, cellSize);
        newY= randomPosition(panelHeight, cellSize);
        }
        while (snake.isOnSnake(newX, newY)); 
        
        this.x= newX;
        this.y= newY;
        
    }
}


