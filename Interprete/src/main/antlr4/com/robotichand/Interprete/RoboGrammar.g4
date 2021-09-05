grammar RoboGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
	import java.lang.Math;
	import com.robotichand.Interprete.ast.ASTNode;
	import com.robotichand.Interprete.ast.Constant;
	import com.robotichand.Interprete.ast.IfCond;
	import com.robotichand.Interprete.ast.PrintLn;
	import com.robotichand.Interprete.ast.VarAssign;
	import com.robotichand.Interprete.ast.VarRef;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<String, Object>();
}


program:{
		List<ASTNode> body = new ArrayList<ASTNode>();
		Map<String,Object> symbolTable = new HashMap<String,Object>();
	}
	(sentence {body.add($sentence.node);})*
	{
		for(ASTNode n : body) {
			n.execute(symbolTable);
		} 		
	};

sentence returns [ASTNode node]: println {$node = $println.node;} 
				| conditional {$node = $conditional.node;}
				| var_assign {$node = $var_assign.node;};

conditional returns [ASTNode node]: IF (c1 = condition) 
			{
				List<ASTNode> body = new ArrayList<ASTNode>();
				List<List<ASTNode>> elseIfBodies = new ArrayList<List<ASTNode>>();
				List<ASTNode> elseIfConds = new ArrayList<ASTNode>();
			}
			OPEN_BRAC (s1 = sentence {body.add($s1.node);})* CLOSE_BRAC
			
			(ELSE_IF {List<ASTNode> elseIfBody = new ArrayList<ASTNode>();}
			(c2 = condition {elseIfConds.add($c2.node);})* 
				OPEN_BRAC (s2 = sentence {elseIfBody.add($s2.node);})* CLOSE_BRAC {elseIfBodies.add(elseIfBody);})*
				
			(ELSE 
			{
				List<ASTNode> elseBody = new ArrayList<ASTNode>();
			}
			OPEN_BRAC (s3 = sentence {elseBody.add($s3.node);})* CLOSE_BRAC
			{
				$node = new IfCond($c1.node, body, elseIfConds, elseIfBodies, elseBody);
			})?;

condition returns [ASTNode node]: bool {$node = $bool.node;};

println returns [ASTNode node]: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{
			$node = new PrintLn($expression.node);
		};
		
var_assign returns [ASTNode node]:
		LET ID ASSIGN expression SEMICOLON 
		{$node = new VarAssign($ID.text, $expression.node);};


//opera returns [int result]: OPERA OPEN_PAR operator COMMA o1 = expression COMMA o2 = expression CLOSE_PAR 
	//	{
		//	if(($o1.value).getClass() == Boolean.class || ($o2.value).getClass() == Boolean.class){
			//	System.out.println("Invalid type. Expected Integer, instead got boolean");
			//}else{
			//	int result;
			//	if (($operator.text).equals("+")) {
				//	result = ((int)$o1.value + (int)$o2.value);
					//
				//}else if(($operator.text).equals("*")){
					//result = ((int)$o1.value * (int)$o2.value);
				//
				//}else if (($operator.text).equals("/")){
					//result = ((int)$o1.value / (int)$o2.value);
					//
				//}else if (($operator.text).equals("-")){
					//result = ((int)$o1.value - (int)$o2.value);
				//	
				//}else{
					//result = (int)(Math.pow((int)$o1.value, (int)$o2.value));
					//
				//}
				//$result = result;
				//}
		//};
		
bool returns [ASTNode node]: //REVISAR REDUNDANCIA
		BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));};
		
expression returns [ASTNode node]: 
		NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text));}
		| 
		ID {$node = new VarRef($ID.text);}
		//|
		//opera {$value = $opera.result;}
		| 
		BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));};
		
operator: SUM | MINUS | MULT | DIV | EXP;


PRINTLN: 'println!';
LET: 'let';
OPERA: 'OPERA';
BOOLEAN: 'true' | 'false';
IF: 'if';
ELSE_IF: 'else if';
ELSE: 'else';

ASSIGN: '=';
SEMICOLON: ';';
COMMA: ',';

SUM: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
EXP: '**';

OPEN_PAR: '(';
CLOSE_PAR: ')';
OPEN_BRAC: '{';
CLOSE_BRAC: '}';

NUMBER: [0-9]+;

ID: [a-zA-Z#_?][a-zA-Z0-9]*;

WS: [ \n\t\r]+ -> skip;

