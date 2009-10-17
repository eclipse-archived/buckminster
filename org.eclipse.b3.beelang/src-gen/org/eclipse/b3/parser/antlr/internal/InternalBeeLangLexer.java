package org.eclipse.b3.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalBeeLangLexer extends Lexer {
    public static final int T79=79;
    public static final int T14=14;
    public static final int T29=29;
    public static final int T36=36;
    public static final int T58=58;
    public static final int T70=70;
    public static final int RULE_STRING=5;
    public static final int T74=74;
    public static final int T35=35;
    public static final int T61=61;
    public static final int T45=45;
    public static final int T20=20;
    public static final int T34=34;
    public static final int T64=64;
    public static final int T25=25;
    public static final int T18=18;
    public static final int T37=37;
    public static final int T26=26;
    public static final int RULE_INT=6;
    public static final int T32=32;
    public static final int T17=17;
    public static final int T51=51;
    public static final int T46=46;
    public static final int T77=77;
    public static final int T16=16;
    public static final int T38=38;
    public static final int T41=41;
    public static final int T24=24;
    public static final int T19=19;
    public static final int T69=69;
    public static final int T39=39;
    public static final int T21=21;
    public static final int T62=62;
    public static final int T44=44;
    public static final int T55=55;
    public static final int RULE_ML_COMMENT=7;
    public static final int RULE_ID=4;
    public static final int T73=73;
    public static final int T68=68;
    public static final int T33=33;
    public static final int T11=11;
    public static final int T22=22;
    public static final int T50=50;
    public static final int T78=78;
    public static final int T43=43;
    public static final int T12=12;
    public static final int T23=23;
    public static final int T28=28;
    public static final int T42=42;
    public static final int T66=66;
    public static final int T40=40;
    public static final int T71=71;
    public static final int T63=63;
    public static final int T57=57;
    public static final int T72=72;
    public static final int T13=13;
    public static final int T65=65;
    public static final int T56=56;
    public static final int T76=76;
    public static final int T75=75;
    public static final int T59=59;
    public static final int RULE_WS=9;
    public static final int T48=48;
    public static final int T15=15;
    public static final int T54=54;
    public static final int EOF=-1;
    public static final int T67=67;
    public static final int T47=47;
    public static final int Tokens=80;
    public static final int T53=53;
    public static final int T60=60;
    public static final int RULE_ANY_OTHER=10;
    public static final int T31=31;
    public static final int T49=49;
    public static final int RULE_SL_COMMENT=8;
    public static final int T27=27;
    public static final int T52=52;
    public static final int T30=30;
    public InternalBeeLangLexer() {;} 
    public InternalBeeLangLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g"; }

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:10:5: ( '.' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:10:7: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:11:5: ( '~~~~~' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:11:7: '~~~~~'
            {
            match("~~~~~"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:12:5: ( '/' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:12:7: '/'
            {
            match('/'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:13:5: ( '-' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:13:7: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:14:5: ( '!' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:14:7: '!'
            {
            match('!'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:15:5: ( '@' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:15:7: '@'
            {
            match('@'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:16:5: ( '$' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:16:7: '$'
            {
            match('$'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:17:5: ( '%' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:17:7: '%'
            {
            match('%'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:18:5: ( '&' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:18:7: '&'
            {
            match('&'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:19:5: ( '+' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:19:7: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:20:5: ( '=' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:20:7: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:21:5: ( '*' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:21:7: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:22:5: ( '<' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:22:7: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:23:5: ( '>' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:23:7: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:24:5: ( ':' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:24:7: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:25:5: ( '?' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:25:7: '?'
            {
            match('?'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:26:5: ( '[' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:26:7: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:27:5: ( '(' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:27:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:28:5: ( ',' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:28:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:29:5: ( ']' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:29:7: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:30:5: ( ')' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:30:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:31:5: ( 'import' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:31:7: 'import'
            {
            match("import"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:32:5: ( '.*' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:32:7: '.*'
            {
            match(".*"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:33:5: ( ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:33:7: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:34:5: ( 'unit' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:34:7: 'unit'
            {
            match("unit"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:35:5: ( 'version' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:35:7: 'version'
            {
            match("version"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:36:5: ( 'implements' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:36:7: 'implements'
            {
            match("implements"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:37:5: ( '{' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:37:7: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:38:5: ( 'properties' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:38:7: 'properties'
            {
            match("properties"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:39:5: ( 'provides' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:39:7: 'provides'
            {
            match("provides"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:40:5: ( '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:40:7: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start T42
    public final void mT42() throws RecognitionException {
        try {
            int _type = T42;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:41:5: ( 'requires' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:41:7: 'requires'
            {
            match("requires"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T42

    // $ANTLR start T43
    public final void mT43() throws RecognitionException {
        try {
            int _type = T43;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:42:5: ( 'meta' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:42:7: 'meta'
            {
            match("meta"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T43

    // $ANTLR start T44
    public final void mT44() throws RecognitionException {
        try {
            int _type = T44;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:43:5: ( 'advice' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:43:7: 'advice'
            {
            match("advice"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T44

    // $ANTLR start T45
    public final void mT45() throws RecognitionException {
        try {
            int _type = T45;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:44:5: ( 'sequential' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:44:7: 'sequential'
            {
            match("sequential"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T45

    // $ANTLR start T46
    public final void mT46() throws RecognitionException {
        try {
            int _type = T46;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:45:5: ( 'repositories' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:45:7: 'repositories'
            {
            match("repositories"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T46

    // $ANTLR start T47
    public final void mT47() throws RecognitionException {
        try {
            int _type = T47;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:46:5: ( 'when' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:46:7: 'when'
            {
            match("when"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T47

    // $ANTLR start T48
    public final void mT48() throws RecognitionException {
        try {
            int _type = T48;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:47:5: ( 'local' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:47:7: 'local'
            {
            match("local"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T48

    // $ANTLR start T49
    public final void mT49() throws RecognitionException {
        try {
            int _type = T49;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:48:5: ( 'immutable' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:48:7: 'immutable'
            {
            match("immutable"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T49

    // $ANTLR start T50
    public final void mT50() throws RecognitionException {
        try {
            int _type = T50;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:49:5: ( 'unset' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:49:7: 'unset'
            {
            match("unset"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T50

    // $ANTLR start T51
    public final void mT51() throws RecognitionException {
        try {
            int _type = T51;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:50:5: ( '#' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:50:7: '#'
            {
            match('#'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T51

    // $ANTLR start T52
    public final void mT52() throws RecognitionException {
        try {
            int _type = T52;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:51:5: ( 'artifacts' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:51:7: 'artifacts'
            {
            match("artifacts"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T52

    // $ANTLR start T53
    public final void mT53() throws RecognitionException {
        try {
            int _type = T53;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:52:5: ( 'annotations' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:52:7: 'annotations'
            {
            match("annotations"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T53

    // $ANTLR start T54
    public final void mT54() throws RecognitionException {
        try {
            int _type = T54;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:53:5: ( 'expr' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:53:7: 'expr'
            {
            match("expr"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T54

    // $ANTLR start T55
    public final void mT55() throws RecognitionException {
        try {
            int _type = T55;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:54:5: ( 'group' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:54:7: 'group'
            {
            match("group"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T55

    // $ANTLR start T56
    public final void mT56() throws RecognitionException {
        try {
            int _type = T56;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:55:5: ( 'hidden' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:55:7: 'hidden'
            {
            match("hidden"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T56

    // $ANTLR start T57
    public final void mT57() throws RecognitionException {
        try {
            int _type = T57;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:56:5: ( 'with' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:56:7: 'with'
            {
            match("with"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T57

    // $ANTLR start T58
    public final void mT58() throws RecognitionException {
        try {
            int _type = T58;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:57:5: ( 'action' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:57:7: 'action'
            {
            match("action"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T58

    // $ANTLR start T59
    public final void mT59() throws RecognitionException {
        try {
            int _type = T59;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:58:5: ( 'actor' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:58:7: 'actor'
            {
            match("actor"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T59

    // $ANTLR start T60
    public final void mT60() throws RecognitionException {
        try {
            int _type = T60;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:59:5: ( '...' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:59:7: '...'
            {
            match("..."); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T60

    // $ANTLR start T61
    public final void mT61() throws RecognitionException {
        try {
            int _type = T61;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:60:5: ( 'layout' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:60:7: 'layout'
            {
            match("layout"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T61

    // $ANTLR start T62
    public final void mT62() throws RecognitionException {
        try {
            int _type = T62;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:61:5: ( 'resolver' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:61:7: 'resolver'
            {
            match("resolver"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T62

    // $ANTLR start T63
    public final void mT63() throws RecognitionException {
        try {
            int _type = T63;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:62:5: ( '..' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:62:7: '..'
            {
            match(".."); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T63

    // $ANTLR start T64
    public final void mT64() throws RecognitionException {
        try {
            int _type = T64;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:63:5: ( 'precondition' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:63:7: 'precondition'
            {
            match("precondition"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T64

    // $ANTLR start T65
    public final void mT65() throws RecognitionException {
        try {
            int _type = T65;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:64:5: ( 'postcondition' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:64:7: 'postcondition'
            {
            match("postcondition"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T65

    // $ANTLR start T66
    public final void mT66() throws RecognitionException {
        try {
            int _type = T66;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:65:5: ( 'assert' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:65:7: 'assert'
            {
            match("assert"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T66

    // $ANTLR start T67
    public final void mT67() throws RecognitionException {
        try {
            int _type = T67;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:66:5: ( '||' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:66:7: '||'
            {
            match("||"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T67

    // $ANTLR start T68
    public final void mT68() throws RecognitionException {
        try {
            int _type = T68;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:67:5: ( '&&' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:67:7: '&&'
            {
            match("&&"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T68

    // $ANTLR start T69
    public final void mT69() throws RecognitionException {
        try {
            int _type = T69;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:68:5: ( '~=' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:68:7: '~='
            {
            match("~="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T69

    // $ANTLR start T70
    public final void mT70() throws RecognitionException {
        try {
            int _type = T70;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:69:5: ( '==' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:69:7: '=='
            {
            match("=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T70

    // $ANTLR start T71
    public final void mT71() throws RecognitionException {
        try {
            int _type = T71;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:70:5: ( '!=' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:70:7: '!='
            {
            match("!="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T71

    // $ANTLR start T72
    public final void mT72() throws RecognitionException {
        try {
            int _type = T72;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:71:5: ( '>=' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:71:7: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T72

    // $ANTLR start T73
    public final void mT73() throws RecognitionException {
        try {
            int _type = T73;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:72:5: ( '<=' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:72:7: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T73

    // $ANTLR start T74
    public final void mT74() throws RecognitionException {
        try {
            int _type = T74;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:73:5: ( 'true' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:73:7: 'true'
            {
            match("true"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T74

    // $ANTLR start T75
    public final void mT75() throws RecognitionException {
        try {
            int _type = T75;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:74:5: ( 'false' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:74:7: 'false'
            {
            match("false"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T75

    // $ANTLR start T76
    public final void mT76() throws RecognitionException {
        try {
            int _type = T76;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:75:5: ( 'null' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:75:7: 'null'
            {
            match("null"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T76

    // $ANTLR start T77
    public final void mT77() throws RecognitionException {
        try {
            int _type = T77;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:76:5: ( 'public' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:76:7: 'public'
            {
            match("public"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T77

    // $ANTLR start T78
    public final void mT78() throws RecognitionException {
        try {
            int _type = T78;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:77:5: ( 'private' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:77:7: 'private'
            {
            match("private"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T78

    // $ANTLR start T79
    public final void mT79() throws RecognitionException {
        try {
            int _type = T79;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:78:5: ( 'parallel' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:78:7: 'parallel'
            {
            match("parallel"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T79

    // $ANTLR start RULE_ID
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6696:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6696:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6696:35: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_INT
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6698:10: ( ( '0' .. '9' )+ )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6698:12: ( '0' .. '9' )+
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6698:12: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6698:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_STRING
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\"') ) {
                alt5=1;
            }
            else if ( (LA5_0=='\'') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("6700:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop3:
                    do {
                        int alt3=3;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0=='\\') ) {
                            alt3=1;
                        }
                        else if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFE')) ) {
                            alt3=2;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:62: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:82: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:87: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='&')||(LA4_0>='(' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFE')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:88: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6700:129: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_ML_COMMENT
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6702:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6702:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6702:24: ( options {greedy=false; } : . )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='*') ) {
                    int LA6_1 = input.LA(2);

                    if ( (LA6_1=='/') ) {
                        alt6=2;
                    }
                    else if ( ((LA6_1>='\u0000' && LA6_1<='.')||(LA6_1>='0' && LA6_1<='\uFFFE')) ) {
                        alt6=1;
                    }


                }
                else if ( ((LA6_0>='\u0000' && LA6_0<=')')||(LA6_0>='+' && LA6_0<='\uFFFE')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6702:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match("*/"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='\t')||(LA7_0>='\u000B' && LA7_0<='\f')||(LA7_0>='\u000E' && LA7_0<='\uFFFE')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:40: ( ( '\\r' )? '\\n' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='\n'||LA9_0=='\r') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:41: ( '\\r' )? '\\n'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:41: ( '\\r' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='\r') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6704:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    // $ANTLR start RULE_WS
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6706:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6706:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6706:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\t' && LA10_0<='\n')||LA10_0=='\r'||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ANY_OTHER
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6708:16: ( . )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6708:18: .
            {
            matchAny(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ANY_OTHER

    public void mTokens() throws RecognitionException {
        // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:8: ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt11=76;
        int LA11_0 = input.LA(1);

        if ( (LA11_0=='.') ) {
            switch ( input.LA(2) ) {
            case '.':
                {
                int LA11_49 = input.LA(3);

                if ( (LA11_49=='.') ) {
                    alt11=50;
                }
                else {
                    alt11=53;}
                }
                break;
            case '*':
                {
                alt11=23;
                }
                break;
            default:
                alt11=1;}

        }
        else if ( (LA11_0=='~') ) {
            switch ( input.LA(2) ) {
            case '=':
                {
                alt11=59;
                }
                break;
            case '~':
                {
                alt11=2;
                }
                break;
            default:
                alt11=76;}

        }
        else if ( (LA11_0=='/') ) {
            switch ( input.LA(2) ) {
            case '/':
                {
                alt11=74;
                }
                break;
            case '*':
                {
                alt11=73;
                }
                break;
            default:
                alt11=3;}

        }
        else if ( (LA11_0=='-') ) {
            alt11=4;
        }
        else if ( (LA11_0=='!') ) {
            int LA11_5 = input.LA(2);

            if ( (LA11_5=='=') ) {
                alt11=61;
            }
            else {
                alt11=5;}
        }
        else if ( (LA11_0=='@') ) {
            alt11=6;
        }
        else if ( (LA11_0=='$') ) {
            alt11=7;
        }
        else if ( (LA11_0=='%') ) {
            alt11=8;
        }
        else if ( (LA11_0=='&') ) {
            int LA11_9 = input.LA(2);

            if ( (LA11_9=='&') ) {
                alt11=58;
            }
            else {
                alt11=9;}
        }
        else if ( (LA11_0=='+') ) {
            alt11=10;
        }
        else if ( (LA11_0=='=') ) {
            int LA11_11 = input.LA(2);

            if ( (LA11_11=='=') ) {
                alt11=60;
            }
            else {
                alt11=11;}
        }
        else if ( (LA11_0=='*') ) {
            alt11=12;
        }
        else if ( (LA11_0=='<') ) {
            int LA11_13 = input.LA(2);

            if ( (LA11_13=='=') ) {
                alt11=63;
            }
            else {
                alt11=13;}
        }
        else if ( (LA11_0=='>') ) {
            int LA11_14 = input.LA(2);

            if ( (LA11_14=='=') ) {
                alt11=62;
            }
            else {
                alt11=14;}
        }
        else if ( (LA11_0==':') ) {
            alt11=15;
        }
        else if ( (LA11_0=='?') ) {
            alt11=16;
        }
        else if ( (LA11_0=='[') ) {
            alt11=17;
        }
        else if ( (LA11_0=='(') ) {
            alt11=18;
        }
        else if ( (LA11_0==',') ) {
            alt11=19;
        }
        else if ( (LA11_0==']') ) {
            alt11=20;
        }
        else if ( (LA11_0==')') ) {
            alt11=21;
        }
        else if ( (LA11_0=='i') ) {
            int LA11_22 = input.LA(2);

            if ( (LA11_22=='m') ) {
                switch ( input.LA(3) ) {
                case 'p':
                    {
                    switch ( input.LA(4) ) {
                    case 'l':
                        {
                        int LA11_147 = input.LA(5);

                        if ( (LA11_147=='e') ) {
                            int LA11_181 = input.LA(6);

                            if ( (LA11_181=='m') ) {
                                int LA11_215 = input.LA(7);

                                if ( (LA11_215=='e') ) {
                                    int LA11_242 = input.LA(8);

                                    if ( (LA11_242=='n') ) {
                                        int LA11_264 = input.LA(9);

                                        if ( (LA11_264=='t') ) {
                                            int LA11_279 = input.LA(10);

                                            if ( (LA11_279=='s') ) {
                                                int LA11_292 = input.LA(11);

                                                if ( ((LA11_292>='0' && LA11_292<='9')||(LA11_292>='A' && LA11_292<='Z')||LA11_292=='_'||(LA11_292>='a' && LA11_292<='z')) ) {
                                                    alt11=70;
                                                }
                                                else {
                                                    alt11=27;}
                                            }
                                            else {
                                                alt11=70;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                        }
                        break;
                    case 'o':
                        {
                        int LA11_148 = input.LA(5);

                        if ( (LA11_148=='r') ) {
                            int LA11_182 = input.LA(6);

                            if ( (LA11_182=='t') ) {
                                int LA11_216 = input.LA(7);

                                if ( ((LA11_216>='0' && LA11_216<='9')||(LA11_216>='A' && LA11_216<='Z')||LA11_216=='_'||(LA11_216>='a' && LA11_216<='z')) ) {
                                    alt11=70;
                                }
                                else {
                                    alt11=22;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                        }
                        break;
                    default:
                        alt11=70;}

                    }
                    break;
                case 'm':
                    {
                    int LA11_117 = input.LA(4);

                    if ( (LA11_117=='u') ) {
                        int LA11_149 = input.LA(5);

                        if ( (LA11_149=='t') ) {
                            int LA11_183 = input.LA(6);

                            if ( (LA11_183=='a') ) {
                                int LA11_217 = input.LA(7);

                                if ( (LA11_217=='b') ) {
                                    int LA11_244 = input.LA(8);

                                    if ( (LA11_244=='l') ) {
                                        int LA11_265 = input.LA(9);

                                        if ( (LA11_265=='e') ) {
                                            int LA11_280 = input.LA(10);

                                            if ( ((LA11_280>='0' && LA11_280<='9')||(LA11_280>='A' && LA11_280<='Z')||LA11_280=='_'||(LA11_280>='a' && LA11_280<='z')) ) {
                                                alt11=70;
                                            }
                                            else {
                                                alt11=39;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                default:
                    alt11=70;}

            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0==';') ) {
            alt11=24;
        }
        else if ( (LA11_0=='u') ) {
            int LA11_24 = input.LA(2);

            if ( (LA11_24=='n') ) {
                switch ( input.LA(3) ) {
                case 'i':
                    {
                    int LA11_118 = input.LA(4);

                    if ( (LA11_118=='t') ) {
                        int LA11_150 = input.LA(5);

                        if ( ((LA11_150>='0' && LA11_150<='9')||(LA11_150>='A' && LA11_150<='Z')||LA11_150=='_'||(LA11_150>='a' && LA11_150<='z')) ) {
                            alt11=70;
                        }
                        else {
                            alt11=25;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                case 's':
                    {
                    int LA11_119 = input.LA(4);

                    if ( (LA11_119=='e') ) {
                        int LA11_151 = input.LA(5);

                        if ( (LA11_151=='t') ) {
                            int LA11_185 = input.LA(6);

                            if ( ((LA11_185>='0' && LA11_185<='9')||(LA11_185>='A' && LA11_185<='Z')||LA11_185=='_'||(LA11_185>='a' && LA11_185<='z')) ) {
                                alt11=70;
                            }
                            else {
                                alt11=40;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                default:
                    alt11=70;}

            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='v') ) {
            int LA11_25 = input.LA(2);

            if ( (LA11_25=='e') ) {
                int LA11_84 = input.LA(3);

                if ( (LA11_84=='r') ) {
                    int LA11_120 = input.LA(4);

                    if ( (LA11_120=='s') ) {
                        int LA11_152 = input.LA(5);

                        if ( (LA11_152=='i') ) {
                            int LA11_186 = input.LA(6);

                            if ( (LA11_186=='o') ) {
                                int LA11_219 = input.LA(7);

                                if ( (LA11_219=='n') ) {
                                    int LA11_245 = input.LA(8);

                                    if ( ((LA11_245>='0' && LA11_245<='9')||(LA11_245>='A' && LA11_245<='Z')||LA11_245=='_'||(LA11_245>='a' && LA11_245<='z')) ) {
                                        alt11=70;
                                    }
                                    else {
                                        alt11=26;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='{') ) {
            alt11=28;
        }
        else if ( (LA11_0=='p') ) {
            switch ( input.LA(2) ) {
            case 'r':
                {
                switch ( input.LA(3) ) {
                case 'i':
                    {
                    int LA11_121 = input.LA(4);

                    if ( (LA11_121=='v') ) {
                        int LA11_153 = input.LA(5);

                        if ( (LA11_153=='a') ) {
                            int LA11_187 = input.LA(6);

                            if ( (LA11_187=='t') ) {
                                int LA11_220 = input.LA(7);

                                if ( (LA11_220=='e') ) {
                                    int LA11_246 = input.LA(8);

                                    if ( ((LA11_246>='0' && LA11_246<='9')||(LA11_246>='A' && LA11_246<='Z')||LA11_246=='_'||(LA11_246>='a' && LA11_246<='z')) ) {
                                        alt11=70;
                                    }
                                    else {
                                        alt11=68;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                case 'o':
                    {
                    switch ( input.LA(4) ) {
                    case 'p':
                        {
                        int LA11_154 = input.LA(5);

                        if ( (LA11_154=='e') ) {
                            int LA11_188 = input.LA(6);

                            if ( (LA11_188=='r') ) {
                                int LA11_221 = input.LA(7);

                                if ( (LA11_221=='t') ) {
                                    int LA11_247 = input.LA(8);

                                    if ( (LA11_247=='i') ) {
                                        int LA11_268 = input.LA(9);

                                        if ( (LA11_268=='e') ) {
                                            int LA11_281 = input.LA(10);

                                            if ( (LA11_281=='s') ) {
                                                int LA11_294 = input.LA(11);

                                                if ( ((LA11_294>='0' && LA11_294<='9')||(LA11_294>='A' && LA11_294<='Z')||LA11_294=='_'||(LA11_294>='a' && LA11_294<='z')) ) {
                                                    alt11=70;
                                                }
                                                else {
                                                    alt11=29;}
                                            }
                                            else {
                                                alt11=70;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                        }
                        break;
                    case 'v':
                        {
                        int LA11_155 = input.LA(5);

                        if ( (LA11_155=='i') ) {
                            int LA11_189 = input.LA(6);

                            if ( (LA11_189=='d') ) {
                                int LA11_222 = input.LA(7);

                                if ( (LA11_222=='e') ) {
                                    int LA11_248 = input.LA(8);

                                    if ( (LA11_248=='s') ) {
                                        int LA11_269 = input.LA(9);

                                        if ( ((LA11_269>='0' && LA11_269<='9')||(LA11_269>='A' && LA11_269<='Z')||LA11_269=='_'||(LA11_269>='a' && LA11_269<='z')) ) {
                                            alt11=70;
                                        }
                                        else {
                                            alt11=30;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                        }
                        break;
                    default:
                        alt11=70;}

                    }
                    break;
                case 'e':
                    {
                    int LA11_123 = input.LA(4);

                    if ( (LA11_123=='c') ) {
                        int LA11_156 = input.LA(5);

                        if ( (LA11_156=='o') ) {
                            int LA11_190 = input.LA(6);

                            if ( (LA11_190=='n') ) {
                                int LA11_223 = input.LA(7);

                                if ( (LA11_223=='d') ) {
                                    int LA11_249 = input.LA(8);

                                    if ( (LA11_249=='i') ) {
                                        int LA11_270 = input.LA(9);

                                        if ( (LA11_270=='t') ) {
                                            int LA11_283 = input.LA(10);

                                            if ( (LA11_283=='i') ) {
                                                int LA11_295 = input.LA(11);

                                                if ( (LA11_295=='o') ) {
                                                    int LA11_303 = input.LA(12);

                                                    if ( (LA11_303=='n') ) {
                                                        int LA11_308 = input.LA(13);

                                                        if ( ((LA11_308>='0' && LA11_308<='9')||(LA11_308>='A' && LA11_308<='Z')||LA11_308=='_'||(LA11_308>='a' && LA11_308<='z')) ) {
                                                            alt11=70;
                                                        }
                                                        else {
                                                            alt11=54;}
                                                    }
                                                    else {
                                                        alt11=70;}
                                                }
                                                else {
                                                    alt11=70;}
                                            }
                                            else {
                                                alt11=70;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                default:
                    alt11=70;}

                }
                break;
            case 'a':
                {
                int LA11_87 = input.LA(3);

                if ( (LA11_87=='r') ) {
                    int LA11_124 = input.LA(4);

                    if ( (LA11_124=='a') ) {
                        int LA11_157 = input.LA(5);

                        if ( (LA11_157=='l') ) {
                            int LA11_191 = input.LA(6);

                            if ( (LA11_191=='l') ) {
                                int LA11_224 = input.LA(7);

                                if ( (LA11_224=='e') ) {
                                    int LA11_250 = input.LA(8);

                                    if ( (LA11_250=='l') ) {
                                        int LA11_271 = input.LA(9);

                                        if ( ((LA11_271>='0' && LA11_271<='9')||(LA11_271>='A' && LA11_271<='Z')||LA11_271=='_'||(LA11_271>='a' && LA11_271<='z')) ) {
                                            alt11=70;
                                        }
                                        else {
                                            alt11=69;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            case 'u':
                {
                int LA11_88 = input.LA(3);

                if ( (LA11_88=='b') ) {
                    int LA11_125 = input.LA(4);

                    if ( (LA11_125=='l') ) {
                        int LA11_158 = input.LA(5);

                        if ( (LA11_158=='i') ) {
                            int LA11_192 = input.LA(6);

                            if ( (LA11_192=='c') ) {
                                int LA11_225 = input.LA(7);

                                if ( ((LA11_225>='0' && LA11_225<='9')||(LA11_225>='A' && LA11_225<='Z')||LA11_225=='_'||(LA11_225>='a' && LA11_225<='z')) ) {
                                    alt11=70;
                                }
                                else {
                                    alt11=67;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            case 'o':
                {
                int LA11_89 = input.LA(3);

                if ( (LA11_89=='s') ) {
                    int LA11_126 = input.LA(4);

                    if ( (LA11_126=='t') ) {
                        int LA11_159 = input.LA(5);

                        if ( (LA11_159=='c') ) {
                            int LA11_193 = input.LA(6);

                            if ( (LA11_193=='o') ) {
                                int LA11_226 = input.LA(7);

                                if ( (LA11_226=='n') ) {
                                    int LA11_252 = input.LA(8);

                                    if ( (LA11_252=='d') ) {
                                        int LA11_272 = input.LA(9);

                                        if ( (LA11_272=='i') ) {
                                            int LA11_285 = input.LA(10);

                                            if ( (LA11_285=='t') ) {
                                                int LA11_296 = input.LA(11);

                                                if ( (LA11_296=='i') ) {
                                                    int LA11_304 = input.LA(12);

                                                    if ( (LA11_304=='o') ) {
                                                        int LA11_309 = input.LA(13);

                                                        if ( (LA11_309=='n') ) {
                                                            int LA11_313 = input.LA(14);

                                                            if ( ((LA11_313>='0' && LA11_313<='9')||(LA11_313>='A' && LA11_313<='Z')||LA11_313=='_'||(LA11_313>='a' && LA11_313<='z')) ) {
                                                                alt11=70;
                                                            }
                                                            else {
                                                                alt11=55;}
                                                        }
                                                        else {
                                                            alt11=70;}
                                                    }
                                                    else {
                                                        alt11=70;}
                                                }
                                                else {
                                                    alt11=70;}
                                            }
                                            else {
                                                alt11=70;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            default:
                alt11=70;}

        }
        else if ( (LA11_0=='}') ) {
            alt11=31;
        }
        else if ( (LA11_0=='r') ) {
            int LA11_29 = input.LA(2);

            if ( (LA11_29=='e') ) {
                switch ( input.LA(3) ) {
                case 'q':
                    {
                    int LA11_127 = input.LA(4);

                    if ( (LA11_127=='u') ) {
                        int LA11_160 = input.LA(5);

                        if ( (LA11_160=='i') ) {
                            int LA11_194 = input.LA(6);

                            if ( (LA11_194=='r') ) {
                                int LA11_227 = input.LA(7);

                                if ( (LA11_227=='e') ) {
                                    int LA11_253 = input.LA(8);

                                    if ( (LA11_253=='s') ) {
                                        int LA11_273 = input.LA(9);

                                        if ( ((LA11_273>='0' && LA11_273<='9')||(LA11_273>='A' && LA11_273<='Z')||LA11_273=='_'||(LA11_273>='a' && LA11_273<='z')) ) {
                                            alt11=70;
                                        }
                                        else {
                                            alt11=32;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                case 's':
                    {
                    int LA11_128 = input.LA(4);

                    if ( (LA11_128=='o') ) {
                        int LA11_161 = input.LA(5);

                        if ( (LA11_161=='l') ) {
                            int LA11_195 = input.LA(6);

                            if ( (LA11_195=='v') ) {
                                int LA11_228 = input.LA(7);

                                if ( (LA11_228=='e') ) {
                                    int LA11_254 = input.LA(8);

                                    if ( (LA11_254=='r') ) {
                                        int LA11_274 = input.LA(9);

                                        if ( ((LA11_274>='0' && LA11_274<='9')||(LA11_274>='A' && LA11_274<='Z')||LA11_274=='_'||(LA11_274>='a' && LA11_274<='z')) ) {
                                            alt11=70;
                                        }
                                        else {
                                            alt11=52;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                case 'p':
                    {
                    int LA11_129 = input.LA(4);

                    if ( (LA11_129=='o') ) {
                        int LA11_162 = input.LA(5);

                        if ( (LA11_162=='s') ) {
                            int LA11_196 = input.LA(6);

                            if ( (LA11_196=='i') ) {
                                int LA11_229 = input.LA(7);

                                if ( (LA11_229=='t') ) {
                                    int LA11_255 = input.LA(8);

                                    if ( (LA11_255=='o') ) {
                                        int LA11_275 = input.LA(9);

                                        if ( (LA11_275=='r') ) {
                                            int LA11_288 = input.LA(10);

                                            if ( (LA11_288=='i') ) {
                                                int LA11_297 = input.LA(11);

                                                if ( (LA11_297=='e') ) {
                                                    int LA11_305 = input.LA(12);

                                                    if ( (LA11_305=='s') ) {
                                                        int LA11_310 = input.LA(13);

                                                        if ( ((LA11_310>='0' && LA11_310<='9')||(LA11_310>='A' && LA11_310<='Z')||LA11_310=='_'||(LA11_310>='a' && LA11_310<='z')) ) {
                                                            alt11=70;
                                                        }
                                                        else {
                                                            alt11=36;}
                                                    }
                                                    else {
                                                        alt11=70;}
                                                }
                                                else {
                                                    alt11=70;}
                                            }
                                            else {
                                                alt11=70;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                    }
                    break;
                default:
                    alt11=70;}

            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='m') ) {
            int LA11_30 = input.LA(2);

            if ( (LA11_30=='e') ) {
                int LA11_92 = input.LA(3);

                if ( (LA11_92=='t') ) {
                    int LA11_130 = input.LA(4);

                    if ( (LA11_130=='a') ) {
                        int LA11_163 = input.LA(5);

                        if ( ((LA11_163>='0' && LA11_163<='9')||(LA11_163>='A' && LA11_163<='Z')||LA11_163=='_'||(LA11_163>='a' && LA11_163<='z')) ) {
                            alt11=70;
                        }
                        else {
                            alt11=33;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='a') ) {
            switch ( input.LA(2) ) {
            case 'r':
                {
                int LA11_93 = input.LA(3);

                if ( (LA11_93=='t') ) {
                    int LA11_131 = input.LA(4);

                    if ( (LA11_131=='i') ) {
                        int LA11_164 = input.LA(5);

                        if ( (LA11_164=='f') ) {
                            int LA11_198 = input.LA(6);

                            if ( (LA11_198=='a') ) {
                                int LA11_230 = input.LA(7);

                                if ( (LA11_230=='c') ) {
                                    int LA11_256 = input.LA(8);

                                    if ( (LA11_256=='t') ) {
                                        int LA11_276 = input.LA(9);

                                        if ( (LA11_276=='s') ) {
                                            int LA11_289 = input.LA(10);

                                            if ( ((LA11_289>='0' && LA11_289<='9')||(LA11_289>='A' && LA11_289<='Z')||LA11_289=='_'||(LA11_289>='a' && LA11_289<='z')) ) {
                                                alt11=70;
                                            }
                                            else {
                                                alt11=42;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            case 's':
                {
                int LA11_94 = input.LA(3);

                if ( (LA11_94=='s') ) {
                    int LA11_132 = input.LA(4);

                    if ( (LA11_132=='e') ) {
                        int LA11_165 = input.LA(5);

                        if ( (LA11_165=='r') ) {
                            int LA11_199 = input.LA(6);

                            if ( (LA11_199=='t') ) {
                                int LA11_231 = input.LA(7);

                                if ( ((LA11_231>='0' && LA11_231<='9')||(LA11_231>='A' && LA11_231<='Z')||LA11_231=='_'||(LA11_231>='a' && LA11_231<='z')) ) {
                                    alt11=70;
                                }
                                else {
                                    alt11=56;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            case 'd':
                {
                int LA11_95 = input.LA(3);

                if ( (LA11_95=='v') ) {
                    int LA11_133 = input.LA(4);

                    if ( (LA11_133=='i') ) {
                        int LA11_166 = input.LA(5);

                        if ( (LA11_166=='c') ) {
                            int LA11_200 = input.LA(6);

                            if ( (LA11_200=='e') ) {
                                int LA11_232 = input.LA(7);

                                if ( ((LA11_232>='0' && LA11_232<='9')||(LA11_232>='A' && LA11_232<='Z')||LA11_232=='_'||(LA11_232>='a' && LA11_232<='z')) ) {
                                    alt11=70;
                                }
                                else {
                                    alt11=34;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            case 'c':
                {
                int LA11_96 = input.LA(3);

                if ( (LA11_96=='t') ) {
                    switch ( input.LA(4) ) {
                    case 'o':
                        {
                        int LA11_167 = input.LA(5);

                        if ( (LA11_167=='r') ) {
                            int LA11_201 = input.LA(6);

                            if ( ((LA11_201>='0' && LA11_201<='9')||(LA11_201>='A' && LA11_201<='Z')||LA11_201=='_'||(LA11_201>='a' && LA11_201<='z')) ) {
                                alt11=70;
                            }
                            else {
                                alt11=49;}
                        }
                        else {
                            alt11=70;}
                        }
                        break;
                    case 'i':
                        {
                        int LA11_168 = input.LA(5);

                        if ( (LA11_168=='o') ) {
                            int LA11_202 = input.LA(6);

                            if ( (LA11_202=='n') ) {
                                int LA11_234 = input.LA(7);

                                if ( ((LA11_234>='0' && LA11_234<='9')||(LA11_234>='A' && LA11_234<='Z')||LA11_234=='_'||(LA11_234>='a' && LA11_234<='z')) ) {
                                    alt11=70;
                                }
                                else {
                                    alt11=48;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                        }
                        break;
                    default:
                        alt11=70;}

                }
                else {
                    alt11=70;}
                }
                break;
            case 'n':
                {
                int LA11_97 = input.LA(3);

                if ( (LA11_97=='n') ) {
                    int LA11_135 = input.LA(4);

                    if ( (LA11_135=='o') ) {
                        int LA11_169 = input.LA(5);

                        if ( (LA11_169=='t') ) {
                            int LA11_203 = input.LA(6);

                            if ( (LA11_203=='a') ) {
                                int LA11_235 = input.LA(7);

                                if ( (LA11_235=='t') ) {
                                    int LA11_260 = input.LA(8);

                                    if ( (LA11_260=='i') ) {
                                        int LA11_277 = input.LA(9);

                                        if ( (LA11_277=='o') ) {
                                            int LA11_290 = input.LA(10);

                                            if ( (LA11_290=='n') ) {
                                                int LA11_299 = input.LA(11);

                                                if ( (LA11_299=='s') ) {
                                                    int LA11_306 = input.LA(12);

                                                    if ( ((LA11_306>='0' && LA11_306<='9')||(LA11_306>='A' && LA11_306<='Z')||LA11_306=='_'||(LA11_306>='a' && LA11_306<='z')) ) {
                                                        alt11=70;
                                                    }
                                                    else {
                                                        alt11=43;}
                                                }
                                                else {
                                                    alt11=70;}
                                            }
                                            else {
                                                alt11=70;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            default:
                alt11=70;}

        }
        else if ( (LA11_0=='s') ) {
            int LA11_32 = input.LA(2);

            if ( (LA11_32=='e') ) {
                int LA11_98 = input.LA(3);

                if ( (LA11_98=='q') ) {
                    int LA11_136 = input.LA(4);

                    if ( (LA11_136=='u') ) {
                        int LA11_170 = input.LA(5);

                        if ( (LA11_170=='e') ) {
                            int LA11_204 = input.LA(6);

                            if ( (LA11_204=='n') ) {
                                int LA11_236 = input.LA(7);

                                if ( (LA11_236=='t') ) {
                                    int LA11_261 = input.LA(8);

                                    if ( (LA11_261=='i') ) {
                                        int LA11_278 = input.LA(9);

                                        if ( (LA11_278=='a') ) {
                                            int LA11_291 = input.LA(10);

                                            if ( (LA11_291=='l') ) {
                                                int LA11_300 = input.LA(11);

                                                if ( ((LA11_300>='0' && LA11_300<='9')||(LA11_300>='A' && LA11_300<='Z')||LA11_300=='_'||(LA11_300>='a' && LA11_300<='z')) ) {
                                                    alt11=70;
                                                }
                                                else {
                                                    alt11=35;}
                                            }
                                            else {
                                                alt11=70;}
                                        }
                                        else {
                                            alt11=70;}
                                    }
                                    else {
                                        alt11=70;}
                                }
                                else {
                                    alt11=70;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='w') ) {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA11_99 = input.LA(3);

                if ( (LA11_99=='t') ) {
                    int LA11_137 = input.LA(4);

                    if ( (LA11_137=='h') ) {
                        int LA11_171 = input.LA(5);

                        if ( ((LA11_171>='0' && LA11_171<='9')||(LA11_171>='A' && LA11_171<='Z')||LA11_171=='_'||(LA11_171>='a' && LA11_171<='z')) ) {
                            alt11=70;
                        }
                        else {
                            alt11=47;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            case 'h':
                {
                int LA11_100 = input.LA(3);

                if ( (LA11_100=='e') ) {
                    int LA11_138 = input.LA(4);

                    if ( (LA11_138=='n') ) {
                        int LA11_172 = input.LA(5);

                        if ( ((LA11_172>='0' && LA11_172<='9')||(LA11_172>='A' && LA11_172<='Z')||LA11_172=='_'||(LA11_172>='a' && LA11_172<='z')) ) {
                            alt11=70;
                        }
                        else {
                            alt11=37;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            default:
                alt11=70;}

        }
        else if ( (LA11_0=='l') ) {
            switch ( input.LA(2) ) {
            case 'a':
                {
                int LA11_101 = input.LA(3);

                if ( (LA11_101=='y') ) {
                    int LA11_139 = input.LA(4);

                    if ( (LA11_139=='o') ) {
                        int LA11_173 = input.LA(5);

                        if ( (LA11_173=='u') ) {
                            int LA11_207 = input.LA(6);

                            if ( (LA11_207=='t') ) {
                                int LA11_237 = input.LA(7);

                                if ( ((LA11_237>='0' && LA11_237<='9')||(LA11_237>='A' && LA11_237<='Z')||LA11_237=='_'||(LA11_237>='a' && LA11_237<='z')) ) {
                                    alt11=70;
                                }
                                else {
                                    alt11=51;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            case 'o':
                {
                int LA11_102 = input.LA(3);

                if ( (LA11_102=='c') ) {
                    int LA11_140 = input.LA(4);

                    if ( (LA11_140=='a') ) {
                        int LA11_174 = input.LA(5);

                        if ( (LA11_174=='l') ) {
                            int LA11_208 = input.LA(6);

                            if ( ((LA11_208>='0' && LA11_208<='9')||(LA11_208>='A' && LA11_208<='Z')||LA11_208=='_'||(LA11_208>='a' && LA11_208<='z')) ) {
                                alt11=70;
                            }
                            else {
                                alt11=38;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
                }
                break;
            default:
                alt11=70;}

        }
        else if ( (LA11_0=='#') ) {
            alt11=41;
        }
        else if ( (LA11_0=='e') ) {
            int LA11_36 = input.LA(2);

            if ( (LA11_36=='x') ) {
                int LA11_104 = input.LA(3);

                if ( (LA11_104=='p') ) {
                    int LA11_141 = input.LA(4);

                    if ( (LA11_141=='r') ) {
                        int LA11_175 = input.LA(5);

                        if ( ((LA11_175>='0' && LA11_175<='9')||(LA11_175>='A' && LA11_175<='Z')||LA11_175=='_'||(LA11_175>='a' && LA11_175<='z')) ) {
                            alt11=70;
                        }
                        else {
                            alt11=44;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='g') ) {
            int LA11_37 = input.LA(2);

            if ( (LA11_37=='r') ) {
                int LA11_105 = input.LA(3);

                if ( (LA11_105=='o') ) {
                    int LA11_142 = input.LA(4);

                    if ( (LA11_142=='u') ) {
                        int LA11_176 = input.LA(5);

                        if ( (LA11_176=='p') ) {
                            int LA11_210 = input.LA(6);

                            if ( ((LA11_210>='0' && LA11_210<='9')||(LA11_210>='A' && LA11_210<='Z')||LA11_210=='_'||(LA11_210>='a' && LA11_210<='z')) ) {
                                alt11=70;
                            }
                            else {
                                alt11=45;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='h') ) {
            int LA11_38 = input.LA(2);

            if ( (LA11_38=='i') ) {
                int LA11_106 = input.LA(3);

                if ( (LA11_106=='d') ) {
                    int LA11_143 = input.LA(4);

                    if ( (LA11_143=='d') ) {
                        int LA11_177 = input.LA(5);

                        if ( (LA11_177=='e') ) {
                            int LA11_211 = input.LA(6);

                            if ( (LA11_211=='n') ) {
                                int LA11_240 = input.LA(7);

                                if ( ((LA11_240>='0' && LA11_240<='9')||(LA11_240>='A' && LA11_240<='Z')||LA11_240=='_'||(LA11_240>='a' && LA11_240<='z')) ) {
                                    alt11=70;
                                }
                                else {
                                    alt11=46;}
                            }
                            else {
                                alt11=70;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='|') ) {
            int LA11_39 = input.LA(2);

            if ( (LA11_39=='|') ) {
                alt11=57;
            }
            else {
                alt11=76;}
        }
        else if ( (LA11_0=='t') ) {
            int LA11_40 = input.LA(2);

            if ( (LA11_40=='r') ) {
                int LA11_108 = input.LA(3);

                if ( (LA11_108=='u') ) {
                    int LA11_144 = input.LA(4);

                    if ( (LA11_144=='e') ) {
                        int LA11_178 = input.LA(5);

                        if ( ((LA11_178>='0' && LA11_178<='9')||(LA11_178>='A' && LA11_178<='Z')||LA11_178=='_'||(LA11_178>='a' && LA11_178<='z')) ) {
                            alt11=70;
                        }
                        else {
                            alt11=64;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='f') ) {
            int LA11_41 = input.LA(2);

            if ( (LA11_41=='a') ) {
                int LA11_109 = input.LA(3);

                if ( (LA11_109=='l') ) {
                    int LA11_145 = input.LA(4);

                    if ( (LA11_145=='s') ) {
                        int LA11_179 = input.LA(5);

                        if ( (LA11_179=='e') ) {
                            int LA11_213 = input.LA(6);

                            if ( ((LA11_213>='0' && LA11_213<='9')||(LA11_213>='A' && LA11_213<='Z')||LA11_213=='_'||(LA11_213>='a' && LA11_213<='z')) ) {
                                alt11=70;
                            }
                            else {
                                alt11=65;}
                        }
                        else {
                            alt11=70;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( (LA11_0=='n') ) {
            int LA11_42 = input.LA(2);

            if ( (LA11_42=='u') ) {
                int LA11_110 = input.LA(3);

                if ( (LA11_110=='l') ) {
                    int LA11_146 = input.LA(4);

                    if ( (LA11_146=='l') ) {
                        int LA11_180 = input.LA(5);

                        if ( ((LA11_180>='0' && LA11_180<='9')||(LA11_180>='A' && LA11_180<='Z')||LA11_180=='_'||(LA11_180>='a' && LA11_180<='z')) ) {
                            alt11=70;
                        }
                        else {
                            alt11=66;}
                    }
                    else {
                        alt11=70;}
                }
                else {
                    alt11=70;}
            }
            else {
                alt11=70;}
        }
        else if ( ((LA11_0>='A' && LA11_0<='Z')||LA11_0=='_'||(LA11_0>='b' && LA11_0<='d')||(LA11_0>='j' && LA11_0<='k')||LA11_0=='o'||LA11_0=='q'||(LA11_0>='x' && LA11_0<='z')) ) {
            alt11=70;
        }
        else if ( ((LA11_0>='0' && LA11_0<='9')) ) {
            alt11=71;
        }
        else if ( (LA11_0=='\"') ) {
            int LA11_45 = input.LA(2);

            if ( ((LA11_45>='\u0000' && LA11_45<='\uFFFE')) ) {
                alt11=72;
            }
            else {
                alt11=76;}
        }
        else if ( (LA11_0=='\'') ) {
            int LA11_46 = input.LA(2);

            if ( ((LA11_46>='\u0000' && LA11_46<='\uFFFE')) ) {
                alt11=72;
            }
            else {
                alt11=76;}
        }
        else if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
            alt11=75;
        }
        else if ( ((LA11_0>='\u0000' && LA11_0<='\b')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\u001F')||LA11_0=='\\'||LA11_0=='^'||LA11_0=='`'||(LA11_0>='\u007F' && LA11_0<='\uFFFE')) ) {
            alt11=76;
        }
        else {
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | T69 | T70 | T71 | T72 | T73 | T74 | T75 | T76 | T77 | T78 | T79 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 11, 0, input);

            throw nvae;
        }
        switch (alt11) {
            case 1 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:10: T11
                {
                mT11(); 

                }
                break;
            case 2 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:14: T12
                {
                mT12(); 

                }
                break;
            case 3 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:18: T13
                {
                mT13(); 

                }
                break;
            case 4 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:22: T14
                {
                mT14(); 

                }
                break;
            case 5 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:26: T15
                {
                mT15(); 

                }
                break;
            case 6 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:30: T16
                {
                mT16(); 

                }
                break;
            case 7 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:34: T17
                {
                mT17(); 

                }
                break;
            case 8 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:38: T18
                {
                mT18(); 

                }
                break;
            case 9 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:42: T19
                {
                mT19(); 

                }
                break;
            case 10 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:46: T20
                {
                mT20(); 

                }
                break;
            case 11 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:50: T21
                {
                mT21(); 

                }
                break;
            case 12 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:54: T22
                {
                mT22(); 

                }
                break;
            case 13 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:58: T23
                {
                mT23(); 

                }
                break;
            case 14 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:62: T24
                {
                mT24(); 

                }
                break;
            case 15 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:66: T25
                {
                mT25(); 

                }
                break;
            case 16 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:70: T26
                {
                mT26(); 

                }
                break;
            case 17 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:74: T27
                {
                mT27(); 

                }
                break;
            case 18 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:78: T28
                {
                mT28(); 

                }
                break;
            case 19 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:82: T29
                {
                mT29(); 

                }
                break;
            case 20 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:86: T30
                {
                mT30(); 

                }
                break;
            case 21 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:90: T31
                {
                mT31(); 

                }
                break;
            case 22 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:94: T32
                {
                mT32(); 

                }
                break;
            case 23 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:98: T33
                {
                mT33(); 

                }
                break;
            case 24 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:102: T34
                {
                mT34(); 

                }
                break;
            case 25 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:106: T35
                {
                mT35(); 

                }
                break;
            case 26 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:110: T36
                {
                mT36(); 

                }
                break;
            case 27 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:114: T37
                {
                mT37(); 

                }
                break;
            case 28 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:118: T38
                {
                mT38(); 

                }
                break;
            case 29 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:122: T39
                {
                mT39(); 

                }
                break;
            case 30 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:126: T40
                {
                mT40(); 

                }
                break;
            case 31 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:130: T41
                {
                mT41(); 

                }
                break;
            case 32 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:134: T42
                {
                mT42(); 

                }
                break;
            case 33 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:138: T43
                {
                mT43(); 

                }
                break;
            case 34 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:142: T44
                {
                mT44(); 

                }
                break;
            case 35 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:146: T45
                {
                mT45(); 

                }
                break;
            case 36 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:150: T46
                {
                mT46(); 

                }
                break;
            case 37 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:154: T47
                {
                mT47(); 

                }
                break;
            case 38 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:158: T48
                {
                mT48(); 

                }
                break;
            case 39 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:162: T49
                {
                mT49(); 

                }
                break;
            case 40 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:166: T50
                {
                mT50(); 

                }
                break;
            case 41 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:170: T51
                {
                mT51(); 

                }
                break;
            case 42 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:174: T52
                {
                mT52(); 

                }
                break;
            case 43 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:178: T53
                {
                mT53(); 

                }
                break;
            case 44 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:182: T54
                {
                mT54(); 

                }
                break;
            case 45 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:186: T55
                {
                mT55(); 

                }
                break;
            case 46 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:190: T56
                {
                mT56(); 

                }
                break;
            case 47 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:194: T57
                {
                mT57(); 

                }
                break;
            case 48 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:198: T58
                {
                mT58(); 

                }
                break;
            case 49 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:202: T59
                {
                mT59(); 

                }
                break;
            case 50 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:206: T60
                {
                mT60(); 

                }
                break;
            case 51 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:210: T61
                {
                mT61(); 

                }
                break;
            case 52 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:214: T62
                {
                mT62(); 

                }
                break;
            case 53 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:218: T63
                {
                mT63(); 

                }
                break;
            case 54 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:222: T64
                {
                mT64(); 

                }
                break;
            case 55 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:226: T65
                {
                mT65(); 

                }
                break;
            case 56 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:230: T66
                {
                mT66(); 

                }
                break;
            case 57 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:234: T67
                {
                mT67(); 

                }
                break;
            case 58 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:238: T68
                {
                mT68(); 

                }
                break;
            case 59 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:242: T69
                {
                mT69(); 

                }
                break;
            case 60 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:246: T70
                {
                mT70(); 

                }
                break;
            case 61 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:250: T71
                {
                mT71(); 

                }
                break;
            case 62 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:254: T72
                {
                mT72(); 

                }
                break;
            case 63 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:258: T73
                {
                mT73(); 

                }
                break;
            case 64 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:262: T74
                {
                mT74(); 

                }
                break;
            case 65 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:266: T75
                {
                mT75(); 

                }
                break;
            case 66 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:270: T76
                {
                mT76(); 

                }
                break;
            case 67 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:274: T77
                {
                mT77(); 

                }
                break;
            case 68 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:278: T78
                {
                mT78(); 

                }
                break;
            case 69 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:282: T79
                {
                mT79(); 

                }
                break;
            case 70 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:286: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 71 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:294: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 72 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:303: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 73 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:315: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 74 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:331: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 75 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:347: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 76 :
                // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1:355: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


 

}