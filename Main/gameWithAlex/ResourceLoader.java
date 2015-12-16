package gameWithAlex;

import java.io.InputStream;

final public class ResourceLoader 
{
	//what it says it does but for only images
	public static InputStream load(String path)
	{
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if(input == null)
			input = ResourceLoader.class.getResourceAsStream("/" +path);
		return input;
		
	}
}
