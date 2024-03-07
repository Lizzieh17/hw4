/*
 * Lizzie Howell
 * 3/7/2024
 * Assignment 4 - Map Editor
 */
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class View extends JPanel
{
	//move
	private Model model;
	private int scrollY = 0;
	private Image wall_image;
	//movve

	public View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		this.wall_image = loadImage("wall.png");
            // System.out.println("wall image loaded");
            // System.out.println(wall_image);
	}

	public void paintComponent(Graphics g){
		g.setColor(new Color(25, 25, 25));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.getWalls().size(); i++){
			Wall wall = model.getWalls().get(i);
			//wall.drawWall(g, this.scrollY);
			g.drawImage(wall_image, wall.getX(), (wall.getY() - scrollY), wall.getW(), wall.getH(), null);

		}
		model.getPacman().drawPac(g);
	}

	static BufferedImage loadImage(String filepath){
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(filepath));
			System.out.print("Imaged loaded: ");
			System.out.println(filepath);
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.out.println(filepath);
			System.exit(1);
		}
		return image;
	}
	public void cameraUp(){
		//scrollY +=  model.getModelSpeed();
		scrollY -=  4;
	}
	
	public void cameraDown(){
		//scrollY += model.getModelSpeed();
		scrollY +=  4;
	}
}
