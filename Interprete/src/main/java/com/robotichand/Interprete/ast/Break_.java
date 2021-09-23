package com.robotichand.Interprete.ast;

import java.util.List;
import java.util.Map;

public class Break_ implements ASTNode{
	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		
		return "break";

	}
}
