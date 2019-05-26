package tema5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class FileUtil {
	
	private static BufferedWriter writer = null;
	
	public static void writeMapToFile(Map map,String path)
	{
		try {
			
			File logFile = new File(path);
			writer = new BufferedWriter(new FileWriter(logFile));
			map.forEach((k,v)->{ try {
				writer.write(k + " : "+ v + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}});
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeListToFile(List list, String path)
	{
		try {
			
			File logFile = new File(path);
			writer = new BufferedWriter(new FileWriter(logFile));
			list.forEach( x ->{ try {
				writer.write(x + " miliseconds"+ "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}});
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
