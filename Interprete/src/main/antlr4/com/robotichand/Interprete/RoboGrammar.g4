grammar RoboGrammar;

program: PROGRAM OPEN_BRAC sentence* CLOSE_BRAC;

sentence: println;

println: PRINTLN OPEN_PAR expression CLOSE_PAR SEMICOLON
		{System.out.println($expression.value);};

expression returns [Object value]: 
		NUMBER {$value = Integer.parseInt($NUMBER.text);}
		| 
		ID {$value = $ID.text;};

PROGRAM: 'program';
LET: 'let';
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

