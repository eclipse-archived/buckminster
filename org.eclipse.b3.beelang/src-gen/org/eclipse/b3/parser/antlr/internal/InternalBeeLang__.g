lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T16 : 'reexport' ;
T17 : 'import' ;
T18 : 'as' ;
T19 : ';' ;
T20 : 'unit' ;
T21 : 'version' ;
T22 : 'implements' ;
T23 : ',' ;
T24 : '{' ;
T25 : 'default' ;
T26 : 'properties' ;
T27 : 'provides' ;
T28 : '}' ;
T29 : 'requires' ;
T30 : 'env' ;
T31 : 'sequential' ;
T32 : 'repositories' ;
T33 : '/' ;
T34 : 'when' ;
T35 : 'extends' ;
T36 : 'final' ;
T37 : 'unset' ;
T38 : '#' ;
T39 : 'annotations' ;
T40 : '[' ;
T41 : ']' ;
T42 : 'with' ;
T43 : '(' ;
T44 : ')' ;
T45 : 'concern' ;
T46 : 'cached' ;
T47 : 'builder' ;
T48 : ':' ;
T49 : '...' ;
T50 : 'input' ;
T51 : 'output' ;
T52 : 'resolver' ;
T53 : 'query' ;
T54 : '.' ;
T55 : '..' ;
T56 : '*' ;
T57 : '**' ;
T58 : 'precondition' ;
T59 : 'postcondition' ;
T60 : 'assert' ;
T61 : 'function' ;
T62 : '<' ;
T63 : '>' ;
T64 : 'method' ;
T65 : '&' ;
T66 : 'super' ;
T67 : 'return' ;
T68 : 'var' ;
T69 : '::' ;
T70 : '=>' ;
T71 : '?' ;
T72 : '||' ;
T73 : '&&' ;
T74 : '^' ;
T75 : '|' ;
T76 : '<<' ;
T77 : '>>' ;
T78 : '>>>' ;
T79 : '+' ;
T80 : '-' ;
T81 : '%' ;
T82 : '!' ;
T83 : '++' ;
T84 : '--' ;
T85 : '~' ;
T86 : 'context' ;
T87 : '_' ;
T88 : 'throw' ;
T89 : 'try' ;
T90 : 'endtry' ;
T91 : 'catch' ;
T92 : 'finally' ;
T93 : 'switch' ;
T94 : 'endswitch' ;
T95 : 'case' ;
T96 : 'if' ;
T97 : 'then' ;
T98 : 'endif' ;
T99 : 'elseif' ;
T100 : 'else' ;
T101 : 'new' ;
T102 : 'true' ;
T103 : 'false' ;
T104 : 'null' ;
T105 : 'this' ;
T106 : '~~~~~' ;
T107 : '@' ;
T108 : '$' ;
T109 : '=' ;
T110 : '+=' ;
T111 : '-=' ;
T112 : '*=' ;
T113 : '/=' ;
T114 : '%=' ;
T115 : '&=' ;
T116 : '^=' ;
T117 : '|=' ;
T118 : '<<=' ;
T119 : '>>=' ;
T120 : '>>>=' ;
T121 : '~=' ;
T122 : '==' ;
T123 : '===' ;
T124 : '!=' ;
T125 : '!==' ;
T126 : '>=' ;
T127 : '<=' ;
T128 : 'instanceof' ;
T129 : 'public' ;
T130 : 'private' ;
T131 : 'parallel' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12499
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12501
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12503
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12505
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12507
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12509
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12511
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12513
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12515
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12517
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12519
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12521
RULE_ANY_OTHER : .;


