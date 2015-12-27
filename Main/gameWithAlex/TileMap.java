 
package gameWithAlex;

import java.io.*;
import java.awt.*;

import carboardForest.StandardBlack;
import carboardForest.StandardGreenItem;
import carboardForest.StandardKillTile;
import carboardForest.StandardWhite;
import carboardForest.Tile;
import carboardForest.downeyTiles.CornerTile;
import carboardForest.downeyTiles.FloorTile;
import carboardForest.downeyTiles.WallTile;
import carboardForest.downeyTiles.cellingTile;

public class TileMap
{
    private int x;
    private int y;
    private int tileSize;
    private int[][] map;
    private Tile[][] Tilemap;
    private int mapWidth;
    private int mapHeight;
   
    public TileMap(String s , int TileSize )
    {
        this.tileSize = TileSize;
        try
        {
            // inputs from buffered reader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            getClass().getClassLoader().getResourceAsStream(
                                s)));
            //reads first line
            mapWidth = Integer.parseInt(br.readLine());
            mapHeight = Integer.parseInt(br.readLine());
            //sets maps
            Tilemap = new Tile[mapHeight][mapWidth];
            map = new int[mapHeight][mapWidth];
            //sets the map so i can set it to the tilemap
            String Delimitors = " ";
            for(int row = 0; row < mapHeight; row++)
            {
                String line = br.readLine();
                String tokens[] = line.split(Delimitors);
                for(int col = 0; col < mapWidth; col++)
                {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
            //sets tilemap and puts it with correct types
            for(int row = 0; row < mapHeight; row++)
            {
                for(int col = 0; col < mapWidth; col++)
                {
                	 int rc = map[row][col];
                     switch(rc)
                     {
                     	case 0:Tilemap[row][col] = new StandardWhite();break;
                     	case 1:Tilemap[row][col] = new StandardBlack();break;
                     	case 2:Tilemap[row][col] = new StandardKillTile();break;
                     	case 3:Tilemap[row][col] = new StandardGreenItem();break;
                     	//case 4:Tilemap[row][col] = new cellingTile();break;
                     	//case 5: Tilemap[row][col] = new CornerTile();break;
                     	//case 6: Tilemap[row][col] = new FloorTile();break;
                     	//case 7: Tilemap[row][col] = new WallTile();break;
                     	default :Tilemap[row][col] = new StandardWhite();break;
                     }
                	
                }
                
            }
            
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    public void update()
    {
            //updates the Rectangle for the Tilemap
            for(int row = 0; row < mapHeight; row++)
            {
                for(int col = 0; col < mapWidth; col++)
                {
                    Tilemap[row][col].setRectangle(row, col, x, y, tileSize); 
                }
            }
    }
    //get and set
    public int getx()
    {
        return x ;
    }
    public int gety()
    {
        return y;
    }
    public void setx(int i)
    {
        x = i;
    }
    public void sety(int i)
    {
        y = i ;
    }
    public int getWidth()
    {
        return mapWidth;
    }
    public int getHeight()
    {
        return mapHeight;
    }
    public int getTileSize()
    {
        return tileSize;
    }
    public Tile getTile(int row ,int col)
    {
        return Tilemap[row][col];
    }
    public void setTile(int row,int col,Tile t)
    {
         Tilemap[row][col] = t;
    }
    // the draw method for the Tilemap
    public void draw(Graphics g)
    {
        //runs through the Tilemap and 
        for(int row = 0; row < mapHeight; row++)
        {
            for(int col = 0; col < mapWidth; col++)
            {
                Tilemap[row][col].Draw(g,row, col, x, y, tileSize);         
            }
        
        
        }
    }
}
