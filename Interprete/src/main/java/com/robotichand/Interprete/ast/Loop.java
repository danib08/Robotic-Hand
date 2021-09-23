package com.robotichand.Interprete.ast;

import java.util.List;
import java.util.Map;

public class Loop implements ASTNode{
	private List<ASTNode> body;
	
	public Loop(List<ASTNode> body) {
		super();
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		boolean hasBreak = false;
		
		for (ASTNode n : body) {
			if(symbolTable.toString().equals("break")){
				System.out.println("n: " + n);
				hasBreak = true;				
			}
		}
		if(hasBreak) {
			for (ASTNode n : body) {
				n.execute(symbolTable);
				if(n.execute(symbolTable).toString().equals("break")){
					break;
				}
			}
		}
		else {
			System.out.println("Error: Loop needs break");
		}
		return null;
	}
	
}
