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
T33 : 'containers' ;
T34 : '/' ;
T35 : 'when' ;
T36 : 'extends' ;
T37 : 'final' ;
T38 : 'unset' ;
T39 : '#' ;
T40 : 'annotations' ;
T41 : '[' ;
T42 : ']' ;
T43 : 'with' ;
T44 : '(' ;
T45 : ')' ;
T46 : 'concern' ;
T47 : 'cached' ;
T48 : 'builder' ;
T49 : ':' ;
T50 : '...' ;
T51 : 'input' ;
T52 : 'output' ;
T53 : 'repository' ;
T54 : 'select-first' ;
T55 : 'select-best' ;
T56 : 'container' ;
T57 : 'agent' ;
T58 : 'query' ;
T59 : 'precondition' ;
T60 : 'postcondition' ;
T61 : 'assert' ;
T62 : 'function' ;
T63 : 'method' ;
T64 : '<' ;
T65 : '>' ;
T66 : '&' ;
T67 : 'super' ;
T68 : 'return' ;
T69 : 'var' ;
T70 : '::' ;
T71 : '=>' ;
T72 : '?' ;
T73 : '||' ;
T74 : '&&' ;
T75 : '^' ;
T76 : '|' ;
T77 : '<<' ;
T78 : '>>' ;
T79 : '>>>' ;
T80 : '+' ;
T81 : '-' ;
T82 : '*' ;
T83 : '%' ;
T84 : '!' ;
T85 : '++' ;
T86 : '--' ;
T87 : '~' ;
T88 : '.' ;
T89 : 'context' ;
T90 : '_' ;
T91 : 'throw' ;
T92 : 'try' ;
T93 : 'endtry' ;
T94 : 'catch' ;
T95 : 'finally' ;
T96 : 'switch' ;
T97 : 'endswitch' ;
T98 : 'case' ;
T99 : 'if' ;
T100 : 'then' ;
T101 : 'endif' ;
T102 : 'elseif' ;
T103 : 'else' ;
T104 : 'new' ;
T105 : 'true' ;
T106 : 'false' ;
T107 : 'null' ;
T108 : 'this' ;
T109 : '~~~~~' ;
T110 : '@' ;
T111 : '$' ;
T112 : '=' ;
T113 : '..' ;
T114 : '**' ;
T115 : '+=' ;
T116 : '-=' ;
T117 : '*=' ;
T118 : '/=' ;
T119 : '%=' ;
T120 : '&=' ;
T121 : '^=' ;
T122 : '|=' ;
T123 : '<<=' ;
T124 : '>>=' ;
T125 : '>>>=' ;
T126 : '~=' ;
T127 : '==' ;
T128 : '===' ;
T129 : '!=' ;
T130 : '!==' ;
T131 : '>=' ;
T132 : '<=' ;
T133 : 'instanceof' ;
T134 : 'public' ;
T135 : 'private' ;
T136 : 'parallel' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12386
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12388
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12390
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12392
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12394
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12396
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12398
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12400
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12402
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12404
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12406
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 12408
RULE_ANY_OTHER : .;


