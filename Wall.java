/*
 * Lizzie Howell
 * 3/9/2024
 * Assignment 4 - Debuggers and Iterators
 */

import java.awt.Graphics;
import java.awt.Image;

public class Wall {
    private int x, y, w, h;
    static Image wall_image = null;

    public Wall(){
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }

    public Wall(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;  
    }
    
    public boolean wallClicked(int mouseX, int mouseY){	
        if ((mouseX > x && mouseX < (x + w)) && (mouseY > y && mouseY < (y + h))){
            System.out.println("Wall detected!");
            print();
            return true;
        }
            return false;
    }

    public void updateWallY(int scrollY){
        this.y = this.y + scrollY;
    }

    public void drawWall(Graphics g, int scrollY){      
		g.drawImage(wall_image, x, y - scrollY, w, h, null);
    }

    public void print(){
        System.out.println("Wall"+ " x: " + x + " y: " + y + " w: " + w + " h: " + h);
    }

    //unmarshaling contructor
    Wall(JSON ob){
        h = (int)ob.getLong("h");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        x = (int)ob.getLong("x");
        
        if(wall_image == null){
            try{
                wall_image = View.loadImage("wall.png");
            }
            catch(Exception e){
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getW(){
        return w;
    }
    public int getH(){
        return h;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getWallBottom(){
        
        return y + h;
    }
    public int getWallRight(){
        return x + w;
    }

    @Override 
    public String toString(){
        return "Wall (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h;
    }

    JSON marshal(){
        //System.out.println("marshal from Wall called.");
        JSON ob = JSON.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }
    public void unmarshal(JSON ob){
		//System.out.println("unmarshal from Wall called.");
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
	}
}
