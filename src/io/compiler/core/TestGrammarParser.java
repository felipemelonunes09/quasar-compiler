// Generated from TestGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import io.compiler.types.*;
	import io.compiler.core.exception.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TestGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		TEXTO=10, AP=11, FP=12, OP=13, ID=14, NUM=15, WS=16, DP=17, COMMA=18, 
		PV=19, OP_AT=20;
	public static final int
		RULE_program = 0, RULE_declarevar = 1, RULE_comando = 2, RULE_cmdAtrib = 3, 
		RULE_cmdLeitura = 4, RULE_cmdEscrita = 5, RULE_expr = 6, RULE_term = 7, 
		RULE_exprl = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declarevar", "comando", "cmdAtrib", "cmdLeitura", "cmdEscrita", 
			"expr", "term", "exprl"
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

	@Override
	public String getGrammarFileName() { return "TestGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



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

	public TestGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<DeclarevarContext> declarevar() {
			return getRuleContexts(DeclarevarContext.class);
		}
		public DeclarevarContext declarevar(int i) {
			return getRuleContext(DeclarevarContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(T__0);
			setState(20); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(19);
				declarevar();
				}
				}
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
			setState(24);
			match(T__1);
			setState(26); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(25);
				comando();
				}
				}
				setState(28); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 17152L) != 0) );
			setState(30);
			match(T__2);
			setState(31);
			match(T__3);
			 
							exibirVar();
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarevarContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(TestGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(TestGrammarParser.ID, i);
		}
		public TerminalNode DP() { return getToken(TestGrammarParser.DP, 0); }
		public TerminalNode PV() { return getToken(TestGrammarParser.PV, 0); }
		public List<TerminalNode> COMMA() { return getTokens(TestGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TestGrammarParser.COMMA, i);
		}
		public DeclarevarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarevar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterDeclarevar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitDeclarevar(this);
		}
	}

	public final DeclarevarContext declarevar() throws RecognitionException {
		DeclarevarContext _localctx = new DeclarevarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declarevar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__4);
			 currentDecl.clear(); 
			setState(36);
			match(ID);
			 currentDecl.add(new Var(_input.LT(-1).getText())); 
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(38);
				match(COMMA);
				setState(39);
				match(ID);
				 currentDecl.add(new Var(_input.LT(-1).getText())); 
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
			match(DP);
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(47);
				match(T__5);
				 currentType = Types.NUMBER; 
				}
				break;
			case T__6:
				{
				setState(49);
				match(T__6);
				 currentType = Types.TEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 updateType(); 
			setState(54);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public CmdAtribContext cmdAtrib() {
			return getRuleContext(CmdAtribContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comando);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				cmdAtrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				cmdLeitura();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				cmdEscrita();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAtribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TestGrammarParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(TestGrammarParser.OP_AT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PV() { return getToken(TestGrammarParser.PV, 0); }
		public CmdAtribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAtrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterCmdAtrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitCmdAtrib(this);
		}
	}

	public final CmdAtribContext cmdAtrib() throws RecognitionException {
		CmdAtribContext _localctx = new CmdAtribContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cmdAtrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(ID);
			 
							if (!isDeclared(_input.LT(-1).getText())) { 
								throw new QuasarSemanticException("Undeclared Variable: " + _input.LT(-1).getText()); 
							} 
							symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
							leftType = symbolTable.get(_input.LT(-1).getText()).getType();
						
			setState(63);
			match(OP_AT);
			setState(64);
			expr();
			setState(65);
			match(PV);

							System.out.println("Left Side Expression type = " + leftType);
							System.out.println("Right Side Expression type = " + rightType);
							
							if (leftType.getValue() < rightType.getValue()) {
								throw new QuasarSemanticException("Type missmatch on assigment"); 
							}
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(TestGrammarParser.AP, 0); }
		public TerminalNode ID() { return getToken(TestGrammarParser.ID, 0); }
		public TerminalNode FP() { return getToken(TestGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(TestGrammarParser.PV, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__7);
			setState(69);
			match(AP);
			setState(70);
			match(ID);

							 if (!isDeclared(_input.LT(-1).getText())) { 
							 	throw new QuasarSemanticException("Undeclared Variable: " + _input.LT(-1).getText()); 
							 	} 
							 	symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
							 
			setState(72);
			match(FP);
			setState(73);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(TestGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(TestGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(TestGrammarParser.PV, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__8);
			setState(76);
			match(AP);
			{
			setState(77);
			term();
			}
			setState(78);
			match(FP);
			setState(79);
			match(PV);
			 rightType = null; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			term();
			setState(83);
			exprl();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TestGrammarParser.ID, 0); }
		public TerminalNode NUM() { return getToken(TestGrammarParser.NUM, 0); }
		public TerminalNode TEXTO() { return getToken(TestGrammarParser.TEXTO, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		try {
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(ID);
				 
									if (!isDeclared(_input.LT(-1).getText())) 
									{
									 	throw new QuasarSemanticException("Undeclared Variable: " + _input.LT(-1).getText()); 
									} 
									if (!symbolTable.get(_input.LT(-1).getText()).isInitialized()) {
											throw new QuasarSemanticException("Variable: " + _input.LT(-1).getText() + " has no value assigned"); 
									}
									if (rightType == null) {
										rightType = symbolTable.get(_input.LT(-1).getText()).getType();
									}
									else {
										if (symbolTable.get(_input.LT(-1).getText()).getType().getValue() > rightType.getValue()) {
											rightType = symbolTable.get(_input.LT(-1).getText()).getType();
										}
									}
								  
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(NUM);
				 if (rightType == null) {
									rightType = Types.NUMBER;
								  }
								  else {
								  	if (rightType.getValue() < Types.TEXT.getValue()) {
								  		rightType = Types.NUMBER;
								  	}
								  }
								
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				match(TEXTO);
				 if (rightType == null) {
									rightType = Types.TEXT;
								  }
								  else {
								  	if (rightType.getValue() < Types.TEXT.getValue()) {
								  		rightType = Types.TEXT;
								  	}
								  }
								
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlContext extends ParserRuleContext {
		public List<TerminalNode> OP() { return getTokens(TestGrammarParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(TestGrammarParser.OP, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestGrammarListener ) ((TestGrammarListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(93);
				match(OP);
				setState(94);
				term();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0014e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0004\u0000\u0015\b\u0000\u000b\u0000"+
		"\f\u0000\u0016\u0001\u0000\u0001\u0000\u0004\u0000\u001b\b\u0000\u000b"+
		"\u0000\f\u0000\u001c\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001*\b\u0001\n\u0001\f\u0001-\t\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u00014\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002<\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\\\b\u0007\u0001\b\u0001\b\u0005\b`\b\b"+
		"\n\b\f\bc\t\b\u0001\b\u0000\u0000\t\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0000\u0000d\u0000\u0012\u0001\u0000\u0000\u0000\u0002\"\u0001"+
		"\u0000\u0000\u0000\u0004;\u0001\u0000\u0000\u0000\u0006=\u0001\u0000\u0000"+
		"\u0000\bD\u0001\u0000\u0000\u0000\nK\u0001\u0000\u0000\u0000\fR\u0001"+
		"\u0000\u0000\u0000\u000e[\u0001\u0000\u0000\u0000\u0010a\u0001\u0000\u0000"+
		"\u0000\u0012\u0014\u0005\u0001\u0000\u0000\u0013\u0015\u0003\u0002\u0001"+
		"\u0000\u0014\u0013\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000"+
		"\u0000\u0016\u0014\u0001\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000"+
		"\u0000\u0017\u0018\u0001\u0000\u0000\u0000\u0018\u001a\u0005\u0002\u0000"+
		"\u0000\u0019\u001b\u0003\u0004\u0002\u0000\u001a\u0019\u0001\u0000\u0000"+
		"\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c\u001a\u0001\u0000\u0000"+
		"\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000\u0000"+
		"\u0000\u001e\u001f\u0005\u0003\u0000\u0000\u001f \u0005\u0004\u0000\u0000"+
		" !\u0006\u0000\uffff\uffff\u0000!\u0001\u0001\u0000\u0000\u0000\"#\u0005"+
		"\u0005\u0000\u0000#$\u0006\u0001\uffff\uffff\u0000$%\u0005\u000e\u0000"+
		"\u0000%+\u0006\u0001\uffff\uffff\u0000&\'\u0005\u0012\u0000\u0000\'(\u0005"+
		"\u000e\u0000\u0000(*\u0006\u0001\uffff\uffff\u0000)&\u0001\u0000\u0000"+
		"\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000"+
		"\u0000\u0000,.\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000.3\u0005"+
		"\u0011\u0000\u0000/0\u0005\u0006\u0000\u000004\u0006\u0001\uffff\uffff"+
		"\u000012\u0005\u0007\u0000\u000024\u0006\u0001\uffff\uffff\u00003/\u0001"+
		"\u0000\u0000\u000031\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u0000"+
		"56\u0006\u0001\uffff\uffff\u000067\u0005\u0013\u0000\u00007\u0003\u0001"+
		"\u0000\u0000\u00008<\u0003\u0006\u0003\u00009<\u0003\b\u0004\u0000:<\u0003"+
		"\n\u0005\u0000;8\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;:\u0001"+
		"\u0000\u0000\u0000<\u0005\u0001\u0000\u0000\u0000=>\u0005\u000e\u0000"+
		"\u0000>?\u0006\u0003\uffff\uffff\u0000?@\u0005\u0014\u0000\u0000@A\u0003"+
		"\f\u0006\u0000AB\u0005\u0013\u0000\u0000BC\u0006\u0003\uffff\uffff\u0000"+
		"C\u0007\u0001\u0000\u0000\u0000DE\u0005\b\u0000\u0000EF\u0005\u000b\u0000"+
		"\u0000FG\u0005\u000e\u0000\u0000GH\u0006\u0004\uffff\uffff\u0000HI\u0005"+
		"\f\u0000\u0000IJ\u0005\u0013\u0000\u0000J\t\u0001\u0000\u0000\u0000KL"+
		"\u0005\t\u0000\u0000LM\u0005\u000b\u0000\u0000MN\u0003\u000e\u0007\u0000"+
		"NO\u0005\f\u0000\u0000OP\u0005\u0013\u0000\u0000PQ\u0006\u0005\uffff\uffff"+
		"\u0000Q\u000b\u0001\u0000\u0000\u0000RS\u0003\u000e\u0007\u0000ST\u0003"+
		"\u0010\b\u0000T\r\u0001\u0000\u0000\u0000UV\u0005\u000e\u0000\u0000V\\"+
		"\u0006\u0007\uffff\uffff\u0000WX\u0005\u000f\u0000\u0000X\\\u0006\u0007"+
		"\uffff\uffff\u0000YZ\u0005\n\u0000\u0000Z\\\u0006\u0007\uffff\uffff\u0000"+
		"[U\u0001\u0000\u0000\u0000[W\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000"+
		"\u0000\\\u000f\u0001\u0000\u0000\u0000]^\u0005\r\u0000\u0000^`\u0003\u000e"+
		"\u0007\u0000_]\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001"+
		"\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b\u0011\u0001\u0000\u0000"+
		"\u0000ca\u0001\u0000\u0000\u0000\u0007\u0016\u001c+3;[a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}