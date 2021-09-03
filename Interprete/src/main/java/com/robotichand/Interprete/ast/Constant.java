package com.robotichand.Interprete.ast;

public class Constant implements ASTNode {
	
	private Object value;
	
	public Constant(Object value) {
		super();
		this.value = value;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return value;
	}

}
