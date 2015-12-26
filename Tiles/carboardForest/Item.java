asdfasdfpackage carboardForest;

/**
 * Write a description of interface Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public interface Item extends Tile
{
    public abstract void playerHas(boolean has);
    public abstract boolean getHas();
    public abstract void DrawBackgroundTile(Graphics g,int row, int col, int x, int y, int tileSize );
}
