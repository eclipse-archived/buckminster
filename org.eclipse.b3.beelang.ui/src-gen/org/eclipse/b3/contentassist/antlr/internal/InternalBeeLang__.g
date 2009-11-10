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
T48 : '.' ;
T49 : '@' ;
T50 : '$' ;
T51 : '=' ;
T52 : '<' ;
T53 : '>' ;
T54 : ':' ;
T55 : '?' ;
T56 : '**' ;
T57 : '+=' ;
T58 : '-=' ;
T59 : '*=' ;
T60 : '/=' ;
T61 : '%=' ;
T62 : '&=' ;
T63 : '^=' ;
T64 : '|=' ;
T65 : '<<=' ;
T66 : '>>=' ;
T67 : '>>>=' ;
T68 : '~=' ;
T69 : '==' ;
T70 : '===' ;
T71 : '!=' ;
T72 : '!==' ;
T73 : '>=' ;
T74 : '<=' ;
T75 : 'instanceof' ;
T76 : 'public' ;
T77 : 'private' ;
T78 : 'parallel' ;
T79 : 'sequential' ;
T80 : 'import' ;
T81 : ';' ;
T82 : 'as' ;
T83 : '{' ;
T84 : '}' ;
T85 : 'version' ;
T86 : 'implements' ;
T87 : ',' ;
T88 : 'default' ;
T89 : 'provides' ;
T90 : 'requires' ;
T91 : 'env' ;
T92 : 'repositories' ;
T93 : 'containers' ;
T94 : 'when' ;
T95 : 'extends' ;
T96 : 'unset' ;
T97 : '#' ;
T98 : 'annotations' ;
T99 : 'with' ;
T100 : 'concern' ;
T101 : 'builder' ;
T102 : '...' ;
T103 : 'repository' ;
T104 : 'container' ;
T105 : 'agent' ;
T106 : 'query' ;
T107 : 'precondition' ;
T108 : 'postcondition' ;
T109 : 'assert' ;
T110 : 'super' ;
T111 : 'return' ;
T112 : '::' ;
T113 : '=>' ;
T114 : 'context' ;
T115 : '_' ;
T116 : 'throw' ;
T117 : 'try' ;
T118 : 'endtry' ;
T119 : 'catch' ;
T120 : 'finally' ;
T121 : 'switch' ;
T122 : 'endswitch' ;
T123 : 'case' ;
T124 : 'if' ;
T125 : 'then' ;
T126 : 'endif' ;
T127 : 'elseif' ;
T128 : 'else' ;
T129 : 'reexport' ;
T130 : 'final' ;
T131 : 'cached' ;
T132 : '||' ;
T133 : '&&' ;
T134 : 'new' ;
T135 : 'null' ;
T136 : 'this' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23613
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23615
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23617
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23619
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23621
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23623
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23625
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23627
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23629
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23631
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23633
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 23635
RULE_ANY_OTHER : .;


