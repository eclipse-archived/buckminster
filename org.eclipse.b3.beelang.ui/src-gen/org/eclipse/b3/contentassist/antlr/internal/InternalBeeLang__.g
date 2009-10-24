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
T39 : '<<=' ;
T40 : '>>=' ;
T41 : '>>>=' ;
T42 : '&=' ;
T43 : '^=' ;
T44 : '|=' ;
T45 : '^' ;
T46 : '|' ;
T47 : '~=' ;
T48 : '==' ;
T49 : '===' ;
T50 : '!=' ;
T51 : '!==' ;
T52 : '>=' ;
T53 : '<=' ;
T54 : '==.' ;
T55 : 'instanceof' ;
T56 : '/' ;
T57 : '++' ;
T58 : '--' ;
T59 : 'typeof' ;
T60 : 'delete' ;
T61 : '~' ;
T62 : 'group' ;
T63 : 'layout' ;
T64 : 'properties' ;
T65 : 'true' ;
T66 : 'false' ;
T67 : 'public' ;
T68 : 'private' ;
T69 : 'parallel' ;
T70 : 'sequential' ;
T71 : ',' ;
T72 : 'import' ;
T73 : '.*' ;
T74 : ';' ;
T75 : 'unit' ;
T76 : '{' ;
T77 : '}' ;
T78 : 'version' ;
T79 : 'implements' ;
T80 : 'provides' ;
T81 : 'requires' ;
T82 : 'meta' ;
T83 : 'advice' ;
T84 : 'repositories' ;
T85 : 'when' ;
T86 : 'unset' ;
T87 : '#' ;
T88 : 'annotations' ;
T89 : 'expr' ;
T90 : 'with' ;
T91 : 'resolver' ;
T92 : 'assert' ;
T93 : 'break' ;
T94 : 'continue' ;
T95 : 'while' ;
T96 : 'switch' ;
T97 : 'default' ;
T98 : 'case' ;
T99 : 'for' ;
T100 : 'do' ;
T101 : 'return' ;
T102 : 'function' ;
T103 : 'if' ;
T104 : 'else' ;
T105 : 'throw' ;
T106 : 'try' ;
T107 : 'catch' ;
T108 : 'finally' ;
T109 : 'var' ;
T110 : 'new' ;
T111 : 'immutable' ;
T112 : 'hidden' ;
T113 : '..' ;
T114 : 'precondition' ;
T115 : 'postcondition' ;
T116 : 'in' ;
T117 : '||' ;
T118 : '&&' ;
T119 : 'null' ;
T120 : 'void' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19180
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19182
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19184
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19186
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19188
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19190
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19192
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19194
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19196
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19198
RULE_REAL : (RULE_INT '.' RULE_INT|RULE_INT '.'|'.' RULE_INT|RULE_INT) (('e'|'E') ('-'|'+') RULE_INT)?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19200
RULE_ANY_OTHER : .;


