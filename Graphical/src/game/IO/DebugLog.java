package game.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class DebugLog
{
	static String debugLog = "Aktuális futáshoz tartozó logszintű infók";
	
	//Itt csak a debugoláshoz szükséges infók kerülnek kiírásra
	public static void WriteDebugLog(String newDebugInfo)
	{
		debugLog = new StringBuilder().append(debugLog).append(newDebugInfo + "\n").toString();
	}
	
	public static void WriteOutDebugLog()
	{
		//itt írjuk ki fájlba a teljes debuglog-ot.
		try 
    	{
    		FileOutputStream fos = new FileOutputStream(new File("debuglog.txt"));
    		ObjectOutputStream oos = new ObjectOutputStream(fos);

    		oos.writeObject(debugLog);

    		oos.close();    		
    		fos.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
}