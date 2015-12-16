package gameWithAlex;


 

import java.io.*;
import java.awt.*;

import carboardForest.StandardBlack;
import carboardForest.Tile;
import carboardForest.StandardWhite;
public class TileMap
{
    private int x;
    private int y;
    private int tileSize;
    private int[][] map;
    private Tile[][] Tilemap;
    private int mapWidth;
    private int mapHeight;
   
    public TileMap(String s , int TileSize)
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
            // makes the map in to a string matrices
            Tilemap = new Tile[mapHeight][mapWidth];
            map = new int[mapHeight][mapWidth];
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
            // sets the tilemap
            for(int row = 0; row < mapHeight; row++)
            {
                for(int col = 0; col < mapWidth; col++)
                {
                	 int rc = map[row][col];
                     switch(rc)
                     {
                     	case 0:Tilemap[row][col] = new StandardWhite();break;
                     	case 1:Tilemap[row][col] = new StandardBlack();break;
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
            //0 = free block
            //1 = Can't move through block
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

        // draws blocks on the grid
    public void draw(Graphics g)
    {
        //0 = free block
        //1 = Can't move through block
        for(int row = 0; row < mapHeight; row++)
        {
            for(int col = 0; col < mapWidth; col++)
            {
                Tilemap[row][col].Draw(g,row, col, x, y, tileSize);         
            }
        
        
        }
    }
}
