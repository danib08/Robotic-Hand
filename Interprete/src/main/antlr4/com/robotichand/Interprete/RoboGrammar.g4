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
	import com.robotichand.Interprete.ast.Opera;
	import com.robotichand.Interprete.ast.Move;	
	import com.robotichand.Interprete.ast.Delay;	
	import com.robotichand.Interprete.ast.Lista;	
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
				| var_assign {$node = $var_assign.node;}
				| opera {$node = $opera.node;}
				| move {$node = $move.node;}
				| delay {$node = $delay.node;};
				
				
move returns [ASTNode node]: MOVE OPEN_PAR (list) COMMA bool CLOSE_PAR SEMICOLON
		{
			$node = new Move($list.node , $bool.node);
		};
				

conditional returns [ASTNode node]: IF (c1 = condition) 
			{
				List<ASTNode> body = new ArrayList<ASTNode>();
				List<List<ASTNode>> elseIfBodies = new ArrayList<List<ASTNode>>();
				List<ASTNode> elseIfConds = new ArrayList<ASTNode>();
				List<ASTNode> elseBody = new ArrayList<ASTNode>();
			}
			OPEN_BRAC (s1 = sentence {body.add($s1.node);})* CLOSE_BRAC
			
			(ELSE_IF {List<ASTNode> elseIfBody = new ArrayList<ASTNode>();}
			(c2 = condition {elseIfConds.add($c2.node);})* 
				OPEN_BRAC (s2 = sentence {elseIfBody.add($s2.node);})* CLOSE_BRAC 
				{elseIfBodies.add(elseIfBody);})*
				
			(ELSE OPEN_BRAC (s3 = sentence {elseBody.add($s3.node);})* CLOSE_BRAC)?
			{
				$node = new IfCond($c1.node, body, elseIfConds, elseIfBodies, elseBody);
			};
			

condition returns [ASTNode node]: bool {$node = $bool.node;};

println returns [ASTNode node]: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{
			$node = new PrintLn($expression.node);
		};
		
delay returns [ASTNode node]: DELAY OPEN_PAR NUMBER COMMA STRING CLOSE_PAR SEMICOLON
		{
			$node = new Delay(new Constant(Integer.parseInt($NUMBER.text)), $STRING.text);
		};


opera returns [ASTNode node]: OPERA OPEN_PAR operator COMMA o1 = expression COMMA o2 = expression CLOSE_PAR 
		{
			$node = new Opera($operator.text, $o1.node, $o2.node);		
		};

var_assign returns [ASTNode node]:
		LET ID ASSIGN expression SEMICOLON 
		{$node = new VarAssign($ID.text, $expression.node);};
		

bool returns [ASTNode node]:
		BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));}
		|
		ID {$node = new VarRef($ID.text);}
		;
		
expression returns [ASTNode node]: 
		NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text));}
		| 
		ID {$node = new VarRef($ID.text);}
		|
		opera {$node = $opera.node;}
		| 
		BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));};
		
list returns [ASTNode node]: OPEN_CUADR 
		{
			List<String> fingers = new ArrayList<String>();
		}
		((s1 = STRING {
			String finger = ($s1.text).replace("\"", "");
			fingers.add(finger);
		} 
		COMMA)* STRING {
			String finger = ($STRING.text).replace("\"", "");
			fingers.add(finger);
		}
		)? CLOSE_CUADR 
		
		{
			$node = new Lista(fingers);
		};
		
operator: SUM | MINUS | MULT | DIV | EXP;


PRINTLN: 'println!';
LET: 'let';
OPERA: 'OPERA';
BOOLEAN: 'true' | 'false';
IF: 'if';
ELSE_IF: 'else if';
ELSE: 'else';
MOVE: 'Move';
DELAY: 'Delay';

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
OPEN_CUADR: '[';
CLOSE_CUADR: ']';

NUMBER: [0-9]+;

STRING : '"' .*? '"' ;

ID: [a-zA-Z#_?][a-zA-Z0-9]*;

WS: [ \n\t\r]+ -> skip;

