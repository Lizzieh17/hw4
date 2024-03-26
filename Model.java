/*
 * Lizzie Howell
 * 3/9/2024
 * Assignment 4 - Map Editor
 */

import java.util.ArrayList;
import java.util.Iterator;

public class Model {
	private ArrayList<Wall> walls;
	private int begX, begY;
	private Pacman pacman;
	private boolean colliding;
	
	public Model() {
		walls = new ArrayList<Wall>();
		pacman = new Pacman();
	}

	public void update() {
		// pacman.update();
	}

	public void checkCollision(int scrollY) {
		// y = head, x = left, toes = y + h, right = x + w
		int pacHead = pacman.getPacY();
		int pacLeft = pacman.getPacX();
		int pacToes = (pacman.getPacY() + pacman.getPacH());
		int pacRight = (pacman.getPacX() + pacman.getPacW());
		colliding = false;

		for(int i = 0; i<walls.size(); i++){
			Wall wall = walls.get(i);
			int wallTop = wall.getY();
			int wallLeft = wall.getX();
			int wallBottom = (wall.getY() + wall.getH());
			int wallRight = (wall.getX() + wall.getW());

			if((((pacHead < wallTop) && (pacToes > wallBottom)) || ((pacToes > wallTop) && (pacToes < wallBottom))) 
			&& ((pacRight > wallLeft) && (pacLeft < wallRight)))
			{
				colliding = true;
				pacman.getOutOfWall(scrollY);
			}
			if((((pacLeft > wallLeft) && (pacLeft < wallRight)) || ((pacRight > wallLeft) && (pacRight < wallRight))) 
			&& ((pacToes > wallTop) && (pacHead < wallBottom)))
			{
				colliding = true;
				pacman.getOutOfWall(scrollY);
			}
		}
	}

	// 0 = left; 1 = up; 2= right; 3 = down;
	public void arrowKeyPressed(int direction) {
		if (direction == 0) {
			// waka left
			pacman.animate(0);
		}
		if (direction == 1) {
			// waka up
			pacman.animate(1);
		}
		if (direction == 2) {
			// waka right
			pacman.animate(2);
		}
		if (direction == 3) {
			// waka down
			pacman.animate(3);
		}
	}

	public void startWalls(int x, int y) {
		begX = x;
		begY = y;
	}

	public void stopWalls(int newX, int newY, int scrollY) {
		// System.out.println("Stoping wall...");
		int width = 0;
		int height = 0;

		// accounting for when the user drags right to left
		if (newX < begX) {
			width = begX - newX;
			begX = newX;
		}
		else if (newX > begX) {
			width = newX - begX;
		}
		if (newY < begY) {
			height = begY - newY;
			begY = newY;
		}
		else if (newY > begY) {
			height = newY - begY;
		}
		// add the new wall
		if (scrollY != 0) {
			begY += scrollY;
		}
		Wall wall = new Wall(begX, begY, width, height);
		walls.add(wall);
	}

	public void clearWalls() {
		System.out.println("Clearing walls...");
		Iterator<Wall> iterate = walls.iterator();
		int size = getWalls().size() - 1;
		while (iterate.hasNext()) {
			Wall wall = getWalls().get(size);
			getWalls().remove(wall);
			size--;
		}
	}

	public boolean isColliding(){
		return colliding;
	}
	public double getModelSpeed() {
		return pacman.getPacSpeed();
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public Pacman getPacman() {
		return pacman;
	}

	public int getLowestWallY() {
		Wall wall = null;
		int lowestY = 0;
		for (int i = 0; i < getWalls().size(); i++) {
			wall = walls.get(i);
			if (wall.getY() > lowestY) {
				lowestY = wall.getWallBottom();
			}
		}
		return lowestY;
	}

	public int getHighestWallY() {
		Wall wall = null;
		int highestY = 800;
		for (int i = 0; i < getWalls().size(); i++) {
			wall = walls.get(i);
			if (wall.getY() < highestY) {
				highestY = wall.getY();
			}
		}
		return highestY;
	}

	public void save() {
		JSON mapsave = marshal();
		mapsave.save("map.json");
		System.out.println("Your map have been saved.");
	}

	public void load() {
		JSON mapload = JSON.load("map.json");
		unmarshal(mapload);
		System.out.println("Your map have been loaded.");
	}

	JSON marshal() {
		// System.out.println("marshal from model called.");
		JSON ob = JSON.newObject();
		JSON tmpList = JSON.newList();
		ob.add("walls", tmpList);
		for (int i = 0; i < walls.size(); i++) {
			tmpList.add(walls.get(i).marshal());
		}
		return ob;
	}

	public void unmarshal(JSON ob) {
		// System.out.println("unmarshal from model called.");
		clearWalls();
		JSON tmpList = ob.get("walls");
		for (int i = 0; i < tmpList.size(); i++) {
			Wall wall = new Wall(tmpList.get(i));
			walls.add(wall);
		}
	}
}