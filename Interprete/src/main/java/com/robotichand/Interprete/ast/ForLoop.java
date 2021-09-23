package com.robotichand.Interprete.ast;

import java.util.List;
import java.util.Map;

public class ForLoop implements ASTNode{
	private String name;
	private String range;
	private ASTNode startRange;
	private ASTNode endRange;
	private List<ASTNode> body;
	
	public ForLoop(String name, ASTNode startRange, String range, ASTNode endRange, List<ASTNode> body){
		super();
		this.name = name;
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
		int cont = start;
		symbolTable.put(name, start);
		if(range.equals("..")){
			for(int i = start; i < end; i++) {
				for (ASTNode n : body) {
					cont++;
					n.execute(symbolTable);
					symbolTable.put(name, cont);
				}
			}		
		}
		
		else if(range.equals("..=")){
			for(int i = start; i <= end; i++) {
				
				for (ASTNode n : body) {
					cont++;
					n.execute(symbolTable);
					symbolTable.put(name, cont);
				}
			}		
		}
		//symbolTable.clear();
		return null;
	
	}
}
