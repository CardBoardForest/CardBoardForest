package carboardForest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class StandardBlack implements Tile
{
	private final String Type ="cantMove";
	private Rectangle r;
	@Override
	public int getNum() 
	{
		return 1;
		
	}

	@Override
	public void setRectangle(int row, int col, int x, int y, int tileSize) 
	{
		
		r =new Rectangle(col*tileSize+x,row*tileSize+y,tileSize,tileSize);
	}

	@Override
	public void Draw(Graphics g,int row, int col, int x, int y, int tileSize ) 
	{
		g.setColor(Color.BLACK);
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


}
