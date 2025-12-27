import java.awt.Color;
import java.awt.Graphics;


public class Snake {
    
    private final int[] x = new int[100];
    private final int[] y = new int[100];
    private int size = 2;
    private final int cellSize;
    private int dx;
    private int dy;



    //Constructor
    public Snake(int startX, int startY, int cellSize) {
        x[0] = startX;
        y[0] = startY;
        x[1] = startX - cellSize;
        y[1] = startY;
        this.cellSize = cellSize;

        //the initial direction of the snake
        dx = cellSize;
        dy = 0;


    }

    //Getter

    public int getHeadX() {
        return x[0];
    }
    public int getHeadY() {
        return y[0];
    }
    public int getSize(){
        return size;
    }

    //how the snake will move
    public void setDirection(int dx, int dy) {
        if (this.dx + dx != 0 || this.dy + dy != 0) {
            this.dx = dx;
            this.dy = dy; 


        }
    }

    //reset method of snake

    public void Sreset(int startX, int startY){
        x[0] = startX;
        y[0] = startY;
        x[1] = startX - cellSize;
        y[1] = startY;

        for (int i = 1; i < x.length; i++) {
            x[i] = 0;
            y[i] = 0;
        } 

        dx = cellSize;
        dy=0;
        size = 2;


    }

    //Draw method
    public void draw(Graphics g) {
     

        g.setColor(Color.GREEN);
        for(int i=0; i<size; i++){
            g.fillRect(x[i],y[i], cellSize, cellSize);
        }

    }

    //Update the position of the snake
    public void update() {

        for (int i = size - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
    }
        x[0] += dx;
        y[0] += dy;
    }

    //Grows the snake
    public void grow() {
        size++;
        if (size > x.length) {
            size = x.length; 
            
            
            
        }
      
    }
    // Makes sure snake doesn't spawn on itself
    public boolean isOnSnake(int xPos, int yPos) {
        for (int i = 0; i < size; i++) {
            if (x[i] == xPos && y[i] == yPos) return true;
        }
        return false;
    }

    // Checks to see if it hits itself
    public boolean checkSelfCollision() {
        for (int i = 1; i < size; i++) { // skip head
            if (x[0] == x[i] && y[0] == y[i]) return true;
        }
        return false;
    }

}

    
