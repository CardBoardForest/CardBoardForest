package carboardForest;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Tile 
{
	public int getNum();
	public void setRectangle(int row, int col,int x , int y , int tileSize);
	public Rectangle getRectangle();
	public void Draw(Graphics g, int row, int col, int x, int y, int tileSize);
	public String getType();
}
