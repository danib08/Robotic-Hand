grammar RoboGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<String, Object>();
}

program: PROGRAM OPEN_BRAC sentence* CLOSE_BRAC;

sentence: primitive_function | var_assign;

var_assign: LET ID ASSIGN expression SEMICOLON
		{symbolTable.put($ID.text, $expression.value);};
		
primitive_function: opera | println;

println: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{System.out.println($expression.value);};

opera: OPERA OPEN_PAR operator COMMA o1=operando COMMA o2=operando CLOSE_PAR SEMICOLON
		{
			if (($operator.text).equals("+")) {
				int result = Integer.parseInt($o1.text) + Integer.parseInt($o2.text);
				System.out.println(result);
			}
		};

expression returns [Object value]: 
		NUMBER {$value = Integer.parseInt($NUMBER.text);}
		| 
		ID {$value = symbolTable.get($ID.text);};

constant returns [Object value]: 
		NUMBER {$value = Integer.parseInt($NUMBER.text);};
		
operator: SUM | MINUS | MULT | DIV | EXP;

operando: NUMBER | ID | OPERA;

PROGRAM: 'program';
PRINTLN: 'println!';
LET: 'let';
OPERA: 'OPERA';

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

