package com.robotichand.Interprete.ast;

public class PrintLn implements ASTNode {

	private ASTNode data;
	
	public PrintLn(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		System.out.println(data.execute());
		return null;
	}

}
