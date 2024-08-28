// Generated from TestGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import io.compiler.types.*;
	import io.compiler.core.exception.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TestGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		TEXTO=10, AP=11, FP=12, OP=13, ID=14, NUM=15, WS=16, DP=17, COMMA=18, 
		PV=19, OP_AT=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"TEXTO", "AP", "FP", "OP", "ID", "NUM", "WS", "DP", "COMMA", "PV", "OP_AT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'program'", "'inicio'", "'fim'", "'endprogram'", "'declare'", 
			"'number'", "'text'", "'leia'", "'escreva'", null, "'('", "')'", null, 
			null, null, null, "':'", "','", "';'", "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "TEXTO", 
			"AP", "FP", "OP", "ID", "NUM", "WS", "DP", "COMMA", "PV", "OP_AT"
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
		private ArrayList<Var> currentDecl	= new ArrayList<Var>();
		private Types currentType;
		private Types leftType=null, rightType=null;

		public void exibirVar() {
			for (String id : symbolTable.keySet()) {
				System.out.println(symbolTable.get(id));
			}
		}
		
		public void updateType() {
			for (Var v: currentDecl) {
				v.setType(currentType);
				symbolTable.put(v.getId(), v);
			}
		}
		
		public boolean isDeclared(String id) {
			return symbolTable.get(id) != null;
		}


	public TestGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TestGrammar.g4"; }

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
		"\u0004\u0000\u0014\u0098\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0005"+
		"\tk\b\t\n\t\f\tn\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0005\rz\b\r\n\r\f\r}\t\r\u0001"+
		"\u000e\u0004\u000e\u0080\b\u000e\u000b\u000e\f\u000e\u0081\u0001\u000e"+
		"\u0001\u000e\u0004\u000e\u0086\b\u000e\u000b\u000e\f\u000e\u0087\u0003"+
		"\u000e\u008a\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0000\u0000\u0014\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014\u0001\u0000\u0006\u0004\u0000  09AZaz\u0003\u0000"+
		"*+--//\u0001\u0000az\u0003\u000009AZaz\u0001\u000009\u0003\u0000\t\n\r"+
		"\r  \u009c\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0001)\u0001"+
		"\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u00058\u0001\u0000\u0000"+
		"\u0000\u0007<\u0001\u0000\u0000\u0000\tG\u0001\u0000\u0000\u0000\u000b"+
		"O\u0001\u0000\u0000\u0000\rV\u0001\u0000\u0000\u0000\u000f[\u0001\u0000"+
		"\u0000\u0000\u0011`\u0001\u0000\u0000\u0000\u0013h\u0001\u0000\u0000\u0000"+
		"\u0015q\u0001\u0000\u0000\u0000\u0017s\u0001\u0000\u0000\u0000\u0019u"+
		"\u0001\u0000\u0000\u0000\u001bw\u0001\u0000\u0000\u0000\u001d\u007f\u0001"+
		"\u0000\u0000\u0000\u001f\u008b\u0001\u0000\u0000\u0000!\u008f\u0001\u0000"+
		"\u0000\u0000#\u0091\u0001\u0000\u0000\u0000%\u0093\u0001\u0000\u0000\u0000"+
		"\'\u0095\u0001\u0000\u0000\u0000)*\u0005p\u0000\u0000*+\u0005r\u0000\u0000"+
		"+,\u0005o\u0000\u0000,-\u0005g\u0000\u0000-.\u0005r\u0000\u0000./\u0005"+
		"a\u0000\u0000/0\u0005m\u0000\u00000\u0002\u0001\u0000\u0000\u000012\u0005"+
		"i\u0000\u000023\u0005n\u0000\u000034\u0005i\u0000\u000045\u0005c\u0000"+
		"\u000056\u0005i\u0000\u000067\u0005o\u0000\u00007\u0004\u0001\u0000\u0000"+
		"\u000089\u0005f\u0000\u00009:\u0005i\u0000\u0000:;\u0005m\u0000\u0000"+
		";\u0006\u0001\u0000\u0000\u0000<=\u0005e\u0000\u0000=>\u0005n\u0000\u0000"+
		">?\u0005d\u0000\u0000?@\u0005p\u0000\u0000@A\u0005r\u0000\u0000AB\u0005"+
		"o\u0000\u0000BC\u0005g\u0000\u0000CD\u0005r\u0000\u0000DE\u0005a\u0000"+
		"\u0000EF\u0005m\u0000\u0000F\b\u0001\u0000\u0000\u0000GH\u0005d\u0000"+
		"\u0000HI\u0005e\u0000\u0000IJ\u0005c\u0000\u0000JK\u0005l\u0000\u0000"+
		"KL\u0005a\u0000\u0000LM\u0005r\u0000\u0000MN\u0005e\u0000\u0000N\n\u0001"+
		"\u0000\u0000\u0000OP\u0005n\u0000\u0000PQ\u0005u\u0000\u0000QR\u0005m"+
		"\u0000\u0000RS\u0005b\u0000\u0000ST\u0005e\u0000\u0000TU\u0005r\u0000"+
		"\u0000U\f\u0001\u0000\u0000\u0000VW\u0005t\u0000\u0000WX\u0005e\u0000"+
		"\u0000XY\u0005x\u0000\u0000YZ\u0005t\u0000\u0000Z\u000e\u0001\u0000\u0000"+
		"\u0000[\\\u0005l\u0000\u0000\\]\u0005e\u0000\u0000]^\u0005i\u0000\u0000"+
		"^_\u0005a\u0000\u0000_\u0010\u0001\u0000\u0000\u0000`a\u0005e\u0000\u0000"+
		"ab\u0005s\u0000\u0000bc\u0005c\u0000\u0000cd\u0005r\u0000\u0000de\u0005"+
		"e\u0000\u0000ef\u0005v\u0000\u0000fg\u0005a\u0000\u0000g\u0012\u0001\u0000"+
		"\u0000\u0000hl\u0005\"\u0000\u0000ik\u0007\u0000\u0000\u0000ji\u0001\u0000"+
		"\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001"+
		"\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"op\u0005\"\u0000\u0000p\u0014\u0001\u0000\u0000\u0000qr\u0005(\u0000\u0000"+
		"r\u0016\u0001\u0000\u0000\u0000st\u0005)\u0000\u0000t\u0018\u0001\u0000"+
		"\u0000\u0000uv\u0007\u0001\u0000\u0000v\u001a\u0001\u0000\u0000\u0000"+
		"w{\u0007\u0002\u0000\u0000xz\u0007\u0003\u0000\u0000yx\u0001\u0000\u0000"+
		"\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|\u001c\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000"+
		"~\u0080\u0007\u0004\u0000\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0081"+
		"\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0001\u0000\u0000\u0000\u0082\u0089\u0001\u0000\u0000\u0000\u0083\u0085"+
		"\u0005.\u0000\u0000\u0084\u0086\u0007\u0004\u0000\u0000\u0085\u0084\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0085\u0001"+
		"\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008a\u0001"+
		"\u0000\u0000\u0000\u0089\u0083\u0001\u0000\u0000\u0000\u0089\u008a\u0001"+
		"\u0000\u0000\u0000\u008a\u001e\u0001\u0000\u0000\u0000\u008b\u008c\u0007"+
		"\u0005\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008e\u0006"+
		"\u000f\u0000\u0000\u008e \u0001\u0000\u0000\u0000\u008f\u0090\u0005:\u0000"+
		"\u0000\u0090\"\u0001\u0000\u0000\u0000\u0091\u0092\u0005,\u0000\u0000"+
		"\u0092$\u0001\u0000\u0000\u0000\u0093\u0094\u0005;\u0000\u0000\u0094&"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0005:\u0000\u0000\u0096\u0097\u0005"+
		"=\u0000\u0000\u0097(\u0001\u0000\u0000\u0000\b\u0000jly{\u0081\u0087\u0089"+
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