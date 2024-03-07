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

public class View extends JPanel
{
	private BufferedImage wall_image;
	private Model model;
	private int scrollY;
	private Image[] pacmanImages;

	public View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		try {
			this.wall_image = ImageIO.read(new File("wall.png"));
			pacmanImages = new Image[12];
		
			for(int i = 0; i < 12; i++){
				pacmanImages[i] = loadImage("pacmanImages/pacman" + (i+1) + ".png");
			}
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void paintComponent(Graphics g){
		g.setColor(new Color(25, 25, 25));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.getWalls().size(); i++){
			Wall wall = model.getWalls().get(i);
			g.drawImage(wall_image, wall.getX(), (wall.getY() - scrollY), wall.getW(), wall.getH(), null);
		}
		g.drawImage(this.pacmanImages[0], model.getPacmanX(), model.getPacmanY(), null);
	}

	public Image loadImage(String filepath){
		BufferedImage pacman_images = null;
		try{
			pacman_images = ImageIO.read(new File(filepath));
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.out.println(filepath);
			System.exit(1);
		}
		return pacman_images;
	}

	public void cameraUp(){
		scrollY += - model.getPacmanSpeed();
	}
	
	public void cameraDown(){
		scrollY += model.getPacmanSpeed();
	}
}
