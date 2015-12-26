package gameWithAlex;

// @author Colin Wakefield

import java.awt.image.BufferedImage;

public interface Character
{
   public boolean living();
   public BufferedImage[] getsprite(String s);
   public void moveup(boolean b );
   public void movedown(boolean b );
   public void moveleft(boolean b );
   public void moveright(boolean b );
   public double getx();
   public double gety();
   public void setx(double x);
   public void sety(double y);
}
