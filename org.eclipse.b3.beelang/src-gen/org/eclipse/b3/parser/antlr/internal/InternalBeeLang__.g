lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : '.' ;
T12 : '~~~~~' ;
T13 : '/' ;
T14 : '-' ;
T15 : '!' ;
T16 : '@' ;
T17 : '$' ;
T18 : '%' ;
T19 : '&' ;
T20 : '+' ;
T21 : '=' ;
T22 : '*' ;
T23 : '<' ;
T24 : '>' ;
T25 : ':' ;
T26 : '?' ;
T27 : '[' ;
T28 : '(' ;
T29 : ',' ;
T30 : ']' ;
T31 : ')' ;
T32 : 'import' ;
T33 : '.*' ;
T34 : ';' ;
T35 : 'unit' ;
T36 : 'version' ;
T37 : 'implements' ;
T38 : '{' ;
T39 : 'properties' ;
T40 : 'provides' ;
T41 : '}' ;
T42 : 'requires' ;
T43 : 'meta' ;
T44 : 'advice' ;
T45 : 'sequential' ;
T46 : 'repositories' ;
T47 : 'when' ;
T48 : 'local' ;
T49 : 'immutable' ;
T50 : 'unset' ;
T51 : '#' ;
T52 : 'artifacts' ;
T53 : 'annotations' ;
T54 : 'expr' ;
T55 : 'group' ;
T56 : 'hidden' ;
T57 : 'with' ;
T58 : 'action' ;
T59 : 'actor' ;
T60 : '...' ;
T61 : 'layout' ;
T62 : 'resolver' ;
T63 : '..' ;
T64 : 'precondition' ;
T65 : 'postcondition' ;
T66 : 'assert' ;
T67 : '||' ;
T68 : '&&' ;
T69 : '~=' ;
T70 : '==' ;
T71 : '!=' ;
T72 : '>=' ;
T73 : '<=' ;
T74 : 'true' ;
T75 : 'false' ;
T76 : 'null' ;
T77 : 'public' ;
T78 : 'private' ;
T79 : 'parallel' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6696
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6698
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6700
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6702
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6704
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6706
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6708
RULE_ANY_OTHER : .;


