grammar RoboGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<String, Object>();
}

program: PROGRAM OPEN_BRAC sentence* CLOSE_BRAC;

sentence: println | var_assign;

var_assign: LET ID ASSIGN expression SEMICOLON
		{symbolTable.put($ID.text, $expression.value);};

println: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{System.out.println($expression.value);};

expression returns [Object value]: 
		NUMBER {$value = Integer.parseInt($NUMBER.text);}
		| 
		ID {$value = symbolTable.get($ID.text);};

constant returns [Object value]: 
		NUMBER {$value = Integer.parseInt($NUMBER.text);};

PROGRAM: 'program';
LET: 'let';
OPERA: 'OPERA';
PRINTLN: 'println!';

ASSIGN: '=';
SEMICOLON: ';';

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

