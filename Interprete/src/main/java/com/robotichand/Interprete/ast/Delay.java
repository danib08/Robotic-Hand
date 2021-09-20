package com.robotichand.Interprete.ast;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.robotichand.Interprete.MainProgram;


public class Delay implements ASTNode {
	
	private ASTNode constant;
	private String rango;
	


	public Delay(ASTNode constant, String rango) {
		super();
		this.constant = constant;
		this.rango = rango;
	}


	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		System.out.println(rango);
		rango = rango.replace("\"", "");
		System.out.println(rango);
		int cantidad = (int) constant.execute(symbolTable);
		int tiempo = 0;
		boolean valid = true;
		
		switch(rango) {
			case "Mil":
				tiempo = cantidad;
				break;
			case "Seg":
				tiempo = cantidad*1000;
				break;
			case "Min":
				tiempo = cantidad*60*1000;
				break;
			default:
				System.out.println("Time range must be: Mil, Seg or Min.");
				valid = false;
		}
		
		if (valid) {
			String data = "D" + tiempo;
			
			try {
	            //MainProgram.arduino.sendData(data);
	            //System.out.println(data);
				if (tiempo <= 1500) {
					Thread.sleep(tiempo);
				}else {
					Thread.sleep(tiempo-1500);
				}
	            
	        } catch (Exception ex) {
	            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
	        }		
			
		}
		
		return null;
	}

}
