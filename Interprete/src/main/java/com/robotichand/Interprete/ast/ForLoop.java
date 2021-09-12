package com.robotichand.Interprete.ast;

import java.util.List;
import java.util.Map;

public class ForLoop implements ASTNode{
	private ASTNode initialExpression;
	private String range;
	private ASTNode startRange;
	private ASTNode endRange;
	private List<ASTNode> body;
	private int index;
	
	public ForLoop(ASTNode initialExpression, ASTNode startRange, String range, ASTNode endRange, List<ASTNode> body){
		super();
		this.initialExpression = initialExpression;
		this.startRange = startRange;
		this.range = range;
		this.endRange = endRange;
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		int start = (int) startRange.execute(symbolTable);
		int end = (int) endRange.execute(symbolTable);
		
		if(range.equals("..")){
			for(int i = start; i < end; i++) {
				//index = i;
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
			}		
		}
		
		else if(range.equals("..=")){
			for(int i = start; i <= end; i++) {
				
				for (ASTNode n : body) {
					n.execute(symbolTable);
				}
				//index = i;
			}		
		}
		return index;
	
	}
}
