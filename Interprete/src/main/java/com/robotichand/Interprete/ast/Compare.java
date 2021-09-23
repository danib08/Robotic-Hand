package com.robotichand.Interprete.ast;

import java.util.Map;

public class Compare implements ASTNode{
	
	private ASTNode o1;
	private String operator;
	private ASTNode o2;

	public Compare(ASTNode o1, String operator, ASTNode o2) {
		super();
		this.o1 = o1;
		this.operator = operator;
		this.o2 = o2;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		boolean result = false;
		if ((o1.execute(symbolTable)).getClass() == Boolean.class && (o2.execute(symbolTable)).getClass() == Boolean.class &&
				(operator.equals("&&") || operator.equals("||"))) {
			if (operator.equals("&&")) {
				result = ((boolean) o1.execute(symbolTable) && (boolean) o2.execute(symbolTable));
			} else if (operator.equals("||")) {
				result = ((boolean) o1.execute(symbolTable) || (boolean) o2.execute(symbolTable));
			}
		} else if ((o1.execute(symbolTable)).getClass() == Integer.class && (o2.execute(symbolTable)).getClass() == Integer.class && 
				(operator.equals("<") || operator.equals(">") || operator.equals("<=") || operator.equals(">=") || operator.equals("=="))) {
			if (operator.equals("<")) {
				result =  ((int) o1.execute(symbolTable) < (int) o2.execute(symbolTable));
			} else if (operator.equals(">")) {
				result =  ((int) o1.execute(symbolTable) > (int) o2.execute(symbolTable));
			} else if (operator.equals("<=")) {
				result =  ((int) o1.execute(symbolTable) <= (int) o2.execute(symbolTable));
			} else if (operator.equals(">=")) {
				result =  ((int) o1.execute(symbolTable) >= (int) o2.execute(symbolTable));
			} else if (operator.equals("==")) {
				result =  ((int) o1.execute(symbolTable) == (int) o2.execute(symbolTable));
			}
		}
		return result;
	}

}
