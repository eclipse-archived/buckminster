lexer grammar InternalBeeLang;
@header {
package org.eclipse.b3.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;
}

T11 : '.' ;
T12 : '-' ;
T13 : '!' ;
T14 : '@' ;
T15 : '$' ;
T16 : '%' ;
T17 : '&' ;
T18 : '/' ;
T19 : '+' ;
T20 : '=' ;
T21 : '*' ;
T22 : '<' ;
T23 : '>' ;
T24 : ':' ;
T25 : '?' ;
T26 : '[' ;
T27 : '(' ;
T28 : ']' ;
T29 : ')' ;
T30 : '~=' ;
T31 : '==' ;
T32 : '!=' ;
T33 : '>=' ;
T34 : '<=' ;
T35 : 'true' ;
T36 : 'false' ;
T37 : 'public' ;
T38 : 'private' ;
T39 : ',' ;
T40 : 'import' ;
T41 : '.*' ;
T42 : ';' ;
T43 : 'unit' ;
T44 : '{' ;
T45 : '}' ;
T46 : 'version' ;
T47 : 'implements' ;
T48 : 'provides' ;
T49 : 'requires' ;
T50 : 'meta' ;
T51 : 'properties' ;
T52 : 'unset' ;
T53 : 'property' ;
T54 : 'advice' ;
T55 : 'synchronize' ;
T56 : 'repositories' ;
T57 : 'when' ;
T58 : '#' ;
T59 : 'artifacts' ;
T60 : 'group' ;
T61 : 'with' ;
T62 : 'action' ;
T63 : 'actor' ;
T64 : 'result' ;
T65 : 'resolver' ;
T66 : 'assert' ;
T67 : '->' ;
T68 : 'synchronized' ;
T69 : 'immutable' ;
T70 : 'void' ;
T71 : '..' ;
T72 : 'precondition' ;
T73 : 'postcondition' ;
T74 : '||' ;
T75 : '&&' ;
T76 : 'null' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 13467
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 13469
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 13471
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 13473
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 13475
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 13477
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 13479
RULE_ANY_OTHER : .;


