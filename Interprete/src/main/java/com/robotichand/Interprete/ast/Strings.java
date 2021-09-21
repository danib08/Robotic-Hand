package com.robotichand.Interprete.ast;

import java.util.Map;
import java.util.List;

public class Strings implements ASTNode {

	private List<String> fingers;

	public Strings(List<String> fingers) {
		super();
		this.fingers = fingers;
	}
	
	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		return fingers;
	}

}
