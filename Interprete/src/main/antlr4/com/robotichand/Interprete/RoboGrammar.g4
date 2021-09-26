grammar RoboGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
	import java.lang.Math;
	import com.robotichand.Interprete.ast.ASTNode;
	import com.robotichand.Interprete.ast.Constant;
	import com.robotichand.Interprete.ast.IfCond;
	import com.robotichand.Interprete.ast.Compare;
	import com.robotichand.Interprete.ast.PrintLn;
	import com.robotichand.Interprete.ast.VarAssign;
	import com.robotichand.Interprete.ast.VarRef;
	import com.robotichand.Interprete.ast.Opera;
	import com.robotichand.Interprete.ast.ForLoop;
	import com.robotichand.Interprete.ast.While;
	import com.robotichand.Interprete.ast.Loop;
	import com.robotichand.Interprete.ast.BreakLoop;
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
				| for_loop {$node = $for_loop.node;}
				| while_loop {$node = $while_loop.node;}
				| loop {$node = $loop.node;}
				| break_loop {$node = $break_loop.node;};
				
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
			

condition returns [ASTNode node]: bool {$node = $bool.node;}
				| (o1 = bool) logic (o2 = bool) 
				{
					$node = new Compare($o1.node, $logic.text, $o2.node);
				}
				| (n1 = num) algorithmic (n2 = num)
				{
					$node = new Compare($n1.node, $algorithmic.text, $n2.node);
				};

println returns [ASTNode node]: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{
			$node = new PrintLn($expression.node);
		};

opera returns [ASTNode node]: OPERA OPEN_PAR operator COMMA o1 = num COMMA o2 = num CLOSE_PAR 
		{
			$node = new Opera($operator.text, $o1.node, $o2.node);		
		};

var_assign returns [ASTNode node]:
		LET ID ASSIGN expression SEMICOLON 
		{$node = new VarAssign($ID.text, $expression.node);};

for_loop returns [ASTNode node]: FOR ID IN startRange = expression range endRange = expression
			{
				List<ASTNode> body = new ArrayList<ASTNode>();
				
			}
			OPEN_BRAC (sc = sentence {body.add($sc.node);})* CLOSE_BRAC
			{
				
				$node = new ForLoop($ID.text, $startRange.node, $range.text, $endRange.node, body);
			};
			
while_loop returns [ASTNode node]: WHILE OPEN_PAR (exp1 = ID algorithmic exp2 = ID) CLOSE_PAR
			{
				List<ASTNode> body = new ArrayList<ASTNode>();
				
			}
			OPEN_BRAC (sc = sentence {body.add($sc.node);})* CLOSE_BRAC
			{
				$node = new While($exp1.text, $algorithmic.text, $exp2.text, body);
			};
fn returns [ASTNode node]:;

loop returns [ASTNode node]: LOOP
			{ 
				List<ASTNode> body = new ArrayList<ASTNode>();
			} 
			OPEN_BRAC (sc = sentence {body.add($sc.node);})*  CLOSE_BRAC
			{
				$node = new Loop(body);
			};
break_loop returns [ASTNode node]: BREAK SEMICOLON
			{
				$node = new BreakLoop($BREAK.text);
			};	   

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
		
num returns [ASTNode node]:
		NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text));}
		| 
		ID {$node = new VarRef($ID.text);}
		|
		opera {$node = $opera.node;};
		
operator: SUM | MINUS | MULT | DIV | EXP;

range: RANGE | RANGE_END;

logic: AND | OR;

algorithmic: LESS | GREATER | LESSEQUALS | GREATEREQUALS | EQUALS | DIFFERENT;



PRINTLN: 'println!';
LET: 'let';
OPERA: 'OPERA';
BOOLEAN: 'true' | 'false';
IF: 'if';
ELSE_IF: 'else if';
ELSE: 'else';
FOR: 'for';
IN: 'in';
WHILE: 'while';
LOOP : 'loop';
BREAK: 'break';

ASSIGN: '=';
SEMICOLON: ';';
COMMA: ',';
RANGE: '..';
RANGE_END: '..=';

SUM: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
EXP: '**';

AND: '&&';
OR: '||';

LESS: '<';
GREATER: '>';
LESSEQUALS: '<=';
GREATEREQUALS: '>=';
EQUALS: '==';
DIFFERENT: '<>';
 
OPEN_PAR: '(';
CLOSE_PAR: ')';
OPEN_BRAC: '{';
CLOSE_BRAC: '}';

NUMBER: [0-9]+;

ID: [a-zA-Z#_?][a-zA-Z0-9]*;

COMMENT: ( '@' ~[\r\n]* '\r'? '\n') -> skip;

WS: [ \n\t\r]+ -> skip;

