package com.robotichand.Interprete.ast;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.robotichand.Interprete.MainProgram;


public class Delay implements ASTNode {
	
	private ASTNode constant;
	private String rango;
	private MainProgram program = new MainProgram();


	public Delay(ASTNode constant, String rango) {
		super();
		this.constant = constant;
		this.rango = rango;
	}


	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		
		int cantidad = (int) constant.execute(symbolTable);
		int tiempo = 0;
		boolean valid = true;
		
		switch(rango) {
			case "Mil":
				tiempo = cantidad;
		
			case "Seg":
				tiempo = cantidad*1000;
				
			case "Min":
				tiempo = cantidad*60*1000;
				
			default:
				System.out.println("Time range must be: Mil, Seg or Min.");
				valid = false;
		}
		
		if (valid) {
			String data = "D" + tiempo;
			
			try {
	            program.arduino.sendData("data");
	        } catch (Exception ex) {
	            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex)
	        }		
			
		}
		
		return null;
	}

}
