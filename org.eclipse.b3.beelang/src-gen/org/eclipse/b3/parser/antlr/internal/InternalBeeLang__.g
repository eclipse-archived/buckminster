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
T53 : 'unset' ;
T54 : '#' ;
T55 : 'annotations' ;
T56 : 'hidden' ;
T57 : 'with' ;
T58 : 'layout' ;
T59 : 'group' ;
T60 : 'resolver' ;
T61 : '..' ;
T62 : '**' ;
T63 : 'precondition' ;
T64 : 'postcondition' ;
T65 : 'assert' ;
T66 : 'break' ;
T67 : 'continue' ;
T68 : 'while' ;
T69 : 'switch' ;
T70 : 'default' ;
T71 : 'case' ;
T72 : 'for' ;
T73 : 'in' ;
T74 : 'do' ;
T75 : 'return' ;
T76 : 'function' ;
T77 : 'if' ;
T78 : 'else' ;
T79 : 'throw' ;
T80 : 'try' ;
T81 : 'catch' ;
T82 : 'finally' ;
T83 : 'var' ;
T84 : '||' ;
T85 : '&&' ;
T86 : '^' ;
T87 : '|' ;
T88 : '<<' ;
T89 : '>>' ;
T90 : '>>>' ;
T91 : '++' ;
T92 : '--' ;
T93 : 'typeof' ;
T94 : 'delete' ;
T95 : '~' ;
T96 : 'new' ;
T97 : 'true' ;
T98 : 'false' ;
T99 : 'null' ;
T100 : 'undefined' ;
T101 : '~{' ;
T102 : 'public' ;
T103 : 'private' ;
T104 : 'parallel' ;
T105 : '+=' ;
T106 : '-=' ;
T107 : '*=' ;
T108 : '/=' ;
T109 : '%=' ;
T110 : '&=' ;
T111 : '^=' ;
T112 : '|=' ;
T113 : '<<=' ;
T114 : '>>=' ;
T115 : '>>>=' ;
T116 : '~=' ;
T117 : '==' ;
T118 : '===' ;
T119 : '!=' ;
T120 : '!==' ;
T121 : '>=' ;
T122 : '<=' ;
T123 : 'instanceof' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9902
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9904
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9906
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9908
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9910
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9912
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9914
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9916
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9918
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9920
RULE_REAL : (RULE_INT '.' RULE_INT|RULE_INT '.'|'.' RULE_INT|RULE_INT) (('e'|'E') ('-'|'+') RULE_INT)?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 9922
RULE_ANY_OTHER : .;


