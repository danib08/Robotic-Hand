grammar RoboGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
	import java.lang.Math;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<String, Object>();
}

program: {
		List<ASTNode node> body = new ArrayList<ASTNode>();
	}
	(println {body.add($println.node);} | conditional {body.add($conditional.node);})*
	{
		for(ASTNode n : body) {
			n.execute();
		} 		
	}
	;

//program returns [ASTNode node]: (println | var_assign | conditional)*;

sentence returns [ASTNode node]: println {$node = $println.node};

//sentence returns [ASTNode node]: println | var_assign;


//var_assign returns [ASTNode node]: LET ID ASSIGN expression SEMICOLON
//		{
//			boolean var_exists = symbolTable.containsKey($ID.text);
//			
//			if ($expression.value == null){
//				System.out.println($ID.text + " not defined");
//			}
//			else if (!var_exists) {
//				symbolTable.put($ID.text, $expression.value);
//			}
//			else {
//				 Object original_class = (symbolTable.get($ID.text)).getClass();
//				
//				if (($expression.value).getClass() == original_class) {
//					symbolTable.put($ID.text, $expression.value);
//				}
//				else {
//					String type;
//					if (original_class == Boolean.class) {
//						type = "boolean";
//					}
//					else {
//						type = "integer";
//					}
//					System.out.println("Expected " + type + " type. Can't convert different types." );
//				}
//			}
//		};

conditional returns [ASTNode node]: IF condition 
			{
				List<ASTNode node> body = new ArrayList<ASTNode>();
			}
			OPEN_BRAC (s1 = sentence {body.add($s1.node);})* CLOSE_BRAC
			ELSE 
			{
				List<ASTNode node> elseBody = new ArrayList<ASTNode>();
			}
			OPEN_BRAC (s2 = sentence {elseBody.add($s2.node);})* CLOSE_BRAC
			{
				$node = new If($condition.node, body, elseBody);
			};
		
//conditional returns [ASTNode node]: IF condition OPEN_BRAC (sentence)* CLOSE_BRAC
			//(ELSE_IF condition OPEN_BRAC (sentence*) CLOSE_BRAC)*
			//(ELSE OPEN_BRAC (sentence)* CLOSE_BRAC)?;

condition returns [ASTNode node]: BOOLEAN {$node = $BOOLEAN.node};

println returns [ASTNode node]: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{
			$node = new PrintLn($expression.node);
		};

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

expression returns [ASTNode node]: 
		NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text));}
		//| 
		//ID {$value = symbolTable.get($ID.text);}
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

