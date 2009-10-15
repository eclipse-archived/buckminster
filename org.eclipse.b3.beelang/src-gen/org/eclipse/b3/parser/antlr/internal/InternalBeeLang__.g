lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : '.' ;
T12 : '/' ;
T13 : '-' ;
T14 : '!' ;
T15 : '@' ;
T16 : '$' ;
T17 : '%' ;
T18 : '&' ;
T19 : '+' ;
T20 : '=' ;
T21 : '*' ;
T22 : '<' ;
T23 : '>' ;
T24 : ':' ;
T25 : '?' ;
T26 : '[' ;
T27 : '(' ;
T28 : ',' ;
T29 : ']' ;
T30 : ')' ;
T31 : 'import' ;
T32 : '.*' ;
T33 : ';' ;
T34 : 'synchronized' ;
T35 : 'unit' ;
T36 : 'version' ;
T37 : 'implements' ;
T38 : '{' ;
T39 : 'provides' ;
T40 : '}' ;
T41 : 'requires' ;
T42 : 'meta' ;
T43 : 'properties' ;
T44 : 'unset' ;
T45 : 'property' ;
T46 : 'advice' ;
T47 : 'synchronize' ;
T48 : 'repositories' ;
T49 : 'when' ;
T50 : 'immutable' ;
T51 : '#' ;
T52 : 'artifacts' ;
T53 : 'group' ;
T54 : 'void' ;
T55 : 'with' ;
T56 : 'action' ;
T57 : 'actor' ;
T58 : 'result' ;
T59 : 'resolver' ;
T60 : '..' ;
T61 : 'precondition' ;
T62 : 'postcondition' ;
T63 : 'assert' ;
T64 : '||' ;
T65 : '&&' ;
T66 : '~=' ;
T67 : '==' ;
T68 : '!=' ;
T69 : '>=' ;
T70 : '<=' ;
T71 : '->' ;
T72 : 'true' ;
T73 : 'false' ;
T74 : 'null' ;
T75 : 'public' ;
T76 : 'private' ;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6338
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6340
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6342
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6344
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6346
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6348
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g" 6350
RULE_ANY_OTHER : .;


