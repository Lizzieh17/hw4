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
        prevX = 0;
        prevY = 0;
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

//model has arraylist of walls a pacman, wallstart wallstop, could have scroll and one file wiht an iterator
    public Pacman(int x, int y){
        this.x = x;
        this.y = y;
        w = 50;
        h = 50;
        this.speed = 5;
    }

    public void savePac(){
        this.prevX = this.x;
        this.prevY = this.y;
    }

    public void drawPac(Graphics g){
        //System.out.println("draw pac invoked");
        g.drawImage(currentImage, getPacX(), getPacY(), getPacW(), getPacH(), null);
    }

    public void getOutOfWall(Wall wall, int scrollY){        
        if(((prevY + h) <= (wall.getY() + scrollY)) && (y + h <= wall.getY())){
            if(((x+w) >= wall.getX()) && (prevX <= wall.getX())){
                x = wall.getX() - w;
            }
            //right wall
            else if((x <= (wall.getX() + wall.getW())) && (prevX >= (wall.getX() + wall.getW()))){
                x = wall.getX() + wall.getW();
            }
            else {
                y = wall.getY() - h - scrollY;
            }
        }
        else if(((wall.getY() + wall.getH()) <= y) && (prevY >= (wall.getY() + wall.getH()))){
            y = wall.getY() + wall.getH();
        }
        //left double check
        else if(((x+w) >= wall.getX()) && (prevX <= wall.getX())){
            x = wall.getX() - w;
        }
        //right wall double check
        else if((x <= (wall.getX() + wall.getW())) && (prevX >= (wall.getX() + wall.getW()))){
            x = wall.getX() + wall.getW();
        }
    }


    public void update(){      }

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

    @Override 
    public String toString(){
        return "Pacman (x,y) = (" + x + ", " + y + ")";
    }
}
