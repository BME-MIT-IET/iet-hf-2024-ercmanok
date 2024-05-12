package game.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class InfoLog
{
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss\n");
	static LocalDateTime now = LocalDateTime.now();
	static String infoLog = dtf.format(now);
	//Itt minden is kiírásra kerül
	public static void WriteInfoLog(String newInfo)
	{
		//TODO
		infoLog = String.format("%s%s\n", infoLog, newInfo);
	}
	
	public static void WriteOutInfoLog()
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


		//itt írjuk ki fájlba a teljes infolog-ot.
		try 
    	{
    		FileOutputStream fos = new FileOutputStream(new File("infolog"+dateFormat.format(date)+".txt"));
    		ObjectOutputStream oos = new ObjectOutputStream(fos);

    		oos.writeObject(infoLog);

    		oos.close();    		
    		fos.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
}