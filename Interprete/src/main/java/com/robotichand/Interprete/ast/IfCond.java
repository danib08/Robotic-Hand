package com.robotichand.Interprete.ast;

import java.util.List;

public class IfCond implements ASTNode {
	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseIfConds;
	private List<List<ASTNode>> elseIfBodies;
	private List<ASTNode> elseBody;
	
	public IfCond(ASTNode condition, List<ASTNode> body, List<ASTNode> elseIfConds, List<List<ASTNode>> elseIfBodies, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseIfConds = elseIfConds;
		this.elseIfBodies = elseIfBodies;
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
			int cont = 0;
			boolean cumple = false;
			for (ASTNode c : elseIfConds) {
				if ((boolean)c.execute()) {
					cumple = true;
					break;
				}
				cont++;
			}
			if (cumple) {
				List<ASTNode> elseIfBodyCumple = elseIfBodies.get(cont);
				for (ASTNode n : elseIfBodyCumple) {
					n.execute();
				}
			}
			else {
				for(ASTNode n : elseBody) {
					n.execute();
				}
			}
			
		}
		return null;
	}

}
