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
T20 : 'select-first' ;
T21 : 'select-best' ;
T22 : 'function' ;
T23 : 'method' ;
T24 : 'var' ;
T25 : '&' ;
T26 : '^' ;
T27 : '|' ;
T28 : '<<' ;
T29 : '>>' ;
T30 : '>>>' ;
T31 : '+' ;
T32 : '-' ;
T33 : '*' ;
T34 : '/' ;
T35 : '%' ;
T36 : '!' ;
T37 : '++' ;
T38 : '--' ;
T39 : '~' ;
T40 : 'properties' ;
T41 : 'unit' ;
T42 : 'true' ;
T43 : 'false' ;
T44 : '[' ;
T45 : '(' ;
T46 : ']' ;
T47 : ')' ;
T48 : 'super' ;
T49 : 'this' ;
T50 : '.' ;
T51 : '@' ;
T52 : '$' ;
T53 : '=' ;
T54 : '<' ;
T55 : '>' ;
T56 : ':' ;
T57 : '?' ;
T58 : '**' ;
T59 : '+=' ;
T60 : '-=' ;
T61 : '*=' ;
T62 : '/=' ;
T63 : '%=' ;
T64 : '&=' ;
T65 : '^=' ;
T66 : '|=' ;
T67 : '<<=' ;
T68 : '>>=' ;
T69 : '>>>=' ;
T70 : '~=' ;
T71 : '==' ;
T72 : '===' ;
T73 : '!=' ;
T74 : '!==' ;
T75 : '>=' ;
T76 : '<=' ;
T77 : 'instanceof' ;
T78 : 'public' ;
T79 : 'private' ;
T80 : 'parallel' ;
T81 : 'sequential' ;
T82 : 'import' ;
T83 : ';' ;
T84 : 'as' ;
T85 : '{' ;
T86 : '}' ;
T87 : 'version' ;
T88 : 'implements' ;
T89 : ',' ;
T90 : 'default' ;
T91 : 'provides' ;
T92 : 'requires' ;
T93 : 'env' ;
T94 : 'repositories' ;
T95 : 'containers' ;
T96 : 'when' ;
T97 : 'extends' ;
T98 : 'unset' ;
T99 : '#' ;
T100 : 'annotations' ;
T101 : 'with' ;
T102 : 'concern' ;
T103 : 'builder' ;
T104 : '...' ;
T105 : 'repository' ;
T106 : 'container' ;
T107 : 'agent' ;
T108 : 'query' ;
T109 : 'precondition' ;
T110 : 'postcondition' ;
T111 : 'assert' ;
T112 : 'return' ;
T113 : '::' ;
T114 : '=>' ;
T115 : 'context' ;
T116 : '_' ;
T117 : 'throw' ;
T118 : 'try' ;
T119 : 'endtry' ;
T120 : 'catch' ;
T121 : 'finally' ;
T122 : 'switch' ;
T123 : 'endswitch' ;
T124 : 'case' ;
T125 : 'if' ;
T126 : 'then' ;
T127 : 'endif' ;
T128 : 'elseif' ;
T129 : 'else' ;
T130 : 'reexport' ;
T131 : 'final' ;
T132 : 'cached' ;
T133 : '||' ;
T134 : '&&' ;
T135 : 'new' ;
T136 : 'null' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24606
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24608
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24610
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24612
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24614
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24616
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24618
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24620
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24622
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24624
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24626
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24628
RULE_ANY_OTHER : .;


