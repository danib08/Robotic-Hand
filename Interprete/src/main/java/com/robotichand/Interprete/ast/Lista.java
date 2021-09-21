package com.robotichand.Interprete.ast;

import java.util.Map;
import java.util.List;


public class Lista implements ASTNode {
	
	private List<String> fingers;
	
	public Lista(List<String> fingers) {
		super();
		this.fingers = fingers;
	}


	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		for (String finger : fingers) {
			System.out.println(finger);
		}
		return fingers;
	}

}
