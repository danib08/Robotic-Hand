package com.robotichand.Interprete;

import panamahitek.Arduino.PanamaHitek_Arduino;
import java.io.IOException;
import com.robotichand.Ide.Window;

public class MainProgram {

    public static PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    Window window;
		
		try {
			window = new Window(arduino);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
