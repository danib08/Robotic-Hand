package com.robotichand.Interprete.ast;

import java.util.List;
import java.util.Map;

public class While implements ASTNode{
	private List<ASTNode> body;
	private ASTNode exp1;
	private String comparator;
	private ASTNode exp2;
	private int start;
	private int end;
	
	
	public While(ASTNode exp1, String comparator, ASTNode exp2, List<ASTNode> body) {
		super();
		this.exp1 = exp1;
		this.comparator = comparator;
		this.exp2 = exp2;
		this.body = body;
	}	

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		start = (int) exp1.execute(symbolTable);
		end = (int) exp2.execute(symbolTable);
		if(comparator.equals("<")) {
			while(start < end) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals("<=")) {
			while(start <= end) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals(">")) {
			while(start > end) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals(">=")) {
			while(start >= end) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals("==")) {
			while(start == end) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}else if(comparator.equals("<>")) {
			while(start != end) {
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				start++;
			}
		}
		
		return null;
	}
}
