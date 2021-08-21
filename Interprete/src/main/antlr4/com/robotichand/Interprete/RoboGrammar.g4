grammar RoboGrammar;

start
:
	'hello' 'world'
;

WS
:
	[ \t\r\n]+ -> skip
;