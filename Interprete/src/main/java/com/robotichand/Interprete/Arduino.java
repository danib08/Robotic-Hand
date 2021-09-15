package com.robotichand.Interprete;

import panamahitek.Arduino.PanamaHitek_Arduino;


public class Arduino {
    PanamaHitek_Arduino arduino;

	public Arduino() {
		super();
		this.arduino = new PanamaHitek_Arduino();
	}

	
	public void getPortsAvaiable() {
		arduino.getPortsAvailable();
	}
}
