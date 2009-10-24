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
T59 : 'annotations' ;
T60 : 'expr' ;
T61 : 'hidden' ;
T62 : 'with' ;
T63 : 'layout' ;
T64 : 'group' ;
T65 : 'resolver' ;
T66 : '..' ;
T67 : 'precondition' ;
T68 : 'postcondition' ;
T69 : 'assert' ;
T70 : 'break' ;
T71 : 'continue' ;
T72 : 'while' ;
T73 : 'switch' ;
T74 : 'default' ;
T75 : 'case' ;
T76 : 'for' ;
T77 : 'in' ;
T78 : 'do' ;
T79 : 'return' ;
T80 : 'function' ;
T81 : 'if' ;
T82 : 'else' ;
T83 : 'throw' ;
T84 : 'try' ;
T85 : 'catch' ;
T86 : 'finally' ;
T87 : 'var' ;
T88 : '%=' ;
T89 : '<<=' ;
T90 : '>>=' ;
T91 : '>>>=' ;
T92 : '&=' ;
T93 : '^=' ;
T94 : '|=' ;
T95 : '||' ;
T96 : '&&' ;
T97 : '^' ;
T98 : '|' ;
T99 : '~=' ;
T100 : '==' ;
T101 : '===' ;
T102 : '!=' ;
T103 : '!==' ;
T104 : '>=' ;
T105 : '<=' ;
T106 : '==.' ;
T107 : 'instanceof' ;
T108 : '++' ;
T109 : '--' ;
T110 : 'typeof' ;
T111 : 'delete' ;
T112 : '~' ;
T113 : 'new' ;
T114 : 'true' ;
T115 : 'false' ;
T116 : 'null' ;
T117 : 'void' ;
T118 : 'public' ;
T119 : 'private' ;
T120 : 'parallel' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9404
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9406
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9408
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9410
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9412
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9414
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9416
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9418
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9420
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9422
RULE_REAL : (RULE_INT '.' RULE_INT|RULE_INT '.'|'.' RULE_INT|RULE_INT) (('e'|'E') ('-'|'+') RULE_INT)?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9424
RULE_ANY_OTHER : .;


