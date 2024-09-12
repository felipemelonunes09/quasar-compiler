// Generated from QuasarGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Stack;
	
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
	import io.compiler.core.ast.expression.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class QuasarGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, TEXT=12, IDENTIFIER=13, OPERATOR=14, NUMBER=15, REAL_NUMBER=16, 
		WS=17, COMMENT=18, RELATIONAL_OPERATOR=19, START_BLOCK=20, END_BLOCK=21, 
		OPEN_P=22, CLOSE_P=23, END_COMMAND=24, COMMA=25, COLLON=26, VAR=27, ATRIBUITION_OPERATOR=28;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "TEXT", "IDENTIFIER", "OPERATOR", "NUMBER", "REAL_NUMBER", 
			"WS", "COMMENT", "RELATIONAL_OPERATOR", "START_BLOCK", "END_BLOCK", "OPEN_P", 
			"CLOSE_P", "END_COMMAND", "COMMA", "COLLON", "VAR", "ATRIBUITION_OPERATOR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'while'", "'do:while'", "'if'", "'else'", "'number'", "'text'", 
			"'real'", "'read'", "'write'", "'and'", "'or'", null, null, null, null, 
			null, null, null, null, "'{'", "'}'", "'('", "')'", "';'", "','", "':'", 
			"'-var'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"TEXT", "IDENTIFIER", "OPERATOR", "NUMBER", "REAL_NUMBER", "WS", "COMMENT", 
			"RELATIONAL_OPERATOR", "START_BLOCK", "END_BLOCK", "OPEN_P", "CLOSE_P", 
			"END_COMMAND", "COMMA", "COLLON", "VAR", "ATRIBUITION_OPERATOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		
		private HashMap<String, Var> symbolTable = new HashMap<String, Var>();
		private ArrayList<Var> currentDeclaration = new ArrayList<Var>();
		private Program program = new Program();
		
		private Stack<ArrayList<Command>> stack 		 = new Stack<ArrayList<Command>>();
		private Stack<BlockCommand> blockStack			 = new Stack<BlockCommand>();
		
		private Stack<ExpressionCommand> expressionStack = new Stack<ExpressionCommand>();
		
		private AttribuitionCommand currentAttribuitionCommand;
		
		private Types currentType;
		private Types leftType = null, rightType = null;
		
		
		public Program getProgram(){
			return this.program;
		}
		
		public void showVariables() {
			for (String id : symbolTable.keySet()) {
				System.out.println(symbolTable.get(id));
			}
		}
		
		public void updateType() {
			for (Var v: currentDeclaration) {
				v.setType(currentType);
				symbolTable.put(v.getId(), v);
				stack.peek().add(new DeclarationCommand(v));
			}
		}
		
		public boolean isDeclared(String id) {
			return symbolTable.get(id) != null;
		}
		
		public void setVarInitializated(String id) {
			if (!isDeclared(id)) { 
				throw new QuasarSemanticException("Undeclared Variable: " + id); 
			} 
			symbolTable.get(id).setInitialized(true);
		}
		
		public void setTypeIdentifier(String id) {
			System.out.println("Setting the type of the var");
			System.out.println(id);
			
			// case var is not declared
			if (!isDeclared(id)) 
				throw new QuasarSemanticException("Undeclared Variable: " + id);
			
			Var var = symbolTable.get(id);
			
			// case var was not properly initializated
			if (!var.isInitialized())
				throw new QuasarSemanticException("Variable: " + var + " has no value assigned");
				
			if (rightType == null) {
				rightType = var.getType();
			}
			else {
				if (var.getType().getValue() > rightType.getValue()) {
					rightType = var.getType();
				}
			}
		}
		
		public void setType(Types type) {
			if (rightType == null) {
				rightType = type;
			}
			else {
				if (rightType.getValue() <= type.getValue()) {
					rightType = type;
				}
				else {
					throw new QuasarSemanticException("Type Missmatch on Assigment"); 
				}
			}
		}
		
		public void addExpressionTerm(String term) {
	        if (!expressionStack.isEmpty() && expressionStack.peek() != null) {
	            expressionStack.peek().addTerm(term);
	        }
	    }

		public void addExpressionOperator(String operator) {
			if (!expressionStack.isEmpty() && expressionStack.peek() != null) {
				expressionStack.peek().addOperator(operator);
			}
		}
		
		public void resetTypes() {
			rightType=null;
			leftType=null;
		}


	public QuasarGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QuasarGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001c\u00c9\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000bv\b\u000b\n\u000b"+
		"\f\u000by\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0003\f~\b\f\u0001\f"+
		"\u0005\f\u0081\b\f\n\f\f\f\u0084\t\f\u0001\r\u0001\r\u0001\u000e\u0004"+
		"\u000e\u0089\b\u000e\u000b\u000e\f\u000e\u008a\u0001\u000f\u0004\u000f"+
		"\u008e\b\u000f\u000b\u000f\f\u000f\u008f\u0001\u000f\u0001\u000f\u0004"+
		"\u000f\u0094\b\u000f\u000b\u000f\f\u000f\u0095\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0004\u0011\u00a0\b\u0011\u000b\u0011\f\u0011\u00a1\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u00b2\b\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u00a1\u0000\u001c\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c\u0001"+
		"\u0000\u0007\u0001\u0000\"\"\u0002\u0000AZaz\u0003\u000009AZaz\u0003\u0000"+
		"*+--//\u0001\u000009\u0003\u0000\t\n\r\r  \u0002\u0000<<>>\u00d2\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00019\u0001\u0000\u0000\u0000\u0003?\u0001\u0000\u0000\u0000\u0005H"+
		"\u0001\u0000\u0000\u0000\u0007K\u0001\u0000\u0000\u0000\tP\u0001\u0000"+
		"\u0000\u0000\u000bW\u0001\u0000\u0000\u0000\r\\\u0001\u0000\u0000\u0000"+
		"\u000fa\u0001\u0000\u0000\u0000\u0011f\u0001\u0000\u0000\u0000\u0013l"+
		"\u0001\u0000\u0000\u0000\u0015p\u0001\u0000\u0000\u0000\u0017s\u0001\u0000"+
		"\u0000\u0000\u0019}\u0001\u0000\u0000\u0000\u001b\u0085\u0001\u0000\u0000"+
		"\u0000\u001d\u0088\u0001\u0000\u0000\u0000\u001f\u008d\u0001\u0000\u0000"+
		"\u0000!\u0097\u0001\u0000\u0000\u0000#\u009b\u0001\u0000\u0000\u0000%"+
		"\u00b1\u0001\u0000\u0000\u0000\'\u00b3\u0001\u0000\u0000\u0000)\u00b5"+
		"\u0001\u0000\u0000\u0000+\u00b7\u0001\u0000\u0000\u0000-\u00b9\u0001\u0000"+
		"\u0000\u0000/\u00bb\u0001\u0000\u0000\u00001\u00bd\u0001\u0000\u0000\u0000"+
		"3\u00bf\u0001\u0000\u0000\u00005\u00c1\u0001\u0000\u0000\u00007\u00c6"+
		"\u0001\u0000\u0000\u00009:\u0005w\u0000\u0000:;\u0005h\u0000\u0000;<\u0005"+
		"i\u0000\u0000<=\u0005l\u0000\u0000=>\u0005e\u0000\u0000>\u0002\u0001\u0000"+
		"\u0000\u0000?@\u0005d\u0000\u0000@A\u0005o\u0000\u0000AB\u0005:\u0000"+
		"\u0000BC\u0005w\u0000\u0000CD\u0005h\u0000\u0000DE\u0005i\u0000\u0000"+
		"EF\u0005l\u0000\u0000FG\u0005e\u0000\u0000G\u0004\u0001\u0000\u0000\u0000"+
		"HI\u0005i\u0000\u0000IJ\u0005f\u0000\u0000J\u0006\u0001\u0000\u0000\u0000"+
		"KL\u0005e\u0000\u0000LM\u0005l\u0000\u0000MN\u0005s\u0000\u0000NO\u0005"+
		"e\u0000\u0000O\b\u0001\u0000\u0000\u0000PQ\u0005n\u0000\u0000QR\u0005"+
		"u\u0000\u0000RS\u0005m\u0000\u0000ST\u0005b\u0000\u0000TU\u0005e\u0000"+
		"\u0000UV\u0005r\u0000\u0000V\n\u0001\u0000\u0000\u0000WX\u0005t\u0000"+
		"\u0000XY\u0005e\u0000\u0000YZ\u0005x\u0000\u0000Z[\u0005t\u0000\u0000"+
		"[\f\u0001\u0000\u0000\u0000\\]\u0005r\u0000\u0000]^\u0005e\u0000\u0000"+
		"^_\u0005a\u0000\u0000_`\u0005l\u0000\u0000`\u000e\u0001\u0000\u0000\u0000"+
		"ab\u0005r\u0000\u0000bc\u0005e\u0000\u0000cd\u0005a\u0000\u0000de\u0005"+
		"d\u0000\u0000e\u0010\u0001\u0000\u0000\u0000fg\u0005w\u0000\u0000gh\u0005"+
		"r\u0000\u0000hi\u0005i\u0000\u0000ij\u0005t\u0000\u0000jk\u0005e\u0000"+
		"\u0000k\u0012\u0001\u0000\u0000\u0000lm\u0005a\u0000\u0000mn\u0005n\u0000"+
		"\u0000no\u0005d\u0000\u0000o\u0014\u0001\u0000\u0000\u0000pq\u0005o\u0000"+
		"\u0000qr\u0005r\u0000\u0000r\u0016\u0001\u0000\u0000\u0000sw\u0005\"\u0000"+
		"\u0000tv\b\u0000\u0000\u0000ut\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000"+
		"\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xz\u0001\u0000"+
		"\u0000\u0000yw\u0001\u0000\u0000\u0000z{\u0005\"\u0000\u0000{\u0018\u0001"+
		"\u0000\u0000\u0000|~\u0007\u0001\u0000\u0000}|\u0001\u0000\u0000\u0000"+
		"~\u0082\u0001\u0000\u0000\u0000\u007f\u0081\u0007\u0002\u0000\u0000\u0080"+
		"\u007f\u0001\u0000\u0000\u0000\u0081\u0084\u0001\u0000\u0000\u0000\u0082"+
		"\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u001a\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0085"+
		"\u0086\u0007\u0003\u0000\u0000\u0086\u001c\u0001\u0000\u0000\u0000\u0087"+
		"\u0089\u0007\u0004\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0001\u0000\u0000\u0000\u008b\u001e\u0001\u0000\u0000\u0000\u008c"+
		"\u008e\u0007\u0004\u0000\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e"+
		"\u008f\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091"+
		"\u0093\u0005.\u0000\u0000\u0092\u0094\u0007\u0004\u0000\u0000\u0093\u0092"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0093"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096 \u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0007\u0005\u0000\u0000\u0098\u0099\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0006\u0010\u0000\u0000\u009a\"\u0001\u0000"+
		"\u0000\u0000\u009b\u009c\u0005/\u0000\u0000\u009c\u009d\u0005*\u0000\u0000"+
		"\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u00a0\t\u0000\u0000\u0000\u009f"+
		"\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005*\u0000\u0000\u00a4\u00a5"+
		"\u0005/\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0006"+
		"\u0011\u0000\u0000\u00a7$\u0001\u0000\u0000\u0000\u00a8\u00b2\u0007\u0006"+
		"\u0000\u0000\u00a9\u00aa\u0005>\u0000\u0000\u00aa\u00b2\u0005=\u0000\u0000"+
		"\u00ab\u00ac\u0005<\u0000\u0000\u00ac\u00b2\u0005=\u0000\u0000\u00ad\u00ae"+
		"\u0005>\u0000\u0000\u00ae\u00b2\u0005<\u0000\u0000\u00af\u00b0\u0005="+
		"\u0000\u0000\u00b0\u00b2\u0005=\u0000\u0000\u00b1\u00a8\u0001\u0000\u0000"+
		"\u0000\u00b1\u00a9\u0001\u0000\u0000\u0000\u00b1\u00ab\u0001\u0000\u0000"+
		"\u0000\u00b1\u00ad\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000"+
		"\u0000\u00b2&\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005{\u0000\u0000\u00b4"+
		"(\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005}\u0000\u0000\u00b6*\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0005(\u0000\u0000\u00b8,\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u0005)\u0000\u0000\u00ba.\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0005;\u0000\u0000\u00bc0\u0001\u0000\u0000\u0000\u00bd\u00be\u0005"+
		",\u0000\u0000\u00be2\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005:\u0000"+
		"\u0000\u00c04\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005-\u0000\u0000\u00c2"+
		"\u00c3\u0005v\u0000\u0000\u00c3\u00c4\u0005a\u0000\u0000\u00c4\u00c5\u0005"+
		"r\u0000\u0000\u00c56\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005-\u0000"+
		"\u0000\u00c7\u00c8\u0005>\u0000\u0000\u00c88\u0001\u0000\u0000\u0000\n"+
		"\u0000w}\u0080\u0082\u008a\u008f\u0095\u00a1\u00b1\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}