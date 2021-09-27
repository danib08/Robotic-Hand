package com.robotichand.Interprete.ast;

import java.util.List;
import java.util.Map;

public class While implements ASTNode{
	private List<ASTNode> body;
	private String exp1;
	private String comparator;
	private String exp2;
	
	
	public While(String exp1, String comparator, String exp2, List<ASTNode> body) {
		super();
		this.exp1 = exp1;
		this.comparator = comparator;
		this.exp2 = exp2;
		this.body = body;
	}	

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		/*
		if(comparator.equals("<")) {
			while ((int) symbolTable.get(exp1) < (int) symbolTable.get(exp2)) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
			}
			*/
		int start = (int) symbolTable.get(exp1);
		if(comparator.equals("<")) {
			while (start < (int) symbolTable.get(exp2)) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		
		}else if(comparator.equals("<=")) {
			while(start <= (int) symbolTable.get(exp2)) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals(">")) {
			while(start > (int) symbolTable.get(exp2)) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals(">=")) {
			while(start >= (int) symbolTable.get(exp2)) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals("==")) {
			while(start == (int) symbolTable.get(exp2)) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals("<>")) {
			while(start != (int) symbolTable.get(exp2)) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}
		return null;
	}
}
