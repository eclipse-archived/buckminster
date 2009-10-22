lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;
}

T15 : '~~~~~' ;
T16 : '.' ;
T17 : '-' ;
T18 : '!' ;
T19 : '@' ;
T20 : '$' ;
T21 : '%' ;
T22 : '&' ;
T23 : '+' ;
T24 : '=' ;
T25 : '*' ;
T26 : '<' ;
T27 : '>' ;
T28 : ':' ;
T29 : '?' ;
T30 : '[' ;
T31 : '(' ;
T32 : ']' ;
T33 : ')' ;
T34 : '+=' ;
T35 : '-=' ;
T36 : '*=' ;
T37 : '/=' ;
T38 : '%=' ;
T39 : '~=' ;
T40 : '==' ;
T41 : '!=' ;
T42 : '>=' ;
T43 : '<=' ;
T44 : '/' ;
T45 : '++' ;
T46 : '--' ;
T47 : 'true' ;
T48 : 'false' ;
T49 : 'public' ;
T50 : 'private' ;
T51 : 'parallel' ;
T52 : 'sequential' ;
T53 : ',' ;
T54 : 'import' ;
T55 : '.*' ;
T56 : ';' ;
T57 : 'unit' ;
T58 : '{' ;
T59 : '}' ;
T60 : 'version' ;
T61 : 'implements' ;
T62 : 'properties' ;
T63 : 'provides' ;
T64 : 'requires' ;
T65 : 'meta' ;
T66 : 'advice' ;
T67 : 'repositories' ;
T68 : 'when' ;
T69 : 'unset' ;
T70 : '#' ;
T71 : 'artifacts' ;
T72 : 'annotations' ;
T73 : 'expr' ;
T74 : 'group' ;
T75 : 'with' ;
T76 : 'action' ;
T77 : 'layout' ;
T78 : 'resolver' ;
T79 : 'assert' ;
T80 : 'break' ;
T81 : 'continue' ;
T82 : 'while' ;
T83 : 'switch' ;
T84 : 'default' ;
T85 : 'case' ;
T86 : 'for' ;
T87 : 'return' ;
T88 : 'var' ;
T89 : 'immutable' ;
T90 : 'hidden' ;
T91 : '..' ;
T92 : 'precondition' ;
T93 : 'postcondition' ;
T94 : '||' ;
T95 : '&&' ;
T96 : 'null' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16664
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16666
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16668
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16670
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16672
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16674
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16676
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16678
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16680
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16682
RULE_REAL : (RULE_INT '.' RULE_INT|RULE_INT '.'|'.' RULE_INT|RULE_INT) (('e'|'E') ('-'|'+') RULE_INT)?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 16684
RULE_ANY_OTHER : .;


