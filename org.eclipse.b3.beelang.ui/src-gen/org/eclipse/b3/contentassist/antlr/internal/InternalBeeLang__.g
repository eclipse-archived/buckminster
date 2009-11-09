lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;
}

T16 : 'input' ;
T17 : 'output' ;
T18 : '~~~~~' ;
T19 : '..' ;
T20 : 'var' ;
T21 : '&' ;
T22 : '^' ;
T23 : '|' ;
T24 : '<<' ;
T25 : '>>' ;
T26 : '>>>' ;
T27 : '+' ;
T28 : '-' ;
T29 : '*' ;
T30 : '/' ;
T31 : '%' ;
T32 : '!' ;
T33 : '++' ;
T34 : '--' ;
T35 : '~' ;
T36 : 'properties' ;
T37 : 'unit' ;
T38 : 'true' ;
T39 : 'false' ;
T40 : '[' ;
T41 : '(' ;
T42 : ']' ;
T43 : ')' ;
T44 : '.' ;
T45 : '@' ;
T46 : '$' ;
T47 : '=' ;
T48 : '<' ;
T49 : '>' ;
T50 : ':' ;
T51 : '?' ;
T52 : '+=' ;
T53 : '-=' ;
T54 : '*=' ;
T55 : '/=' ;
T56 : '%=' ;
T57 : '&=' ;
T58 : '^=' ;
T59 : '|=' ;
T60 : '<<=' ;
T61 : '>>=' ;
T62 : '>>>=' ;
T63 : '~=' ;
T64 : '==' ;
T65 : '===' ;
T66 : '!=' ;
T67 : '!==' ;
T68 : '>=' ;
T69 : '<=' ;
T70 : 'instanceof' ;
T71 : 'public' ;
T72 : 'private' ;
T73 : 'parallel' ;
T74 : 'sequential' ;
T75 : 'import' ;
T76 : ';' ;
T77 : 'as' ;
T78 : '{' ;
T79 : '}' ;
T80 : 'version' ;
T81 : 'implements' ;
T82 : ',' ;
T83 : 'default' ;
T84 : 'provides' ;
T85 : 'requires' ;
T86 : 'env' ;
T87 : 'repositories' ;
T88 : 'when' ;
T89 : 'extends' ;
T90 : 'unset' ;
T91 : '#' ;
T92 : 'annotations' ;
T93 : 'with' ;
T94 : 'concern' ;
T95 : 'builder' ;
T96 : '...' ;
T97 : 'resolver' ;
T98 : 'query' ;
T99 : '**' ;
T100 : 'precondition' ;
T101 : 'postcondition' ;
T102 : 'assert' ;
T103 : 'function' ;
T104 : 'method' ;
T105 : 'super' ;
T106 : 'return' ;
T107 : '::' ;
T108 : '=>' ;
T109 : 'context' ;
T110 : '_' ;
T111 : 'throw' ;
T112 : 'try' ;
T113 : 'endtry' ;
T114 : 'catch' ;
T115 : 'finally' ;
T116 : 'switch' ;
T117 : 'endswitch' ;
T118 : 'case' ;
T119 : 'if' ;
T120 : 'then' ;
T121 : 'endif' ;
T122 : 'elseif' ;
T123 : 'else' ;
T124 : 'reexport' ;
T125 : 'final' ;
T126 : 'cached' ;
T127 : '||' ;
T128 : '&&' ;
T129 : 'new' ;
T130 : 'null' ;
T131 : 'this' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24635
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24637
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24639
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24641
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24643
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24645
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24647
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24649
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24651
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24653
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24655
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24657
RULE_ANY_OTHER : .;


