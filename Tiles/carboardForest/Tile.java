// @author Colin Wakefield
package carboardForest;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface Tile 
{
	public int ImageNumber();
	public void setRectangle(int row, int col,int x , int y , int tileSize);
	public Rectangle getRectangle();
	public void Draw(Graphics g, int row, int col, int x, int y, int tileSize,BufferedImage i);
	public String getType();
}
