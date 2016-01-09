package mp3;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class MP3Player {
	static File file;
	static FileInputStream fis;
	static BufferedInputStream bis;
	static Player player;
	static Thread one;
	
	
	public static void play(String s){
		try{
			file = new File(s);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			
			one = new Thread() {
			    public void run() {
			        try {
						player = new Player(bis);
						player.play();

			            Thread.sleep(1000);
			        } catch(InterruptedException v) {
			            System.out.println(v);
			        } catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }  
			};
			one.start();
			
		}catch(IOException e){}
	}
	
	
	public static void playI(String s,int n){

		try{
			file = new File(s);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);

			try {
				player = new Player(bis);
			} catch (JavaLayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 one = new Thread() {
			    public void run() {
			        try {

							player.play();
			            Thread.sleep(1000);
			        } catch(InterruptedException v) {
			            System.out.println(v);
			        } catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }  
			};
			
		}catch(IOException e){}
		
		
		
	}
	
	public static void stop(){
		System.out.println(Thread.activeCount());
		try {
			one.wait(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}