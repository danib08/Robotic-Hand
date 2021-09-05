package com.robotichand.Interprete.ast;

import java.util.Map;

public class VarAssign implements ASTNode {
	
	private String name;
	private ASTNode expression;
	
	
	public VarAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}


	@Override
	public Object execute(Map<String, Object> symbolTable) {
		
		int length = name.length();
		
		if (length >= 3 && length <= 15) {
			
			boolean var_exists = symbolTable.containsKey(name);
			
			if (expression.execute(symbolTable) == null){
				System.out.println("The assigned variable is not defined");
			}
			else if (!var_exists) {
				symbolTable.put(name, expression.execute(symbolTable));
			}
			else {
				 Object original_class = (symbolTable.get(name)).getClass();
				
				if ((expression.execute(symbolTable)).getClass() == original_class) {
					symbolTable.put(name, expression.execute(symbolTable));
				}
				else {
					String type;
					if (original_class == Boolean.class) {
						type = "boolean";
					}
					else {
						type = "integer";
					}
					System.out.println("Expected " + type + " type. Can't convert different types." );
				}
			}
		}
		else {
			System.out.println("Identifier must be of length between 3 and 15 characters");
		}

		
	return null;
	}

}
