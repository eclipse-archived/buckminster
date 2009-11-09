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
T52 : 'repository' ;
T53 : 'select-first' ;
T54 : 'select-best' ;
T55 : 'query' ;
T56 : '.' ;
T57 : '..' ;
T58 : '*' ;
T59 : '**' ;
T60 : 'precondition' ;
T61 : 'postcondition' ;
T62 : 'assert' ;
T63 : 'function' ;
T64 : '<' ;
T65 : '>' ;
T66 : 'method' ;
T67 : '&' ;
T68 : 'super' ;
T69 : 'return' ;
T70 : 'var' ;
T71 : '::' ;
T72 : '=>' ;
T73 : '?' ;
T74 : '||' ;
T75 : '&&' ;
T76 : '^' ;
T77 : '|' ;
T78 : '<<' ;
T79 : '>>' ;
T80 : '>>>' ;
T81 : '+' ;
T82 : '-' ;
T83 : '%' ;
T84 : '!' ;
T85 : '++' ;
T86 : '--' ;
T87 : '~' ;
T88 : 'context' ;
T89 : '_' ;
T90 : 'throw' ;
T91 : 'try' ;
T92 : 'endtry' ;
T93 : 'catch' ;
T94 : 'finally' ;
T95 : 'switch' ;
T96 : 'endswitch' ;
T97 : 'case' ;
T98 : 'if' ;
T99 : 'then' ;
T100 : 'endif' ;
T101 : 'elseif' ;
T102 : 'else' ;
T103 : 'new' ;
T104 : 'true' ;
T105 : 'false' ;
T106 : 'null' ;
T107 : 'this' ;
T108 : '~~~~~' ;
T109 : '@' ;
T110 : '$' ;
T111 : '=' ;
T112 : '+=' ;
T113 : '-=' ;
T114 : '*=' ;
T115 : '/=' ;
T116 : '%=' ;
T117 : '&=' ;
T118 : '^=' ;
T119 : '|=' ;
T120 : '<<=' ;
T121 : '>>=' ;
T122 : '>>>=' ;
T123 : '~=' ;
T124 : '==' ;
T125 : '===' ;
T126 : '!=' ;
T127 : '!==' ;
T128 : '>=' ;
T129 : '<=' ;
T130 : 'instanceof' ;
T131 : 'public' ;
T132 : 'private' ;
T133 : 'parallel' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12531
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12533
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12535
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12537
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12539
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12541
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12543
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12545
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12547
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12549
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12551
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12553
RULE_ANY_OTHER : .;


