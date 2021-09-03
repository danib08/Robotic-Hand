package com.robotichand.Interprete.ast;

import java.util.List;

public class IfCond implements ASTNode {
	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;
	
	public IfCond(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		if ((boolean)condition.execute()) {
			for (ASTNode n : body) {
				n.execute();
			}
		}
		else {
			for(ASTNode n : elseBody) {
				n.execute();
			}
		}
		return null;
	}

}
