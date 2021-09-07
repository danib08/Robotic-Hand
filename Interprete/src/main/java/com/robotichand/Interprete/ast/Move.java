package com.robotichand.Interprete.ast;

import java.util.Map;

import com.robotichand.Arduino.Create;

public class Move implements ASTNode {
	
	private String finger;
	private ASTNode condition;
	private Create create = new Create();
	
	public Move(String finger, ASTNode condition) {
		super();
		this.finger = finger;
		this.condition = condition;
		create.conectar();
		
	}

	
	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		try {
			finger = finger.replace("\"", "");
			if ((condition.execute(symbolTable)).getClass() != Boolean.class) {
				System.out.println("The orientation parameter must be a boolean");
				return null;
			}
			
			if (finger.length() == 1 && finger.equals(finger.toUpperCase())) {
				switch(finger) {
					case "P":
						create.elevar("P");
						System.out.println("P");
						break;
					case "I":
						System.out.println("I");
						break;
					case "M":
						System.out.println("M");
						break;
					case "A":
						System.out.println("A");
						break;
					case "Q":
						System.out.println("Q");
						break;
					case "T":
						System.out.println("T");
						break;
					default:
						System.out.println("The finger parameter must be: P, I, M, A, Q, T");
				}
			}
			else {
				System.out.println("First parameter must be an uppercase letter.");
			}
		}catch(NullPointerException e) {
			System.out.println("The parameters must be String and Boolean");
		}
		return null;
	}

}
