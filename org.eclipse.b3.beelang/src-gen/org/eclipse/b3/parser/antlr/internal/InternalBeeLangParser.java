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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'.'", "'~~~~~'", "'/'", "'-'", "'!'", "'@'", "'$'", "'%'", "'&'", "'+'", "'='", "'*'", "'<'", "'>'", "':'", "'?'", "'['", "'('", "','", "']'", "')'", "'import'", "'.*'", "';'", "'unit'", "'version'", "'implements'", "'{'", "'properties'", "'provides'", "'}'", "'requires'", "'meta'", "'advice'", "'sequential'", "'repositories'", "'when'", "'local'", "'immutable'", "'unset'", "'#'", "'artifacts'", "'annotations'", "'expr'", "'group'", "'hidden'", "'with'", "'action'", "'actor'", "'...'", "'layout'", "'resolver'", "'..'", "'precondition'", "'postcondition'", "'assert'", "'||'", "'&&'", "'~='", "'=='", "'!='", "'>='", "'<='", "'true'", "'false'", "'null'", "'public'", "'private'", "'parallel'"
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

                if ( (LA1_0==32) ) {
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:144:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* this_HIDDENBUG_3= ruleHIDDENBUG ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        AntlrDatatypeRuleToken this_HIDDENBUG_3 = null;


         setCurrentLookahead(); resetLookahead(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:152:6: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* this_HIDDENBUG_3= ruleHIDDENBUG ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:153:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* this_HIDDENBUG_3= ruleHIDDENBUG )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:153:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* this_HIDDENBUG_3= ruleHIDDENBUG )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:153:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* this_HIDDENBUG_3= ruleHIDDENBUG
            {
            this_ID_0=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedName270); 

            		current.merge(this_ID_0);
                
             
                createLeafNode(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:160:1: (kw= '.' this_ID_2= RULE_ID )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:161:2: kw= '.' this_ID_2= RULE_ID
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

             
                    currentNode=createCompositeNode(grammarAccess.getQualifiedNameAccess().getHIDDENBUGParserRuleCall_2(), currentNode); 
                
            pushFollow(FOLLOW_ruleHIDDENBUG_in_ruleQualifiedName333);
            this_HIDDENBUG_3=ruleHIDDENBUG();
            _fsp--;


            		current.merge(this_HIDDENBUG_3);
                
             
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
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
    // $ANTLR end ruleQualifiedName


    // $ANTLR start entryRuleHIDDENBUG
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:192:1: entryRuleHIDDENBUG returns [String current=null] : iv_ruleHIDDENBUG= ruleHIDDENBUG EOF ;
    public final String entryRuleHIDDENBUG() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleHIDDENBUG = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:192:50: (iv_ruleHIDDENBUG= ruleHIDDENBUG EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:193:2: iv_ruleHIDDENBUG= ruleHIDDENBUG EOF
            {
             currentNode = createCompositeNode(grammarAccess.getHIDDENBUGRule(), currentNode); 
            pushFollow(FOLLOW_ruleHIDDENBUG_in_entryRuleHIDDENBUG377);
            iv_ruleHIDDENBUG=ruleHIDDENBUG();
            _fsp--;

             current =iv_ruleHIDDENBUG.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleHIDDENBUG388); 

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
    // $ANTLR end entryRuleHIDDENBUG


    // $ANTLR start ruleHIDDENBUG
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:200:1: ruleHIDDENBUG returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '~~~~~' )? ;
    public final AntlrDatatypeRuleToken ruleHIDDENBUG() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS", "RULE_SL_COMMENT", "RULE_ML_COMMENT");
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:208:6: ( (kw= '~~~~~' )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:209:1: (kw= '~~~~~' )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:209:1: (kw= '~~~~~' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==12) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:210:2: kw= '~~~~~'
                    {
                    kw=(Token)input.LT(1);
                    match(input,12,FOLLOW_12_in_ruleHIDDENBUG426); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getHIDDENBUGAccess().getTildeTildeTildeTildeTildeKeyword(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
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
    // $ANTLR end ruleHIDDENBUG


    // $ANTLR start entryRuleEscapedQualifiedName
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:223:1: entryRuleEscapedQualifiedName returns [String current=null] : iv_ruleEscapedQualifiedName= ruleEscapedQualifiedName EOF ;
    public final String entryRuleEscapedQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEscapedQualifiedName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:223:61: (iv_ruleEscapedQualifiedName= ruleEscapedQualifiedName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:224:2: iv_ruleEscapedQualifiedName= ruleEscapedQualifiedName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEscapedQualifiedNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_entryRuleEscapedQualifiedName466);
            iv_ruleEscapedQualifiedName=ruleEscapedQualifiedName();
            _fsp--;

             current =iv_ruleEscapedQualifiedName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEscapedQualifiedName477); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:231:1: ruleEscapedQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName ) ;
    public final AntlrDatatypeRuleToken ruleEscapedQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        AntlrDatatypeRuleToken this_QualifiedName_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:237:6: ( (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:238:1: (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:238:1: (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_STRING) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("238:1: (this_STRING_0= RULE_STRING | this_QualifiedName_1= ruleQualifiedName )", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:238:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleEscapedQualifiedName517); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEscapedQualifiedNameAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:247:5: this_QualifiedName_1= ruleQualifiedName
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEscapedQualifiedNameAccess().getQualifiedNameParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleEscapedQualifiedName550);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:265:1: entryRuleInterfaceName returns [String current=null] : iv_ruleInterfaceName= ruleInterfaceName EOF ;
    public final String entryRuleInterfaceName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInterfaceName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:265:54: (iv_ruleInterfaceName= ruleInterfaceName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:266:2: iv_ruleInterfaceName= ruleInterfaceName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInterfaceNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleInterfaceName_in_entryRuleInterfaceName594);
            iv_ruleInterfaceName=ruleInterfaceName();
            _fsp--;

             current =iv_ruleInterfaceName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceName605); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:273:1: ruleInterfaceName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_QualifiedName_0= ruleQualifiedName ;
    public final AntlrDatatypeRuleToken ruleInterfaceName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_QualifiedName_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:279:6: (this_QualifiedName_0= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:281:5: this_QualifiedName_0= ruleQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getInterfaceNameAccess().getQualifiedNameParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleInterfaceName651);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:299:1: entryRuleCompoundName returns [String current=null] : iv_ruleCompoundName= ruleCompoundName EOF ;
    public final String entryRuleCompoundName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCompoundName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:299:53: (iv_ruleCompoundName= ruleCompoundName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:300:2: iv_ruleCompoundName= ruleCompoundName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCompoundNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleCompoundName_in_entryRuleCompoundName694);
            iv_ruleCompoundName=ruleCompoundName();
            _fsp--;

             current =iv_ruleCompoundName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCompoundName705); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:307:1: ruleCompoundName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName ) ;
    public final AntlrDatatypeRuleToken ruleCompoundName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_EscapedQualifiedName_0 = null;

        AntlrDatatypeRuleToken this_EscapedQualifiedName_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:313:6: ( (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:314:1: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:314:1: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:315:5: this_EscapedQualifiedName_0= ruleEscapedQualifiedName kw= '/' this_EscapedQualifiedName_2= ruleEscapedQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getCompoundNameAccess().getEscapedQualifiedNameParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName752);
            this_EscapedQualifiedName_0=ruleEscapedQualifiedName();
            _fsp--;


            		current.merge(this_EscapedQualifiedName_0);
                
             
                    currentNode = currentNode.getParent();
                
            kw=(Token)input.LT(1);
            match(input,13,FOLLOW_13_in_ruleCompoundName770); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getCompoundNameAccess().getSolidusKeyword_1(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getCompoundNameAccess().getEscapedQualifiedNameParserRuleCall_2(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName792);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:350:1: entryRuleUnitName returns [String current=null] : iv_ruleUnitName= ruleUnitName EOF ;
    public final String entryRuleUnitName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnitName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:350:49: (iv_ruleUnitName= ruleUnitName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:351:2: iv_ruleUnitName= ruleUnitName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnitNameRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnitName_in_entryRuleUnitName836);
            iv_ruleUnitName=ruleUnitName();
            _fsp--;

             current =iv_ruleUnitName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnitName847); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:358:1: ruleUnitName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EscapedQualifiedName_0= ruleEscapedQualifiedName ;
    public final AntlrDatatypeRuleToken ruleUnitName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EscapedQualifiedName_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:364:6: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:366:5: this_EscapedQualifiedName_0= ruleEscapedQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getUnitNameAccess().getEscapedQualifiedNameParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_ruleUnitName893);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:384:1: entryRulePartName returns [String current=null] : iv_rulePartName= rulePartName EOF ;
    public final String entryRulePartName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePartName = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:384:49: (iv_rulePartName= rulePartName EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:385:2: iv_rulePartName= rulePartName EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPartNameRule(), currentNode); 
            pushFollow(FOLLOW_rulePartName_in_entryRulePartName936);
            iv_rulePartName=rulePartName();
            _fsp--;

             current =iv_rulePartName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePartName947); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:392:1: rulePartName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EscapedQualifiedName_0= ruleEscapedQualifiedName ;
    public final AntlrDatatypeRuleToken rulePartName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EscapedQualifiedName_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:398:6: (this_EscapedQualifiedName_0= ruleEscapedQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:400:5: this_EscapedQualifiedName_0= ruleEscapedQualifiedName
            {
             
                    currentNode=createCompositeNode(grammarAccess.getPartNameAccess().getEscapedQualifiedNameParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleEscapedQualifiedName_in_rulePartName993);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:418:1: entryRuleSeparator returns [String current=null] : iv_ruleSeparator= ruleSeparator EOF ;
    public final String entryRuleSeparator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSeparator = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:418:50: (iv_ruleSeparator= ruleSeparator EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:419:2: iv_ruleSeparator= ruleSeparator EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSeparatorRule(), currentNode); 
            pushFollow(FOLLOW_ruleSeparator_in_entryRuleSeparator1036);
            iv_ruleSeparator=ruleSeparator();
            _fsp--;

             current =iv_ruleSeparator.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSeparator1047); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:426:1: ruleSeparator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' ) ;
    public final AntlrDatatypeRuleToken ruleSeparator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:432:6: ( (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:433:1: (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:433:1: (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' )
            int alt5=15;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt5=1;
                }
                break;
            case 14:
                {
                alt5=2;
                }
                break;
            case 15:
                {
                alt5=3;
                }
                break;
            case 16:
                {
                alt5=4;
                }
                break;
            case 17:
                {
                alt5=5;
                }
                break;
            case 18:
                {
                alt5=6;
                }
                break;
            case 19:
                {
                alt5=7;
                }
                break;
            case 13:
                {
                alt5=8;
                }
                break;
            case 20:
                {
                alt5=9;
                }
                break;
            case 21:
                {
                alt5=10;
                }
                break;
            case 22:
                {
                alt5=11;
                }
                break;
            case 23:
                {
                alt5=12;
                }
                break;
            case 24:
                {
                alt5=13;
                }
                break;
            case 25:
                {
                alt5=14;
                }
                break;
            case 26:
                {
                alt5=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("433:1: (kw= '.' | kw= '-' | kw= '!' | kw= '@' | kw= '$' | kw= '%' | kw= '&' | kw= '/' | kw= '+' | kw= '=' | kw= '*' | kw= '<' | kw= '>' | kw= ':' | kw= '?' )", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:434:2: kw= '.'
                    {
                    kw=(Token)input.LT(1);
                    match(input,11,FOLLOW_11_in_ruleSeparator1085); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getFullStopKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:441:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,14,FOLLOW_14_in_ruleSeparator1104); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getHyphenMinusKeyword_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:448:2: kw= '!'
                    {
                    kw=(Token)input.LT(1);
                    match(input,15,FOLLOW_15_in_ruleSeparator1123); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getExclamationMarkKeyword_2(), null); 
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:455:2: kw= '@'
                    {
                    kw=(Token)input.LT(1);
                    match(input,16,FOLLOW_16_in_ruleSeparator1142); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getCommercialAtKeyword_3(), null); 
                        

                    }
                    break;
                case 5 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:462:2: kw= '$'
                    {
                    kw=(Token)input.LT(1);
                    match(input,17,FOLLOW_17_in_ruleSeparator1161); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getDollarSignKeyword_4(), null); 
                        

                    }
                    break;
                case 6 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:469:2: kw= '%'
                    {
                    kw=(Token)input.LT(1);
                    match(input,18,FOLLOW_18_in_ruleSeparator1180); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getPercentSignKeyword_5(), null); 
                        

                    }
                    break;
                case 7 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:476:2: kw= '&'
                    {
                    kw=(Token)input.LT(1);
                    match(input,19,FOLLOW_19_in_ruleSeparator1199); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getAmpersandKeyword_6(), null); 
                        

                    }
                    break;
                case 8 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:483:2: kw= '/'
                    {
                    kw=(Token)input.LT(1);
                    match(input,13,FOLLOW_13_in_ruleSeparator1218); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getSolidusKeyword_7(), null); 
                        

                    }
                    break;
                case 9 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:490:2: kw= '+'
                    {
                    kw=(Token)input.LT(1);
                    match(input,20,FOLLOW_20_in_ruleSeparator1237); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getPlusSignKeyword_8(), null); 
                        

                    }
                    break;
                case 10 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:497:2: kw= '='
                    {
                    kw=(Token)input.LT(1);
                    match(input,21,FOLLOW_21_in_ruleSeparator1256); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getEqualsSignKeyword_9(), null); 
                        

                    }
                    break;
                case 11 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:504:2: kw= '*'
                    {
                    kw=(Token)input.LT(1);
                    match(input,22,FOLLOW_22_in_ruleSeparator1275); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getAsteriskKeyword_10(), null); 
                        

                    }
                    break;
                case 12 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:511:2: kw= '<'
                    {
                    kw=(Token)input.LT(1);
                    match(input,23,FOLLOW_23_in_ruleSeparator1294); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getLessThanSignKeyword_11(), null); 
                        

                    }
                    break;
                case 13 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:518:2: kw= '>'
                    {
                    kw=(Token)input.LT(1);
                    match(input,24,FOLLOW_24_in_ruleSeparator1313); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getGreaterThanSignKeyword_12(), null); 
                        

                    }
                    break;
                case 14 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:525:2: kw= ':'
                    {
                    kw=(Token)input.LT(1);
                    match(input,25,FOLLOW_25_in_ruleSeparator1332); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getSeparatorAccess().getColonKeyword_13(), null); 
                        

                    }
                    break;
                case 15 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:532:2: kw= '?'
                    {
                    kw=(Token)input.LT(1);
                    match(input,26,FOLLOW_26_in_ruleSeparator1351); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:545:1: entryRuleAlfanumSym returns [String current=null] : iv_ruleAlfanumSym= ruleAlfanumSym EOF ;
    public final String entryRuleAlfanumSym() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAlfanumSym = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:545:51: (iv_ruleAlfanumSym= ruleAlfanumSym EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:546:2: iv_ruleAlfanumSym= ruleAlfanumSym EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAlfanumSymRule(), currentNode); 
            pushFollow(FOLLOW_ruleAlfanumSym_in_entryRuleAlfanumSym1390);
            iv_ruleAlfanumSym=ruleAlfanumSym();
            _fsp--;

             current =iv_ruleAlfanumSym.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAlfanumSym1401); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:553:1: ruleAlfanumSym returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* ) ;
    public final AntlrDatatypeRuleToken ruleAlfanumSym() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_INT_1=null;
        Token this_ID_3=null;
        Token this_INT_4=null;
        AntlrDatatypeRuleToken this_Separator_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:559:6: ( ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:560:1: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:560:1: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:560:2: (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )*
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:560:2: (this_ID_0= RULE_ID | this_INT_1= RULE_INT )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_INT) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("560:2: (this_ID_0= RULE_ID | this_INT_1= RULE_INT )", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:560:7: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAlfanumSym1442); 

                    		current.merge(this_ID_0);
                        
                     
                        createLeafNode(grammarAccess.getAlfanumSymAccess().getIDTerminalRuleCall_0_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:568:10: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAlfanumSym1468); 

                    		current.merge(this_INT_1);
                        
                     
                        createLeafNode(grammarAccess.getAlfanumSymAccess().getINTTerminalRuleCall_0_1(), null); 
                        

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:575:2: (this_Separator_2= ruleSeparator | this_ID_3= RULE_ID | this_INT_4= RULE_INT )*
            loop7:
            do {
                int alt7=4;
                switch ( input.LA(1) ) {
                case 11:
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
                case 26:
                    {
                    alt7=1;
                    }
                    break;
                case RULE_ID:
                    {
                    alt7=2;
                    }
                    break;
                case RULE_INT:
                    {
                    alt7=3;
                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:576:5: this_Separator_2= ruleSeparator
            	    {
            	     
            	            currentNode=createCompositeNode(grammarAccess.getAlfanumSymAccess().getSeparatorParserRuleCall_1_0(), currentNode); 
            	        
            	    pushFollow(FOLLOW_ruleSeparator_in_ruleAlfanumSym1497);
            	    this_Separator_2=ruleSeparator();
            	    _fsp--;


            	    		current.merge(this_Separator_2);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:587:10: this_ID_3= RULE_ID
            	    {
            	    this_ID_3=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAlfanumSym1523); 

            	    		current.merge(this_ID_3);
            	        
            	     
            	        createLeafNode(grammarAccess.getAlfanumSymAccess().getIDTerminalRuleCall_1_1(), null); 
            	        

            	    }
            	    break;
            	case 3 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:595:10: this_INT_4= RULE_INT
            	    {
            	    this_INT_4=(Token)input.LT(1);
            	    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAlfanumSym1549); 

            	    		current.merge(this_INT_4);
            	        
            	     
            	        createLeafNode(grammarAccess.getAlfanumSymAccess().getINTTerminalRuleCall_1_2(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop7;
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:610:1: entryRuleVersion returns [String current=null] : iv_ruleVersion= ruleVersion EOF ;
    public final String entryRuleVersion() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVersion = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:610:48: (iv_ruleVersion= ruleVersion EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:611:2: iv_ruleVersion= ruleVersion EOF
            {
             currentNode = createCompositeNode(grammarAccess.getVersionRule(), currentNode); 
            pushFollow(FOLLOW_ruleVersion_in_entryRuleVersion1595);
            iv_ruleVersion=ruleVersion();
            _fsp--;

             current =iv_ruleVersion.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVersion1606); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:618:1: ruleVersion returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym ) ;
    public final AntlrDatatypeRuleToken ruleVersion() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        AntlrDatatypeRuleToken this_AlfanumSym_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:624:6: ( (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:625:1: (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:625:1: (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_STRING) ) {
                alt8=1;
            }
            else if ( (LA8_0==RULE_ID||LA8_0==RULE_INT) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("625:1: (this_STRING_0= RULE_STRING | this_AlfanumSym_1= ruleAlfanumSym )", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:625:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleVersion1646); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getVersionAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:634:5: this_AlfanumSym_1= ruleAlfanumSym
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getVersionAccess().getAlfanumSymParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAlfanumSym_in_ruleVersion1679);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:652:1: entryRuleVersionRange returns [String current=null] : iv_ruleVersionRange= ruleVersionRange EOF ;
    public final String entryRuleVersionRange() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVersionRange = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:652:53: (iv_ruleVersionRange= ruleVersionRange EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:653:2: iv_ruleVersionRange= ruleVersionRange EOF
            {
             currentNode = createCompositeNode(grammarAccess.getVersionRangeRule(), currentNode); 
            pushFollow(FOLLOW_ruleVersionRange_in_entryRuleVersionRange1723);
            iv_ruleVersionRange=ruleVersionRange();
            _fsp--;

             current =iv_ruleVersionRange.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVersionRange1734); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:660:1: ruleVersionRange returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion ) ;
    public final AntlrDatatypeRuleToken ruleVersionRange() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Version_2 = null;

        AntlrDatatypeRuleToken this_Version_4 = null;

        AntlrDatatypeRuleToken this_Version_7 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:666:6: ( ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:667:1: ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:667:1: ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=27 && LA12_0<=28)) ) {
                alt12=1;
            }
            else if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_INT)) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("667:1: ( ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) ) | this_Version_7= ruleVersion )", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:667:2: ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:667:2: ( (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:667:3: (kw= '[' | kw= '(' ) this_Version_2= ruleVersion (kw= ',' this_Version_4= ruleVersion )? (kw= ']' | kw= ')' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:667:3: (kw= '[' | kw= '(' )
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==27) ) {
                        alt9=1;
                    }
                    else if ( (LA9_0==28) ) {
                        alt9=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("667:3: (kw= '[' | kw= '(' )", 9, 0, input);

                        throw nvae;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:668:2: kw= '['
                            {
                            kw=(Token)input.LT(1);
                            match(input,27,FOLLOW_27_in_ruleVersionRange1774); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getLeftSquareBracketKeyword_0_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:675:2: kw= '('
                            {
                            kw=(Token)input.LT(1);
                            match(input,28,FOLLOW_28_in_ruleVersionRange1793); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getLeftParenthesisKeyword_0_0_1(), null); 
                                

                            }
                            break;

                    }

                     
                            currentNode=createCompositeNode(grammarAccess.getVersionRangeAccess().getVersionParserRuleCall_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleVersion_in_ruleVersionRange1816);
                    this_Version_2=ruleVersion();
                    _fsp--;


                    		current.merge(this_Version_2);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:691:1: (kw= ',' this_Version_4= ruleVersion )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==29) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:692:2: kw= ',' this_Version_4= ruleVersion
                            {
                            kw=(Token)input.LT(1);
                            match(input,29,FOLLOW_29_in_ruleVersionRange1835); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getCommaKeyword_0_2_0(), null); 
                                
                             
                                    currentNode=createCompositeNode(grammarAccess.getVersionRangeAccess().getVersionParserRuleCall_0_2_1(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleVersion_in_ruleVersionRange1857);
                            this_Version_4=ruleVersion();
                            _fsp--;


                            		current.merge(this_Version_4);
                                
                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:708:3: (kw= ']' | kw= ')' )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==30) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0==31) ) {
                        alt11=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("708:3: (kw= ']' | kw= ')' )", 11, 0, input);

                        throw nvae;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:709:2: kw= ']'
                            {
                            kw=(Token)input.LT(1);
                            match(input,30,FOLLOW_30_in_ruleVersionRange1878); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getRightSquareBracketKeyword_0_3_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:716:2: kw= ')'
                            {
                            kw=(Token)input.LT(1);
                            match(input,31,FOLLOW_31_in_ruleVersionRange1897); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getVersionRangeAccess().getRightParenthesisKeyword_0_3_1(), null); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:723:5: this_Version_7= ruleVersion
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getVersionRangeAccess().getVersionParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleVersion_in_ruleVersionRange1927);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:741:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:741:48: (iv_ruleImport= ruleImport EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:742:2: iv_ruleImport= ruleImport EOF
            {
             currentNode = createCompositeNode(grammarAccess.getImportRule(), currentNode); 
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport1970);
            iv_ruleImport=ruleImport();
            _fsp--;

             current =iv_ruleImport; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport1980); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:749:1: ruleImport returns [EObject current=null] : ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_importClass_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:754:6: ( ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:755:1: ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:755:1: ( 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:755:2: 'import' (lv_importClass_1= ruleQualifiedName ) ( '.*' )? ';'
            {
            match(input,32,FOLLOW_32_in_ruleImport2014); 

                    createLeafNode(grammarAccess.getImportAccess().getImportKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:759:1: (lv_importClass_1= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:762:6: lv_importClass_1= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getImportAccess().getImportClassQualifiedNameParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleImport2048);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:780:2: ( '.*' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==33) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:780:3: '.*'
                    {
                    match(input,33,FOLLOW_33_in_ruleImport2062); 

                            createLeafNode(grammarAccess.getImportAccess().getFullStopAsteriskKeyword_2(), null); 
                        

                    }
                    break;

            }

            match(input,34,FOLLOW_34_in_ruleImport2073); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:795:1: entryRuleUnit returns [EObject current=null] : iv_ruleUnit= ruleUnit EOF ;
    public final EObject entryRuleUnit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnit = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:795:46: (iv_ruleUnit= ruleUnit EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:796:2: iv_ruleUnit= ruleUnit EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnitRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnit_in_entryRuleUnit2106);
            iv_ruleUnit=ruleUnit();
            _fsp--;

             current =iv_ruleUnit; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnit2116); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:803:1: ruleUnit returns [EObject current=null] : ( (lv_executionMode_0= ruleExecutionMode )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( 'properties' (lv_propertyStatements_11= ruleNamedPropertyStatements ) )* ( ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' ) | ( 'advice' (lv_advice_39= ruleNamedAdvice ) ) | ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' ) | ( 'sequential' (lv_synchronize_45= ruleSynchronization ) ) | (lv_parts_46= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' ) )* '}' ) ;
    public final EObject ruleUnit() throws RecognitionException {
        EObject current = null;

        Enumerator lv_executionMode_0 = null;

        AntlrDatatypeRuleToken lv_name_2 = null;

        AntlrDatatypeRuleToken lv_version_4 = null;

        AntlrDatatypeRuleToken lv_implements_6 = null;

        AntlrDatatypeRuleToken lv_implements_8 = null;

        EObject lv_propertyStatements_11 = null;

        EObject lv_providedCapability_14 = null;

        EObject lv_providedCapability_18 = null;

        EObject lv_requiredCapabilities_22 = null;

        EObject lv_requiredCapabilities_26 = null;

        EObject lv_metaRequiredCapabilities_31 = null;

        EObject lv_metaRequiredCapabilities_36 = null;

        EObject lv_advice_39 = null;

        EObject lv_synchronize_42 = null;

        EObject lv_synchronize_45 = null;

        EObject lv_parts_46 = null;

        EObject lv_repositoryConfig_49 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:810:6: ( ( (lv_executionMode_0= ruleExecutionMode )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( 'properties' (lv_propertyStatements_11= ruleNamedPropertyStatements ) )* ( ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' ) | ( 'advice' (lv_advice_39= ruleNamedAdvice ) ) | ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' ) | ( 'sequential' (lv_synchronize_45= ruleSynchronization ) ) | (lv_parts_46= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' ) )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:811:1: ( (lv_executionMode_0= ruleExecutionMode )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( 'properties' (lv_propertyStatements_11= ruleNamedPropertyStatements ) )* ( ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' ) | ( 'advice' (lv_advice_39= ruleNamedAdvice ) ) | ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' ) | ( 'sequential' (lv_synchronize_45= ruleSynchronization ) ) | (lv_parts_46= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' ) )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:811:1: ( (lv_executionMode_0= ruleExecutionMode )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( 'properties' (lv_propertyStatements_11= ruleNamedPropertyStatements ) )* ( ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' ) | ( 'advice' (lv_advice_39= ruleNamedAdvice ) ) | ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' ) | ( 'sequential' (lv_synchronize_45= ruleSynchronization ) ) | (lv_parts_46= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' ) )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:811:2: (lv_executionMode_0= ruleExecutionMode )? 'unit' (lv_name_2= ruleUnitName )? ( 'version' (lv_version_4= ruleVersion ) )? ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )? '{' ( 'properties' (lv_propertyStatements_11= ruleNamedPropertyStatements ) )* ( ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' ) | ( 'advice' (lv_advice_39= ruleNamedAdvice ) ) | ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' ) | ( 'sequential' (lv_synchronize_45= ruleSynchronization ) ) | (lv_parts_46= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' ) )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:811:2: (lv_executionMode_0= ruleExecutionMode )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==45||LA14_0==79) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:814:6: lv_executionMode_0= ruleExecutionMode
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getExecutionModeExecutionModeEnumRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleExecutionMode_in_ruleUnit2175);
                    lv_executionMode_0=ruleExecutionMode();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "executionMode", lv_executionMode_0, "ExecutionMode", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,35,FOLLOW_35_in_ruleUnit2189); 

                    createLeafNode(grammarAccess.getUnitAccess().getUnitKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:836:1: (lv_name_2= ruleUnitName )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=RULE_ID && LA15_0<=RULE_STRING)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:839:6: lv_name_2= ruleUnitName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getNameUnitNameParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUnitName_in_ruleUnit2223);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:857:3: ( 'version' (lv_version_4= ruleVersion ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==36) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:857:4: 'version' (lv_version_4= ruleVersion )
                    {
                    match(input,36,FOLLOW_36_in_ruleUnit2238); 

                            createLeafNode(grammarAccess.getUnitAccess().getVersionKeyword_3_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:861:1: (lv_version_4= ruleVersion )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:864:6: lv_version_4= ruleVersion
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getVersionVersionParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVersion_in_ruleUnit2272);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:882:4: ( 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==37) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:882:5: 'implements' (lv_implements_6= ruleInterfaceName ) ( ',' (lv_implements_8= ruleInterfaceName ) )*
                    {
                    match(input,37,FOLLOW_37_in_ruleUnit2288); 

                            createLeafNode(grammarAccess.getUnitAccess().getImplementsKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:886:1: (lv_implements_6= ruleInterfaceName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:889:6: lv_implements_6= ruleInterfaceName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getImplementsInterfaceNameParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInterfaceName_in_ruleUnit2322);
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

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:907:2: ( ',' (lv_implements_8= ruleInterfaceName ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==29) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:907:3: ',' (lv_implements_8= ruleInterfaceName )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleUnit2336); 

                    	            createLeafNode(grammarAccess.getUnitAccess().getCommaKeyword_4_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:911:1: (lv_implements_8= ruleInterfaceName )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:914:6: lv_implements_8= ruleInterfaceName
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getImplementsInterfaceNameParserRuleCall_4_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleInterfaceName_in_ruleUnit2370);
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
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleUnit2387); 

                    createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_5(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:936:1: ( 'properties' (lv_propertyStatements_11= ruleNamedPropertyStatements ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==39) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:936:2: 'properties' (lv_propertyStatements_11= ruleNamedPropertyStatements )
            	    {
            	    match(input,39,FOLLOW_39_in_ruleUnit2397); 

            	            createLeafNode(grammarAccess.getUnitAccess().getPropertiesKeyword_6_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:940:1: (lv_propertyStatements_11= ruleNamedPropertyStatements )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:943:6: lv_propertyStatements_11= ruleNamedPropertyStatements
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getPropertyStatementsNamedPropertyStatementsParserRuleCall_6_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleNamedPropertyStatements_in_ruleUnit2431);
            	    lv_propertyStatements_11=ruleNamedPropertyStatements();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "propertyStatements", lv_propertyStatements_11, "NamedPropertyStatements", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:961:4: ( ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' ) | ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' ) | ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' ) | ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' ) | ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' ) | ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' ) | ( 'advice' (lv_advice_39= ruleNamedAdvice ) ) | ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' ) | ( 'sequential' (lv_synchronize_45= ruleSynchronization ) ) | (lv_parts_46= ruleBuildPart ) | ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' ) )*
            loop25:
            do {
                int alt25=12;
                switch ( input.LA(1) ) {
                case 40:
                    {
                    int LA25_2 = input.LA(2);

                    if ( (LA25_2==38) ) {
                        alt25=1;
                    }
                    else if ( (LA25_2==RULE_ID||LA25_2==47) ) {
                        alt25=2;
                    }


                    }
                    break;
                case 42:
                    {
                    int LA25_3 = input.LA(2);

                    if ( (LA25_3==38) ) {
                        alt25=3;
                    }
                    else if ( (LA25_3==RULE_ID||LA25_3==47) ) {
                        alt25=4;
                    }


                    }
                    break;
                case 43:
                    {
                    int LA25_4 = input.LA(2);

                    if ( (LA25_4==42) ) {
                        int LA25_13 = input.LA(3);

                        if ( (LA25_13==38) ) {
                            alt25=5;
                        }
                        else if ( (LA25_13==RULE_ID||LA25_13==47) ) {
                            alt25=6;
                        }


                    }


                    }
                    break;
                case 44:
                    {
                    alt25=7;
                    }
                    break;
                case 45:
                    {
                    switch ( input.LA(2) ) {
                    case 38:
                        {
                        alt25=8;
                        }
                        break;
                    case 55:
                    case 58:
                        {
                        alt25=10;
                        }
                        break;
                    case RULE_ID:
                    case RULE_STRING:
                        {
                        alt25=9;
                        }
                        break;

                    }

                    }
                    break;
                case 52:
                case 55:
                case 58:
                case 77:
                case 78:
                case 79:
                    {
                    alt25=10;
                    }
                    break;
                case 46:
                    {
                    alt25=11;
                    }
                    break;

                }

                switch (alt25) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:961:5: ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:961:5: ( 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:961:6: 'provides' '{' ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+ '}'
            	    {
            	    match(input,40,FOLLOW_40_in_ruleUnit2448); 

            	            createLeafNode(grammarAccess.getUnitAccess().getProvidesKeyword_7_0_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2457); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_7_0_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:969:1: ( (lv_providedCapability_14= ruleProvidedCapability ) ';' )+
            	    int cnt20=0;
            	    loop20:
            	    do {
            	        int alt20=2;
            	        int LA20_0 = input.LA(1);

            	        if ( (LA20_0==RULE_ID||LA20_0==47) ) {
            	            alt20=1;
            	        }


            	        switch (alt20) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:969:2: (lv_providedCapability_14= ruleProvidedCapability ) ';'
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:969:2: (lv_providedCapability_14= ruleProvidedCapability )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:972:6: lv_providedCapability_14= ruleProvidedCapability
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getProvidedCapabilityProvidedCapabilityParserRuleCall_7_0_2_0_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleUnit2492);
            	    	    lv_providedCapability_14=ruleProvidedCapability();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "providedCapability", lv_providedCapability_14, "ProvidedCapability", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,34,FOLLOW_34_in_ruleUnit2505); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_7_0_2_1(), null); 
            	    	        

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

            	    match(input,41,FOLLOW_41_in_ruleUnit2516); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_7_0_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:999:6: ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:999:6: ( 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:999:7: 'provides' (lv_providedCapability_18= ruleProvidedCapability ) ';'
            	    {
            	    match(input,40,FOLLOW_40_in_ruleUnit2533); 

            	            createLeafNode(grammarAccess.getUnitAccess().getProvidesKeyword_7_1_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1003:1: (lv_providedCapability_18= ruleProvidedCapability )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1006:6: lv_providedCapability_18= ruleProvidedCapability
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getProvidedCapabilityProvidedCapabilityParserRuleCall_7_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleUnit2567);
            	    lv_providedCapability_18=ruleProvidedCapability();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "providedCapability", lv_providedCapability_18, "ProvidedCapability", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,34,FOLLOW_34_in_ruleUnit2580); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_7_1_2(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 3 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1029:6: ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1029:6: ( 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1029:7: 'requires' '{' ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+ '}'
            	    {
            	    match(input,42,FOLLOW_42_in_ruleUnit2597); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_7_2_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2606); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_7_2_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1037:1: ( (lv_requiredCapabilities_22= ruleRequiredCapability ) ';' )+
            	    int cnt21=0;
            	    loop21:
            	    do {
            	        int alt21=2;
            	        int LA21_0 = input.LA(1);

            	        if ( (LA21_0==RULE_ID||LA21_0==47) ) {
            	            alt21=1;
            	        }


            	        switch (alt21) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1037:2: (lv_requiredCapabilities_22= ruleRequiredCapability ) ';'
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1037:2: (lv_requiredCapabilities_22= ruleRequiredCapability )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1040:6: lv_requiredCapabilities_22= ruleRequiredCapability
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getRequiredCapabilitiesRequiredCapabilityParserRuleCall_7_2_2_0_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2641);
            	    	    lv_requiredCapabilities_22=ruleRequiredCapability();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "requiredCapabilities", lv_requiredCapabilities_22, "RequiredCapability", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,34,FOLLOW_34_in_ruleUnit2654); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_7_2_2_1(), null); 
            	    	        

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

            	    match(input,41,FOLLOW_41_in_ruleUnit2665); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_7_2_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1067:6: ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1067:6: ( 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1067:7: 'requires' (lv_requiredCapabilities_26= ruleRequiredCapability ) ';'
            	    {
            	    match(input,42,FOLLOW_42_in_ruleUnit2682); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_7_3_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1071:1: (lv_requiredCapabilities_26= ruleRequiredCapability )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1074:6: lv_requiredCapabilities_26= ruleRequiredCapability
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getRequiredCapabilitiesRequiredCapabilityParserRuleCall_7_3_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2716);
            	    lv_requiredCapabilities_26=ruleRequiredCapability();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "requiredCapabilities", lv_requiredCapabilities_26, "RequiredCapability", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,34,FOLLOW_34_in_ruleUnit2729); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_7_3_2(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 5 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1097:6: ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1097:6: ( 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1097:7: 'meta' 'requires' '{' ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+ '}'
            	    {
            	    match(input,43,FOLLOW_43_in_ruleUnit2746); 

            	            createLeafNode(grammarAccess.getUnitAccess().getMetaKeyword_7_4_0(), null); 
            	        
            	    match(input,42,FOLLOW_42_in_ruleUnit2755); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_7_4_1(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2764); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_7_4_2(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1109:1: ( (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';' )+
            	    int cnt22=0;
            	    loop22:
            	    do {
            	        int alt22=2;
            	        int LA22_0 = input.LA(1);

            	        if ( (LA22_0==RULE_ID||LA22_0==47) ) {
            	            alt22=1;
            	        }


            	        switch (alt22) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1109:2: (lv_metaRequiredCapabilities_31= ruleRequiredCapability ) ';'
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1109:2: (lv_metaRequiredCapabilities_31= ruleRequiredCapability )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1112:6: lv_metaRequiredCapabilities_31= ruleRequiredCapability
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getMetaRequiredCapabilitiesRequiredCapabilityParserRuleCall_7_4_3_0_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2799);
            	    	    lv_metaRequiredCapabilities_31=ruleRequiredCapability();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "metaRequiredCapabilities", lv_metaRequiredCapabilities_31, "RequiredCapability", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }

            	    	    match(input,34,FOLLOW_34_in_ruleUnit2812); 

            	    	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_7_4_3_1(), null); 
            	    	        

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

            	    match(input,41,FOLLOW_41_in_ruleUnit2823); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_7_4_4(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 6 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1139:6: ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1139:6: ( 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1139:7: 'meta' 'requires' (lv_metaRequiredCapabilities_36= ruleRequiredCapability ) ';'
            	    {
            	    match(input,43,FOLLOW_43_in_ruleUnit2840); 

            	            createLeafNode(grammarAccess.getUnitAccess().getMetaKeyword_7_5_0(), null); 
            	        
            	    match(input,42,FOLLOW_42_in_ruleUnit2849); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRequiresKeyword_7_5_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1147:1: (lv_metaRequiredCapabilities_36= ruleRequiredCapability )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1150:6: lv_metaRequiredCapabilities_36= ruleRequiredCapability
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getMetaRequiredCapabilitiesRequiredCapabilityParserRuleCall_7_5_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleRequiredCapability_in_ruleUnit2883);
            	    lv_metaRequiredCapabilities_36=ruleRequiredCapability();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "metaRequiredCapabilities", lv_metaRequiredCapabilities_36, "RequiredCapability", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,34,FOLLOW_34_in_ruleUnit2896); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSemicolonKeyword_7_5_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 7 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1173:6: ( 'advice' (lv_advice_39= ruleNamedAdvice ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1173:6: ( 'advice' (lv_advice_39= ruleNamedAdvice ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1173:7: 'advice' (lv_advice_39= ruleNamedAdvice )
            	    {
            	    match(input,44,FOLLOW_44_in_ruleUnit2913); 

            	            createLeafNode(grammarAccess.getUnitAccess().getAdviceKeyword_7_6_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1177:1: (lv_advice_39= ruleNamedAdvice )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1180:6: lv_advice_39= ruleNamedAdvice
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getAdviceNamedAdviceParserRuleCall_7_6_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleNamedAdvice_in_ruleUnit2947);
            	    lv_advice_39=ruleNamedAdvice();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "advice", lv_advice_39, "NamedAdvice", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1199:6: ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1199:6: ( 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1199:7: 'sequential' '{' (lv_synchronize_42= ruleSynchronization )+ '}'
            	    {
            	    match(input,45,FOLLOW_45_in_ruleUnit2968); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSequentialKeyword_7_7_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit2977); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_7_7_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1207:1: (lv_synchronize_42= ruleSynchronization )+
            	    int cnt23=0;
            	    loop23:
            	    do {
            	        int alt23=2;
            	        int LA23_0 = input.LA(1);

            	        if ( ((LA23_0>=RULE_ID && LA23_0<=RULE_STRING)) ) {
            	            alt23=1;
            	        }


            	        switch (alt23) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1210:6: lv_synchronize_42= ruleSynchronization
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getSynchronizeSynchronizationParserRuleCall_7_7_2_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleSynchronization_in_ruleUnit3011);
            	    	    lv_synchronize_42=ruleSynchronization();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "synchronize", lv_synchronize_42, "Synchronization", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt23 >= 1 ) break loop23;
            	                EarlyExitException eee =
            	                    new EarlyExitException(23, input);
            	                throw eee;
            	        }
            	        cnt23++;
            	    } while (true);

            	    match(input,41,FOLLOW_41_in_ruleUnit3025); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_7_7_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 9 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1233:6: ( 'sequential' (lv_synchronize_45= ruleSynchronization ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1233:6: ( 'sequential' (lv_synchronize_45= ruleSynchronization ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1233:7: 'sequential' (lv_synchronize_45= ruleSynchronization )
            	    {
            	    match(input,45,FOLLOW_45_in_ruleUnit3042); 

            	            createLeafNode(grammarAccess.getUnitAccess().getSequentialKeyword_7_8_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1237:1: (lv_synchronize_45= ruleSynchronization )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1240:6: lv_synchronize_45= ruleSynchronization
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getSynchronizeSynchronizationParserRuleCall_7_8_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleSynchronization_in_ruleUnit3076);
            	    lv_synchronize_45=ruleSynchronization();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "synchronize", lv_synchronize_45, "Synchronization", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 10 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1259:6: (lv_parts_46= ruleBuildPart )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1259:6: (lv_parts_46= ruleBuildPart )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1262:6: lv_parts_46= ruleBuildPart
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getPartsBuildPartParserRuleCall_7_9_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleBuildPart_in_ruleUnit3121);
            	    lv_parts_46=ruleBuildPart();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "parts", lv_parts_46, "BuildPart", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 11 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1281:6: ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1281:6: ( 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1281:7: 'repositories' '{' (lv_repositoryConfig_49= ruleRepositoryConfiguration )* '}'
            	    {
            	    match(input,46,FOLLOW_46_in_ruleUnit3141); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRepositoriesKeyword_7_10_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleUnit3150); 

            	            createLeafNode(grammarAccess.getUnitAccess().getLeftCurlyBracketKeyword_7_10_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1289:1: (lv_repositoryConfig_49= ruleRepositoryConfiguration )*
            	    loop24:
            	    do {
            	        int alt24=2;
            	        int LA24_0 = input.LA(1);

            	        if ( (LA24_0==RULE_STRING||LA24_0==62) ) {
            	            alt24=1;
            	        }


            	        switch (alt24) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1292:6: lv_repositoryConfig_49= ruleRepositoryConfiguration
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getUnitAccess().getRepositoryConfigRepositoryConfigurationParserRuleCall_7_10_2_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleRepositoryConfiguration_in_ruleUnit3184);
            	    	    lv_repositoryConfig_49=ruleRepositoryConfiguration();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getUnitRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "repositoryConfig", lv_repositoryConfig_49, "RepositoryConfiguration", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop24;
            	        }
            	    } while (true);

            	    match(input,41,FOLLOW_41_in_ruleUnit3198); 

            	            createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_7_10_3(), null); 
            	        

            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleUnit3210); 

                    createLeafNode(grammarAccess.getUnitAccess().getRightCurlyBracketKeyword_8(), null); 
                

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1325:1: entryRuleProvidedCapability returns [EObject current=null] : iv_ruleProvidedCapability= ruleProvidedCapability EOF ;
    public final EObject entryRuleProvidedCapability() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProvidedCapability = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1325:60: (iv_ruleProvidedCapability= ruleProvidedCapability EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1326:2: iv_ruleProvidedCapability= ruleProvidedCapability EOF
            {
             currentNode = createCompositeNode(grammarAccess.getProvidedCapabilityRule(), currentNode); 
            pushFollow(FOLLOW_ruleProvidedCapability_in_entryRuleProvidedCapability3243);
            iv_ruleProvidedCapability=ruleProvidedCapability();
            _fsp--;

             current =iv_ruleProvidedCapability; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleProvidedCapability3253); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1333:1: ruleProvidedCapability returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? ) ;
    public final EObject ruleProvidedCapability() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_1 = null;

        AntlrDatatypeRuleToken lv_interface_2 = null;

        AntlrDatatypeRuleToken lv_name_4 = null;

        AntlrDatatypeRuleToken lv_version_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1338:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1339:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1339:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1339:2: ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_version_6= ruleVersion ) )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1339:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==47) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1339:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,47,FOLLOW_47_in_ruleProvidedCapability3288); 

                            createLeafNode(grammarAccess.getProvidedCapabilityAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1343:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1346:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_ruleProvidedCapability3322);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1364:4: (lv_interface_2= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1367:6: lv_interface_2= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getInterfaceQualifiedNameParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3362);
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

            match(input,13,FOLLOW_13_in_ruleProvidedCapability3375); 

                    createLeafNode(grammarAccess.getProvidedCapabilityAccess().getSolidusKeyword_2(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1389:1: (lv_name_4= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1392:6: lv_name_4= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getNameQualifiedNameParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3409);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1410:2: ( '/' (lv_version_6= ruleVersion ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==13) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1410:3: '/' (lv_version_6= ruleVersion )
                    {
                    match(input,13,FOLLOW_13_in_ruleProvidedCapability3423); 

                            createLeafNode(grammarAccess.getProvidedCapabilityAccess().getSolidusKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1414:1: (lv_version_6= ruleVersion )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1417:6: lv_version_6= ruleVersion
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getProvidedCapabilityAccess().getVersionVersionParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVersion_in_ruleProvidedCapability3457);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1442:1: entryRuleRequiredCapability returns [EObject current=null] : iv_ruleRequiredCapability= ruleRequiredCapability EOF ;
    public final EObject entryRuleRequiredCapability() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequiredCapability = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1442:60: (iv_ruleRequiredCapability= ruleRequiredCapability EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1443:2: iv_ruleRequiredCapability= ruleRequiredCapability EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRequiredCapabilityRule(), currentNode); 
            pushFollow(FOLLOW_ruleRequiredCapability_in_entryRuleRequiredCapability3496);
            iv_ruleRequiredCapability=ruleRequiredCapability();
            _fsp--;

             current =iv_ruleRequiredCapability; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRequiredCapability3506); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1450:1: ruleRequiredCapability returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? ) ;
    public final EObject ruleRequiredCapability() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_1 = null;

        AntlrDatatypeRuleToken lv_interface_2 = null;

        AntlrDatatypeRuleToken lv_name_4 = null;

        AntlrDatatypeRuleToken lv_range_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1455:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1456:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1456:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1456:2: ( 'when' (lv_filter_1= ruleFilter ) )? (lv_interface_2= ruleQualifiedName ) '/' (lv_name_4= ruleQualifiedName ) ( '/' (lv_range_6= ruleVersionRange ) )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1456:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==47) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1456:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,47,FOLLOW_47_in_ruleRequiredCapability3541); 

                            createLeafNode(grammarAccess.getRequiredCapabilityAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1460:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1463:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_ruleRequiredCapability3575);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1481:4: (lv_interface_2= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1484:6: lv_interface_2= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getInterfaceQualifiedNameParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3615);
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

            match(input,13,FOLLOW_13_in_ruleRequiredCapability3628); 

                    createLeafNode(grammarAccess.getRequiredCapabilityAccess().getSolidusKeyword_2(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1506:1: (lv_name_4= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1509:6: lv_name_4= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getNameQualifiedNameParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3662);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1527:2: ( '/' (lv_range_6= ruleVersionRange ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==13) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1527:3: '/' (lv_range_6= ruleVersionRange )
                    {
                    match(input,13,FOLLOW_13_in_ruleRequiredCapability3676); 

                            createLeafNode(grammarAccess.getRequiredCapabilityAccess().getSolidusKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1531:1: (lv_range_6= ruleVersionRange )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1534:6: lv_range_6= ruleVersionRange
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRequiredCapabilityAccess().getRangeVersionRangeParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVersionRange_in_ruleRequiredCapability3710);
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


    // $ANTLR start entryRuleNamedPropertyStatements
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1559:1: entryRuleNamedPropertyStatements returns [EObject current=null] : iv_ruleNamedPropertyStatements= ruleNamedPropertyStatements EOF ;
    public final EObject entryRuleNamedPropertyStatements() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamedPropertyStatements = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1559:65: (iv_ruleNamedPropertyStatements= ruleNamedPropertyStatements EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1560:2: iv_ruleNamedPropertyStatements= ruleNamedPropertyStatements EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamedPropertyStatementsRule(), currentNode); 
            pushFollow(FOLLOW_ruleNamedPropertyStatements_in_entryRuleNamedPropertyStatements3749);
            iv_ruleNamedPropertyStatements=ruleNamedPropertyStatements();
            _fsp--;

             current =iv_ruleNamedPropertyStatements; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamedPropertyStatements3759); 

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
    // $ANTLR end entryRuleNamedPropertyStatements


    // $ANTLR start ruleNamedPropertyStatements
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1567:1: ruleNamedPropertyStatements returns [EObject current=null] : ( (lv_name_0= ruleQualifiedName )? (lv_statements_1= rulePropertyStatements ) ) ;
    public final EObject ruleNamedPropertyStatements() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;

        EObject lv_statements_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1572:6: ( ( (lv_name_0= ruleQualifiedName )? (lv_statements_1= rulePropertyStatements ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1573:1: ( (lv_name_0= ruleQualifiedName )? (lv_statements_1= rulePropertyStatements ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1573:1: ( (lv_name_0= ruleQualifiedName )? (lv_statements_1= rulePropertyStatements ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1573:2: (lv_name_0= ruleQualifiedName )? (lv_statements_1= rulePropertyStatements )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1573:2: (lv_name_0= ruleQualifiedName )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_ID) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1576:6: lv_name_0= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNamedPropertyStatementsAccess().getNameQualifiedNameParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleNamedPropertyStatements3818);
                    lv_name_0=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNamedPropertyStatementsRule().getType().getClassifier());
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1594:3: (lv_statements_1= rulePropertyStatements )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1597:6: lv_statements_1= rulePropertyStatements
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNamedPropertyStatementsAccess().getStatementsPropertyStatementsParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePropertyStatements_in_ruleNamedPropertyStatements3857);
            lv_statements_1=rulePropertyStatements();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNamedPropertyStatementsRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "statements", lv_statements_1, "PropertyStatements", currentNode);
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
    // $ANTLR end ruleNamedPropertyStatements


    // $ANTLR start entryRulePropertyStatements
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1622:1: entryRulePropertyStatements returns [EObject current=null] : iv_rulePropertyStatements= rulePropertyStatements EOF ;
    public final EObject entryRulePropertyStatements() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyStatements = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1622:60: (iv_rulePropertyStatements= rulePropertyStatements EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1623:2: iv_rulePropertyStatements= rulePropertyStatements EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPropertyStatementsRule(), currentNode); 
            pushFollow(FOLLOW_rulePropertyStatements_in_entryRulePropertyStatements3894);
            iv_rulePropertyStatements=rulePropertyStatements();
            _fsp--;

             current =iv_rulePropertyStatements; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyStatements3904); 

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
    // $ANTLR end entryRulePropertyStatements


    // $ANTLR start rulePropertyStatements
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1630:1: rulePropertyStatements returns [EObject current=null] : ( '{' ( ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) ) )* '}' ) ;
    public final EObject rulePropertyStatements() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_2 = null;

        EObject lv_statements_3 = null;

        EObject lv_statements_4 = null;

        EObject lv_statements_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1635:6: ( ( '{' ( ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) ) )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1636:1: ( '{' ( ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) ) )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1636:1: ( '{' ( ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) ) )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1636:2: '{' ( ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) ) )* '}'
            {
            match(input,38,FOLLOW_38_in_rulePropertyStatements3938); 

                    createLeafNode(grammarAccess.getPropertyStatementsAccess().getLeftCurlyBracketKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1640:1: ( ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID||LA33_0==38||(LA33_0>=47 && LA33_0<=50)) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1640:2: ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1640:2: ( 'when' (lv_filter_2= ruleFilter ) )?
            	    int alt31=2;
            	    int LA31_0 = input.LA(1);

            	    if ( (LA31_0==47) ) {
            	        alt31=1;
            	    }
            	    switch (alt31) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1640:3: 'when' (lv_filter_2= ruleFilter )
            	            {
            	            match(input,47,FOLLOW_47_in_rulePropertyStatements3949); 

            	                    createLeafNode(grammarAccess.getPropertyStatementsAccess().getWhenKeyword_1_0_0(), null); 
            	                
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1644:1: (lv_filter_2= ruleFilter )
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1647:6: lv_filter_2= ruleFilter
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getPropertyStatementsAccess().getFilterFilterParserRuleCall_1_0_1_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleFilter_in_rulePropertyStatements3983);
            	            lv_filter_2=ruleFilter();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getPropertyStatementsRule().getType().getClassifier());
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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1665:4: ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) )
            	    int alt32=3;
            	    switch ( input.LA(1) ) {
            	    case 50:
            	        {
            	        alt32=1;
            	        }
            	        break;
            	    case RULE_ID:
            	    case 48:
            	    case 49:
            	        {
            	        alt32=2;
            	        }
            	        break;
            	    case 38:
            	        {
            	        alt32=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("1665:4: ( (lv_statements_3= ruleUnsetPropertyStatement ) | (lv_statements_4= rulePropertyStatement ) | (lv_statements_5= rulePropertyStatements ) )", 32, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt32) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1665:5: (lv_statements_3= ruleUnsetPropertyStatement )
            	            {
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1665:5: (lv_statements_3= ruleUnsetPropertyStatement )
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1668:6: lv_statements_3= ruleUnsetPropertyStatement
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getPropertyStatementsAccess().getStatementsUnsetPropertyStatementParserRuleCall_1_1_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleUnsetPropertyStatement_in_rulePropertyStatements4024);
            	            lv_statements_3=ruleUnsetPropertyStatement();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getPropertyStatementsRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        
            	            	        try {
            	            	       		add(current, "statements", lv_statements_3, "UnsetPropertyStatement", currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1687:6: (lv_statements_4= rulePropertyStatement )
            	            {
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1687:6: (lv_statements_4= rulePropertyStatement )
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1690:6: lv_statements_4= rulePropertyStatement
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getPropertyStatementsAccess().getStatementsPropertyStatementParserRuleCall_1_1_1_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulePropertyStatement_in_rulePropertyStatements4068);
            	            lv_statements_4=rulePropertyStatement();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getPropertyStatementsRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        
            	            	        try {
            	            	       		add(current, "statements", lv_statements_4, "PropertyStatement", currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }


            	            }
            	            break;
            	        case 3 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1709:6: (lv_statements_5= rulePropertyStatements )
            	            {
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1709:6: (lv_statements_5= rulePropertyStatements )
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1712:6: lv_statements_5= rulePropertyStatements
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getPropertyStatementsAccess().getStatementsPropertyStatementsParserRuleCall_1_1_2_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulePropertyStatements_in_rulePropertyStatements4112);
            	            lv_statements_5=rulePropertyStatements();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getPropertyStatementsRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        
            	            	        try {
            	            	       		add(current, "statements", lv_statements_5, "PropertyStatements", currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_rulePropertyStatements4128); 

                    createLeafNode(grammarAccess.getPropertyStatementsAccess().getRightCurlyBracketKeyword_2(), null); 
                

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
    // $ANTLR end rulePropertyStatements


    // $ANTLR start entryRulePropertyStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1741:1: entryRulePropertyStatement returns [EObject current=null] : iv_rulePropertyStatement= rulePropertyStatement EOF ;
    public final EObject entryRulePropertyStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyStatement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1741:59: (iv_rulePropertyStatement= rulePropertyStatement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1742:2: iv_rulePropertyStatement= rulePropertyStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPropertyStatementRule(), currentNode); 
            pushFollow(FOLLOW_rulePropertyStatement_in_entryRulePropertyStatement4161);
            iv_rulePropertyStatement=rulePropertyStatement();
            _fsp--;

             current =iv_rulePropertyStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyStatement4171); 

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
    // $ANTLR end entryRulePropertyStatement


    // $ANTLR start rulePropertyStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1749:1: rulePropertyStatement returns [EObject current=null] : ( (lv_local_0= 'local' )? (lv_immutable_1= 'immutable' )? (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' ) ;
    public final EObject rulePropertyStatement() throws RecognitionException {
        EObject current = null;

        Token lv_local_0=null;
        Token lv_immutable_1=null;
        AntlrDatatypeRuleToken lv_key_2 = null;

        EObject lv_value_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1754:6: ( ( (lv_local_0= 'local' )? (lv_immutable_1= 'immutable' )? (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1755:1: ( (lv_local_0= 'local' )? (lv_immutable_1= 'immutable' )? (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1755:1: ( (lv_local_0= 'local' )? (lv_immutable_1= 'immutable' )? (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1755:2: (lv_local_0= 'local' )? (lv_immutable_1= 'immutable' )? (lv_key_2= ruleQualifiedName ) '=' (lv_value_4= rulePropertyExpression ) ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1755:2: (lv_local_0= 'local' )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==48) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1757:6: lv_local_0= 'local'
                    {
                    lv_local_0=(Token)input.LT(1);
                    match(input,48,FOLLOW_48_in_rulePropertyStatement4217); 

                            createLeafNode(grammarAccess.getPropertyStatementAccess().getLocalLocalKeyword_0_0(), "local"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPropertyStatementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "local", true, "local", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1776:3: (lv_immutable_1= 'immutable' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==49) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1778:6: lv_immutable_1= 'immutable'
                    {
                    lv_immutable_1=(Token)input.LT(1);
                    match(input,49,FOLLOW_49_in_rulePropertyStatement4252); 

                            createLeafNode(grammarAccess.getPropertyStatementAccess().getImmutableImmutableKeyword_1_0(), "immutable"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPropertyStatementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "immutable", true, "immutable", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1797:3: (lv_key_2= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1800:6: lv_key_2= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getPropertyStatementAccess().getKeyQualifiedNameParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_rulePropertyStatement4300);
            lv_key_2=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPropertyStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "key", lv_key_2, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,21,FOLLOW_21_in_rulePropertyStatement4313); 

                    createLeafNode(grammarAccess.getPropertyStatementAccess().getEqualsSignKeyword_3(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1822:1: (lv_value_4= rulePropertyExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1825:6: lv_value_4= rulePropertyExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getPropertyStatementAccess().getValuePropertyExpressionParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePropertyExpression_in_rulePropertyStatement4347);
            lv_value_4=rulePropertyExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPropertyStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_4, "PropertyExpression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,34,FOLLOW_34_in_rulePropertyStatement4360); 

                    createLeafNode(grammarAccess.getPropertyStatementAccess().getSemicolonKeyword_5(), null); 
                

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
    // $ANTLR end rulePropertyStatement


    // $ANTLR start entryRuleUnsetPropertyStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1854:1: entryRuleUnsetPropertyStatement returns [EObject current=null] : iv_ruleUnsetPropertyStatement= ruleUnsetPropertyStatement EOF ;
    public final EObject entryRuleUnsetPropertyStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnsetPropertyStatement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1854:64: (iv_ruleUnsetPropertyStatement= ruleUnsetPropertyStatement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1855:2: iv_ruleUnsetPropertyStatement= ruleUnsetPropertyStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnsetPropertyStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnsetPropertyStatement_in_entryRuleUnsetPropertyStatement4393);
            iv_ruleUnsetPropertyStatement=ruleUnsetPropertyStatement();
            _fsp--;

             current =iv_ruleUnsetPropertyStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsetPropertyStatement4403); 

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
    // $ANTLR end entryRuleUnsetPropertyStatement


    // $ANTLR start ruleUnsetPropertyStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1862:1: ruleUnsetPropertyStatement returns [EObject current=null] : ( 'unset' (lv_unsetProperties_1= ruleQualifiedName ) ';' ) ;
    public final EObject ruleUnsetPropertyStatement() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_unsetProperties_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1867:6: ( ( 'unset' (lv_unsetProperties_1= ruleQualifiedName ) ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1868:1: ( 'unset' (lv_unsetProperties_1= ruleQualifiedName ) ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1868:1: ( 'unset' (lv_unsetProperties_1= ruleQualifiedName ) ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1868:2: 'unset' (lv_unsetProperties_1= ruleQualifiedName ) ';'
            {
            match(input,50,FOLLOW_50_in_ruleUnsetPropertyStatement4437); 

                    createLeafNode(grammarAccess.getUnsetPropertyStatementAccess().getUnsetKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1872:1: (lv_unsetProperties_1= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1875:6: lv_unsetProperties_1= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUnsetPropertyStatementAccess().getUnsetPropertiesQualifiedNameParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleUnsetPropertyStatement4471);
            lv_unsetProperties_1=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getUnsetPropertyStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		add(current, "unsetProperties", lv_unsetProperties_1, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,34,FOLLOW_34_in_ruleUnsetPropertyStatement4484); 

                    createLeafNode(grammarAccess.getUnsetPropertyStatementAccess().getSemicolonKeyword_2(), null); 
                

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
    // $ANTLR end ruleUnsetPropertyStatement


    // $ANTLR start entryRuleAnnotationStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1904:1: entryRuleAnnotationStatement returns [EObject current=null] : iv_ruleAnnotationStatement= ruleAnnotationStatement EOF ;
    public final EObject entryRuleAnnotationStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotationStatement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1904:61: (iv_ruleAnnotationStatement= ruleAnnotationStatement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1905:2: iv_ruleAnnotationStatement= ruleAnnotationStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAnnotationStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleAnnotationStatement_in_entryRuleAnnotationStatement4517);
            iv_ruleAnnotationStatement=ruleAnnotationStatement();
            _fsp--;

             current =iv_ruleAnnotationStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnnotationStatement4527); 

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
    // $ANTLR end entryRuleAnnotationStatement


    // $ANTLR start ruleAnnotationStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1912:1: ruleAnnotationStatement returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_immutable_2= 'immutable' )? (lv_key_3= ruleQualifiedName ) '=' (lv_value_5= rulePropertyExpression ) ';' ) ;
    public final EObject ruleAnnotationStatement() throws RecognitionException {
        EObject current = null;

        Token lv_immutable_2=null;
        EObject lv_filter_1 = null;

        AntlrDatatypeRuleToken lv_key_3 = null;

        EObject lv_value_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1917:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_immutable_2= 'immutable' )? (lv_key_3= ruleQualifiedName ) '=' (lv_value_5= rulePropertyExpression ) ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1918:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_immutable_2= 'immutable' )? (lv_key_3= ruleQualifiedName ) '=' (lv_value_5= rulePropertyExpression ) ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1918:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? (lv_immutable_2= 'immutable' )? (lv_key_3= ruleQualifiedName ) '=' (lv_value_5= rulePropertyExpression ) ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1918:2: ( 'when' (lv_filter_1= ruleFilter ) )? (lv_immutable_2= 'immutable' )? (lv_key_3= ruleQualifiedName ) '=' (lv_value_5= rulePropertyExpression ) ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1918:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==47) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1918:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,47,FOLLOW_47_in_ruleAnnotationStatement4562); 

                            createLeafNode(grammarAccess.getAnnotationStatementAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1922:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1925:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAnnotationStatementAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_ruleAnnotationStatement4596);
                    lv_filter_1=ruleFilter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAnnotationStatementRule().getType().getClassifier());
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1943:4: (lv_immutable_2= 'immutable' )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==49) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1945:6: lv_immutable_2= 'immutable'
                    {
                    lv_immutable_2=(Token)input.LT(1);
                    match(input,49,FOLLOW_49_in_ruleAnnotationStatement4623); 

                            createLeafNode(grammarAccess.getAnnotationStatementAccess().getImmutableImmutableKeyword_1_0(), "immutable"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAnnotationStatementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "immutable", true, "immutable", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1964:3: (lv_key_3= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1967:6: lv_key_3= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAnnotationStatementAccess().getKeyQualifiedNameParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleAnnotationStatement4671);
            lv_key_3=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAnnotationStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "key", lv_key_3, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,21,FOLLOW_21_in_ruleAnnotationStatement4684); 

                    createLeafNode(grammarAccess.getAnnotationStatementAccess().getEqualsSignKeyword_3(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1989:1: (lv_value_5= rulePropertyExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:1992:6: lv_value_5= rulePropertyExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAnnotationStatementAccess().getValuePropertyExpressionParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleAnnotationStatement4718);
            lv_value_5=rulePropertyExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAnnotationStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_5, "PropertyExpression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,34,FOLLOW_34_in_ruleAnnotationStatement4731); 

                    createLeafNode(grammarAccess.getAnnotationStatementAccess().getSemicolonKeyword_5(), null); 
                

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
    // $ANTLR end ruleAnnotationStatement


    // $ANTLR start entryRulePropertyExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2021:1: entryRulePropertyExpression returns [EObject current=null] : iv_rulePropertyExpression= rulePropertyExpression EOF ;
    public final EObject entryRulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2021:60: (iv_rulePropertyExpression= rulePropertyExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2022:2: iv_rulePropertyExpression= rulePropertyExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPropertyExpressionRule(), currentNode); 
            pushFollow(FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression4764);
            iv_rulePropertyExpression=rulePropertyExpression();
            _fsp--;

             current =iv_rulePropertyExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyExpression4774); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2029:1: rulePropertyExpression returns [EObject current=null] : this_Expression_0= ruleExpression ;
    public final EObject rulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Expression_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2034:6: (this_Expression_0= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2036:5: this_Expression_0= ruleExpression
            {
             
                    currentNode=createCompositeNode(grammarAccess.getPropertyExpressionAccess().getExpressionParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleExpression_in_rulePropertyExpression4820);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2051:1: entryRuleSynchronization returns [EObject current=null] : iv_ruleSynchronization= ruleSynchronization EOF ;
    public final EObject entryRuleSynchronization() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSynchronization = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2051:57: (iv_ruleSynchronization= ruleSynchronization EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2052:2: iv_ruleSynchronization= ruleSynchronization EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSynchronizationRule(), currentNode); 
            pushFollow(FOLLOW_ruleSynchronization_in_entryRuleSynchronization4851);
            iv_ruleSynchronization=ruleSynchronization();
            _fsp--;

             current =iv_ruleSynchronization; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSynchronization4861); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2059:1: ruleSynchronization returns [EObject current=null] : ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' ) ;
    public final EObject ruleSynchronization() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_partrefs_0 = null;

        AntlrDatatypeRuleToken lv_partrefs_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2064:6: ( ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2065:1: ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2065:1: ( (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2065:2: (lv_partrefs_0= ruleSynchronizedPart ) ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+ ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2065:2: (lv_partrefs_0= ruleSynchronizedPart )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2068:6: lv_partrefs_0= ruleSynchronizedPart
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getSynchronizationAccess().getPartrefsSynchronizedPartParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4920);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2086:2: ( ',' (lv_partrefs_2= ruleSynchronizedPart ) )+
            int cnt38=0;
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==29) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2086:3: ',' (lv_partrefs_2= ruleSynchronizedPart )
            	    {
            	    match(input,29,FOLLOW_29_in_ruleSynchronization4934); 

            	            createLeafNode(grammarAccess.getSynchronizationAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2090:1: (lv_partrefs_2= ruleSynchronizedPart )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2093:6: lv_partrefs_2= ruleSynchronizedPart
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getSynchronizationAccess().getPartrefsSynchronizedPartParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4968);
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
            	    if ( cnt38 >= 1 ) break loop38;
                        EarlyExitException eee =
                            new EarlyExitException(38, input);
                        throw eee;
                }
                cnt38++;
            } while (true);

            match(input,34,FOLLOW_34_in_ruleSynchronization4983); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2122:1: entryRuleSynchronizedPart returns [String current=null] : iv_ruleSynchronizedPart= ruleSynchronizedPart EOF ;
    public final String entryRuleSynchronizedPart() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSynchronizedPart = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2122:57: (iv_ruleSynchronizedPart= ruleSynchronizedPart EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2123:2: iv_ruleSynchronizedPart= ruleSynchronizedPart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSynchronizedPartRule(), currentNode); 
            pushFollow(FOLLOW_ruleSynchronizedPart_in_entryRuleSynchronizedPart5017);
            iv_ruleSynchronizedPart=ruleSynchronizedPart();
            _fsp--;

             current =iv_ruleSynchronizedPart.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSynchronizedPart5028); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2130:1: ruleSynchronizedPart returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName ) ;
    public final AntlrDatatypeRuleToken ruleSynchronizedPart() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_CompoundName_0 = null;

        AntlrDatatypeRuleToken this_PartName_2 = null;

        AntlrDatatypeRuleToken this_PartName_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2136:6: ( ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2137:1: ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2137:1: ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName )
            int alt40=2;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2137:2: (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2137:2: (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2138:5: this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )?
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getSynchronizedPartAccess().getCompoundNameParserRuleCall_0_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCompoundName_in_ruleSynchronizedPart5076);
                    this_CompoundName_0=ruleCompoundName();
                    _fsp--;


                    		current.merge(this_CompoundName_0);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2148:1: (kw= '#' this_PartName_2= rulePartName )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==51) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2149:2: kw= '#' this_PartName_2= rulePartName
                            {
                            kw=(Token)input.LT(1);
                            match(input,51,FOLLOW_51_in_ruleSynchronizedPart5095); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getSynchronizedPartAccess().getNumberSignKeyword_0_1_0(), null); 
                                
                             
                                    currentNode=createCompositeNode(grammarAccess.getSynchronizedPartAccess().getPartNameParserRuleCall_0_1_1(), currentNode); 
                                
                            pushFollow(FOLLOW_rulePartName_in_ruleSynchronizedPart5117);
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
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2167:5: this_PartName_3= rulePartName
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getSynchronizedPartAccess().getPartNameParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulePartName_in_ruleSynchronizedPart5153);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2185:1: entryRuleBuildPart returns [EObject current=null] : iv_ruleBuildPart= ruleBuildPart EOF ;
    public final EObject entryRuleBuildPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuildPart = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2185:51: (iv_ruleBuildPart= ruleBuildPart EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2186:2: iv_ruleBuildPart= ruleBuildPart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBuildPartRule(), currentNode); 
            pushFollow(FOLLOW_ruleBuildPart_in_entryRuleBuildPart5196);
            iv_ruleBuildPart=ruleBuildPart();
            _fsp--;

             current =iv_ruleBuildPart; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuildPart5206); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2193:1: ruleBuildPart returns [EObject current=null] : (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction ) ;
    public final EObject ruleBuildPart() throws RecognitionException {
        EObject current = null;

        EObject this_Artifacts_0 = null;

        EObject this_Group_1 = null;

        EObject this_Action_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2198:6: ( (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )
            int alt41=3;
            switch ( input.LA(1) ) {
            case 77:
                {
                switch ( input.LA(2) ) {
                case 52:
                    {
                    alt41=1;
                    }
                    break;
                case 79:
                    {
                    int LA41_4 = input.LA(3);

                    if ( (LA41_4==58) ) {
                        alt41=3;
                    }
                    else if ( (LA41_4==55) ) {
                        alt41=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 45:
                    {
                    int LA41_5 = input.LA(3);

                    if ( (LA41_5==55) ) {
                        alt41=2;
                    }
                    else if ( (LA41_5==58) ) {
                        alt41=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 5, input);

                        throw nvae;
                    }
                    }
                    break;
                case 58:
                    {
                    alt41=3;
                    }
                    break;
                case 55:
                    {
                    alt41=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 1, input);

                    throw nvae;
                }

                }
                break;
            case 78:
                {
                switch ( input.LA(2) ) {
                case 79:
                    {
                    int LA41_4 = input.LA(3);

                    if ( (LA41_4==58) ) {
                        alt41=3;
                    }
                    else if ( (LA41_4==55) ) {
                        alt41=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 45:
                    {
                    int LA41_5 = input.LA(3);

                    if ( (LA41_5==55) ) {
                        alt41=2;
                    }
                    else if ( (LA41_5==58) ) {
                        alt41=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 5, input);

                        throw nvae;
                    }
                    }
                    break;
                case 58:
                    {
                    alt41=3;
                    }
                    break;
                case 52:
                    {
                    alt41=1;
                    }
                    break;
                case 55:
                    {
                    alt41=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 2, input);

                    throw nvae;
                }

                }
                break;
            case 52:
                {
                alt41=1;
                }
                break;
            case 79:
                {
                int LA41_4 = input.LA(2);

                if ( (LA41_4==58) ) {
                    alt41=3;
                }
                else if ( (LA41_4==55) ) {
                    alt41=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 4, input);

                    throw nvae;
                }
                }
                break;
            case 45:
                {
                int LA41_5 = input.LA(2);

                if ( (LA41_5==55) ) {
                    alt41=2;
                }
                else if ( (LA41_5==58) ) {
                    alt41=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 5, input);

                    throw nvae;
                }
                }
                break;
            case 55:
                {
                alt41=2;
                }
                break;
            case 58:
                {
                alt41=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("2199:1: (this_Artifacts_0= ruleArtifacts | this_Group_1= ruleGroup | this_Action_2= ruleAction )", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2200:5: this_Artifacts_0= ruleArtifacts
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getBuildPartAccess().getArtifactsParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleArtifacts_in_ruleBuildPart5253);
                    this_Artifacts_0=ruleArtifacts();
                    _fsp--;

                     
                            current = this_Artifacts_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2210:5: this_Group_1= ruleGroup
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getBuildPartAccess().getGroupParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleGroup_in_ruleBuildPart5280);
                    this_Group_1=ruleGroup();
                    _fsp--;

                     
                            current = this_Group_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2220:5: this_Action_2= ruleAction
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getBuildPartAccess().getActionParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAction_in_ruleBuildPart5307);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2235:1: entryRuleArtifacts returns [EObject current=null] : iv_ruleArtifacts= ruleArtifacts EOF ;
    public final EObject entryRuleArtifacts() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArtifacts = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2235:51: (iv_ruleArtifacts= ruleArtifacts EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2236:2: iv_ruleArtifacts= ruleArtifacts EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArtifactsRule(), currentNode); 
            pushFollow(FOLLOW_ruleArtifacts_in_entryRuleArtifacts5339);
            iv_ruleArtifacts=ruleArtifacts();
            _fsp--;

             current =iv_ruleArtifacts; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArtifacts5349); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2243:1: ruleArtifacts returns [EObject current=null] : ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' ) ;
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
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2248:6: ( ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2249:1: ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2249:1: ( (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2249:2: (lv_visibility_0= ruleVisibility )? 'artifacts' (lv_name_2= rulePartName ) ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )? (lv_asserts_7= rulePostConditionAssert )? '{' (lv_paths_9= rulePathGroup )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2249:2: (lv_visibility_0= ruleVisibility )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( ((LA42_0>=77 && LA42_0<=78)) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2252:6: lv_visibility_0= ruleVisibility
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getVisibilityVisibilityEnumRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVisibility_in_ruleArtifacts5408);
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

            match(input,52,FOLLOW_52_in_ruleArtifacts5422); 

                    createLeafNode(grammarAccess.getArtifactsAccess().getArtifactsKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2274:1: (lv_name_2= rulePartName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2277:6: lv_name_2= rulePartName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getNamePartNameParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePartName_in_ruleArtifacts5456);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2295:2: ( 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )* )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==40) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2295:3: 'provides' (lv_providedCapabilities_4= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )*
                    {
                    match(input,40,FOLLOW_40_in_ruleArtifacts5470); 

                            createLeafNode(grammarAccess.getArtifactsAccess().getProvidesKeyword_3_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2299:1: (lv_providedCapabilities_4= ruleProvidedCapability )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2302:6: lv_providedCapabilities_4= ruleProvidedCapability
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleArtifacts5504);
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

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2320:2: ( ',' (lv_providedCapabilities_6= ruleProvidedCapability ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==29) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2320:3: ',' (lv_providedCapabilities_6= ruleProvidedCapability )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleArtifacts5518); 

                    	            createLeafNode(grammarAccess.getArtifactsAccess().getCommaKeyword_3_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2324:1: (lv_providedCapabilities_6= ruleProvidedCapability )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2327:6: lv_providedCapabilities_6= ruleProvidedCapability
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_3_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleArtifacts5552);
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
                    	    break loop43;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2345:6: (lv_asserts_7= rulePostConditionAssert )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==65) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2348:6: lv_asserts_7= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getAssertsPostConditionAssertParserRuleCall_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleArtifacts5594);
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

            match(input,38,FOLLOW_38_in_ruleArtifacts5608); 

                    createLeafNode(grammarAccess.getArtifactsAccess().getLeftCurlyBracketKeyword_5(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2370:1: (lv_paths_9= rulePathGroup )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=RULE_ID && LA46_0<=RULE_STRING)||LA46_0==13||LA46_0==47||(LA46_0>=53 && LA46_0<=54)) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2373:6: lv_paths_9= rulePathGroup
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getArtifactsAccess().getPathsPathGroupParserRuleCall_6_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePathGroup_in_ruleArtifacts5642);
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
            	    break loop46;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleArtifacts5656); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2402:1: entryRulePathGroup returns [EObject current=null] : iv_rulePathGroup= rulePathGroup EOF ;
    public final EObject entryRulePathGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathGroup = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2402:51: (iv_rulePathGroup= rulePathGroup EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2403:2: iv_rulePathGroup= rulePathGroup EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPathGroupRule(), currentNode); 
            pushFollow(FOLLOW_rulePathGroup_in_entryRulePathGroup5689);
            iv_rulePathGroup=rulePathGroup();
            _fsp--;

             current =iv_rulePathGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePathGroup5699); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2410:1: rulePathGroup returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' ) | ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' ) ) ) ;
    public final EObject rulePathGroup() throws RecognitionException {
        EObject current = null;

        Token lv_firstIsBase_5=null;
        EObject lv_filter_1 = null;

        EObject lv_paths_2 = null;

        EObject lv_paths_4 = null;

        EObject lv_paths_6 = null;

        EObject lv_paths_8 = null;

        AntlrDatatypeRuleToken lv_unsetProperties_14 = null;

        EObject lv_setProperties_16 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2415:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' ) | ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' ) ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2416:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' ) | ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' ) ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2416:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' ) | ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2416:2: ( 'when' (lv_filter_1= ruleFilter ) )? ( ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' ) | ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2416:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==47) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2416:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,47,FOLLOW_47_in_rulePathGroup5734); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2420:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2423:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_rulePathGroup5768);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2441:4: ( ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' ) | ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' ) )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=RULE_ID && LA52_0<=RULE_STRING)||LA52_0==13||LA52_0==54) ) {
                alt52=1;
            }
            else if ( (LA52_0==53) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2441:4: ( ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' ) | ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' ) )", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2441:5: ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2441:5: ( (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2441:6: (lv_paths_2= rulePathExpression ) ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) ) ';'
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2441:6: (lv_paths_2= rulePathExpression )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2444:6: lv_paths_2= rulePathExpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathExpressionParserRuleCall_1_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePathExpression_in_rulePathGroup5810);
                    lv_paths_2=rulePathExpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "paths", lv_paths_2, "PathExpression", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2462:2: ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==29||LA50_0==34) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==27) ) {
                        alt50=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2462:2: ( ( ',' (lv_paths_4= rulePathExpression ) )* | ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' ) )", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2462:3: ( ',' (lv_paths_4= rulePathExpression ) )*
                            {
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2462:3: ( ',' (lv_paths_4= rulePathExpression ) )*
                            loop48:
                            do {
                                int alt48=2;
                                int LA48_0 = input.LA(1);

                                if ( (LA48_0==29) ) {
                                    alt48=1;
                                }


                                switch (alt48) {
                            	case 1 :
                            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2462:4: ',' (lv_paths_4= rulePathExpression )
                            	    {
                            	    match(input,29,FOLLOW_29_in_rulePathGroup5825); 

                            	            createLeafNode(grammarAccess.getPathGroupAccess().getCommaKeyword_1_0_1_0_0(), null); 
                            	        
                            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2466:1: (lv_paths_4= rulePathExpression )
                            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2469:6: lv_paths_4= rulePathExpression
                            	    {
                            	     
                            	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathExpressionParserRuleCall_1_0_1_0_1_0(), currentNode); 
                            	    	    
                            	    pushFollow(FOLLOW_rulePathExpression_in_rulePathGroup5859);
                            	    lv_paths_4=rulePathExpression();
                            	    _fsp--;


                            	    	        if (current==null) {
                            	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	    	        }
                            	    	        
                            	    	        try {
                            	    	       		add(current, "paths", lv_paths_4, "PathExpression", currentNode);
                            	    	        } catch (ValueConverterException vce) {
                            	    				handleValueConverterException(vce);
                            	    	        }
                            	    	        currentNode = currentNode.getParent();
                            	    	    

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop48;
                                }
                            } while (true);


                            }
                            break;
                        case 2 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2488:6: ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' )
                            {
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2488:6: ( (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']' )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2488:7: (lv_firstIsBase_5= '[' ) (lv_paths_6= rulePathExpression ) ( ',' (lv_paths_8= rulePathExpression ) )* ']'
                            {
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2488:7: (lv_firstIsBase_5= '[' )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2490:6: lv_firstIsBase_5= '['
                            {
                            lv_firstIsBase_5=(Token)input.LT(1);
                            match(input,27,FOLLOW_27_in_rulePathGroup5893); 

                                    createLeafNode(grammarAccess.getPathGroupAccess().getFirstIsBaseLeftSquareBracketKeyword_1_0_1_1_0_0(), "firstIsBase"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "firstIsBase", true, "[", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }

                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2509:2: (lv_paths_6= rulePathExpression )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2512:6: lv_paths_6= rulePathExpression
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathExpressionParserRuleCall_1_0_1_1_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulePathExpression_in_rulePathGroup5940);
                            lv_paths_6=rulePathExpression();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		add(current, "paths", lv_paths_6, "PathExpression", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }

                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2530:2: ( ',' (lv_paths_8= rulePathExpression ) )*
                            loop49:
                            do {
                                int alt49=2;
                                int LA49_0 = input.LA(1);

                                if ( (LA49_0==29) ) {
                                    alt49=1;
                                }


                                switch (alt49) {
                            	case 1 :
                            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2530:3: ',' (lv_paths_8= rulePathExpression )
                            	    {
                            	    match(input,29,FOLLOW_29_in_rulePathGroup5954); 

                            	            createLeafNode(grammarAccess.getPathGroupAccess().getCommaKeyword_1_0_1_1_2_0(), null); 
                            	        
                            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2534:1: (lv_paths_8= rulePathExpression )
                            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2537:6: lv_paths_8= rulePathExpression
                            	    {
                            	     
                            	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getPathsPathExpressionParserRuleCall_1_0_1_1_2_1_0(), currentNode); 
                            	    	    
                            	    pushFollow(FOLLOW_rulePathExpression_in_rulePathGroup5988);
                            	    lv_paths_8=rulePathExpression();
                            	    _fsp--;


                            	    	        if (current==null) {
                            	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	    	        }
                            	    	        
                            	    	        try {
                            	    	       		add(current, "paths", lv_paths_8, "PathExpression", currentNode);
                            	    	        } catch (ValueConverterException vce) {
                            	    				handleValueConverterException(vce);
                            	    	        }
                            	    	        currentNode = currentNode.getParent();
                            	    	    

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop49;
                                }
                            } while (true);

                            match(input,30,FOLLOW_30_in_rulePathGroup6003); 

                                    createLeafNode(grammarAccess.getPathGroupAccess().getRightSquareBracketKeyword_1_0_1_1_3(), null); 
                                

                            }


                            }
                            break;

                    }

                    match(input,34,FOLLOW_34_in_rulePathGroup6014); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getSemicolonKeyword_1_0_2(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2564:6: ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2564:6: ( 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2564:7: 'annotations' '{' ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+ '}'
                    {
                    match(input,53,FOLLOW_53_in_rulePathGroup6031); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getAnnotationsKeyword_1_1_0(), null); 
                        
                    match(input,38,FOLLOW_38_in_rulePathGroup6040); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getLeftCurlyBracketKeyword_1_1_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2572:1: ( ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' ) | (lv_setProperties_16= ruleAnnotationStatement ) )+
                    int cnt51=0;
                    loop51:
                    do {
                        int alt51=3;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==50) ) {
                            alt51=1;
                        }
                        else if ( (LA51_0==RULE_ID||LA51_0==47||LA51_0==49) ) {
                            alt51=2;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2572:2: ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' )
                    	    {
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2572:2: ( 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';' )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2572:3: 'unset' (lv_unsetProperties_14= ruleQualifiedName ) ';'
                    	    {
                    	    match(input,50,FOLLOW_50_in_rulePathGroup6051); 

                    	            createLeafNode(grammarAccess.getPathGroupAccess().getUnsetKeyword_1_1_2_0_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2576:1: (lv_unsetProperties_14= ruleQualifiedName )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2579:6: lv_unsetProperties_14= ruleQualifiedName
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getUnsetPropertiesQualifiedNameParserRuleCall_1_1_2_0_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleQualifiedName_in_rulePathGroup6085);
                    	    lv_unsetProperties_14=ruleQualifiedName();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "unsetProperties", lv_unsetProperties_14, "QualifiedName", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }

                    	    match(input,34,FOLLOW_34_in_rulePathGroup6098); 

                    	            createLeafNode(grammarAccess.getPathGroupAccess().getSemicolonKeyword_1_1_2_0_2(), null); 
                    	        

                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2602:6: (lv_setProperties_16= ruleAnnotationStatement )
                    	    {
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2602:6: (lv_setProperties_16= ruleAnnotationStatement )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2605:6: lv_setProperties_16= ruleAnnotationStatement
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPathGroupAccess().getSetPropertiesAnnotationStatementParserRuleCall_1_1_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAnnotationStatement_in_rulePathGroup6139);
                    	    lv_setProperties_16=ruleAnnotationStatement();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPathGroupRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "setProperties", lv_setProperties_16, "AnnotationStatement", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt51 >= 1 ) break loop51;
                                EarlyExitException eee =
                                    new EarlyExitException(51, input);
                                throw eee;
                        }
                        cnt51++;
                    } while (true);

                    match(input,41,FOLLOW_41_in_rulePathGroup6154); 

                            createLeafNode(grammarAccess.getPathGroupAccess().getRightCurlyBracketKeyword_1_1_3(), null); 
                        

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


    // $ANTLR start entryRulePathExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2634:1: entryRulePathExpression returns [EObject current=null] : iv_rulePathExpression= rulePathExpression EOF ;
    public final EObject entryRulePathExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2634:56: (iv_rulePathExpression= rulePathExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2635:2: iv_rulePathExpression= rulePathExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPathExpressionRule(), currentNode); 
            pushFollow(FOLLOW_rulePathExpression_in_entryRulePathExpression6189);
            iv_rulePathExpression=rulePathExpression();
            _fsp--;

             current =iv_rulePathExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePathExpression6199); 

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
    // $ANTLR end entryRulePathExpression


    // $ANTLR start rulePathExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2642:1: rulePathExpression returns [EObject current=null] : ( rulePath | this_ExprStatement_1= ruleExprStatement ) ;
    public final EObject rulePathExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ExprStatement_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2647:6: ( ( rulePath | this_ExprStatement_1= ruleExprStatement ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2648:1: ( rulePath | this_ExprStatement_1= ruleExprStatement )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2648:1: ( rulePath | this_ExprStatement_1= ruleExprStatement )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( ((LA53_0>=RULE_ID && LA53_0<=RULE_STRING)||LA53_0==13) ) {
                alt53=1;
            }
            else if ( (LA53_0==54) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2648:1: ( rulePath | this_ExprStatement_1= ruleExprStatement )", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2648:2: rulePath
                    {
                    pushFollow(FOLLOW_rulePath_in_rulePathExpression6233);
                    rulePath();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2650:5: this_ExprStatement_1= ruleExprStatement
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPathExpressionAccess().getExprStatementParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleExprStatement_in_rulePathExpression6253);
                    this_ExprStatement_1=ruleExprStatement();
                    _fsp--;

                     
                            current = this_ExprStatement_1; 
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
    // $ANTLR end rulePathExpression


    // $ANTLR start entryRuleExprStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2665:1: entryRuleExprStatement returns [EObject current=null] : iv_ruleExprStatement= ruleExprStatement EOF ;
    public final EObject entryRuleExprStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExprStatement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2665:55: (iv_ruleExprStatement= ruleExprStatement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2666:2: iv_ruleExprStatement= ruleExprStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExprStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleExprStatement_in_entryRuleExprStatement6285);
            iv_ruleExprStatement=ruleExprStatement();
            _fsp--;

             current =iv_ruleExprStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExprStatement6295); 

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
    // $ANTLR end entryRuleExprStatement


    // $ANTLR start ruleExprStatement
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2673:1: ruleExprStatement returns [EObject current=null] : ( 'expr' (lv_expression_1= ruleExpression ) ) ;
    public final EObject ruleExprStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_expression_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2678:6: ( ( 'expr' (lv_expression_1= ruleExpression ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2679:1: ( 'expr' (lv_expression_1= ruleExpression ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2679:1: ( 'expr' (lv_expression_1= ruleExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2679:2: 'expr' (lv_expression_1= ruleExpression )
            {
            match(input,54,FOLLOW_54_in_ruleExprStatement6329); 

                    createLeafNode(grammarAccess.getExprStatementAccess().getExprKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2683:1: (lv_expression_1= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2686:6: lv_expression_1= ruleExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getExprStatementAccess().getExpressionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleExprStatement6363);
            lv_expression_1=ruleExpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getExprStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "expression", lv_expression_1, "Expression", currentNode);
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
    // $ANTLR end ruleExprStatement


    // $ANTLR start entryRulePath
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2711:1: entryRulePath returns [String current=null] : iv_rulePath= rulePath EOF ;
    public final String entryRulePath() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePath = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2711:45: (iv_rulePath= rulePath EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2712:2: iv_rulePath= rulePath EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPathRule(), currentNode); 
            pushFollow(FOLLOW_rulePath_in_entryRulePath6401);
            iv_rulePath=rulePath();
            _fsp--;

             current =iv_rulePath.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePath6412); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2719:1: rulePath returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) ) ;
    public final AntlrDatatypeRuleToken rulePath() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_2 = null;

        AntlrDatatypeRuleToken this_QualifiedName_4 = null;


         setCurrentLookahead(); resetLookahead(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2727:6: ( (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2728:1: (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2728:1: (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==RULE_STRING) ) {
                alt57=1;
            }
            else if ( (LA57_0==RULE_ID||LA57_0==13) ) {
                alt57=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2728:1: (this_STRING_0= RULE_STRING | ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? ) )", 57, 0, input);

                throw nvae;
            }
            switch (alt57) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2728:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePath6452); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getPathAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2736:6: ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2736:6: ( (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2736:7: (kw= '/' )? this_QualifiedName_2= ruleQualifiedName (kw= '/' this_QualifiedName_4= ruleQualifiedName )* (kw= '/' )?
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2736:7: (kw= '/' )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==13) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2737:2: kw= '/'
                            {
                            kw=(Token)input.LT(1);
                            match(input,13,FOLLOW_13_in_rulePath6478); 

                                    current.merge(kw);
                                    createLeafNode(grammarAccess.getPathAccess().getSolidusKeyword_1_0(), null); 
                                

                            }
                            break;

                    }

                     
                            currentNode=createCompositeNode(grammarAccess.getPathAccess().getQualifiedNameParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleQualifiedName_in_rulePath6502);
                    this_QualifiedName_2=ruleQualifiedName();
                    _fsp--;


                    		current.merge(this_QualifiedName_2);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2753:1: (kw= '/' this_QualifiedName_4= ruleQualifiedName )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==13) ) {
                            int LA55_1 = input.LA(2);

                            if ( (LA55_1==RULE_ID) ) {
                                alt55=1;
                            }


                        }


                        switch (alt55) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2754:2: kw= '/' this_QualifiedName_4= ruleQualifiedName
                    	    {
                    	    kw=(Token)input.LT(1);
                    	    match(input,13,FOLLOW_13_in_rulePath6521); 

                    	            current.merge(kw);
                    	            createLeafNode(grammarAccess.getPathAccess().getSolidusKeyword_1_2_0(), null); 
                    	        
                    	     
                    	            currentNode=createCompositeNode(grammarAccess.getPathAccess().getQualifiedNameParserRuleCall_1_2_1(), currentNode); 
                    	        
                    	    pushFollow(FOLLOW_ruleQualifiedName_in_rulePath6543);
                    	    this_QualifiedName_4=ruleQualifiedName();
                    	    _fsp--;


                    	    		current.merge(this_QualifiedName_4);
                    	        
                    	     
                    	            currentNode = currentNode.getParent();
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop55;
                        }
                    } while (true);

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2770:3: (kw= '/' )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==13) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2771:2: kw= '/'
                            {
                            kw=(Token)input.LT(1);
                            match(input,13,FOLLOW_13_in_rulePath6564); 

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
    // $ANTLR end rulePath


    // $ANTLR start entryRuleGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2784:1: entryRuleGroup returns [EObject current=null] : iv_ruleGroup= ruleGroup EOF ;
    public final EObject entryRuleGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGroup = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2784:47: (iv_ruleGroup= ruleGroup EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2785:2: iv_ruleGroup= ruleGroup EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGroupRule(), currentNode); 
            pushFollow(FOLLOW_ruleGroup_in_entryRuleGroup6605);
            iv_ruleGroup=ruleGroup();
            _fsp--;

             current =iv_ruleGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGroup6615); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2792:1: ruleGroup returns [EObject current=null] : ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' ) ;
    public final EObject ruleGroup() throws RecognitionException {
        EObject current = null;

        Enumerator lv_visibility_0 = null;

        Enumerator lv_executionMode_1 = null;

        AntlrDatatypeRuleToken lv_name_3 = null;

        EObject lv_providedCapabilities_5 = null;

        EObject lv_providedCapabilities_7 = null;

        EObject lv_asserts_8 = null;

        EObject lv_asserts_9 = null;

        EObject lv_prerequisites_11 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2797:6: ( ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2798:1: ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2798:1: ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2798:2: (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'group' (lv_name_3= rulePartName ) ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )? (lv_asserts_8= rulePreConditionAssert )? (lv_asserts_9= rulePostConditionAssert )? '{' (lv_prerequisites_11= rulePrerequisite )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2798:2: (lv_visibility_0= ruleVisibility )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( ((LA58_0>=77 && LA58_0<=78)) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2801:6: lv_visibility_0= ruleVisibility
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getVisibilityVisibilityEnumRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVisibility_in_ruleGroup6674);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2819:3: (lv_executionMode_1= ruleExecutionMode )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==45||LA59_0==79) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2822:6: lv_executionMode_1= ruleExecutionMode
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getExecutionModeExecutionModeEnumRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleExecutionMode_in_ruleGroup6713);
                    lv_executionMode_1=ruleExecutionMode();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getGroupRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "executionMode", lv_executionMode_1, "ExecutionMode", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,55,FOLLOW_55_in_ruleGroup6727); 

                    createLeafNode(grammarAccess.getGroupAccess().getGroupKeyword_2(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2844:1: (lv_name_3= rulePartName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2847:6: lv_name_3= rulePartName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getNamePartNameParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePartName_in_ruleGroup6761);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2865:2: ( 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )* )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==40) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2865:3: 'provides' (lv_providedCapabilities_5= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )*
                    {
                    match(input,40,FOLLOW_40_in_ruleGroup6775); 

                            createLeafNode(grammarAccess.getGroupAccess().getProvidesKeyword_4_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2869:1: (lv_providedCapabilities_5= ruleProvidedCapability )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2872:6: lv_providedCapabilities_5= ruleProvidedCapability
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleGroup6809);
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

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2890:2: ( ',' (lv_providedCapabilities_7= ruleProvidedCapability ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==29) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2890:3: ',' (lv_providedCapabilities_7= ruleProvidedCapability )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleGroup6823); 

                    	            createLeafNode(grammarAccess.getGroupAccess().getCommaKeyword_4_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2894:1: (lv_providedCapabilities_7= ruleProvidedCapability )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2897:6: lv_providedCapabilities_7= ruleProvidedCapability
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_4_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleGroup6857);
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
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2915:6: (lv_asserts_8= rulePreConditionAssert )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==64) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2918:6: lv_asserts_8= rulePreConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getAssertsPreConditionAssertParserRuleCall_5_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePreConditionAssert_in_ruleGroup6899);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2936:3: (lv_asserts_9= rulePostConditionAssert )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==65) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2939:6: lv_asserts_9= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getAssertsPostConditionAssertParserRuleCall_6_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleGroup6938);
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

            match(input,38,FOLLOW_38_in_ruleGroup6952); 

                    createLeafNode(grammarAccess.getGroupAccess().getLeftCurlyBracketKeyword_7(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2961:1: (lv_prerequisites_11= rulePrerequisite )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( ((LA64_0>=RULE_ID && LA64_0<=RULE_STRING)||LA64_0==38||LA64_0==47||LA64_0==54||LA64_0==56) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2964:6: lv_prerequisites_11= rulePrerequisite
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getGroupAccess().getPrerequisitesPrerequisiteParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePrerequisite_in_ruleGroup6986);
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
            	    break loop64;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleGroup7000); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2993:1: entryRulePrerequisite returns [EObject current=null] : iv_rulePrerequisite= rulePrerequisite EOF ;
    public final EObject entryRulePrerequisite() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrerequisite = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2993:54: (iv_rulePrerequisite= rulePrerequisite EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:2994:2: iv_rulePrerequisite= rulePrerequisite EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrerequisiteRule(), currentNode); 
            pushFollow(FOLLOW_rulePrerequisite_in_entryRulePrerequisite7033);
            iv_rulePrerequisite=rulePrerequisite();
            _fsp--;

             current =iv_rulePrerequisite; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrerequisite7043); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3001:1: rulePrerequisite returns [EObject current=null] : ( ( (lv_surpressed_0= 'hidden' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? ) ) ';' ) ;
    public final EObject rulePrerequisite() throws RecognitionException {
        EObject current = null;

        Token lv_surpressed_0=null;
        EObject lv_filter_2 = null;

        AntlrDatatypeRuleToken lv_alias_3 = null;

        EObject lv_partReference_5 = null;

        EObject lv_closure_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3006:6: ( ( ( (lv_surpressed_0= 'hidden' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? ) ) ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3007:1: ( ( (lv_surpressed_0= 'hidden' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? ) ) ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3007:1: ( ( (lv_surpressed_0= 'hidden' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? ) ) ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3007:2: ( (lv_surpressed_0= 'hidden' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? ) ) ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3007:2: ( (lv_surpressed_0= 'hidden' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3007:3: (lv_surpressed_0= 'hidden' )? ( 'when' (lv_filter_2= ruleFilter ) )? ( (lv_alias_3= ruleQualifiedName ) '=' )? ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3007:3: (lv_surpressed_0= 'hidden' )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==56) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3009:6: lv_surpressed_0= 'hidden'
                    {
                    lv_surpressed_0=(Token)input.LT(1);
                    match(input,56,FOLLOW_56_in_rulePrerequisite7090); 

                            createLeafNode(grammarAccess.getPrerequisiteAccess().getSurpressedHiddenKeyword_0_0_0(), "surpressed"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrerequisiteRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "surpressed", true, "hidden", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3028:3: ( 'when' (lv_filter_2= ruleFilter ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==47) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3028:4: 'when' (lv_filter_2= ruleFilter )
                    {
                    match(input,47,FOLLOW_47_in_rulePrerequisite7114); 

                            createLeafNode(grammarAccess.getPrerequisiteAccess().getWhenKeyword_0_1_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3032:1: (lv_filter_2= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3035:6: lv_filter_2= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getFilterFilterParserRuleCall_0_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_rulePrerequisite7148);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3053:4: ( (lv_alias_3= ruleQualifiedName ) '=' )?
            int alt67=2;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3053:5: (lv_alias_3= ruleQualifiedName ) '='
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3053:5: (lv_alias_3= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3056:6: lv_alias_3= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getAliasQualifiedNameParserRuleCall_0_2_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_rulePrerequisite7189);
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

                    match(input,21,FOLLOW_21_in_rulePrerequisite7202); 

                            createLeafNode(grammarAccess.getPrerequisiteAccess().getEqualsSignKeyword_0_2_1(), null); 
                        

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3078:3: ( (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3078:4: (lv_partReference_5= rulePrerequisiteEntry ) (lv_closure_6= ruleClosure )?
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3078:4: (lv_partReference_5= rulePrerequisiteEntry )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3081:6: lv_partReference_5= rulePrerequisiteEntry
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getPartReferencePrerequisiteEntryParserRuleCall_0_3_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePrerequisiteEntry_in_rulePrerequisite7239);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3099:2: (lv_closure_6= ruleClosure )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==57) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3102:6: lv_closure_6= ruleClosure
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrerequisiteAccess().getClosureClosureParserRuleCall_0_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleClosure_in_rulePrerequisite7277);
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


            }


            }

            match(input,34,FOLLOW_34_in_rulePrerequisite7293); 

                    createLeafNode(grammarAccess.getPrerequisiteAccess().getSemicolonKeyword_1(), null); 
                

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3131:1: entryRuleClosure returns [EObject current=null] : iv_ruleClosure= ruleClosure EOF ;
    public final EObject entryRuleClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClosure = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3131:49: (iv_ruleClosure= ruleClosure EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3132:2: iv_ruleClosure= ruleClosure EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClosureRule(), currentNode); 
            pushFollow(FOLLOW_ruleClosure_in_entryRuleClosure7326);
            iv_ruleClosure=ruleClosure();
            _fsp--;

             current =iv_ruleClosure; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClosure7336); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3139:1: ruleClosure returns [EObject current=null] : ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' ) | ( 'advice' (lv_advice_10= ruleCompoundAdvice ) ) )* '}' ) ;
    public final EObject ruleClosure() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_unsetProperties_5 = null;

        EObject lv_setProperties_7 = null;

        EObject lv_advice_10 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3144:6: ( ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' ) | ( 'advice' (lv_advice_10= ruleCompoundAdvice ) ) )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3145:1: ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' ) | ( 'advice' (lv_advice_10= ruleCompoundAdvice ) ) )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3145:1: ( 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' ) | ( 'advice' (lv_advice_10= ruleCompoundAdvice ) ) )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3145:2: 'with' '{' ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' ) | ( 'advice' (lv_advice_10= ruleCompoundAdvice ) ) )* '}'
            {
            match(input,57,FOLLOW_57_in_ruleClosure7370); 

                    createLeafNode(grammarAccess.getClosureAccess().getWithKeyword_0(), null); 
                
            match(input,38,FOLLOW_38_in_ruleClosure7379); 

                    createLeafNode(grammarAccess.getClosureAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3153:1: ( ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' ) | ( 'advice' (lv_advice_10= ruleCompoundAdvice ) ) )*
            loop70:
            do {
                int alt70=3;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==39) ) {
                    alt70=1;
                }
                else if ( (LA70_0==44) ) {
                    alt70=2;
                }


                switch (alt70) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3153:2: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3153:2: ( 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3153:3: 'properties' '{' ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+ '}'
            	    {
            	    match(input,39,FOLLOW_39_in_ruleClosure7390); 

            	            createLeafNode(grammarAccess.getClosureAccess().getPropertiesKeyword_2_0_0(), null); 
            	        
            	    match(input,38,FOLLOW_38_in_ruleClosure7399); 

            	            createLeafNode(grammarAccess.getClosureAccess().getLeftCurlyBracketKeyword_2_0_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3161:1: ( ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' ) | (lv_setProperties_7= rulePropertyStatement ) )+
            	    int cnt69=0;
            	    loop69:
            	    do {
            	        int alt69=3;
            	        int LA69_0 = input.LA(1);

            	        if ( (LA69_0==50) ) {
            	            alt69=1;
            	        }
            	        else if ( (LA69_0==RULE_ID||(LA69_0>=48 && LA69_0<=49)) ) {
            	            alt69=2;
            	        }


            	        switch (alt69) {
            	    	case 1 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3161:2: ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' )
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3161:2: ( 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';' )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3161:3: 'unset' (lv_unsetProperties_5= ruleQualifiedName ) ';'
            	    	    {
            	    	    match(input,50,FOLLOW_50_in_ruleClosure7410); 

            	    	            createLeafNode(grammarAccess.getClosureAccess().getUnsetKeyword_2_0_2_0_0(), null); 
            	    	        
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3165:1: (lv_unsetProperties_5= ruleQualifiedName )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3168:6: lv_unsetProperties_5= ruleQualifiedName
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getUnsetPropertiesQualifiedNameParserRuleCall_2_0_2_0_1_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleQualifiedName_in_ruleClosure7444);
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

            	    	    match(input,34,FOLLOW_34_in_ruleClosure7457); 

            	    	            createLeafNode(grammarAccess.getClosureAccess().getSemicolonKeyword_2_0_2_0_2(), null); 
            	    	        

            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3191:6: (lv_setProperties_7= rulePropertyStatement )
            	    	    {
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3191:6: (lv_setProperties_7= rulePropertyStatement )
            	    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3194:6: lv_setProperties_7= rulePropertyStatement
            	    	    {
            	    	     
            	    	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getSetPropertiesPropertyStatementParserRuleCall_2_0_2_1_0(), currentNode); 
            	    	    	    
            	    	    pushFollow(FOLLOW_rulePropertyStatement_in_ruleClosure7498);
            	    	    lv_setProperties_7=rulePropertyStatement();
            	    	    _fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = factory.create(grammarAccess.getClosureRule().getType().getClassifier());
            	    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	    	        }
            	    	    	        
            	    	    	        try {
            	    	    	       		add(current, "setProperties", lv_setProperties_7, "PropertyStatement", currentNode);
            	    	    	        } catch (ValueConverterException vce) {
            	    	    				handleValueConverterException(vce);
            	    	    	        }
            	    	    	        currentNode = currentNode.getParent();
            	    	    	    

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt69 >= 1 ) break loop69;
            	                EarlyExitException eee =
            	                    new EarlyExitException(69, input);
            	                throw eee;
            	        }
            	        cnt69++;
            	    } while (true);

            	    match(input,41,FOLLOW_41_in_ruleClosure7513); 

            	            createLeafNode(grammarAccess.getClosureAccess().getRightCurlyBracketKeyword_2_0_3(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3217:6: ( 'advice' (lv_advice_10= ruleCompoundAdvice ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3217:6: ( 'advice' (lv_advice_10= ruleCompoundAdvice ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3217:7: 'advice' (lv_advice_10= ruleCompoundAdvice )
            	    {
            	    match(input,44,FOLLOW_44_in_ruleClosure7530); 

            	            createLeafNode(grammarAccess.getClosureAccess().getAdviceKeyword_2_1_0(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3221:1: (lv_advice_10= ruleCompoundAdvice )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3224:6: lv_advice_10= ruleCompoundAdvice
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClosureAccess().getAdviceCompoundAdviceParserRuleCall_2_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleClosure7564);
            	    lv_advice_10=ruleCompoundAdvice();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClosureRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "advice", lv_advice_10, "CompoundAdvice", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleClosure7580); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3253:1: entryRulePrerequisiteEntry returns [EObject current=null] : iv_rulePrerequisiteEntry= rulePrerequisiteEntry EOF ;
    public final EObject entryRulePrerequisiteEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrerequisiteEntry = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3253:59: (iv_rulePrerequisiteEntry= rulePrerequisiteEntry EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3254:2: iv_rulePrerequisiteEntry= rulePrerequisiteEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrerequisiteEntryRule(), currentNode); 
            pushFollow(FOLLOW_rulePrerequisiteEntry_in_entryRulePrerequisiteEntry7613);
            iv_rulePrerequisiteEntry=rulePrerequisiteEntry();
            _fsp--;

             current =iv_rulePrerequisiteEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrerequisiteEntry7623); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3261:1: rulePrerequisiteEntry returns [EObject current=null] : (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences | this_ExprStatement_3= ruleExprStatement ) ;
    public final EObject rulePrerequisiteEntry() throws RecognitionException {
        EObject current = null;

        EObject this_PartInSelf_0 = null;

        EObject this_CapabilityReferencedPart_1 = null;

        EObject this_CompoundReferences_2 = null;

        EObject this_ExprStatement_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3266:6: ( (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences | this_ExprStatement_3= ruleExprStatement ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3267:1: (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences | this_ExprStatement_3= ruleExprStatement )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3267:1: (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences | this_ExprStatement_3= ruleExprStatement )
            int alt71=4;
            alt71 = dfa71.predict(input);
            switch (alt71) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3268:5: this_PartInSelf_0= rulePartInSelf
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrerequisiteEntryAccess().getPartInSelfParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_rulePartInSelf_in_rulePrerequisiteEntry7670);
                    this_PartInSelf_0=rulePartInSelf();
                    _fsp--;

                     
                            current = this_PartInSelf_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3278:5: this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrerequisiteEntryAccess().getCapabilityReferencedPartParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCapabilityReferencedPart_in_rulePrerequisiteEntry7697);
                    this_CapabilityReferencedPart_1=ruleCapabilityReferencedPart();
                    _fsp--;

                     
                            current = this_CapabilityReferencedPart_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3288:5: this_CompoundReferences_2= ruleCompoundReferences
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrerequisiteEntryAccess().getCompoundReferencesParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCompoundReferences_in_rulePrerequisiteEntry7724);
                    this_CompoundReferences_2=ruleCompoundReferences();
                    _fsp--;

                     
                            current = this_CompoundReferences_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3298:5: this_ExprStatement_3= ruleExprStatement
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrerequisiteEntryAccess().getExprStatementParserRuleCall_3(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleExprStatement_in_rulePrerequisiteEntry7751);
                    this_ExprStatement_3=ruleExprStatement();
                    _fsp--;

                     
                            current = this_ExprStatement_3; 
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3313:1: entryRulePartInSelf returns [EObject current=null] : iv_rulePartInSelf= rulePartInSelf EOF ;
    public final EObject entryRulePartInSelf() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePartInSelf = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3313:52: (iv_rulePartInSelf= rulePartInSelf EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3314:2: iv_rulePartInSelf= rulePartInSelf EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPartInSelfRule(), currentNode); 
            pushFollow(FOLLOW_rulePartInSelf_in_entryRulePartInSelf7783);
            iv_rulePartInSelf=rulePartInSelf();
            _fsp--;

             current =iv_rulePartInSelf; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePartInSelf7793); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3321:1: rulePartInSelf returns [EObject current=null] : (lv_partName_0= rulePartName ) ;
    public final EObject rulePartInSelf() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_partName_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3326:6: ( (lv_partName_0= rulePartName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3327:1: (lv_partName_0= rulePartName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3327:1: (lv_partName_0= rulePartName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3330:6: lv_partName_0= rulePartName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getPartInSelfAccess().getPartNamePartNameParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePartName_in_rulePartInSelf7851);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3355:1: entryRuleCapabilityReferencedPart returns [EObject current=null] : iv_ruleCapabilityReferencedPart= ruleCapabilityReferencedPart EOF ;
    public final EObject entryRuleCapabilityReferencedPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCapabilityReferencedPart = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3355:66: (iv_ruleCapabilityReferencedPart= ruleCapabilityReferencedPart EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3356:2: iv_ruleCapabilityReferencedPart= ruleCapabilityReferencedPart EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCapabilityReferencedPartRule(), currentNode); 
            pushFollow(FOLLOW_ruleCapabilityReferencedPart_in_entryRuleCapabilityReferencedPart7887);
            iv_ruleCapabilityReferencedPart=ruleCapabilityReferencedPart();
            _fsp--;

             current =iv_ruleCapabilityReferencedPart; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCapabilityReferencedPart7897); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3363:1: ruleCapabilityReferencedPart returns [EObject current=null] : ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) ) ;
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
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3368:6: ( ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3369:1: ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3369:1: ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) )
            int alt74=2;
            alt74 = dfa74.predict(input);
            switch (alt74) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3369:2: ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3369:2: ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3369:3: (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3369:3: (lv_interface_0= ruleInterfaceName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3372:6: lv_interface_0= ruleInterfaceName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getInterfaceInterfaceNameParserRuleCall_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart7957);
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

                    match(input,13,FOLLOW_13_in_ruleCapabilityReferencedPart7970); 

                            createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_0_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3394:1: (lv_name_2= ruleUnitName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3397:6: lv_name_2= ruleUnitName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getNameUnitNameParserRuleCall_0_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart8004);
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

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3415:2: ( '/' (lv_range_4= ruleVersionRange ) )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==13) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3415:3: '/' (lv_range_4= ruleVersionRange )
                            {
                            match(input,13,FOLLOW_13_in_ruleCapabilityReferencedPart8018); 

                                    createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_0_3_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3419:1: (lv_range_4= ruleVersionRange )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3422:6: lv_range_4= ruleVersionRange
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getRangeVersionRangeParserRuleCall_0_3_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart8052);
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

                    match(input,51,FOLLOW_51_in_ruleCapabilityReferencedPart8067); 

                            createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getNumberSignKeyword_0_4(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3444:1: (lv_partName_6= rulePartName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3447:6: lv_partName_6= rulePartName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getPartNamePartNameParserRuleCall_0_5_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePartName_in_ruleCapabilityReferencedPart8101);
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
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3466:6: ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3466:6: ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3466:7: (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )?
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3466:7: (lv_interface_7= ruleInterfaceName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3469:6: lv_interface_7= ruleInterfaceName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getInterfaceInterfaceNameParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart8147);
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

                    match(input,13,FOLLOW_13_in_ruleCapabilityReferencedPart8160); 

                            createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_1_1(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3491:1: (lv_name_9= ruleUnitName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3494:6: lv_name_9= ruleUnitName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getNameUnitNameParserRuleCall_1_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart8194);
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

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3512:2: ( '/' (lv_range_11= ruleVersionRange ) )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==13) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3512:3: '/' (lv_range_11= ruleVersionRange )
                            {
                            match(input,13,FOLLOW_13_in_ruleCapabilityReferencedPart8208); 

                                    createLeafNode(grammarAccess.getCapabilityReferencedPartAccess().getSolidusKeyword_1_3_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3516:1: (lv_range_11= ruleVersionRange )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3519:6: lv_range_11= ruleVersionRange
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getCapabilityReferencedPartAccess().getRangeVersionRangeParserRuleCall_1_3_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart8242);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3544:1: entryRuleCompoundReferences returns [EObject current=null] : iv_ruleCompoundReferences= ruleCompoundReferences EOF ;
    public final EObject entryRuleCompoundReferences() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompoundReferences = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3544:60: (iv_ruleCompoundReferences= ruleCompoundReferences EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3545:2: iv_ruleCompoundReferences= ruleCompoundReferences EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCompoundReferencesRule(), currentNode); 
            pushFollow(FOLLOW_ruleCompoundReferences_in_entryRuleCompoundReferences8282);
            iv_ruleCompoundReferences=ruleCompoundReferences();
            _fsp--;

             current =iv_ruleCompoundReferences; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCompoundReferences8292); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3552:1: ruleCompoundReferences returns [EObject current=null] : ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' ) ;
    public final EObject ruleCompoundReferences() throws RecognitionException {
        EObject current = null;

        EObject lv_prerequisites_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3557:6: ( ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3558:1: ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3558:1: ( '{' (lv_prerequisites_1= rulePrerequisite )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3558:2: '{' (lv_prerequisites_1= rulePrerequisite )* '}'
            {
            match(input,38,FOLLOW_38_in_ruleCompoundReferences8326); 

                    createLeafNode(grammarAccess.getCompoundReferencesAccess().getLeftCurlyBracketKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3562:1: (lv_prerequisites_1= rulePrerequisite )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( ((LA75_0>=RULE_ID && LA75_0<=RULE_STRING)||LA75_0==38||LA75_0==47||LA75_0==54||LA75_0==56) ) {
                    alt75=1;
                }


                switch (alt75) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3565:6: lv_prerequisites_1= rulePrerequisite
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getCompoundReferencesAccess().getPrerequisitesPrerequisiteParserRuleCall_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePrerequisite_in_ruleCompoundReferences8360);
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
            	    break loop75;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleCompoundReferences8374); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3594:1: entryRuleAction returns [EObject current=null] : iv_ruleAction= ruleAction EOF ;
    public final EObject entryRuleAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAction = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3594:48: (iv_ruleAction= ruleAction EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3595:2: iv_ruleAction= ruleAction EOF
            {
             currentNode = createCompositeNode(grammarAccess.getActionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAction_in_entryRuleAction8407);
            iv_ruleAction=ruleAction();
            _fsp--;

             current =iv_ruleAction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAction8417); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3602:1: ruleAction returns [EObject current=null] : ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ( '...' (lv_actorParameters_11= ruleAnonymousParameter ) ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_16= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )* )? (lv_asserts_19= rulePreConditionAssert )? (lv_asserts_20= rulePostConditionAssert )? '{' ( 'properties' (lv_properties_23= rulePropertyStatements ) )? (lv_advice_24= ruleAdvice )? (lv_resultGroup_25= ruleActionInputGroup )? (lv_layout_26= ruleLayout )* '}' ) ;
    public final EObject ruleAction() throws RecognitionException {
        EObject current = null;

        Enumerator lv_visibility_0 = null;

        Enumerator lv_executionMode_1 = null;

        EObject lv_actorParameters_7 = null;

        EObject lv_actorParameters_9 = null;

        EObject lv_actorParameters_11 = null;

        EObject lv_actorParameters_13 = null;

        EObject lv_providedCapabilities_16 = null;

        EObject lv_providedCapabilities_18 = null;

        EObject lv_asserts_19 = null;

        EObject lv_asserts_20 = null;

        EObject lv_properties_23 = null;

        EObject lv_advice_24 = null;

        EObject lv_resultGroup_25 = null;

        EObject lv_layout_26 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3607:6: ( ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ( '...' (lv_actorParameters_11= ruleAnonymousParameter ) ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_16= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )* )? (lv_asserts_19= rulePreConditionAssert )? (lv_asserts_20= rulePostConditionAssert )? '{' ( 'properties' (lv_properties_23= rulePropertyStatements ) )? (lv_advice_24= ruleAdvice )? (lv_resultGroup_25= ruleActionInputGroup )? (lv_layout_26= ruleLayout )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3608:1: ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ( '...' (lv_actorParameters_11= ruleAnonymousParameter ) ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_16= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )* )? (lv_asserts_19= rulePreConditionAssert )? (lv_asserts_20= rulePostConditionAssert )? '{' ( 'properties' (lv_properties_23= rulePropertyStatements ) )? (lv_advice_24= ruleAdvice )? (lv_resultGroup_25= ruleActionInputGroup )? (lv_layout_26= ruleLayout )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3608:1: ( (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ( '...' (lv_actorParameters_11= ruleAnonymousParameter ) ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_16= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )* )? (lv_asserts_19= rulePreConditionAssert )? (lv_asserts_20= rulePostConditionAssert )? '{' ( 'properties' (lv_properties_23= rulePropertyStatements ) )? (lv_advice_24= ruleAdvice )? (lv_resultGroup_25= ruleActionInputGroup )? (lv_layout_26= ruleLayout )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3608:2: (lv_visibility_0= ruleVisibility )? (lv_executionMode_1= ruleExecutionMode )? 'action' rulePartName 'actor' ruleQualifiedName '(' ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )? ( '...' (lv_actorParameters_11= ruleAnonymousParameter ) ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )* )? ')' ( 'provides' (lv_providedCapabilities_16= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )* )? (lv_asserts_19= rulePreConditionAssert )? (lv_asserts_20= rulePostConditionAssert )? '{' ( 'properties' (lv_properties_23= rulePropertyStatements ) )? (lv_advice_24= ruleAdvice )? (lv_resultGroup_25= ruleActionInputGroup )? (lv_layout_26= ruleLayout )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3608:2: (lv_visibility_0= ruleVisibility )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( ((LA76_0>=77 && LA76_0<=78)) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3611:6: lv_visibility_0= ruleVisibility
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getVisibilityVisibilityEnumRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleVisibility_in_ruleAction8476);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3629:3: (lv_executionMode_1= ruleExecutionMode )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==45||LA77_0==79) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3632:6: lv_executionMode_1= ruleExecutionMode
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getExecutionModeExecutionModeEnumRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleExecutionMode_in_ruleAction8515);
                    lv_executionMode_1=ruleExecutionMode();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "executionMode", lv_executionMode_1, "ExecutionMode", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,58,FOLLOW_58_in_ruleAction8529); 

                    createLeafNode(grammarAccess.getActionAccess().getActionKeyword_2(), null); 
                
            pushFollow(FOLLOW_rulePartName_in_ruleAction8538);
            rulePartName();
            _fsp--;

            match(input,59,FOLLOW_59_in_ruleAction8539); 

                    createLeafNode(grammarAccess.getActionAccess().getActorKeyword_4(), null); 
                
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleAction8548);
            ruleQualifiedName();
            _fsp--;

            match(input,28,FOLLOW_28_in_ruleAction8549); 

                    createLeafNode(grammarAccess.getActionAccess().getLeftParenthesisKeyword_6(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3662:1: ( (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )* )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==RULE_ID) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3662:2: (lv_actorParameters_7= ruleParameter ) ( ',' (lv_actorParameters_9= ruleParameter ) )*
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3662:2: (lv_actorParameters_7= ruleParameter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3665:6: lv_actorParameters_7= ruleParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getActorParametersParameterParserRuleCall_7_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleParameter_in_ruleAction8584);
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

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3683:2: ( ',' (lv_actorParameters_9= ruleParameter ) )*
                    loop78:
                    do {
                        int alt78=2;
                        int LA78_0 = input.LA(1);

                        if ( (LA78_0==29) ) {
                            alt78=1;
                        }


                        switch (alt78) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3683:3: ',' (lv_actorParameters_9= ruleParameter )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleAction8598); 

                    	            createLeafNode(grammarAccess.getActionAccess().getCommaKeyword_7_1_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3687:1: (lv_actorParameters_9= ruleParameter )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3690:6: lv_actorParameters_9= ruleParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getActorParametersParameterParserRuleCall_7_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleParameter_in_ruleAction8632);
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
                    	    break loop78;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3708:6: ( '...' (lv_actorParameters_11= ruleAnonymousParameter ) ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )* )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==60) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3708:7: '...' (lv_actorParameters_11= ruleAnonymousParameter ) ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )*
                    {
                    match(input,60,FOLLOW_60_in_ruleAction8650); 

                            createLeafNode(grammarAccess.getActionAccess().getFullStopFullStopFullStopKeyword_8_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3712:1: (lv_actorParameters_11= ruleAnonymousParameter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3715:6: lv_actorParameters_11= ruleAnonymousParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getActorParametersAnonymousParameterParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAnonymousParameter_in_ruleAction8684);
                    lv_actorParameters_11=ruleAnonymousParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "actorParameters", lv_actorParameters_11, "AnonymousParameter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3733:2: ( ',' (lv_actorParameters_13= ruleAnonymousParameter ) )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==29) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3733:3: ',' (lv_actorParameters_13= ruleAnonymousParameter )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleAction8698); 

                    	            createLeafNode(grammarAccess.getActionAccess().getCommaKeyword_8_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3737:1: (lv_actorParameters_13= ruleAnonymousParameter )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3740:6: lv_actorParameters_13= ruleAnonymousParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getActorParametersAnonymousParameterParserRuleCall_8_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAnonymousParameter_in_ruleAction8732);
                    	    lv_actorParameters_13=ruleAnonymousParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "actorParameters", lv_actorParameters_13, "AnonymousParameter", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop80;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,31,FOLLOW_31_in_ruleAction8749); 

                    createLeafNode(grammarAccess.getActionAccess().getRightParenthesisKeyword_9(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3762:1: ( 'provides' (lv_providedCapabilities_16= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )* )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==40) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3762:2: 'provides' (lv_providedCapabilities_16= ruleProvidedCapability ) ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )*
                    {
                    match(input,40,FOLLOW_40_in_ruleAction8759); 

                            createLeafNode(grammarAccess.getActionAccess().getProvidesKeyword_10_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3766:1: (lv_providedCapabilities_16= ruleProvidedCapability )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3769:6: lv_providedCapabilities_16= ruleProvidedCapability
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleAction8793);
                    lv_providedCapabilities_16=ruleProvidedCapability();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "providedCapabilities", lv_providedCapabilities_16, "ProvidedCapability", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3787:2: ( ',' (lv_providedCapabilities_18= ruleProvidedCapability ) )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==29) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3787:3: ',' (lv_providedCapabilities_18= ruleProvidedCapability )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleAction8807); 

                    	            createLeafNode(grammarAccess.getActionAccess().getCommaKeyword_10_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3791:1: (lv_providedCapabilities_18= ruleProvidedCapability )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3794:6: lv_providedCapabilities_18= ruleProvidedCapability
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getProvidedCapabilitiesProvidedCapabilityParserRuleCall_10_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleProvidedCapability_in_ruleAction8841);
                    	    lv_providedCapabilities_18=ruleProvidedCapability();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "providedCapabilities", lv_providedCapabilities_18, "ProvidedCapability", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3812:6: (lv_asserts_19= rulePreConditionAssert )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==64) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3815:6: lv_asserts_19= rulePreConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getAssertsPreConditionAssertParserRuleCall_11_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePreConditionAssert_in_ruleAction8883);
                    lv_asserts_19=rulePreConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_19, "PreConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3833:3: (lv_asserts_20= rulePostConditionAssert )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==65) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3836:6: lv_asserts_20= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getAssertsPostConditionAssertParserRuleCall_12_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleAction8922);
                    lv_asserts_20=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_20, "PostConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleAction8936); 

                    createLeafNode(grammarAccess.getActionAccess().getLeftCurlyBracketKeyword_13(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3858:1: ( 'properties' (lv_properties_23= rulePropertyStatements ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==39) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3858:2: 'properties' (lv_properties_23= rulePropertyStatements )
                    {
                    match(input,39,FOLLOW_39_in_ruleAction8946); 

                            createLeafNode(grammarAccess.getActionAccess().getPropertiesKeyword_14_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3862:1: (lv_properties_23= rulePropertyStatements )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3865:6: lv_properties_23= rulePropertyStatements
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getPropertiesPropertyStatementsParserRuleCall_14_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePropertyStatements_in_ruleAction8980);
                    lv_properties_23=rulePropertyStatements();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "properties", lv_properties_23, "PropertyStatements", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3883:4: (lv_advice_24= ruleAdvice )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==44) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3886:6: lv_advice_24= ruleAdvice
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getAdviceAdviceParserRuleCall_15_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAdvice_in_ruleAction9020);
                    lv_advice_24=ruleAdvice();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "advice", lv_advice_24, "Advice", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3904:3: (lv_resultGroup_25= ruleActionInputGroup )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==55) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3907:6: lv_resultGroup_25= ruleActionInputGroup
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getResultGroupActionInputGroupParserRuleCall_16_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleActionInputGroup_in_ruleAction9059);
                    lv_resultGroup_25=ruleActionInputGroup();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "resultGroup", lv_resultGroup_25, "ActionInputGroup", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3925:3: (lv_layout_26= ruleLayout )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==47||LA89_0==61) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3928:6: lv_layout_26= ruleLayout
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getActionAccess().getLayoutLayoutParserRuleCall_17_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleLayout_in_ruleAction9098);
            	    lv_layout_26=ruleLayout();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getActionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "layout", lv_layout_26, "Layout", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop89;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleAction9112); 

                    createLeafNode(grammarAccess.getActionAccess().getRightCurlyBracketKeyword_18(), null); 
                

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3957:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3957:51: (iv_ruleParameter= ruleParameter EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3958:2: iv_ruleParameter= ruleParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getParameterRule(), currentNode); 
            pushFollow(FOLLOW_ruleParameter_in_entryRuleParameter9145);
            iv_ruleParameter=ruleParameter();
            _fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParameter9155); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3965:1: ruleParameter returns [EObject current=null] : ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;

        EObject lv_value_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3970:6: ( ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3971:1: ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3971:1: ( (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3971:2: (lv_name_0= ruleQualifiedName ) '=' (lv_value_2= ruleExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3971:2: (lv_name_0= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3974:6: lv_name_0= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getParameterAccess().getNameQualifiedNameParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleParameter9214);
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

            match(input,21,FOLLOW_21_in_ruleParameter9227); 

                    createLeafNode(grammarAccess.getParameterAccess().getEqualsSignKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3996:1: (lv_value_2= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:3999:6: lv_value_2= ruleExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getParameterAccess().getValueExpressionParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleParameter9261);
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


    // $ANTLR start entryRuleAnonymousParameter
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4024:1: entryRuleAnonymousParameter returns [EObject current=null] : iv_ruleAnonymousParameter= ruleAnonymousParameter EOF ;
    public final EObject entryRuleAnonymousParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnonymousParameter = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4024:60: (iv_ruleAnonymousParameter= ruleAnonymousParameter EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4025:2: iv_ruleAnonymousParameter= ruleAnonymousParameter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAnonymousParameterRule(), currentNode); 
            pushFollow(FOLLOW_ruleAnonymousParameter_in_entryRuleAnonymousParameter9298);
            iv_ruleAnonymousParameter=ruleAnonymousParameter();
            _fsp--;

             current =iv_ruleAnonymousParameter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnonymousParameter9308); 

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
    // $ANTLR end entryRuleAnonymousParameter


    // $ANTLR start ruleAnonymousParameter
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4032:1: ruleAnonymousParameter returns [EObject current=null] : this_Expression_0= ruleExpression ;
    public final EObject ruleAnonymousParameter() throws RecognitionException {
        EObject current = null;

        EObject this_Expression_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4037:6: (this_Expression_0= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4039:5: this_Expression_0= ruleExpression
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAnonymousParameterAccess().getExpressionParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleExpression_in_ruleAnonymousParameter9354);
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
    // $ANTLR end ruleAnonymousParameter


    // $ANTLR start entryRuleLayout
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4054:1: entryRuleLayout returns [EObject current=null] : iv_ruleLayout= ruleLayout EOF ;
    public final EObject entryRuleLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLayout = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4054:48: (iv_ruleLayout= ruleLayout EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4055:2: iv_ruleLayout= ruleLayout EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLayoutRule(), currentNode); 
            pushFollow(FOLLOW_ruleLayout_in_entryRuleLayout9385);
            iv_ruleLayout=ruleLayout();
            _fsp--;

             current =iv_ruleLayout; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLayout9395); 

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
    // $ANTLR end entryRuleLayout


    // $ANTLR start ruleLayout
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4062:1: ruleLayout returns [EObject current=null] : ( ( 'when' (lv_filter_1= ruleFilter ) )? 'layout' (lv_name_3= ruleQualifiedName )? (lv_asserts_4= rulePostConditionAssert )? '{' (lv_paths_6= rulePathGroup )* '}' ) ;
    public final EObject ruleLayout() throws RecognitionException {
        EObject current = null;

        EObject lv_filter_1 = null;

        AntlrDatatypeRuleToken lv_name_3 = null;

        EObject lv_asserts_4 = null;

        EObject lv_paths_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4067:6: ( ( ( 'when' (lv_filter_1= ruleFilter ) )? 'layout' (lv_name_3= ruleQualifiedName )? (lv_asserts_4= rulePostConditionAssert )? '{' (lv_paths_6= rulePathGroup )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4068:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? 'layout' (lv_name_3= ruleQualifiedName )? (lv_asserts_4= rulePostConditionAssert )? '{' (lv_paths_6= rulePathGroup )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4068:1: ( ( 'when' (lv_filter_1= ruleFilter ) )? 'layout' (lv_name_3= ruleQualifiedName )? (lv_asserts_4= rulePostConditionAssert )? '{' (lv_paths_6= rulePathGroup )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4068:2: ( 'when' (lv_filter_1= ruleFilter ) )? 'layout' (lv_name_3= ruleQualifiedName )? (lv_asserts_4= rulePostConditionAssert )? '{' (lv_paths_6= rulePathGroup )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4068:2: ( 'when' (lv_filter_1= ruleFilter ) )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==47) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4068:3: 'when' (lv_filter_1= ruleFilter )
                    {
                    match(input,47,FOLLOW_47_in_ruleLayout9430); 

                            createLeafNode(grammarAccess.getLayoutAccess().getWhenKeyword_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4072:1: (lv_filter_1= ruleFilter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4075:6: lv_filter_1= ruleFilter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLayoutAccess().getFilterFilterParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleFilter_in_ruleLayout9464);
                    lv_filter_1=ruleFilter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLayoutRule().getType().getClassifier());
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

            match(input,61,FOLLOW_61_in_ruleLayout9479); 

                    createLeafNode(grammarAccess.getLayoutAccess().getLayoutKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4097:1: (lv_name_3= ruleQualifiedName )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==RULE_ID) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4100:6: lv_name_3= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLayoutAccess().getNameQualifiedNameParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleLayout9513);
                    lv_name_3=ruleQualifiedName();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLayoutRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_3, "QualifiedName", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4118:3: (lv_asserts_4= rulePostConditionAssert )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==65) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4121:6: lv_asserts_4= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getLayoutAccess().getAssertsPostConditionAssertParserRuleCall_3_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleLayout9552);
                    lv_asserts_4=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLayoutRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "asserts", lv_asserts_4, "PostConditionAssert", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,38,FOLLOW_38_in_ruleLayout9566); 

                    createLeafNode(grammarAccess.getLayoutAccess().getLeftCurlyBracketKeyword_4(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4143:1: (lv_paths_6= rulePathGroup )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( ((LA93_0>=RULE_ID && LA93_0<=RULE_STRING)||LA93_0==13||LA93_0==47||(LA93_0>=53 && LA93_0<=54)) ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4146:6: lv_paths_6= rulePathGroup
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getLayoutAccess().getPathsPathGroupParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePathGroup_in_ruleLayout9600);
            	    lv_paths_6=rulePathGroup();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getLayoutRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "paths", lv_paths_6, "PathGroup", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop93;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleLayout9614); 

                    createLeafNode(grammarAccess.getLayoutAccess().getRightCurlyBracketKeyword_6(), null); 
                

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
    // $ANTLR end ruleLayout


    // $ANTLR start entryRuleActionInputGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4175:1: entryRuleActionInputGroup returns [EObject current=null] : iv_ruleActionInputGroup= ruleActionInputGroup EOF ;
    public final EObject entryRuleActionInputGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActionInputGroup = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4175:58: (iv_ruleActionInputGroup= ruleActionInputGroup EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4176:2: iv_ruleActionInputGroup= ruleActionInputGroup EOF
            {
             currentNode = createCompositeNode(grammarAccess.getActionInputGroupRule(), currentNode); 
            pushFollow(FOLLOW_ruleActionInputGroup_in_entryRuleActionInputGroup9647);
            iv_ruleActionInputGroup=ruleActionInputGroup();
            _fsp--;

             current =iv_ruleActionInputGroup; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleActionInputGroup9657); 

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
    // $ANTLR end entryRuleActionInputGroup


    // $ANTLR start ruleActionInputGroup
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4183:1: ruleActionInputGroup returns [EObject current=null] : ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' ) ;
    public final EObject ruleActionInputGroup() throws RecognitionException {
        EObject current = null;

        EObject lv_asserts_1 = null;

        EObject lv_asserts_2 = null;

        EObject lv_prerequisites_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4188:6: ( ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4189:1: ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4189:1: ( 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4189:2: 'group' (lv_asserts_1= rulePreConditionAssert )? (lv_asserts_2= rulePostConditionAssert )? '{' (lv_prerequisites_4= rulePrerequisite )* '}'
            {
            match(input,55,FOLLOW_55_in_ruleActionInputGroup9691); 

                    createLeafNode(grammarAccess.getActionInputGroupAccess().getGroupKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4193:1: (lv_asserts_1= rulePreConditionAssert )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==64) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4196:6: lv_asserts_1= rulePreConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionInputGroupAccess().getAssertsPreConditionAssertParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePreConditionAssert_in_ruleActionInputGroup9725);
                    lv_asserts_1=rulePreConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionInputGroupRule().getType().getClassifier());
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4214:3: (lv_asserts_2= rulePostConditionAssert )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==65) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4217:6: lv_asserts_2= rulePostConditionAssert
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getActionInputGroupAccess().getAssertsPostConditionAssertParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePostConditionAssert_in_ruleActionInputGroup9764);
                    lv_asserts_2=rulePostConditionAssert();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getActionInputGroupRule().getType().getClassifier());
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

            match(input,38,FOLLOW_38_in_ruleActionInputGroup9778); 

                    createLeafNode(grammarAccess.getActionInputGroupAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4239:1: (lv_prerequisites_4= rulePrerequisite )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( ((LA96_0>=RULE_ID && LA96_0<=RULE_STRING)||LA96_0==38||LA96_0==47||LA96_0==54||LA96_0==56) ) {
                    alt96=1;
                }


                switch (alt96) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4242:6: lv_prerequisites_4= rulePrerequisite
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getActionInputGroupAccess().getPrerequisitesPrerequisiteParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePrerequisite_in_ruleActionInputGroup9812);
            	    lv_prerequisites_4=rulePrerequisite();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getActionInputGroupRule().getType().getClassifier());
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
            	    break loop96;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleActionInputGroup9826); 

                    createLeafNode(grammarAccess.getActionInputGroupAccess().getRightCurlyBracketKeyword_5(), null); 
                

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
    // $ANTLR end ruleActionInputGroup


    // $ANTLR start entryRuleRepositoryConfiguration
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4271:1: entryRuleRepositoryConfiguration returns [EObject current=null] : iv_ruleRepositoryConfiguration= ruleRepositoryConfiguration EOF ;
    public final EObject entryRuleRepositoryConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRepositoryConfiguration = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4271:65: (iv_ruleRepositoryConfiguration= ruleRepositoryConfiguration EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4272:2: iv_ruleRepositoryConfiguration= ruleRepositoryConfiguration EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRepositoryConfigurationRule(), currentNode); 
            pushFollow(FOLLOW_ruleRepositoryConfiguration_in_entryRuleRepositoryConfiguration9859);
            iv_ruleRepositoryConfiguration=ruleRepositoryConfiguration();
            _fsp--;

             current =iv_ruleRepositoryConfiguration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRepositoryConfiguration9869); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4279:1: ruleRepositoryConfiguration returns [EObject current=null] : ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' ) ;
    public final EObject ruleRepositoryConfiguration() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_location_0 = null;

        AntlrDatatypeRuleToken lv_resolverClass_2 = null;

        EObject lv_advice_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4284:6: ( ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4285:1: ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4285:1: ( ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4285:2: ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) ) (lv_advice_3= ruleCompoundAdvice )? ';'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4285:2: ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) )
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==RULE_STRING) ) {
                alt97=1;
            }
            else if ( (LA97_0==62) ) {
                alt97=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4285:2: ( (lv_location_0= ruleURI ) | ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) ) )", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4285:3: (lv_location_0= ruleURI )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4285:3: (lv_location_0= ruleURI )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4288:6: lv_location_0= ruleURI
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRepositoryConfigurationAccess().getLocationURIParserRuleCall_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleURI_in_ruleRepositoryConfiguration9929);
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
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4307:6: ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4307:6: ( 'resolver' (lv_resolverClass_2= ruleQualifiedName ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4307:7: 'resolver' (lv_resolverClass_2= ruleQualifiedName )
                    {
                    match(input,62,FOLLOW_62_in_ruleRepositoryConfiguration9949); 

                            createLeafNode(grammarAccess.getRepositoryConfigurationAccess().getResolverKeyword_0_1_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4311:1: (lv_resolverClass_2= ruleQualifiedName )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4314:6: lv_resolverClass_2= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRepositoryConfigurationAccess().getResolverClassQualifiedNameParserRuleCall_0_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleRepositoryConfiguration9983);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4332:4: (lv_advice_3= ruleCompoundAdvice )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==38) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4335:6: lv_advice_3= ruleCompoundAdvice
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRepositoryConfigurationAccess().getAdviceCompoundAdviceParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleRepositoryConfiguration10023);
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

            match(input,34,FOLLOW_34_in_ruleRepositoryConfiguration10037); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4364:1: entryRuleURI returns [String current=null] : iv_ruleURI= ruleURI EOF ;
    public final String entryRuleURI() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleURI = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4364:44: (iv_ruleURI= ruleURI EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4365:2: iv_ruleURI= ruleURI EOF
            {
             currentNode = createCompositeNode(grammarAccess.getURIRule(), currentNode); 
            pushFollow(FOLLOW_ruleURI_in_entryRuleURI10071);
            iv_ruleURI=ruleURI();
            _fsp--;

             current =iv_ruleURI.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleURI10082); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4372:1: ruleURI returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleURI() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4378:6: (this_STRING_0= RULE_STRING )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4379:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleURI10121); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4394:1: entryRuleNamedAdvice returns [EObject current=null] : iv_ruleNamedAdvice= ruleNamedAdvice EOF ;
    public final EObject entryRuleNamedAdvice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamedAdvice = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4394:53: (iv_ruleNamedAdvice= ruleNamedAdvice EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4395:2: iv_ruleNamedAdvice= ruleNamedAdvice EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamedAdviceRule(), currentNode); 
            pushFollow(FOLLOW_ruleNamedAdvice_in_entryRuleNamedAdvice10163);
            iv_ruleNamedAdvice=ruleNamedAdvice();
            _fsp--;

             current =iv_ruleNamedAdvice; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamedAdvice10173); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4402:1: ruleNamedAdvice returns [EObject current=null] : ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) ) ;
    public final EObject ruleNamedAdvice() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;

        EObject lv_advice_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4407:6: ( ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4408:1: ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4408:1: ( (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4408:2: (lv_name_0= ruleQualifiedName )? (lv_advice_1= ruleCompoundAdvice )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4408:2: (lv_name_0= ruleQualifiedName )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==RULE_ID) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4411:6: lv_name_0= ruleQualifiedName
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNamedAdviceAccess().getNameQualifiedNameParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleNamedAdvice10232);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4429:3: (lv_advice_1= ruleCompoundAdvice )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4432:6: lv_advice_1= ruleCompoundAdvice
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNamedAdviceAccess().getAdviceCompoundAdviceParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleNamedAdvice10271);
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


    // $ANTLR start entryRuleAdvice
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4457:1: entryRuleAdvice returns [EObject current=null] : iv_ruleAdvice= ruleAdvice EOF ;
    public final EObject entryRuleAdvice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvice = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4457:48: (iv_ruleAdvice= ruleAdvice EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4458:2: iv_ruleAdvice= ruleAdvice EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdviceRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvice_in_entryRuleAdvice10308);
            iv_ruleAdvice=ruleAdvice();
            _fsp--;

             current =iv_ruleAdvice; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvice10318); 

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
    // $ANTLR end entryRuleAdvice


    // $ANTLR start ruleAdvice
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4465:1: ruleAdvice returns [EObject current=null] : ( 'advice' this_CompoundAdvice_1= ruleCompoundAdvice ) ;
    public final EObject ruleAdvice() throws RecognitionException {
        EObject current = null;

        EObject this_CompoundAdvice_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4470:6: ( ( 'advice' this_CompoundAdvice_1= ruleCompoundAdvice ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4471:1: ( 'advice' this_CompoundAdvice_1= ruleCompoundAdvice )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4471:1: ( 'advice' this_CompoundAdvice_1= ruleCompoundAdvice )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4471:2: 'advice' this_CompoundAdvice_1= ruleCompoundAdvice
            {
            match(input,44,FOLLOW_44_in_ruleAdvice10352); 

                    createLeafNode(grammarAccess.getAdviceAccess().getAdviceKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getAdviceAccess().getCompoundAdviceParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleAdvice10374);
            this_CompoundAdvice_1=ruleCompoundAdvice();
            _fsp--;

             
                    current = this_CompoundAdvice_1; 
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
    // $ANTLR end ruleAdvice


    // $ANTLR start entryRuleCompoundAdvice
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4491:1: entryRuleCompoundAdvice returns [EObject current=null] : iv_ruleCompoundAdvice= ruleCompoundAdvice EOF ;
    public final EObject entryRuleCompoundAdvice() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompoundAdvice = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4491:56: (iv_ruleCompoundAdvice= ruleCompoundAdvice EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4492:2: iv_ruleCompoundAdvice= ruleCompoundAdvice EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCompoundAdviceRule(), currentNode); 
            pushFollow(FOLLOW_ruleCompoundAdvice_in_entryRuleCompoundAdvice10406);
            iv_ruleCompoundAdvice=ruleCompoundAdvice();
            _fsp--;

             current =iv_ruleCompoundAdvice; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCompoundAdvice10416); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4499:1: ruleCompoundAdvice returns [EObject current=null] : ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' ) ;
    public final EObject ruleCompoundAdvice() throws RecognitionException {
        EObject current = null;

        EObject lv_advice_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4504:6: ( ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4505:1: ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4505:1: ( '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4505:2: '{' ( (lv_advice_1= ruleAdviceStatement ) ';' )* '}'
            {
            match(input,38,FOLLOW_38_in_ruleCompoundAdvice10450); 

                    createLeafNode(grammarAccess.getCompoundAdviceAccess().getLeftCurlyBracketKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4509:1: ( (lv_advice_1= ruleAdviceStatement ) ';' )*
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( (LA100_0==RULE_ID||LA100_0==11||LA100_0==13||LA100_0==26||LA100_0==63) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4509:2: (lv_advice_1= ruleAdviceStatement ) ';'
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4509:2: (lv_advice_1= ruleAdviceStatement )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4512:6: lv_advice_1= ruleAdviceStatement
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getCompoundAdviceAccess().getAdviceAdviceStatementParserRuleCall_1_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdviceStatement_in_ruleCompoundAdvice10485);
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

            	    match(input,34,FOLLOW_34_in_ruleCompoundAdvice10498); 

            	            createLeafNode(grammarAccess.getCompoundAdviceAccess().getSemicolonKeyword_1_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop100;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_ruleCompoundAdvice10509); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4545:1: entryRuleAdviceStatement returns [EObject current=null] : iv_ruleAdviceStatement= ruleAdviceStatement EOF ;
    public final EObject entryRuleAdviceStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdviceStatement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4545:57: (iv_ruleAdviceStatement= ruleAdviceStatement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4546:2: iv_ruleAdviceStatement= ruleAdviceStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdviceStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdviceStatement_in_entryRuleAdviceStatement10542);
            iv_ruleAdviceStatement=ruleAdviceStatement();
            _fsp--;

             current =iv_ruleAdviceStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdviceStatement10552); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4553:1: ruleAdviceStatement returns [EObject current=null] : ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) ) ;
    public final EObject ruleAdviceStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_path_0 = null;

        EObject lv_value_2 = null;

        EObject lv_advice_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4558:6: ( ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4559:1: ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4559:1: ( (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4559:2: (lv_path_0= ruleAdvicePath ) ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4559:2: (lv_path_0= ruleAdvicePath )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4562:6: lv_path_0= ruleAdvicePath
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAdviceStatementAccess().getPathAdvicePathParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleAdvicePath_in_ruleAdviceStatement10611);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4580:2: ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==21) ) {
                alt101=1;
            }
            else if ( (LA101_0==38) ) {
                alt101=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4580:2: ( ( '=' (lv_value_2= ruleExpression ) ) | (lv_advice_3= ruleCompoundAdvice ) )", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4580:3: ( '=' (lv_value_2= ruleExpression ) )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4580:3: ( '=' (lv_value_2= ruleExpression ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4580:4: '=' (lv_value_2= ruleExpression )
                    {
                    match(input,21,FOLLOW_21_in_ruleAdviceStatement10626); 

                            createLeafNode(grammarAccess.getAdviceStatementAccess().getEqualsSignKeyword_1_0_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4584:1: (lv_value_2= ruleExpression )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4587:6: lv_value_2= ruleExpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAdviceStatementAccess().getValueExpressionParserRuleCall_1_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleAdviceStatement10660);
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
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4606:6: (lv_advice_3= ruleCompoundAdvice )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4606:6: (lv_advice_3= ruleCompoundAdvice )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4609:6: lv_advice_3= ruleCompoundAdvice
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAdviceStatementAccess().getAdviceCompoundAdviceParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleCompoundAdvice_in_ruleAdviceStatement10705);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4634:1: entryRuleAdvicePath returns [EObject current=null] : iv_ruleAdvicePath= ruleAdvicePath EOF ;
    public final EObject entryRuleAdvicePath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePath = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4634:52: (iv_ruleAdvicePath= ruleAdvicePath EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4635:2: iv_ruleAdvicePath= ruleAdvicePath EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePath_in_entryRuleAdvicePath10743);
            iv_ruleAdvicePath=ruleAdvicePath();
            _fsp--;

             current =iv_ruleAdvicePath; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePath10753); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4642:1: ruleAdvicePath returns [EObject current=null] : ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* ) ;
    public final EObject ruleAdvicePath() throws RecognitionException {
        EObject current = null;

        EObject lv_pathElements_0 = null;

        EObject lv_pathElements_1 = null;

        EObject lv_pathElements_2 = null;

        EObject lv_pathElement_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4647:6: ( ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4648:1: ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4648:1: ( (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4648:2: (lv_pathElements_0= ruleAdvicePathSeparator )? (lv_pathElements_1= ruleAdvicePathElement ) ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )*
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4648:2: (lv_pathElements_0= ruleAdvicePathSeparator )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==13) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4651:6: lv_pathElements_0= ruleAdvicePathSeparator
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementsAdvicePathSeparatorParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10812);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4669:3: (lv_pathElements_1= ruleAdvicePathElement )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4672:6: lv_pathElements_1= ruleAdvicePathElement
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementsAdvicePathElementParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10851);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4690:2: ( (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement ) )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==13) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4690:3: (lv_pathElements_2= ruleAdvicePathSeparator ) (lv_pathElement_3= ruleAdvicePathElement )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4690:3: (lv_pathElements_2= ruleAdvicePathSeparator )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4693:6: lv_pathElements_2= ruleAdvicePathSeparator
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementsAdvicePathSeparatorParserRuleCall_2_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10890);
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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4711:2: (lv_pathElement_3= ruleAdvicePathElement )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4714:6: lv_pathElement_3= ruleAdvicePathElement
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAdvicePathAccess().getPathElementAdvicePathElementParserRuleCall_2_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10928);
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
            	    break loop103;
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4739:1: entryRuleAdvicePathSeparator returns [EObject current=null] : iv_ruleAdvicePathSeparator= ruleAdvicePathSeparator EOF ;
    public final EObject entryRuleAdvicePathSeparator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePathSeparator = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4739:61: (iv_ruleAdvicePathSeparator= ruleAdvicePathSeparator EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4740:2: iv_ruleAdvicePathSeparator= ruleAdvicePathSeparator EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathSeparatorRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePathSeparator_in_entryRuleAdvicePathSeparator10967);
            iv_ruleAdvicePathSeparator=ruleAdvicePathSeparator();
            _fsp--;

             current =iv_ruleAdvicePathSeparator; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePathSeparator10977); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4747:1: ruleAdvicePathSeparator returns [EObject current=null] : this_AdvicePathChildren_0= ruleAdvicePathChildren ;
    public final EObject ruleAdvicePathSeparator() throws RecognitionException {
        EObject current = null;

        EObject this_AdvicePathChildren_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4752:6: (this_AdvicePathChildren_0= ruleAdvicePathChildren )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4754:5: this_AdvicePathChildren_0= ruleAdvicePathChildren
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAdvicePathSeparatorAccess().getAdvicePathChildrenParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleAdvicePathChildren_in_ruleAdvicePathSeparator11023);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4769:1: entryRuleAdvicePathChildren returns [EObject current=null] : iv_ruleAdvicePathChildren= ruleAdvicePathChildren EOF ;
    public final EObject entryRuleAdvicePathChildren() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePathChildren = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4769:60: (iv_ruleAdvicePathChildren= ruleAdvicePathChildren EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4770:2: iv_ruleAdvicePathChildren= ruleAdvicePathChildren EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathChildrenRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePathChildren_in_entryRuleAdvicePathChildren11054);
            iv_ruleAdvicePathChildren=ruleAdvicePathChildren();
            _fsp--;

             current =iv_ruleAdvicePathChildren; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePathChildren11064); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4777:1: ruleAdvicePathChildren returns [EObject current=null] : ( () '/' ) ;
    public final EObject ruleAdvicePathChildren() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4782:6: ( ( () '/' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4783:1: ( () '/' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4783:1: ( () '/' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4783:2: () '/'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4783:2: ()
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4784:5: 
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

            match(input,13,FOLLOW_13_in_ruleAdvicePathChildren11107); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4805:1: entryRuleAdvicePathElement returns [EObject current=null] : iv_ruleAdvicePathElement= ruleAdvicePathElement EOF ;
    public final EObject entryRuleAdvicePathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvicePathElement = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4805:59: (iv_ruleAdvicePathElement= ruleAdvicePathElement EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4806:2: iv_ruleAdvicePathElement= ruleAdvicePathElement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdvicePathElementRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdvicePathElement_in_entryRuleAdvicePathElement11140);
            iv_ruleAdvicePathElement=ruleAdvicePathElement();
            _fsp--;

             current =iv_ruleAdvicePathElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdvicePathElement11150); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4813:1: ruleAdvicePathElement returns [EObject current=null] : ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) ) ;
    public final EObject ruleAdvicePathElement() throws RecognitionException {
        EObject current = null;

        Token lv_node_0=null;
        Token lv_node_4=null;
        EObject lv_predicate_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4818:6: ( ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4819:1: ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4819:1: ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) )
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==RULE_ID||LA106_0==11||LA106_0==26) ) {
                alt106=1;
            }
            else if ( (LA106_0==63) ) {
                alt106=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4819:1: ( ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? ) | (lv_node_4= '..' ) )", 106, 0, input);

                throw nvae;
            }
            switch (alt106) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4819:2: ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4819:2: ( (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )? )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4819:3: (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) ) ( '[' (lv_predicate_2= ruleExpression ) ']' )?
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4819:3: (lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' ) )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4821:6: lv_node_0= ( ruleQualifiedName | ruleWildcardNode | '.' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4821:16: ( ruleQualifiedName | ruleWildcardNode | '.' )
                    int alt104=3;
                    switch ( input.LA(1) ) {
                    case RULE_ID:
                        {
                        alt104=1;
                        }
                        break;
                    case 26:
                        {
                        alt104=2;
                        }
                        break;
                    case 11:
                        {
                        alt104=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("4821:16: ( ruleQualifiedName | ruleWildcardNode | '.' )", 104, 0, input);

                        throw nvae;
                    }

                    switch (alt104) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4821:18: ruleQualifiedName
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getAdvicePathElementAccess().getNodeQualifiedNameParserRuleCall_0_0_0_0(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleQualifiedName_in_ruleAdvicePathElement11202);
                            ruleQualifiedName();
                            _fsp--;

                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4828:7: ruleWildcardNode
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getAdvicePathElementAccess().getNodeWildcardNodeParserRuleCall_0_0_0_1(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleWildcardNode_in_ruleAdvicePathElement11216);
                            ruleWildcardNode();
                            _fsp--;

                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;
                        case 3 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4835:6: '.'
                            {
                            match(input,11,FOLLOW_11_in_ruleAdvicePathElement11226); 

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

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4855:2: ( '[' (lv_predicate_2= ruleExpression ) ']' )?
                    int alt105=2;
                    int LA105_0 = input.LA(1);

                    if ( (LA105_0==27) ) {
                        alt105=1;
                    }
                    switch (alt105) {
                        case 1 :
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4855:3: '[' (lv_predicate_2= ruleExpression ) ']'
                            {
                            match(input,27,FOLLOW_27_in_ruleAdvicePathElement11251); 

                                    createLeafNode(grammarAccess.getAdvicePathElementAccess().getLeftSquareBracketKeyword_0_1_0(), null); 
                                
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4859:1: (lv_predicate_2= ruleExpression )
                            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4862:6: lv_predicate_2= ruleExpression
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getAdvicePathElementAccess().getPredicateExpressionParserRuleCall_0_1_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleExpression_in_ruleAdvicePathElement11285);
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

                            match(input,30,FOLLOW_30_in_ruleAdvicePathElement11298); 

                                    createLeafNode(grammarAccess.getAdvicePathElementAccess().getRightSquareBracketKeyword_0_1_2(), null); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4885:6: (lv_node_4= '..' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4885:6: (lv_node_4= '..' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4887:6: lv_node_4= '..'
                    {
                    lv_node_4=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_ruleAdvicePathElement11328); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4913:1: entryRuleWildcardNode returns [String current=null] : iv_ruleWildcardNode= ruleWildcardNode EOF ;
    public final String entryRuleWildcardNode() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleWildcardNode = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4913:53: (iv_ruleWildcardNode= ruleWildcardNode EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4914:2: iv_ruleWildcardNode= ruleWildcardNode EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWildcardNodeRule(), currentNode); 
            pushFollow(FOLLOW_ruleWildcardNode_in_entryRuleWildcardNode11375);
            iv_ruleWildcardNode=ruleWildcardNode();
            _fsp--;

             current =iv_ruleWildcardNode.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWildcardNode11386); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4921:1: ruleWildcardNode returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '?' (kw= '?' )? ) ;
    public final AntlrDatatypeRuleToken ruleWildcardNode() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4927:6: ( (kw= '?' (kw= '?' )? ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4928:1: (kw= '?' (kw= '?' )? )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4928:1: (kw= '?' (kw= '?' )? )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4929:2: kw= '?' (kw= '?' )?
            {
            kw=(Token)input.LT(1);
            match(input,26,FOLLOW_26_in_ruleWildcardNode11424); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getWildcardNodeAccess().getQuestionMarkKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4934:1: (kw= '?' )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==26) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4935:2: kw= '?'
                    {
                    kw=(Token)input.LT(1);
                    match(input,26,FOLLOW_26_in_ruleWildcardNode11438); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4948:1: entryRuleFilter returns [EObject current=null] : iv_ruleFilter= ruleFilter EOF ;
    public final EObject entryRuleFilter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFilter = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4948:48: (iv_ruleFilter= ruleFilter EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4949:2: iv_ruleFilter= ruleFilter EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFilterRule(), currentNode); 
            pushFollow(FOLLOW_ruleFilter_in_entryRuleFilter11478);
            iv_ruleFilter=ruleFilter();
            _fsp--;

             current =iv_ruleFilter; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFilter11488); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4956:1: ruleFilter returns [EObject current=null] : ( '(' (lv_predicate_1= ruleExpression ) ')' ) ;
    public final EObject ruleFilter() throws RecognitionException {
        EObject current = null;

        EObject lv_predicate_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4961:6: ( ( '(' (lv_predicate_1= ruleExpression ) ')' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4962:1: ( '(' (lv_predicate_1= ruleExpression ) ')' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4962:1: ( '(' (lv_predicate_1= ruleExpression ) ')' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4962:2: '(' (lv_predicate_1= ruleExpression ) ')'
            {
            match(input,28,FOLLOW_28_in_ruleFilter11522); 

                    createLeafNode(grammarAccess.getFilterAccess().getLeftParenthesisKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4966:1: (lv_predicate_1= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4969:6: lv_predicate_1= ruleExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFilterAccess().getPredicateExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleFilter11556);
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

            match(input,31,FOLLOW_31_in_ruleFilter11569); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4998:1: entryRulePreConditionAssert returns [EObject current=null] : iv_rulePreConditionAssert= rulePreConditionAssert EOF ;
    public final EObject entryRulePreConditionAssert() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreConditionAssert = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4998:60: (iv_rulePreConditionAssert= rulePreConditionAssert EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:4999:2: iv_rulePreConditionAssert= rulePreConditionAssert EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPreConditionAssertRule(), currentNode); 
            pushFollow(FOLLOW_rulePreConditionAssert_in_entryRulePreConditionAssert11602);
            iv_rulePreConditionAssert=rulePreConditionAssert();
            _fsp--;

             current =iv_rulePreConditionAssert; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePreConditionAssert11612); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5006:1: rulePreConditionAssert returns [EObject current=null] : ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) ;
    public final EObject rulePreConditionAssert() throws RecognitionException {
        EObject current = null;

        Token lv_scope_0=null;
        EObject lv_asserts_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5011:6: ( ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5012:1: ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5012:1: ( (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5012:2: (lv_scope_0= 'precondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5012:2: (lv_scope_0= 'precondition' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5014:6: lv_scope_0= 'precondition'
            {
            lv_scope_0=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_rulePreConditionAssert11658); 

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

            match(input,38,FOLLOW_38_in_rulePreConditionAssert11680); 

                    createLeafNode(grammarAccess.getPreConditionAssertAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5037:1: (lv_asserts_2= ruleAssertionExpression )*
            loop108:
            do {
                int alt108=2;
                int LA108_0 = input.LA(1);

                if ( (LA108_0==66) ) {
                    alt108=1;
                }


                switch (alt108) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5040:6: lv_asserts_2= ruleAssertionExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getPreConditionAssertAccess().getAssertsAssertionExpressionParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAssertionExpression_in_rulePreConditionAssert11714);
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
            	    break loop108;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_rulePreConditionAssert11728); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5069:1: entryRulePostConditionAssert returns [EObject current=null] : iv_rulePostConditionAssert= rulePostConditionAssert EOF ;
    public final EObject entryRulePostConditionAssert() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePostConditionAssert = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5069:61: (iv_rulePostConditionAssert= rulePostConditionAssert EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5070:2: iv_rulePostConditionAssert= rulePostConditionAssert EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPostConditionAssertRule(), currentNode); 
            pushFollow(FOLLOW_rulePostConditionAssert_in_entryRulePostConditionAssert11761);
            iv_rulePostConditionAssert=rulePostConditionAssert();
            _fsp--;

             current =iv_rulePostConditionAssert; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePostConditionAssert11771); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5077:1: rulePostConditionAssert returns [EObject current=null] : ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) ;
    public final EObject rulePostConditionAssert() throws RecognitionException {
        EObject current = null;

        Token lv_scope_0=null;
        EObject lv_asserts_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5082:6: ( ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5083:1: ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5083:1: ( (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5083:2: (lv_scope_0= 'postcondition' ) '{' (lv_asserts_2= ruleAssertionExpression )* '}'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5083:2: (lv_scope_0= 'postcondition' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5085:6: lv_scope_0= 'postcondition'
            {
            lv_scope_0=(Token)input.LT(1);
            match(input,65,FOLLOW_65_in_rulePostConditionAssert11817); 

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

            match(input,38,FOLLOW_38_in_rulePostConditionAssert11839); 

                    createLeafNode(grammarAccess.getPostConditionAssertAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5108:1: (lv_asserts_2= ruleAssertionExpression )*
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( (LA109_0==66) ) {
                    alt109=1;
                }


                switch (alt109) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5111:6: lv_asserts_2= ruleAssertionExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getPostConditionAssertAccess().getAssertsAssertionExpressionParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAssertionExpression_in_rulePostConditionAssert11873);
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
            	    break loop109;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_rulePostConditionAssert11887); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5140:1: entryRuleAssertionExpression returns [EObject current=null] : iv_ruleAssertionExpression= ruleAssertionExpression EOF ;
    public final EObject entryRuleAssertionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssertionExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5140:61: (iv_ruleAssertionExpression= ruleAssertionExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5141:2: iv_ruleAssertionExpression= ruleAssertionExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAssertionExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAssertionExpression_in_entryRuleAssertionExpression11920);
            iv_ruleAssertionExpression=ruleAssertionExpression();
            _fsp--;

             current =iv_ruleAssertionExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAssertionExpression11930); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5148:1: ruleAssertionExpression returns [EObject current=null] : ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' ) ;
    public final EObject ruleAssertionExpression() throws RecognitionException {
        EObject current = null;

        Token lv_message_3=null;
        EObject lv_expr_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5153:6: ( ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5154:1: ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5154:1: ( 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5154:2: 'assert' (lv_expr_1= ruleExpression ) ( ',' (lv_message_3= RULE_STRING ) )? ';'
            {
            match(input,66,FOLLOW_66_in_ruleAssertionExpression11964); 

                    createLeafNode(grammarAccess.getAssertionExpressionAccess().getAssertKeyword_0(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5158:1: (lv_expr_1= ruleExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5161:6: lv_expr_1= ruleExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAssertionExpressionAccess().getExprExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleAssertionExpression11998);
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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5179:2: ( ',' (lv_message_3= RULE_STRING ) )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==29) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5179:3: ',' (lv_message_3= RULE_STRING )
                    {
                    match(input,29,FOLLOW_29_in_ruleAssertionExpression12012); 

                            createLeafNode(grammarAccess.getAssertionExpressionAccess().getCommaKeyword_2_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5183:1: (lv_message_3= RULE_STRING )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5185:6: lv_message_3= RULE_STRING
                    {
                    lv_message_3=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleAssertionExpression12034); 

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

            match(input,34,FOLLOW_34_in_ruleAssertionExpression12053); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5216:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5216:52: (iv_ruleExpression= ruleExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5217:2: iv_ruleExpression= ruleExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression12088);
            iv_ruleExpression=ruleExpression();
            _fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression12098); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5224:1: ruleExpression returns [EObject current=null] : this_ValueExpression_0= ruleValueExpression ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ValueExpression_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5229:6: (this_ValueExpression_0= ruleValueExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5231:5: this_ValueExpression_0= ruleValueExpression
            {
             
                    currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getValueExpressionParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleValueExpression_in_ruleExpression12144);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5246:1: entryRuleValueExpression returns [EObject current=null] : iv_ruleValueExpression= ruleValueExpression EOF ;
    public final EObject entryRuleValueExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5246:57: (iv_ruleValueExpression= ruleValueExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5247:2: iv_ruleValueExpression= ruleValueExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getValueExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleValueExpression_in_entryRuleValueExpression12175);
            iv_ruleValueExpression=ruleValueExpression();
            _fsp--;

             current =iv_ruleValueExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleValueExpression12185); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5254:1: ruleValueExpression returns [EObject current=null] : (lv_value_0= ruleOrExpression ) ;
    public final EObject ruleValueExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_value_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5259:6: ( (lv_value_0= ruleOrExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5260:1: (lv_value_0= ruleOrExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5260:1: (lv_value_0= ruleOrExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5263:6: lv_value_0= ruleOrExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getValueExpressionAccess().getValueOrExpressionParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleOrExpression_in_ruleValueExpression12243);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5288:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5288:54: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5289:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getOrExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression12279);
            iv_ruleOrExpression=ruleOrExpression();
            _fsp--;

             current =iv_ruleOrExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression12289); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5296:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5301:6: ( (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5302:1: (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5302:1: (this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5303:5: this_AndExpression_0= ruleAndExpression ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression12336);
            this_AndExpression_0=ruleAndExpression();
            _fsp--;

             
                    current = this_AndExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5311:1: ( () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression ) )*
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==67) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5311:2: () (lv_operator_2= '||' ) (lv_right_3= ruleAndExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5311:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5312:5: 
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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5327:2: (lv_operator_2= '||' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5329:6: lv_operator_2= '||'
            	    {
            	    lv_operator_2=(Token)input.LT(1);
            	    match(input,67,FOLLOW_67_in_ruleOrExpression12366); 

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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5348:2: (lv_right_3= ruleAndExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5351:6: lv_right_3= ruleAndExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression12413);
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
    // $ANTLR end ruleOrExpression


    // $ANTLR start entryRuleAndExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5376:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5376:55: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5377:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAndExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression12452);
            iv_ruleAndExpression=ruleAndExpression();
            _fsp--;

             current =iv_ruleAndExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression12462); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5384:1: ruleAndExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5389:6: ( (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5390:1: (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5390:1: (this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5391:5: this_RelationalExpression_0= ruleRelationalExpression ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAndExpressionAccess().getRelationalExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleAndExpression12509);
            this_RelationalExpression_0=ruleRelationalExpression();
            _fsp--;

             
                    current = this_RelationalExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5399:1: ( () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression ) )*
            loop112:
            do {
                int alt112=2;
                int LA112_0 = input.LA(1);

                if ( (LA112_0==68) ) {
                    alt112=1;
                }


                switch (alt112) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5399:2: () (lv_operator_2= '&&' ) (lv_right_3= ruleRelationalExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5399:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5400:5: 
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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5415:2: (lv_operator_2= '&&' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5417:6: lv_operator_2= '&&'
            	    {
            	    lv_operator_2=(Token)input.LT(1);
            	    match(input,68,FOLLOW_68_in_ruleAndExpression12539); 

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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5436:2: (lv_right_3= ruleRelationalExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5439:6: lv_right_3= ruleRelationalExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAndExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleAndExpression12586);
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
            	    break loop112;
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5464:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5464:62: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5465:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRelationalExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression12625);
            iv_ruleRelationalExpression=ruleRelationalExpression();
            _fsp--;

             current =iv_ruleRelationalExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression12635); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5472:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* ) ;
    public final EObject ruleRelationalExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2=null;
        EObject this_AdditiveExpression_0 = null;

        EObject lv_right_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5477:6: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5478:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5478:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5479:5: this_AdditiveExpression_0= ruleAdditiveExpression ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12682);
            this_AdditiveExpression_0=ruleAdditiveExpression();
            _fsp--;

             
                    current = this_AdditiveExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5487:1: ( () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression ) )*
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( ((LA114_0>=23 && LA114_0<=24)||(LA114_0>=69 && LA114_0<=73)) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5487:2: () (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) ) (lv_right_3= ruleAdditiveExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5487:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5488:5: 
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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5503:2: (lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5505:6: lv_operator_2= ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5505:20: ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' )
            	    int alt113=7;
            	    switch ( input.LA(1) ) {
            	    case 69:
            	        {
            	        alt113=1;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt113=2;
            	        }
            	        break;
            	    case 71:
            	        {
            	        alt113=3;
            	        }
            	        break;
            	    case 72:
            	        {
            	        alt113=4;
            	        }
            	        break;
            	    case 73:
            	        {
            	        alt113=5;
            	        }
            	        break;
            	    case 24:
            	        {
            	        alt113=6;
            	        }
            	        break;
            	    case 23:
            	        {
            	        alt113=7;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("5505:20: ( '~=' | '==' | '!=' | '>=' | '<=' | '>' | '<' )", 113, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt113) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5505:21: '~='
            	            {
            	            match(input,69,FOLLOW_69_in_ruleRelationalExpression12713); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorTildeEqualsSignKeyword_1_1_0_0(), "operator"); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5511:6: '=='
            	            {
            	            match(input,70,FOLLOW_70_in_ruleRelationalExpression12729); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_1(), "operator"); 
            	                

            	            }
            	            break;
            	        case 3 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5517:6: '!='
            	            {
            	            match(input,71,FOLLOW_71_in_ruleRelationalExpression12745); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_2(), "operator"); 
            	                

            	            }
            	            break;
            	        case 4 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5523:6: '>='
            	            {
            	            match(input,72,FOLLOW_72_in_ruleRelationalExpression12761); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_3(), "operator"); 
            	                

            	            }
            	            break;
            	        case 5 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5529:6: '<='
            	            {
            	            match(input,73,FOLLOW_73_in_ruleRelationalExpression12777); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_4(), "operator"); 
            	                

            	            }
            	            break;
            	        case 6 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5535:6: '>'
            	            {
            	            match(input,24,FOLLOW_24_in_ruleRelationalExpression12793); 

            	                    createLeafNode(grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignKeyword_1_1_0_5(), "operator"); 
            	                

            	            }
            	            break;
            	        case 7 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5541:6: '<'
            	            {
            	            match(input,23,FOLLOW_23_in_ruleRelationalExpression12809); 

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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5561:2: (lv_right_3= ruleAdditiveExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5564:6: lv_right_3= ruleAdditiveExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12858);
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
            	    break loop114;
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5589:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5589:60: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5590:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdditiveExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression12897);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();
            _fsp--;

             current =iv_ruleAdditiveExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression12907); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5597:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5602:6: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5603:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5603:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5604:5: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression12954);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();
            _fsp--;

             
                    current = this_MultiplicativeExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5612:1: ( () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression ) )*
            loop116:
            do {
                int alt116=2;
                int LA116_0 = input.LA(1);

                if ( (LA116_0==14||LA116_0==20) ) {
                    alt116=1;
                }


                switch (alt116) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5612:2: () (lv_name_2= ( '+' | '-' ) ) (lv_params_3= ruleMultiplicativeExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5612:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5613:5: 
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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5628:2: (lv_name_2= ( '+' | '-' ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5630:6: lv_name_2= ( '+' | '-' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5630:16: ( '+' | '-' )
            	    int alt115=2;
            	    int LA115_0 = input.LA(1);

            	    if ( (LA115_0==20) ) {
            	        alt115=1;
            	    }
            	    else if ( (LA115_0==14) ) {
            	        alt115=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("5630:16: ( '+' | '-' )", 115, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt115) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5630:17: '+'
            	            {
            	            match(input,20,FOLLOW_20_in_ruleAdditiveExpression12985); 

            	                    createLeafNode(grammarAccess.getAdditiveExpressionAccess().getNamePlusSignKeyword_1_1_0_0(), "name"); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5636:6: '-'
            	            {
            	            match(input,14,FOLLOW_14_in_ruleAdditiveExpression13001); 

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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5656:2: (lv_params_3= ruleMultiplicativeExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5659:6: lv_params_3= ruleMultiplicativeExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression13050);
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
            	    break loop116;
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5684:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5684:66: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5685:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMultiplicativeExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression13089);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();
            _fsp--;

             current =iv_ruleMultiplicativeExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression13099); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5692:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5697:6: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5698:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5698:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5699:5: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression13146);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();
            _fsp--;

             
                    current = this_UnaryOrInfixExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5707:1: ( () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression ) )*
            loop118:
            do {
                int alt118=2;
                int LA118_0 = input.LA(1);

                if ( (LA118_0==13||LA118_0==22) ) {
                    alt118=1;
                }


                switch (alt118) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5707:2: () (lv_name_2= ( '*' | '/' ) ) (lv_params_3= ruleUnaryOrInfixExpression )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5707:2: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5708:5: 
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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5723:2: (lv_name_2= ( '*' | '/' ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5725:6: lv_name_2= ( '*' | '/' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5725:16: ( '*' | '/' )
            	    int alt117=2;
            	    int LA117_0 = input.LA(1);

            	    if ( (LA117_0==22) ) {
            	        alt117=1;
            	    }
            	    else if ( (LA117_0==13) ) {
            	        alt117=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("5725:16: ( '*' | '/' )", 117, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt117) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5725:17: '*'
            	            {
            	            match(input,22,FOLLOW_22_in_ruleMultiplicativeExpression13177); 

            	                    createLeafNode(grammarAccess.getMultiplicativeExpressionAccess().getNameAsteriskKeyword_1_1_0_0(), "name"); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5731:6: '/'
            	            {
            	            match(input,13,FOLLOW_13_in_ruleMultiplicativeExpression13193); 

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

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5751:2: (lv_params_3= ruleUnaryOrInfixExpression )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5754:6: lv_params_3= ruleUnaryOrInfixExpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression13242);
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
    // $ANTLR end ruleMultiplicativeExpression


    // $ANTLR start entryRuleUnaryOrInfixExpression
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5779:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5779:64: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5780:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression13281);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();
            _fsp--;

             current =iv_ruleUnaryOrInfixExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression13291); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5787:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5792:6: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5793:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5793:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( ((LA119_0>=14 && LA119_0<=15)) ) {
                alt119=1;
            }
            else if ( ((LA119_0>=RULE_ID && LA119_0<=RULE_INT)||LA119_0==28||(LA119_0>=74 && LA119_0<=76)) ) {
                alt119=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5793:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )", 119, 0, input);

                throw nvae;
            }
            switch (alt119) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5794:5: this_UnaryExpression_0= ruleUnaryExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression13338);
                    this_UnaryExpression_0=ruleUnaryExpression();
                    _fsp--;

                     
                            current = this_UnaryExpression_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5804:5: this_InfixExpression_1= ruleInfixExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression13365);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5819:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5819:57: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5820:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnaryExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression13397);
            iv_ruleUnaryExpression=ruleUnaryExpression();
            _fsp--;

             current =iv_ruleUnaryExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression13407); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5827:1: ruleUnaryExpression returns [EObject current=null] : ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0=null;
        EObject lv_params_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5832:6: ( ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5833:1: ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5833:1: ( (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5833:2: (lv_name_0= ( '!' | '-' ) ) (lv_params_1= ruleInfixExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5833:2: (lv_name_0= ( '!' | '-' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5835:6: lv_name_0= ( '!' | '-' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5835:16: ( '!' | '-' )
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==15) ) {
                alt120=1;
            }
            else if ( (LA120_0==14) ) {
                alt120=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5835:16: ( '!' | '-' )", 120, 0, input);

                throw nvae;
            }
            switch (alt120) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5835:17: '!'
                    {
                    match(input,15,FOLLOW_15_in_ruleUnaryExpression13454); 

                            createLeafNode(grammarAccess.getUnaryExpressionAccess().getNameExclamationMarkKeyword_0_0_0(), "name"); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5841:6: '-'
                    {
                    match(input,14,FOLLOW_14_in_ruleUnaryExpression13470); 

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

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5861:2: (lv_params_1= ruleInfixExpression )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5864:6: lv_params_1= ruleInfixExpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression13519);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5889:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5889:57: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5890:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInfixExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression13556);
            iv_ruleInfixExpression=ruleInfixExpression();
            _fsp--;

             current =iv_ruleInfixExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression13566); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5897:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' ) | ( () ':' (lv_type_15= RULE_ID ) ) )* ) ;
    public final EObject ruleInfixExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_3=null;
        Token lv_type_15=null;
        EObject this_PrimaryExpression_0 = null;

        EObject lv_params_5 = null;

        EObject lv_params_7 = null;

        EObject lv_paramsParameters_9 = null;

        EObject lv_paramsParameters_11 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5902:6: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' ) | ( () ':' (lv_type_15= RULE_ID ) ) )* ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5903:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' ) | ( () ':' (lv_type_15= RULE_ID ) ) )* )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5903:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' ) | ( () ':' (lv_type_15= RULE_ID ) ) )* )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5904:5: this_PrimaryExpression_0= rulePrimaryExpression ( ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' ) | ( () ':' (lv_type_15= RULE_ID ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression13613);
            this_PrimaryExpression_0=rulePrimaryExpression();
            _fsp--;

             
                    current = this_PrimaryExpression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5912:1: ( ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' ) | ( () ':' (lv_type_15= RULE_ID ) ) )*
            loop125:
            do {
                int alt125=3;
                int LA125_0 = input.LA(1);

                if ( (LA125_0==25) ) {
                    int LA125_2 = input.LA(2);

                    if ( (LA125_2==RULE_ID) ) {
                        int LA125_3 = input.LA(3);

                        if ( (LA125_3==28) ) {
                            alt125=1;
                        }
                        else if ( (LA125_3==EOF||(LA125_3>=13 && LA125_3<=14)||LA125_3==20||(LA125_3>=22 && LA125_3<=25)||LA125_3==27||(LA125_3>=29 && LA125_3<=31)||LA125_3==34||LA125_3==57||LA125_3==60||(LA125_3>=67 && LA125_3<=73)) ) {
                            alt125=2;
                        }


                    }


                }


                switch (alt125) {
            	case 1 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5912:2: ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5912:2: ( () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')' )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5912:3: () ':' (lv_name_3= RULE_ID ) '(' ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )? ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )? ')'
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5912:3: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5913:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0().getType().getClassifier());
            	            try {
            	            	factory.set(temp, "target", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    match(input,25,FOLLOW_25_in_ruleInfixExpression13632); 

            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getColonKeyword_1_0_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5932:1: (lv_name_3= RULE_ID )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5934:6: lv_name_3= RULE_ID
            	    {
            	    lv_name_3=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInfixExpression13654); 

            	    		createLeafNode(grammarAccess.getInfixExpressionAccess().getNameIDTerminalRuleCall_1_0_2_0(), "name"); 
            	    	

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

            	    match(input,28,FOLLOW_28_in_ruleInfixExpression13671); 

            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5956:1: ( (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )* )?
            	    int alt122=2;
            	    int LA122_0 = input.LA(1);

            	    if ( (LA122_0==RULE_ID) ) {
            	        alt122=1;
            	    }
            	    switch (alt122) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5956:2: (lv_params_5= ruleParameter ) ( ',' (lv_params_7= ruleParameter ) )*
            	            {
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5956:2: (lv_params_5= ruleParameter )
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5959:6: lv_params_5= ruleParameter
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsParameterParserRuleCall_1_0_4_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleParameter_in_ruleInfixExpression13706);
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

            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5977:2: ( ',' (lv_params_7= ruleParameter ) )*
            	            loop121:
            	            do {
            	                int alt121=2;
            	                int LA121_0 = input.LA(1);

            	                if ( (LA121_0==29) ) {
            	                    alt121=1;
            	                }


            	                switch (alt121) {
            	            	case 1 :
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5977:3: ',' (lv_params_7= ruleParameter )
            	            	    {
            	            	    match(input,29,FOLLOW_29_in_ruleInfixExpression13720); 

            	            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0(), null); 
            	            	        
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5981:1: (lv_params_7= ruleParameter )
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:5984:6: lv_params_7= ruleParameter
            	            	    {
            	            	     
            	            	    	        currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsParameterParserRuleCall_1_0_4_1_1_0(), currentNode); 
            	            	    	    
            	            	    pushFollow(FOLLOW_ruleParameter_in_ruleInfixExpression13754);
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
            	            	    break loop121;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6002:6: ( '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )* )?
            	    int alt124=2;
            	    int LA124_0 = input.LA(1);

            	    if ( (LA124_0==60) ) {
            	        alt124=1;
            	    }
            	    switch (alt124) {
            	        case 1 :
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6002:7: '...' (lv_paramsParameters_9= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )*
            	            {
            	            match(input,60,FOLLOW_60_in_ruleInfixExpression13772); 

            	                    createLeafNode(grammarAccess.getInfixExpressionAccess().getFullStopFullStopFullStopKeyword_1_0_5_0(), null); 
            	                
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6006:1: (lv_paramsParameters_9= ruleAnonymousParameter )
            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6009:6: lv_paramsParameters_9= ruleAnonymousParameter
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsParametersAnonymousParameterParserRuleCall_1_0_5_1_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleAnonymousParameter_in_ruleInfixExpression13806);
            	            lv_paramsParameters_9=ruleAnonymousParameter();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getInfixExpressionRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        
            	            	        try {
            	            	       		add(current, "paramsParameters", lv_paramsParameters_9, "AnonymousParameter", currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }

            	            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6027:2: ( ',' (lv_paramsParameters_11= ruleAnonymousParameter ) )*
            	            loop123:
            	            do {
            	                int alt123=2;
            	                int LA123_0 = input.LA(1);

            	                if ( (LA123_0==29) ) {
            	                    alt123=1;
            	                }


            	                switch (alt123) {
            	            	case 1 :
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6027:3: ',' (lv_paramsParameters_11= ruleAnonymousParameter )
            	            	    {
            	            	    match(input,29,FOLLOW_29_in_ruleInfixExpression13820); 

            	            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_5_2_0(), null); 
            	            	        
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6031:1: (lv_paramsParameters_11= ruleAnonymousParameter )
            	            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6034:6: lv_paramsParameters_11= ruleAnonymousParameter
            	            	    {
            	            	     
            	            	    	        currentNode=createCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsParametersAnonymousParameterParserRuleCall_1_0_5_2_1_0(), currentNode); 
            	            	    	    
            	            	    pushFollow(FOLLOW_ruleAnonymousParameter_in_ruleInfixExpression13854);
            	            	    lv_paramsParameters_11=ruleAnonymousParameter();
            	            	    _fsp--;


            	            	    	        if (current==null) {
            	            	    	            current = factory.create(grammarAccess.getInfixExpressionRule().getType().getClassifier());
            	            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	    	        }
            	            	    	        
            	            	    	        try {
            	            	    	       		add(current, "paramsParameters", lv_paramsParameters_11, "AnonymousParameter", currentNode);
            	            	    	        } catch (ValueConverterException vce) {
            	            	    				handleValueConverterException(vce);
            	            	    	        }
            	            	    	        currentNode = currentNode.getParent();
            	            	    	    

            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop123;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    match(input,31,FOLLOW_31_in_ruleInfixExpression13871); 

            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_6(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6057:6: ( () ':' (lv_type_15= RULE_ID ) )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6057:6: ( () ':' (lv_type_15= RULE_ID ) )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6057:7: () ':' (lv_type_15= RULE_ID )
            	    {
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6057:7: ()
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6058:5: 
            	    {
            	     
            	            temp=factory.create(grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0().getType().getClassifier());
            	            try {
            	            	factory.set(temp, "target", current, null /*ParserRule*/, currentNode);
            	            } catch(ValueConverterException vce) {
            	            	handleValueConverterException(vce);
            	            }
            	            current = temp; 
            	            temp = null;
            	            CompositeNode newNode = createCompositeNode(grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0(), currentNode.getParent());
            	        newNode.getChildren().add(currentNode);
            	        moveLookaheadInfo(currentNode, newNode);
            	        currentNode = newNode; 
            	            associateNodeWithAstElement(currentNode, current); 
            	        

            	    }

            	    match(input,25,FOLLOW_25_in_ruleInfixExpression13897); 

            	            createLeafNode(grammarAccess.getInfixExpressionAccess().getColonKeyword_1_1_1(), null); 
            	        
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6077:1: (lv_type_15= RULE_ID )
            	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6079:6: lv_type_15= RULE_ID
            	    {
            	    lv_type_15=(Token)input.LT(1);
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInfixExpression13919); 

            	    		createLeafNode(grammarAccess.getInfixExpressionAccess().getTypeIDTerminalRuleCall_1_1_2_0(), "type"); 
            	    	

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getInfixExpressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		set(current, "type", lv_type_15, "ID", lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop125;
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6104:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6104:59: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6105:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrimaryExpressionRule(), currentNode); 
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression13963);
            iv_rulePrimaryExpression=rulePrimaryExpression();
            _fsp--;

             current =iv_rulePrimaryExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression13973); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6112:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_GlobalVarExpression_2= ruleGlobalVarExpression | this_ParanthesizedExpression_3= ruleParanthesizedExpression ) ;
    public final EObject rulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Literal_0 = null;

        EObject this_FeatureCall_1 = null;

        EObject this_GlobalVarExpression_2 = null;

        EObject this_ParanthesizedExpression_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6117:6: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_GlobalVarExpression_2= ruleGlobalVarExpression | this_ParanthesizedExpression_3= ruleParanthesizedExpression ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6118:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_GlobalVarExpression_2= ruleGlobalVarExpression | this_ParanthesizedExpression_3= ruleParanthesizedExpression )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6118:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_GlobalVarExpression_2= ruleGlobalVarExpression | this_ParanthesizedExpression_3= ruleParanthesizedExpression )
            int alt126=4;
            alt126 = dfa126.predict(input);
            switch (alt126) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6119:5: this_Literal_0= ruleLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression14020);
                    this_Literal_0=ruleLiteral();
                    _fsp--;

                     
                            current = this_Literal_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6129:5: this_FeatureCall_1= ruleFeatureCall
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleFeatureCall_in_rulePrimaryExpression14047);
                    this_FeatureCall_1=ruleFeatureCall();
                    _fsp--;

                     
                            current = this_FeatureCall_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6139:5: this_GlobalVarExpression_2= ruleGlobalVarExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression14074);
                    this_GlobalVarExpression_2=ruleGlobalVarExpression();
                    _fsp--;

                     
                            current = this_GlobalVarExpression_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6149:5: this_ParanthesizedExpression_3= ruleParanthesizedExpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_3(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression14101);
                    this_ParanthesizedExpression_3=ruleParanthesizedExpression();
                    _fsp--;

                     
                            current = this_ParanthesizedExpression_3; 
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


    // $ANTLR start entryRuleFeatureCall
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6164:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6164:53: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6165:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFeatureCallRule(), currentNode); 
            pushFollow(FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall14133);
            iv_ruleFeatureCall=ruleFeatureCall();
            _fsp--;

             current =iv_ruleFeatureCall; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureCall14143); 

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
    // $ANTLR end entryRuleFeatureCall


    // $ANTLR start ruleFeatureCall
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6172:1: ruleFeatureCall returns [EObject current=null] : this_OperationCall_0= ruleOperationCall ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6177:6: (this_OperationCall_0= ruleOperationCall )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6179:5: this_OperationCall_0= ruleOperationCall
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleOperationCall_in_ruleFeatureCall14189);
            this_OperationCall_0=ruleOperationCall();
            _fsp--;

             
                    current = this_OperationCall_0; 
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
    // $ANTLR end ruleFeatureCall


    // $ANTLR start entryRuleOperationCall
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6194:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6194:55: (iv_ruleOperationCall= ruleOperationCall EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6195:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
             currentNode = createCompositeNode(grammarAccess.getOperationCallRule(), currentNode); 
            pushFollow(FOLLOW_ruleOperationCall_in_entryRuleOperationCall14220);
            iv_ruleOperationCall=ruleOperationCall();
            _fsp--;

             current =iv_ruleOperationCall; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCall14230); 

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
    // $ANTLR end entryRuleOperationCall


    // $ANTLR start ruleOperationCall
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6202:1: ruleOperationCall returns [EObject current=null] : ( (lv_name_0= ruleQualifiedName ) '(' ( (lv_params_2= ruleParameter ) ( ',' (lv_params_4= ruleParameter ) )* )? ( '...' (lv_paramsParameters_6= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )* )? ')' ) ;
    public final EObject ruleOperationCall() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;

        EObject lv_params_2 = null;

        EObject lv_params_4 = null;

        EObject lv_paramsParameters_6 = null;

        EObject lv_paramsParameters_8 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6207:6: ( ( (lv_name_0= ruleQualifiedName ) '(' ( (lv_params_2= ruleParameter ) ( ',' (lv_params_4= ruleParameter ) )* )? ( '...' (lv_paramsParameters_6= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )* )? ')' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6208:1: ( (lv_name_0= ruleQualifiedName ) '(' ( (lv_params_2= ruleParameter ) ( ',' (lv_params_4= ruleParameter ) )* )? ( '...' (lv_paramsParameters_6= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )* )? ')' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6208:1: ( (lv_name_0= ruleQualifiedName ) '(' ( (lv_params_2= ruleParameter ) ( ',' (lv_params_4= ruleParameter ) )* )? ( '...' (lv_paramsParameters_6= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )* )? ')' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6208:2: (lv_name_0= ruleQualifiedName ) '(' ( (lv_params_2= ruleParameter ) ( ',' (lv_params_4= ruleParameter ) )* )? ( '...' (lv_paramsParameters_6= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )* )? ')'
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6208:2: (lv_name_0= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6211:6: lv_name_0= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getOperationCallAccess().getNameQualifiedNameParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleOperationCall14289);
            lv_name_0=ruleQualifiedName();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getOperationCallRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_0, "QualifiedName", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,28,FOLLOW_28_in_ruleOperationCall14302); 

                    createLeafNode(grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1(), null); 
                
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6233:1: ( (lv_params_2= ruleParameter ) ( ',' (lv_params_4= ruleParameter ) )* )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==RULE_ID) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6233:2: (lv_params_2= ruleParameter ) ( ',' (lv_params_4= ruleParameter ) )*
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6233:2: (lv_params_2= ruleParameter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6236:6: lv_params_2= ruleParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOperationCallAccess().getParamsParameterParserRuleCall_2_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleParameter_in_ruleOperationCall14337);
                    lv_params_2=ruleParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOperationCallRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "params", lv_params_2, "Parameter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6254:2: ( ',' (lv_params_4= ruleParameter ) )*
                    loop127:
                    do {
                        int alt127=2;
                        int LA127_0 = input.LA(1);

                        if ( (LA127_0==29) ) {
                            alt127=1;
                        }


                        switch (alt127) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6254:3: ',' (lv_params_4= ruleParameter )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleOperationCall14351); 

                    	            createLeafNode(grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6258:1: (lv_params_4= ruleParameter )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6261:6: lv_params_4= ruleParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getOperationCallAccess().getParamsParameterParserRuleCall_2_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleParameter_in_ruleOperationCall14385);
                    	    lv_params_4=ruleParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getOperationCallRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "params", lv_params_4, "Parameter", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop127;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6279:6: ( '...' (lv_paramsParameters_6= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )* )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==60) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6279:7: '...' (lv_paramsParameters_6= ruleAnonymousParameter ) ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )*
                    {
                    match(input,60,FOLLOW_60_in_ruleOperationCall14403); 

                            createLeafNode(grammarAccess.getOperationCallAccess().getFullStopFullStopFullStopKeyword_3_0(), null); 
                        
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6283:1: (lv_paramsParameters_6= ruleAnonymousParameter )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6286:6: lv_paramsParameters_6= ruleAnonymousParameter
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOperationCallAccess().getParamsParametersAnonymousParameterParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAnonymousParameter_in_ruleOperationCall14437);
                    lv_paramsParameters_6=ruleAnonymousParameter();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOperationCallRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "paramsParameters", lv_paramsParameters_6, "AnonymousParameter", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6304:2: ( ',' (lv_paramsParameters_8= ruleAnonymousParameter ) )*
                    loop129:
                    do {
                        int alt129=2;
                        int LA129_0 = input.LA(1);

                        if ( (LA129_0==29) ) {
                            alt129=1;
                        }


                        switch (alt129) {
                    	case 1 :
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6304:3: ',' (lv_paramsParameters_8= ruleAnonymousParameter )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleOperationCall14451); 

                    	            createLeafNode(grammarAccess.getOperationCallAccess().getCommaKeyword_3_2_0(), null); 
                    	        
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6308:1: (lv_paramsParameters_8= ruleAnonymousParameter )
                    	    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6311:6: lv_paramsParameters_8= ruleAnonymousParameter
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getOperationCallAccess().getParamsParametersAnonymousParameterParserRuleCall_3_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleAnonymousParameter_in_ruleOperationCall14485);
                    	    lv_paramsParameters_8=ruleAnonymousParameter();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getOperationCallRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "paramsParameters", lv_paramsParameters_8, "AnonymousParameter", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop129;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,31,FOLLOW_31_in_ruleOperationCall14502); 

                    createLeafNode(grammarAccess.getOperationCallAccess().getRightParenthesisKeyword_4(), null); 
                

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
    // $ANTLR end ruleOperationCall


    // $ANTLR start entryRuleLiteral
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6340:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6340:49: (iv_ruleLiteral= ruleLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6341:2: iv_ruleLiteral= ruleLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral14535);
            iv_ruleLiteral=ruleLiteral();
            _fsp--;

             current =iv_ruleLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral14545); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6348:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_StringLiteral_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6353:6: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6354:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6354:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral )
            int alt131=4;
            switch ( input.LA(1) ) {
            case 74:
            case 75:
                {
                alt131=1;
                }
                break;
            case RULE_INT:
                {
                alt131=2;
                }
                break;
            case 76:
                {
                alt131=3;
                }
                break;
            case RULE_STRING:
                {
                alt131=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("6354:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_StringLiteral_3= ruleStringLiteral )", 131, 0, input);

                throw nvae;
            }

            switch (alt131) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6355:5: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral14592);
                    this_BooleanLiteral_0=ruleBooleanLiteral();
                    _fsp--;

                     
                            current = this_BooleanLiteral_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6365:5: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral14619);
                    this_IntegerLiteral_1=ruleIntegerLiteral();
                    _fsp--;

                     
                            current = this_IntegerLiteral_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6375:5: this_NullLiteral_2= ruleNullLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral14646);
                    this_NullLiteral_2=ruleNullLiteral();
                    _fsp--;

                     
                            current = this_NullLiteral_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6385:5: this_StringLiteral_3= ruleStringLiteral
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_3(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral14673);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6400:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6400:56: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6401:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBooleanLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral14705);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();
            _fsp--;

             current =iv_ruleBooleanLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral14715); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6408:1: ruleBooleanLiteral returns [EObject current=null] : (lv_val_0= ( 'true' | 'false' ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6413:6: ( (lv_val_0= ( 'true' | 'false' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6414:1: (lv_val_0= ( 'true' | 'false' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6414:1: (lv_val_0= ( 'true' | 'false' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6416:6: lv_val_0= ( 'true' | 'false' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6416:15: ( 'true' | 'false' )
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==74) ) {
                alt132=1;
            }
            else if ( (LA132_0==75) ) {
                alt132=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("6416:15: ( 'true' | 'false' )", 132, 0, input);

                throw nvae;
            }
            switch (alt132) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6416:16: 'true'
                    {
                    match(input,74,FOLLOW_74_in_ruleBooleanLiteral14761); 

                            createLeafNode(grammarAccess.getBooleanLiteralAccess().getValTrueKeyword_0_0(), "val"); 
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6422:6: 'false'
                    {
                    match(input,75,FOLLOW_75_in_ruleBooleanLiteral14777); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6449:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6449:56: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6450:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIntegerLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral14824);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();
            _fsp--;

             current =iv_ruleIntegerLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral14834); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6457:1: ruleIntegerLiteral returns [EObject current=null] : (lv_val_0= RULE_INT ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6462:6: ( (lv_val_0= RULE_INT ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6463:1: (lv_val_0= RULE_INT )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6463:1: (lv_val_0= RULE_INT )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6465:6: lv_val_0= RULE_INT
            {
            lv_val_0=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral14880); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6490:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6490:53: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6491:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNullLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral14920);
            iv_ruleNullLiteral=ruleNullLiteral();
            _fsp--;

             current =iv_ruleNullLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral14930); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6498:1: ruleNullLiteral returns [EObject current=null] : (lv_val_0= 'null' ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6503:6: ( (lv_val_0= 'null' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6504:1: (lv_val_0= 'null' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6504:1: (lv_val_0= 'null' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6506:6: lv_val_0= 'null'
            {
            lv_val_0=(Token)input.LT(1);
            match(input,76,FOLLOW_76_in_ruleNullLiteral14975); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6532:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6532:55: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6533:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStringLiteralRule(), currentNode); 
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral15020);
            iv_ruleStringLiteral=ruleStringLiteral();
            _fsp--;

             current =iv_ruleStringLiteral; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral15030); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6540:1: ruleStringLiteral returns [EObject current=null] : (lv_val_0= RULE_STRING ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6545:6: ( (lv_val_0= RULE_STRING ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6546:1: (lv_val_0= RULE_STRING )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6546:1: (lv_val_0= RULE_STRING )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6548:6: lv_val_0= RULE_STRING
            {
            lv_val_0=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral15076); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6573:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6573:65: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6574:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getParanthesizedExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression15116);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();
            _fsp--;

             current =iv_ruleParanthesizedExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression15126); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6581:1: ruleParanthesizedExpression returns [EObject current=null] : ( '(' this_Expression_1= ruleExpression ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Expression_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6586:6: ( ( '(' this_Expression_1= ruleExpression ')' ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6587:1: ( '(' this_Expression_1= ruleExpression ')' )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6587:1: ( '(' this_Expression_1= ruleExpression ')' )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6587:2: '(' this_Expression_1= ruleExpression ')'
            {
            match(input,28,FOLLOW_28_in_ruleParanthesizedExpression15160); 

                    createLeafNode(grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression15182);
            this_Expression_1=ruleExpression();
            _fsp--;

             
                    current = this_Expression_1; 
                    currentNode = currentNode.getParent();
                
            match(input,31,FOLLOW_31_in_ruleParanthesizedExpression15190); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6611:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6611:61: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6612:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGlobalVarExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression15223);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();
            _fsp--;

             current =iv_ruleGlobalVarExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression15233); 

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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6619:1: ruleGlobalVarExpression returns [EObject current=null] : (lv_name_0= ruleQualifiedName ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6624:6: ( (lv_name_0= ruleQualifiedName ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6625:1: (lv_name_0= ruleQualifiedName )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6625:1: (lv_name_0= ruleQualifiedName )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6628:6: lv_name_0= ruleQualifiedName
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameQualifiedNameParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleGlobalVarExpression15291);
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
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6653:1: ruleVisibility returns [Enumerator current=null] : ( ( 'public' ) | ( 'private' ) ) ;
    public final Enumerator ruleVisibility() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6657:6: ( ( ( 'public' ) | ( 'private' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6658:1: ( ( 'public' ) | ( 'private' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6658:1: ( ( 'public' ) | ( 'private' ) )
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==77) ) {
                alt133=1;
            }
            else if ( (LA133_0==78) ) {
                alt133=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("6658:1: ( ( 'public' ) | ( 'private' ) )", 133, 0, input);

                throw nvae;
            }
            switch (alt133) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6658:2: ( 'public' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6658:2: ( 'public' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6658:4: 'public'
                    {
                    match(input,77,FOLLOW_77_in_ruleVisibility15341); 

                            current = grammarAccess.getVisibilityAccess().getPublicEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getVisibilityAccess().getPublicEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6664:6: ( 'private' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6664:6: ( 'private' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6664:8: 'private'
                    {
                    match(input,78,FOLLOW_78_in_ruleVisibility15356); 

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


    // $ANTLR start ruleExecutionMode
    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6676:1: ruleExecutionMode returns [Enumerator current=null] : ( ( 'parallel' ) | ( 'sequential' ) ) ;
    public final Enumerator ruleExecutionMode() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6680:6: ( ( ( 'parallel' ) | ( 'sequential' ) ) )
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6681:1: ( ( 'parallel' ) | ( 'sequential' ) )
            {
            // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6681:1: ( ( 'parallel' ) | ( 'sequential' ) )
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==79) ) {
                alt134=1;
            }
            else if ( (LA134_0==45) ) {
                alt134=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("6681:1: ( ( 'parallel' ) | ( 'sequential' ) )", 134, 0, input);

                throw nvae;
            }
            switch (alt134) {
                case 1 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6681:2: ( 'parallel' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6681:2: ( 'parallel' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6681:4: 'parallel'
                    {
                    match(input,79,FOLLOW_79_in_ruleExecutionMode15401); 

                            current = grammarAccess.getExecutionModeAccess().getParallelEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getExecutionModeAccess().getParallelEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6687:6: ( 'sequential' )
                    {
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6687:6: ( 'sequential' )
                    // ../org.eclipse.b3.beelang/src-gen/org/eclipse/b3/parser/antlr/internal/InternalBeeLang.g:6687:8: 'sequential'
                    {
                    match(input,45,FOLLOW_45_in_ruleExecutionMode15416); 

                            current = grammarAccess.getExecutionModeAccess().getSequentialEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getExecutionModeAccess().getSequentialEnumLiteralDeclaration_1(), null); 
                        

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
    // $ANTLR end ruleExecutionMode


    protected DFA40 dfa40 = new DFA40(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA74 dfa74 = new DFA74(this);
    protected DFA126 dfa126 = new DFA126(this);
    static final String DFA40_eotS =
        "\10\uffff";
    static final String DFA40_eofS =
        "\1\uffff\2\4\3\uffff\2\4";
    static final String DFA40_minS =
        "\1\4\1\15\1\13\2\uffff\1\4\1\15\1\13";
    static final String DFA40_maxS =
        "\1\5\2\42\2\uffff\1\4\2\42";
    static final String DFA40_acceptS =
        "\3\uffff\1\1\1\2\3\uffff";
    static final String DFA40_specialS =
        "\10\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\2\1\1",
            "\1\3\17\uffff\1\4\4\uffff\1\4",
            "\1\5\1\6\1\3\17\uffff\1\4\4\uffff\1\4",
            "",
            "",
            "\1\7",
            "\1\3\17\uffff\1\4\4\uffff\1\4",
            "\1\5\1\6\1\3\17\uffff\1\4\4\uffff\1\4"
    };

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "2137:1: ( (this_CompoundName_0= ruleCompoundName (kw= '#' this_PartName_2= rulePartName )? ) | this_PartName_3= rulePartName )";
        }
    }
    static final String DFA67_eotS =
        "\7\uffff";
    static final String DFA67_eofS =
        "\7\uffff";
    static final String DFA67_minS =
        "\1\4\1\13\1\uffff\1\4\1\15\1\uffff\1\13";
    static final String DFA67_maxS =
        "\1\66\1\71\1\uffff\1\4\1\71\1\uffff\1\71";
    static final String DFA67_acceptS =
        "\2\uffff\1\2\2\uffff\1\1\1\uffff";
    static final String DFA67_specialS =
        "\7\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\1\1\2\40\uffff\1\2\17\uffff\1\2",
            "\1\3\1\4\1\2\7\uffff\1\5\14\uffff\1\2\26\uffff\1\2",
            "",
            "\1\6",
            "\1\2\7\uffff\1\5\14\uffff\1\2\26\uffff\1\2",
            "",
            "\1\3\1\4\1\2\7\uffff\1\5\14\uffff\1\2\26\uffff\1\2"
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
            return "3053:4: ( (lv_alias_3= ruleQualifiedName ) '=' )?";
        }
    }
    static final String DFA71_eotS =
        "\11\uffff";
    static final String DFA71_eofS =
        "\2\uffff\1\1\3\uffff\1\1\1\uffff\1\1";
    static final String DFA71_minS =
        "\1\4\1\uffff\1\13\2\uffff\1\4\1\15\1\uffff\1\13";
    static final String DFA71_maxS =
        "\1\66\1\uffff\1\71\2\uffff\1\4\1\71\1\uffff\1\71";
    static final String DFA71_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\2\uffff\1\2\1\uffff";
    static final String DFA71_specialS =
        "\11\uffff}>";
    static final String[] DFA71_transitionS = {
            "\1\2\1\1\40\uffff\1\3\17\uffff\1\4",
            "",
            "\1\5\1\6\1\7\24\uffff\1\1\26\uffff\1\1",
            "",
            "",
            "\1\10",
            "\1\7\24\uffff\1\1\26\uffff\1\1",
            "",
            "\1\5\1\6\1\7\24\uffff\1\1\26\uffff\1\1"
    };

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "3267:1: (this_PartInSelf_0= rulePartInSelf | this_CapabilityReferencedPart_1= ruleCapabilityReferencedPart | this_CompoundReferences_2= ruleCompoundReferences | this_ExprStatement_3= ruleExprStatement )";
        }
    }
    static final String DFA74_eotS =
        "\117\uffff";
    static final String DFA74_eofS =
        "\6\uffff\2\11\4\uffff\1\11\2\uffff\4\11\3\uffff\21\11\1\uffff\2"+
        "\11\45\uffff";
    static final String DFA74_minS =
        "\1\4\1\13\1\4\1\15\1\4\1\13\1\15\1\13\1\4\2\uffff\1\4\1\15\2\4\1"+
        "\42\2\4\1\13\1\35\24\4\2\42\21\4\1\36\23\4";
    static final String DFA74_maxS =
        "\1\4\1\15\1\4\1\15\1\5\1\15\2\71\1\34\2\uffff\1\4\1\71\2\6\4\71"+
        "\3\37\21\71\1\6\2\71\45\37";
    static final String DFA74_acceptS =
        "\11\uffff\1\2\1\1\104\uffff";
    static final String DFA74_specialS =
        "\117\uffff}>";
    static final String[] DFA74_transitionS = {
            "\1\1",
            "\1\2\1\3\1\4",
            "\1\5",
            "\1\4",
            "\1\7\1\6",
            "\1\2\1\3\1\4",
            "\1\10\24\uffff\1\11\20\uffff\1\12\5\uffff\1\11",
            "\1\13\1\14\1\10\24\uffff\1\11\20\uffff\1\12\5\uffff\1\11",
            "\1\20\1\17\1\21\24\uffff\1\15\1\16",
            "",
            "",
            "\1\22",
            "\1\10\24\uffff\1\11\20\uffff\1\12\5\uffff\1\11",
            "\1\24\1\23\1\25",
            "\1\24\1\23\1\25",
            "\1\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\13\1\14\1\10\24\uffff\1\11\20\uffff\1\12\5\uffff\1\11",
            "\1\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\45\1\uffff\1\46\4\uffff\1\26\1\uffff\1\35\1\27\1\30\1\31"+
            "\1\32\1\33\1\34\1\36\1\37\1\40\1\41\1\42\1\43\1\44\7\uffff\1"+
            "\11\20\uffff\1\12\5\uffff\1\11",
            "\1\74\1\73\1\75",
            "\1\11\20\uffff\1\12\5\uffff\1\11",
            "\1\11\20\uffff\1\12\5\uffff\1\11",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\71\1\uffff\1\72\4\uffff\1\52\1\uffff\1\61\1\53\1\54\1\55"+
            "\1\56\1\57\1\60\1\62\1\63\1\64\1\65\1\66\1\67\1\70\2\uffff\1"+
            "\47\1\50\1\51",
            "\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51",
            "\1\115\1\uffff\1\116\4\uffff\1\76\1\uffff\1\105\1\77\1\100\1"+
            "\101\1\102\1\103\1\104\1\106\1\107\1\110\1\111\1\112\1\113\1"+
            "\114\3\uffff\1\50\1\51"
    };

    static final short[] DFA74_eot = DFA.unpackEncodedString(DFA74_eotS);
    static final short[] DFA74_eof = DFA.unpackEncodedString(DFA74_eofS);
    static final char[] DFA74_min = DFA.unpackEncodedStringToUnsignedChars(DFA74_minS);
    static final char[] DFA74_max = DFA.unpackEncodedStringToUnsignedChars(DFA74_maxS);
    static final short[] DFA74_accept = DFA.unpackEncodedString(DFA74_acceptS);
    static final short[] DFA74_special = DFA.unpackEncodedString(DFA74_specialS);
    static final short[][] DFA74_transition;

    static {
        int numStates = DFA74_transitionS.length;
        DFA74_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA74_transition[i] = DFA.unpackEncodedString(DFA74_transitionS[i]);
        }
    }

    class DFA74 extends DFA {

        public DFA74(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 74;
            this.eot = DFA74_eot;
            this.eof = DFA74_eof;
            this.min = DFA74_min;
            this.max = DFA74_max;
            this.accept = DFA74_accept;
            this.special = DFA74_special;
            this.transition = DFA74_transition;
        }
        public String getDescription() {
            return "3369:1: ( ( (lv_interface_0= ruleInterfaceName ) '/' (lv_name_2= ruleUnitName ) ( '/' (lv_range_4= ruleVersionRange ) )? '#' (lv_partName_6= rulePartName ) ) | ( (lv_interface_7= ruleInterfaceName ) '/' (lv_name_9= ruleUnitName ) ( '/' (lv_range_11= ruleVersionRange ) )? ) )";
        }
    }
    static final String DFA126_eotS =
        "\11\uffff";
    static final String DFA126_eofS =
        "\2\uffff\1\7\2\uffff\1\7\2\uffff\1\7";
    static final String DFA126_minS =
        "\1\4\1\uffff\1\13\1\uffff\1\4\1\15\2\uffff\1\13";
    static final String DFA126_maxS =
        "\1\114\1\uffff\1\111\1\uffff\1\4\1\111\2\uffff\1\111";
    static final String DFA126_acceptS =
        "\1\uffff\1\1\1\uffff\1\4\2\uffff\1\2\1\3\1\uffff";
    static final String DFA126_specialS =
        "\11\uffff}>";
    static final String[] DFA126_transitionS = {
            "\1\2\2\1\25\uffff\1\3\55\uffff\3\1",
            "",
            "\1\4\1\5\2\7\5\uffff\1\7\1\uffff\4\7\1\uffff\1\7\1\6\3\7\2\uffff"+
            "\1\7\26\uffff\1\7\2\uffff\1\7\6\uffff\7\7",
            "",
            "\1\10",
            "\2\7\5\uffff\1\7\1\uffff\4\7\1\uffff\1\7\1\6\3\7\2\uffff\1\7"+
            "\26\uffff\1\7\2\uffff\1\7\6\uffff\7\7",
            "",
            "",
            "\1\4\1\5\2\7\5\uffff\1\7\1\uffff\4\7\1\uffff\1\7\1\6\3\7\2\uffff"+
            "\1\7\26\uffff\1\7\2\uffff\1\7\6\uffff\7\7"
    };

    static final short[] DFA126_eot = DFA.unpackEncodedString(DFA126_eotS);
    static final short[] DFA126_eof = DFA.unpackEncodedString(DFA126_eofS);
    static final char[] DFA126_min = DFA.unpackEncodedStringToUnsignedChars(DFA126_minS);
    static final char[] DFA126_max = DFA.unpackEncodedStringToUnsignedChars(DFA126_maxS);
    static final short[] DFA126_accept = DFA.unpackEncodedString(DFA126_acceptS);
    static final short[] DFA126_special = DFA.unpackEncodedString(DFA126_specialS);
    static final short[][] DFA126_transition;

    static {
        int numStates = DFA126_transitionS.length;
        DFA126_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA126_transition[i] = DFA.unpackEncodedString(DFA126_transitionS[i]);
        }
    }

    class DFA126 extends DFA {

        public DFA126(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 126;
            this.eot = DFA126_eot;
            this.eof = DFA126_eof;
            this.min = DFA126_min;
            this.max = DFA126_max;
            this.accept = DFA126_accept;
            this.special = DFA126_special;
            this.transition = DFA126_transition;
        }
        public String getDescription() {
            return "6118:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_GlobalVarExpression_2= ruleGlobalVarExpression | this_ParanthesizedExpression_3= ruleParanthesizedExpression )";
        }
    }
 

    public static final BitSet FOLLOW_ruleBeeModel_in_entryRuleBeeModel73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBeeModel83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleBeeModel142 = new BitSet(new long[]{0x0000200900000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleUnit_in_ruleBeeModel181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName219 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedName230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName270 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_11_in_ruleQualifiedName289 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName304 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_ruleHIDDENBUG_in_ruleQualifiedName333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleHIDDENBUG_in_entryRuleHIDDENBUG377 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleHIDDENBUG388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleHIDDENBUG426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_entryRuleEscapedQualifiedName466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEscapedQualifiedName477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleEscapedQualifiedName517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleEscapedQualifiedName550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_entryRuleInterfaceName594 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceName605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleInterfaceName651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundName_in_entryRuleCompoundName694 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCompoundName705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName752 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleCompoundName770 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_ruleCompoundName792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnitName_in_entryRuleUnitName836 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnitName847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_ruleUnitName893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartName_in_entryRulePartName936 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePartName947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEscapedQualifiedName_in_rulePartName993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSeparator_in_entryRuleSeparator1036 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSeparator1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleSeparator1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleSeparator1104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleSeparator1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleSeparator1142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleSeparator1161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleSeparator1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleSeparator1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleSeparator1218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleSeparator1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleSeparator1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleSeparator1275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleSeparator1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleSeparator1313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleSeparator1332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleSeparator1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlfanumSym_in_entryRuleAlfanumSym1390 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAlfanumSym1401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAlfanumSym1442 = new BitSet(new long[]{0x0000000007FFE852L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAlfanumSym1468 = new BitSet(new long[]{0x0000000007FFE852L});
    public static final BitSet FOLLOW_ruleSeparator_in_ruleAlfanumSym1497 = new BitSet(new long[]{0x0000000007FFE852L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAlfanumSym1523 = new BitSet(new long[]{0x0000000007FFE852L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAlfanumSym1549 = new BitSet(new long[]{0x0000000007FFE852L});
    public static final BitSet FOLLOW_ruleVersion_in_entryRuleVersion1595 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVersion1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleVersion1646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlfanumSym_in_ruleVersion1679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVersionRange_in_entryRuleVersionRange1723 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVersionRange1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleVersionRange1774 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_28_in_ruleVersionRange1793 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleVersionRange1816 = new BitSet(new long[]{0x00000000E0000000L});
    public static final BitSet FOLLOW_29_in_ruleVersionRange1835 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleVersionRange1857 = new BitSet(new long[]{0x00000000C0000000L});
    public static final BitSet FOLLOW_30_in_ruleVersionRange1878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleVersionRange1897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleVersionRange1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport1970 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport1980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleImport2014 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleImport2048 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33_in_ruleImport2062 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleImport2073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnit_in_entryRuleUnit2106 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnit2116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExecutionMode_in_ruleUnit2175 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleUnit2189 = new BitSet(new long[]{0x0000007000000030L});
    public static final BitSet FOLLOW_ruleUnitName_in_ruleUnit2223 = new BitSet(new long[]{0x0000007000000000L});
    public static final BitSet FOLLOW_36_in_ruleUnit2238 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleUnit2272 = new BitSet(new long[]{0x0000006000000000L});
    public static final BitSet FOLLOW_37_in_ruleUnit2288 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleUnit2322 = new BitSet(new long[]{0x0000004020000000L});
    public static final BitSet FOLLOW_29_in_ruleUnit2336 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleUnit2370 = new BitSet(new long[]{0x0000004020000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2387 = new BitSet(new long[]{0x04907F8000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_39_in_ruleUnit2397 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_ruleNamedPropertyStatements_in_ruleUnit2431 = new BitSet(new long[]{0x04907F8000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_40_in_ruleUnit2448 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2457 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleUnit2492 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleUnit2505 = new BitSet(new long[]{0x0000820000000010L});
    public static final BitSet FOLLOW_41_in_ruleUnit2516 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_40_in_ruleUnit2533 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleUnit2567 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleUnit2580 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_42_in_ruleUnit2597 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2606 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2641 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleUnit2654 = new BitSet(new long[]{0x0000820000000010L});
    public static final BitSet FOLLOW_41_in_ruleUnit2665 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_42_in_ruleUnit2682 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2716 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleUnit2729 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_43_in_ruleUnit2746 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleUnit2755 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2764 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2799 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleUnit2812 = new BitSet(new long[]{0x0000820000000010L});
    public static final BitSet FOLLOW_41_in_ruleUnit2823 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_43_in_ruleUnit2840 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleUnit2849 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_ruleUnit2883 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleUnit2896 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_44_in_ruleUnit2913 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_ruleNamedAdvice_in_ruleUnit2947 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_45_in_ruleUnit2968 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit2977 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleSynchronization_in_ruleUnit3011 = new BitSet(new long[]{0x0000020000000030L});
    public static final BitSet FOLLOW_41_in_ruleUnit3025 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_45_in_ruleUnit3042 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleSynchronization_in_ruleUnit3076 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_ruleBuildPart_in_ruleUnit3121 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_46_in_ruleUnit3141 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleUnit3150 = new BitSet(new long[]{0x4000020000000020L});
    public static final BitSet FOLLOW_ruleRepositoryConfiguration_in_ruleUnit3184 = new BitSet(new long[]{0x4000020000000020L});
    public static final BitSet FOLLOW_41_in_ruleUnit3198 = new BitSet(new long[]{0x04907F0000000000L,0x000000000000E000L});
    public static final BitSet FOLLOW_41_in_ruleUnit3210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_entryRuleProvidedCapability3243 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProvidedCapability3253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleProvidedCapability3288 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleFilter_in_ruleProvidedCapability3322 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3362 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleProvidedCapability3375 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleProvidedCapability3409 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_ruleProvidedCapability3423 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleVersion_in_ruleProvidedCapability3457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRequiredCapability_in_entryRuleRequiredCapability3496 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRequiredCapability3506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleRequiredCapability3541 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleFilter_in_ruleRequiredCapability3575 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3615 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleRequiredCapability3628 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleRequiredCapability3662 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_ruleRequiredCapability3676 = new BitSet(new long[]{0x0000000018000070L});
    public static final BitSet FOLLOW_ruleVersionRange_in_ruleRequiredCapability3710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamedPropertyStatements_in_entryRuleNamedPropertyStatements3749 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamedPropertyStatements3759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleNamedPropertyStatements3818 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rulePropertyStatements_in_ruleNamedPropertyStatements3857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyStatements_in_entryRulePropertyStatements3894 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyStatements3904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rulePropertyStatements3938 = new BitSet(new long[]{0x0007824000000010L});
    public static final BitSet FOLLOW_47_in_rulePropertyStatements3949 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleFilter_in_rulePropertyStatements3983 = new BitSet(new long[]{0x0007004000000010L});
    public static final BitSet FOLLOW_ruleUnsetPropertyStatement_in_rulePropertyStatements4024 = new BitSet(new long[]{0x0007824000000010L});
    public static final BitSet FOLLOW_rulePropertyStatement_in_rulePropertyStatements4068 = new BitSet(new long[]{0x0007824000000010L});
    public static final BitSet FOLLOW_rulePropertyStatements_in_rulePropertyStatements4112 = new BitSet(new long[]{0x0007824000000010L});
    public static final BitSet FOLLOW_41_in_rulePropertyStatements4128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyStatement_in_entryRulePropertyStatement4161 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyStatement4171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_rulePropertyStatement4217 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_49_in_rulePropertyStatement4252 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePropertyStatement4300 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_rulePropertyStatement4313 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_rulePropertyStatement4347 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_rulePropertyStatement4360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsetPropertyStatement_in_entryRuleUnsetPropertyStatement4393 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsetPropertyStatement4403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleUnsetPropertyStatement4437 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleUnsetPropertyStatement4471 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleUnsetPropertyStatement4484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnnotationStatement_in_entryRuleAnnotationStatement4517 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnnotationStatement4527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleAnnotationStatement4562 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleFilter_in_ruleAnnotationStatement4596 = new BitSet(new long[]{0x0002000000000010L});
    public static final BitSet FOLLOW_49_in_ruleAnnotationStatement4623 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAnnotationStatement4671 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleAnnotationStatement4684 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleAnnotationStatement4718 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleAnnotationStatement4731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression4764 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyExpression4774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePropertyExpression4820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSynchronization_in_entryRuleSynchronization4851 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSynchronization4861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4920 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleSynchronization4934 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleSynchronizedPart_in_ruleSynchronization4968 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_34_in_ruleSynchronization4983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSynchronizedPart_in_entryRuleSynchronizedPart5017 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSynchronizedPart5028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundName_in_ruleSynchronizedPart5076 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_ruleSynchronizedPart5095 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleSynchronizedPart5117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartName_in_ruleSynchronizedPart5153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuildPart_in_entryRuleBuildPart5196 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuildPart5206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArtifacts_in_ruleBuildPart5253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroup_in_ruleBuildPart5280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAction_in_ruleBuildPart5307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArtifacts_in_entryRuleArtifacts5339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArtifacts5349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVisibility_in_ruleArtifacts5408 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_ruleArtifacts5422 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleArtifacts5456 = new BitSet(new long[]{0x0000014000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleArtifacts5470 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleArtifacts5504 = new BitSet(new long[]{0x0000004020000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleArtifacts5518 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleArtifacts5552 = new BitSet(new long[]{0x0000004020000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleArtifacts5594 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleArtifacts5608 = new BitSet(new long[]{0x0060820000002030L});
    public static final BitSet FOLLOW_rulePathGroup_in_ruleArtifacts5642 = new BitSet(new long[]{0x0060820000002030L});
    public static final BitSet FOLLOW_41_in_ruleArtifacts5656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePathGroup_in_entryRulePathGroup5689 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePathGroup5699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_rulePathGroup5734 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleFilter_in_rulePathGroup5768 = new BitSet(new long[]{0x0060000000002030L});
    public static final BitSet FOLLOW_rulePathExpression_in_rulePathGroup5810 = new BitSet(new long[]{0x0000000428000000L});
    public static final BitSet FOLLOW_29_in_rulePathGroup5825 = new BitSet(new long[]{0x0040000000002030L});
    public static final BitSet FOLLOW_rulePathExpression_in_rulePathGroup5859 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_27_in_rulePathGroup5893 = new BitSet(new long[]{0x0040000000002030L});
    public static final BitSet FOLLOW_rulePathExpression_in_rulePathGroup5940 = new BitSet(new long[]{0x0000000060000000L});
    public static final BitSet FOLLOW_29_in_rulePathGroup5954 = new BitSet(new long[]{0x0040000000002030L});
    public static final BitSet FOLLOW_rulePathExpression_in_rulePathGroup5988 = new BitSet(new long[]{0x0000000060000000L});
    public static final BitSet FOLLOW_30_in_rulePathGroup6003 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_rulePathGroup6014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_rulePathGroup6031 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_rulePathGroup6040 = new BitSet(new long[]{0x0006800000000010L});
    public static final BitSet FOLLOW_50_in_rulePathGroup6051 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePathGroup6085 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_rulePathGroup6098 = new BitSet(new long[]{0x0006820000000010L});
    public static final BitSet FOLLOW_ruleAnnotationStatement_in_rulePathGroup6139 = new BitSet(new long[]{0x0006820000000010L});
    public static final BitSet FOLLOW_41_in_rulePathGroup6154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePathExpression_in_entryRulePathExpression6189 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePathExpression6199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePath_in_rulePathExpression6233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprStatement_in_rulePathExpression6253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprStatement_in_entryRuleExprStatement6285 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExprStatement6295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleExprStatement6329 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExprStatement6363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePath_in_entryRulePath6401 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePath6412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePath6452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rulePath6478 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePath6502 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_rulePath6521 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePath6543 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_rulePath6564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGroup_in_entryRuleGroup6605 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGroup6615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVisibility_in_ruleGroup6674 = new BitSet(new long[]{0x0080200000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleExecutionMode_in_ruleGroup6713 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleGroup6727 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleGroup6761 = new BitSet(new long[]{0x0000014000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_40_in_ruleGroup6775 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleGroup6809 = new BitSet(new long[]{0x0000004020000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_29_in_ruleGroup6823 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleGroup6857 = new BitSet(new long[]{0x0000004020000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_ruleGroup6899 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleGroup6938 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleGroup6952 = new BitSet(new long[]{0x0140824000000030L});
    public static final BitSet FOLLOW_rulePrerequisite_in_ruleGroup6986 = new BitSet(new long[]{0x0140824000000030L});
    public static final BitSet FOLLOW_41_in_ruleGroup7000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrerequisite_in_entryRulePrerequisite7033 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrerequisite7043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_rulePrerequisite7090 = new BitSet(new long[]{0x0040804000000030L});
    public static final BitSet FOLLOW_47_in_rulePrerequisite7114 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleFilter_in_rulePrerequisite7148 = new BitSet(new long[]{0x0040004000000030L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePrerequisite7189 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_rulePrerequisite7202 = new BitSet(new long[]{0x0040004000000030L});
    public static final BitSet FOLLOW_rulePrerequisiteEntry_in_rulePrerequisite7239 = new BitSet(new long[]{0x0200000400000000L});
    public static final BitSet FOLLOW_ruleClosure_in_rulePrerequisite7277 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_rulePrerequisite7293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClosure_in_entryRuleClosure7326 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClosure7336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleClosure7370 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleClosure7379 = new BitSet(new long[]{0x0000128000000000L});
    public static final BitSet FOLLOW_39_in_ruleClosure7390 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleClosure7399 = new BitSet(new long[]{0x0007000000000010L});
    public static final BitSet FOLLOW_50_in_ruleClosure7410 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleClosure7444 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleClosure7457 = new BitSet(new long[]{0x0007020000000010L});
    public static final BitSet FOLLOW_rulePropertyStatement_in_ruleClosure7498 = new BitSet(new long[]{0x0007020000000010L});
    public static final BitSet FOLLOW_41_in_ruleClosure7513 = new BitSet(new long[]{0x0000128000000000L});
    public static final BitSet FOLLOW_44_in_ruleClosure7530 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleClosure7564 = new BitSet(new long[]{0x0000128000000000L});
    public static final BitSet FOLLOW_41_in_ruleClosure7580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrerequisiteEntry_in_entryRulePrerequisiteEntry7613 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrerequisiteEntry7623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartInSelf_in_rulePrerequisiteEntry7670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCapabilityReferencedPart_in_rulePrerequisiteEntry7697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundReferences_in_rulePrerequisiteEntry7724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprStatement_in_rulePrerequisiteEntry7751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartInSelf_in_entryRulePartInSelf7783 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePartInSelf7793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartName_in_rulePartInSelf7851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCapabilityReferencedPart_in_entryRuleCapabilityReferencedPart7887 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCapabilityReferencedPart7897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart7957 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleCapabilityReferencedPart7970 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart8004 = new BitSet(new long[]{0x0008000000002000L});
    public static final BitSet FOLLOW_13_in_ruleCapabilityReferencedPart8018 = new BitSet(new long[]{0x0000000018000070L});
    public static final BitSet FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart8052 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleCapabilityReferencedPart8067 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleCapabilityReferencedPart8101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceName_in_ruleCapabilityReferencedPart8147 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleCapabilityReferencedPart8160 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleUnitName_in_ruleCapabilityReferencedPart8194 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_ruleCapabilityReferencedPart8208 = new BitSet(new long[]{0x0000000018000070L});
    public static final BitSet FOLLOW_ruleVersionRange_in_ruleCapabilityReferencedPart8242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundReferences_in_entryRuleCompoundReferences8282 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCompoundReferences8292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleCompoundReferences8326 = new BitSet(new long[]{0x0140824000000030L});
    public static final BitSet FOLLOW_rulePrerequisite_in_ruleCompoundReferences8360 = new BitSet(new long[]{0x0140824000000030L});
    public static final BitSet FOLLOW_41_in_ruleCompoundReferences8374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAction_in_entryRuleAction8407 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAction8417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVisibility_in_ruleAction8476 = new BitSet(new long[]{0x0400200000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleExecutionMode_in_ruleAction8515 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleAction8529 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulePartName_in_ruleAction8538 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleAction8539 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAction8548 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleAction8549 = new BitSet(new long[]{0x1000000080000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleAction8584 = new BitSet(new long[]{0x10000000A0000000L});
    public static final BitSet FOLLOW_29_in_ruleAction8598 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleAction8632 = new BitSet(new long[]{0x10000000A0000000L});
    public static final BitSet FOLLOW_60_in_ruleAction8650 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAnonymousParameter_in_ruleAction8684 = new BitSet(new long[]{0x00000000A0000000L});
    public static final BitSet FOLLOW_29_in_ruleAction8698 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAnonymousParameter_in_ruleAction8732 = new BitSet(new long[]{0x00000000A0000000L});
    public static final BitSet FOLLOW_31_in_ruleAction8749 = new BitSet(new long[]{0x0000014000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_40_in_ruleAction8759 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleAction8793 = new BitSet(new long[]{0x0000004020000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_29_in_ruleAction8807 = new BitSet(new long[]{0x0000800000000010L});
    public static final BitSet FOLLOW_ruleProvidedCapability_in_ruleAction8841 = new BitSet(new long[]{0x0000004020000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_ruleAction8883 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleAction8922 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleAction8936 = new BitSet(new long[]{0x2080928000000000L});
    public static final BitSet FOLLOW_39_in_ruleAction8946 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_rulePropertyStatements_in_ruleAction8980 = new BitSet(new long[]{0x2080920000000000L});
    public static final BitSet FOLLOW_ruleAdvice_in_ruleAction9020 = new BitSet(new long[]{0x2080820000000000L});
    public static final BitSet FOLLOW_ruleActionInputGroup_in_ruleAction9059 = new BitSet(new long[]{0x2000820000000000L});
    public static final BitSet FOLLOW_ruleLayout_in_ruleAction9098 = new BitSet(new long[]{0x2000820000000000L});
    public static final BitSet FOLLOW_41_in_ruleAction9112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParameter_in_entryRuleParameter9145 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParameter9155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleParameter9214 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleParameter9227 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParameter9261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnonymousParameter_in_entryRuleAnonymousParameter9298 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnonymousParameter9308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAnonymousParameter9354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLayout_in_entryRuleLayout9385 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLayout9395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleLayout9430 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleFilter_in_ruleLayout9464 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_ruleLayout9479 = new BitSet(new long[]{0x0000004000000010L,0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleLayout9513 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleLayout9552 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleLayout9566 = new BitSet(new long[]{0x0060820000002030L});
    public static final BitSet FOLLOW_rulePathGroup_in_ruleLayout9600 = new BitSet(new long[]{0x0060820000002030L});
    public static final BitSet FOLLOW_41_in_ruleLayout9614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleActionInputGroup_in_entryRuleActionInputGroup9647 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleActionInputGroup9657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleActionInputGroup9691 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_ruleActionInputGroup9725 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_ruleActionInputGroup9764 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleActionInputGroup9778 = new BitSet(new long[]{0x0140824000000030L});
    public static final BitSet FOLLOW_rulePrerequisite_in_ruleActionInputGroup9812 = new BitSet(new long[]{0x0140824000000030L});
    public static final BitSet FOLLOW_41_in_ruleActionInputGroup9826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRepositoryConfiguration_in_entryRuleRepositoryConfiguration9859 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRepositoryConfiguration9869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleURI_in_ruleRepositoryConfiguration9929 = new BitSet(new long[]{0x0000004400000000L});
    public static final BitSet FOLLOW_62_in_ruleRepositoryConfiguration9949 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleRepositoryConfiguration9983 = new BitSet(new long[]{0x0000004400000000L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleRepositoryConfiguration10023 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleRepositoryConfiguration10037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleURI_in_entryRuleURI10071 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleURI10082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleURI10121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamedAdvice_in_entryRuleNamedAdvice10163 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamedAdvice10173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleNamedAdvice10232 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleNamedAdvice10271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvice_in_entryRuleAdvice10308 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvice10318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleAdvice10352 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleAdvice10374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_entryRuleCompoundAdvice10406 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCompoundAdvice10416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleCompoundAdvice10450 = new BitSet(new long[]{0x8000020004002810L});
    public static final BitSet FOLLOW_ruleAdviceStatement_in_ruleCompoundAdvice10485 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleCompoundAdvice10498 = new BitSet(new long[]{0x8000020004002810L});
    public static final BitSet FOLLOW_41_in_ruleCompoundAdvice10509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdviceStatement_in_entryRuleAdviceStatement10542 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdviceStatement10552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePath_in_ruleAdviceStatement10611 = new BitSet(new long[]{0x0000004000200000L});
    public static final BitSet FOLLOW_21_in_ruleAdviceStatement10626 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAdviceStatement10660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundAdvice_in_ruleAdviceStatement10705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePath_in_entryRuleAdvicePath10743 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePath10753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10812 = new BitSet(new long[]{0x8000000004000810L});
    public static final BitSet FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10851 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ruleAdvicePathSeparator_in_ruleAdvicePath10890 = new BitSet(new long[]{0x8000000004000810L});
    public static final BitSet FOLLOW_ruleAdvicePathElement_in_ruleAdvicePath10928 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ruleAdvicePathSeparator_in_entryRuleAdvicePathSeparator10967 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePathSeparator10977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathChildren_in_ruleAdvicePathSeparator11023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathChildren_in_entryRuleAdvicePathChildren11054 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePathChildren11064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleAdvicePathChildren11107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdvicePathElement_in_entryRuleAdvicePathElement11140 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdvicePathElement11150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleAdvicePathElement11202 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_ruleWildcardNode_in_ruleAdvicePathElement11216 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_11_in_ruleAdvicePathElement11226 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_ruleAdvicePathElement11251 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAdvicePathElement11285 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleAdvicePathElement11298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleAdvicePathElement11328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWildcardNode_in_entryRuleWildcardNode11375 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWildcardNode11386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleWildcardNode11424 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_ruleWildcardNode11438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFilter_in_entryRuleFilter11478 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFilter11488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleFilter11522 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleFilter11556 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleFilter11569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePreConditionAssert_in_entryRulePreConditionAssert11602 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePreConditionAssert11612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_rulePreConditionAssert11658 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_rulePreConditionAssert11680 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ruleAssertionExpression_in_rulePreConditionAssert11714 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_41_in_rulePreConditionAssert11728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePostConditionAssert_in_entryRulePostConditionAssert11761 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePostConditionAssert11771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_rulePostConditionAssert11817 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_rulePostConditionAssert11839 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ruleAssertionExpression_in_rulePostConditionAssert11873 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_41_in_rulePostConditionAssert11887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAssertionExpression_in_entryRuleAssertionExpression11920 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAssertionExpression11930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_ruleAssertionExpression11964 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAssertionExpression11998 = new BitSet(new long[]{0x0000000420000000L});
    public static final BitSet FOLLOW_29_in_ruleAssertionExpression12012 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleAssertionExpression12034 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleAssertionExpression12053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression12088 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression12098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValueExpression_in_ruleExpression12144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValueExpression_in_entryRuleValueExpression12175 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValueExpression12185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleValueExpression12243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression12279 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression12289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression12336 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleOrExpression12366 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression12413 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression12452 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression12462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleAndExpression12509 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleAndExpression12539 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleAndExpression12586 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression12625 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression12635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12682 = new BitSet(new long[]{0x0000000001800002L,0x00000000000003E0L});
    public static final BitSet FOLLOW_69_in_ruleRelationalExpression12713 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_70_in_ruleRelationalExpression12729 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_71_in_ruleRelationalExpression12745 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_72_in_ruleRelationalExpression12761 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_73_in_ruleRelationalExpression12777 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_24_in_ruleRelationalExpression12793 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_23_in_ruleRelationalExpression12809 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression12858 = new BitSet(new long[]{0x0000000001800002L,0x00000000000003E0L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression12897 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression12907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression12954 = new BitSet(new long[]{0x0000000000104002L});
    public static final BitSet FOLLOW_20_in_ruleAdditiveExpression12985 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_14_in_ruleAdditiveExpression13001 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression13050 = new BitSet(new long[]{0x0000000000104002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression13089 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression13099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression13146 = new BitSet(new long[]{0x0000000000402002L});
    public static final BitSet FOLLOW_22_in_ruleMultiplicativeExpression13177 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_13_in_ruleMultiplicativeExpression13193 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression13242 = new BitSet(new long[]{0x0000000000402002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression13281 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression13291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression13338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression13365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression13397 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression13407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleUnaryExpression13454 = new BitSet(new long[]{0x0000000010000070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_14_in_ruleUnaryExpression13470 = new BitSet(new long[]{0x0000000010000070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression13519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression13556 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression13566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression13613 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression13632 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInfixExpression13654 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleInfixExpression13671 = new BitSet(new long[]{0x1000000080000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleInfixExpression13706 = new BitSet(new long[]{0x10000000A0000000L});
    public static final BitSet FOLLOW_29_in_ruleInfixExpression13720 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleInfixExpression13754 = new BitSet(new long[]{0x10000000A0000000L});
    public static final BitSet FOLLOW_60_in_ruleInfixExpression13772 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAnonymousParameter_in_ruleInfixExpression13806 = new BitSet(new long[]{0x00000000A0000000L});
    public static final BitSet FOLLOW_29_in_ruleInfixExpression13820 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAnonymousParameter_in_ruleInfixExpression13854 = new BitSet(new long[]{0x00000000A0000000L});
    public static final BitSet FOLLOW_31_in_ruleInfixExpression13871 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression13897 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInfixExpression13919 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression13963 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression13973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression14020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_rulePrimaryExpression14047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression14074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression14101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall14133 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureCall14143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_ruleFeatureCall14189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_entryRuleOperationCall14220 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCall14230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleOperationCall14289 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleOperationCall14302 = new BitSet(new long[]{0x1000000080000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleOperationCall14337 = new BitSet(new long[]{0x10000000A0000000L});
    public static final BitSet FOLLOW_29_in_ruleOperationCall14351 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleParameter_in_ruleOperationCall14385 = new BitSet(new long[]{0x10000000A0000000L});
    public static final BitSet FOLLOW_60_in_ruleOperationCall14403 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAnonymousParameter_in_ruleOperationCall14437 = new BitSet(new long[]{0x00000000A0000000L});
    public static final BitSet FOLLOW_29_in_ruleOperationCall14451 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleAnonymousParameter_in_ruleOperationCall14485 = new BitSet(new long[]{0x00000000A0000000L});
    public static final BitSet FOLLOW_31_in_ruleOperationCall14502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral14535 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral14545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral14592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral14619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral14646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral14673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral14705 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral14715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleBooleanLiteral14761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleBooleanLiteral14777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral14824 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral14834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral14880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral14920 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral14930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleNullLiteral14975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral15020 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral15030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral15076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression15116 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression15126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleParanthesizedExpression15160 = new BitSet(new long[]{0x000000001000C070L,0x0000000000001C00L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression15182 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleParanthesizedExpression15190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression15223 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression15233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleGlobalVarExpression15291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleVisibility15341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleVisibility15356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleExecutionMode15401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleExecutionMode15416 = new BitSet(new long[]{0x0000000000000002L});

}