package com.robotichand.Interprete.ast;

import java.util.Map;

public class Move implements ASTNode {
	
	private String finger;
	private ASTNode condition;
	
	public Move(String finger, ASTNode condition) {
		super();
		this.finger = finger;
		this.condition = condition;
	}


	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		if (finger.length() == 1 && finger.equals(finger.toUpperCase())) {
			System.out.println(finger);
		}
		else {
			System.out.println("First parameter must be an uppercase letter.");
		}
		return null;
	}

}
