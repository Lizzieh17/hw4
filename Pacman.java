/*
 * Lizzie Howell
 * 3/9/2024
 * Assignment 4 - Map Editor
 */

import java.awt.Graphics;
import java.awt.Image;

public class Pacman {
    private int x, y, w, h;
    private double speed;
    static Image[][] pacmanImages = null;
    private Image currentImage;
    private int direction;
    private int frame;
    private int prevX, prevY;
    private int MAX_IMAGES = 3;
    private int MAX_DIRECTION = 4;

    public Pacman(){
        x = 400;
        y = 400;
        w = 40;
        h = 40;
        speed = 5.00;
        direction = 0;
        frame = 0;
        prevX = 400;
        prevY = 400;
        pacmanImages = new Image[MAX_DIRECTION][MAX_IMAGES];
        
        if(currentImage == null){
            try{
                // i = direction, z = image 
                int count = 0;
                for(int d = 0; d < 4; d++){
                    for(int z = 0; z < 3;z++)
                        {
                            count++;
                            pacmanImages[d][z] = View.loadImage("pacmanImages/pacman" + (count) + ".png");
                        }
                }
                currentImage = pacmanImages[0][0];
            }
            catch(Exception e){
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
    }

    public Pacman(int x, int y){
        this.x = x;
        this.y = y;
        w = 50;
        h = 50;
        this.speed = 5;
    }

    public void savePac(){
        prevX = x;
        prevY = y;
    }

    public void drawPac(Graphics g, int scrollY){
        //System.out.println("draw pac invoked");
        g.drawImage(currentImage, getPacX(), getPacY() - scrollY, getPacW(), getPacH(), null);
    }

    public void getOutOfWall(int scrollY){        
        this.x = this.prevX;
        this.y = this.prevY;
    }

    public void setImage(int d, int i){
        this.direction = d;
        this.frame = i;
        currentImage = pacmanImages[d][i];
    }
    public void animate(int d){
        direction = d;
        frame++;
        if (frame >= MAX_IMAGES){
            frame = 0;
        }
        setImage(direction, frame);
    }


    public void movePacRight(){
        prevX = x;
        if (x >= 775) {
            x = 4;
        } else {
            x += speed;
        }
    }
    public void movePacLeft(){
        prevX = x;
        if (x <= 4) {
            x = 775;
        } else {
            x -= speed;
        }
    }
    public void movePacUp(){
        prevY = y;
        y -= speed;
    }
    public void movePacDown(){
        prevY = y;
        y += speed;
    }

    public int getPacX(){
        return x;
    }
    public int getPacY(){
        return y;
    }
    public int getPacH(){
        return h;
    }
    public int getPacW(){
        return w;
    }
    public void setPacX(int x){
        this.x = x;
    }
    public void setPacY(int y){
        this.y = y;
    }
    public double getPacSpeed(){
        return speed;
    }

    @Override 
    public String toString(){
        return "Pacman (x,y) = (" + x + ", " + y + ")" + ", Previous Pacman (x,y) = (" + prevX +" , " + prevY + " )";
    }
}
