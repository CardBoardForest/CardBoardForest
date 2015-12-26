package carboardForest;
/**
 * Write a description of class StandardGreenItem here.
 * 
 * @author (colin wakefield) 
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class StandardGreenItem implements Item
{
    private final String Type ="item";
    private Rectangle r;
    boolean have;
    public StandardGreenItem()
    {
         have = false;
    }
    @Override
    public void playerHas(boolean have)
    {
         this.have = have;
        
    }
    @Override
    public boolean getHas()
    {
         return have;
        
    }
    @Override
    public int getNum() 
    {
        return 3;
        
    }

    @Override
    public void setRectangle(int row, int col, int x, int y, int tileSize) 
    {
        if(!have)
        r =new Rectangle(col*tileSize+x,row*tileSize+y,tileSize,tileSize);
    }

    @Override
    public void Draw(Graphics g,int row, int col, int x, int y, int tileSize ) 
    {
    	this.DrawBackgroundTile(g,col,row,x,y,tileSize);
    	if(!have)
    	{
        g.setColor(Color.green);
        g.fillRect(col*tileSize+x,row*tileSize+y,tileSize/2,tileSize/2);
    	}
        
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
	public void DrawBackgroundTile(Graphics g, int row, int col, int x, int y, int tileSize) 
	{
		g.setColor(Color.pink);
        g.fillRect(col*tileSize+x,row*tileSize+y,tileSize,tileSize);
	}

}
