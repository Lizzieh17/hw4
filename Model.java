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

	public Model() {
		walls = new ArrayList<Wall>();
		pacman = new Pacman();
	}

	public void update() {
		// pacman.update();
	}

	public void movePacman(char direction) {
		if (direction == 'R') {
			int x = pacman.getPacX();
			if (x >= 775) {
				pacman.setPacX(4);
			} else {
				int speed = (int) pacman.getPacSpeed();
				x += speed;
				pacman.setPacX(x);
			}
		}
		if (direction == 'L') {
			int x = pacman.getPacX();
			if (x <= 4) {
				pacman.setPacX(775);
			} else {
				int speed = (int) pacman.getPacSpeed();
				x -= speed;
				pacman.setPacX(x);
			}
		}
		if (direction == 'U') {
			int y = pacman.getPacY();
			int speed = (int) pacman.getPacSpeed();
			y -= speed;
			pacman.setPacY(y);
		}
		if (direction == 'D') {
			int y = pacman.getPacY();
			int speed = (int) pacman.getPacSpeed();
			y += speed;
			pacman.setPacY(y);
		}
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

	public boolean checkCollision(Wall wall, int scrollY) {
		// y = head, x = left, toes = y + h, right = x + w
		int pacHead = pacman.getPacY();
		int pacLeft = pacman.getPacX();
		int pacToes = (pacman.getPacY() + pacman.getPacH());
		int pacRight = (pacman.getPacX() + pacman.getPacW());
		int wallTop = wall.getY() - scrollY;
		int wallLeft = wall.getX();
		int wallBottom = (wall.getY() - scrollY + wall.getH());
		int wallRight = (wall.getX() + wall.getW());

		if (pacLeft < wallRight && pacRight > wallLeft && pacHead < wallBottom && pacToes > wallTop){
			//System.out.println("colliding");
			pacman.getOutOfWall(wall, scrollY);
		}
		else{

			return false;
		}
		return false;
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
		if (newY < begY) {
			height = begY - newY;
			begY = newY;
		}
		if (newX > begX) {
			width = newX - begX;
		}
		if (newY > begY) {
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
		walls = new ArrayList<Wall>();
		JSON tmpList = ob.get("walls");
		for (int i = 0; i < tmpList.size(); i++) {
			Wall wall = new Wall(tmpList.get(i));
			walls.add(wall);
		}
	}
}