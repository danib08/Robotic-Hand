package com.robotichand.Interprete.ast;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Loop implements ASTNode{
	private List<ASTNode> body;
	//private List<ASTNode> loopBody;
	
	public Loop(List<ASTNode> body) {
		super();
		this.body = body;
		//this.loopBody = new ArrayList<ASTNode>();
	}

	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		boolean hasBreak = false;
		/*
		for (ASTNode n : body) {
			if (n.getClass().equals(List.class)) {
				addSentenceList((List<ASTNode>) n);
			} else {
				loopBody.add(n);
			}
		}
		*/
		for (ASTNode n : body) {
			if(n.getClass().equals(com.robotichand.Interprete.ast.BreakLoop.class)){
				hasBreak = true;				
			}
		}
		if(hasBreak) {
			for (ASTNode n : body) {
				n.execute(symbolTable);
				if(n.getClass().equals(com.robotichand.Interprete.ast.BreakLoop.class)){
					break;
				}
			}
		}
		else {
			System.out.println("Error: Loop needs break");
		}
		return null;
	}
	/*
	private void addSentenceList(List<ASTNode> codeBlock) {
		if (codeBlock.getClass().equals(List.class)) {
			addSentenceList(codeBlock);
		} else {
			for (ASTNode n : codeBlock) {
				loopBody.add(n);
			}
		}
	}
	*/
}
