grammar RoboGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
	import java.lang.Math;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<String, Object>();
}


sentence: (println | var_assign)*;

var_assign: LET ID ASSIGN expression SEMICOLON
		{symbolTable.put($ID.text, $expression.value);};
		
primitive_function: opera | println;

println: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{System.out.println($expression.value);};

opera returns [int result]: OPERA OPEN_PAR operator COMMA o1 = expression COMMA o2 = expression CLOSE_PAR 
		{
			if(($o1.value).getClass() == Boolean.class || ($o2.value).getClass() == Boolean.class){
				System.out.println("Invalid type. Expected Integer, instead got boolean");
			}else{
				int result;
				if (($operator.text).equals("+")) {
					result = ((int)$o1.value + (int)$o2.value);
					
				}else if(($operator.text).equals("*")){
					result = ((int)$o1.value * (int)$o2.value);
				
				}else if (($operator.text).equals("/")){
					result = ((int)$o1.value / (int)$o2.value);
					
				}else if (($operator.text).equals("-")){
					result = ((int)$o1.value - (int)$o2.value);
					
				}else{
					result = (int)(Math.pow((int)$o1.value, (int)$o2.value));
					
				}
				$result = result;
				}
		};

expression returns [Object value]: 
		NUMBER {$value = Integer.parseInt($NUMBER.text);}
		| 
		ID {$value = symbolTable.get($ID.text);}
		|
		opera {$value = $opera.result;}
		|
		bool {$value = $bool.value;};
		
bool returns[boolean value]: 
		TRUE {$value = true;} 
		| 
		FALSE {$value = false;};
		
operator: SUM | MINUS | MULT | DIV | EXP;


PRINTLN: 'println!';
LET: 'let';
OPERA: 'OPERA';
TRUE:'true';
FALSE:'false';

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

