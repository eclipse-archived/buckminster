lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T15 : 'reexport' ;
T16 : 'import' ;
T17 : 'as' ;
T18 : ';' ;
T19 : '.*' ;
T20 : 'unit' ;
T21 : 'version' ;
T22 : 'implements' ;
T23 : ',' ;
T24 : '{' ;
T25 : 'properties' ;
T26 : 'provides' ;
T27 : '}' ;
T28 : 'requires' ;
T29 : 'meta' ;
T30 : 'advice' ;
T31 : 'sequential' ;
T32 : 'repositories' ;
T33 : 'when' ;
T34 : '/' ;
T35 : 'immutable' ;
T36 : 'unset' ;
T37 : '#' ;
T38 : 'annotations' ;
T39 : '[' ;
T40 : ']' ;
T41 : '=' ;
T42 : 'with' ;
T43 : 'input' ;
T44 : 'output' ;
T45 : 'resolver' ;
T46 : ':' ;
T47 : '.' ;
T48 : '..' ;
T49 : '*' ;
T50 : '**' ;
T51 : '(' ;
T52 : ')' ;
T53 : 'precondition' ;
T54 : 'postcondition' ;
T55 : 'assert' ;
T56 : 'break' ;
T57 : 'continue' ;
T58 : 'while' ;
T59 : 'switch' ;
T60 : 'default' ;
T61 : 'case' ;
T62 : 'for' ;
T63 : 'in' ;
T64 : 'do' ;
T65 : 'return' ;
T66 : 'function' ;
T67 : 'if' ;
T68 : 'else' ;
T69 : 'throw' ;
T70 : 'try' ;
T71 : 'catch' ;
T72 : 'finally' ;
T73 : 'var' ;
T74 : '?' ;
T75 : '||' ;
T76 : '&&' ;
T77 : '&' ;
T78 : '^' ;
T79 : '|' ;
T80 : '<<' ;
T81 : '>>' ;
T82 : '>>>' ;
T83 : '+' ;
T84 : '-' ;
T85 : '%' ;
T86 : '!' ;
T87 : '++' ;
T88 : '--' ;
T89 : 'typeof' ;
T90 : 'delete' ;
T91 : '~' ;
T92 : 'new' ;
T93 : 'fun' ;
T94 : 'true' ;
T95 : 'false' ;
T96 : 'null' ;
T97 : 'undefined' ;
T98 : 'query' ;
T99 : '~~~~~' ;
T100 : '@' ;
T101 : '$' ;
T102 : '<' ;
T103 : '>' ;
T104 : '+=' ;
T105 : '-=' ;
T106 : '*=' ;
T107 : '/=' ;
T108 : '%=' ;
T109 : '&=' ;
T110 : '^=' ;
T111 : '|=' ;
T112 : '<<=' ;
T113 : '>>=' ;
T114 : '>>>=' ;
T115 : '~=' ;
T116 : '==' ;
T117 : '===' ;
T118 : '!=' ;
T119 : '!==' ;
T120 : '>=' ;
T121 : '<=' ;
T122 : 'instanceof' ;
T123 : 'select' ;
T124 : 'collect' ;
T125 : 'reject' ;
T126 : 'exists' ;
T127 : 'notexists' ;
T128 : 'foreach' ;
T129 : 'public' ;
T130 : 'private' ;
T131 : 'parallel' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10281
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10283
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10285
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10287
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10289
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10291
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10293
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10295
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10297
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10299
RULE_REAL : (RULE_INT '.' RULE_INT|RULE_INT '.'|'.' RULE_INT|RULE_INT) (('e'|'E') ('-'|'+') RULE_INT)?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 10301
RULE_ANY_OTHER : .;


