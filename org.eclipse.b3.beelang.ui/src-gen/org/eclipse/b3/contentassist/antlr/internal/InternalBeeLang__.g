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
T48 : 'this' ;
T49 : '.' ;
T50 : '@' ;
T51 : '$' ;
T52 : '=' ;
T53 : '<' ;
T54 : '>' ;
T55 : ':' ;
T56 : '?' ;
T57 : '**' ;
T58 : '+=' ;
T59 : '-=' ;
T60 : '*=' ;
T61 : '/=' ;
T62 : '%=' ;
T63 : '&=' ;
T64 : '^=' ;
T65 : '|=' ;
T66 : '<<=' ;
T67 : '>>=' ;
T68 : '>>>=' ;
T69 : '~=' ;
T70 : '==' ;
T71 : '===' ;
T72 : '!=' ;
T73 : '!==' ;
T74 : '>=' ;
T75 : '<=' ;
T76 : 'instanceof' ;
T77 : 'public' ;
T78 : 'private' ;
T79 : 'parallel' ;
T80 : 'sequential' ;
T81 : 'import' ;
T82 : ';' ;
T83 : 'as' ;
T84 : '{' ;
T85 : '}' ;
T86 : 'version' ;
T87 : 'implements' ;
T88 : ',' ;
T89 : 'default' ;
T90 : 'provides' ;
T91 : 'requires' ;
T92 : 'env' ;
T93 : 'repositories' ;
T94 : 'containers' ;
T95 : 'when' ;
T96 : 'extends' ;
T97 : 'unset' ;
T98 : '#' ;
T99 : 'annotations' ;
T100 : 'with' ;
T101 : 'concern' ;
T102 : 'builder' ;
T103 : '...' ;
T104 : 'repository' ;
T105 : 'container' ;
T106 : 'agent' ;
T107 : 'query' ;
T108 : 'precondition' ;
T109 : 'postcondition' ;
T110 : 'assert' ;
T111 : 'super' ;
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

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24217
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24219
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24221
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24223
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24225
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24227
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24229
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24231
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24233
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24235
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24237
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24239
RULE_ANY_OTHER : .;


