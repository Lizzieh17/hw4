/*
 * Lizzie Howell
 * 3/7/2024
 * Assignment 4 - Map Editor
 */
public class Pacman {
    private int x, y;
    private double speed;
    private int index;

    public Pacman(){
        x = 600;
        y = 300;
        speed = 4.00;
        index = 0;
    }

    public Pacman(int x, int y, double speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void update(){      }

    public int getPacX(){
        return x;
    }
    public int getPacY(){
        return y;
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
    public int getIndex(){
        return index;
    }

    @Override 
    public String toString(){
        return "Pacman (x,y) = (" + x + ", " + y + ")";
    }
}
