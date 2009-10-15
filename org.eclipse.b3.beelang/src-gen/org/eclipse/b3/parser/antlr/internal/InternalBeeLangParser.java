package org.eclipse.b3.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.b3.services.BeeLangGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalBeeLangParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'.'", "'/'", "'-'", "'!'", "'@'", "'$'", "'%'", "'&'", "'+'", "'='", "'*'", "'<'", "'>'", "':'", "'?'", "'['", "'('", "','", "']'", "')'", "'import'", "'.*'", "';'", "'synchronized'", "'unit'", "'version'", "'implements'", "'{'", "'provides'", "'}'", "'requires'", "'meta'", "'properties'", "'unset'", "'property'", "'advice'", "'synchronize'", "'repositories'", "'when'", "'immutable'", "'#'", "'artifacts'", "'group'", "'void'", "'with'", "'action'", "'actor'", "'result'", "'resolver'", "'..'", "'precondition'", "'postcondition'", "'assert'", "'||'", "'&&'", "'~='", "'=='", "'!='", "'>='", "'<='", "'->'", "'true'", "'false'", "'null'", "'public'", "'private'"
    };
    public static final int RULE_ML_COMMENT=7;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int EOF=-1;
    public static final int RULE_INT=6;
    public static final int RULE_STRING=5;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;

        public InternalBeeLangParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g"; }


     
     	private BeeLangGrammarAccess grammarAccess;
     	
        public InternalBeeLangParser(TokenStream input, IAstFactory factory, BeeLangGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("org/eclipse/b3/parser/antlr/internal/InternalBeeLang.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "BeeModel";	
       	} 



    // $ANTLR start entryRuleBeeModel
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:73:1: entryRuleBeeModel returns [EObject current=null] : iv_ruleBeeModel= ruleBeeModel EOF ;
    public final EObject entryRuleBeeModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBeeModel = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:73:50: (iv_ruleBeeModel= ruleBeeModel EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:74:2: iv_ruleBeeModel= ruleBeeModel EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBeeModelRule(), currentNode); 
            pushFollow(FOLLOW_ruleBeeModel_in_entryRuleBeeModel73);
            iv_ruleBeeModel=ruleBeeModel();
            _fsp--;

             current =iv_ruleBeeModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBeeModel83); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleBeeModel


    // $ANTLR start ruleBeeModel
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:81:1: ruleBeeModel returns [EObject current=null] : ( (lv_imports_0= ruleImport )* (lv_body_1= ruleUnit ) ) ;
    public final EObject ruleBeeModel() throws RecognitionException {
        EObject current = null;

        EObject lv_imports_0 = null;

        EObject lv_body_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:86:6: ( ( (lv_imports_0= ruleImport )* (lv_body_1= ruleUnit ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:87:1: ( (lv_imports_0= ruleImport )* (lv_body_1= ruleUnit ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:87:1: ( (lv_imports_0= ruleImport )* (lv_body_1= ruleUnit ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:87:2: (lv_imports_0= ruleImport )* (lv_body_1= ruleUnit )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:87:2: (lv_imports_0= ruleImport )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==31) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:90:6: lv_imports_0= ruleImport
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getBeeModelAccess().getImportsImportParserRuleCall_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleImport_in_ruleBeeModel142);
            	    lv_imports_0=ruleImport();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getBeeModelRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "imports", lv_imports_0, "Import", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:108:3: (lv_body_1= ruleUnit )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:111:6: lv_body_1= ruleUnit
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getBeeModelAccess().getBodyUnitParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleUnit_in_ruleBeeModel181);
            lv_body_1=ruleUnit();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getBeeModelRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "body", lv_body_1, "Unit", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleBeeModel


    // $ANTLR start entryRuleQualifiedName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:136:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:136:54: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:137:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getQualifiedNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName219);
            iv_ruleQualifiedName=ruleQualifiedName();
            _fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedName230); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleQualifiedName


    // $ANTLR start ruleQualifiedName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:144:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:150:6: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:151:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:151:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:151:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedName270); 

            		current.merge(this_ID_0);
                
             
                createLeafNode(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:158:1: (kw= '.' this_ID_2= RULE_ID )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:159:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)input.LT(1);
            	    match(input,11,FOLLOW_11_in_ruleQualifiedName289); 

            	            current.merge(kw);
            	            createLeafNode(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0(), null); 
            	        
            	    this_ID_2=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedName304); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        createLeafNode(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleQualifiedName


    // $ANTLR start entryRuleEscapedQualifiedName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:179:1: entryRuleEscapedQualifiedName returns [String current=null] : iv_ruleEscapedQualifiedName= ruleEscapedQualifiedName EOF ;
    public final String entryRuleEscapedQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEscapedQualifiedName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:179:61: (iv_ruleEscapedQualifiedName= ruleEscapedQualifiedName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:180:2: iv_ruleEscapedQualifiedName= ruleEscapedQualifiedName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEscapedQualifiedNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_entryRuleEscapedQualifiedName350);
            iv_ruleEscapedQualifiedName=ruleEscapedQualifiedName();
            _fsp--;

             current =iv_ruleEscapedQualifiedName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEscapedQualifiedName361); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEscapedQualifiedName


    // $ANTLR start ruleEscapedQualifiedName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:187:1: ruleEscapedQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName ) ;
    public final AntlrDatatypeRuleToken ruleEscapedQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        AntlrDatatypeRuleToken this_QualifiedName_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:193:6: ( (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:194:1: (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:194:1: (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_STRING) ) {
                alt3=1;
            }
            else if ( (LA3_0==RULE_ID) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("194:1: (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName )", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:194:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleEscapedQualifiedName401); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEscapedQualifiedNameAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:203:5: this_QualifiedName_1= ruleQualifiedName
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEscapedQualifiedNameAccess().getQualifiedNameParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleEscapedQualifiedName434);
                    this_QualifiedName_1=ruleQualifiedName();
                    _fsp--;


                    		current.merge(this_QualifiedName_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEscapedQualifiedName


    // $ANTLR start entryRuleInterfaceName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:221:1: entryRuleInterfaceName returns [String current=null] : iv_ruleInterfaceName= ruleInterfaceName EOF ;
    public final String entryRuleInterfaceName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInterfaceName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:221:54: (iv_ruleInterfaceName= ruleInterfaceName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:222:2: iv_ruleInterfaceName= ruleInterfaceName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInterfaceNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleInterfaceName_in_entryRuleInterfaceName478);
            iv_ruleInterfaceName=ruleInterfaceName();
            _fsp--;

             current =iv_ruleInterfaceName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceName489); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleInterfaceName


    // $ANTLR start ruleInterfaceName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:229:1: ruleInterfaceName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_QualifiedName_0= ruleQualifiedName ;
    public final AntlrDatatypeRuleToken ruleInterfaceName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_QualifiedName_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:235:6: (this_QualifiedName_0= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:237:5: this_QualifiedName_0= ruleQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getInterfaceNameAccess().getQualifiedNameParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleInterfaceName535);
            this_QualifiedName_0=ruleQualifiedName();
            _fsp--;


            		current.merge(this_QualifiedName_0);
                
             
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleInterfaceName


    // $ANTLR start entryRuleCompoundName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:255:1: entryRuleCompoundName returns [String current=null] : iv_ruleCompoundName= ruleCompoundName EOF ;
    public final String entryRuleCompoundName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCompoundName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:255:53: (iv_ruleCompoundName= ruleCompoundName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:256:2: iv_ruleCompoundName= ruleCompoundName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCompoundNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleCompoundName_in_entryRuleCompoundName578);
            iv_ruleCompoundName=ruleCompoundName();
            _fsp--;

             current =iv_ruleCompoundName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCompoundName589); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCompoundName


    // $ANTLR start ruleCompoundName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:263:1: ruleCompoundName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName ) ;
    public final AntlrDatatypeRuleToken ruleCompoundName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_EscapedQualifiedName_0 = null;

        AntlrDatatypeRuleToken this_EscapedQualifiedName_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:269:6: ( (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:270:1: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:270:1: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:271:5: this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getCompoundNameAccess().getEscapedQualifiedNameParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName636);
            this_EscapedQualifiedName_0=ruleEscapedQualifiedName();
            _fsp--;


            		current.merge(this_EscapedQualifiedName_0);
                
             
                    currentNode = currentNode.getParent();
                
            kw=(Token)input.LT(1);
            match(input,12,FOLLOW_12_in_ruleCompoundName654); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getCompoundNameAccess().getSolidusKeyword_1(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getCompoundNameAccess().getEscapedQualifiedNameParserRuleCall_2(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName676);
            this_EscapedQualifiedName_2=ruleEscapedQualifiedName();
            _fsp--;


            		current.merge(this_EscapedQualifiedName_2);
                
             
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCompoundName


    // $ANTLR start entryRuleUnitName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:306:1: entryRuleUnitName returns [String current=null] : iv_ruleUnitName= ruleUnitName EOF ;
    public final String entryRuleUnitName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnitName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:306:49: (iv_ruleUnitName= ruleUnitName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:307:2: iv_ruleUnitName= ruleUnitName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnitNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnitName_in_entryRuleUnitName720);
            iv_ruleUnitName=ruleUnitName();
            _fsp--;

             current =iv_ruleUnitName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnitName731); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleUnitName


    // $ANTLR start ruleUnitName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:314:1: ruleUnitName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EscapedQualifiedName_0= ruleEscapedQualifiedName ;
    public final AntlrDatatypeRuleToken ruleUnitName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EscapedQualifiedName_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:320:6: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:322:5: this_EscapedQualifiedName_0= ruleEscapedQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getUnitNameAccess().getEscapedQualifiedNameParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_ruleUnitName777);
            this_EscapedQualifiedName_0=ruleEscapedQualifiedName();
            _fsp--;


            		current.merge(this_EscapedQualifiedName_0);
                
             
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleUnitName


    // $ANTLR start entryRulePartName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:340:1: entryRulePartName returns [String current=null] : iv_rulePartName= rulePartName EOF ;
    public final String entryRulePartName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePartName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:340:49: (iv_rulePartName= rulePartName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:341:2: iv_rulePartName= rulePartName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPartNameRule(), currentNode); 
            pushFollow(FOLLOW_rulePartName_in_entryRulePartName820);
            iv_rulePartName=rulePartName();
            _fsp--;

             current =iv_rulePartName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePartName831); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePartName


    // $ANTLR start rulePartName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:348:1: rulePartName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EscapedQualifiedName_0= ruleEscapedQualifiedName ;
    public final AntlrDatatypeRuleToken rulePartName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EscapedQualifiedName_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:354:6: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:356:5: this_EscapedQualifiedName_0= ruleEscapedQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getPartNameAccess().getEscapedQualifiedNameParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_rulePartName877);
            this_EscapedQualifiedName_0=ruleEscapedQualifiedName();
            _fsp--;


            		current.merge(this_EscapedQualifiedName_0);
                
             
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePartName


    // $ANTLR start entryRuleSeparator
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:374:1: entryRuleSeparator returns [String current=null] : iv_ruleSeparator= ruleSeparator EOF ;
    public final String entryRuleSeparator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSeparator = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:374:50: (iv_ruleSeparator= ruleSeparator EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:375:2: iv_ruleSeparator= ruleSeparator EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSeparatorRule(), currentNode); 
            pushFollow(FOLLOW_ruleSeparator_in_entryRuleSeparator920);
            iv_ruleSeparator=ruleSeparator();
            _fsp--;

             current =iv_ruleSeparator.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSeparator931); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSeparator


    // $ANTLR start ruleSeparator
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:382:1: ruleSeparator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' ) ;
    public final AntlrDatatypeRuleToken ruleSeparator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:388:6: ( (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:389:1: (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:389:1: (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' )
            int alt4=15;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt4=1;
                }
                break;
            case 13:
                {
                alt4=2;
                }
                break;
            case 14:
                {
                alt4=3;
                }
                break;
            case 15:
                {
                alt4=4;
                }
                break;
            case 16:
                {
                alt4=5;
                }
                break;
            case 17:
                {
                alt4=6;
                }
                break;
            case 18:
                {
                alt4=7;
                }
                break;
            case 12:
                {
                alt4=8;
                }
                break;
            case 19:
                {
                alt4=9;
                }
                break;
            case 20:
                {
                alt4=10;
                }
                break;
            case 21:
                {
                alt4=11;
                }
                break;
            case 22:
                {
                alt4=12;
                }
                break;
            case 23:
                {
                alt4=13;
                }
                break;
            case 24:
                {
                alt4=14;
                }
                break;
            case 25:
                {
                alt4=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("389:1: (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' )", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:390:2: kw= '.'
                    {
                    kw=(Token)input.LT(1);
                    match(input,11,FOLLOW_11_in_ruleSeparator969); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getFullStopKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:397:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,13,FOLLOW_13_in_ruleSeparator988); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getHyphenMinusKeyword_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:404:2: kw= '!'
                    {
                    kw=(Token)input.LT(1);
                    match(input,14,FOLLOW_14_in_ruleSeparator1007); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getExclamationMarkKeyword_2(), null); 
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:411:2: kw= '@'
                    {
                    kw=(Token)input.LT(1);
                    match(input,15,FOLLOW_15_in_ruleSeparator1026); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getCommercialAtKeyword_3(), null); 
                        

                    }
                    break;
                case 5 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:418:2: kw= '$'
                    {
                    kw=(Token)input.LT(1);
                    match(input,16,FOLLOW_16_in_ruleSeparator1045); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getDollarSignKeyword_4(), null); 
                        

                    }
                    break;
                case 6 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:425:2: kw= '%'
                    {
                    kw=(Token)input.LT(1);
                    match(input,17,FOLLOW_17_in_ruleSeparator1064); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getPercentSignKeyword_5(), null); 
                        

                    }
                    break;
                case 7 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:432:2: kw= '&'
                    {
                    kw=(Token)input.LT(1);
                    match(input,18,FOLLOW_18_in_ruleSeparator1083); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getAmpersandKeyword_6(), null); 
                        

                    }
                    break;
                case 8 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:439:2: kw= '/'
                    {
                    kw=(Token)input.LT(1);
                    match(input,12,FOLLOW_12_in_ruleSeparator1102); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getSolidusKeyword_7(), null); 
                        

                    }
                    break;
                case 9 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:446:2: kw= '+'
                    {
                    kw=(Token)input.LT(1);
                    match(input,19,FOLLOW_19_in_ruleSeparator1121); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getPlusSignKeyword_8(), null); 
                        

                    }
                    break;
                case 10 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:453:2: kw= '='
                    {
                    kw=(Token)input.LT(1);
                    match(input,20,FOLLOW_20_in_ruleSeparator1140); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getEqualsSignKeyword_9(), null); 
                        

                    }
                    break;
                case 11 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:460:2: kw= '*'
                    {
                    kw=(Token)input.LT(1);
                    match(input,21,FOLLOW_21_in_ruleSeparator1159); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getAsteriskKeyword_10(), null); 
                        

                    }
                    break;
                case 12 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:467:2: kw= '<'
                    {
                    kw=(Token)input.LT(1);
                    match(input,22,FOLLOW_22_in_ruleSeparator1178); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getLessThanSignKeyword_11(), null); 
                        

                    }
                    break;
                case 13 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:474:2: kw= '>'
                    {
                    kw=(Token)input.LT(1);
                    match(input,23,FOLLOW_23_in_ruleSeparator1197); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getGreaterThanSignKeyword_12(), null); 
                        

                    }
                    break;
                case 14 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:481:2: kw= ':'
                    {
                    kw=(Token)input.LT(1);
                    match(input,24,FOLLOW_24_in_ruleSeparator1216); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getColonKeyword_13(), null); 
                        

                    }
                    break;
                case 15 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:488:2: kw= '?'
                    {
                    kw=(Token)input.LT(1);
                    match(input,25,FOLLOW_25_in_ruleSeparator1235); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getQuestionMarkKeyword_14(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSeparator


    // $ANTLR start entryRuleAlfanumSym
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:501:1: entryRuleAlfanumSym returns [String current=null] : iv_ruleAlfanumSym= ruleAlfanumSym EOF ;
    public final String entryRuleAlfanumSym() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAlfanumSym = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:501:51: (iv_ruleAlfanumSym= ruleAlfanumSym EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:502:2: iv_ruleAlfanumSym= ruleAlfanumSym EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAlfanumSymRule(), currentNode); 
            pushFollow(FOLLOW_ruleAlfanumSym_in_entryRuleAlfanumSym1274);
            iv_ruleAlfanumSym=ruleAlfanumSym();
            _fsp--;

             current =iv_ruleAlfanumSym.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAlfanumSym1285); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAlfanumSym


    // $ANTLR start ruleAlfanumSym
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:509:1: ruleAlfanumSym returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* ) ;
    public final AntlrDatatypeRuleToken ruleAlfanumSym() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_INT_1=null;
        Token this_ID_3=null;
        Token this_INT_4=null;
        AntlrDatatypeRuleToken this_Separator_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:515:6: ( ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:516:1: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:516:1: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:516:2: (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )*
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:516:2: (this_ID_0= RULE_ID | this_INT_1= RULE_INT )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_INT) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("516:2: (this_ID_0= RULE_ID | this_INT_1= RULE_INT )", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:516:7: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAlfanumSym1326); 

                    		current.merge(this_ID_0);
                        
                     
                        createLeafNode(grammarAccess.getAlfanumSymAccess().getIDTerminalRuleCall_0_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:524:10: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAlfanumSym1352); 

                    		current.merge(this_INT_1);
                        
                     
                        createLeafNode(grammarAccess.getAlfanumSymAccess().getINTTerminalRuleCall_0_1(), null); 
                        

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:531:2: (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )*
            loop6:
            do {
                int alt6=4;
                switch ( input.LA(1) ) {
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                    {
                    alt6=1;
                    }
                    break;
                case RULE_ID:
                    {
                    alt6=2;
                    }
                    break;
                case RULE_INT:
                    {
                    alt6=3;
                    }
                    break;

                }

                switch (alt6) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:532:5: this_Separator_2= ruleSeparator
            	    {
            	     
            	            currentNode=createCompositeNode(grammarAccess.getAlfanumSymAccess().getSeparatorParserRuleCall_1_0(), currentNode); 
            	        
            	    pushFollow(FOLLOW_ruleSeparator_in_ruleAlfanumSym1381);
            	    this_Separator_2=ruleSeparator();
            	    _fsp--;


            	    		current.merge(this_Separator_2);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:543:10: this_ID_3= RULE_ID
            	    {
            	    this_ID_3=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAlfanumSym1407); 

            	    		current.merge(this_ID_3);
            	        
            	     
            	        createLeafNode(grammarAccess.getAlfanumSymAccess().getIDTerminalRuleCall_1_1(), null); 
            	        

            	    }
            	    break;
            	case 3 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:551:10: this_INT_4= RULE_INT
            	    {
            	    this_INT_4=(Token)input.LT(1);
            	    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAlfanumSym1433); 

            	    		current.merge(this_INT_4);
            	        
            	     
            	        createLeafNode(grammarAccess.getAlfanumSymAccess().getINTTerminalRuleCall_1_2(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAlfanumSym


    // $ANTLR start entryRuleVersion
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:566:1: entryRuleVersion returns [String current=null] : iv_ruleVersion= ruleVersion EOF ;
    public final String entryRuleVersion() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVersion = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:566:48: (iv_ruleVersion= ruleVersion EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:567:2: iv_ruleVersion= ruleVersion EOF
            {
             currentNode = createCompositeNode(grammarAccess.getVersionRule(), currentNode); 
            pushFollow(FOLLOW_ruleVersion_in_entryRuleVersion1479);
            iv_ruleVersion=ruleVersion();
            _fsp--;

             current =iv_ruleVersion.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVersion1490); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleVersion


    // $ANTLR start ruleVersion
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:574:1: ruleVersion returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym ) ;
    public final AntlrDatatypeRuleToken ruleVersion() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        AntlrDatatypeRuleToken this_AlfanumSym_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:580:6: ( (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:581:1: (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:581:1: (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_STRING) ) {
                alt7=1;
            }
            else if ( (LA7_0==RULE_ID||LA7_0==RULE_INT) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("581:1: (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym )", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:581:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleVersion1530); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getVersionAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:590:5: this_AlfanumSym_1= ruleAlfanumSym
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getVersionAccess().getAlfanumSymParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAlfanumSym_in_ruleVersion1563);
                    this_AlfanumSym_1=ruleAlfanumSym();
                    _fsp--;


                    		current.merge(this_AlfanumSym_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleVersion


    // $ANTLR start entryRuleVersionRange
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:608:1: entryRuleVersionRange returns [String current=null] : iv_ruleVersionRange= ruleVersionRange EOF ;
    public final String entryRuleVersionRange() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVersionRange = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:608:53: (iv_ruleVersionRange= ruleVersionRange EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:609:2: iv_ruleVersionRange= ruleVersionRange EOF
            {
             currentNode = createCompositeNode(grammarAccess.getVersionRangeRule(), currentNode); 
            pushFollow(FOLLOW_ruleVersionRange_in_entryRuleVersionRange1607);
            iv_ruleVersionRange=ruleVersionRange();
            _fsp--;

             current =iv_ruleVersionRange.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVersionRange1618); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleVersionRange


    // $ANTLR start ruleVersionRange
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:616:1: ruleVersionRange returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion ) ;
    public final AntlrDatatypeRuleToken ruleVersionRange() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Version_2 = null;

        AntlrDatatypeRuleToken this_Version_4 = null;

        AntlrDatatypeRuleToken this_Version_7 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:622:6: ( ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:623:1: ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:623:1: ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=26 && LA11_0<=27)) ) {
                alt11=1;
            }
            else if ( ((LA11_0>=RULE_ID && LA11_0<=RULE_INT)) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("623:1: ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion )", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:623:2: ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:623:2: ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:623:3: (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:623:3: (kw= '[' | kw= '(' )
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==26) ) {
                        alt8=1;
                    }
                    else if ( (LA8_0==27) ) {
                        alt8=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("623:3: (kw= '[' | kw= '(' )", 8, 0, input);

                        throw nvae;
                    }
                    switch (alt8) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:624:2: kw= '['
                            {
                            kw=(Token)input.LT(1);
                            match(input,26,FOLLOW_26_in_ruleVersionRange1658); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getLeftSquareBracketKeyword_0_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:631:2: kw= '('
                            {
                            kw=(Token)input.LT(1);
                            match(input,27,FOLLOW_27_in_ruleVersionRange1677); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getLeftParenthesisKeyword_0_0_1(), null); 
                                

                            }
                            break;

                    }

                     
                            currentNode=createCompositeNode(grammarAccess.getVersionRangeAccess().getVersionParserRuleCall_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleVersion_in_ruleVersionRange1700);
                    this_Version_2=ruleVersion();
                    _fsp--;


                    		current.merge(this_Version_2);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:647:1: (kw= ',' this_Version_4= ruleVersion )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==28) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:648:2: kw= ',' this_Version_4= ruleVersion
                            {
                            kw=(Token)input.LT(1);
                            match(input,28,FOLLOW_28_in_ruleVersionRange1719); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getCommaKeyword_0_2_0(), null); 
                                
                             
                                    currentNode=createCompositeNode(grammarAccess.getVersionRangeAccess().getVersionParserRuleCall_0_2_1(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleVersion_in_ruleVersionRange1741);
                            this_Version_4=ruleVersion();
                            _fsp--;


                            		current.merge(this_Version_4);
                                
                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:664:3: (kw= ']' | kw= ')' )
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==29) ) {
                        alt10=1;
                    }
                    else if ( (LA10_0==30) ) {
                        alt10=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("664:3: (kw= ']' | kw= ')' )", 10, 0, input);

                        throw nvae;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:665:2: kw= ']'
                            {
                            kw=(Token)input.LT(1);
                            match(input,29,FOLLOW_29_in_ruleVersionRange1762); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getRightSquareBracketKeyword_0_3_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:672:2: kw= ')'
                            {
                            kw=(Token)input.LT(1);
                            match(input,30,FOLLOW_30_in_ruleVersionRange1781); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getRightParenthesisKeyword_0_3_1(), null); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:679:5: this_Version_7= ruleVersion
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getVersionRangeAccess().getVersionParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleVersion_in_ruleVersionRange1811);
                    this_Version_7=ruleVersion();
                    _fsp--;


                    		current.merge(this_Version_7);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleVersionRange


    // $ANTLR start entryRuleImport
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:697:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:697:48: (iv_ruleImport= ruleImport EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:698:2: iv_ruleImport= ruleImport EOF
            {
             currentNode = createCompositeNode(grammarAccess.getImportRule(), currentNode); 
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport1854);
            iv_ruleImport=ruleImport();
            _fsp--;

             current =iv_ruleImport; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport1864); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleImport


    // $ANTLR start ruleImport
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:705:1: ruleImport returns [EObject current=null] : ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_importClass_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:710:6: ( ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:711:1: ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:711:1: ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:711:2: 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';'
            {
            match(input,31,FOLLOW_31_in_ruleImport1898); 

                    createLeafNode(grammarAccess.getImportAccess().getImportKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:715:1: (lv_importClass_1= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:718:6: lv_importClass_1= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getImportAccess().getImportClassQualifiedNameParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleImport1932);
            lv_importClass_1=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getImportRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "importClass", lv_importClass_1, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:736:2: ( '.*' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==32) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:736:3: '.*'
                    {
                    match(input,32,FOLLOW_32_in_ruleImport1946); 

                            createLeafNode(grammarAccess.getImportAccess().getFullStopAsteriskKeyword_2(), null); 
                        

                    }
                    break;

            }

            match(input,33,FOLLOW_33_in_ruleImport1957); 

                    createLeafNode(grammarAccess.getImportAccess().getSemicolonKeyword_3(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleImport


    // $ANTLR start entryRuleUnit
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:751:1: entryRuleUnit returns [EObject current=null] : iv_ruleUnit= ruleUnit EOF ;
    public final EObject entryRuleUnit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnit = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:751:46: (iv_ruleUnit= ruleUnit EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:752:2: iv_ruleUnit= ruleUnit EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnitRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnit_in_entryRuleUnit1990);
            iv_ruleUnit=ruleUnit();
            _fsp--;

             current =iv_ruleUnit; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnit2000); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleUnit


    // $ANTLR start ruleUnit
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:759:1: ruleUnit returns [EObject current=null] : ( (lv_synchronized_0= 'synchronized' )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' ) | (lv_setProperties_47= ruleStringProperty2 ) | ( 'advice' (lv_advice_49= ruleNamedAdvice ) ) | ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' ) | ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) ) | (lv_parts_56= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' ) )* '}' ) ;
    public final EObject ruleUnit() throws RecognitionException {
        EObject current = null;

        Token lv_synchronized_0=null;
        AntlrDatatypeRuleToken lv_name_2 = null;

        AntlrDatatypeRuleToken lv_version_4 = null;

        AntlrDatatypeRuleToken lv_implements_6 = null;

        AntlrDatatypeRuleToken lv_implements_8 = null;

        EObject lv_providedCapability_12 = null;

        EObject lv_providedCapability_16 = null;

        EObject lv_requiredCapabilities_20 = null;

        EObject lv_requiredCapabilities_24 = null;

        EObject lv_metaRequiredCapabilities_29 = null;

        EObject lv_metaRequiredCapabilities_34 = null;

        AntlrDatatypeRuleToken lv_unsetProperties_39 = null;

        EObject lv_setProperties_41 = null;

        AntlrDatatypeRuleToken lv_unsetProperties_45 = null;

        EObject lv_setProperties_47 = null;

        EObject lv_advice_49 = null;

        EObject lv_synchronize_52 = null;

        EObject lv_synchronize_55 = null;

        EObject lv_parts_56 = null;

        EObject lv_repositoryConfig_59 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:766:6: ( ( (lv_synchronized_0= 'synchronized' )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' ) | (lv_setProperties_47= ruleStringProperty2 ) | ( 'advice' (lv_advice_49= ruleNamedAdvice ) ) | ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' ) | ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) ) | (lv_parts_56= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' ) )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:767:1: ( (lv_synchronized_0= 'synchronized' )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' ) | (lv_setProperties_47= ruleStringProperty2 ) | ( 'advice' (lv_advice_49= ruleNamedAdvice ) ) | ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' ) | ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) ) | (lv_parts_56= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' ) )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:767:1: ( (lv_synchronized_0= 'synchronized' )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' ) | (lv_setProperties_47= ruleStringProperty2 ) | ( 'advice' (lv_advice_49= ruleNamedAdvice ) ) | ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' ) | ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) ) | (lv_parts_56= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' ) )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:767:2: (lv_synchronized_0= 'synchronized' )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' ) | (lv_setProperties_47= ruleStringProperty2 ) | ( 'advice' (lv_advice_49= ruleNamedAdvice ) ) | ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' ) | ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) ) | (lv_parts_56= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' ) )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:767:2: (lv_synchronized_0= 'synchronized' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==34) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:769:6: lv_synchronized_0= 'synchronized'
                    {
                    lv_synchronized_0=(Token)input.LT(1);
                    match(input,34,FOLLOW_34_in_ruleUnit2046); 

                            createLeafNode(grammarAccess.getUnitAccess().getSynchronizedSynchronizedKeyword_0_0(), "synchronized"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "synchronized", true, "synchronized", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            match(input,35,FOLLOW_35_in_ruleUnit2069); 

                    createLeafNode(grammarAccess.getUnitAccess().getUnitKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:792:1: (lv_name_2= ruleUnitName )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=RULE_ID && LA14_0<=RULE_STRING)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:795:6: lv_name_2= ruleUnitName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getNameUnitNameParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUnitName_in_ruleUnit2103);
                    lv_name_2=ruleUnitName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_2, "UnitName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:813:3: ( 'version' (lv_version_4= ruleVersion ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==36) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:813:4: 'version' (lv_version_4= ruleVersion )
                    {
                    match(input,36,FOLLOW_36_in_ruleUnit2118); 

                            createLeafNode(grammarAccess.getUnitAccess().getVersionKeyword_3_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:817:1: (lv_version_4= ruleVersion )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:820:6: lv_version_4= ruleVersion
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getVersionVersionParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVersion_in_ruleUnit2152);
                    lv_version_4=ruleVersion();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "version", lv_version_4, "Version", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:838:4: ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==37) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:838:5: 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )*
                    {
                    match(input,37,FOLLOW_37_in_ruleUnit2168); 

                            createLeafNode(grammarAccess.getUnitAccess().getImplementsKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:842:1: (lv_implements_6= ruleInterfaceName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:845:6: lv_implements_6= ruleInterfaceName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getImplementsInterfaceNameParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInterfaceName_in_ruleUnit2202);
                    lv_implements_6=ruleInterfaceName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "implements", lv_implements_6, "InterfaceName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:863:2: ( ',' (lv_implements_8= ruleInterfaceName ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==28) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:863:3: ',' (lv_implements_8= ruleInterfaceName )
                    	    {
                    	    match(input,28,FOLLOW_28_in_ruleUnit2216); 

                    	            createLeafNode(grammarAccess.getUnitAccess().getCommaKeyword_4_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:867:1: (lv_implements_8= ruleInterfaceName )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:870:6: lv_implements_8= ruleInterfaceName
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getImplementsInterfaceNameParserRuleCall_4_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleInterfaceName_in_ruleUnit2250);
                    	    lv_implements_8=ruleInterfaceName();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "implements", lv_implements_8, "InterfaceName", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleUnit2267); 

                    createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_5(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:892:1: ( ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' ) | (lv_setProperties_47= ruleStringProperty2 ) | ( 'advice' (lv_advice_49= ruleNamedAdvice ) ) | ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' ) | ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) ) | (lv_parts_56= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' ) )*
            loop24:
            do {
                int alt24=15;
                switch ( input.LA(1) ) {
                case 39:
                    {
                    int LA24_2 = input.LA(2);

                    if ( (LA24_2==38) ) {
                        alt24=1;
                    }
                    else if ( (LA24_2==RULE_ID||LA24_2==49) ) {
                        alt24=2;
                    }


                    }
                    break;
                case 41:
                    {
                    int LA24_3 = input.LA(2);

                    if ( (LA24_3==38) ) {
                        alt24=3;
                    }
                    else if ( (LA24_3==RULE_ID||LA24_3==49) ) {
                        alt24=4;
                    }


                    }
                    break;
                case 42:
                    {
                    int LA24_4 = input.LA(2);

                    if ( (LA24_4==41) ) {
                        int LA24_16 = input.LA(3);

                        if ( (LA24_16==38) ) {
                            alt24=5;
                        }
                        else if ( (LA24_16==RULE_ID||LA24_16==49) ) {
                            alt24=6;
                        }


                    }


                    }
                    break;
                case 43:
                    {
                    alt24=7;
                    }
                    break;
                case 44:
                    {
                    alt24=8;
                    }
                    break;
                case 45:
                case 50:
                    {
                    alt24=9;
                    }
                    break;
                case 46:
                    {
                    alt24=10;
                    }
                    break;
                case 47:
                    {
                    int LA24_9 = input.LA(2);

                    if ( (LA24_9==38) ) {
                        alt24=11;
                    }
                    else if ( ((LA24_9>=RULE_ID && LA24_9<=RULE_STRING)) ) {
                        alt24=12;
                    }


                    }
                    break;
                case 34:
                case 52:
                case 53:
                case 56:
                case 75:
                case 76:
                    {
                    alt24=13;
                    }
                    break;
                case 48:
                    {
                    alt24=14;
                    }
                    break;

                }

                switch (alt24) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:892:2: ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:892:2: ( 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:892:3: 'provides' '{' ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+ '}'
            	    {
            	    match(input,39,FOLLOW_39_in_ruleUnit2278); 

            	            createLeafNode(grammarAccess.getUnitAccess().getProvidesKeyword_6_0_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2287); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_6_0_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:900:1: ( (lv_providedCapability_12= ruleProvidedCapability ) ';' )+
            	    int cnt18=0;
            	    loop18:
            	    do {
            	        int alt18=2;
            	        int LA18_0 = input.LA(1);

            	        if ( (LA18_0==RULE_ID||LA18_0==49) ) {
            	            alt18=1;
            	        }


            	        switch (alt18) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:900:2: (lv_providedCapability_12= ruleProvidedCapability ) ';'
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:900:2: (lv_providedCapability_12= ruleProvidedCapability )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:903:6: lv_providedCapability_12= ruleProvidedCapability
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getProvidedCapabilityProvidedCapabilityParserRuleCall_6_0_2_0_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleUnit2322);
            	    	    lv_providedCapability_12=ruleProvidedCapability();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "providedCapability", lv_providedCapability_12, "ProvidedCapability", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,33,FOLLOW_33_in_ruleUnit2335); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_0_2_1(), null); 
            	    	        

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt18 >= 1 ) break loop18;
            	                EarlyExitException eee =
            	                    new EarlyExitException(18, input);
            	                throw eee;
            	        }
            	        cnt18++;
            	    } while (true);

            	    match(input,40,FOLLOW_40_in_ruleUnit2346); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_6_0_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:930:6: ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:930:6: ( 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:930:7: 'provides' (lv_providedCapability_16= ruleProvidedCapability ) ';'
            	    {
            	    match(input,39,FOLLOW_39_in_ruleUnit2363); 

            	            createLeafNode(grammarAccess.getUnitAccess().getProvidesKeyword_6_1_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:934:1: (lv_providedCapability_16= ruleProvidedCapability )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:937:6: lv_providedCapability_16= ruleProvidedCapability
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getProvidedCapabilityProvidedCapabilityParserRuleCall_6_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleUnit2397);
            	    lv_providedCapability_16=ruleProvidedCapability();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "providedCapability", lv_providedCapability_16, "ProvidedCapability", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,33,FOLLOW_33_in_ruleUnit2410); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_1_2(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 3 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:960:6: ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:960:6: ( 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:960:7: 'requires' '{' ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+ '}'
            	    {
            	    match(input,41,FOLLOW_41_in_ruleUnit2427); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_6_2_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2436); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_6_2_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:968:1: ( (lv_requiredCapabilities_20= ruleRequiredCapability ) ';' )+
            	    int cnt19=0;
            	    loop19:
            	    do {
            	        int alt19=2;
            	        int LA19_0 = input.LA(1);

            	        if ( (LA19_0==RULE_ID||LA19_0==49) ) {
            	            alt19=1;
            	        }


            	        switch (alt19) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:968:2: (lv_requiredCapabilities_20= ruleRequiredCapability ) ';'
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:968:2: (lv_requiredCapabilities_20= ruleRequiredCapability )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:971:6: lv_requiredCapabilities_20= ruleRequiredCapability
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getRequiredCapabilitiesRequiredCapabilityParserRuleCall_6_2_2_0_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2471);
            	    	    lv_requiredCapabilities_20=ruleRequiredCapability();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "requiredCapabilities", lv_requiredCapabilities_20, "RequiredCapability", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,33,FOLLOW_33_in_ruleUnit2484); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_2_2_1(), null); 
            	    	        

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt19 >= 1 ) break loop19;
            	                EarlyExitException eee =
            	                    new EarlyExitException(19, input);
            	                throw eee;
            	        }
            	        cnt19++;
            	    } while (true);

            	    match(input,40,FOLLOW_40_in_ruleUnit2495); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_6_2_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:998:6: ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:998:6: ( 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:998:7: 'requires' (lv_requiredCapabilities_24= ruleRequiredCapability ) ';'
            	    {
            	    match(input,41,FOLLOW_41_in_ruleUnit2512); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_6_3_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1002:1: (lv_requiredCapabilities_24= ruleRequiredCapability )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1005:6: lv_requiredCapabilities_24= ruleRequiredCapability
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getRequiredCapabilitiesRequiredCapabilityParserRuleCall_6_3_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2546);
            	    lv_requiredCapabilities_24=ruleRequiredCapability();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "requiredCapabilities", lv_requiredCapabilities_24, "RequiredCapability", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,33,FOLLOW_33_in_ruleUnit2559); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_3_2(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 5 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1028:6: ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1028:6: ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1028:7: 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+ '}'
            	    {
            	    match(input,42,FOLLOW_42_in_ruleUnit2576); 

            	            createLeafNode(grammarAccess.getUnitAccess().getMetaKeyword_6_4_0(), null); 
            	        
            	    match(input,41,FOLLOW_41_in_ruleUnit2585); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_6_4_1(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2594); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_6_4_2(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1040:1: ( (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';' )+
            	    int cnt20=0;
            	    loop20:
            	    do {
            	        int alt20=2;
            	        int LA20_0 = input.LA(1);

            	        if ( (LA20_0==RULE_ID||LA20_0==49) ) {
            	            alt20=1;
            	        }


            	        switch (alt20) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1040:2: (lv_metaRequiredCapabilities_29= ruleRequiredCapability ) ';'
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1040:2: (lv_metaRequiredCapabilities_29= ruleRequiredCapability )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1043:6: lv_metaRequiredCapabilities_29= ruleRequiredCapability
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getMetaRequiredCapabilitiesRequiredCapabilityParserRuleCall_6_4_3_0_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2629);
            	    	    lv_metaRequiredCapabilities_29=ruleRequiredCapability();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "metaRequiredCapabilities", lv_metaRequiredCapabilities_29, "RequiredCapability", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,33,FOLLOW_33_in_ruleUnit2642); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_4_3_1(), null); 
            	    	        

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt20 >= 1 ) break loop20;
            	                EarlyExitException eee =
            	                    new EarlyExitException(20, input);
            	                throw eee;
            	        }
            	        cnt20++;
            	    } while (true);

            	    match(input,40,FOLLOW_40_in_ruleUnit2653); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_6_4_4(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 6 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1070:6: ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1070:6: ( 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1070:7: 'meta' 'requires' (lv_metaRequiredCapabilities_34= ruleRequiredCapability ) ';'
            	    {
            	    match(input,42,FOLLOW_42_in_ruleUnit2670); 

            	            createLeafNode(grammarAccess.getUnitAccess().getMetaKeyword_6_5_0(), null); 
            	        
            	    match(input,41,FOLLOW_41_in_ruleUnit2679); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_6_5_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1078:1: (lv_metaRequiredCapabilities_34= ruleRequiredCapability )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1081:6: lv_metaRequiredCapabilities_34= ruleRequiredCapability
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getMetaRequiredCapabilitiesRequiredCapabilityParserRuleCall_6_5_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2713);
            	    lv_metaRequiredCapabilities_34=ruleRequiredCapability();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "metaRequiredCapabilities", lv_metaRequiredCapabilities_34, "RequiredCapability", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,33,FOLLOW_33_in_ruleUnit2726); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_5_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 7 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1104:6: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1104:6: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1104:7: 'properties' '{' ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+ '}'
            	    {
            	    match(input,43,FOLLOW_43_in_ruleUnit2743); 

            	            createLeafNode(grammarAccess.getUnitAccess().getPropertiesKeyword_6_6_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2752); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_6_6_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1112:1: ( ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' ) | (lv_setProperties_41= ruleStringProperty ) )+
            	    int cnt21=0;
            	    loop21:
            	    do {
            	        int alt21=3;
            	        int LA21_0 = input.LA(1);

            	        if ( (LA21_0==44) ) {
            	            alt21=1;
            	        }
            	        else if ( (LA21_0==RULE_ID||LA21_0==50) ) {
            	            alt21=2;
            	        }


            	        switch (alt21) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1112:2: ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' )
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1112:2: ( 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';' )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1112:3: 'unset' (lv_unsetProperties_39= ruleQualifiedName ) ';'
            	    	    {
            	    	    match(input,44,FOLLOW_44_in_ruleUnit2763); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getUnsetKeyword_6_6_2_0_0(), null); 
            	    	        
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1116:1: (lv_unsetProperties_39= ruleQualifiedName )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1119:6: lv_unsetProperties_39= ruleQualifiedName
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getUnsetPropertiesQualifiedNameParserRuleCall_6_6_2_0_1_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleQualifiedName_in_ruleUnit2797);
            	    	    lv_unsetProperties_39=ruleQualifiedName();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "unsetProperties", lv_unsetProperties_39, "QualifiedName", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,33,FOLLOW_33_in_ruleUnit2810); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_6_2_0_2(), null); 
            	    	        

            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1142:6: (lv_setProperties_41= ruleStringProperty )
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1142:6: (lv_setProperties_41= ruleStringProperty )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1145:6: lv_setProperties_41= ruleStringProperty
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getSetPropertiesStringPropertyParserRuleCall_6_6_2_1_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleStringProperty_in_ruleUnit2851);
            	    	    lv_setProperties_41=ruleStringProperty();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "setProperties", lv_setProperties_41, "StringProperty", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt21 >= 1 ) break loop21;
            	                EarlyExitException eee =
            	                    new EarlyExitException(21, input);
            	                throw eee;
            	        }
            	        cnt21++;
            	    } while (true);

            	    match(input,40,FOLLOW_40_in_ruleUnit2866); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_6_6_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 8 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1168:6: ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1168:6: ( 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1168:7: 'unset' 'property' (lv_unsetProperties_45= ruleQualifiedName ) ';'
            	    {
            	    match(input,44,FOLLOW_44_in_ruleUnit2883); 

            	            createLeafNode(grammarAccess.getUnitAccess().getUnsetKeyword_6_7_0(), null); 
            	        
            	    match(input,45,FOLLOW_45_in_ruleUnit2892); 

            	            createLeafNode(grammarAccess.getUnitAccess().getPropertyKeyword_6_7_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1176:1: (lv_unsetProperties_45= ruleQualifiedName )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1179:6: lv_unsetProperties_45= ruleQualifiedName
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getUnsetPropertiesQualifiedNameParserRuleCall_6_7_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleQualifiedName_in_ruleUnit2926);
            	    lv_unsetProperties_45=ruleQualifiedName();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "unsetProperties", lv_unsetProperties_45, "QualifiedName", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,33,FOLLOW_33_in_ruleUnit2939); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_6_7_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 9 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1202:6: (lv_setProperties_47= ruleStringProperty2 )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1202:6: (lv_setProperties_47= ruleStringProperty2 )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1205:6: lv_setProperties_47= ruleStringProperty2
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getSetPropertiesStringProperty2ParserRuleCall_6_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleStringProperty2_in_ruleUnit2980);
            	    lv_setProperties_47=ruleStringProperty2();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "setProperties", lv_setProperties_47, "StringProperty2", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 10 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1224:6: ( 'advice' (lv_advice_49= ruleNamedAdvice ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1224:6: ( 'advice' (lv_advice_49= ruleNamedAdvice ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1224:7: 'advice' (lv_advice_49= ruleNamedAdvice )
            	    {
            	    match(input,46,FOLLOW_46_in_ruleUnit3000); 

            	            createLeafNode(grammarAccess.getUnitAccess().getAdviceKeyword_6_9_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1228:1: (lv_advice_49= ruleNamedAdvice )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1231:6: lv_advice_49= ruleNamedAdvice
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getAdviceNamedAdviceParserRuleCall_6_9_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleNamedAdvice_in_ruleUnit3034);
            	    lv_advice_49=ruleNamedAdvice();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "advice", lv_advice_49, "NamedAdvice", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 11 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1250:6: ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1250:6: ( 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1250:7: 'synchronize' '{' (lv_synchronize_52= ruleSynchronization )+ '}'
            	    {
            	    match(input,47,FOLLOW_47_in_ruleUnit3055); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSynchronizeKeyword_6_10_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit3064); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_6_10_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1258:1: (lv_synchronize_52= ruleSynchronization )+
            	    int cnt22=0;
            	    loop22:
            	    do {
            	        int alt22=2;
            	        int LA22_0 = input.LA(1);

            	        if ( ((LA22_0>=RULE_ID && LA22_0<=RULE_STRING)) ) {
            	            alt22=1;
            	        }


            	        switch (alt22) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1261:6: lv_synchronize_52= ruleSynchronization
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getSynchronizeSynchronizationParserRuleCall_6_10_2_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleSynchronization_in_ruleUnit3098);
            	    	    lv_synchronize_52=ruleSynchronization();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "synchronize", lv_synchronize_52, "Synchronization", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt22 >= 1 ) break loop22;
            	                EarlyExitException eee =
            	                    new EarlyExitException(22, input);
            	                throw eee;
            	        }
            	        cnt22++;
            	    } while (true);

            	    match(input,40,FOLLOW_40_in_ruleUnit3112); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_6_10_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 12 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1284:6: ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1284:6: ( 'synchronize' (lv_synchronize_55= ruleSynchronization ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1284:7: 'synchronize' (lv_synchronize_55= ruleSynchronization )
            	    {
            	    match(input,47,FOLLOW_47_in_ruleUnit3129); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSynchronizeKeyword_6_11_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1288:1: (lv_synchronize_55= ruleSynchronization )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1291:6: lv_synchronize_55= ruleSynchronization
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getSynchronizeSynchronizationParserRuleCall_6_11_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleSynchronization_in_ruleUnit3163);
            	    lv_synchronize_55=ruleSynchronization();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "synchronize", lv_synchronize_55, "Synchronization", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 13 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1310:6: (lv_parts_56= ruleBuildPart )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1310:6: (lv_parts_56= ruleBuildPart )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1313:6: lv_parts_56= ruleBuildPart
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getPartsBuildPartParserRuleCall_6_12_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleBuildPart_in_ruleUnit3208);
            	    lv_parts_56=ruleBuildPart();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "parts", lv_parts_56, "BuildPart", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 14 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1332:6: ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1332:6: ( 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1332:7: 'repositories' '{' (lv_repositoryConfig_59= ruleRepositoryConfiguration )* '}'
            	    {
            	    match(input,48,FOLLOW_48_in_ruleUnit3228); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRepositoriesKeyword_6_13_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit3237); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_6_13_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1340:1: (lv_repositoryConfig_59= ruleRepositoryConfiguration )*
            	    loop23:
            	    do {
            	        int alt23=2;
            	        int LA23_0 = input.LA(1);

            	        if ( (LA23_0==RULE_STRING||LA23_0==59) ) {
            	            alt23=1;
            	        }


            	        switch (alt23) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1343:6: lv_repositoryConfig_59= ruleRepositoryConfiguration
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getRepositoryConfigRepositoryConfigurationParserRuleCall_6_13_2_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleRepositoryConfiguration_in_ruleUnit3271);
            	    	    lv_repositoryConfig_59=ruleRepositoryConfiguration();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "repositoryConfig", lv_repositoryConfig_59, "RepositoryConfiguration", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop23;
            	        }
            	    } while (true);

            	    match(input,40,FOLLOW_40_in_ruleUnit3285); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_6_13_3(), null); 
            	        

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleUnit3297); 

                    createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_7(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
            		myHiddenTokenState.restore();
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleUnit


    // $ANTLR start entryRuleProvidedCapability
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1376:1: entryRuleProvidedCapability returns [EObject current=null] : iv_ruleProvidedCapability= ruleProvidedCapability EOF ;
    public final EObject entryRuleProvidedCapability() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProvidedCapability = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1376:60: (iv_ruleProvidedCapability= ruleProvidedCapability EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1377:2: iv_ruleProvidedCapability= ruleProvidedCapability EOF
            {
             currentNode = createCompositeNode(grammarAccess.getProvidedCapabilityRule(), currentNode); 
            pushFollow(FOLLOW_ruleProvidedCapability_in_entryRuleProvidedCapability3330);
            iv_ruleProvidedCapability=ruleProvidedCapability();
            _fsp--;

             current =iv_ruleProvidedCapability; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleProvidedCapability3340); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleProvidedCapability


    // $ANTLR start ruleProvidedCapability
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1384:1: ruleProvidedCapability returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? ) ;
    public final EObject ruleProvidedCapability() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_1 = null;

        AntlrDatatypeRuleToken lv_interface_2 = null;

        AntlrDatatypeRuleToken lv_name_4 = null;

        AntlrDatatypeRuleToken lv_version_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1389:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1390:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1390:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1390:2: ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1390:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==49) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1390:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,49,FOLLOW_49_in_ruleProvidedCapability3375); 

                            createLeafNode(grammarAccess.getProvidedCapabilityAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1394:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1397:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_ruleProvidedCapability3409);
                    lv_filter_1=ruleFilter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getProvidedCapabilityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "filter", lv_filter_1, "Filter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1415:4: (lv_interface_2= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1418:6: lv_interface_2= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getInterfaceQualifiedNameParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3449);
            lv_interface_2=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getProvidedCapabilityRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "interface", lv_interface_2, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,12,FOLLOW_12_in_ruleProvidedCapability3462); 

                    createLeafNode(grammarAccess.getProvidedCapabilityAccess().getSolidusKeyword_2(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1440:1: (lv_name_4= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1443:6: lv_name_4= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getNameQualifiedNameParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3496);
            lv_name_4=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getProvidedCapabilityRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_4, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1461:2: ( '/' (lv_version_6= ruleVersion ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==12) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1461:3: '/' (lv_version_6= ruleVersion )
                    {
                    match(input,12,FOLLOW_12_in_ruleProvidedCapability3510); 

                            createLeafNode(grammarAccess.getProvidedCapabilityAccess().getSolidusKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1465:1: (lv_version_6= ruleVersion )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1468:6: lv_version_6= ruleVersion
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getVersionVersionParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVersion_in_ruleProvidedCapability3544);
                    lv_version_6=ruleVersion();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getProvidedCapabilityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "version", lv_version_6, "Version", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleProvidedCapability


    // $ANTLR start entryRuleRequiredCapability
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1493:1: entryRuleRequiredCapability returns [EObject current=null] : iv_ruleRequiredCapability= ruleRequiredCapability EOF ;
    public final EObject entryRuleRequiredCapability() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequiredCapability = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1493:60: (iv_ruleRequiredCapability= ruleRequiredCapability EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1494:2: iv_ruleRequiredCapability= ruleRequiredCapability EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRequiredCapabilityRule(), currentNode); 
            pushFollow(FOLLOW_ruleRequiredCapability_in_entryRuleRequiredCapability3583);
            iv_ruleRequiredCapability=ruleRequiredCapability();
            _fsp--;

             current =iv_ruleRequiredCapability; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRequiredCapability3593); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleRequiredCapability


    // $ANTLR start ruleRequiredCapability
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1501:1: ruleRequiredCapability returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? ) ;
    public final EObject ruleRequiredCapability() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_1 = null;

        AntlrDatatypeRuleToken lv_interface_2 = null;

        AntlrDatatypeRuleToken lv_name_4 = null;

        AntlrDatatypeRuleToken lv_range_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1506:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1507:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1507:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1507:2: ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1507:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==49) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1507:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,49,FOLLOW_49_in_ruleRequiredCapability3628); 

                            createLeafNode(grammarAccess.getRequiredCapabilityAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1511:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1514:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_ruleRequiredCapability3662);
                    lv_filter_1=ruleFilter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRequiredCapabilityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "filter", lv_filter_1, "Filter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1532:4: (lv_interface_2= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1535:6: lv_interface_2= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getInterfaceQualifiedNameParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3702);
            lv_interface_2=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getRequiredCapabilityRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "interface", lv_interface_2, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,12,FOLLOW_12_in_ruleRequiredCapability3715); 

                    createLeafNode(grammarAccess.getRequiredCapabilityAccess().getSolidusKeyword_2(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1557:1: (lv_name_4= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1560:6: lv_name_4= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getNameQualifiedNameParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3749);
            lv_name_4=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getRequiredCapabilityRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_4, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1578:2: ( '/' (lv_range_6= ruleVersionRange ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==12) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1578:3: '/' (lv_range_6= ruleVersionRange )
                    {
                    match(input,12,FOLLOW_12_in_ruleRequiredCapability3763); 

                            createLeafNode(grammarAccess.getRequiredCapabilityAccess().getSolidusKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1582:1: (lv_range_6= ruleVersionRange )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1585:6: lv_range_6= ruleVersionRange
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getRangeVersionRangeParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVersionRange_in_ruleRequiredCapability3797);
                    lv_range_6=ruleVersionRange();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRequiredCapabilityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "range", lv_range_6, "VersionRange", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleRequiredCapability


    // $ANTLR start entryRuleStringProperty
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1610:1: entryRuleStringProperty returns [EObject current=null] : iv_ruleStringProperty= ruleStringProperty EOF ;
    public final EObject entryRuleStringProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringProperty = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1610:56: (iv_ruleStringProperty= ruleStringProperty EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1611:2: iv_ruleStringProperty= ruleStringProperty EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStringPropertyRule(), currentNode); 
            pushFollow(FOLLOW_ruleStringProperty_in_entryRuleStringProperty3836);
            iv_ruleStringProperty=ruleStringProperty();
            _fsp--;

             current =iv_ruleStringProperty; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringProperty3846); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleStringProperty


    // $ANTLR start ruleStringProperty
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1618:1: ruleStringProperty returns [EObject current=null] : ( ( (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';' ) | ( ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';' ) ) ;
    public final EObject ruleStringProperty() throws RecognitionException {
        EObject current = null;

        Token lv_immutable_0=null;
        AntlrDatatypeRuleToken lv_key_1 = null;

        EObject lv_value_3 = null;

        AntlrDatatypeRuleToken lv_key_5 = null;

        EObject lv_value_7 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1623:6: ( ( ( (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';' ) | ( ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1624:1: ( ( (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';' ) | ( ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1624:1: ( ( (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';' ) | ( ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';' ) )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==50) ) {
                alt30=1;
            }
            else if ( (LA30_0==RULE_ID) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1624:1: ( ( (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';' ) | ( ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';' ) )", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1624:2: ( (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1624:2: ( (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1624:3: (lv_immutable_0= 'immutable' ) (lv_key_1= ruleQualifiedName ) '=' (lv_value_3= rulePropertyExpression ) ';'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1624:3: (lv_immutable_0= 'immutable' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1626:6: lv_immutable_0= 'immutable'
                    {
                    lv_immutable_0=(Token)input.LT(1);
                    match(input,50,FOLLOW_50_in_ruleStringProperty3893); 

                            createLeafNode(grammarAccess.getStringPropertyAccess().getImmutableImmutableKeyword_0_0_0(), "immutable"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringPropertyRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "immutable", true, "immutable", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1645:2: (lv_key_1= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1648:6: lv_key_1= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getStringPropertyAccess().getKeyQualifiedNameParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleStringProperty3940);
                    lv_key_1=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringPropertyRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "key", lv_key_1, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,20,FOLLOW_20_in_ruleStringProperty3953); 

                            createLeafNode(grammarAccess.getStringPropertyAccess().getEqualsSignKeyword_0_2(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1670:1: (lv_value_3= rulePropertyExpression )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1673:6: lv_value_3= rulePropertyExpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getStringPropertyAccess().getValuePropertyExpressionParserRuleCall_0_3_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePropertyExpression_in_ruleStringProperty3987);
                    lv_value_3=rulePropertyExpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringPropertyRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "value", lv_value_3, "PropertyExpression", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,33,FOLLOW_33_in_ruleStringProperty4000); 

                            createLeafNode(grammarAccess.getStringPropertyAccess().getSemicolonKeyword_0_4(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1696:6: ( ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1696:6: ( ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1696:7: ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? ) ';'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1696:7: ( (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1696:8: (lv_key_5= ruleQualifiedName ) ( '=' (lv_value_7= rulePropertyExpression ) )?
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1696:8: (lv_key_5= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1699:6: lv_key_5= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getStringPropertyAccess().getKeyQualifiedNameParserRuleCall_1_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleStringProperty4043);
                    lv_key_5=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringPropertyRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "key", lv_key_5, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1717:2: ( '=' (lv_value_7= rulePropertyExpression ) )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==20) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1717:3: '=' (lv_value_7= rulePropertyExpression )
                            {
                            match(input,20,FOLLOW_20_in_ruleStringProperty4057); 

                                    createLeafNode(grammarAccess.getStringPropertyAccess().getEqualsSignKeyword_1_0_1_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1721:1: (lv_value_7= rulePropertyExpression )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1724:6: lv_value_7= rulePropertyExpression
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getStringPropertyAccess().getValuePropertyExpressionParserRuleCall_1_0_1_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulePropertyExpression_in_ruleStringProperty4091);
                            lv_value_7=rulePropertyExpression();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getStringPropertyRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "value", lv_value_7, "PropertyExpression", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }


                            }
                            break;

                    }


                    }

                    match(input,33,FOLLOW_33_in_ruleStringProperty4107); 

                            createLeafNode(grammarAccess.getStringPropertyAccess().getSemicolonKeyword_1_1(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleStringProperty


    // $ANTLR start entryRuleStringProperty2
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1753:1: entryRuleStringProperty2 returns [EObject current=null] : iv_ruleStringProperty2= ruleStringProperty2 EOF ;
    public final EObject entryRuleStringProperty2() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringProperty2 = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1753:57: (iv_ruleStringProperty2= ruleStringProperty2 EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1754:2: iv_ruleStringProperty2= ruleStringProperty2 EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStringProperty2Rule(), currentNode); 
            pushFollow(FOLLOW_ruleStringProperty2_in_entryRuleStringProperty24141);
            iv_ruleStringProperty2=ruleStringProperty2();
            _fsp--;

             current =iv_ruleStringProperty2; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringProperty24151); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleStringProperty2


    // $ANTLR start ruleStringProperty2
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1761:1: ruleStringProperty2 returns [EObject current=null] : ( ( (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' ) | ( ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';' ) ) ;
    public final EObject ruleStringProperty2() throws RecognitionException {
        EObject current = null;

        Token lv_immutable_0=null;
        AntlrDatatypeRuleToken lv_key_2 = null;

        EObject lv_value_4 = null;

        AntlrDatatypeRuleToken lv_key_7 = null;

        EObject lv_value_9 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1766:6: ( ( ( (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' ) | ( ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1767:1: ( ( (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' ) | ( ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1767:1: ( ( (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' ) | ( ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';' ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==50) ) {
                alt32=1;
            }
            else if ( (LA32_0==45) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1767:1: ( ( (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' ) | ( ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';' ) )", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1767:2: ( (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1767:2: ( (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1767:3: (lv_immutable_0= 'immutable' ) 'property' (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1767:3: (lv_immutable_0= 'immutable' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1769:6: lv_immutable_0= 'immutable'
                    {
                    lv_immutable_0=(Token)input.LT(1);
                    match(input,50,FOLLOW_50_in_ruleStringProperty24198); 

                            createLeafNode(grammarAccess.getStringProperty2Access().getImmutableImmutableKeyword_0_0_0(), "immutable"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringProperty2Rule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "immutable", true, "immutable", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,45,FOLLOW_45_in_ruleStringProperty24220); 

                            createLeafNode(grammarAccess.getStringProperty2Access().getPropertyKeyword_0_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1792:1: (lv_key_2= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1795:6: lv_key_2= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getStringProperty2Access().getKeyQualifiedNameParserRuleCall_0_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleStringProperty24254);
                    lv_key_2=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringProperty2Rule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "key", lv_key_2, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,20,FOLLOW_20_in_ruleStringProperty24267); 

                            createLeafNode(grammarAccess.getStringProperty2Access().getEqualsSignKeyword_0_3(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1817:1: (lv_value_4= rulePropertyExpression )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1820:6: lv_value_4= rulePropertyExpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getStringProperty2Access().getValuePropertyExpressionParserRuleCall_0_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePropertyExpression_in_ruleStringProperty24301);
                    lv_value_4=rulePropertyExpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringProperty2Rule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "value", lv_value_4, "PropertyExpression", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,33,FOLLOW_33_in_ruleStringProperty24314); 

                            createLeafNode(grammarAccess.getStringProperty2Access().getSemicolonKeyword_0_5(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1843:6: ( ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1843:6: ( ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1843:7: ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? ) ';'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1843:7: ( 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1843:8: 'property' (lv_key_7= ruleQualifiedName ) ( '=' (lv_value_9= rulePropertyExpression ) )?
                    {
                    match(input,45,FOLLOW_45_in_ruleStringProperty24332); 

                            createLeafNode(grammarAccess.getStringProperty2Access().getPropertyKeyword_1_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1847:1: (lv_key_7= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1850:6: lv_key_7= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getStringProperty2Access().getKeyQualifiedNameParserRuleCall_1_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleStringProperty24366);
                    lv_key_7=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getStringProperty2Rule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "key", lv_key_7, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1868:2: ( '=' (lv_value_9= rulePropertyExpression ) )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==20) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1868:3: '=' (lv_value_9= rulePropertyExpression )
                            {
                            match(input,20,FOLLOW_20_in_ruleStringProperty24380); 

                                    createLeafNode(grammarAccess.getStringProperty2Access().getEqualsSignKeyword_1_0_2_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1872:1: (lv_value_9= rulePropertyExpression )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1875:6: lv_value_9= rulePropertyExpression
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getStringProperty2Access().getValuePropertyExpressionParserRuleCall_1_0_2_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulePropertyExpression_in_ruleStringProperty24414);
                            lv_value_9=rulePropertyExpression();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getStringProperty2Rule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "value", lv_value_9, "PropertyExpression", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }


                            }
                            break;

                    }


                    }

                    match(input,33,FOLLOW_33_in_ruleStringProperty24430); 

                            createLeafNode(grammarAccess.getStringProperty2Access().getSemicolonKeyword_1_1(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleStringProperty2


    // $ANTLR start entryRulePropertyExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1904:1: entryRulePropertyExpression returns [EObject current=null] : iv_rulePropertyExpression= rulePropertyExpression EOF ;
    public final EObject entryRulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1904:60: (iv_rulePropertyExpression= rulePropertyExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1905:2: iv_rulePropertyExpression= rulePropertyExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPropertyExpressionRule(), currentNode); 
            pushFollow(FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression4464);
            iv_rulePropertyExpression=rulePropertyExpression();
            _fsp--;

             current =iv_rulePropertyExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyExpression4474); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePropertyExpression


    // $ANTLR start rulePropertyExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1912:1: rulePropertyExpression returns [EObject current=null] : this_Expression_0= ruleExpression ;
    public final EObject rulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Expression_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1917:6: (this_Expression_0= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1919:5: this_Expression_0= ruleExpression
            {
             
                    currentNode=createCompositeNode(grammarAccess.getPropertyExpressionAccess().getExpressionParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleExpression_in_rulePropertyExpression4520);
            this_Expression_0=ruleExpression();
            _fsp--;

             
                    current = this_Expression_0; 
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePropertyExpression


    // $ANTLR start entryRuleSynchronization
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1934:1: entryRuleSynchronization returns [EObject current=null] : iv_ruleSynchronization= ruleSynchronization EOF ;
    public final EObject entryRuleSynchronization() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSynchronization = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1934:57: (iv_ruleSynchronization= ruleSynchronization EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1935:2: iv_ruleSynchronization= ruleSynchronization EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSynchronizationRule(), currentNode); 
            pushFollow(FOLLOW_ruleSynchronization_in_entryRuleSynchronization4551);
            iv_ruleSynchronization=ruleSynchronization();
            _fsp--;

             current =iv_ruleSynchronization; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSynchronization4561); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSynchronization


    // $ANTLR start ruleSynchronization
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1942:1: ruleSynchronization returns [EObject current=null] : ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' ) ;
    public final EObject ruleSynchronization() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_partrefs_0 = null;

        AntlrDatatypeRuleToken lv_partrefs_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1947:6: ( ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1948:1: ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1948:1: ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1948:2: (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1948:2: (lv_partrefs_0= ruleSynchronizedPart )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1951:6: lv_partrefs_0= ruleSynchronizedPart
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getSynchronizationAccess().getPartrefsSynchronizedPartParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4620);
            lv_partrefs_0=ruleSynchronizedPart();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getSynchronizationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		add(current, "partrefs", lv_partrefs_0, "SynchronizedPart", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1969:2: ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+
            int cnt33=0;
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==28) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1969:3: ',' (lv_partrefs_2= ruleSynchronizedPart )
            	    {
            	    match(input,28,FOLLOW_28_in_ruleSynchronization4634); 

            	            createLeafNode(grammarAccess.getSynchronizationAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1973:1: (lv_partrefs_2= ruleSynchronizedPart )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1976:6: lv_partrefs_2= ruleSynchronizedPart
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getSynchronizationAccess().getPartrefsSynchronizedPartParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4668);
            	    lv_partrefs_2=ruleSynchronizedPart();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getSynchronizationRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "partrefs", lv_partrefs_2, "SynchronizedPart", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt33 >= 1 ) break loop33;
                        EarlyExitException eee =
                            new EarlyExitException(33, input);
                        throw eee;
                }
                cnt33++;
            } while (true);

            match(input,33,FOLLOW_33_in_ruleSynchronization4683); 

                    createLeafNode(grammarAccess.getSynchronizationAccess().getSemicolonKeyword_2(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSynchronization


    // $ANTLR start entryRuleSynchronizedPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2005:1: entryRuleSynchronizedPart returns [String current=null] : iv_ruleSynchronizedPart= ruleSynchronizedPart EOF ;
    public final String entryRuleSynchronizedPart() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSynchronizedPart = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2005:57: (iv_ruleSynchronizedPart= ruleSynchronizedPart EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2006:2: iv_ruleSynchronizedPart= ruleSynchronizedPart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSynchronizedPartRule(), currentNode); 
            pushFollow(FOLLOW_ruleSynchronizedPart_in_entryRuleSynchronizedPart4717);
            iv_ruleSynchronizedPart=ruleSynchronizedPart();
            _fsp--;

             current =iv_ruleSynchronizedPart.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSynchronizedPart4728); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSynchronizedPart


    // $ANTLR start ruleSynchronizedPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2013:1: ruleSynchronizedPart returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName ) ;
    public final AntlrDatatypeRuleToken ruleSynchronizedPart() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_CompoundName_0 = null;

        AntlrDatatypeRuleToken this_PartName_2 = null;

        AntlrDatatypeRuleToken this_PartName_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2019:6: ( ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2020:1: ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2020:1: ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName )
            int alt35=2;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2020:2: (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2020:2: (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2021:5: this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )?
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getSynchronizedPartAccess().getCompoundNameParserRuleCall_0_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCompoundName_in_ruleSynchronizedPart4776);
                    this_CompoundName_0=ruleCompoundName();
                    _fsp--;


                    		current.merge(this_CompoundName_0);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2031:1: (kw= '#' this_PartName_2= rulePartName )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==51) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2032:2: kw= '#' this_PartName_2= rulePartName
                            {
                            kw=(Token)input.LT(1);
                            match(input,51,FOLLOW_51_in_ruleSynchronizedPart4795); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getSynchronizedPartAccess().getNumberSignKeyword_0_1_0(), null); 
                                
                             
                                    currentNode=createCompositeNode(grammarAccess.getSynchronizedPartAccess().getPartNameParserRuleCall_0_1_1(), currentNode); 
                                
                            pushFollow(FOLLOW_rulePartName_in_ruleSynchronizedPart4817);
                            this_PartName_2=rulePartName();
                            _fsp--;


                            		current.merge(this_PartName_2);
                                
                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2050:5: this_PartName_3= rulePartName
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getSynchronizedPartAccess().getPartNameParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulePartName_in_ruleSynchronizedPart4853);
                    this_PartName_3=rulePartName();
                    _fsp--;


                    		current.merge(this_PartName_3);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSynchronizedPart


    // $ANTLR start entryRuleBuildPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2068:1: entryRuleBuildPart returns [EObject current=null] : iv_ruleBuildPart= ruleBuildPart EOF ;
    public final EObject entryRuleBuildPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuildPart = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2068:51: (iv_ruleBuildPart= ruleBuildPart EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2069:2: iv_ruleBuildPart= ruleBuildPart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBuildPartRule(), currentNode); 
            pushFollow(FOLLOW_ruleBuildPart_in_entryRuleBuildPart4896);
            iv_ruleBuildPart=ruleBuildPart();
            _fsp--;

             current =iv_ruleBuildPart; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuildPart4906); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleBuildPart


    // $ANTLR start ruleBuildPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2076:1: ruleBuildPart returns [EObject current=null] : (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction ) ;
    public final EObject ruleBuildPart() throws RecognitionException {
        EObject current = null;

        EObject this_Artifacts_0 = null;

        EObject this_Group_1 = null;

        EObject this_Action_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2081:6: ( (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )
            int alt36=3;
            switch ( input.LA(1) ) {
            case 75:
                {
                switch ( input.LA(2) ) {
                case 52:
                    {
                    alt36=1;
                    }
                    break;
                case 34:
                    {
                    int LA36_4 = input.LA(3);

                    if ( (LA36_4==53) ) {
                        alt36=2;
                    }
                    else if ( (LA36_4==56) ) {
                        alt36=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 36, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 53:
                    {
                    alt36=2;
                    }
                    break;
                case 56:
                    {
                    alt36=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 36, 1, input);

                    throw nvae;
                }

                }
                break;
            case 76:
                {
                switch ( input.LA(2) ) {
                case 52:
                    {
                    alt36=1;
                    }
                    break;
                case 34:
                    {
                    int LA36_4 = input.LA(3);

                    if ( (LA36_4==53) ) {
                        alt36=2;
                    }
                    else if ( (LA36_4==56) ) {
                        alt36=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 36, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 56:
                    {
                    alt36=3;
                    }
                    break;
                case 53:
                    {
                    alt36=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 36, 2, input);

                    throw nvae;
                }

                }
                break;
            case 52:
                {
                alt36=1;
                }
                break;
            case 34:
                {
                int LA36_4 = input.LA(2);

                if ( (LA36_4==53) ) {
                    alt36=2;
                }
                else if ( (LA36_4==56) ) {
                    alt36=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 36, 4, input);

                    throw nvae;
                }
                }
                break;
            case 53:
                {
                alt36=2;
                }
                break;
            case 56:
                {
                alt36=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("2082:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2083:5: this_Artifacts_0= ruleArtifacts
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getBuildPartAccess().getArtifactsParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleArtifacts_in_ruleBuildPart4953);
                    this_Artifacts_0=ruleArtifacts();
                    _fsp--;

                     
                            current = this_Artifacts_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2093:5: this_Group_1= ruleGroup
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getBuildPartAccess().getGroupParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleGroup_in_ruleBuildPart4980);
                    this_Group_1=ruleGroup();
                    _fsp--;

                     
                            current = this_Group_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2103:5: this_Action_2= ruleAction
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getBuildPartAccess().getActionParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAction_in_ruleBuildPart5007);
                    this_Action_2=ruleAction();
                    _fsp--;

                     
                            current = this_Action_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleBuildPart


    // $ANTLR start entryRuleArtifacts
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2118:1: entryRuleArtifacts returns [EObject current=null] : iv_ruleArtifacts= ruleArtifacts EOF ;
    public final EObject entryRuleArtifacts() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArtifacts = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2118:51: (iv_ruleArtifacts= ruleArtifacts EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2119:2: iv_ruleArtifacts= ruleArtifacts EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArtifactsRule(), currentNode); 
            pushFollow(FOLLOW_ruleArtifacts_in_entryRuleArtifacts5039);
            iv_ruleArtifacts=ruleArtifacts();
            _fsp--;

             current =iv_ruleArtifacts; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArtifacts5049); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleArtifacts


    // $ANTLR start ruleArtifacts
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2126:1: ruleArtifacts returns [EObject current=null] : ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' ) ;
    public final EObject ruleArtifacts() throws RecognitionException {
        EObject current = null;

        Enumerator lv_visibility_0 = null;

        AntlrDatatypeRuleToken lv_name_2 = null;

        EObject lv_providedCapabilities_4 = null;

        EObject lv_providedCapabilities_6 = null;

        EObject lv_asserts_7 = null;

        EObject lv_paths_9 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2131:6: ( ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2132:1: ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2132:1: ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2132:2: (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2132:2: (lv_visibility_0= ruleVisibility )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( ((LA37_0>=75 && LA37_0<=76)) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2135:6: lv_visibility_0= ruleVisibility
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getVisibilityVisibilityEnumRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVisibility_in_ruleArtifacts5108);
                    lv_visibility_0=ruleVisibility();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getArtifactsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "visibility", lv_visibility_0, "Visibility", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,52,FOLLOW_52_in_ruleArtifacts5122); 

                    createLeafNode(grammarAccess.getArtifactsAccess().getArtifactsKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2157:1: (lv_name_2= rulePartName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2160:6: lv_name_2= rulePartName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getNamePartNameParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePartName_in_ruleArtifacts5156);
            lv_name_2=rulePartName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getArtifactsRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_2, "PartName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2178:2: ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==39) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2178:3: 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )*
                    {
                    match(input,39,FOLLOW_39_in_ruleArtifacts5170); 

                            createLeafNode(grammarAccess.getArtifactsAccess().getProvidesKeyword_3_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2182:1: (lv_providedCapabilities_4= ruleProvidedCapability )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2185:6: lv_providedCapabilities_4= ruleProvidedCapability
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleArtifacts5204);
                    lv_providedCapabilities_4=ruleProvidedCapability();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getArtifactsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "providedCapabilities", lv_providedCapabilities_4, "ProvidedCapability", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2203:2: ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )*
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==28) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2203:3: ',' (lv_providedCapabilities_6= ruleProvidedCapability )
                    	    {
                    	    match(input,28,FOLLOW_28_in_ruleArtifacts5218); 

                    	            createLeafNode(grammarAccess.getArtifactsAccess().getCommaKeyword_3_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2207:1: (lv_providedCapabilities_6= ruleProvidedCapability )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2210:6: lv_providedCapabilities_6= ruleProvidedCapability
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_3_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleArtifacts5252);
                    	    lv_providedCapabilities_6=ruleProvidedCapability();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getArtifactsRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "providedCapabilities", lv_providedCapabilities_6, "ProvidedCapability", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2228:6: (lv_asserts_7= rulePostConditionAssert )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==62) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2231:6: lv_asserts_7= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getAssertsPostConditionAssertParserRuleCall_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleArtifacts5294);
                    lv_asserts_7=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getArtifactsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_7, "PostConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleArtifacts5308); 

                    createLeafNode(grammarAccess.getArtifactsAccess().getLeftCurlyBracketKeyword_5(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2253:1: (lv_paths_9= rulePathGroup )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( ((LA41_0>=RULE_ID && LA41_0<=RULE_STRING)||LA41_0==12||(LA41_0>=43 && LA41_0<=45)||(LA41_0>=49 && LA41_0<=50)) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2256:6: lv_paths_9= rulePathGroup
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getPathsPathGroupParserRuleCall_6_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePathGroup_in_ruleArtifacts5342);
            	    lv_paths_9=rulePathGroup();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getArtifactsRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "paths", lv_paths_9, "PathGroup", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleArtifacts5356); 

                    createLeafNode(grammarAccess.getArtifactsAccess().getRightCurlyBracketKeyword_7(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleArtifacts


    // $ANTLR start entryRulePathGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2285:1: entryRulePathGroup returns [EObject current=null] : iv_rulePathGroup= rulePathGroup EOF ;
    public final EObject entryRulePathGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathGroup = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2285:51: (iv_rulePathGroup= rulePathGroup EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2286:2: iv_rulePathGroup= rulePathGroup EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPathGroupRule(), currentNode); 
            pushFollow(FOLLOW_rulePathGroup_in_entryRulePathGroup5389);
            iv_rulePathGroup=rulePathGroup();
            _fsp--;

             current =iv_rulePathGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePathGroup5399); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePathGroup


    // $ANTLR start rulePathGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2293:1: rulePathGroup returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' ) | ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' ) | (lv_setProperties_24= ruleStringProperty2 ) ) ) ;
    public final EObject rulePathGroup() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_1 = null;

        AntlrDatatypeRuleToken lv_paths_2 = null;

        AntlrDatatypeRuleToken lv_paths_4 = null;

        AntlrDatatypeRuleToken lv_basePath_6 = null;

        AntlrDatatypeRuleToken lv_paths_8 = null;

        AntlrDatatypeRuleToken lv_paths_10 = null;

        AntlrDatatypeRuleToken lv_unsetProperties_16 = null;

        EObject lv_setProperties_18 = null;

        AntlrDatatypeRuleToken lv_unsetProperties_22 = null;

        EObject lv_setProperties_24 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2298:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' ) | ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' ) | (lv_setProperties_24= ruleStringProperty2 ) ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2299:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' ) | ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' ) | (lv_setProperties_24= ruleStringProperty2 ) ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2299:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' ) | ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' ) | (lv_setProperties_24= ruleStringProperty2 ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2299:2: ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' ) | ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' ) | (lv_setProperties_24= ruleStringProperty2 ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2299:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==49) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2299:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,49,FOLLOW_49_in_rulePathGroup5434); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2303:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2306:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_rulePathGroup5468);
                    lv_filter_1=ruleFilter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "filter", lv_filter_1, "Filter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2324:4: ( ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' ) | ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' ) | (lv_setProperties_24= ruleStringProperty2 ) )
            int alt46=5;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2324:5: ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2324:5: ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2324:6: (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2324:6: (lv_paths_2= rulePath )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2327:6: lv_paths_2= rulePath
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathParserRuleCall_1_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePath_in_rulePathGroup5510);
                    lv_paths_2=rulePath();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "paths", lv_paths_2, "Path", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2345:2: ( ',' (lv_paths_4= rulePath ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==28) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2345:3: ',' (lv_paths_4= rulePath )
                    	    {
                    	    match(input,28,FOLLOW_28_in_rulePathGroup5524); 

                    	            createLeafNode(grammarAccess.getPathGroupAccess().getCommaKeyword_1_0_1_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2349:1: (lv_paths_4= rulePath )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2352:6: lv_paths_4= rulePath
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathParserRuleCall_1_0_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePath_in_rulePathGroup5558);
                    	    lv_paths_4=rulePath();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "paths", lv_paths_4, "Path", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);

                    match(input,33,FOLLOW_33_in_rulePathGroup5573); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getSemicolonKeyword_1_0_2(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2375:6: ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2375:6: ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2375:7: (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2375:7: (lv_basePath_6= rulePath )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2378:6: lv_basePath_6= rulePath
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getBasePathPathParserRuleCall_1_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePath_in_rulePathGroup5615);
                    lv_basePath_6=rulePath();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "basePath", lv_basePath_6, "Path", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,26,FOLLOW_26_in_rulePathGroup5628); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getLeftSquareBracketKeyword_1_1_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2400:1: (lv_paths_8= rulePath )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2403:6: lv_paths_8= rulePath
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathParserRuleCall_1_1_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePath_in_rulePathGroup5662);
                    lv_paths_8=rulePath();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "paths", lv_paths_8, "Path", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2421:2: ( ',' (lv_paths_10= rulePath ) )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==28) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2421:3: ',' (lv_paths_10= rulePath )
                    	    {
                    	    match(input,28,FOLLOW_28_in_rulePathGroup5676); 

                    	            createLeafNode(grammarAccess.getPathGroupAccess().getCommaKeyword_1_1_3_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2425:1: (lv_paths_10= rulePath )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2428:6: lv_paths_10= rulePath
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathParserRuleCall_1_1_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePath_in_rulePathGroup5710);
                    	    lv_paths_10=rulePath();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "paths", lv_paths_10, "Path", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);

                    match(input,29,FOLLOW_29_in_rulePathGroup5725); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getRightSquareBracketKeyword_1_1_4(), null); 
                        
                    match(input,33,FOLLOW_33_in_rulePathGroup5734); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getSemicolonKeyword_1_1_5(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2455:6: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2455:6: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2455:7: 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}'
                    {
                    match(input,43,FOLLOW_43_in_rulePathGroup5751); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getPropertiesKeyword_1_2_0(), null); 
                        
                    match(input,38,FOLLOW_38_in_rulePathGroup5760); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getLeftCurlyBracketKeyword_1_2_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2463:1: ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+
                    int cnt45=0;
                    loop45:
                    do {
                        int alt45=3;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==44) ) {
                            alt45=1;
                        }
                        else if ( (LA45_0==RULE_ID||LA45_0==50) ) {
                            alt45=2;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2463:2: ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' )
                    	    {
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2463:2: ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2463:3: 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';'
                    	    {
                    	    match(input,44,FOLLOW_44_in_rulePathGroup5771); 

                    	            createLeafNode(grammarAccess.getPathGroupAccess().getUnsetKeyword_1_2_2_0_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2467:1: (lv_unsetProperties_16= ruleQualifiedName )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2470:6: lv_unsetProperties_16= ruleQualifiedName
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getUnsetPropertiesQualifiedNameParserRuleCall_1_2_2_0_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleQualifiedName_in_rulePathGroup5805);
                    	    lv_unsetProperties_16=ruleQualifiedName();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "unsetProperties", lv_unsetProperties_16, "QualifiedName", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }

                    	    match(input,33,FOLLOW_33_in_rulePathGroup5818); 

                    	            createLeafNode(grammarAccess.getPathGroupAccess().getSemicolonKeyword_1_2_2_0_2(), null); 
                    	        

                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2493:6: (lv_setProperties_18= ruleStringProperty )
                    	    {
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2493:6: (lv_setProperties_18= ruleStringProperty )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2496:6: lv_setProperties_18= ruleStringProperty
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getSetPropertiesStringPropertyParserRuleCall_1_2_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleStringProperty_in_rulePathGroup5859);
                    	    lv_setProperties_18=ruleStringProperty();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "setProperties", lv_setProperties_18, "StringProperty", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt45 >= 1 ) break loop45;
                                EarlyExitException eee =
                                    new EarlyExitException(45, input);
                                throw eee;
                        }
                        cnt45++;
                    } while (true);

                    match(input,40,FOLLOW_40_in_rulePathGroup5874); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getRightCurlyBracketKeyword_1_2_3(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2519:6: ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2519:6: ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2519:7: 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';'
                    {
                    match(input,44,FOLLOW_44_in_rulePathGroup5891); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getUnsetKeyword_1_3_0(), null); 
                        
                    match(input,45,FOLLOW_45_in_rulePathGroup5900); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getPropertyKeyword_1_3_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2527:1: (lv_unsetProperties_22= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2530:6: lv_unsetProperties_22= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getUnsetPropertiesQualifiedNameParserRuleCall_1_3_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_rulePathGroup5934);
                    lv_unsetProperties_22=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "unsetProperties", lv_unsetProperties_22, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,33,FOLLOW_33_in_rulePathGroup5947); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getSemicolonKeyword_1_3_3(), null); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2553:6: (lv_setProperties_24= ruleStringProperty2 )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2553:6: (lv_setProperties_24= ruleStringProperty2 )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2556:6: lv_setProperties_24= ruleStringProperty2
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getSetPropertiesStringProperty2ParserRuleCall_1_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleStringProperty2_in_rulePathGroup5988);
                    lv_setProperties_24=ruleStringProperty2();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "setProperties", lv_setProperties_24, "StringProperty2", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePathGroup


    // $ANTLR start entryRulePath
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2581:1: entryRulePath returns [String current=null] : iv_rulePath= rulePath EOF ;
    public final String entryRulePath() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePath = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2581:45: (iv_rulePath= rulePath EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2582:2: iv_rulePath= rulePath EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPathRule(), currentNode); 
            pushFollow(FOLLOW_rulePath_in_entryRulePath6027);
            iv_rulePath=rulePath();
            _fsp--;

             current =iv_rulePath.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePath6038); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePath


    // $ANTLR start rulePath
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2589:1: rulePath returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) ) ;
    public final AntlrDatatypeRuleToken rulePath() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_2 = null;

        AntlrDatatypeRuleToken this_QualifiedName_4 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2595:6: ( (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2596:1: (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2596:1: (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==RULE_STRING) ) {
                alt50=1;
            }
            else if ( (LA50_0==RULE_ID||LA50_0==12) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2596:1: (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) )", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2596:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePath6078); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getPathAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2604:6: ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2604:6: ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2604:7: (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )?
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2604:7: (kw= '/' )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==12) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2605:2: kw= '/'
                            {
                            kw=(Token)input.LT(1);
                            match(input,12,FOLLOW_12_in_rulePath6104); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getPathAccess().getSolidusKeyword_1_0(), null); 
                                

                            }
                            break;

                    }

                     
                            currentNode=createCompositeNode(grammarAccess.getPathAccess().getQualifiedNameParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleQualifiedName_in_rulePath6128);
                    this_QualifiedName_2=ruleQualifiedName();
                    _fsp--;


                    		current.merge(this_QualifiedName_2);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2621:1: (kw= '/' this_QualifiedName_4= ruleQualifiedName )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==12) ) {
                            int LA48_1 = input.LA(2);

                            if ( (LA48_1==RULE_ID) ) {
                                alt48=1;
                            }


                        }


                        switch (alt48) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2622:2: kw= '/' this_QualifiedName_4= ruleQualifiedName
                    	    {
                    	    kw=(Token)input.LT(1);
                    	    match(input,12,FOLLOW_12_in_rulePath6147); 

                    	            current.merge(kw);
                    	            createLeafNode(grammarAccess.getPathAccess().getSolidusKeyword_1_2_0(), null); 
                    	        
                    	     
                    	            currentNode=createCompositeNode(grammarAccess.getPathAccess().getQualifiedNameParserRuleCall_1_2_1(), currentNode); 
                    	        
                    	    pushFollow(FOLLOW_ruleQualifiedName_in_rulePath6169);
                    	    this_QualifiedName_4=ruleQualifiedName();
                    	    _fsp--;


                    	    		current.merge(this_QualifiedName_4);
                    	        
                    	     
                    	            currentNode = currentNode.getParent();
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2638:3: (kw= '/' )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==12) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2639:2: kw= '/'
                            {
                            kw=(Token)input.LT(1);
                            match(input,12,FOLLOW_12_in_rulePath6190); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getPathAccess().getSolidusKeyword_1_3(), null); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePath


    // $ANTLR start entryRuleGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2652:1: entryRuleGroup returns [EObject current=null] : iv_ruleGroup= ruleGroup EOF ;
    public final EObject entryRuleGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroup = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2652:47: (iv_ruleGroup= ruleGroup EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2653:2: iv_ruleGroup= ruleGroup EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGroupRule(), currentNode); 
            pushFollow(FOLLOW_ruleGroup_in_entryRuleGroup6231);
            iv_ruleGroup=ruleGroup();
            _fsp--;

             current =iv_ruleGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroup6241); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleGroup


    // $ANTLR start ruleGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2660:1: ruleGroup returns [EObject current=null] : ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' ) ;
    public final EObject ruleGroup() throws RecognitionException {
        EObject current = null;

        Token lv_synchronized_1=null;
        Enumerator lv_visibility_0 = null;

        AntlrDatatypeRuleToken lv_name_3 = null;

        EObject lv_providedCapabilities_5 = null;

        EObject lv_providedCapabilities_7 = null;

        EObject lv_asserts_8 = null;

        EObject lv_asserts_9 = null;

        EObject lv_prerequisites_11 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2665:6: ( ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2666:1: ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2666:1: ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2666:2: (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2666:2: (lv_visibility_0= ruleVisibility )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( ((LA51_0>=75 && LA51_0<=76)) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2669:6: lv_visibility_0= ruleVisibility
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getVisibilityVisibilityEnumRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVisibility_in_ruleGroup6300);
                    lv_visibility_0=ruleVisibility();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "visibility", lv_visibility_0, "Visibility", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2687:3: (lv_synchronized_1= 'synchronized' )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==34) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2689:6: lv_synchronized_1= 'synchronized'
                    {
                    lv_synchronized_1=(Token)input.LT(1);
                    match(input,34,FOLLOW_34_in_ruleGroup6326); 

                            createLeafNode(grammarAccess.getGroupAccess().getSynchronizedSynchronizedKeyword_1_0(), "synchronized"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "synchronized", true, "synchronized", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            match(input,53,FOLLOW_53_in_ruleGroup6349); 

                    createLeafNode(grammarAccess.getGroupAccess().getGroupKeyword_2(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2712:1: (lv_name_3= rulePartName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2715:6: lv_name_3= rulePartName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getNamePartNameParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePartName_in_ruleGroup6383);
            lv_name_3=rulePartName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_3, "PartName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2733:2: ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==39) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2733:3: 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )*
                    {
                    match(input,39,FOLLOW_39_in_ruleGroup6397); 

                            createLeafNode(grammarAccess.getGroupAccess().getProvidesKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2737:1: (lv_providedCapabilities_5= ruleProvidedCapability )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2740:6: lv_providedCapabilities_5= ruleProvidedCapability
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleGroup6431);
                    lv_providedCapabilities_5=ruleProvidedCapability();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "providedCapabilities", lv_providedCapabilities_5, "ProvidedCapability", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2758:2: ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==28) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2758:3: ',' (lv_providedCapabilities_7= ruleProvidedCapability )
                    	    {
                    	    match(input,28,FOLLOW_28_in_ruleGroup6445); 

                    	            createLeafNode(grammarAccess.getGroupAccess().getCommaKeyword_4_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2762:1: (lv_providedCapabilities_7= ruleProvidedCapability )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2765:6: lv_providedCapabilities_7= ruleProvidedCapability
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_4_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleGroup6479);
                    	    lv_providedCapabilities_7=ruleProvidedCapability();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "providedCapabilities", lv_providedCapabilities_7, "ProvidedCapability", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop53;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2783:6: (lv_asserts_8= rulePreConditionAssert )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==61) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2786:6: lv_asserts_8= rulePreConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getAssertsPreConditionAssertParserRuleCall_5_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePreConditionAssert_in_ruleGroup6521);
                    lv_asserts_8=rulePreConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_8, "PreConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2804:3: (lv_asserts_9= rulePostConditionAssert )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==62) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2807:6: lv_asserts_9= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getAssertsPostConditionAssertParserRuleCall_6_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleGroup6560);
                    lv_asserts_9=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_9, "PostConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleGroup6574); 

                    createLeafNode(grammarAccess.getGroupAccess().getLeftCurlyBracketKeyword_7(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2829:1: (lv_prerequisites_11= rulePrerequisite )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( ((LA57_0>=RULE_ID && LA57_0<=RULE_STRING)||LA57_0==38||LA57_0==49||LA57_0==54) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2832:6: lv_prerequisites_11= rulePrerequisite
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getPrerequisitesPrerequisiteParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePrerequisite_in_ruleGroup6608);
            	    lv_prerequisites_11=rulePrerequisite();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "prerequisites", lv_prerequisites_11, "Prerequisite", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleGroup6622); 

                    createLeafNode(grammarAccess.getGroupAccess().getRightCurlyBracketKeyword_9(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleGroup


    // $ANTLR start entryRulePrerequisite
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2861:1: entryRulePrerequisite returns [EObject current=null] : iv_rulePrerequisite= rulePrerequisite EOF ;
    public final EObject entryRulePrerequisite() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrerequisite = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2861:54: (iv_rulePrerequisite= rulePrerequisite EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2862:2: iv_rulePrerequisite= rulePrerequisite EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrerequisiteRule(), currentNode); 
            pushFollow(FOLLOW_rulePrerequisite_in_entryRulePrerequisite6655);
            iv_rulePrerequisite=rulePrerequisite();
            _fsp--;

             current =iv_rulePrerequisite; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrerequisite6665); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePrerequisite


    // $ANTLR start rulePrerequisite
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2869:1: rulePrerequisite returns [EObject current=null] : ( ( (lv_surpressed_0= 'void' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? (lv_partReference_5= rulePrerequisiteEntry ) ) (lv_closure_6= ruleClosure )? ';' ) ;
    public final EObject rulePrerequisite() throws RecognitionException {
        EObject current = null;

        Token lv_surpressed_0=null;
        EObject lv_filter_2 = null;

        AntlrDatatypeRuleToken lv_alias_3 = null;

        EObject lv_partReference_5 = null;

        EObject lv_closure_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2874:6: ( ( ( (lv_surpressed_0= 'void' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? (lv_partReference_5= rulePrerequisiteEntry ) ) (lv_closure_6= ruleClosure )? ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2875:1: ( ( (lv_surpressed_0= 'void' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? (lv_partReference_5= rulePrerequisiteEntry ) ) (lv_closure_6= ruleClosure )? ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2875:1: ( ( (lv_surpressed_0= 'void' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? (lv_partReference_5= rulePrerequisiteEntry ) ) (lv_closure_6= ruleClosure )? ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2875:2: ( (lv_surpressed_0= 'void' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? (lv_partReference_5= rulePrerequisiteEntry ) ) (lv_closure_6= ruleClosure )? ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2875:2: ( (lv_surpressed_0= 'void' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? (lv_partReference_5= rulePrerequisiteEntry ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2875:3: (lv_surpressed_0= 'void' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? (lv_partReference_5= rulePrerequisiteEntry )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2875:3: (lv_surpressed_0= 'void' )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==54) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2877:6: lv_surpressed_0= 'void'
                    {
                    lv_surpressed_0=(Token)input.LT(1);
                    match(input,54,FOLLOW_54_in_rulePrerequisite6712); 

                            createLeafNode(grammarAccess.getPrerequisiteAccess().getSurpressedVoidKeyword_0_0_0(), "surpressed"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrerequisiteRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "surpressed", true, "void", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2896:3: ( 'when' (lv_filter_2= ruleFilter ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==49) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2896:4: 'when' (lv_filter_2= ruleFilter )
                    {
                    match(input,49,FOLLOW_49_in_rulePrerequisite6736); 

                            createLeafNode(grammarAccess.getPrerequisiteAccess().getWhenKeyword_0_1_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2900:1: (lv_filter_2= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2903:6: lv_filter_2= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getFilterFilterParserRuleCall_0_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_rulePrerequisite6770);
                    lv_filter_2=ruleFilter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrerequisiteRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "filter", lv_filter_2, "Filter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2921:4: ( (lv_alias_3= ruleQualifiedName ) '=' )?
            int alt60=2;
            alt60 = dfa60.predict(input);
            switch (alt60) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2921:5: (lv_alias_3= ruleQualifiedName ) '='
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2921:5: (lv_alias_3= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2924:6: lv_alias_3= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getAliasQualifiedNameParserRuleCall_0_2_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_rulePrerequisite6811);
                    lv_alias_3=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrerequisiteRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "alias", lv_alias_3, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,20,FOLLOW_20_in_rulePrerequisite6824); 

                            createLeafNode(grammarAccess.getPrerequisiteAccess().getEqualsSignKeyword_0_2_1(), null); 
                        

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2946:3: (lv_partReference_5= rulePrerequisiteEntry )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2949:6: lv_partReference_5= rulePrerequisiteEntry
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getPartReferencePrerequisiteEntryParserRuleCall_0_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePrerequisiteEntry_in_rulePrerequisite6860);
            lv_partReference_5=rulePrerequisiteEntry();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPrerequisiteRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "partReference", lv_partReference_5, "PrerequisiteEntry", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2967:3: (lv_closure_6= ruleClosure )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==55) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2970:6: lv_closure_6= ruleClosure
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getClosureClosureParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleClosure_in_rulePrerequisite6899);
                    lv_closure_6=ruleClosure();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrerequisiteRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "closure", lv_closure_6, "Closure", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,33,FOLLOW_33_in_rulePrerequisite6913); 

                    createLeafNode(grammarAccess.getPrerequisiteAccess().getSemicolonKeyword_2(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePrerequisite


    // $ANTLR start entryRuleClosure
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2999:1: entryRuleClosure returns [EObject current=null] : iv_ruleClosure= ruleClosure EOF ;
    public final EObject entryRuleClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClosure = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2999:49: (iv_ruleClosure= ruleClosure EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3000:2: iv_ruleClosure= ruleClosure EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClosureRule(), currentNode); 
            pushFollow(FOLLOW_ruleClosure_in_entryRuleClosure6946);
            iv_ruleClosure=ruleClosure();
            _fsp--;

             current =iv_ruleClosure; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClosure6956); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleClosure


    // $ANTLR start ruleClosure
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3007:1: ruleClosure returns [EObject current=null] : ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' ) | (lv_setProperties_13= ruleStringProperty2 ) | ( 'advice' (lv_advice_15= ruleCompoundAdvice ) ) )* '}' ) ;
    public final EObject ruleClosure() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_unsetProperties_5 = null;

        EObject lv_setProperties_7 = null;

        AntlrDatatypeRuleToken lv_unsetProperties_11 = null;

        EObject lv_setProperties_13 = null;

        EObject lv_advice_15 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3012:6: ( ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' ) | (lv_setProperties_13= ruleStringProperty2 ) | ( 'advice' (lv_advice_15= ruleCompoundAdvice ) ) )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3013:1: ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' ) | (lv_setProperties_13= ruleStringProperty2 ) | ( 'advice' (lv_advice_15= ruleCompoundAdvice ) ) )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3013:1: ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' ) | (lv_setProperties_13= ruleStringProperty2 ) | ( 'advice' (lv_advice_15= ruleCompoundAdvice ) ) )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3013:2: 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' ) | (lv_setProperties_13= ruleStringProperty2 ) | ( 'advice' (lv_advice_15= ruleCompoundAdvice ) ) )* '}'
            {
            match(input,55,FOLLOW_55_in_ruleClosure6990); 

                    createLeafNode(grammarAccess.getClosureAccess().getWithKeyword_0(), null); 
                
            match(input,38,FOLLOW_38_in_ruleClosure6999); 

                    createLeafNode(grammarAccess.getClosureAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3021:1: ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' ) | (lv_setProperties_13= ruleStringProperty2 ) | ( 'advice' (lv_advice_15= ruleCompoundAdvice ) ) )*
            loop63:
            do {
                int alt63=5;
                switch ( input.LA(1) ) {
                case 43:
                    {
                    alt63=1;
                    }
                    break;
                case 44:
                    {
                    alt63=2;
                    }
                    break;
                case 45:
                case 50:
                    {
                    alt63=3;
                    }
                    break;
                case 46:
                    {
                    alt63=4;
                    }
                    break;

                }

                switch (alt63) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3021:2: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3021:2: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3021:3: 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+ '}'
            	    {
            	    match(input,43,FOLLOW_43_in_ruleClosure7010); 

            	            createLeafNode(grammarAccess.getClosureAccess().getPropertiesKeyword_2_0_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleClosure7019); 

            	            createLeafNode(grammarAccess.getClosureAccess().getLeftCurlyBracketKeyword_2_0_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3029:1: ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= ruleStringProperty ) )+
            	    int cnt62=0;
            	    loop62:
            	    do {
            	        int alt62=3;
            	        int LA62_0 = input.LA(1);

            	        if ( (LA62_0==44) ) {
            	            alt62=1;
            	        }
            	        else if ( (LA62_0==RULE_ID||LA62_0==50) ) {
            	            alt62=2;
            	        }


            	        switch (alt62) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3029:2: ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' )
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3029:2: ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3029:3: 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';'
            	    	    {
            	    	    match(input,44,FOLLOW_44_in_ruleClosure7030); 

            	    	            createLeafNode(grammarAccess.getClosureAccess().getUnsetKeyword_2_0_2_0_0(), null); 
            	    	        
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3033:1: (lv_unsetProperties_5= ruleQualifiedName )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3036:6: lv_unsetProperties_5= ruleQualifiedName
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getUnsetPropertiesQualifiedNameParserRuleCall_2_0_2_0_1_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleQualifiedName_in_ruleClosure7064);
            	    	    lv_unsetProperties_5=ruleQualifiedName();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getClosureRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "unsetProperties", lv_unsetProperties_5, "QualifiedName", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,33,FOLLOW_33_in_ruleClosure7077); 

            	    	            createLeafNode(grammarAccess.getClosureAccess().getSemicolonKeyword_2_0_2_0_2(), null); 
            	    	        

            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3059:6: (lv_setProperties_7= ruleStringProperty )
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3059:6: (lv_setProperties_7= ruleStringProperty )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3062:6: lv_setProperties_7= ruleStringProperty
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getSetPropertiesStringPropertyParserRuleCall_2_0_2_1_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleStringProperty_in_ruleClosure7118);
            	    	    lv_setProperties_7=ruleStringProperty();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getClosureRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "setProperties", lv_setProperties_7, "StringProperty", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt62 >= 1 ) break loop62;
            	                EarlyExitException eee =
            	                    new EarlyExitException(62, input);
            	                throw eee;
            	        }
            	        cnt62++;
            	    } while (true);

            	    match(input,40,FOLLOW_40_in_ruleClosure7133); 

            	            createLeafNode(grammarAccess.getClosureAccess().getRightCurlyBracketKeyword_2_0_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3085:6: ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3085:6: ( 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3085:7: 'unset' 'property' (lv_unsetProperties_11= ruleQualifiedName ) ';'
            	    {
            	    match(input,44,FOLLOW_44_in_ruleClosure7150); 

            	            createLeafNode(grammarAccess.getClosureAccess().getUnsetKeyword_2_1_0(), null); 
            	        
            	    match(input,45,FOLLOW_45_in_ruleClosure7159); 

            	            createLeafNode(grammarAccess.getClosureAccess().getPropertyKeyword_2_1_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3093:1: (lv_unsetProperties_11= ruleQualifiedName )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3096:6: lv_unsetProperties_11= ruleQualifiedName
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getUnsetPropertiesQualifiedNameParserRuleCall_2_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleQualifiedName_in_ruleClosure7193);
            	    lv_unsetProperties_11=ruleQualifiedName();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClosureRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "unsetProperties", lv_unsetProperties_11, "QualifiedName", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,33,FOLLOW_33_in_ruleClosure7206); 

            	            createLeafNode(grammarAccess.getClosureAccess().getSemicolonKeyword_2_1_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 3 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3119:6: (lv_setProperties_13= ruleStringProperty2 )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3119:6: (lv_setProperties_13= ruleStringProperty2 )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3122:6: lv_setProperties_13= ruleStringProperty2
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getSetPropertiesStringProperty2ParserRuleCall_2_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleStringProperty2_in_ruleClosure7247);
            	    lv_setProperties_13=ruleStringProperty2();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClosureRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "setProperties", lv_setProperties_13, "StringProperty2", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3141:6: ( 'advice' (lv_advice_15= ruleCompoundAdvice ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3141:6: ( 'advice' (lv_advice_15= ruleCompoundAdvice ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3141:7: 'advice' (lv_advice_15= ruleCompoundAdvice )
            	    {
            	    match(input,46,FOLLOW_46_in_ruleClosure7267); 

            	            createLeafNode(grammarAccess.getClosureAccess().getAdviceKeyword_2_3_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3145:1: (lv_advice_15= ruleCompoundAdvice )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3148:6: lv_advice_15= ruleCompoundAdvice
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getAdviceCompoundAdviceParserRuleCall_2_3_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleClosure7301);
            	    lv_advice_15=ruleCompoundAdvice();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClosureRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "advice", lv_advice_15, "CompoundAdvice", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop63;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleClosure7317); 

                    createLeafNode(grammarAccess.getClosureAccess().getRightCurlyBracketKeyword_3(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleClosure


    // $ANTLR start entryRulePrerequisiteEntry
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3177:1: entryRulePrerequisiteEntry returns [EObject current=null] : iv_rulePrerequisiteEntry= rulePrerequisiteEntry EOF ;
    public final EObject entryRulePrerequisiteEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrerequisiteEntry = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3177:59: (iv_rulePrerequisiteEntry= rulePrerequisiteEntry EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3178:2: iv_rulePrerequisiteEntry= rulePrerequisiteEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrerequisiteEntryRule(), currentNode); 
            pushFollow(FOLLOW_rulePrerequisiteEntry_in_entryRulePrerequisiteEntry7350);
            iv_rulePrerequisiteEntry=rulePrerequisiteEntry();
            _fsp--;

             current =iv_rulePrerequisiteEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrerequisiteEntry7360); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePrerequisiteEntry


    // $ANTLR start rulePrerequisiteEntry
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3185:1: rulePrerequisiteEntry returns [EObject current=null] : (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences ) ;
    public final EObject rulePrerequisiteEntry() throws RecognitionException {
        EObject current = null;

        EObject this_PartInSelf_0 = null;

        EObject this_CapabilityReferencedPart_1 = null;

        EObject this_CompoundReferences_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3190:6: ( (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3191:1: (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3191:1: (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences )
            int alt64=3;
            alt64 = dfa64.predict(input);
            switch (alt64) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3192:5: this_PartInSelf_0= rulePartInSelf
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrerequisiteEntryAccess().getPartInSelfParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_rulePartInSelf_in_rulePrerequisiteEntry7407);
                    this_PartInSelf_0=rulePartInSelf();
                    _fsp--;

                     
                            current = this_PartInSelf_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3202:5: this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrerequisiteEntryAccess().getCapabilityReferencedPartParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCapabilityReferencedPart_in_rulePrerequisiteEntry7434);
                    this_CapabilityReferencedPart_1=ruleCapabilityReferencedPart();
                    _fsp--;

                     
                            current = this_CapabilityReferencedPart_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3212:5: this_CompoundReferences_2= ruleCompoundReferences
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrerequisiteEntryAccess().getCompoundReferencesParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCompoundReferences_in_rulePrerequisiteEntry7461);
                    this_CompoundReferences_2=ruleCompoundReferences();
                    _fsp--;

                     
                            current = this_CompoundReferences_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePrerequisiteEntry


    // $ANTLR start entryRulePartInSelf
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3227:1: entryRulePartInSelf returns [EObject current=null] : iv_rulePartInSelf= rulePartInSelf EOF ;
    public final EObject entryRulePartInSelf() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePartInSelf = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3227:52: (iv_rulePartInSelf= rulePartInSelf EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3228:2: iv_rulePartInSelf= rulePartInSelf EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPartInSelfRule(), currentNode); 
            pushFollow(FOLLOW_rulePartInSelf_in_entryRulePartInSelf7493);
            iv_rulePartInSelf=rulePartInSelf();
            _fsp--;

             current =iv_rulePartInSelf; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePartInSelf7503); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePartInSelf


    // $ANTLR start rulePartInSelf
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3235:1: rulePartInSelf returns [EObject current=null] : (lv_partName_0= rulePartName ) ;
    public final EObject rulePartInSelf() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_partName_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3240:6: ( (lv_partName_0= rulePartName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3241:1: (lv_partName_0= rulePartName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3241:1: (lv_partName_0= rulePartName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3244:6: lv_partName_0= rulePartName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getPartInSelfAccess().getPartNamePartNameParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePartName_in_rulePartInSelf7561);
            lv_partName_0=rulePartName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPartInSelfRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "partName", lv_partName_0, "PartName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePartInSelf


    // $ANTLR start entryRuleCapabilityReferencedPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3269:1: entryRuleCapabilityReferencedPart returns [EObject current=null] : iv_ruleCapabilityReferencedPart= ruleCapabilityReferencedPart EOF ;
    public final EObject entryRuleCapabilityReferencedPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCapabilityReferencedPart = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3269:66: (iv_ruleCapabilityReferencedPart= ruleCapabilityReferencedPart EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3270:2: iv_ruleCapabilityReferencedPart= ruleCapabilityReferencedPart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCapabilityReferencedPartRule(), currentNode); 
            pushFollow(FOLLOW_ruleCapabilityReferencedPart_in_entryRuleCapabilityReferencedPart7597);
            iv_ruleCapabilityReferencedPart=ruleCapabilityReferencedPart();
            _fsp--;

             current =iv_ruleCapabilityReferencedPart; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCapabilityReferencedPart7607); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCapabilityReferencedPart


    // $ANTLR start ruleCapabilityReferencedPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3277:1: ruleCapabilityReferencedPart returns [EObject current=null] : ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) ) ;
    public final EObject ruleCapabilityReferencedPart() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_interface_0 = null;

        AntlrDatatypeRuleToken lv_name_2 = null;

        AntlrDatatypeRuleToken lv_range_4 = null;

        AntlrDatatypeRuleToken lv_partName_6 = null;

        AntlrDatatypeRuleToken lv_interface_7 = null;

        AntlrDatatypeRuleToken lv_name_9 = null;

        AntlrDatatypeRuleToken lv_range_11 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3282:6: ( ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3283:1: ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3283:1: ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) )
            int alt67=2;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3283:2: ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3283:2: ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3283:3: (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3283:3: (lv_interface_0= ruleInterfaceName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3286:6: lv_interface_0= ruleInterfaceName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getInterfaceInterfaceNameParserRuleCall_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart7667);
                    lv_interface_0=ruleInterfaceName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCapabilityReferencedPartRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "interface", lv_interface_0, "InterfaceName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleCapabilityReferencedPart7680); 

                            createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_0_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3308:1: (lv_name_2= ruleUnitName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3311:6: lv_name_2= ruleUnitName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getNameUnitNameParserRuleCall_0_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart7714);
                    lv_name_2=ruleUnitName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCapabilityReferencedPartRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_2, "UnitName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3329:2: ( '/' (lv_range_4= ruleVersionRange ) )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==12) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3329:3: '/' (lv_range_4= ruleVersionRange )
                            {
                            match(input,12,FOLLOW_12_in_ruleCapabilityReferencedPart7728); 

                                    createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_0_3_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3333:1: (lv_range_4= ruleVersionRange )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3336:6: lv_range_4= ruleVersionRange
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getRangeVersionRangeParserRuleCall_0_3_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart7762);
                            lv_range_4=ruleVersionRange();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getCapabilityReferencedPartRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "range", lv_range_4, "VersionRange", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }


                            }
                            break;

                    }

                    match(input,51,FOLLOW_51_in_ruleCapabilityReferencedPart7777); 

                            createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getNumberSignKeyword_0_4(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3358:1: (lv_partName_6= rulePartName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3361:6: lv_partName_6= rulePartName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getPartNamePartNameParserRuleCall_0_5_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePartName_in_ruleCapabilityReferencedPart7811);
                    lv_partName_6=rulePartName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCapabilityReferencedPartRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "partName", lv_partName_6, "PartName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3380:6: ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3380:6: ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3380:7: (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )?
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3380:7: (lv_interface_7= ruleInterfaceName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3383:6: lv_interface_7= ruleInterfaceName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getInterfaceInterfaceNameParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart7857);
                    lv_interface_7=ruleInterfaceName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCapabilityReferencedPartRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "interface", lv_interface_7, "InterfaceName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleCapabilityReferencedPart7870); 

                            createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_1_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3405:1: (lv_name_9= ruleUnitName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3408:6: lv_name_9= ruleUnitName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getNameUnitNameParserRuleCall_1_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart7904);
                    lv_name_9=ruleUnitName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCapabilityReferencedPartRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_9, "UnitName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3426:2: ( '/' (lv_range_11= ruleVersionRange ) )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==12) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3426:3: '/' (lv_range_11= ruleVersionRange )
                            {
                            match(input,12,FOLLOW_12_in_ruleCapabilityReferencedPart7918); 

                                    createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_1_3_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3430:1: (lv_range_11= ruleVersionRange )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3433:6: lv_range_11= ruleVersionRange
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getRangeVersionRangeParserRuleCall_1_3_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart7952);
                            lv_range_11=ruleVersionRange();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getCapabilityReferencedPartRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "range", lv_range_11, "VersionRange", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCapabilityReferencedPart


    // $ANTLR start entryRuleCompoundReferences
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3458:1: entryRuleCompoundReferences returns [EObject current=null] : iv_ruleCompoundReferences= ruleCompoundReferences EOF ;
    public final EObject entryRuleCompoundReferences() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompoundReferences = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3458:60: (iv_ruleCompoundReferences= ruleCompoundReferences EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3459:2: iv_ruleCompoundReferences= ruleCompoundReferences EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCompoundReferencesRule(), currentNode); 
            pushFollow(FOLLOW_ruleCompoundReferences_in_entryRuleCompoundReferences7992);
            iv_ruleCompoundReferences=ruleCompoundReferences();
            _fsp--;

             current =iv_ruleCompoundReferences; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCompoundReferences8002); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCompoundReferences


    // $ANTLR start ruleCompoundReferences
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3466:1: ruleCompoundReferences returns [EObject current=null] : ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' ) ;
    public final EObject ruleCompoundReferences() throws RecognitionException {
        EObject current = null;

        EObject lv_prerequisites_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3471:6: ( ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3472:1: ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3472:1: ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3472:2: '{' (lv_prerequisites_1= rulePrerequisite )* '}'
            {
            match(input,38,FOLLOW_38_in_ruleCompoundReferences8036); 

                    createLeafNode(grammarAccess.getCompoundReferencesAccess().getLeftCurlyBracketKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3476:1: (lv_prerequisites_1= rulePrerequisite )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( ((LA68_0>=RULE_ID && LA68_0<=RULE_STRING)||LA68_0==38||LA68_0==49||LA68_0==54) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3479:6: lv_prerequisites_1= rulePrerequisite
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getCompoundReferencesAccess().getPrerequisitesPrerequisiteParserRuleCall_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePrerequisite_in_ruleCompoundReferences8070);
            	    lv_prerequisites_1=rulePrerequisite();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getCompoundReferencesRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "prerequisites", lv_prerequisites_1, "Prerequisite", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleCompoundReferences8084); 

                    createLeafNode(grammarAccess.getCompoundReferencesAccess().getRightCurlyBracketKeyword_2(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCompoundReferences


    // $ANTLR start entryRuleAction
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3508:1: entryRuleAction returns [EObject current=null] : iv_ruleAction= ruleAction EOF ;
    public final EObject entryRuleAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAction = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3508:48: (iv_ruleAction= ruleAction EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3509:2: iv_ruleAction= ruleAction EOF
            {
             currentNode = createCompositeNode(grammarAccess.getActionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAction_in_entryRuleAction8117);
            iv_ruleAction=ruleAction();
            _fsp--;

             current =iv_ruleAction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAction8127); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAction


    // $ANTLR start ruleAction
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3516:1: ruleAction returns [EObject current=null] : ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_12= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )* )? (lv_asserts_15= rulePreConditionAssert )? (lv_asserts_16= rulePostConditionAssert )? '{' (lv_resultGroups_18= ruleResult )* '}' ) ;
    public final EObject ruleAction() throws RecognitionException {
        EObject current = null;

        Token lv_synchronized_1=null;
        Enumerator lv_visibility_0 = null;

        EObject lv_actorParameters_7 = null;

        EObject lv_actorParameters_9 = null;

        EObject lv_providedCapabilities_12 = null;

        EObject lv_providedCapabilities_14 = null;

        EObject lv_asserts_15 = null;

        EObject lv_asserts_16 = null;

        EObject lv_resultGroups_18 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3521:6: ( ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_12= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )* )? (lv_asserts_15= rulePreConditionAssert )? (lv_asserts_16= rulePostConditionAssert )? '{' (lv_resultGroups_18= ruleResult )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3522:1: ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_12= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )* )? (lv_asserts_15= rulePreConditionAssert )? (lv_asserts_16= rulePostConditionAssert )? '{' (lv_resultGroups_18= ruleResult )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3522:1: ( (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_12= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )* )? (lv_asserts_15= rulePreConditionAssert )? (lv_asserts_16= rulePostConditionAssert )? '{' (lv_resultGroups_18= ruleResult )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3522:2: (lv_visibility_0= ruleVisibility )? (lv_synchronized_1= 'synchronized' )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_12= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )* )? (lv_asserts_15= rulePreConditionAssert )? (lv_asserts_16= rulePostConditionAssert )? '{' (lv_resultGroups_18= ruleResult )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3522:2: (lv_visibility_0= ruleVisibility )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( ((LA69_0>=75 && LA69_0<=76)) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3525:6: lv_visibility_0= ruleVisibility
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getVisibilityVisibilityEnumRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVisibility_in_ruleAction8186);
                    lv_visibility_0=ruleVisibility();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "visibility", lv_visibility_0, "Visibility", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3543:3: (lv_synchronized_1= 'synchronized' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==34) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3545:6: lv_synchronized_1= 'synchronized'
                    {
                    lv_synchronized_1=(Token)input.LT(1);
                    match(input,34,FOLLOW_34_in_ruleAction8212); 

                            createLeafNode(grammarAccess.getActionAccess().getSynchronizedSynchronizedKeyword_1_0(), "synchronized"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "synchronized", true, "synchronized", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            match(input,56,FOLLOW_56_in_ruleAction8235); 

                    createLeafNode(grammarAccess.getActionAccess().getActionKeyword_2(), null); 
                
            pushFollow(FOLLOW_rulePartName_in_ruleAction8244);
            rulePartName();
            _fsp--;

            match(input,57,FOLLOW_57_in_ruleAction8245); 

                    createLeafNode(grammarAccess.getActionAccess().getActorKeyword_4(), null); 
                
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleAction8254);
            ruleQualifiedName();
            _fsp--;

            match(input,27,FOLLOW_27_in_ruleAction8255); 

                    createLeafNode(grammarAccess.getActionAccess().getLeftParenthesisKeyword_6(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3576:1: ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==RULE_ID) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3576:2: (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )*
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3576:2: (lv_actorParameters_7= ruleParameter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3579:6: lv_actorParameters_7= ruleParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getActorParametersParameterParserRuleCall_7_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleParameter_in_ruleAction8290);
                    lv_actorParameters_7=ruleParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "actorParameters", lv_actorParameters_7, "Parameter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3597:2: ( ',' (lv_actorParameters_9= ruleParameter ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==28) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3597:3: ',' (lv_actorParameters_9= ruleParameter )
                    	    {
                    	    match(input,28,FOLLOW_28_in_ruleAction8304); 

                    	            createLeafNode(grammarAccess.getActionAccess().getCommaKeyword_7_1_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3601:1: (lv_actorParameters_9= ruleParameter )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3604:6: lv_actorParameters_9= ruleParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getActorParametersParameterParserRuleCall_7_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleParameter_in_ruleAction8338);
                    	    lv_actorParameters_9=ruleParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "actorParameters", lv_actorParameters_9, "Parameter", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,30,FOLLOW_30_in_ruleAction8355); 

                    createLeafNode(grammarAccess.getActionAccess().getRightParenthesisKeyword_8(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3626:1: ( 'provides' (lv_providedCapabilities_12= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==39) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3626:2: 'provides' (lv_providedCapabilities_12= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )*
                    {
                    match(input,39,FOLLOW_39_in_ruleAction8365); 

                            createLeafNode(grammarAccess.getActionAccess().getProvidesKeyword_9_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3630:1: (lv_providedCapabilities_12= ruleProvidedCapability )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3633:6: lv_providedCapabilities_12= ruleProvidedCapability
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleAction8399);
                    lv_providedCapabilities_12=ruleProvidedCapability();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "providedCapabilities", lv_providedCapabilities_12, "ProvidedCapability", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3651:2: ( ',' (lv_providedCapabilities_14= ruleProvidedCapability ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==28) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3651:3: ',' (lv_providedCapabilities_14= ruleProvidedCapability )
                    	    {
                    	    match(input,28,FOLLOW_28_in_ruleAction8413); 

                    	            createLeafNode(grammarAccess.getActionAccess().getCommaKeyword_9_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3655:1: (lv_providedCapabilities_14= ruleProvidedCapability )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3658:6: lv_providedCapabilities_14= ruleProvidedCapability
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_9_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleAction8447);
                    	    lv_providedCapabilities_14=ruleProvidedCapability();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "providedCapabilities", lv_providedCapabilities_14, "ProvidedCapability", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3676:6: (lv_asserts_15= rulePreConditionAssert )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==61) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3679:6: lv_asserts_15= rulePreConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getAssertsPreConditionAssertParserRuleCall_10_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePreConditionAssert_in_ruleAction8489);
                    lv_asserts_15=rulePreConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_15, "PreConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3697:3: (lv_asserts_16= rulePostConditionAssert )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==62) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3700:6: lv_asserts_16= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getAssertsPostConditionAssertParserRuleCall_11_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleAction8528);
                    lv_asserts_16=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_16, "PostConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleAction8542); 

                    createLeafNode(grammarAccess.getActionAccess().getLeftCurlyBracketKeyword_12(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3722:1: (lv_resultGroups_18= ruleResult )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==38||LA77_0==49||LA77_0==58||(LA77_0>=75 && LA77_0<=76)) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3725:6: lv_resultGroups_18= ruleResult
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getResultGroupsResultParserRuleCall_13_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleResult_in_ruleAction8576);
            	    lv_resultGroups_18=ruleResult();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "resultGroups", lv_resultGroups_18, "Result", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleAction8590); 

                    createLeafNode(grammarAccess.getActionAccess().getRightCurlyBracketKeyword_14(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAction


    // $ANTLR start entryRuleParameter
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3754:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3754:51: (iv_ruleParameter= ruleParameter EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3755:2: iv_ruleParameter= ruleParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getParameterRule(), currentNode); 
            pushFollow(FOLLOW_ruleParameter_in_entryRuleParameter8623);
            iv_ruleParameter=ruleParameter();
            _fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameter8633); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleParameter


    // $ANTLR start ruleParameter
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3762:1: ruleParameter returns [EObject current=null] : ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;

        EObject lv_value_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3767:6: ( ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3768:1: ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3768:1: ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3768:2: (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3768:2: (lv_name_0= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3771:6: lv_name_0= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getParameterAccess().getNameQualifiedNameParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleParameter8692);
            lv_name_0=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getParameterRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_0, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,20,FOLLOW_20_in_ruleParameter8705); 

                    createLeafNode(grammarAccess.getParameterAccess().getEqualsSignKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3793:1: (lv_value_2= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3796:6: lv_value_2= ruleExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getParameterAccess().getValueExpressionParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleParameter8739);
            lv_value_2=ruleExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getParameterRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_2, "Expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleParameter


    // $ANTLR start entryRuleResult
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3821:1: entryRuleResult returns [EObject current=null] : iv_ruleResult= ruleResult EOF ;
    public final EObject entryRuleResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResult = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3821:48: (iv_ruleResult= ruleResult EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3822:2: iv_ruleResult= ruleResult EOF
            {
             currentNode = createCompositeNode(grammarAccess.getResultRule(), currentNode); 
            pushFollow(FOLLOW_ruleResult_in_entryRuleResult8776);
            iv_ruleResult=ruleResult();
            _fsp--;

             current =iv_ruleResult; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResult8786); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleResult


    // $ANTLR start ruleResult
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3829:1: ruleResult returns [EObject current=null] : ( (lv_result_0= ruleResultPart ) (lv_group_1= ruleResultGroup )? ) ;
    public final EObject ruleResult() throws RecognitionException {
        EObject current = null;

        EObject lv_result_0 = null;

        EObject lv_group_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3834:6: ( ( (lv_result_0= ruleResultPart ) (lv_group_1= ruleResultGroup )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3835:1: ( (lv_result_0= ruleResultPart ) (lv_group_1= ruleResultGroup )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3835:1: ( (lv_result_0= ruleResultPart ) (lv_group_1= ruleResultGroup )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3835:2: (lv_result_0= ruleResultPart ) (lv_group_1= ruleResultGroup )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3835:2: (lv_result_0= ruleResultPart )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3838:6: lv_result_0= ruleResultPart
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getResultAccess().getResultResultPartParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleResultPart_in_ruleResult8845);
            lv_result_0=ruleResultPart();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getResultRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		add(current, "result", lv_result_0, "ResultPart", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3856:2: (lv_group_1= ruleResultGroup )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==53) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3859:6: lv_group_1= ruleResultGroup
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getResultAccess().getGroupResultGroupParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleResultGroup_in_ruleResult8883);
                    lv_group_1=ruleResultGroup();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getResultRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "group", lv_group_1, "ResultGroup", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleResult


    // $ANTLR start entryRuleResultPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3884:1: entryRuleResultPart returns [EObject current=null] : iv_ruleResultPart= ruleResultPart EOF ;
    public final EObject entryRuleResultPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResultPart = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3884:52: (iv_ruleResultPart= ruleResultPart EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3885:2: iv_ruleResultPart= ruleResultPart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getResultPartRule(), currentNode); 
            pushFollow(FOLLOW_ruleResultPart_in_entryRuleResultPart8921);
            iv_ruleResultPart=ruleResultPart();
            _fsp--;

             current =iv_ruleResultPart; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResultPart8931); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleResultPart


    // $ANTLR start ruleResultPart
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3892:1: ruleResultPart returns [EObject current=null] : (this_BasicResult_0= ruleBasicResult | ( '{' (lv_result_2= ruleBasicResult )* '}' ) ) ;
    public final EObject ruleResultPart() throws RecognitionException {
        EObject current = null;

        EObject this_BasicResult_0 = null;

        EObject lv_result_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3897:6: ( (this_BasicResult_0= ruleBasicResult | ( '{' (lv_result_2= ruleBasicResult )* '}' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3898:1: (this_BasicResult_0= ruleBasicResult | ( '{' (lv_result_2= ruleBasicResult )* '}' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3898:1: (this_BasicResult_0= ruleBasicResult | ( '{' (lv_result_2= ruleBasicResult )* '}' ) )
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==49||LA80_0==58||(LA80_0>=75 && LA80_0<=76)) ) {
                alt80=1;
            }
            else if ( (LA80_0==38) ) {
                alt80=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("3898:1: (this_BasicResult_0= ruleBasicResult | ( '{' (lv_result_2= ruleBasicResult )* '}' ) )", 80, 0, input);

                throw nvae;
            }
            switch (alt80) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3899:5: this_BasicResult_0= ruleBasicResult
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getResultPartAccess().getBasicResultParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleBasicResult_in_ruleResultPart8978);
                    this_BasicResult_0=ruleBasicResult();
                    _fsp--;

                     
                            current = this_BasicResult_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3908:6: ( '{' (lv_result_2= ruleBasicResult )* '}' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3908:6: ( '{' (lv_result_2= ruleBasicResult )* '}' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3908:7: '{' (lv_result_2= ruleBasicResult )* '}'
                    {
                    match(input,38,FOLLOW_38_in_ruleResultPart8993); 

                            createLeafNode(grammarAccess.getResultPartAccess().getLeftCurlyBracketKeyword_1_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3912:1: (lv_result_2= ruleBasicResult )*
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==49||LA79_0==58||(LA79_0>=75 && LA79_0<=76)) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3915:6: lv_result_2= ruleBasicResult
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getResultPartAccess().getResultBasicResultParserRuleCall_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleBasicResult_in_ruleResultPart9027);
                    	    lv_result_2=ruleBasicResult();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getResultPartRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "result", lv_result_2, "BasicResult", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }
                    	    break;

                    	default :
                    	    break loop79;
                        }
                    } while (true);

                    match(input,40,FOLLOW_40_in_ruleResultPart9041); 

                            createLeafNode(grammarAccess.getResultPartAccess().getRightCurlyBracketKeyword_1_2(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleResultPart


    // $ANTLR start entryRuleBasicResult
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3944:1: entryRuleBasicResult returns [EObject current=null] : iv_ruleBasicResult= ruleBasicResult EOF ;
    public final EObject entryRuleBasicResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBasicResult = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3944:53: (iv_ruleBasicResult= ruleBasicResult EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3945:2: iv_ruleBasicResult= ruleBasicResult EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBasicResultRule(), currentNode); 
            pushFollow(FOLLOW_ruleBasicResult_in_entryRuleBasicResult9075);
            iv_ruleBasicResult=ruleBasicResult();
            _fsp--;

             current =iv_ruleBasicResult; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBasicResult9085); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleBasicResult


    // $ANTLR start ruleBasicResult
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3952:1: ruleBasicResult returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_visibility_2= ruleVisibility )? 'result' (lv_name_4= rulePartName )? (lv_asserts_5= rulePreConditionAssert )? (lv_asserts_6= rulePostConditionAssert )? '{' (lv_paths_8= rulePathGroup )* '}' (lv_closure_10= ruleClosure )? ) ;
    public final EObject ruleBasicResult() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_1 = null;

        Enumerator lv_visibility_2 = null;

        AntlrDatatypeRuleToken lv_name_4 = null;

        EObject lv_asserts_5 = null;

        EObject lv_asserts_6 = null;

        EObject lv_paths_8 = null;

        EObject lv_closure_10 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3957:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_visibility_2= ruleVisibility )? 'result' (lv_name_4= rulePartName )? (lv_asserts_5= rulePreConditionAssert )? (lv_asserts_6= rulePostConditionAssert )? '{' (lv_paths_8= rulePathGroup )* '}' (lv_closure_10= ruleClosure )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3958:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_visibility_2= ruleVisibility )? 'result' (lv_name_4= rulePartName )? (lv_asserts_5= rulePreConditionAssert )? (lv_asserts_6= rulePostConditionAssert )? '{' (lv_paths_8= rulePathGroup )* '}' (lv_closure_10= ruleClosure )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3958:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_visibility_2= ruleVisibility )? 'result' (lv_name_4= rulePartName )? (lv_asserts_5= rulePreConditionAssert )? (lv_asserts_6= rulePostConditionAssert )? '{' (lv_paths_8= rulePathGroup )* '}' (lv_closure_10= ruleClosure )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3958:2: ( 'when' (lv_filter_1= ruleFilter ) )? (lv_visibility_2= ruleVisibility )? 'result' (lv_name_4= rulePartName )? (lv_asserts_5= rulePreConditionAssert )? (lv_asserts_6= rulePostConditionAssert )? '{' (lv_paths_8= rulePathGroup )* '}' (lv_closure_10= ruleClosure )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3958:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==49) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3958:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,49,FOLLOW_49_in_ruleBasicResult9120); 

                            createLeafNode(grammarAccess.getBasicResultAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3962:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3965:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getBasicResultAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_ruleBasicResult9154);
                    lv_filter_1=ruleFilter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getBasicResultRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "filter", lv_filter_1, "Filter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3983:4: (lv_visibility_2= ruleVisibility )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( ((LA82_0>=75 && LA82_0<=76)) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3986:6: lv_visibility_2= ruleVisibility
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getBasicResultAccess().getVisibilityVisibilityEnumRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVisibility_in_ruleBasicResult9194);
                    lv_visibility_2=ruleVisibility();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getBasicResultRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "visibility", lv_visibility_2, "Visibility", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,58,FOLLOW_58_in_ruleBasicResult9208); 

                    createLeafNode(grammarAccess.getBasicResultAccess().getResultKeyword_2(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4008:1: (lv_name_4= rulePartName )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( ((LA83_0>=RULE_ID && LA83_0<=RULE_STRING)) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4011:6: lv_name_4= rulePartName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getBasicResultAccess().getNamePartNameParserRuleCall_3_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePartName_in_ruleBasicResult9242);
                    lv_name_4=rulePartName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getBasicResultRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_4, "PartName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4029:3: (lv_asserts_5= rulePreConditionAssert )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==61) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4032:6: lv_asserts_5= rulePreConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getBasicResultAccess().getAssertsPreConditionAssertParserRuleCall_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePreConditionAssert_in_ruleBasicResult9281);
                    lv_asserts_5=rulePreConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getBasicResultRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_5, "PreConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4050:3: (lv_asserts_6= rulePostConditionAssert )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==62) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4053:6: lv_asserts_6= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getBasicResultAccess().getAssertsPostConditionAssertParserRuleCall_5_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleBasicResult9320);
                    lv_asserts_6=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getBasicResultRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_6, "PostConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleBasicResult9334); 

                    createLeafNode(grammarAccess.getBasicResultAccess().getLeftCurlyBracketKeyword_6(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4075:1: (lv_paths_8= rulePathGroup )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( ((LA86_0>=RULE_ID && LA86_0<=RULE_STRING)||LA86_0==12||(LA86_0>=43 && LA86_0<=45)||(LA86_0>=49 && LA86_0<=50)) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4078:6: lv_paths_8= rulePathGroup
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getBasicResultAccess().getPathsPathGroupParserRuleCall_7_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePathGroup_in_ruleBasicResult9368);
            	    lv_paths_8=rulePathGroup();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getBasicResultRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "paths", lv_paths_8, "PathGroup", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleBasicResult9382); 

                    createLeafNode(grammarAccess.getBasicResultAccess().getRightCurlyBracketKeyword_8(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4100:1: (lv_closure_10= ruleClosure )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==55) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4103:6: lv_closure_10= ruleClosure
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getBasicResultAccess().getClosureClosureParserRuleCall_9_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleClosure_in_ruleBasicResult9416);
                    lv_closure_10=ruleClosure();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getBasicResultRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "closure", lv_closure_10, "Closure", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleBasicResult


    // $ANTLR start entryRuleResultGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4128:1: entryRuleResultGroup returns [EObject current=null] : iv_ruleResultGroup= ruleResultGroup EOF ;
    public final EObject entryRuleResultGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResultGroup = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4128:53: (iv_ruleResultGroup= ruleResultGroup EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4129:2: iv_ruleResultGroup= ruleResultGroup EOF
            {
             currentNode = createCompositeNode(grammarAccess.getResultGroupRule(), currentNode); 
            pushFollow(FOLLOW_ruleResultGroup_in_entryRuleResultGroup9454);
            iv_ruleResultGroup=ruleResultGroup();
            _fsp--;

             current =iv_ruleResultGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResultGroup9464); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleResultGroup


    // $ANTLR start ruleResultGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4136:1: ruleResultGroup returns [EObject current=null] : ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' (lv_closure_6= ruleClosure )? ) ;
    public final EObject ruleResultGroup() throws RecognitionException {
        EObject current = null;

        EObject lv_asserts_1 = null;

        EObject lv_asserts_2 = null;

        EObject lv_prerequisites_4 = null;

        EObject lv_closure_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4141:6: ( ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' (lv_closure_6= ruleClosure )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4142:1: ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' (lv_closure_6= ruleClosure )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4142:1: ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' (lv_closure_6= ruleClosure )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4142:2: 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' (lv_closure_6= ruleClosure )?
            {
            match(input,53,FOLLOW_53_in_ruleResultGroup9498); 

                    createLeafNode(grammarAccess.getResultGroupAccess().getGroupKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4146:1: (lv_asserts_1= rulePreConditionAssert )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==61) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4149:6: lv_asserts_1= rulePreConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getResultGroupAccess().getAssertsPreConditionAssertParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePreConditionAssert_in_ruleResultGroup9532);
                    lv_asserts_1=rulePreConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getResultGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_1, "PreConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4167:3: (lv_asserts_2= rulePostConditionAssert )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==62) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4170:6: lv_asserts_2= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getResultGroupAccess().getAssertsPostConditionAssertParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleResultGroup9571);
                    lv_asserts_2=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getResultGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_2, "PostConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleResultGroup9585); 

                    createLeafNode(grammarAccess.getResultGroupAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4192:1: (lv_prerequisites_4= rulePrerequisite )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( ((LA90_0>=RULE_ID && LA90_0<=RULE_STRING)||LA90_0==38||LA90_0==49||LA90_0==54) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4195:6: lv_prerequisites_4= rulePrerequisite
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getResultGroupAccess().getPrerequisitesPrerequisiteParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePrerequisite_in_ruleResultGroup9619);
            	    lv_prerequisites_4=rulePrerequisite();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getResultGroupRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "prerequisites", lv_prerequisites_4, "Prerequisite", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleResultGroup9633); 

                    createLeafNode(grammarAccess.getResultGroupAccess().getRightCurlyBracketKeyword_5(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4217:1: (lv_closure_6= ruleClosure )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==55) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4220:6: lv_closure_6= ruleClosure
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getResultGroupAccess().getClosureClosureParserRuleCall_6_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleClosure_in_ruleResultGroup9667);
                    lv_closure_6=ruleClosure();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getResultGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "closure", lv_closure_6, "Closure", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleResultGroup


    // $ANTLR start entryRuleRepositoryConfiguration
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4245:1: entryRuleRepositoryConfiguration returns [EObject current=null] : iv_ruleRepositoryConfiguration= ruleRepositoryConfiguration EOF ;
    public final EObject entryRuleRepositoryConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRepositoryConfiguration = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4245:65: (iv_ruleRepositoryConfiguration= ruleRepositoryConfiguration EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4246:2: iv_ruleRepositoryConfiguration= ruleRepositoryConfiguration EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRepositoryConfigurationRule(), currentNode); 
            pushFollow(FOLLOW_ruleRepositoryConfiguration_in_entryRuleRepositoryConfiguration9705);
            iv_ruleRepositoryConfiguration=ruleRepositoryConfiguration();
            _fsp--;

             current =iv_ruleRepositoryConfiguration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRepositoryConfiguration9715); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleRepositoryConfiguration


    // $ANTLR start ruleRepositoryConfiguration
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4253:1: ruleRepositoryConfiguration returns [EObject current=null] : ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' ) ;
    public final EObject ruleRepositoryConfiguration() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_location_0 = null;

        AntlrDatatypeRuleToken lv_resolverClass_2 = null;

        EObject lv_advice_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4258:6: ( ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4259:1: ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4259:1: ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4259:2: ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4259:2: ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==RULE_STRING) ) {
                alt92=1;
            }
            else if ( (LA92_0==59) ) {
                alt92=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4259:2: ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) )", 92, 0, input);

                throw nvae;
            }
            switch (alt92) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4259:3: (lv_location_0= ruleURI )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4259:3: (lv_location_0= ruleURI )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4262:6: lv_location_0= ruleURI
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRepositoryConfigurationAccess().getLocationURIParserRuleCall_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleURI_in_ruleRepositoryConfiguration9775);
                    lv_location_0=ruleURI();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRepositoryConfigurationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "location", lv_location_0, "URI", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4281:6: ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4281:6: ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4281:7: 'resolver' (lv_resolverClass_2= ruleQualifiedName )
                    {
                    match(input,59,FOLLOW_59_in_ruleRepositoryConfiguration9795); 

                            createLeafNode(grammarAccess.getRepositoryConfigurationAccess().getResolverKeyword_0_1_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4285:1: (lv_resolverClass_2= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4288:6: lv_resolverClass_2= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRepositoryConfigurationAccess().getResolverClassQualifiedNameParserRuleCall_0_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleRepositoryConfiguration9829);
                    lv_resolverClass_2=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRepositoryConfigurationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "resolverClass", lv_resolverClass_2, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4306:4: (lv_advice_3= ruleCompoundAdvice )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==38) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4309:6: lv_advice_3= ruleCompoundAdvice
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRepositoryConfigurationAccess().getAdviceCompoundAdviceParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleRepositoryConfiguration9869);
                    lv_advice_3=ruleCompoundAdvice();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRepositoryConfigurationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "advice", lv_advice_3, "CompoundAdvice", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,33,FOLLOW_33_in_ruleRepositoryConfiguration9883); 

                    createLeafNode(grammarAccess.getRepositoryConfigurationAccess().getSemicolonKeyword_2(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleRepositoryConfiguration


    // $ANTLR start entryRuleURI
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4338:1: entryRuleURI returns [String current=null] : iv_ruleURI= ruleURI EOF ;
    public final String entryRuleURI() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleURI = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4338:44: (iv_ruleURI= ruleURI EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4339:2: iv_ruleURI= ruleURI EOF
            {
             currentNode = createCompositeNode(grammarAccess.getURIRule(), currentNode); 
            pushFollow(FOLLOW_ruleURI_in_entryRuleURI9917);
            iv_ruleURI=ruleURI();
            _fsp--;

             current =iv_ruleURI.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleURI9928); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleURI


    // $ANTLR start ruleURI
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4346:1: ruleURI returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleURI() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4352:6: (this_STRING_0= RULE_STRING )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4353:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleURI9967); 

            		current.merge(this_STRING_0);
                
             
                createLeafNode(grammarAccess.getURIAccess().getSTRINGTerminalRuleCall(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleURI


    // $ANTLR start entryRuleNamedAdvice
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4368:1: entryRuleNamedAdvice returns [EObject current=null] : iv_ruleNamedAdvice= ruleNamedAdvice EOF ;
    public final EObject entryRuleNamedAdvice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamedAdvice = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4368:53: (iv_ruleNamedAdvice= ruleNamedAdvice EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4369:2: iv_ruleNamedAdvice= ruleNamedAdvice EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamedAdviceRule(), currentNode); 
            pushFollow(FOLLOW_ruleNamedAdvice_in_entryRuleNamedAdvice10009);
            iv_ruleNamedAdvice=ruleNamedAdvice();
            _fsp--;

             current =iv_ruleNamedAdvice; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamedAdvice10019); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleNamedAdvice


    // $ANTLR start ruleNamedAdvice
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4376:1: ruleNamedAdvice returns [EObject current=null] : ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) ) ;
    public final EObject ruleNamedAdvice() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;

        EObject lv_advice_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4381:6: ( ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4382:1: ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4382:1: ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4382:2: (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4382:2: (lv_name_0= ruleQualifiedName )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==RULE_ID) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4385:6: lv_name_0= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNamedAdviceAccess().getNameQualifiedNameParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleNamedAdvice10078);
                    lv_name_0=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNamedAdviceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_0, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4403:3: (lv_advice_1= ruleCompoundAdvice )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4406:6: lv_advice_1= ruleCompoundAdvice
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNamedAdviceAccess().getAdviceCompoundAdviceParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleNamedAdvice10117);
            lv_advice_1=ruleCompoundAdvice();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNamedAdviceRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "advice", lv_advice_1, "CompoundAdvice", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleNamedAdvice


    // $ANTLR start entryRuleCompoundAdvice
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4431:1: entryRuleCompoundAdvice returns [EObject current=null] : iv_ruleCompoundAdvice= ruleCompoundAdvice EOF ;
    public final EObject entryRuleCompoundAdvice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompoundAdvice = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4431:56: (iv_ruleCompoundAdvice= ruleCompoundAdvice EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4432:2: iv_ruleCompoundAdvice= ruleCompoundAdvice EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCompoundAdviceRule(), currentNode); 
            pushFollow(FOLLOW_ruleCompoundAdvice_in_entryRuleCompoundAdvice10154);
            iv_ruleCompoundAdvice=ruleCompoundAdvice();
            _fsp--;

             current =iv_ruleCompoundAdvice; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCompoundAdvice10164); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCompoundAdvice


    // $ANTLR start ruleCompoundAdvice
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4439:1: ruleCompoundAdvice returns [EObject current=null] : ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' ) ;
    public final EObject ruleCompoundAdvice() throws RecognitionException {
        EObject current = null;

        EObject lv_advice_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4444:6: ( ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4445:1: ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4445:1: ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4445:2: '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}'
            {
            match(input,38,FOLLOW_38_in_ruleCompoundAdvice10198); 

                    createLeafNode(grammarAccess.getCompoundAdviceAccess().getLeftCurlyBracketKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4449:1: ( (lv_advice_1= ruleAdviceStatement ) ';' )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==RULE_ID||(LA95_0>=11 && LA95_0<=12)||LA95_0==25||LA95_0==60) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4449:2: (lv_advice_1= ruleAdviceStatement ) ';'
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4449:2: (lv_advice_1= ruleAdviceStatement )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4452:6: lv_advice_1= ruleAdviceStatement
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getCompoundAdviceAccess().getAdviceAdviceStatementParserRuleCall_1_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdviceStatement_in_ruleCompoundAdvice10233);
            	    lv_advice_1=ruleAdviceStatement();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getCompoundAdviceRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "advice", lv_advice_1, "AdviceStatement", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,33,FOLLOW_33_in_ruleCompoundAdvice10246); 

            	            createLeafNode(grammarAccess.getCompoundAdviceAccess().getSemicolonKeyword_1_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop95;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_ruleCompoundAdvice10257); 

                    createLeafNode(grammarAccess.getCompoundAdviceAccess().getRightCurlyBracketKeyword_2(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCompoundAdvice


    // $ANTLR start entryRuleAdviceStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4485:1: entryRuleAdviceStatement returns [EObject current=null] : iv_ruleAdviceStatement= ruleAdviceStatement EOF ;
    public final EObject entryRuleAdviceStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdviceStatement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4485:57: (iv_ruleAdviceStatement= ruleAdviceStatement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4486:2: iv_ruleAdviceStatement= ruleAdviceStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdviceStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdviceStatement_in_entryRuleAdviceStatement10290);
            iv_ruleAdviceStatement=ruleAdviceStatement();
            _fsp--;

             current =iv_ruleAdviceStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdviceStatement10300); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAdviceStatement


    // $ANTLR start ruleAdviceStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4493:1: ruleAdviceStatement returns [EObject current=null] : ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) ) ;
    public final EObject ruleAdviceStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_path_0 = null;

        EObject lv_value_2 = null;

        EObject lv_advice_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4498:6: ( ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4499:1: ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4499:1: ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4499:2: (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4499:2: (lv_path_0= ruleAdvicePath )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4502:6: lv_path_0= ruleAdvicePath
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAdviceStatementAccess().getPathAdvicePathParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleAdvicePath_in_ruleAdviceStatement10359);
            lv_path_0=ruleAdvicePath();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAdviceStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "path", lv_path_0, "AdvicePath", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4520:2: ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) )
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==20) ) {
                alt96=1;
            }
            else if ( (LA96_0==38) ) {
                alt96=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4520:2: ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) )", 96, 0, input);

                throw nvae;
            }
            switch (alt96) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4520:3: ( '=' (lv_value_2= ruleExpression ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4520:3: ( '=' (lv_value_2= ruleExpression ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4520:4: '=' (lv_value_2= ruleExpression )
                    {
                    match(input,20,FOLLOW_20_in_ruleAdviceStatement10374); 

                            createLeafNode(grammarAccess.getAdviceStatementAccess().getEqualsSignKeyword_1_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4524:1: (lv_value_2= ruleExpression )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4527:6: lv_value_2= ruleExpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAdviceStatementAccess().getValueExpressionParserRuleCall_1_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleAdviceStatement10408);
                    lv_value_2=ruleExpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAdviceStatementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "value", lv_value_2, "Expression", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4546:6: (lv_advice_3= ruleCompoundAdvice )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4546:6: (lv_advice_3= ruleCompoundAdvice )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4549:6: lv_advice_3= ruleCompoundAdvice
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAdviceStatementAccess().getAdviceCompoundAdviceParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleAdviceStatement10453);
                    lv_advice_3=ruleCompoundAdvice();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAdviceStatementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "advice", lv_advice_3, "CompoundAdvice", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAdviceStatement


    // $ANTLR start entryRuleAdvicePath
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4574:1: entryRuleAdvicePath returns [EObject current=null] : iv_ruleAdvicePath= ruleAdvicePath EOF ;
    public final EObject entryRuleAdvicePath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePath = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4574:52: (iv_ruleAdvicePath= ruleAdvicePath EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4575:2: iv_ruleAdvicePath= ruleAdvicePath EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePath_in_entryRuleAdvicePath10491);
            iv_ruleAdvicePath=ruleAdvicePath();
            _fsp--;

             current =iv_ruleAdvicePath; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePath10501); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAdvicePath


    // $ANTLR start ruleAdvicePath
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4582:1: ruleAdvicePath returns [EObject current=null] : ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* ) ;
    public final EObject ruleAdvicePath() throws RecognitionException {
        EObject current = null;

        EObject lv_pathElements_0 = null;

        EObject lv_pathElements_1 = null;

        EObject lv_pathElements_2 = null;

        EObject lv_pathElement_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4587:6: ( ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4588:1: ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4588:1: ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4588:2: (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )*
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4588:2: (lv_pathElements_0= ruleAdvicePathSeparator )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==12) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4591:6: lv_pathElements_0= ruleAdvicePathSeparator
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementsAdvicePathSeparatorParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10560);
                    lv_pathElements_0=ruleAdvicePathSeparator();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAdvicePathRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "pathElements", lv_pathElements_0, "AdvicePathSeparator", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4609:3: (lv_pathElements_1= ruleAdvicePathElement )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4612:6: lv_pathElements_1= ruleAdvicePathElement
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementsAdvicePathElementParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10599);
            lv_pathElements_1=ruleAdvicePathElement();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAdvicePathRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		add(current, "pathElements", lv_pathElements_1, "AdvicePathElement", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4630:2: ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==12) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4630:3: (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4630:3: (lv_pathElements_2= ruleAdvicePathSeparator )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4633:6: lv_pathElements_2= ruleAdvicePathSeparator
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementsAdvicePathSeparatorParserRuleCall_2_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10638);
            	    lv_pathElements_2=ruleAdvicePathSeparator();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAdvicePathRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "pathElements", lv_pathElements_2, "AdvicePathSeparator", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4651:2: (lv_pathElement_3= ruleAdvicePathElement )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4654:6: lv_pathElement_3= ruleAdvicePathElement
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementAdvicePathElementParserRuleCall_2_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10676);
            	    lv_pathElement_3=ruleAdvicePathElement();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAdvicePathRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "pathElement", lv_pathElement_3, "AdvicePathElement", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAdvicePath


    // $ANTLR start entryRuleAdvicePathSeparator
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4679:1: entryRuleAdvicePathSeparator returns [EObject current=null] : iv_ruleAdvicePathSeparator= ruleAdvicePathSeparator EOF ;
    public final EObject entryRuleAdvicePathSeparator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePathSeparator = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4679:61: (iv_ruleAdvicePathSeparator= ruleAdvicePathSeparator EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4680:2: iv_ruleAdvicePathSeparator= ruleAdvicePathSeparator EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathSeparatorRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePathSeparator_in_entryRuleAdvicePathSeparator10715);
            iv_ruleAdvicePathSeparator=ruleAdvicePathSeparator();
            _fsp--;

             current =iv_ruleAdvicePathSeparator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePathSeparator10725); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAdvicePathSeparator


    // $ANTLR start ruleAdvicePathSeparator
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4687:1: ruleAdvicePathSeparator returns [EObject current=null] : this_AdvicePathChildren_0= ruleAdvicePathChildren ;
    public final EObject ruleAdvicePathSeparator() throws RecognitionException {
        EObject current = null;

        EObject this_AdvicePathChildren_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4692:6: (this_AdvicePathChildren_0= ruleAdvicePathChildren )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4694:5: this_AdvicePathChildren_0= ruleAdvicePathChildren
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAdvicePathSeparatorAccess().getAdvicePathChildrenParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleAdvicePathChildren_in_ruleAdvicePathSeparator10771);
            this_AdvicePathChildren_0=ruleAdvicePathChildren();
            _fsp--;

             
                    current = this_AdvicePathChildren_0; 
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAdvicePathSeparator


    // $ANTLR start entryRuleAdvicePathChildren
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4709:1: entryRuleAdvicePathChildren returns [EObject current=null] : iv_ruleAdvicePathChildren= ruleAdvicePathChildren EOF ;
    public final EObject entryRuleAdvicePathChildren() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePathChildren = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4709:60: (iv_ruleAdvicePathChildren= ruleAdvicePathChildren EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4710:2: iv_ruleAdvicePathChildren= ruleAdvicePathChildren EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathChildrenRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePathChildren_in_entryRuleAdvicePathChildren10802);
            iv_ruleAdvicePathChildren=ruleAdvicePathChildren();
            _fsp--;

             current =iv_ruleAdvicePathChildren; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePathChildren10812); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAdvicePathChildren


    // $ANTLR start ruleAdvicePathChildren
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4717:1: ruleAdvicePathChildren returns [EObject current=null] : ( () '/' ) ;
    public final EObject ruleAdvicePathChildren() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4722:6: ( ( () '/' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4723:1: ( () '/' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4723:1: ( () '/' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4723:2: () '/'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4723:2: ()
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4724:5: 
            {
             
                    temp=factory.create(grammarAccess.getAdvicePathChildrenAccess().getAdvicePathChildrenAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getAdvicePathChildrenAccess().getAdvicePathChildrenAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,12,FOLLOW_12_in_ruleAdvicePathChildren10855); 

                    createLeafNode(grammarAccess.getAdvicePathChildrenAccess().getSolidusKeyword_1(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAdvicePathChildren


    // $ANTLR start entryRuleAdvicePathElement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4745:1: entryRuleAdvicePathElement returns [EObject current=null] : iv_ruleAdvicePathElement= ruleAdvicePathElement EOF ;
    public final EObject entryRuleAdvicePathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePathElement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4745:59: (iv_ruleAdvicePathElement= ruleAdvicePathElement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4746:2: iv_ruleAdvicePathElement= ruleAdvicePathElement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathElementRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePathElement_in_entryRuleAdvicePathElement10888);
            iv_ruleAdvicePathElement=ruleAdvicePathElement();
            _fsp--;

             current =iv_ruleAdvicePathElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePathElement10898); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAdvicePathElement


    // $ANTLR start ruleAdvicePathElement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4753:1: ruleAdvicePathElement returns [EObject current=null] : ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) ) ;
    public final EObject ruleAdvicePathElement() throws RecognitionException {
        EObject current = null;

        Token lv_node_0=null;
        Token lv_node_4=null;
        EObject lv_predicate_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4758:6: ( ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4759:1: ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4759:1: ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==RULE_ID||LA101_0==11||LA101_0==25) ) {
                alt101=1;
            }
            else if ( (LA101_0==60) ) {
                alt101=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4759:1: ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) )", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4759:2: ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4759:2: ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4759:3: (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )?
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4759:3: (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4761:6: lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4761:16: ( ruleQualifiedName | ruleWildcardNode | '.' )
                    int alt99=3;
                    switch ( input.LA(1) ) {
                    case RULE_ID:
                        {
                        alt99=1;
                        }
                        break;
                    case 25:
                        {
                        alt99=2;
                        }
                        break;
                    case 11:
                        {
                        alt99=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("4761:16: ( ruleQualifiedName | ruleWildcardNode | '.' )", 99, 0, input);

                        throw nvae;
                    }

                    switch (alt99) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4761:18: ruleQualifiedName
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getAdvicePathElementAccess().getNodeQualifiedNameParserRuleCall_0_0_0_0(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleQualifiedName_in_ruleAdvicePathElement10950);
                            ruleQualifiedName();
                            _fsp--;

                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4768:7: ruleWildcardNode
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getAdvicePathElementAccess().getNodeWildcardNodeParserRuleCall_0_0_0_1(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleWildcardNode_in_ruleAdvicePathElement10964);
                            ruleWildcardNode();
                            _fsp--;

                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;
                        case 3 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4775:6: '.'
                            {
                            match(input,11,FOLLOW_11_in_ruleAdvicePathElement10974); 

                                    createLeafNode(grammarAccess.getAdvicePathElementAccess().getNodeFullStopKeyword_0_0_0_2(), "node"); 
                                

                            }
                            break;

                    }


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAdvicePathElementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "node", /* lv_node_0 */ input.LT(-1), null, lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4795:2: ( '[' (lv_predicate_2= ruleExpression ) ']' )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==26) ) {
                        alt100=1;
                    }
                    switch (alt100) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4795:3: '[' (lv_predicate_2= ruleExpression ) ']'
                            {
                            match(input,26,FOLLOW_26_in_ruleAdvicePathElement10999); 

                                    createLeafNode(grammarAccess.getAdvicePathElementAccess().getLeftSquareBracketKeyword_0_1_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4799:1: (lv_predicate_2= ruleExpression )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4802:6: lv_predicate_2= ruleExpression
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getAdvicePathElementAccess().getPredicateExpressionParserRuleCall_0_1_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleExpression_in_ruleAdvicePathElement11033);
                            lv_predicate_2=ruleExpression();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getAdvicePathElementRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "predicate", lv_predicate_2, "Expression", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }

                            match(input,29,FOLLOW_29_in_ruleAdvicePathElement11046); 

                                    createLeafNode(grammarAccess.getAdvicePathElementAccess().getRightSquareBracketKeyword_0_1_2(), null); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4825:6: (lv_node_4= '..' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4825:6: (lv_node_4= '..' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4827:6: lv_node_4= '..'
                    {
                    lv_node_4=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_ruleAdvicePathElement11076); 

                            createLeafNode(grammarAccess.getAdvicePathElementAccess().getNodeFullStopFullStopKeyword_1_0(), "node"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAdvicePathElementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "node", /* lv_node_4 */ input.LT(-1), "..", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAdvicePathElement


    // $ANTLR start entryRuleWildcardNode
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4853:1: entryRuleWildcardNode returns [String current=null] : iv_ruleWildcardNode= ruleWildcardNode EOF ;
    public final String entryRuleWildcardNode() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleWildcardNode = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4853:53: (iv_ruleWildcardNode= ruleWildcardNode EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4854:2: iv_ruleWildcardNode= ruleWildcardNode EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWildcardNodeRule(), currentNode); 
            pushFollow(FOLLOW_ruleWildcardNode_in_entryRuleWildcardNode11123);
            iv_ruleWildcardNode=ruleWildcardNode();
            _fsp--;

             current =iv_ruleWildcardNode.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWildcardNode11134); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleWildcardNode


    // $ANTLR start ruleWildcardNode
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4861:1: ruleWildcardNode returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '?' (kw= '?' )? ) ;
    public final AntlrDatatypeRuleToken ruleWildcardNode() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4867:6: ( (kw= '?' (kw= '?' )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4868:1: (kw= '?' (kw= '?' )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4868:1: (kw= '?' (kw= '?' )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4869:2: kw= '?' (kw= '?' )?
            {
            kw=(Token)input.LT(1);
            match(input,25,FOLLOW_25_in_ruleWildcardNode11172); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getWildcardNodeAccess().getQuestionMarkKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4874:1: (kw= '?' )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==25) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4875:2: kw= '?'
                    {
                    kw=(Token)input.LT(1);
                    match(input,25,FOLLOW_25_in_ruleWildcardNode11186); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getWildcardNodeAccess().getQuestionMarkKeyword_1(), null); 
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleWildcardNode


    // $ANTLR start entryRuleFilter
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4888:1: entryRuleFilter returns [EObject current=null] : iv_ruleFilter= ruleFilter EOF ;
    public final EObject entryRuleFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFilter = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4888:48: (iv_ruleFilter= ruleFilter EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4889:2: iv_ruleFilter= ruleFilter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFilterRule(), currentNode); 
            pushFollow(FOLLOW_ruleFilter_in_entryRuleFilter11226);
            iv_ruleFilter=ruleFilter();
            _fsp--;

             current =iv_ruleFilter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFilter11236); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleFilter


    // $ANTLR start ruleFilter
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4896:1: ruleFilter returns [EObject current=null] : ( '(' (lv_predicate_1= ruleExpression ) ')' ) ;
    public final EObject ruleFilter() throws RecognitionException {
        EObject current = null;

        EObject lv_predicate_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4901:6: ( ( '(' (lv_predicate_1= ruleExpression ) ')' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4902:1: ( '(' (lv_predicate_1= ruleExpression ) ')' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4902:1: ( '(' (lv_predicate_1= ruleExpression ) ')' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4902:2: '(' (lv_predicate_1= ruleExpression ) ')'
            {
            match(input,27,FOLLOW_27_in_ruleFilter11270); 

                    createLeafNode(grammarAccess.getFilterAccess().getLeftParenthesisKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4906:1: (lv_predicate_1= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4909:6: lv_predicate_1= ruleExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFilterAccess().getPredicateExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleFilter11304);
            lv_predicate_1=ruleExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFilterRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "predicate", lv_predicate_1, "Expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,30,FOLLOW_30_in_ruleFilter11317); 

                    createLeafNode(grammarAccess.getFilterAccess().getRightParenthesisKeyword_2(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleFilter


    // $ANTLR start entryRulePreConditionAssert
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4938:1: entryRulePreConditionAssert returns [EObject current=null] : iv_rulePreConditionAssert= rulePreConditionAssert EOF ;
    public final EObject entryRulePreConditionAssert() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreConditionAssert = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4938:60: (iv_rulePreConditionAssert= rulePreConditionAssert EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4939:2: iv_rulePreConditionAssert= rulePreConditionAssert EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPreConditionAssertRule(), currentNode); 
            pushFollow(FOLLOW_rulePreConditionAssert_in_entryRulePreConditionAssert11350);
            iv_rulePreConditionAssert=rulePreConditionAssert();
            _fsp--;

             current =iv_rulePreConditionAssert; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePreConditionAssert11360); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePreConditionAssert


    // $ANTLR start rulePreConditionAssert
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4946:1: rulePreConditionAssert returns [EObject current=null] : ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) ;
    public final EObject rulePreConditionAssert() throws RecognitionException {
        EObject current = null;

        Token lv_scope_0=null;
        EObject lv_asserts_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4951:6: ( ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4952:1: ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4952:1: ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4952:2: (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4952:2: (lv_scope_0= 'precondition' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4954:6: lv_scope_0= 'precondition'
            {
            lv_scope_0=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_rulePreConditionAssert11406); 

                    createLeafNode(grammarAccess.getPreConditionAssertAccess().getScopePreconditionKeyword_0_0(), "scope"); 
                

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPreConditionAssertRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "scope", /* lv_scope_0 */ input.LT(-1), "precondition", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,38,FOLLOW_38_in_rulePreConditionAssert11428); 

                    createLeafNode(grammarAccess.getPreConditionAssertAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4977:1: (lv_asserts_2= ruleAssertionExpression )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==63) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4980:6: lv_asserts_2= ruleAssertionExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getPreConditionAssertAccess().getAssertsAssertionExpressionParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAssertionExpression_in_rulePreConditionAssert11462);
            	    lv_asserts_2=ruleAssertionExpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getPreConditionAssertRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "asserts", lv_asserts_2, "AssertionExpression", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_rulePreConditionAssert11476); 

                    createLeafNode(grammarAccess.getPreConditionAssertAccess().getRightCurlyBracketKeyword_3(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePreConditionAssert


    // $ANTLR start entryRulePostConditionAssert
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5009:1: entryRulePostConditionAssert returns [EObject current=null] : iv_rulePostConditionAssert= rulePostConditionAssert EOF ;
    public final EObject entryRulePostConditionAssert() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePostConditionAssert = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5009:61: (iv_rulePostConditionAssert= rulePostConditionAssert EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5010:2: iv_rulePostConditionAssert= rulePostConditionAssert EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPostConditionAssertRule(), currentNode); 
            pushFollow(FOLLOW_rulePostConditionAssert_in_entryRulePostConditionAssert11509);
            iv_rulePostConditionAssert=rulePostConditionAssert();
            _fsp--;

             current =iv_rulePostConditionAssert; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePostConditionAssert11519); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePostConditionAssert


    // $ANTLR start rulePostConditionAssert
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5017:1: rulePostConditionAssert returns [EObject current=null] : ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) ;
    public final EObject rulePostConditionAssert() throws RecognitionException {
        EObject current = null;

        Token lv_scope_0=null;
        EObject lv_asserts_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5022:6: ( ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5023:1: ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5023:1: ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5023:2: (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5023:2: (lv_scope_0= 'postcondition' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5025:6: lv_scope_0= 'postcondition'
            {
            lv_scope_0=(Token)input.LT(1);
            match(input,62,FOLLOW_62_in_rulePostConditionAssert11565); 

                    createLeafNode(grammarAccess.getPostConditionAssertAccess().getScopePostconditionKeyword_0_0(), "scope"); 
                

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPostConditionAssertRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "scope", /* lv_scope_0 */ input.LT(-1), "postcondition", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,38,FOLLOW_38_in_rulePostConditionAssert11587); 

                    createLeafNode(grammarAccess.getPostConditionAssertAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5048:1: (lv_asserts_2= ruleAssertionExpression )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==63) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5051:6: lv_asserts_2= ruleAssertionExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getPostConditionAssertAccess().getAssertsAssertionExpressionParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAssertionExpression_in_rulePostConditionAssert11621);
            	    lv_asserts_2=ruleAssertionExpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getPostConditionAssertRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "asserts", lv_asserts_2, "AssertionExpression", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);

            match(input,40,FOLLOW_40_in_rulePostConditionAssert11635); 

                    createLeafNode(grammarAccess.getPostConditionAssertAccess().getRightCurlyBracketKeyword_3(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePostConditionAssert


    // $ANTLR start entryRuleAssertionExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5080:1: entryRuleAssertionExpression returns [EObject current=null] : iv_ruleAssertionExpression= ruleAssertionExpression EOF ;
    public final EObject entryRuleAssertionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssertionExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5080:61: (iv_ruleAssertionExpression= ruleAssertionExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5081:2: iv_ruleAssertionExpression= ruleAssertionExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAssertionExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAssertionExpression_in_entryRuleAssertionExpression11668);
            iv_ruleAssertionExpression=ruleAssertionExpression();
            _fsp--;

             current =iv_ruleAssertionExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAssertionExpression11678); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAssertionExpression


    // $ANTLR start ruleAssertionExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5088:1: ruleAssertionExpression returns [EObject current=null] : ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' ) ;
    public final EObject ruleAssertionExpression() throws RecognitionException {
        EObject current = null;

        Token lv_message_3=null;
        EObject lv_expr_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5093:6: ( ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5094:1: ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5094:1: ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5094:2: 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';'
            {
            match(input,63,FOLLOW_63_in_ruleAssertionExpression11712); 

                    createLeafNode(grammarAccess.getAssertionExpressionAccess().getAssertKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5098:1: (lv_expr_1= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5101:6: lv_expr_1= ruleExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAssertionExpressionAccess().getExprExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleAssertionExpression11746);
            lv_expr_1=ruleExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAssertionExpressionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "expr", lv_expr_1, "Expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5119:2: ( ',' (lv_message_3= RULE_STRING ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==28) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5119:3: ',' (lv_message_3= RULE_STRING )
                    {
                    match(input,28,FOLLOW_28_in_ruleAssertionExpression11760); 

                            createLeafNode(grammarAccess.getAssertionExpressionAccess().getCommaKeyword_2_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5123:1: (lv_message_3= RULE_STRING )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5125:6: lv_message_3= RULE_STRING
                    {
                    lv_message_3=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleAssertionExpression11782); 

                    		createLeafNode(grammarAccess.getAssertionExpressionAccess().getMessageSTRINGTerminalRuleCall_2_1_0(), "message"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAssertionExpressionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "message", lv_message_3, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,33,FOLLOW_33_in_ruleAssertionExpression11801); 

                    createLeafNode(grammarAccess.getAssertionExpressionAccess().getSemicolonKeyword_3(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAssertionExpression


    // $ANTLR start entryRuleExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5156:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5156:52: (iv_ruleExpression= ruleExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5157:2: iv_ruleExpression= ruleExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression11836);
            iv_ruleExpression=ruleExpression();
            _fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression11846); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleExpression


    // $ANTLR start ruleExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5164:1: ruleExpression returns [EObject current=null] : this_ValueExpression_0= ruleValueExpression ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ValueExpression_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5169:6: (this_ValueExpression_0= ruleValueExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5171:5: this_ValueExpression_0= ruleValueExpression
            {
             
                    currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getValueExpressionParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleValueExpression_in_ruleExpression11892);
            this_ValueExpression_0=ruleValueExpression();
            _fsp--;

             
                    current = this_ValueExpression_0; 
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleExpression


    // $ANTLR start entryRuleValueExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5186:1: entryRuleValueExpression returns [EObject current=null] : iv_ruleValueExpression= ruleValueExpression EOF ;
    public final EObject entryRuleValueExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5186:57: (iv_ruleValueExpression= ruleValueExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5187:2: iv_ruleValueExpression= ruleValueExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getValueExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleValueExpression_in_entryRuleValueExpression11923);
            iv_ruleValueExpression=ruleValueExpression();
            _fsp--;

             current =iv_ruleValueExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleValueExpression11933); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleValueExpression


    // $ANTLR start ruleValueExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5194:1: ruleValueExpression returns [EObject current=null] : (lv_value_0= ruleOrExpression ) ;
    public final EObject ruleValueExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_value_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5199:6: ( (lv_value_0= ruleOrExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5200:1: (lv_value_0= ruleOrExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5200:1: (lv_value_0= ruleOrExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5203:6: lv_value_0= ruleOrExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getValueExpressionAccess().getValueOrExpressionParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleOrExpression_in_ruleValueExpression11991);
            lv_value_0=ruleOrExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getValueExpressionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_0, "OrExpression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleValueExpression


    // $ANTLR start entryRuleOrExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5228:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5228:54: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5229:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getOrExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression12027);
            iv_ruleOrExpression=ruleOrExpression();
            _fsp--;

             current =iv_ruleOrExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression12037); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleOrExpression


    // $ANTLR start ruleOrExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5236:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5241:6: ( (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5242:1: (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5242:1: (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5243:5: this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression12084);
            this_AndExpression_0=ruleAndExpression();
            _fsp--;

             
                    current = this_AndExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5251:1: ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==64) ) {
                    alt106=1;
                }


                switch (alt106) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5251:2: () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5251:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5252:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0().getType().getClassifier());
            	            try {
            	            	factory.set(temp, "left", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5267:2: (lv_operator_2= '||' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5269:6: lv_operator_2= '||'
            	    {
            	    lv_operator_2=(Token)input.LT(1);
            	    match(input,64,FOLLOW_64_in_ruleOrExpression12114); 

            	            createLeafNode(grammarAccess.getOrExpressionAccess().getOperatorVerticalLineVerticalLineKeyword_1_1_0(), "operator"); 
            	        

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getOrExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "operator", /* lv_operator_2 */ input.LT(-1), "||", lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5288:2: (lv_right_3= ruleAndExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5291:6: lv_right_3= ruleAndExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression12161);
            	    lv_right_3=ruleAndExpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getOrExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "right", lv_right_3, "AndExpression", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop106;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleOrExpression


    // $ANTLR start entryRuleAndExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5316:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5316:55: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5317:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAndExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression12200);
            iv_ruleAndExpression=ruleAndExpression();
            _fsp--;

             current =iv_ruleAndExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression12210); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAndExpression


    // $ANTLR start ruleAndExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5324:1: ruleAndExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5329:6: ( (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5330:1: (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5330:1: (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5331:5: this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAndExpressionAccess().getRelationalExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleAndExpression12257);
            this_RelationalExpression_0=ruleRelationalExpression();
            _fsp--;

             
                    current = this_RelationalExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5339:1: ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )*
            loop107:
            do {
                int alt107=2;
                int LA107_0 = input.LA(1);

                if ( (LA107_0==65) ) {
                    alt107=1;
                }


                switch (alt107) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5339:2: () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5339:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5340:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0().getType().getClassifier());
            	            try {
            	            	factory.set(temp, "left", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5355:2: (lv_operator_2= '&&' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5357:6: lv_operator_2= '&&'
            	    {
            	    lv_operator_2=(Token)input.LT(1);
            	    match(input,65,FOLLOW_65_in_ruleAndExpression12287); 

            	            createLeafNode(grammarAccess.getAndExpressionAccess().getOperatorAmpersandAmpersandKeyword_1_1_0(), "operator"); 
            	        

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAndExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "operator", /* lv_operator_2 */ input.LT(-1), "&&", lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5376:2: (lv_right_3= ruleRelationalExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5379:6: lv_right_3= ruleRelationalExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAndExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleAndExpression12334);
            	    lv_right_3=ruleRelationalExpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAndExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "right", lv_right_3, "RelationalExpression", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop107;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAndExpression


    // $ANTLR start entryRuleRelationalExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5404:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5404:62: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5405:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRelationalExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression12373);
            iv_ruleRelationalExpression=ruleRelationalExpression();
            _fsp--;

             current =iv_ruleRelationalExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression12383); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleRelationalExpression


    // $ANTLR start ruleRelationalExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5412:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* ) ;
    public final EObject ruleRelationalExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2=null;
        EObject this_AdditiveExpression_0 = null;

        EObject lv_right_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5417:6: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5418:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5418:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5419:5: this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12430);
            this_AdditiveExpression_0=ruleAdditiveExpression();
            _fsp--;

             
                    current = this_AdditiveExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5427:1: ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )*
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( ((LA109_0>=22 && LA109_0<=23)||(LA109_0>=66 && LA109_0<=70)) ) {
                    alt109=1;
                }


                switch (alt109) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5427:2: () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5427:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5428:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0().getType().getClassifier());
            	            try {
            	            	factory.set(temp, "left", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5443:2: (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5445:6: lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5445:20: ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' )
            	    int alt108=7;
            	    switch ( input.LA(1) ) {
            	    case 66:
            	        {
            	        alt108=1;
            	        }
            	        break;
            	    case 67:
            	        {
            	        alt108=2;
            	        }
            	        break;
            	    case 68:
            	        {
            	        alt108=3;
            	        }
            	        break;
            	    case 69:
            	        {
            	        alt108=4;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt108=5;
            	        }
            	        break;
            	    case 23:
            	        {
            	        alt108=6;
            	        }
            	        break;
            	    case 22:
            	        {
            	        alt108=7;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("5445:20: ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' )", 108, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt108) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5445:21: '~='
            	            {
            	            match(input,66,FOLLOW_66_in_ruleRelationalExpression12461); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorTildeEqualsSignKeyword_1_1_0_0(), "operator"); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5451:6: '=='
            	            {
            	            match(input,67,FOLLOW_67_in_ruleRelationalExpression12477); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_1(), "operator"); 
            	                

            	            }
            	            break;
            	        case 3 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5457:6: '!='
            	            {
            	            match(input,68,FOLLOW_68_in_ruleRelationalExpression12493); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_2(), "operator"); 
            	                

            	            }
            	            break;
            	        case 4 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5463:6: '>='
            	            {
            	            match(input,69,FOLLOW_69_in_ruleRelationalExpression12509); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_3(), "operator"); 
            	                

            	            }
            	            break;
            	        case 5 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5469:6: '<='
            	            {
            	            match(input,70,FOLLOW_70_in_ruleRelationalExpression12525); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_4(), "operator"); 
            	                

            	            }
            	            break;
            	        case 6 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5475:6: '>'
            	            {
            	            match(input,23,FOLLOW_23_in_ruleRelationalExpression12541); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignKeyword_1_1_0_5(), "operator"); 
            	                

            	            }
            	            break;
            	        case 7 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5481:6: '<'
            	            {
            	            match(input,22,FOLLOW_22_in_ruleRelationalExpression12557); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignKeyword_1_1_0_6(), "operator"); 
            	                

            	            }
            	            break;

            	    }


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getRelationalExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "operator", /* lv_operator_2 */ input.LT(-1), null, lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5501:2: (lv_right_3= ruleAdditiveExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5504:6: lv_right_3= ruleAdditiveExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12606);
            	    lv_right_3=ruleAdditiveExpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getRelationalExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "right", lv_right_3, "AdditiveExpression", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop109;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleRelationalExpression


    // $ANTLR start entryRuleAdditiveExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5529:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5529:60: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5530:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdditiveExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression12645);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();
            _fsp--;

             current =iv_ruleAdditiveExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression12655); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAdditiveExpression


    // $ANTLR start ruleAdditiveExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5537:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5542:6: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5543:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5543:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5544:5: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression12702);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();
            _fsp--;

             
                    current = this_MultiplicativeExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5552:1: ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )*
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==13||LA111_0==19) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5552:2: () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5552:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5553:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0().getType().getClassifier());
            	            try {
            	            	factory.add(temp, "params", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5568:2: (lv_name_2= ( '+' | '-' ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5570:6: lv_name_2= ( '+' | '-' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5570:16: ( '+' | '-' )
            	    int alt110=2;
            	    int LA110_0 = input.LA(1);

            	    if ( (LA110_0==19) ) {
            	        alt110=1;
            	    }
            	    else if ( (LA110_0==13) ) {
            	        alt110=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("5570:16: ( '+' | '-' )", 110, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt110) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5570:17: '+'
            	            {
            	            match(input,19,FOLLOW_19_in_ruleAdditiveExpression12733); 

            	                    createLeafNode(grammarAccess.getAdditiveExpressionAccess().getNamePlusSignKeyword_1_1_0_0(), "name"); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5576:6: '-'
            	            {
            	            match(input,13,FOLLOW_13_in_ruleAdditiveExpression12749); 

            	                    createLeafNode(grammarAccess.getAdditiveExpressionAccess().getNameHyphenMinusKeyword_1_1_0_1(), "name"); 
            	                

            	            }
            	            break;

            	    }


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAdditiveExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "name", /* lv_name_2 */ input.LT(-1), null, lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5596:2: (lv_params_3= ruleMultiplicativeExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5599:6: lv_params_3= ruleMultiplicativeExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression12798);
            	    lv_params_3=ruleMultiplicativeExpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAdditiveExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "params", lv_params_3, "MultiplicativeExpression", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop111;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAdditiveExpression


    // $ANTLR start entryRuleMultiplicativeExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5624:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5624:66: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5625:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMultiplicativeExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression12837);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();
            _fsp--;

             current =iv_ruleMultiplicativeExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression12847); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleMultiplicativeExpression


    // $ANTLR start ruleMultiplicativeExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5632:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5637:6: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5638:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5638:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5639:5: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression12894);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();
            _fsp--;

             
                    current = this_UnaryOrInfixExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5647:1: ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )*
            loop113:
            do {
                int alt113=2;
                int LA113_0 = input.LA(1);

                if ( (LA113_0==12||LA113_0==21) ) {
                    alt113=1;
                }


                switch (alt113) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5647:2: () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5647:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5648:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0().getType().getClassifier());
            	            try {
            	            	factory.add(temp, "params", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5663:2: (lv_name_2= ( '*' | '/' ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5665:6: lv_name_2= ( '*' | '/' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5665:16: ( '*' | '/' )
            	    int alt112=2;
            	    int LA112_0 = input.LA(1);

            	    if ( (LA112_0==21) ) {
            	        alt112=1;
            	    }
            	    else if ( (LA112_0==12) ) {
            	        alt112=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("5665:16: ( '*' | '/' )", 112, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt112) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5665:17: '*'
            	            {
            	            match(input,21,FOLLOW_21_in_ruleMultiplicativeExpression12925); 

            	                    createLeafNode(grammarAccess.getMultiplicativeExpressionAccess().getNameAsteriskKeyword_1_1_0_0(), "name"); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5671:6: '/'
            	            {
            	            match(input,12,FOLLOW_12_in_ruleMultiplicativeExpression12941); 

            	                    createLeafNode(grammarAccess.getMultiplicativeExpressionAccess().getNameSolidusKeyword_1_1_0_1(), "name"); 
            	                

            	            }
            	            break;

            	    }


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getMultiplicativeExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "name", /* lv_name_2 */ input.LT(-1), null, lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5691:2: (lv_params_3= ruleUnaryOrInfixExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5694:6: lv_params_3= ruleUnaryOrInfixExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression12990);
            	    lv_params_3=ruleUnaryOrInfixExpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getMultiplicativeExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "params", lv_params_3, "UnaryOrInfixExpression", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop113;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleMultiplicativeExpression


    // $ANTLR start entryRuleUnaryOrInfixExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5719:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5719:64: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5720:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression13029);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();
            _fsp--;

             current =iv_ruleUnaryOrInfixExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression13039); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleUnaryOrInfixExpression


    // $ANTLR start ruleUnaryOrInfixExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5727:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5732:6: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5733:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5733:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( ((LA114_0>=13 && LA114_0<=14)) ) {
                alt114=1;
            }
            else if ( ((LA114_0>=RULE_ID && LA114_0<=RULE_INT)||LA114_0==27||(LA114_0>=72 && LA114_0<=74)) ) {
                alt114=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5733:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )", 114, 0, input);

                throw nvae;
            }
            switch (alt114) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5734:5: this_UnaryExpression_0= ruleUnaryExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression13086);
                    this_UnaryExpression_0=ruleUnaryExpression();
                    _fsp--;

                     
                            current = this_UnaryExpression_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5744:5: this_InfixExpression_1= ruleInfixExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression13113);
                    this_InfixExpression_1=ruleInfixExpression();
                    _fsp--;

                     
                            current = this_InfixExpression_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleUnaryOrInfixExpression


    // $ANTLR start entryRuleUnaryExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5759:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5759:57: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5760:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnaryExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression13145);
            iv_ruleUnaryExpression=ruleUnaryExpression();
            _fsp--;

             current =iv_ruleUnaryExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression13155); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleUnaryExpression


    // $ANTLR start ruleUnaryExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5767:1: ruleUnaryExpression returns [EObject current=null] : ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0=null;
        EObject lv_params_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5772:6: ( ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5773:1: ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5773:1: ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5773:2: (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5773:2: (lv_name_0= ( '!' | '-' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5775:6: lv_name_0= ( '!' | '-' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5775:16: ( '!' | '-' )
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==14) ) {
                alt115=1;
            }
            else if ( (LA115_0==13) ) {
                alt115=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5775:16: ( '!' | '-' )", 115, 0, input);

                throw nvae;
            }
            switch (alt115) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5775:17: '!'
                    {
                    match(input,14,FOLLOW_14_in_ruleUnaryExpression13202); 

                            createLeafNode(grammarAccess.getUnaryExpressionAccess().getNameExclamationMarkKeyword_0_0_0(), "name"); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5781:6: '-'
                    {
                    match(input,13,FOLLOW_13_in_ruleUnaryExpression13218); 

                            createLeafNode(grammarAccess.getUnaryExpressionAccess().getNameHyphenMinusKeyword_0_0_1(), "name"); 
                        

                    }
                    break;

            }


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getUnaryExpressionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", /* lv_name_0 */ input.LT(-1), null, lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5801:2: (lv_params_1= ruleInfixExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5804:6: lv_params_1= ruleInfixExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression13267);
            lv_params_1=ruleInfixExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getUnaryExpressionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		add(current, "params", lv_params_1, "InfixExpression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleUnaryExpression


    // $ANTLR start entryRuleInfixExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5829:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5829:57: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5830:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInfixExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression13304);
            iv_ruleInfixExpression=ruleInfixExpression();
            _fsp--;

             current =iv_ruleInfixExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression13314); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleInfixExpression


    // $ANTLR start ruleInfixExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5837:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( () '->' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ')' )* ) ;
    public final EObject ruleInfixExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_3=null;
        EObject this_PrimaryExpression_0 = null;

        EObject lv_params_5 = null;

        EObject lv_params_7 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5842:6: ( (this_PrimaryExpression_0= rulePrimaryExpression ( () '->' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ')' )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5843:1: (this_PrimaryExpression_0= rulePrimaryExpression ( () '->' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ')' )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5843:1: (this_PrimaryExpression_0= rulePrimaryExpression ( () '->' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ')' )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5844:5: this_PrimaryExpression_0= rulePrimaryExpression ( () '->' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ')' )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression13361);
            this_PrimaryExpression_0=rulePrimaryExpression();
            _fsp--;

             
                    current = this_PrimaryExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5852:1: ( () '->' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ')' )*
            loop118:
            do {
                int alt118=2;
                int LA118_0 = input.LA(1);

                if ( (LA118_0==71) ) {
                    alt118=1;
                }


                switch (alt118) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5852:2: () '->' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ')'
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5852:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5853:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0().getType().getClassifier());
            	            try {
            	            	factory.set(temp, "target", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    match(input,71,FOLLOW_71_in_ruleInfixExpression13379); 

            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5872:1: (lv_name_3= RULE_ID )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5874:6: lv_name_3= RULE_ID
            	    {
            	    lv_name_3=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInfixExpression13401); 

            	    		createLeafNode(grammarAccess.getInfixExpressionAccess().getNameIDTerminalRuleCall_1_2_0(), "name"); 
            	    	

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getInfixExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "name", lv_name_3, "ID", lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }

            	    match(input,27,FOLLOW_27_in_ruleInfixExpression13418); 

            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5896:1: ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )?
            	    int alt117=2;
            	    int LA117_0 = input.LA(1);

            	    if ( (LA117_0==RULE_ID) ) {
            	        alt117=1;
            	    }
            	    switch (alt117) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5896:2: (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )*
            	            {
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5896:2: (lv_params_5= ruleParameter )
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5899:6: lv_params_5= ruleParameter
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsParameterParserRuleCall_1_4_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleParameter_in_ruleInfixExpression13453);
            	            lv_params_5=ruleParameter();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getInfixExpressionRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        
            	            	        try {
            	            	       		add(current, "params", lv_params_5, "Parameter", currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }

            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5917:2: ( ',' (lv_params_7= ruleParameter ) )*
            	            loop116:
            	            do {
            	                int alt116=2;
            	                int LA116_0 = input.LA(1);

            	                if ( (LA116_0==28) ) {
            	                    alt116=1;
            	                }


            	                switch (alt116) {
            	            	case 1 :
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5917:3: ',' (lv_params_7= ruleParameter )
            	            	    {
            	            	    match(input,28,FOLLOW_28_in_ruleInfixExpression13467); 

            	            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_4_1_0(), null); 
            	            	        
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5921:1: (lv_params_7= ruleParameter )
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5924:6: lv_params_7= ruleParameter
            	            	    {
            	            	     
            	            	    	        currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsParameterParserRuleCall_1_4_1_1_0(), currentNode); 
            	            	    	    
            	            	    pushFollow(FOLLOW_ruleParameter_in_ruleInfixExpression13501);
            	            	    lv_params_7=ruleParameter();
            	            	    _fsp--;


            	            	    	        if (current==null) {
            	            	    	            current = factory.create(grammarAccess.getInfixExpressionRule().getType().getClassifier());
            	            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	    	        }
            	            	    	        
            	            	    	        try {
            	            	    	       		add(current, "params", lv_params_7, "Parameter", currentNode);
            	            	    	        } catch (ValueConverterException vce) {
            	            	    				handleValueConverterException(vce);
            	            	    	        }
            	            	    	        currentNode = currentNode.getParent();
            	            	    	    

            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop116;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    match(input,30,FOLLOW_30_in_ruleInfixExpression13518); 

            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_5(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop118;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleInfixExpression


    // $ANTLR start entryRulePrimaryExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5953:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5953:59: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5954:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrimaryExpressionRule(), currentNode); 
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression13553);
            iv_rulePrimaryExpression=rulePrimaryExpression();
            _fsp--;

             current =iv_rulePrimaryExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression13563); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePrimaryExpression


    // $ANTLR start rulePrimaryExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5961:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_GlobalVarExpression_1= ruleGlobalVarExpression | this_ParanthesizedExpression_2= ruleParanthesizedExpression ) ;
    public final EObject rulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Literal_0 = null;

        EObject this_GlobalVarExpression_1 = null;

        EObject this_ParanthesizedExpression_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5966:6: ( (this_Literal_0= ruleLiteral | this_GlobalVarExpression_1= ruleGlobalVarExpression | this_ParanthesizedExpression_2= ruleParanthesizedExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5967:1: (this_Literal_0= ruleLiteral | this_GlobalVarExpression_1= ruleGlobalVarExpression | this_ParanthesizedExpression_2= ruleParanthesizedExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5967:1: (this_Literal_0= ruleLiteral | this_GlobalVarExpression_1= ruleGlobalVarExpression | this_ParanthesizedExpression_2= ruleParanthesizedExpression )
            int alt119=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_INT:
            case 72:
            case 73:
            case 74:
                {
                alt119=1;
                }
                break;
            case RULE_ID:
                {
                alt119=2;
                }
                break;
            case 27:
                {
                alt119=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("5967:1: (this_Literal_0= ruleLiteral | this_GlobalVarExpression_1= ruleGlobalVarExpression | this_ParanthesizedExpression_2= ruleParanthesizedExpression )", 119, 0, input);

                throw nvae;
            }

            switch (alt119) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5968:5: this_Literal_0= ruleLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression13610);
                    this_Literal_0=ruleLiteral();
                    _fsp--;

                     
                            current = this_Literal_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5978:5: this_GlobalVarExpression_1= ruleGlobalVarExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression13637);
                    this_GlobalVarExpression_1=ruleGlobalVarExpression();
                    _fsp--;

                     
                            current = this_GlobalVarExpression_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5988:5: this_ParanthesizedExpression_2= ruleParanthesizedExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression13664);
                    this_ParanthesizedExpression_2=ruleParanthesizedExpression();
                    _fsp--;

                     
                            current = this_ParanthesizedExpression_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePrimaryExpression


    // $ANTLR start entryRuleLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6003:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6003:49: (iv_ruleLiteral= ruleLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6004:2: iv_ruleLiteral= ruleLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral13696);
            iv_ruleLiteral=ruleLiteral();
            _fsp--;

             current =iv_ruleLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral13706); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLiteral


    // $ANTLR start ruleLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6011:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_StringLiteral_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6016:6: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6017:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6017:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral )
            int alt120=4;
            switch ( input.LA(1) ) {
            case 72:
            case 73:
                {
                alt120=1;
                }
                break;
            case RULE_INT:
                {
                alt120=2;
                }
                break;
            case 74:
                {
                alt120=3;
                }
                break;
            case RULE_STRING:
                {
                alt120=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("6017:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral )", 120, 0, input);

                throw nvae;
            }

            switch (alt120) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6018:5: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral13753);
                    this_BooleanLiteral_0=ruleBooleanLiteral();
                    _fsp--;

                     
                            current = this_BooleanLiteral_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6028:5: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral13780);
                    this_IntegerLiteral_1=ruleIntegerLiteral();
                    _fsp--;

                     
                            current = this_IntegerLiteral_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6038:5: this_NullLiteral_2= ruleNullLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral13807);
                    this_NullLiteral_2=ruleNullLiteral();
                    _fsp--;

                     
                            current = this_NullLiteral_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6048:5: this_StringLiteral_3= ruleStringLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_3(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral13834);
                    this_StringLiteral_3=ruleStringLiteral();
                    _fsp--;

                     
                            current = this_StringLiteral_3; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLiteral


    // $ANTLR start entryRuleBooleanLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6063:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6063:56: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6064:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBooleanLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral13866);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();
            _fsp--;

             current =iv_ruleBooleanLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral13876); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleBooleanLiteral


    // $ANTLR start ruleBooleanLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6071:1: ruleBooleanLiteral returns [EObject current=null] : (lv_val_0= ( 'true' | 'false' ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6076:6: ( (lv_val_0= ( 'true' | 'false' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6077:1: (lv_val_0= ( 'true' | 'false' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6077:1: (lv_val_0= ( 'true' | 'false' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6079:6: lv_val_0= ( 'true' | 'false' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6079:15: ( 'true' | 'false' )
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==72) ) {
                alt121=1;
            }
            else if ( (LA121_0==73) ) {
                alt121=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("6079:15: ( 'true' | 'false' )", 121, 0, input);

                throw nvae;
            }
            switch (alt121) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6079:16: 'true'
                    {
                    match(input,72,FOLLOW_72_in_ruleBooleanLiteral13922); 

                            createLeafNode(grammarAccess.getBooleanLiteralAccess().getValTrueKeyword_0_0(), "val"); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6085:6: 'false'
                    {
                    match(input,73,FOLLOW_73_in_ruleBooleanLiteral13938); 

                            createLeafNode(grammarAccess.getBooleanLiteralAccess().getValFalseKeyword_0_1(), "val"); 
                        

                    }
                    break;

            }


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getBooleanLiteralRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "val", /* lv_val_0 */ input.LT(-1), null, lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleBooleanLiteral


    // $ANTLR start entryRuleIntegerLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6112:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6112:56: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6113:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIntegerLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral13985);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();
            _fsp--;

             current =iv_ruleIntegerLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral13995); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIntegerLiteral


    // $ANTLR start ruleIntegerLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6120:1: ruleIntegerLiteral returns [EObject current=null] : (lv_val_0= RULE_INT ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6125:6: ( (lv_val_0= RULE_INT ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6126:1: (lv_val_0= RULE_INT )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6126:1: (lv_val_0= RULE_INT )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6128:6: lv_val_0= RULE_INT
            {
            lv_val_0=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral14041); 

            		createLeafNode(grammarAccess.getIntegerLiteralAccess().getValINTTerminalRuleCall_0(), "val"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIntegerLiteralRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "val", lv_val_0, "INT", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIntegerLiteral


    // $ANTLR start entryRuleNullLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6153:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6153:53: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6154:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNullLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral14081);
            iv_ruleNullLiteral=ruleNullLiteral();
            _fsp--;

             current =iv_ruleNullLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral14091); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleNullLiteral


    // $ANTLR start ruleNullLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6161:1: ruleNullLiteral returns [EObject current=null] : (lv_val_0= 'null' ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6166:6: ( (lv_val_0= 'null' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6167:1: (lv_val_0= 'null' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6167:1: (lv_val_0= 'null' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6169:6: lv_val_0= 'null'
            {
            lv_val_0=(Token)input.LT(1);
            match(input,74,FOLLOW_74_in_ruleNullLiteral14136); 

                    createLeafNode(grammarAccess.getNullLiteralAccess().getValNullKeyword_0(), "val"); 
                

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNullLiteralRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "val", /* lv_val_0 */ input.LT(-1), "null", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleNullLiteral


    // $ANTLR start entryRuleStringLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6195:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6195:55: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6196:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStringLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral14181);
            iv_ruleStringLiteral=ruleStringLiteral();
            _fsp--;

             current =iv_ruleStringLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral14191); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleStringLiteral


    // $ANTLR start ruleStringLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6203:1: ruleStringLiteral returns [EObject current=null] : (lv_val_0= RULE_STRING ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6208:6: ( (lv_val_0= RULE_STRING ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6209:1: (lv_val_0= RULE_STRING )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6209:1: (lv_val_0= RULE_STRING )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6211:6: lv_val_0= RULE_STRING
            {
            lv_val_0=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral14237); 

            		createLeafNode(grammarAccess.getStringLiteralAccess().getValSTRINGTerminalRuleCall_0(), "val"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getStringLiteralRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "val", lv_val_0, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleStringLiteral


    // $ANTLR start entryRuleParanthesizedExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6236:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6236:65: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6237:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getParanthesizedExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression14277);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();
            _fsp--;

             current =iv_ruleParanthesizedExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression14287); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleParanthesizedExpression


    // $ANTLR start ruleParanthesizedExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6244:1: ruleParanthesizedExpression returns [EObject current=null] : ( '(' this_Expression_1= ruleExpression ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Expression_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6249:6: ( ( '(' this_Expression_1= ruleExpression ')' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6250:1: ( '(' this_Expression_1= ruleExpression ')' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6250:1: ( '(' this_Expression_1= ruleExpression ')' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6250:2: '(' this_Expression_1= ruleExpression ')'
            {
            match(input,27,FOLLOW_27_in_ruleParanthesizedExpression14321); 

                    createLeafNode(grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression14343);
            this_Expression_1=ruleExpression();
            _fsp--;

             
                    current = this_Expression_1; 
                    currentNode = currentNode.getParent();
                
            match(input,30,FOLLOW_30_in_ruleParanthesizedExpression14351); 

                    createLeafNode(grammarAccess.getParanthesizedExpressionAccess().getRightParenthesisKeyword_2(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleParanthesizedExpression


    // $ANTLR start entryRuleGlobalVarExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6274:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6274:61: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6275:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGlobalVarExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression14384);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();
            _fsp--;

             current =iv_ruleGlobalVarExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression14394); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleGlobalVarExpression


    // $ANTLR start ruleGlobalVarExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6282:1: ruleGlobalVarExpression returns [EObject current=null] : (lv_name_0= ruleQualifiedName ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6287:6: ( (lv_name_0= ruleQualifiedName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6288:1: (lv_name_0= ruleQualifiedName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6288:1: (lv_name_0= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6291:6: lv_name_0= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameQualifiedNameParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleGlobalVarExpression14452);
            lv_name_0=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getGlobalVarExpressionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_0, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleGlobalVarExpression


    // $ANTLR start ruleVisibility
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6316:1: ruleVisibility returns [Enumerator current=null] : ( ( 'public' ) | ( 'private' ) ) ;
    public final Enumerator ruleVisibility() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6320:6: ( ( ( 'public' ) | ( 'private' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6321:1: ( ( 'public' ) | ( 'private' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6321:1: ( ( 'public' ) | ( 'private' ) )
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==75) ) {
                alt122=1;
            }
            else if ( (LA122_0==76) ) {
                alt122=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("6321:1: ( ( 'public' ) | ( 'private' ) )", 122, 0, input);

                throw nvae;
            }
            switch (alt122) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6321:2: ( 'public' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6321:2: ( 'public' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6321:4: 'public'
                    {
                    match(input,75,FOLLOW_75_in_ruleVisibility14502); 

                            current = grammarAccess.getVisibilityAccess().getPublicEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getVisibilityAccess().getPublicEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6327:6: ( 'private' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6327:6: ( 'private' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6327:8: 'private'
                    {
                    match(input,76,FOLLOW_76_in_ruleVisibility14517); 

                            current = grammarAccess.getVisibilityAccess().getPrivateEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getVisibilityAccess().getPrivateEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleVisibility


    protected DFA35 dfa35 = new DFA35(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA60 dfa60 = new DFA60(this);
    protected DFA64 dfa64 = new DFA64(this);
    protected DFA67 dfa67 = new DFA67(this);
    static final String DFA35_eotS =
        "\7\uffff";
    static final String DFA35_eofS =
        "\1\uffff\2\3\3\uffff\1\3";
    static final String DFA35_minS =
        "\1\4\1\14\1\13\2\uffff\1\4\1\13";
    static final String DFA35_maxS =
        "\1\5\2\41\2\uffff\1\4\1\41";
    static final String DFA35_acceptS =
        "\3\uffff\1\2\1\1\2\uffff";
    static final String DFA35_specialS =
        "\7\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\2\1\1",
            "\1\4\17\uffff\1\3\4\uffff\1\3",
            "\1\5\1\4\17\uffff\1\3\4\uffff\1\3",
            "",
            "",
            "\1\6",
            "\1\5\1\4\17\uffff\1\3\4\uffff\1\3"
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "2020:1: ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName )";
        }
    }
    static final String DFA46_eotS =
        "\17\uffff";
    static final String DFA46_eofS =
        "\17\uffff";
    static final String DFA46_minS =
        "\1\4\1\32\1\4\1\13\5\uffff\2\4\2\13\1\4\1\13";
    static final String DFA46_maxS =
        "\1\62\1\41\1\4\1\41\5\uffff\1\4\3\41\1\4\1\41";
    static final String DFA46_acceptS =
        "\4\uffff\1\3\1\4\1\5\1\1\1\2\6\uffff";
    static final String DFA46_specialS =
        "\17\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\3\1\1\6\uffff\1\2\36\uffff\1\4\1\5\1\6\4\uffff\1\6",
            "\1\10\1\uffff\1\7\4\uffff\1\7",
            "\1\3",
            "\1\11\1\12\15\uffff\1\10\1\uffff\1\7\4\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
            "\1\13",
            "\1\14\25\uffff\1\10\1\uffff\1\7\4\uffff\1\7",
            "\1\11\1\12\15\uffff\1\10\1\uffff\1\7\4\uffff\1\7",
            "\1\15\1\12\15\uffff\1\10\1\uffff\1\7\4\uffff\1\7",
            "\1\16",
            "\1\15\1\12\15\uffff\1\10\1\uffff\1\7\4\uffff\1\7"
    };

    static final short[] DFA46_eot = DFA.unpackEncodedString(DFA46_eotS);
    static final short[] DFA46_eof = DFA.unpackEncodedString(DFA46_eofS);
    static final char[] DFA46_min = DFA.unpackEncodedStringToUnsignedChars(DFA46_minS);
    static final char[] DFA46_max = DFA.unpackEncodedStringToUnsignedChars(DFA46_maxS);
    static final short[] DFA46_accept = DFA.unpackEncodedString(DFA46_acceptS);
    static final short[] DFA46_special = DFA.unpackEncodedString(DFA46_specialS);
    static final short[][] DFA46_transition;

    static {
        int numStates = DFA46_transitionS.length;
        DFA46_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA46_transition[i] = DFA.unpackEncodedString(DFA46_transitionS[i]);
        }
    }

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = DFA46_eot;
            this.eof = DFA46_eof;
            this.min = DFA46_min;
            this.max = DFA46_max;
            this.accept = DFA46_accept;
            this.special = DFA46_special;
            this.transition = DFA46_transition;
        }
        public String getDescription() {
            return "2324:4: ( ( (lv_paths_2= rulePath ) ( ',' (lv_paths_4= rulePath ) )* ';' ) | ( (lv_basePath_6= rulePath ) '[' (lv_paths_8= rulePath ) ( ',' (lv_paths_10= rulePath ) )* ']' ';' ) | ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_16= ruleQualifiedName ) ';' ) | (lv_setProperties_18= ruleStringProperty ) )+ '}' ) | ( 'unset' 'property' (lv_unsetProperties_22= ruleQualifiedName ) ';' ) | (lv_setProperties_24= ruleStringProperty2 ) )";
        }
    }
    static final String DFA60_eotS =
        "\6\uffff";
    static final String DFA60_eofS =
        "\6\uffff";
    static final String DFA60_minS =
        "\1\4\1\13\1\uffff\1\4\1\uffff\1\13";
    static final String DFA60_maxS =
        "\1\46\1\67\1\uffff\1\4\1\uffff\1\67";
    static final String DFA60_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\1\uffff";
    static final String DFA60_specialS =
        "\6\uffff}>";
    static final String[] DFA60_transitionS = {
            "\1\1\1\2\40\uffff\1\2",
            "\1\3\1\2\7\uffff\1\4\14\uffff\1\2\25\uffff\1\2",
            "",
            "\1\5",
            "",
            "\1\3\1\2\7\uffff\1\4\14\uffff\1\2\25\uffff\1\2"
    };

    static final short[] DFA60_eot = DFA.unpackEncodedString(DFA60_eotS);
    static final short[] DFA60_eof = DFA.unpackEncodedString(DFA60_eofS);
    static final char[] DFA60_min = DFA.unpackEncodedStringToUnsignedChars(DFA60_minS);
    static final char[] DFA60_max = DFA.unpackEncodedStringToUnsignedChars(DFA60_maxS);
    static final short[] DFA60_accept = DFA.unpackEncodedString(DFA60_acceptS);
    static final short[] DFA60_special = DFA.unpackEncodedString(DFA60_specialS);
    static final short[][] DFA60_transition;

    static {
        int numStates = DFA60_transitionS.length;
        DFA60_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA60_transition[i] = DFA.unpackEncodedString(DFA60_transitionS[i]);
        }
    }

    class DFA60 extends DFA {

        public DFA60(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 60;
            this.eot = DFA60_eot;
            this.eof = DFA60_eof;
            this.min = DFA60_min;
            this.max = DFA60_max;
            this.accept = DFA60_accept;
            this.special = DFA60_special;
            this.transition = DFA60_transition;
        }
        public String getDescription() {
            return "2921:4: ( (lv_alias_3= ruleQualifiedName ) '=' )?";
        }
    }
    static final String DFA64_eotS =
        "\7\uffff";
    static final String DFA64_eofS =
        "\2\uffff\1\1\3\uffff\1\1";
    static final String DFA64_minS =
        "\1\4\1\uffff\1\13\1\uffff\1\4\1\uffff\1\13";
    static final String DFA64_maxS =
        "\1\46\1\uffff\1\67\1\uffff\1\4\1\uffff\1\67";
    static final String DFA64_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\uffff\1\2\1\uffff";
    static final String DFA64_specialS =
        "\7\uffff}>";
    static final String[] DFA64_transitionS = {
            "\1\2\1\1\40\uffff\1\3",
            "",
            "\1\4\1\5\24\uffff\1\1\25\uffff\1\1",
            "",
            "\1\6",
            "",
            "\1\4\1\5\24\uffff\1\1\25\uffff\1\1"
    };

    static final short[] DFA64_eot = DFA.unpackEncodedString(DFA64_eotS);
    static final short[] DFA64_eof = DFA.unpackEncodedString(DFA64_eofS);
    static final char[] DFA64_min = DFA.unpackEncodedStringToUnsignedChars(DFA64_minS);
    static final char[] DFA64_max = DFA.unpackEncodedStringToUnsignedChars(DFA64_maxS);
    static final short[] DFA64_accept = DFA.unpackEncodedString(DFA64_acceptS);
    static final short[] DFA64_special = DFA.unpackEncodedString(DFA64_specialS);
    static final short[][] DFA64_transition;

    static {
        int numStates = DFA64_transitionS.length;
        DFA64_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA64_transition[i] = DFA.unpackEncodedString(DFA64_transitionS[i]);
        }
    }

    class DFA64 extends DFA {

        public DFA64(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 64;
            this.eot = DFA64_eot;
            this.eof = DFA64_eof;
            this.min = DFA64_min;
            this.max = DFA64_max;
            this.accept = DFA64_accept;
            this.special = DFA64_special;
            this.transition = DFA64_transition;
        }
        public String getDescription() {
            return "3191:1: (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences )";
        }
    }
    static final String DFA67_eotS =
        "\115\uffff";
    static final String DFA67_eofS =
        "\5\uffff\2\10\6\uffff\4\10\3\uffff\21\10\1\uffff\2\10\45\uffff";
    static final String DFA67_minS =
        "\1\4\1\13\2\4\1\13\1\14\1\13\1\4\2\uffff\3\4\1\41\2\4\1\13\1\34"+
        "\24\4\2\41\21\4\1\35\23\4";
    static final String DFA67_maxS =
        "\1\4\1\14\1\4\1\5\1\14\2\67\1\33\2\uffff\1\4\2\6\4\67\3\36\21\67"+
        "\1\6\2\67\45\36";
    static final String DFA67_acceptS =
        "\10\uffff\1\2\1\1\103\uffff";
    static final String DFA67_specialS =
        "\115\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\1",
            "\1\2\1\3",
            "\1\4",
            "\1\6\1\5",
            "\1\2\1\3",
            "\1\7\24\uffff\1\10\21\uffff\1\11\3\uffff\1\10",
            "\1\12\1\7\24\uffff\1\10\21\uffff\1\11\3\uffff\1\10",
            "\1\16\1\15\1\17\23\uffff\1\13\1\14",
            "",
            "",
            "\1\20",
            "\1\22\1\21\1\23",
            "\1\22\1\21\1\23",
            "\1\10\21\uffff\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\12\1\7\24\uffff\1\10\21\uffff\1\11\3\uffff\1\10",
            "\1\45\1\46\1\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\43\1\uffff\1\44\4\uffff\1\24\1\33\1\25\1\26\1\27\1\30\1\31"+
            "\1\32\1\34\1\35\1\36\1\37\1\40\1\41\1\42\7\uffff\1\10\21\uffff"+
            "\1\11\3\uffff\1\10",
            "\1\72\1\71\1\73",
            "\1\10\21\uffff\1\11\3\uffff\1\10",
            "\1\10\21\uffff\1\11\3\uffff\1\10",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\67\1\uffff\1\70\4\uffff\1\50\1\57\1\51\1\52\1\53\1\54\1\55"+
            "\1\56\1\60\1\61\1\62\1\63\1\64\1\65\1\66\2\uffff\1\45\1\46\1"+
            "\47",
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47",
            "\1\113\1\uffff\1\114\4\uffff\1\74\1\103\1\75\1\76\1\77\1\100"+
            "\1\101\1\102\1\104\1\105\1\106\1\107\1\110\1\111\1\112\3\uffff"+
            "\1\46\1\47"
    };

    static final short[] DFA67_eot = DFA.unpackEncodedString(DFA67_eotS);
    static final short[] DFA67_eof = DFA.unpackEncodedString(DFA67_eofS);
    static final char[] DFA67_min = DFA.unpackEncodedStringToUnsignedChars(DFA67_minS);
    static final char[] DFA67_max = DFA.unpackEncodedStringToUnsignedChars(DFA67_maxS);
    static final short[] DFA67_accept = DFA.unpackEncodedString(DFA67_acceptS);
    static final short[] DFA67_special = DFA.unpackEncodedString(DFA67_specialS);
    static final short[][] DFA67_transition;

    static {
        int numStates = DFA67_transitionS.length;
        DFA67_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA67_transition[i] = DFA.unpackEncodedString(DFA67_transitionS[i]);
        }
    }

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = DFA67_eot;
            this.eof = DFA67_eof;
            this.min = DFA67_min;
            this.max = DFA67_max;
            this.accept = DFA67_accept;
            this.special = DFA67_special;
            this.transition = DFA67_transition;
        }
        public String getDescription() {
            return "3283:1: ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleBeeModel_in_entryRuleBeeModel73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBeeModel83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleBeeModel142 = new BitSet(new long[]{0x0000000C80000000L});
    public static final BitSet FOLLOW_ruleUnit_in_ruleBeeModel181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName219 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedName230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName270 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_ruleQualifiedName289 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName304 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_entryRuleEscapedQualifiedName350 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEscapedQualifiedName361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleEscapedQualifiedName401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleEscapedQualifiedName434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_entryRuleInterfaceName478 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceName489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleInterfaceName535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundName_in_entryRuleCompoundName578 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCompoundName589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName636 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleCompoundName654 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnitName_in_entryRuleUnitName720 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnitName731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_ruleUnitName777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartName_in_entryRulePartName820 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePartName831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_rulePartName877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSeparator_in_entryRuleSeparator920 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSeparator931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleSeparator969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleSeparator988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleSeparator1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleSeparator1026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleSeparator1045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleSeparator1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleSeparator1083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleSeparator1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleSeparator1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleSeparator1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleSeparator1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleSeparator1178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleSeparator1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleSeparator1216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleSeparator1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlfanumSym_in_entryRuleAlfanumSym1274 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAlfanumSym1285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAlfanumSym1326 = new BitSet(new long[]{0x0000000003FFF852L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAlfanumSym1352 = new BitSet(new long[]{0x0000000003FFF852L});
    public static final BitSet FOLLOW_ruleSeparator_in_ruleAlfanumSym1381 = new BitSet(new long[]{0x0000000003FFF852L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAlfanumSym1407 = new BitSet(new long[]{0x0000000003FFF852L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAlfanumSym1433 = new BitSet(new long[]{0x0000000003FFF852L});
    public static final BitSet FOLLOW_ruleVersion_in_entryRuleVersion1479 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVersion1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleVersion1530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlfanumSym_in_ruleVersion1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVersionRange_in_entryRuleVersionRange1607 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVersionRange1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleVersionRange1658 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_27_in_ruleVersionRange1677 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleVersionRange1700 = new BitSet(new long[]{0x0000000070000000L});
    public static final BitSet FOLLOW_28_in_ruleVersionRange1719 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleVersionRange1741 = new BitSet(new long[]{0x0000000060000000L});
    public static final BitSet FOLLOW_29_in_ruleVersionRange1762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleVersionRange1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleVersionRange1811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport1854 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport1864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleImport1898 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleImport1932 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_32_in_ruleImport1946 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleImport1957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnit_in_entryRuleUnit1990 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnit2000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleUnit2046 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleUnit2069 = new BitSet(new long[]{0x0000007000000030L});
    public static final BitSet FOLLOW_ruleUnitName_in_ruleUnit2103 = new BitSet(new long[]{0x0000007000000000L});
    public static final BitSet FOLLOW_36_in_ruleUnit2118 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleUnit2152 = new BitSet(new long[]{0x0000006000000000L});
    public static final BitSet FOLLOW_37_in_ruleUnit2168 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleUnit2202 = new BitSet(new long[]{0x0000004010000000L});
    public static final BitSet FOLLOW_28_in_ruleUnit2216 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleUnit2250 = new BitSet(new long[]{0x0000004010000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2267 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_39_in_ruleUnit2278 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2287 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleUnit2322 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2335 = new BitSet(new long[]{0x0002010000000010L});
    public static final BitSet FOLLOW_40_in_ruleUnit2346 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_39_in_ruleUnit2363 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleUnit2397 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2410 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_41_in_ruleUnit2427 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2436 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2471 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2484 = new BitSet(new long[]{0x0002010000000010L});
    public static final BitSet FOLLOW_40_in_ruleUnit2495 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_41_in_ruleUnit2512 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2546 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2559 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_42_in_ruleUnit2576 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleUnit2585 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2594 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2629 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2642 = new BitSet(new long[]{0x0002010000000010L});
    public static final BitSet FOLLOW_40_in_ruleUnit2653 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_42_in_ruleUnit2670 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleUnit2679 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2713 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2726 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_43_in_ruleUnit2743 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2752 = new BitSet(new long[]{0x0004100000000010L});
    public static final BitSet FOLLOW_44_in_ruleUnit2763 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleUnit2797 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2810 = new BitSet(new long[]{0x0004110000000010L});
    public static final BitSet FOLLOW_ruleStringProperty_in_ruleUnit2851 = new BitSet(new long[]{0x0004110000000010L});
    public static final BitSet FOLLOW_40_in_ruleUnit2866 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_44_in_ruleUnit2883 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleUnit2892 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleUnit2926 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleUnit2939 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_ruleStringProperty2_in_ruleUnit2980 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_46_in_ruleUnit3000 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_ruleNamedAdvice_in_ruleUnit3034 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_47_in_ruleUnit3055 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit3064 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleSynchronization_in_ruleUnit3098 = new BitSet(new long[]{0x0000010000000030L});
    public static final BitSet FOLLOW_40_in_ruleUnit3112 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_47_in_ruleUnit3129 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleSynchronization_in_ruleUnit3163 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_ruleBuildPart_in_ruleUnit3208 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_48_in_ruleUnit3228 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit3237 = new BitSet(new long[]{0x0800010000000020L});
    public static final BitSet FOLLOW_ruleRepositoryConfiguration_in_ruleUnit3271 = new BitSet(new long[]{0x0800010000000020L});
    public static final BitSet FOLLOW_40_in_ruleUnit3285 = new BitSet(new long[]{0x0135FF8400000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_40_in_ruleUnit3297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_entryRuleProvidedCapability3330 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProvidedCapability3340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleProvidedCapability3375 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ruleFilter_in_ruleProvidedCapability3409 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3449 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleProvidedCapability3462 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3496 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_12_in_ruleProvidedCapability3510 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleProvidedCapability3544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_entryRuleRequiredCapability3583 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRequiredCapability3593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleRequiredCapability3628 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ruleFilter_in_ruleRequiredCapability3662 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3702 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleRequiredCapability3715 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3749 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_12_in_ruleRequiredCapability3763 = new BitSet(new long[]{0x000000000C000070L});
    public static final BitSet FOLLOW_ruleVersionRange_in_ruleRequiredCapability3797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringProperty_in_entryRuleStringProperty3836 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringProperty3846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleStringProperty3893 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleStringProperty3940 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleStringProperty3953 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleStringProperty3987 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleStringProperty4000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleStringProperty4043 = new BitSet(new long[]{0x0000000200100000L});
    public static final BitSet FOLLOW_20_in_ruleStringProperty4057 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleStringProperty4091 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleStringProperty4107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringProperty2_in_entryRuleStringProperty24141 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringProperty24151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleStringProperty24198 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleStringProperty24220 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleStringProperty24254 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleStringProperty24267 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleStringProperty24301 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleStringProperty24314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleStringProperty24332 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleStringProperty24366 = new BitSet(new long[]{0x0000000200100000L});
    public static final BitSet FOLLOW_20_in_ruleStringProperty24380 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleStringProperty24414 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleStringProperty24430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression4464 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyExpression4474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePropertyExpression4520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSynchronization_in_entryRuleSynchronization4551 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSynchronization4561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4620 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleSynchronization4634 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4668 = new BitSet(new long[]{0x0000000210000000L});
    public static final BitSet FOLLOW_33_in_ruleSynchronization4683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSynchronizedPart_in_entryRuleSynchronizedPart4717 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSynchronizedPart4728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundName_in_ruleSynchronizedPart4776 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_ruleSynchronizedPart4795 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleSynchronizedPart4817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartName_in_ruleSynchronizedPart4853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuildPart_in_entryRuleBuildPart4896 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuildPart4906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArtifacts_in_ruleBuildPart4953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroup_in_ruleBuildPart4980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAction_in_ruleBuildPart5007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArtifacts_in_entryRuleArtifacts5039 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArtifacts5049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVisibility_in_ruleArtifacts5108 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_ruleArtifacts5122 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleArtifacts5156 = new BitSet(new long[]{0x400000C000000000L});
    public static final BitSet FOLLOW_39_in_ruleArtifacts5170 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleArtifacts5204 = new BitSet(new long[]{0x4000004010000000L});
    public static final BitSet FOLLOW_28_in_ruleArtifacts5218 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleArtifacts5252 = new BitSet(new long[]{0x4000004010000000L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleArtifacts5294 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleArtifacts5308 = new BitSet(new long[]{0x0006390000001030L});
    public static final BitSet FOLLOW_rulePathGroup_in_ruleArtifacts5342 = new BitSet(new long[]{0x0006390000001030L});
    public static final BitSet FOLLOW_40_in_ruleArtifacts5356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePathGroup_in_entryRulePathGroup5389 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePathGroup5399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rulePathGroup5434 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ruleFilter_in_rulePathGroup5468 = new BitSet(new long[]{0x0004380000001030L});
    public static final BitSet FOLLOW_rulePath_in_rulePathGroup5510 = new BitSet(new long[]{0x0000000210000000L});
    public static final BitSet FOLLOW_28_in_rulePathGroup5524 = new BitSet(new long[]{0x0000000000001030L});
    public static final BitSet FOLLOW_rulePath_in_rulePathGroup5558 = new BitSet(new long[]{0x0000000210000000L});
    public static final BitSet FOLLOW_33_in_rulePathGroup5573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePath_in_rulePathGroup5615 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_rulePathGroup5628 = new BitSet(new long[]{0x0000000000001030L});
    public static final BitSet FOLLOW_rulePath_in_rulePathGroup5662 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_28_in_rulePathGroup5676 = new BitSet(new long[]{0x0000000000001030L});
    public static final BitSet FOLLOW_rulePath_in_rulePathGroup5710 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_29_in_rulePathGroup5725 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_rulePathGroup5734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rulePathGroup5751 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_rulePathGroup5760 = new BitSet(new long[]{0x0004100000000010L});
    public static final BitSet FOLLOW_44_in_rulePathGroup5771 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePathGroup5805 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_rulePathGroup5818 = new BitSet(new long[]{0x0004110000000010L});
    public static final BitSet FOLLOW_ruleStringProperty_in_rulePathGroup5859 = new BitSet(new long[]{0x0004110000000010L});
    public static final BitSet FOLLOW_40_in_rulePathGroup5874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_rulePathGroup5891 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_rulePathGroup5900 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePathGroup5934 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_rulePathGroup5947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringProperty2_in_rulePathGroup5988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePath_in_entryRulePath6027 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePath6038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePath6078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rulePath6104 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePath6128 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_12_in_rulePath6147 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePath6169 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_12_in_rulePath6190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroup_in_entryRuleGroup6231 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroup6241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVisibility_in_ruleGroup6300 = new BitSet(new long[]{0x0020000400000000L});
    public static final BitSet FOLLOW_34_in_ruleGroup6326 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_ruleGroup6349 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleGroup6383 = new BitSet(new long[]{0x600000C000000000L});
    public static final BitSet FOLLOW_39_in_ruleGroup6397 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleGroup6431 = new BitSet(new long[]{0x6000004010000000L});
    public static final BitSet FOLLOW_28_in_ruleGroup6445 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleGroup6479 = new BitSet(new long[]{0x6000004010000000L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_ruleGroup6521 = new BitSet(new long[]{0x4000004000000000L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleGroup6560 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleGroup6574 = new BitSet(new long[]{0x0042014000000030L});
    public static final BitSet FOLLOW_rulePrerequisite_in_ruleGroup6608 = new BitSet(new long[]{0x0042014000000030L});
    public static final BitSet FOLLOW_40_in_ruleGroup6622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrerequisite_in_entryRulePrerequisite6655 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrerequisite6665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_rulePrerequisite6712 = new BitSet(new long[]{0x0002004000000030L});
    public static final BitSet FOLLOW_49_in_rulePrerequisite6736 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ruleFilter_in_rulePrerequisite6770 = new BitSet(new long[]{0x0000004000000030L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePrerequisite6811 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_rulePrerequisite6824 = new BitSet(new long[]{0x0000004000000030L});
    public static final BitSet FOLLOW_rulePrerequisiteEntry_in_rulePrerequisite6860 = new BitSet(new long[]{0x0080000200000000L});
    public static final BitSet FOLLOW_ruleClosure_in_rulePrerequisite6899 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_rulePrerequisite6913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClosure_in_entryRuleClosure6946 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClosure6956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleClosure6990 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleClosure6999 = new BitSet(new long[]{0x0004790000000000L});
    public static final BitSet FOLLOW_43_in_ruleClosure7010 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleClosure7019 = new BitSet(new long[]{0x0004100000000010L});
    public static final BitSet FOLLOW_44_in_ruleClosure7030 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleClosure7064 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleClosure7077 = new BitSet(new long[]{0x0004110000000010L});
    public static final BitSet FOLLOW_ruleStringProperty_in_ruleClosure7118 = new BitSet(new long[]{0x0004110000000010L});
    public static final BitSet FOLLOW_40_in_ruleClosure7133 = new BitSet(new long[]{0x0004790000000000L});
    public static final BitSet FOLLOW_44_in_ruleClosure7150 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleClosure7159 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleClosure7193 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleClosure7206 = new BitSet(new long[]{0x0004790000000000L});
    public static final BitSet FOLLOW_ruleStringProperty2_in_ruleClosure7247 = new BitSet(new long[]{0x0004790000000000L});
    public static final BitSet FOLLOW_46_in_ruleClosure7267 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleClosure7301 = new BitSet(new long[]{0x0004790000000000L});
    public static final BitSet FOLLOW_40_in_ruleClosure7317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrerequisiteEntry_in_entryRulePrerequisiteEntry7350 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrerequisiteEntry7360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartInSelf_in_rulePrerequisiteEntry7407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCapabilityReferencedPart_in_rulePrerequisiteEntry7434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundReferences_in_rulePrerequisiteEntry7461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartInSelf_in_entryRulePartInSelf7493 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePartInSelf7503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartName_in_rulePartInSelf7561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCapabilityReferencedPart_in_entryRuleCapabilityReferencedPart7597 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCapabilityReferencedPart7607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart7667 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleCapabilityReferencedPart7680 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart7714 = new BitSet(new long[]{0x0008000000001000L});
    public static final BitSet FOLLOW_12_in_ruleCapabilityReferencedPart7728 = new BitSet(new long[]{0x000000000C000070L});
    public static final BitSet FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart7762 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleCapabilityReferencedPart7777 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleCapabilityReferencedPart7811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart7857 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleCapabilityReferencedPart7870 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart7904 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_12_in_ruleCapabilityReferencedPart7918 = new BitSet(new long[]{0x000000000C000070L});
    public static final BitSet FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart7952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundReferences_in_entryRuleCompoundReferences7992 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCompoundReferences8002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleCompoundReferences8036 = new BitSet(new long[]{0x0042014000000030L});
    public static final BitSet FOLLOW_rulePrerequisite_in_ruleCompoundReferences8070 = new BitSet(new long[]{0x0042014000000030L});
    public static final BitSet FOLLOW_40_in_ruleCompoundReferences8084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAction_in_entryRuleAction8117 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAction8127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVisibility_in_ruleAction8186 = new BitSet(new long[]{0x0100000400000000L});
    public static final BitSet FOLLOW_34_in_ruleAction8212 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ruleAction8235 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleAction8244 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleAction8245 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAction8254 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleAction8255 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleAction8290 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_28_in_ruleAction8304 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleAction8338 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_30_in_ruleAction8355 = new BitSet(new long[]{0x600000C000000000L});
    public static final BitSet FOLLOW_39_in_ruleAction8365 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleAction8399 = new BitSet(new long[]{0x6000004010000000L});
    public static final BitSet FOLLOW_28_in_ruleAction8413 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleAction8447 = new BitSet(new long[]{0x6000004010000000L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_ruleAction8489 = new BitSet(new long[]{0x4000004000000000L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleAction8528 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleAction8542 = new BitSet(new long[]{0x0402014000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_ruleResult_in_ruleAction8576 = new BitSet(new long[]{0x0402014000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_40_in_ruleAction8590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameter_in_entryRuleParameter8623 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameter8633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleParameter8692 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleParameter8705 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParameter8739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResult_in_entryRuleResult8776 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResult8786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResultPart_in_ruleResult8845 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_ruleResultGroup_in_ruleResult8883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResultPart_in_entryRuleResultPart8921 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResultPart8931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBasicResult_in_ruleResultPart8978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleResultPart8993 = new BitSet(new long[]{0x0402010000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_ruleBasicResult_in_ruleResultPart9027 = new BitSet(new long[]{0x0402010000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_40_in_ruleResultPart9041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBasicResult_in_entryRuleBasicResult9075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBasicResult9085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleBasicResult9120 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ruleFilter_in_ruleBasicResult9154 = new BitSet(new long[]{0x0400000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_ruleVisibility_in_ruleBasicResult9194 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleBasicResult9208 = new BitSet(new long[]{0x6000004000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleBasicResult9242 = new BitSet(new long[]{0x6000004000000000L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_ruleBasicResult9281 = new BitSet(new long[]{0x4000004000000000L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleBasicResult9320 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleBasicResult9334 = new BitSet(new long[]{0x0006390000001030L});
    public static final BitSet FOLLOW_rulePathGroup_in_ruleBasicResult9368 = new BitSet(new long[]{0x0006390000001030L});
    public static final BitSet FOLLOW_40_in_ruleBasicResult9382 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_ruleClosure_in_ruleBasicResult9416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResultGroup_in_entryRuleResultGroup9454 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResultGroup9464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleResultGroup9498 = new BitSet(new long[]{0x6000004000000000L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_ruleResultGroup9532 = new BitSet(new long[]{0x4000004000000000L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleResultGroup9571 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleResultGroup9585 = new BitSet(new long[]{0x0042014000000030L});
    public static final BitSet FOLLOW_rulePrerequisite_in_ruleResultGroup9619 = new BitSet(new long[]{0x0042014000000030L});
    public static final BitSet FOLLOW_40_in_ruleResultGroup9633 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_ruleClosure_in_ruleResultGroup9667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRepositoryConfiguration_in_entryRuleRepositoryConfiguration9705 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRepositoryConfiguration9715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleURI_in_ruleRepositoryConfiguration9775 = new BitSet(new long[]{0x0000004200000000L});
    public static final BitSet FOLLOW_59_in_ruleRepositoryConfiguration9795 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleRepositoryConfiguration9829 = new BitSet(new long[]{0x0000004200000000L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleRepositoryConfiguration9869 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleRepositoryConfiguration9883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleURI_in_entryRuleURI9917 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleURI9928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleURI9967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamedAdvice_in_entryRuleNamedAdvice10009 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamedAdvice10019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleNamedAdvice10078 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleNamedAdvice10117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_entryRuleCompoundAdvice10154 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCompoundAdvice10164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleCompoundAdvice10198 = new BitSet(new long[]{0x1000010002001810L});
    public static final BitSet FOLLOW_ruleAdviceStatement_in_ruleCompoundAdvice10233 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleCompoundAdvice10246 = new BitSet(new long[]{0x1000010002001810L});
    public static final BitSet FOLLOW_40_in_ruleCompoundAdvice10257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdviceStatement_in_entryRuleAdviceStatement10290 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdviceStatement10300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePath_in_ruleAdviceStatement10359 = new BitSet(new long[]{0x0000004000100000L});
    public static final BitSet FOLLOW_20_in_ruleAdviceStatement10374 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAdviceStatement10408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleAdviceStatement10453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePath_in_entryRuleAdvicePath10491 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePath10501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10560 = new BitSet(new long[]{0x1000000002000810L});
    public static final BitSet FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10599 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10638 = new BitSet(new long[]{0x1000000002000810L});
    public static final BitSet FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10676 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_ruleAdvicePathSeparator_in_entryRuleAdvicePathSeparator10715 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePathSeparator10725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathChildren_in_ruleAdvicePathSeparator10771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathChildren_in_entryRuleAdvicePathChildren10802 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePathChildren10812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleAdvicePathChildren10855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathElement_in_entryRuleAdvicePathElement10888 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePathElement10898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAdvicePathElement10950 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_ruleWildcardNode_in_ruleAdvicePathElement10964 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_11_in_ruleAdvicePathElement10974 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_ruleAdvicePathElement10999 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAdvicePathElement11033 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleAdvicePathElement11046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleAdvicePathElement11076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWildcardNode_in_entryRuleWildcardNode11123 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWildcardNode11134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleWildcardNode11172 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleWildcardNode11186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilter_in_entryRuleFilter11226 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFilter11236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleFilter11270 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleFilter11304 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleFilter11317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_entryRulePreConditionAssert11350 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePreConditionAssert11360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_rulePreConditionAssert11406 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_rulePreConditionAssert11428 = new BitSet(new long[]{0x8000010000000000L});
    public static final BitSet FOLLOW_ruleAssertionExpression_in_rulePreConditionAssert11462 = new BitSet(new long[]{0x8000010000000000L});
    public static final BitSet FOLLOW_40_in_rulePreConditionAssert11476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_entryRulePostConditionAssert11509 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePostConditionAssert11519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_rulePostConditionAssert11565 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_rulePostConditionAssert11587 = new BitSet(new long[]{0x8000010000000000L});
    public static final BitSet FOLLOW_ruleAssertionExpression_in_rulePostConditionAssert11621 = new BitSet(new long[]{0x8000010000000000L});
    public static final BitSet FOLLOW_40_in_rulePostConditionAssert11635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAssertionExpression_in_entryRuleAssertionExpression11668 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAssertionExpression11678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleAssertionExpression11712 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAssertionExpression11746 = new BitSet(new long[]{0x0000000210000000L});
    public static final BitSet FOLLOW_28_in_ruleAssertionExpression11760 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleAssertionExpression11782 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleAssertionExpression11801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression11836 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression11846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValueExpression_in_ruleExpression11892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValueExpression_in_entryRuleValueExpression11923 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValueExpression11933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleValueExpression11991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression12027 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression12037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression12084 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleOrExpression12114 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression12161 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression12200 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression12210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleAndExpression12257 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAndExpression12287 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleAndExpression12334 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression12373 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression12383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12430 = new BitSet(new long[]{0x0000000000C00002L,0x000000000000007CL});
    public static final BitSet FOLLOW_66_in_ruleRelationalExpression12461 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_67_in_ruleRelationalExpression12477 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_68_in_ruleRelationalExpression12493 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_69_in_ruleRelationalExpression12509 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_70_in_ruleRelationalExpression12525 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_23_in_ruleRelationalExpression12541 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_22_in_ruleRelationalExpression12557 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12606 = new BitSet(new long[]{0x0000000000C00002L,0x000000000000007CL});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression12645 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression12655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression12702 = new BitSet(new long[]{0x0000000000082002L});
    public static final BitSet FOLLOW_19_in_ruleAdditiveExpression12733 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_13_in_ruleAdditiveExpression12749 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression12798 = new BitSet(new long[]{0x0000000000082002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression12837 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression12847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression12894 = new BitSet(new long[]{0x0000000000201002L});
    public static final BitSet FOLLOW_21_in_ruleMultiplicativeExpression12925 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_12_in_ruleMultiplicativeExpression12941 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression12990 = new BitSet(new long[]{0x0000000000201002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression13029 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression13039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression13086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression13113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression13145 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression13155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleUnaryExpression13202 = new BitSet(new long[]{0x0000000008000070L,0x0000000000000700L});
    public static final BitSet FOLLOW_13_in_ruleUnaryExpression13218 = new BitSet(new long[]{0x0000000008000070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression13267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression13304 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression13314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression13361 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_ruleInfixExpression13379 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInfixExpression13401 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleInfixExpression13418 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleInfixExpression13453 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_28_in_ruleInfixExpression13467 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleInfixExpression13501 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_30_in_ruleInfixExpression13518 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression13553 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression13563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression13610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression13637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression13664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral13696 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral13706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral13753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral13780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral13807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral13834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral13866 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral13876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleBooleanLiteral13922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleBooleanLiteral13938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral13985 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral13995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral14041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral14081 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral14091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleNullLiteral14136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral14181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral14191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral14237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression14277 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression14287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleParanthesizedExpression14321 = new BitSet(new long[]{0x0000000008006070L,0x0000000000000700L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression14343 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleParanthesizedExpression14351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression14384 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression14394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleGlobalVarExpression14452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleVisibility14502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleVisibility14517 = new BitSet(new long[]{0x0000000000000002L});

}