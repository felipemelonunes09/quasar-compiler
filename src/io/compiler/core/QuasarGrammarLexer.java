// Generated from QuasarGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Stack;
	
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
	

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, TEXT=9, 
		IDENTIFIER=10, OPERATOR=11, NUMBER=12, WS=13, RELATIONAL_OPERATOR=14, 
		START_BLOCK=15, END_BLOCK=16, OPEN_P=17, CLOSE_P=18, END_COMMAND=19, COMMA=20, 
		COLLON=21, VAR=22, ATRIBUITION_OPERATOR=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "TEXT", 
			"IDENTIFIER", "OPERATOR", "NUMBER", "WS", "RELATIONAL_OPERATOR", "START_BLOCK", 
			"END_BLOCK", "OPEN_P", "CLOSE_P", "END_COMMAND", "COMMA", "COLLON", "VAR", 
			"ATRIBUITION_OPERATOR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'while'", "'do:while'", "'if'", "'else'", "'number'", "'text'", 
			"'read'", "'write'", null, null, null, null, null, null, "'{'", "'}'", 
			"'('", "')'", "';'", "','", "':'", "'-var'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "TEXT", "IDENTIFIER", 
			"OPERATOR", "NUMBER", "WS", "RELATIONAL_OPERATOR", "START_BLOCK", "END_BLOCK", 
			"OPEN_P", "CLOSE_P", "END_COMMAND", "COMMA", "COLLON", "VAR", "ATRIBUITION_OPERATOR"
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
		private Stack<IfCommand> ifStack 				 = new Stack<IfCommand>();
		private Stack<WhileCommand> loopStack 			 = new Stack<WhileCommand>();
		private Stack<ExpressionCommand> expressionStack = new Stack<ExpressionCommand>();
		
		
		private IfCommand currentIfCommand;
		private WhileCommand currentWhileCommand;
		
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
			
				if (rightType.getValue() < type.getValue()) {
					rightType = type;
				}
			}
		}
		
		public void addExpression(String expression) {
	        if (!expressionStack.isEmpty() && expressionStack.peek() != null) {
	            expressionStack.peek().addExpression(expression);
	        }
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
		"\u0004\u0000\u0017\u00a2\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0005\b`\b\b\n\b\f\bc\t\b\u0001"+
		"\b\u0001\b\u0001\t\u0003\th\b\t\u0001\t\u0005\tk\b\t\n\t\f\tn\t\t\u0001"+
		"\n\u0001\n\u0001\u000b\u0004\u000bs\b\u000b\u000b\u000b\f\u000bt\u0001"+
		"\u000b\u0001\u000b\u0004\u000by\b\u000b\u000b\u000b\f\u000bz\u0003\u000b"+
		"}\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u008c\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0000\u0000\u0017\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017\u0001\u0000\u0007\u0004\u0000"+
		"  09AZaz\u0002\u0000AZaz\u0003\u000009AZaz\u0003\u0000*+--//\u0001\u0000"+
		"09\u0003\u0000\t\n\r\r  \u0002\u0000<<>>\u00aa\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0000-\u0001\u0000\u0000\u0000\u0001/\u0001\u0000\u0000\u0000\u0003"+
		"5\u0001\u0000\u0000\u0000\u0005>\u0001\u0000\u0000\u0000\u0007A\u0001"+
		"\u0000\u0000\u0000\tF\u0001\u0000\u0000\u0000\u000bM\u0001\u0000\u0000"+
		"\u0000\rR\u0001\u0000\u0000\u0000\u000fW\u0001\u0000\u0000\u0000\u0011"+
		"]\u0001\u0000\u0000\u0000\u0013g\u0001\u0000\u0000\u0000\u0015o\u0001"+
		"\u0000\u0000\u0000\u0017r\u0001\u0000\u0000\u0000\u0019~\u0001\u0000\u0000"+
		"\u0000\u001b\u008b\u0001\u0000\u0000\u0000\u001d\u008d\u0001\u0000\u0000"+
		"\u0000\u001f\u008f\u0001\u0000\u0000\u0000!\u0091\u0001\u0000\u0000\u0000"+
		"#\u0093\u0001\u0000\u0000\u0000%\u0095\u0001\u0000\u0000\u0000\'\u0097"+
		"\u0001\u0000\u0000\u0000)\u0099\u0001\u0000\u0000\u0000+\u009b\u0001\u0000"+
		"\u0000\u0000-\u00a0\u0001\u0000\u0000\u0000/0\u0005w\u0000\u000001\u0005"+
		"h\u0000\u000012\u0005i\u0000\u000023\u0005l\u0000\u000034\u0005e\u0000"+
		"\u00004\u0002\u0001\u0000\u0000\u000056\u0005d\u0000\u000067\u0005o\u0000"+
		"\u000078\u0005:\u0000\u000089\u0005w\u0000\u00009:\u0005h\u0000\u0000"+
		":;\u0005i\u0000\u0000;<\u0005l\u0000\u0000<=\u0005e\u0000\u0000=\u0004"+
		"\u0001\u0000\u0000\u0000>?\u0005i\u0000\u0000?@\u0005f\u0000\u0000@\u0006"+
		"\u0001\u0000\u0000\u0000AB\u0005e\u0000\u0000BC\u0005l\u0000\u0000CD\u0005"+
		"s\u0000\u0000DE\u0005e\u0000\u0000E\b\u0001\u0000\u0000\u0000FG\u0005"+
		"n\u0000\u0000GH\u0005u\u0000\u0000HI\u0005m\u0000\u0000IJ\u0005b\u0000"+
		"\u0000JK\u0005e\u0000\u0000KL\u0005r\u0000\u0000L\n\u0001\u0000\u0000"+
		"\u0000MN\u0005t\u0000\u0000NO\u0005e\u0000\u0000OP\u0005x\u0000\u0000"+
		"PQ\u0005t\u0000\u0000Q\f\u0001\u0000\u0000\u0000RS\u0005r\u0000\u0000"+
		"ST\u0005e\u0000\u0000TU\u0005a\u0000\u0000UV\u0005d\u0000\u0000V\u000e"+
		"\u0001\u0000\u0000\u0000WX\u0005w\u0000\u0000XY\u0005r\u0000\u0000YZ\u0005"+
		"i\u0000\u0000Z[\u0005t\u0000\u0000[\\\u0005e\u0000\u0000\\\u0010\u0001"+
		"\u0000\u0000\u0000]a\u0005\"\u0000\u0000^`\u0007\u0000\u0000\u0000_^\u0001"+
		"\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000bd\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000"+
		"\u0000de\u0005\"\u0000\u0000e\u0012\u0001\u0000\u0000\u0000fh\u0007\u0001"+
		"\u0000\u0000gf\u0001\u0000\u0000\u0000hl\u0001\u0000\u0000\u0000ik\u0007"+
		"\u0002\u0000\u0000ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000"+
		"lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000m\u0014\u0001\u0000"+
		"\u0000\u0000nl\u0001\u0000\u0000\u0000op\u0007\u0003\u0000\u0000p\u0016"+
		"\u0001\u0000\u0000\u0000qs\u0007\u0004\u0000\u0000rq\u0001\u0000\u0000"+
		"\u0000st\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000u|\u0001\u0000\u0000\u0000vx\u0005.\u0000\u0000wy\u0007\u0004"+
		"\u0000\u0000xw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000zx\u0001"+
		"\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{}\u0001\u0000\u0000\u0000"+
		"|v\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u0018\u0001\u0000"+
		"\u0000\u0000~\u007f\u0007\u0005\u0000\u0000\u007f\u0080\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0006\f\u0000\u0000\u0081\u001a\u0001\u0000\u0000\u0000"+
		"\u0082\u008c\u0007\u0006\u0000\u0000\u0083\u0084\u0005>\u0000\u0000\u0084"+
		"\u008c\u0005=\u0000\u0000\u0085\u0086\u0005<\u0000\u0000\u0086\u008c\u0005"+
		"=\u0000\u0000\u0087\u0088\u0005>\u0000\u0000\u0088\u008c\u0005<\u0000"+
		"\u0000\u0089\u008a\u0005=\u0000\u0000\u008a\u008c\u0005=\u0000\u0000\u008b"+
		"\u0082\u0001\u0000\u0000\u0000\u008b\u0083\u0001\u0000\u0000\u0000\u008b"+
		"\u0085\u0001\u0000\u0000\u0000\u008b\u0087\u0001\u0000\u0000\u0000\u008b"+
		"\u0089\u0001\u0000\u0000\u0000\u008c\u001c\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0005{\u0000\u0000\u008e\u001e\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0005}\u0000\u0000\u0090 \u0001\u0000\u0000\u0000\u0091\u0092\u0005("+
		"\u0000\u0000\u0092\"\u0001\u0000\u0000\u0000\u0093\u0094\u0005)\u0000"+
		"\u0000\u0094$\u0001\u0000\u0000\u0000\u0095\u0096\u0005;\u0000\u0000\u0096"+
		"&\u0001\u0000\u0000\u0000\u0097\u0098\u0005,\u0000\u0000\u0098(\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0005:\u0000\u0000\u009a*\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0005-\u0000\u0000\u009c\u009d\u0005v\u0000\u0000\u009d"+
		"\u009e\u0005a\u0000\u0000\u009e\u009f\u0005r\u0000\u0000\u009f,\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a1\u0005=\u0000\u0000\u00a1.\u0001\u0000\u0000"+
		"\u0000\n\u0000_agjltz|\u008b\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}