/*
 * Lizzie Howell
 * 3/7/2024
 * Assignment 4 - Debuggers and Iterators
 */
public class Wall {
    private int x, y, w, h;

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
            return true;
        }
        return false;
    }

    public boolean wallHit(int pacX, int pacY){
        if ((pacX > x && pacX < (x + w)) && (pacY > y && pacY < (y + h))){
            System.out.println("Wall Hit!");
            return true;
        }
        return false;
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
