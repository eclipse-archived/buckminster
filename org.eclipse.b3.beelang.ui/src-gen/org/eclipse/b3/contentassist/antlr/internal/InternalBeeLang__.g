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
T22 : 'var' ;
T23 : '&' ;
T24 : '^' ;
T25 : '|' ;
T26 : '<<' ;
T27 : '>>' ;
T28 : '>>>' ;
T29 : '+' ;
T30 : '-' ;
T31 : '*' ;
T32 : '/' ;
T33 : '%' ;
T34 : '!' ;
T35 : '++' ;
T36 : '--' ;
T37 : '~' ;
T38 : 'properties' ;
T39 : 'unit' ;
T40 : 'true' ;
T41 : 'false' ;
T42 : '[' ;
T43 : '(' ;
T44 : ']' ;
T45 : ')' ;
T46 : '.' ;
T47 : '@' ;
T48 : '$' ;
T49 : '=' ;
T50 : '<' ;
T51 : '>' ;
T52 : ':' ;
T53 : '?' ;
T54 : '+=' ;
T55 : '-=' ;
T56 : '*=' ;
T57 : '/=' ;
T58 : '%=' ;
T59 : '&=' ;
T60 : '^=' ;
T61 : '|=' ;
T62 : '<<=' ;
T63 : '>>=' ;
T64 : '>>>=' ;
T65 : '~=' ;
T66 : '==' ;
T67 : '===' ;
T68 : '!=' ;
T69 : '!==' ;
T70 : '>=' ;
T71 : '<=' ;
T72 : 'instanceof' ;
T73 : 'public' ;
T74 : 'private' ;
T75 : 'parallel' ;
T76 : 'sequential' ;
T77 : 'import' ;
T78 : ';' ;
T79 : 'as' ;
T80 : '{' ;
T81 : '}' ;
T82 : 'version' ;
T83 : 'implements' ;
T84 : ',' ;
T85 : 'default' ;
T86 : 'provides' ;
T87 : 'requires' ;
T88 : 'env' ;
T89 : 'repositories' ;
T90 : 'when' ;
T91 : 'extends' ;
T92 : 'unset' ;
T93 : '#' ;
T94 : 'annotations' ;
T95 : 'with' ;
T96 : 'concern' ;
T97 : 'builder' ;
T98 : '...' ;
T99 : 'repository' ;
T100 : 'query' ;
T101 : '**' ;
T102 : 'precondition' ;
T103 : 'postcondition' ;
T104 : 'assert' ;
T105 : 'function' ;
T106 : 'method' ;
T107 : 'super' ;
T108 : 'return' ;
T109 : '::' ;
T110 : '=>' ;
T111 : 'context' ;
T112 : '_' ;
T113 : 'throw' ;
T114 : 'try' ;
T115 : 'endtry' ;
T116 : 'catch' ;
T117 : 'finally' ;
T118 : 'switch' ;
T119 : 'endswitch' ;
T120 : 'case' ;
T121 : 'if' ;
T122 : 'then' ;
T123 : 'endif' ;
T124 : 'elseif' ;
T125 : 'else' ;
T126 : 'reexport' ;
T127 : 'final' ;
T128 : 'cached' ;
T129 : '||' ;
T130 : '&&' ;
T131 : 'new' ;
T132 : 'null' ;
T133 : 'this' ;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24667
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24669
RULE_PID : '$' RULE_ID ('.' RULE_ID)*;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24671
RULE_REGEX : '~/' ('\\' ('/'|'f'|'n'|'r'|'t'|'v'|'\\'|'.'|'*'|'+'|'?'|'w'|'W'|'s'|'S'|'d'|'D'|'b'|'|'|'{'|'}'|'['|']'|'('|')'|'0'..'7' '0'..'7' '0'..'7'|'x' ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F')|'c' 'A'..'Z')|~(('\\'|'/'|' '|'\t'|'\r'|'\n')))* '/' ('g'|'m'|'i')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24673
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'"'|'\r'|'\n')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'x'|'0'|'"'|'\''|'\\')|~(('\\'|'\''|'\r'|'\n')))* '\'');

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24675
RULE_DOCUMENTATION : '/**' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24677
RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24679
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24681
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24683
RULE_HEX : '0' ('x'|'X') ('0'..'9'|'a'..'f'|'A'..'F')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24685
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24687
RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+') RULE_INT;

// $ANTLR src "../org.eclipse.b3.beelang.ui/src-gen/org/eclipse/b3/contentassist/antlr/internal/InternalBeeLang.g" 24689
RULE_ANY_OTHER : .;


