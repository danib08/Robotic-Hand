package com.robotichand.Interprete.ast;

import java.util.List;
import java.util.Map;

public class While implements ASTNode{
	private ASTNode exp1;
	private ASTNode exp2;
	private List<ASTNode> body;
	private ASTNode comparator; 
	
	
	public While(ASTNode exp1, ASTNode comparator, ASTNode exp2, List<ASTNode> body) {
		super();
		this.exp1 = exp1;
		this.comparator = comparator;
		this.exp2 = exp2;
		this.body = body;
	}	

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		if(comparator.equals("<")) {
			while((int)exp1.execute(symbolTable) < (int)exp2.execute(symbolTable)) {
				
			}
		}else if(comparator.equals("<=")) {
			while((int)exp1.execute(symbolTable) <= (int)exp2.execute(symbolTable)) {
				
			}
		}else if(comparator.equals(">")) {
			while((int)exp1.execute(symbolTable) > (int)exp2.execute(symbolTable)) {
				
			}
		}else if(comparator.equals(">=")) {
			while((int)exp1.execute(symbolTable) >= (int)exp2.execute(symbolTable)) {
				
			}
		}else if(comparator.equals("==")) {
			while((int)exp1.execute(symbolTable) == (int)exp2.execute(symbolTable)) {
				
			}
		}else if(comparator.equals("<>")) {
			while((int)exp1.execute(symbolTable) < (int)exp2.execute(symbolTable)) {
				
			}
		}
		
		return null;
	}
}
