// Generated from QuasarGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Stack;
	
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class QuasarGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, TEXT=7, IDENTIFIER=8, 
		OPERATOR=9, NUMBER=10, WS=11, RELATIONAL_OPERATOR=12, START_BLOCK=13, 
		END_BLOCK=14, OPEN_P=15, CLOSE_P=16, END_COMMAND=17, COMMA=18, COLLON=19, 
		VAR=20, ATRIBUITION_OPERATOR=21;
	public static final int
		RULE_program = 0, RULE_command = 1, RULE_if_command = 2, RULE_declaration_command = 3, 
		RULE_atribuition_command = 4, RULE_read_command = 5, RULE_write_command = 6, 
		RULE_expression = 7, RULE_expression_line = 8, RULE_term = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "command", "if_command", "declaration_command", "atribuition_command", 
			"read_command", "write_command", "expression", "expression_line", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'number'", "'text'", "'read'", "'write'", null, 
			null, null, null, null, null, "'{'", "'}'", "'('", "')'", "';'", "','", 
			"':'", "'-var'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "TEXT", "IDENTIFIER", "OPERATOR", 
			"NUMBER", "WS", "RELATIONAL_OPERATOR", "START_BLOCK", "END_BLOCK", "OPEN_P", 
			"CLOSE_P", "END_COMMAND", "COMMA", "COLLON", "VAR", "ATRIBUITION_OPERATOR"
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
	public String getGrammarFileName() { return "QuasarGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		
		private HashMap<String, Var> symbolTable = new HashMap<String, Var>();
		private ArrayList<Var> currentDeclaration = new ArrayList<Var>();
		private Program program = new Program();
		private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();
		private String strExpr = "";
		private IfCommand currentIfCommand;
		
		
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

	public QuasarGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode START_BLOCK() { return getToken(QuasarGrammarParser.START_BLOCK, 0); }
		public TerminalNode END_BLOCK() { return getToken(QuasarGrammarParser.END_BLOCK, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(START_BLOCK);

					stack.push(new ArrayList<Command>());
				
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(22);
				command();
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1048930L) != 0) );
			setState(27);
			match(END_BLOCK);

					program.setSymbolTable(symbolTable);
					program.setCommandList(stack.pop());
				
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
	public static class CommandContext extends ParserRuleContext {
		public TerminalNode END_COMMAND() { return getToken(QuasarGrammarParser.END_COMMAND, 0); }
		public Atribuition_commandContext atribuition_command() {
			return getRuleContext(Atribuition_commandContext.class,0);
		}
		public If_commandContext if_command() {
			return getRuleContext(If_commandContext.class,0);
		}
		public Declaration_commandContext declaration_command() {
			return getRuleContext(Declaration_commandContext.class,0);
		}
		public Read_commandContext read_command() {
			return getRuleContext(Read_commandContext.class,0);
		}
		public Write_commandContext write_command() {
			return getRuleContext(Write_commandContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(30);
				atribuition_command();
				}
				break;
			case T__0:
				{
				setState(31);
				if_command();
				}
				break;
			case VAR:
				{
				setState(32);
				declaration_command();
				}
				break;
			case T__4:
				{
				setState(33);
				read_command();
				}
				break;
			case T__5:
				{
				setState(34);
				write_command();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(37);
			match(END_COMMAND);
			 
					leftType = null; 
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
	public static class If_commandContext extends ParserRuleContext {
		public TerminalNode OPEN_P() { return getToken(QuasarGrammarParser.OPEN_P, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RELATIONAL_OPERATOR() { return getToken(QuasarGrammarParser.RELATIONAL_OPERATOR, 0); }
		public TerminalNode CLOSE_P() { return getToken(QuasarGrammarParser.CLOSE_P, 0); }
		public List<TerminalNode> START_BLOCK() { return getTokens(QuasarGrammarParser.START_BLOCK); }
		public TerminalNode START_BLOCK(int i) {
			return getToken(QuasarGrammarParser.START_BLOCK, i);
		}
		public List<TerminalNode> END_BLOCK() { return getTokens(QuasarGrammarParser.END_BLOCK); }
		public TerminalNode END_BLOCK(int i) {
			return getToken(QuasarGrammarParser.END_BLOCK, i);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public If_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterIf_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitIf_command(this);
		}
	}

	public final If_commandContext if_command() throws RecognitionException {
		If_commandContext _localctx = new If_commandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_if_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__0);

					stack.push(new ArrayList<Command>());
					strExpr = "";
					currentIfCommand = new IfCommand();
				
			setState(42);
			match(OPEN_P);
			setState(43);
			expression();
			setState(44);
			match(RELATIONAL_OPERATOR);
			 strExpr += _input.LT(-1).getText(); 
			setState(46);
			expression();
			setState(47);
			match(CLOSE_P);
			setState(48);
			match(START_BLOCK);
			setState(50); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				command();
				}
				}
				setState(52); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1048930L) != 0) );
			setState(54);
			match(END_BLOCK);

					//change this should have a target generation to Expressions
					currentIfCommand.setExpression(strExpr);
					currentIfCommand.setTrueList(stack.pop()); 
				
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(56);
				match(T__1);
				 
						stack.push(new ArrayList<Command>());
					
				setState(58);
				match(START_BLOCK);
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(59);
					command();
					}
					}
					setState(62); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1048930L) != 0) );
				setState(64);
				match(END_BLOCK);

							currentIfCommand.setFalseList(stack.pop());
						
				}
			}


					stack.peek().add(currentIfCommand);
				
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
	public static class Declaration_commandContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(QuasarGrammarParser.VAR, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(QuasarGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(QuasarGrammarParser.IDENTIFIER, i);
		}
		public TerminalNode COLLON() { return getToken(QuasarGrammarParser.COLLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(QuasarGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QuasarGrammarParser.COMMA, i);
		}
		public Declaration_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterDeclaration_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitDeclaration_command(this);
		}
	}

	public final Declaration_commandContext declaration_command() throws RecognitionException {
		Declaration_commandContext _localctx = new Declaration_commandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(VAR);

					currentDeclaration.clear();
				
			setState(73);
			match(IDENTIFIER);

					currentDeclaration.add(new Var(_input.LT(-1).getText()));
				
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(75);
				match(COMMA);
				setState(76);
				match(IDENTIFIER);

							currentDeclaration.add(new Var(_input.LT(-1).getText())); 
						
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(83);
			match(COLLON);
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(84);
				match(T__2);
				 currentType = Types.NUMBER; 
				}
				break;
			case T__3:
				{
				setState(86);
				match(T__3);
				 currentType = Types.TEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

					updateType();
				
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
	public static class Atribuition_commandContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QuasarGrammarParser.IDENTIFIER, 0); }
		public TerminalNode ATRIBUITION_OPERATOR() { return getToken(QuasarGrammarParser.ATRIBUITION_OPERATOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Atribuition_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atribuition_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterAtribuition_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitAtribuition_command(this);
		}
	}

	public final Atribuition_commandContext atribuition_command() throws RecognitionException {
		Atribuition_commandContext _localctx = new Atribuition_commandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atribuition_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(IDENTIFIER);

					setVarInitializated(_input.LT(-1).getText());
					leftType = symbolTable.get(_input.LT(-1).getText()).getType();
				
			setState(94);
			match(ATRIBUITION_OPERATOR);
			setState(95);
			expression();

					
					System.out.println("Left Side Expression type = " + leftType);
					System.out.println("Right Side Expression type = " + rightType);
				
					if (leftType.getValue() < rightType.getValue()) {
						throw new QuasarSemanticException("Type Missmatch on Assigment"); 
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
	public static class Read_commandContext extends ParserRuleContext {
		public TerminalNode OPEN_P() { return getToken(QuasarGrammarParser.OPEN_P, 0); }
		public TerminalNode IDENTIFIER() { return getToken(QuasarGrammarParser.IDENTIFIER, 0); }
		public TerminalNode CLOSE_P() { return getToken(QuasarGrammarParser.CLOSE_P, 0); }
		public Read_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterRead_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitRead_command(this);
		}
	}

	public final Read_commandContext read_command() throws RecognitionException {
		Read_commandContext _localctx = new Read_commandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_read_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__4);
			setState(99);
			match(OPEN_P);
			setState(100);
			match(IDENTIFIER);

					setVarInitializated(_input.LT(-1).getText());
					Command cmdRead = new ReadCommand( symbolTable.get(_input.LT(-1).getText()));
					stack.peek().add(cmdRead);
				
			setState(102);
			match(CLOSE_P);
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
	public static class Write_commandContext extends ParserRuleContext {
		public TerminalNode OPEN_P() { return getToken(QuasarGrammarParser.OPEN_P, 0); }
		public TerminalNode CLOSE_P() { return getToken(QuasarGrammarParser.CLOSE_P, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Write_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterWrite_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitWrite_command(this);
		}
	}

	public final Write_commandContext write_command() throws RecognitionException {
		Write_commandContext _localctx = new Write_commandContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_write_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__5);
			setState(105);
			match(OPEN_P);
			{
			setState(106);
			term();

					 	Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
					 	stack.peek().add(cmdWrite);
					 
			}
			setState(109);
			match(CLOSE_P);
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
	public static class ExpressionContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Expression_lineContext expression_line() {
			return getRuleContext(Expression_lineContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			term();
			 strExpr += _input.LT(-1).getText(); 
			setState(113);
			expression_line();
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
	public static class Expression_lineContext extends ParserRuleContext {
		public List<TerminalNode> OPERATOR() { return getTokens(QuasarGrammarParser.OPERATOR); }
		public TerminalNode OPERATOR(int i) {
			return getToken(QuasarGrammarParser.OPERATOR, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Expression_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterExpression_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitExpression_line(this);
		}
	}

	public final Expression_lineContext expression_line() throws RecognitionException {
		Expression_lineContext _localctx = new Expression_lineContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPERATOR) {
				{
				{
				setState(115);
				match(OPERATOR);
				 strExpr += _input.LT(-1).getText(); 
				setState(117);
				term();
				 strExpr += _input.LT(-1).getText(); 
				}
				}
				setState(124);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(QuasarGrammarParser.IDENTIFIER, 0); }
		public TerminalNode NUMBER() { return getToken(QuasarGrammarParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(QuasarGrammarParser.TEXT, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_term);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				match(IDENTIFIER);
				 setTypeIdentifier( _input.LT(-1).getText()); 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				match(NUMBER);
				 setType(Types.NUMBER); 
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
				match(TEXT);
				 setType(Types.TEXT);   
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

	public static final String _serializedATN =
		"\u0004\u0001\u0015\u0086\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0001\u0000\u0004"+
		"\u0000\u0018\b\u0000\u000b\u0000\f\u0000\u0019\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001$\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u00023\b\u0002\u000b\u0002"+
		"\f\u00024\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0004\u0002=\b\u0002\u000b\u0002\f\u0002>\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002D\b\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003O\b\u0003\n\u0003\f\u0003R\t\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003Y\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\by\b\b\n\b\f\b|\t"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0084\b\t\u0001"+
		"\t\u0000\u0000\n\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0000"+
		"\u0000\u0088\u0000\u0014\u0001\u0000\u0000\u0000\u0002#\u0001\u0000\u0000"+
		"\u0000\u0004(\u0001\u0000\u0000\u0000\u0006G\u0001\u0000\u0000\u0000\b"+
		"\\\u0001\u0000\u0000\u0000\nb\u0001\u0000\u0000\u0000\fh\u0001\u0000\u0000"+
		"\u0000\u000eo\u0001\u0000\u0000\u0000\u0010z\u0001\u0000\u0000\u0000\u0012"+
		"\u0083\u0001\u0000\u0000\u0000\u0014\u0015\u0005\r\u0000\u0000\u0015\u0017"+
		"\u0006\u0000\uffff\uffff\u0000\u0016\u0018\u0003\u0002\u0001\u0000\u0017"+
		"\u0016\u0001\u0000\u0000\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019"+
		"\u0017\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a"+
		"\u001b\u0001\u0000\u0000\u0000\u001b\u001c\u0005\u000e\u0000\u0000\u001c"+
		"\u001d\u0006\u0000\uffff\uffff\u0000\u001d\u0001\u0001\u0000\u0000\u0000"+
		"\u001e$\u0003\b\u0004\u0000\u001f$\u0003\u0004\u0002\u0000 $\u0003\u0006"+
		"\u0003\u0000!$\u0003\n\u0005\u0000\"$\u0003\f\u0006\u0000#\u001e\u0001"+
		"\u0000\u0000\u0000#\u001f\u0001\u0000\u0000\u0000# \u0001\u0000\u0000"+
		"\u0000#!\u0001\u0000\u0000\u0000#\"\u0001\u0000\u0000\u0000$%\u0001\u0000"+
		"\u0000\u0000%&\u0005\u0011\u0000\u0000&\'\u0006\u0001\uffff\uffff\u0000"+
		"\'\u0003\u0001\u0000\u0000\u0000()\u0005\u0001\u0000\u0000)*\u0006\u0002"+
		"\uffff\uffff\u0000*+\u0005\u000f\u0000\u0000+,\u0003\u000e\u0007\u0000"+
		",-\u0005\f\u0000\u0000-.\u0006\u0002\uffff\uffff\u0000./\u0003\u000e\u0007"+
		"\u0000/0\u0005\u0010\u0000\u000002\u0005\r\u0000\u000013\u0003\u0002\u0001"+
		"\u000021\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u000042\u0001\u0000"+
		"\u0000\u000045\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000067\u0005"+
		"\u000e\u0000\u00007C\u0006\u0002\uffff\uffff\u000089\u0005\u0002\u0000"+
		"\u00009:\u0006\u0002\uffff\uffff\u0000:<\u0005\r\u0000\u0000;=\u0003\u0002"+
		"\u0001\u0000<;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000><\u0001"+
		"\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000"+
		"@A\u0005\u000e\u0000\u0000AB\u0006\u0002\uffff\uffff\u0000BD\u0001\u0000"+
		"\u0000\u0000C8\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0001"+
		"\u0000\u0000\u0000EF\u0006\u0002\uffff\uffff\u0000F\u0005\u0001\u0000"+
		"\u0000\u0000GH\u0005\u0014\u0000\u0000HI\u0006\u0003\uffff\uffff\u0000"+
		"IJ\u0005\b\u0000\u0000JP\u0006\u0003\uffff\uffff\u0000KL\u0005\u0012\u0000"+
		"\u0000LM\u0005\b\u0000\u0000MO\u0006\u0003\uffff\uffff\u0000NK\u0001\u0000"+
		"\u0000\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001"+
		"\u0000\u0000\u0000QS\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000"+
		"SX\u0005\u0013\u0000\u0000TU\u0005\u0003\u0000\u0000UY\u0006\u0003\uffff"+
		"\uffff\u0000VW\u0005\u0004\u0000\u0000WY\u0006\u0003\uffff\uffff\u0000"+
		"XT\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000"+
		"\u0000Z[\u0006\u0003\uffff\uffff\u0000[\u0007\u0001\u0000\u0000\u0000"+
		"\\]\u0005\b\u0000\u0000]^\u0006\u0004\uffff\uffff\u0000^_\u0005\u0015"+
		"\u0000\u0000_`\u0003\u000e\u0007\u0000`a\u0006\u0004\uffff\uffff\u0000"+
		"a\t\u0001\u0000\u0000\u0000bc\u0005\u0005\u0000\u0000cd\u0005\u000f\u0000"+
		"\u0000de\u0005\b\u0000\u0000ef\u0006\u0005\uffff\uffff\u0000fg\u0005\u0010"+
		"\u0000\u0000g\u000b\u0001\u0000\u0000\u0000hi\u0005\u0006\u0000\u0000"+
		"ij\u0005\u000f\u0000\u0000jk\u0003\u0012\t\u0000kl\u0006\u0006\uffff\uffff"+
		"\u0000lm\u0001\u0000\u0000\u0000mn\u0005\u0010\u0000\u0000n\r\u0001\u0000"+
		"\u0000\u0000op\u0003\u0012\t\u0000pq\u0006\u0007\uffff\uffff\u0000qr\u0003"+
		"\u0010\b\u0000r\u000f\u0001\u0000\u0000\u0000st\u0005\t\u0000\u0000tu"+
		"\u0006\b\uffff\uffff\u0000uv\u0003\u0012\t\u0000vw\u0006\b\uffff\uffff"+
		"\u0000wy\u0001\u0000\u0000\u0000xs\u0001\u0000\u0000\u0000y|\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{\u0011"+
		"\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}~\u0005\b\u0000\u0000"+
		"~\u0084\u0006\t\uffff\uffff\u0000\u007f\u0080\u0005\n\u0000\u0000\u0080"+
		"\u0084\u0006\t\uffff\uffff\u0000\u0081\u0082\u0005\u0007\u0000\u0000\u0082"+
		"\u0084\u0006\t\uffff\uffff\u0000\u0083}\u0001\u0000\u0000\u0000\u0083"+
		"\u007f\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0084"+
		"\u0013\u0001\u0000\u0000\u0000\t\u0019#4>CPXz\u0083";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}