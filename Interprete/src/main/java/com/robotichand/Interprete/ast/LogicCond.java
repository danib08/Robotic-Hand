package com.robotichand.Interprete.ast;

import java.util.Map;

public class LogicCond implements ASTNode{
	
	private ASTNode o1;
	private String operator;
	private ASTNode o2;

	public LogicCond(ASTNode o1, String operator, ASTNode o2) {
		super();
		this.o1 = o1;
		this.operator = operator;
		this.o2 = o2;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		boolean result = true;
		if (operator.equals("&&")) {
			result = ((boolean) o1.execute(symbolTable) & (boolean) o2.execute(symbolTable));
		} else if (operator.equals("||")) {
			result = ((boolean) o1.execute(symbolTable) | (boolean) o2.execute(symbolTable));
		}
		return result;
	}

}
