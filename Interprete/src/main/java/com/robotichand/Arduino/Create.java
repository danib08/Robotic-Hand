package com.robotichand.Arduino;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import panamahitek.Arduino.*;

public class Create implements Runnable{
	PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino();
    
    
    public Create() {
		super();
		getPorts();
	}
    
	public void getPorts() {
    	if (Arduino.getPortsAvailable() > 0) {
            List lst = Arduino.getSerialPorts();
            for(int i=0; i<lst.size(); i++){
               System.out.println(lst.get(i));
            }
        } 
    }
	
    public void elevar(String finger) {
    	try {
            Arduino.sendData("1");
            System.out.println("ENVIADO");
        } catch (Exception ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void bajar(String dedo) {
    	try {
            Arduino.sendData("0");
        } catch (Exception ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconectar() {
    	try {
            Arduino.killArduinoConnection();
        } catch (Exception ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void conectar () {
    	try {
            Arduino.arduinoTX("COM4", 9600);

        } catch (Exception ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public void run() {
		conectar();
	}

}
