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
T34 : '^' ;
T35 : '|' ;
T36 : '<<' ;
T37 : '>>' ;
T38 : '>>>' ;
T39 : '/' ;
T40 : '++' ;
T41 : '--' ;
T42 : 'typeof' ;
T43 : 'delete' ;
T44 : '~' ;
T45 : 'group' ;
T46 : 'layout' ;
T47 : 'properties' ;
T48 : 'true' ;
T49 : 'false' ;
T50 : 'public' ;
T51 : 'private' ;
T52 : 'parallel' ;
T53 : 'sequential' ;
T54 : '+=' ;
T55 : '-=' ;
T56 : '*=' ;
T57 : '/=' ;
T58 : '%=' ;
T59 : '&=' ;
T60 : '^=' ;
T61 : '|=' ;
T62 : '<<=' ;
T63 : '>>=' ;
T64 : '>>>=' ;
T65 : '~=' ;
T66 : '==' ;
T67 : '===' ;
T68 : '!=' ;
T69 : '!==' ;
T70 : '>=' ;
T71 : '<=' ;
T72 : 'instanceof' ;
T73 : ',' ;
T74 : 'import' ;
T75 : '.*' ;
T76 : ';' ;
T77 : 'unit' ;
T78 : '{' ;
T79 : '}' ;
T80 : 'version' ;
T81 : 'implements' ;
T82 : 'provides' ;
T83 : 'requires' ;
T84 : 'meta' ;
T85 : 'advice' ;
T86 : 'repositories' ;
T87 : 'when' ;
T88 : 'unset' ;
T89 : '#' ;
T90 : 'annotations' ;
T91 : 'with' ;
T92 : 'resolver' ;
T93 : '..' ;
T94 : '**' ;
T95 : 'precondition' ;
T96 : 'postcondition' ;
T97 : 'assert' ;
T98 : 'break' ;
T99 : 'continue' ;
T100 : 'while' ;
T101 : 'switch' ;
T102 : 'default' ;
T103 : 'case' ;
T104 : 'for' ;
T105 : 'in' ;
T106 : 'do' ;
T107 : 'return' ;
T108 : 'function' ;
T109 : 'if' ;
T110 : 'else' ;
T111 : 'throw' ;
T112 : 'try' ;
T113 : 'catch' ;
T114 : 'finally' ;
T115 : 'var' ;
T116 : '~{' ;
T117 : 'immutable' ;
T118 : 'hidden' ;
T119 : '||' ;
T120 : '&&' ;
T121 : 'new' ;
T122 : 'null' ;
T123 : 'undefined' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19772
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19774
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19776
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19778
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19780
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19782
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19784
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19786
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19788
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19790
RULE_REAL : (RULE_INT '.' RULE_INT|RULE_INT '.'|'.' RULE_INT|RULE_INT) (('e'|'E') ('-'|'+') RULE_INT)?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 19792
RULE_ANY_OTHER : .;


