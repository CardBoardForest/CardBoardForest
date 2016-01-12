// @author Colin Wakefield
package carboardForest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class StandardKillTile implements Tile
{

	private final String Type = "kill";
	private Rectangle r;

	@Override
	public void setRectangle(int row, int col, int x, int y, int tileSize) 
	{
		
		r =new Rectangle(col*tileSize+x,row*tileSize+y,tileSize,tileSize);
	}

	@Override
	public void Draw(Graphics g,int row, int col, int x, int y, int tileSize , BufferedImage i)
	{
		g.setColor(Color.RED);
		g.fillRect(col*tileSize+x,row*tileSize+y,tileSize,tileSize);
	}

	@Override
	public Rectangle getRectangle() 
	{
		return r;
	}

	@Override
	public String getType() 
	{	
		return Type;
	}

	@Override
	public int ImageNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
