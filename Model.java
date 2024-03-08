/*
 * Lizzie Howell
 * 3/7/2024
 * Assignment 4 - Map Editor
 */

import java.util.ArrayList;
import java.util.Iterator;

public class Model
{
	private ArrayList<Wall> walls;
	private int begX, begY;
	private Pacman pacman;

	public Model()
	{
		walls = new ArrayList<Wall>();
		pacman = new Pacman();
	}

	public void update(){
		//pacman.update();
	}
	
	public void movePacman(char direction){
		if(direction == 'R'){
			int x = pacman.getPacX();
			if (x >= 775){
				pacman.setPacX(4);
			}
			else{
				int speed = (int)pacman.getPacSpeed();
				x += speed;
				pacman.setPacX(x);
			}
		}
		if(direction == 'L'){
			int x = pacman.getPacX();
			if (x <= 4){
				pacman.setPacX(775);
			}
			else{
				int speed = (int)pacman.getPacSpeed();
				x -= speed;
				pacman.setPacX(x);
			}
		}
		if(direction == 'U'){
			int y = pacman.getPacY();
			int speed = (int)pacman.getPacSpeed();
			y -= speed;
			pacman.setPacY(y);
		}
		if(direction == 'D'){
			int y = pacman.getPacY();
			int speed = (int)pacman.getPacSpeed();
			y += speed;
			pacman.setPacY(y);
		}
	}

	public double getModelSpeed(){
		return pacman.getPacSpeed();
	}

	public ArrayList<Wall> getWalls(){
		return walls;
	}

	public Pacman getPacman(){
		return pacman;
	}

	public int getLowestWallY(){
		Wall wall = null;
		int lowestY = 0;
		for (int i = 0; i < getWalls().size(); i++){
			wall = walls.get(i);
			if (wall.getY() > lowestY){
				lowestY = wall.getWallBottom();
			}
		}
		//System.out.print("Lowest Wall Y: ");
		//System.out.println(lowestY);
		return lowestY;
    }

	public int getHighestWallY(){
		Wall wall = null;
		int highestY = 800;
		for (int i = 0; i < getWalls().size(); i++){
			wall = walls.get(i);
			if (wall.getY() < highestY){
				highestY = wall.getY();
			}
		}
		// System.out.print("Highest Wall Y: ");
		// System.out.println(highestY);
		return highestY;
    }

	public boolean checkCollision(){
		//y = head, x = left, bottom = toes, right = right
		int pacHead = pacman.getPacY();
		int pacLeft = pacman.getPacX();
		int pacToes = pacman.getPacBottom();
		int pacRight = pacman.getPacRight();
		for (int i = 0; i < getWalls().size(); i++){
			Wall wall = walls.get(i);
			int wallTop = wall.getY();
			int wallLeft = wall.getX();
			int wallBottom = wall.getWallBottom();
			int wallRight = wall.getWallRight();
			if((pacHead > wallTop) && (pacHead < wallBottom) && (pacRight > wallLeft) && (pacRight < wallRight)){
				//collision of pac's head nad right with wall's left and bottom
				pacman.getOutOfWall(wall, 'L');
			}
			if(pacToes > wallTop && pacToes < wallBottom && pacRight > wallLeft && pacRight < wallRight){
				//collision of pac's toes and right with wall's left and top
			}
			if(pacHead > wallTop && pacHead < wallBottom && pacLeft > wallLeft && pacLeft < wallRight){
				//collision of pac's head and left with wall's right and bottom
			}
			if(pacToes > wallTop && pacToes < wallBottom && pacLeft > wallLeft && pacLeft < wallRight){
				//collision of pac's toes and left with wall's right and top
			}
			if(pacLeft < wallLeft && pacRight > wallRight){
				//collision vertically
				pacman.getOutOfWall(wall, 'L');
			}
			if(pacHead < wallTop && pacToes > wallBottom){
				//collision horizontially
			}


























			// if((pacman.getPacY() > wall.getY())&&(pacman.getPacY() < wall.getWallBottom())&&(pacman.getPacRight() > wall.getX())&&(pacman.getPacRight() < wall.getWallRight())){
			// 	//collision with pacmans head and righ side (with wall bottom and left)
			// 	System.out.println("Collision head and right");
			// 	pacman.getOutOfWall(wall, 'L','B');
			// 	return true;
			// }
			// if((pacman.getPacBottom() > wall.getY())&&(pacman.getPacBottom() < wall.getWallBottom())&&(pacman.getPacRight() > wall.getX())&&(pacman.getPacRight() < wall.getWallRight())){
			// 	//collision with pacmans toes and right side (with wall top and left)
			// 	System.out.println("Collision toes and right");
			// 	pacman.getOutOfWall(wall, 'L','T');
			// 	return true;
			// }
			// if((pacman.getPacY() > wall.getY())&&(pacman.getPacY() < wall.getWallBottom())&&(pacman.getPacX() > wall.getX())&&(pacman.getPacX() < wall.getWallRight())){
			// 	//collision with pacmans head and left side (with wall bottom and right)
			// 	System.out.println("Collision head and left");
			// 	pacman.getOutOfWall(wall, 'R','B');
			// 	return true;
			// }
			// if((pacman.getPacBottom() > wall.getY())&&(pacman.getPacBottom() < wall.getWallBottom())&&(pacman.getPacX() > wall.getX())&&(pacman.getPacX() < wall.getWallRight())){
			// 	//collision with pacmans toes and left (with wall top and right)
			// 	System.out.println("Collision toes and left");
			// 	pacman.getOutOfWall(wall, 'R','T');
			// 	return true;
			// }
			// if((pacman.getPacX() < wall.getX())&&(pacman.getPacRight() > wall.getWallRight())){
			// 	//collision vertical
			// 	System.out.println("Collision vertical");
			// 	//pacman.getOutOfWall(wall.getX());
			// 	return true;
			// }
			// if((pacman.getPacY() < wall.getY())&&(pacman.getPacBottom() > wall.getWallBottom())){
			// 	//collision horizontial
			// 	System.out.println("Collision horizontial");
			// 	//pacman.getOutOfWall(wall.getX(), 'R','T');
			// 	return true;
			// }
			// if(pacman.getPacRight() < wall.getX()){
			// 	return false;
			// }
			// else if(pacman.getPacX() > wall.getWallRight()){
			// 	return false;
			// }
			// else if(pacman.getPacBottom() < wall.getY()){
			// 	return false;
			// }
			// else if(pacman.getPacY()>wall.getWallBottom()){
			// 	return false;
			// }
			// else{
			// 	System.out.println("Collision");
			// 	return true;
			// }
		}
		return false;
	}

	// 0 = left; 1 = up; 2= right; 3 = down;
	public void arrowKeyPressed(int direction){
		if(direction == 0){
			//waka left
			pacman.waka(0);
		}
		if(direction == 1){
			//waka up
			pacman.waka(1);
		}
		if(direction == 2){
			// waka right
			pacman.waka(2);
		}
		if(direction == 3){
			//waka down
			pacman.waka(3);
		}
		if(checkCollision()){
			// System.out.println("Collision");
		};
	}

	public void startWalls(int x, int y)
	{
		//System.out.println("Starting wall...");
		begX = x;
		begY = y;
	}
	public void stopWalls(int newX, int newY)
	{
		//System.out.println("Stoping wall...");
		int width = 0;
		int height = 0;

		//accounting for when the user drags right to left
		if(newX < begX){
			width = begX - newX;
			begX = newX;
		}
		if(newY < begY){
			height = begY - newY;
			begY = newY;
		}
		if(newX > begX){
			width = newX - begX;
		}
		if(newY > begY){
			height = newY - begY;
		}
		//add the new wall
		Wall wall = new Wall(begX, begY, width, height);
		walls.add(wall);
	}
	public void clearWalls(){
		System.out.println("Clearing walls...");
		Iterator<Wall> iterate = walls.iterator();
		int size = getWalls().size() - 1;
		while(iterate.hasNext()){
			Wall wall = getWalls().get(size);
			getWalls().remove(wall);
			size--;
		}
	}

	public void save(){
		JSON mapsave = marshal();
		mapsave.save("map.json");
		System.out.println("Your map have been saved.");
	}
	public void load(){
		JSON mapload = JSON.load("map.json");
		unmarshal(mapload);
		System.out.println("Your map have been loaded.");
	}

	JSON marshal() {
		//System.out.println("marshal from model called.");
        JSON ob = JSON.newObject();
        JSON tmpList = JSON.newList();
        ob.add("walls", tmpList);
        for(int i = 0; i < walls.size(); i++){
			tmpList.add(walls.get(i).marshal());
		}
		return ob;
    }

	public void unmarshal(JSON ob){
		//System.out.println("unmarshal from model called.");
		clearWalls();
		walls = new ArrayList<Wall>();
		JSON tmpList = ob.get("walls");
        for(int i = 0; i < tmpList.size(); i++){
			Wall wall = new Wall(tmpList.get(i));
			walls.add(wall);
		}
	}
}