lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T15 : '.' ;
T16 : '~~~~~' ;
T17 : '/' ;
T18 : '-' ;
T19 : '!' ;
T20 : '@' ;
T21 : '$' ;
T22 : '%' ;
T23 : '&' ;
T24 : '+' ;
T25 : '=' ;
T26 : '*' ;
T27 : '<' ;
T28 : '>' ;
T29 : ':' ;
T30 : '?' ;
T31 : '[' ;
T32 : '(' ;
T33 : ',' ;
T34 : ']' ;
T35 : ')' ;
T36 : 'import' ;
T37 : '.*' ;
T38 : ';' ;
T39 : 'unit' ;
T40 : 'version' ;
T41 : 'implements' ;
T42 : '{' ;
T43 : 'properties' ;
T44 : 'provides' ;
T45 : '}' ;
T46 : 'requires' ;
T47 : 'meta' ;
T48 : 'advice' ;
T49 : 'sequential' ;
T50 : 'repositories' ;
T51 : 'when' ;
T52 : 'immutable' ;
T53 : '+=' ;
T54 : '-=' ;
T55 : '*=' ;
T56 : '/=' ;
T57 : 'unset' ;
T58 : '#' ;
T59 : 'artifacts' ;
T60 : 'annotations' ;
T61 : 'expr' ;
T62 : 'group' ;
T63 : 'hidden' ;
T64 : 'with' ;
T65 : 'action' ;
T66 : 'layout' ;
T67 : 'resolver' ;
T68 : '..' ;
T69 : 'precondition' ;
T70 : 'postcondition' ;
T71 : 'assert' ;
T72 : 'break' ;
T73 : 'continue' ;
T74 : 'while' ;
T75 : 'switch' ;
T76 : 'default' ;
T77 : 'case' ;
T78 : 'for' ;
T79 : 'return' ;
T80 : 'var' ;
T81 : '%=' ;
T82 : '||' ;
T83 : '&&' ;
T84 : '~=' ;
T85 : '==' ;
T86 : '!=' ;
T87 : '>=' ;
T88 : '<=' ;
T89 : '++' ;
T90 : '--' ;
T91 : 'true' ;
T92 : 'false' ;
T93 : 'null' ;
T94 : 'public' ;
T95 : 'private' ;
T96 : 'parallel' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8219
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8221
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8223
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8225
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8227
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8229
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8231
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8233
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8235
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8237
RULE_REAL : (RULE_INT '.' RULE_INT|RULE_INT '.'|'.' RULE_INT|RULE_INT) (('e'|'E') ('-'|'+') RULE_INT)?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 8239
RULE_ANY_OTHER : .;


