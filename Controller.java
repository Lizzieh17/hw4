/*
 * Lizzie Howell
 * 3/7/2024
 * Assignment 4 - Map Editor
 */
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class Controller implements ActionListener, MouseListener, KeyListener
{
	private View view;
	private Model model;
	private boolean addWalls;
	private boolean editMode;
	private boolean keyLeft;
	private boolean keyRight;
	private boolean keyUp;
	private boolean keyDown;

	public Controller(Model m)
	{
		model = m;
		addWalls = false;
		editMode = false;
		keyLeft = false;
		keyRight = false;
		keyUp = false;
		keyDown = false;
	}

	public void actionPerformed(ActionEvent e){
	}

	void setView(View v){
		view = v;
	}

	public void mousePressed(MouseEvent e)
	{
		if (editMode == true){
			if (addWalls == true){
				model.startWalls(e.getX(), e.getY());
			}
			for(int i = 0; i < model.getWalls().size(); i++){ 
				Wall wall = model.getWalls().get(i);
				if ((addWalls == false) && (wall.wallClicked(e.getX(), e.getY()) == true)){
					System.out.println("Wall Removed");
					model.getWalls().remove(wall);
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) { 
		if (addWalls == true && editMode == true){
			model.stopWalls(e.getX(), e.getY());
		}
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {  	}

	public void keyPressed(KeyEvent e)
	{
		char key = Character.toLowerCase(e.getKeyChar());
		int key2 = e.getKeyCode();
		//quit
		if(key == 'q' || key2 == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		//scroll view
		// switch(e.getKeyCode()){
		// 	case KeyEvent.VK_UP: view.cameraUp(); break;
		// 	case KeyEvent.VK_DOWN: view.cameraDown(); break;
		// }
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		char key = Character.toLowerCase(e.getKeyChar());

		//save
		if(key == 's'){
			model.save();
		}

		//load
		if(key == 'l'){
			model.load();
		}

		//add walls or not
		if(key == 'a'){
			addWalls = !addWalls;
			System.out.println("Add walls is " + addWalls);
		}

		//enter editmode
		if(key == 'e'){
			editMode = !editMode;
			System.out.println("Edit mode is " + editMode);
			if (addWalls == false){
				addWalls = true;
				System.out.println("Add mode is " + addWalls);
			}
		}

		//clear all walls
		if(key == 'c'){
			if (model.getWalls().size() > 0) {
				model.clearWalls();
				System.out.println("Cleared all walls.");
			}
			else {
				System.out.println("No Walls to clear.");
			}
		}
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
		}
		//System.out.println(model.stringPac());
	}

	public void keyTyped(KeyEvent e){	}

	public void update(){
		if(keyRight){
			model.moveRight();
		}
		if(keyLeft){
			model.moveLeft();
		}
		if(keyDown){
			//scroll down
			model.moveDown();
			view.cameraDown();
		}
		if(keyUp){
			//scroll up
			model.moveUp();
			view.cameraUp();
		}
		// for(int i = 0; i < model.getWalls().size(); i++){ 
		// 	Wall wall = model.getWalls().get(i);
		// 	// if (wall.wallHit(model.getPacmanX(), model.getPacmanY()) == true){
		// 	// 	System.out.println("Wall Removed");
		// 	// 	model.getWalls().remove(wall);
		// 	// }
		// }
	}
}
