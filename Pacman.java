/*
 * Lizzie Howell
 * 3/7/2024
 * Assignment 4 - Map Editor
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

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
        x = 700;
        y = 450;
        w = 30;
        h = 30;
        speed = 4.00;
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

    public void drawPac(Graphics g){
        //System.out.println("draw pac invoked");
        g.drawImage(currentImage, getPacX(), getPacY(), getPacW(), getPacH(), null);
    }

    public void getOutOfWall(Wall wall, char wallHitX, char wallHitY){
        //left collision
        if((wallHitX == 'L')&&((prevX + w) <= wall.getX())){
            setPacX(wall.getX() - w);
            System.out.println("go back!");
        }
        //right collision
        if((wallHitX == 'R')&&(prevX >= (wall.getX() + wall.getW()))){
            setPacX(wall.getX() + wall.getW());
            System.out.println("go back!");
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
    public int getPacBottom(){
        return y+h;
    }
    public int getPacRight(){
        return x+w;
    }

    public void setPacX(int x){
        prevX = this.x;
        this.x = x;
    }
    public void setPacY(int y){
        prevY = this.y;
        this.y = y;
    }
    public double getPacSpeed(){
        return speed;
    }
    public Image getImage(){
        return currentImage;
    }
    public int getDirection(){
        return direction;
    }

    public void setImage(int d, int i){
        this.direction = d;
        this.frame = i;
        currentImage = pacmanImages[d][i];
    }
    public void waka(int d){
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
