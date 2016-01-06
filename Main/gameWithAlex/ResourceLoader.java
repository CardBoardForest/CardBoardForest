package gameWithAlex;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

final public class ResourceLoader 
{
	public static BufferedImage Sheet[];
	//what it says it does but for only images
	public static InputStream load(String path)
	{
		// gets images when it is in the jar file
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if(input == null)
			input = ResourceLoader.class.getResourceAsStream("/" +path);
		return input;
		
	}
	/*
	 * loads the images that are needed for the game
	 * 
	 * 
	 */
	public static void loadSheet()
	{
		try
	       {
	       BufferedImage bigImg = ImageIO.read(ResourceLoader.load("DowneyTiles.png"));
	       final int width = 32;
	       final int height = 32;
	       final int rows = 2;
	       final int cols = 3;
	       BufferedImage[] sprites = new BufferedImage[rows * cols];
	       for (int i = 0; i < rows; i++)
	       {
	                for (int j = 0; j < cols; j++)
	                {
	                        sprites[(i * cols) + j] = bigImg.getSubimage(
	                                    j * width,
	                                    i * height,
	                                    width,
	                                    height
	                                    );
	                }
	            }
	       Sheet = sprites;
	       }
	       catch(IOException ex)
	       {
	           System.out.print("images not found");
	           Sheet = null;
	       }
	}
	public static BufferedImage getImage(int num)
	{
		return Sheet[num];
	}
}
