package com.robotichand.Interprete.ast;

import java.util.Map;

public class Opera implements ASTNode {
	private String operator;
	private ASTNode o1;
	private ASTNode o2;
	
	public Opera(String operator, ASTNode o1, ASTNode o2) {
		super();
		this.operator = operator;
		this.o1 = o1;
		this.o2 = o2;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
	 	if((o1.execute(symbolTable)).getClass() == Boolean.class || (o2.execute(symbolTable)).getClass() == Boolean.class){
			System.out.println("Invalid type. Expected Integer, instead got boolean");
		}else{
			int result;
			if (operator.equals("+")) {
				result = ((int)o1.execute(symbolTable) + (int)o2.execute(symbolTable));
				
			}else if(operator.equals("*")){
				result = ((int)o1.execute(symbolTable) * (int)o2.execute(symbolTable));
			
			}else if (operator.equals("/")){
				result = ((int)o1.execute(symbolTable) / (int)o2.execute(symbolTable));
				
			}else if (operator.equals("-")){
				result = ((int)o1.execute(symbolTable) - (int)o2.execute(symbolTable));
				
			}else{
				result = (int)(Math.pow((int)o1.execute(symbolTable), (int)o2.execute(symbolTable)));
				
			}
			return result;
			}	
		return null;
	}

}
