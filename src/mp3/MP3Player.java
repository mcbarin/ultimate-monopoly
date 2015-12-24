package mp3;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class MP3Player {
	static File file;
	static FileInputStream fis;
	static BufferedInputStream bis;
	static Player player;
	
	
	public static void play(String s){
		try{
			file = new File(s);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			
			try{
				player = new Player(bis);
				player.play();
				
			}catch(JavaLayerException ex){}
			
		}catch(IOException e){}
	}
	
	public static void stop(){
				player.close();
		
	}

}