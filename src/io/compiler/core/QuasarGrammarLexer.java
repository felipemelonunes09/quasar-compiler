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
		TEXT=10, IDENTIFIER=11, OPERATOR=12, NUMBER=13, REAL_NUMBER=14, WS=15, 
		COMMENT=16, RELATIONAL_OPERATOR=17, START_BLOCK=18, END_BLOCK=19, OPEN_P=20, 
		CLOSE_P=21, END_COMMAND=22, COMMA=23, COLLON=24, VAR=25, ATRIBUITION_OPERATOR=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"TEXT", "IDENTIFIER", "OPERATOR", "NUMBER", "REAL_NUMBER", "WS", "COMMENT", 
			"RELATIONAL_OPERATOR", "START_BLOCK", "END_BLOCK", "OPEN_P", "CLOSE_P", 
			"END_COMMAND", "COMMA", "COLLON", "VAR", "ATRIBUITION_OPERATOR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'while'", "'do:while'", "'if'", "'else'", "'number'", "'text'", 
			"'real'", "'read'", "'write'", null, null, null, null, null, null, null, 
			null, "'{'", "'}'", "'('", "')'", "';'", "','", "':'", "'-var'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "TEXT", "IDENTIFIER", 
			"OPERATOR", "NUMBER", "REAL_NUMBER", "WS", "COMMENT", "RELATIONAL_OPERATOR", 
			"START_BLOCK", "END_BLOCK", "OPEN_P", "CLOSE_P", "END_COMMAND", "COMMA", 
			"COLLON", "VAR", "ATRIBUITION_OPERATOR"
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
		"\u0004\u0000\u001a\u00be\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0005\tk\b\t\n\t\f\tn\t\t\u0001\t\u0001\t\u0001\n\u0003\ns\b\n\u0001"+
		"\n\u0005\nv\b\n\n\n\f\ny\t\n\u0001\u000b\u0001\u000b\u0001\f\u0004\f~"+
		"\b\f\u000b\f\f\f\u007f\u0001\r\u0004\r\u0083\b\r\u000b\r\f\r\u0084\u0001"+
		"\r\u0001\r\u0004\r\u0089\b\r\u000b\r\f\r\u008a\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0004\u000f\u0095\b\u000f\u000b\u000f\f\u000f\u0096\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u00a7\b\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0096\u0000\u001a\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a\u0001\u0000\u0007\u0004"+
		"\u0000  09AZaz\u0002\u0000AZaz\u0003\u000009AZaz\u0003\u0000*+--//\u0001"+
		"\u000009\u0003\u0000\t\n\r\r  \u0002\u0000<<>>\u00c7\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00015"+
		"\u0001\u0000\u0000\u0000\u0003;\u0001\u0000\u0000\u0000\u0005D\u0001\u0000"+
		"\u0000\u0000\u0007G\u0001\u0000\u0000\u0000\tL\u0001\u0000\u0000\u0000"+
		"\u000bS\u0001\u0000\u0000\u0000\rX\u0001\u0000\u0000\u0000\u000f]\u0001"+
		"\u0000\u0000\u0000\u0011b\u0001\u0000\u0000\u0000\u0013h\u0001\u0000\u0000"+
		"\u0000\u0015r\u0001\u0000\u0000\u0000\u0017z\u0001\u0000\u0000\u0000\u0019"+
		"}\u0001\u0000\u0000\u0000\u001b\u0082\u0001\u0000\u0000\u0000\u001d\u008c"+
		"\u0001\u0000\u0000\u0000\u001f\u0090\u0001\u0000\u0000\u0000!\u00a6\u0001"+
		"\u0000\u0000\u0000#\u00a8\u0001\u0000\u0000\u0000%\u00aa\u0001\u0000\u0000"+
		"\u0000\'\u00ac\u0001\u0000\u0000\u0000)\u00ae\u0001\u0000\u0000\u0000"+
		"+\u00b0\u0001\u0000\u0000\u0000-\u00b2\u0001\u0000\u0000\u0000/\u00b4"+
		"\u0001\u0000\u0000\u00001\u00b6\u0001\u0000\u0000\u00003\u00bb\u0001\u0000"+
		"\u0000\u000056\u0005w\u0000\u000067\u0005h\u0000\u000078\u0005i\u0000"+
		"\u000089\u0005l\u0000\u00009:\u0005e\u0000\u0000:\u0002\u0001\u0000\u0000"+
		"\u0000;<\u0005d\u0000\u0000<=\u0005o\u0000\u0000=>\u0005:\u0000\u0000"+
		">?\u0005w\u0000\u0000?@\u0005h\u0000\u0000@A\u0005i\u0000\u0000AB\u0005"+
		"l\u0000\u0000BC\u0005e\u0000\u0000C\u0004\u0001\u0000\u0000\u0000DE\u0005"+
		"i\u0000\u0000EF\u0005f\u0000\u0000F\u0006\u0001\u0000\u0000\u0000GH\u0005"+
		"e\u0000\u0000HI\u0005l\u0000\u0000IJ\u0005s\u0000\u0000JK\u0005e\u0000"+
		"\u0000K\b\u0001\u0000\u0000\u0000LM\u0005n\u0000\u0000MN\u0005u\u0000"+
		"\u0000NO\u0005m\u0000\u0000OP\u0005b\u0000\u0000PQ\u0005e\u0000\u0000"+
		"QR\u0005r\u0000\u0000R\n\u0001\u0000\u0000\u0000ST\u0005t\u0000\u0000"+
		"TU\u0005e\u0000\u0000UV\u0005x\u0000\u0000VW\u0005t\u0000\u0000W\f\u0001"+
		"\u0000\u0000\u0000XY\u0005r\u0000\u0000YZ\u0005e\u0000\u0000Z[\u0005a"+
		"\u0000\u0000[\\\u0005l\u0000\u0000\\\u000e\u0001\u0000\u0000\u0000]^\u0005"+
		"r\u0000\u0000^_\u0005e\u0000\u0000_`\u0005a\u0000\u0000`a\u0005d\u0000"+
		"\u0000a\u0010\u0001\u0000\u0000\u0000bc\u0005w\u0000\u0000cd\u0005r\u0000"+
		"\u0000de\u0005i\u0000\u0000ef\u0005t\u0000\u0000fg\u0005e\u0000\u0000"+
		"g\u0012\u0001\u0000\u0000\u0000hl\u0005\"\u0000\u0000ik\u0007\u0000\u0000"+
		"\u0000ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000"+
		"\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000nl\u0001"+
		"\u0000\u0000\u0000op\u0005\"\u0000\u0000p\u0014\u0001\u0000\u0000\u0000"+
		"qs\u0007\u0001\u0000\u0000rq\u0001\u0000\u0000\u0000sw\u0001\u0000\u0000"+
		"\u0000tv\u0007\u0002\u0000\u0000ut\u0001\u0000\u0000\u0000vy\u0001\u0000"+
		"\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000x\u0016"+
		"\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000z{\u0007\u0003\u0000"+
		"\u0000{\u0018\u0001\u0000\u0000\u0000|~\u0007\u0004\u0000\u0000}|\u0001"+
		"\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000"+
		"\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u001a\u0001\u0000\u0000"+
		"\u0000\u0081\u0083\u0007\u0004\u0000\u0000\u0082\u0081\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000"+
		"\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000"+
		"\u0000\u0086\u0088\u0005.\u0000\u0000\u0087\u0089\u0007\u0004\u0000\u0000"+
		"\u0088\u0087\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000"+
		"\u008b\u001c\u0001\u0000\u0000\u0000\u008c\u008d\u0007\u0005\u0000\u0000"+
		"\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f\u0006\u000e\u0000\u0000"+
		"\u008f\u001e\u0001\u0000\u0000\u0000\u0090\u0091\u0005/\u0000\u0000\u0091"+
		"\u0092\u0005*\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093\u0095"+
		"\t\u0000\u0000\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0096\u0094\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0005"+
		"*\u0000\u0000\u0099\u009a\u0005/\u0000\u0000\u009a\u009b\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0006\u000f\u0000\u0000\u009c \u0001\u0000\u0000\u0000"+
		"\u009d\u00a7\u0007\u0006\u0000\u0000\u009e\u009f\u0005>\u0000\u0000\u009f"+
		"\u00a7\u0005=\u0000\u0000\u00a0\u00a1\u0005<\u0000\u0000\u00a1\u00a7\u0005"+
		"=\u0000\u0000\u00a2\u00a3\u0005>\u0000\u0000\u00a3\u00a7\u0005<\u0000"+
		"\u0000\u00a4\u00a5\u0005=\u0000\u0000\u00a5\u00a7\u0005=\u0000\u0000\u00a6"+
		"\u009d\u0001\u0000\u0000\u0000\u00a6\u009e\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a6\u00a2\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a7\"\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0005{\u0000\u0000\u00a9$\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005}"+
		"\u0000\u0000\u00ab&\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005(\u0000\u0000"+
		"\u00ad(\u0001\u0000\u0000\u0000\u00ae\u00af\u0005)\u0000\u0000\u00af*"+
		"\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005;\u0000\u0000\u00b1,\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b3\u0005,\u0000\u0000\u00b3.\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b5\u0005:\u0000\u0000\u00b50\u0001\u0000\u0000\u0000\u00b6\u00b7"+
		"\u0005-\u0000\u0000\u00b7\u00b8\u0005v\u0000\u0000\u00b8\u00b9\u0005a"+
		"\u0000\u0000\u00b9\u00ba\u0005r\u0000\u0000\u00ba2\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0005-\u0000\u0000\u00bc\u00bd\u0005>\u0000\u0000\u00bd4"+
		"\u0001\u0000\u0000\u0000\u000b\u0000jlruw\u007f\u0084\u008a\u0096\u00a6"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}