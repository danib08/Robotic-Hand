package com.robotichand.Interprete.ast;

import java.util.Map;

public class BreakLoop implements ASTNode{
	
	String breakString;
	
	
	
	public BreakLoop(String breakString) {
		super();
		this.breakString = "break";
	}



	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		return breakString;

	}
}
