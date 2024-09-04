// Generated from QuasarGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Stack;
	
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
	import io.compiler.core.ast.expression.*;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		TEXT=10, IDENTIFIER=11, OPERATOR=12, NUMBER=13, REAL_NUMBER=14, WS=15, 
		COMMENT=16, RELATIONAL_OPERATOR=17, START_BLOCK=18, END_BLOCK=19, OPEN_P=20, 
		CLOSE_P=21, END_COMMAND=22, COMMA=23, COLLON=24, VAR=25, ATRIBUITION_OPERATOR=26;
	public static final int
		RULE_program = 0, RULE_command = 1, RULE_while_command = 2, RULE_if_command = 3, 
		RULE_declaration_command = 4, RULE_atribuition_command = 5, RULE_read_command = 6, 
		RULE_write_command = 7, RULE_boolean_expression = 8, RULE_aritmetic_expression = 9, 
		RULE_a_expression_line = 10, RULE_term = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "command", "while_command", "if_command", "declaration_command", 
			"atribuition_command", "read_command", "write_command", "boolean_expression", 
			"aritmetic_expression", "a_expression_line", "term"
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
			setState(24);
			match(START_BLOCK);

					stack.push(new ArrayList<Command>());
				
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				command();
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33557262L) != 0) );
			setState(31);
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
		public If_commandContext if_command() {
			return getRuleContext(If_commandContext.class,0);
		}
		public While_commandContext while_command() {
			return getRuleContext(While_commandContext.class,0);
		}
		public Atribuition_commandContext atribuition_command() {
			return getRuleContext(Atribuition_commandContext.class,0);
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
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
			case T__8:
			case IDENTIFIER:
			case VAR:
				{
				{
				setState(38);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(34);
					atribuition_command();
					}
					break;
				case VAR:
					{
					setState(35);
					declaration_command();
					}
					break;
				case T__7:
					{
					setState(36);
					read_command();
					}
					break;
				case T__8:
					{
					setState(37);
					write_command();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(40);
				match(END_COMMAND);
				}
				}
				break;
			case T__0:
			case T__1:
			case T__2:
				{
				setState(44);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
					{
					setState(42);
					if_command();
					}
					break;
				case T__0:
				case T__1:
					{
					setState(43);
					while_command();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 
					resetTypes();
				
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
	public static class While_commandContext extends ParserRuleContext {
		public TerminalNode OPEN_P() { return getToken(QuasarGrammarParser.OPEN_P, 0); }
		public Boolean_expressionContext boolean_expression() {
			return getRuleContext(Boolean_expressionContext.class,0);
		}
		public TerminalNode CLOSE_P() { return getToken(QuasarGrammarParser.CLOSE_P, 0); }
		public TerminalNode START_BLOCK() { return getToken(QuasarGrammarParser.START_BLOCK, 0); }
		public TerminalNode END_BLOCK() { return getToken(QuasarGrammarParser.END_BLOCK, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public While_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterWhile_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitWhile_command(this);
		}
	}

	public final While_commandContext while_command() throws RecognitionException {
		While_commandContext _localctx = new While_commandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_while_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

				
					stack.push(new ArrayList<Command>());
					blockStack.push(new WhileCommand((_input.LT(-1).getText().equals("while") ? false : true)));
				
			setState(52);
			match(OPEN_P);
			setState(53);
			boolean_expression();
			setState(54);
			match(CLOSE_P);
			setState(55);
			match(START_BLOCK);
			setState(57); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(56);
				command();
				}
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33557262L) != 0) );
			setState(61);
			match(END_BLOCK);

						blockStack.peek().setExpression(expressionStack.pop());
						blockStack.peek().setBlockCommands(stack.pop());
						stack.peek().add(blockStack.pop());
					
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
		public Boolean_expressionContext boolean_expression() {
			return getRuleContext(Boolean_expressionContext.class,0);
		}
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
		enterRule(_localctx, 6, RULE_if_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__2);

					
					stack.push(new ArrayList<Command>());
					blockStack.push(new IfCommand());
				
			setState(66);
			match(OPEN_P);
			setState(67);
			boolean_expression();
			setState(68);
			match(CLOSE_P);
			setState(69);
			match(START_BLOCK);
			setState(71); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(70);
				command();
				}
				}
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33557262L) != 0) );
			setState(75);
			match(END_BLOCK);

					
					blockStack.peek().setExpression(expressionStack.pop());
						
					blockStack.peek().setBlockCommands(stack.pop()); 
					stack.peek().add(blockStack.pop());
				
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(77);
				match(T__3);
				 
						
						System.out.println("else");
						stack.push(new ArrayList<Command>());
						blockStack.push(new ElseCommand());
					
				setState(79);
				match(START_BLOCK);
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(80);
					command();
					}
					}
					setState(83); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33557262L) != 0) );
				setState(85);
				match(END_BLOCK);

							blockStack.push(new ElseCommand());
							blockStack.peek().setBlockCommands(stack.pop());	
							stack.peek().add(blockStack.pop());
						
				}
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
		enterRule(_localctx, 8, RULE_declaration_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(VAR);

					currentDeclaration.clear();
				
			setState(92);
			match(IDENTIFIER);

					currentDeclaration.add(new Var(_input.LT(-1).getText()));
				
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(94);
				match(COMMA);
				setState(95);
				match(IDENTIFIER);

							currentDeclaration.add(new Var(_input.LT(-1).getText())); 
						
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102);
			match(COLLON);
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(103);
				match(T__4);
				 currentType = Types.NUMBER; 
				}
				break;
			case T__5:
				{
				setState(105);
				match(T__5);
				 currentType = Types.TEXT;   
				}
				break;
			case T__6:
				{
				setState(107);
				match(T__6);
				 currentType = Types.REALNUMBER; 
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
		public Aritmetic_expressionContext aritmetic_expression() {
			return getRuleContext(Aritmetic_expressionContext.class,0);
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
		enterRule(_localctx, 10, RULE_atribuition_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(IDENTIFIER);

					setVarInitializated(_input.LT(-1).getText());
					Var v = symbolTable.get(_input.LT(-1).getText());
					currentAttribuitionCommand = new AttribuitionCommand(v);
					leftType = v.getType();
				
			setState(115);
			match(ATRIBUITION_OPERATOR);
			setState(116);
			aritmetic_expression();

					
					System.out.println("Left Side Expression type = " + leftType);
					System.out.println("Right Side Expression type = " + rightType);
					
					// Consider creating a better type hierarchy than < or > for type mismatch
					if (leftType.getValue() != rightType.getValue()) {
						throw new QuasarSemanticException("Type Missmatch on Assigment"); 
					}
					
					currentAttribuitionCommand.setExpression(expressionStack.pop());
					stack.peek().add(currentAttribuitionCommand);
					System.out.println("-----------------------------------------------------------");
				
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
		enterRule(_localctx, 12, RULE_read_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__7);
			setState(120);
			match(OPEN_P);
			setState(121);
			match(IDENTIFIER);

					setVarInitializated(_input.LT(-1).getText());
					Command cmdRead = new ReadCommand( symbolTable.get(_input.LT(-1).getText()));
					stack.peek().add(cmdRead);
				
			setState(123);
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
		enterRule(_localctx, 14, RULE_write_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__8);
			setState(126);
			match(OPEN_P);
			{
			setState(127);
			term();

					 	Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
					 	stack.peek().add(cmdWrite);
					 
			}
			setState(130);
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
	public static class Boolean_expressionContext extends ParserRuleContext {
		public TerminalNode RELATIONAL_OPERATOR() { return getToken(QuasarGrammarParser.RELATIONAL_OPERATOR, 0); }
		public List<Aritmetic_expressionContext> aritmetic_expression() {
			return getRuleContexts(Aritmetic_expressionContext.class);
		}
		public Aritmetic_expressionContext aritmetic_expression(int i) {
			return getRuleContext(Aritmetic_expressionContext.class,i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Boolean_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterBoolean_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitBoolean_expression(this);
		}
	}

	public final Boolean_expressionContext boolean_expression() throws RecognitionException {
		Boolean_expressionContext _localctx = new Boolean_expressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_boolean_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{

					expressionStack.push(new BooleanExpressionCommand());
				
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(133);
				aritmetic_expression();
				 
							ExpressionCommand command = expressionStack.pop();
							expressionStack.peek().addExpression(command);
						
				}
				break;
			case 2:
				{
				setState(136);
				term();
				 
							addExpressionTerm(_input.LT(-1).getText()); 
						
				}
				break;
			}
			setState(141);
			match(RELATIONAL_OPERATOR);
			 addExpressionOperator(_input.LT(-1).getText()); 
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(143);
				aritmetic_expression();

							ExpressionCommand command = expressionStack.pop();
							expressionStack.peek().addExpression(command);
					
				}
				break;
			case 2:
				{
				setState(146);
				term();
				 
							addExpressionTerm(_input.LT(-1).getText()); 
						
				}
				break;
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
	public static class Aritmetic_expressionContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public A_expression_lineContext a_expression_line() {
			return getRuleContext(A_expression_lineContext.class,0);
		}
		public Aritmetic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aritmetic_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterAritmetic_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitAritmetic_expression(this);
		}
	}

	public final Aritmetic_expressionContext aritmetic_expression() throws RecognitionException {
		Aritmetic_expressionContext _localctx = new Aritmetic_expressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_aritmetic_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			term();
			 
					expressionStack.push(new AritmeticExpressionCommand(_input.LT(-1).getText()));
				
			setState(153);
			a_expression_line();
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
	public static class A_expression_lineContext extends ParserRuleContext {
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
		public A_expression_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a_expression_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).enterA_expression_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuasarGrammarListener ) ((QuasarGrammarListener)listener).exitA_expression_line(this);
		}
	}

	public final A_expression_lineContext a_expression_line() throws RecognitionException {
		A_expression_lineContext _localctx = new A_expression_lineContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_a_expression_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPERATOR) {
				{
				{
				setState(155);
				match(OPERATOR);
				 addExpressionOperator(_input.LT(-1).getText()); 
				setState(157);
				term();
				 addExpressionTerm(_input.LT(-1).getText()); 
				}
				}
				setState(164);
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
		public TerminalNode REAL_NUMBER() { return getToken(QuasarGrammarParser.REAL_NUMBER, 0); }
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
		enterRule(_localctx, 22, RULE_term);
		try {
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				match(IDENTIFIER);
				 setTypeIdentifier( _input.LT(-1).getText()); System.out.println(_input.LT(-1).getText()); 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				match(NUMBER);
				 setType(Types.NUMBER); System.out.println(_input.LT(-1).getText());
				}
				break;
			case REAL_NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(169);
				match(REAL_NUMBER);
				 setType(Types.REALNUMBER);System.out.println(_input.LT(-1).getText()); 
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(171);
				match(TEXT);
				 setType(Types.TEXT); System.out.println(_input.LT(-1).getText());
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
		"\u0004\u0001\u001a\u00b0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u001c\b\u0000\u000b\u0000"+
		"\f\u0000\u001d\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\'\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001-\b\u0001\u0003\u0001/\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0004\u0002:\b\u0002\u000b\u0002\f\u0002"+
		";\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003H\b\u0003"+
		"\u000b\u0003\f\u0003I\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0004\u0003R\b\u0003\u000b\u0003\f\u0003S\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003Y\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004b\b\u0004\n\u0004\f\u0004e\t\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004n\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u008c\b\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0096\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00a1\b\n\n"+
		"\n\f\n\u00a4\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00ae\b\u000b\u0001"+
		"\u000b\u0000\u0000\f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0000\u0001\u0001\u0000\u0001\u0002\u00b6\u0000\u0018\u0001\u0000"+
		"\u0000\u0000\u0002.\u0001\u0000\u0000\u0000\u00042\u0001\u0000\u0000\u0000"+
		"\u0006@\u0001\u0000\u0000\u0000\bZ\u0001\u0000\u0000\u0000\nq\u0001\u0000"+
		"\u0000\u0000\fw\u0001\u0000\u0000\u0000\u000e}\u0001\u0000\u0000\u0000"+
		"\u0010\u0084\u0001\u0000\u0000\u0000\u0012\u0097\u0001\u0000\u0000\u0000"+
		"\u0014\u00a2\u0001\u0000\u0000\u0000\u0016\u00ad\u0001\u0000\u0000\u0000"+
		"\u0018\u0019\u0005\u0012\u0000\u0000\u0019\u001b\u0006\u0000\uffff\uffff"+
		"\u0000\u001a\u001c\u0003\u0002\u0001\u0000\u001b\u001a\u0001\u0000\u0000"+
		"\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000"+
		"\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000"+
		"\u0000\u001f \u0005\u0013\u0000\u0000 !\u0006\u0000\uffff\uffff\u0000"+
		"!\u0001\u0001\u0000\u0000\u0000\"\'\u0003\n\u0005\u0000#\'\u0003\b\u0004"+
		"\u0000$\'\u0003\f\u0006\u0000%\'\u0003\u000e\u0007\u0000&\"\u0001\u0000"+
		"\u0000\u0000&#\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000&%\u0001"+
		"\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0005\u0016\u0000\u0000"+
		")/\u0001\u0000\u0000\u0000*-\u0003\u0006\u0003\u0000+-\u0003\u0004\u0002"+
		"\u0000,*\u0001\u0000\u0000\u0000,+\u0001\u0000\u0000\u0000-/\u0001\u0000"+
		"\u0000\u0000.&\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000/0\u0001"+
		"\u0000\u0000\u000001\u0006\u0001\uffff\uffff\u00001\u0003\u0001\u0000"+
		"\u0000\u000023\u0007\u0000\u0000\u000034\u0006\u0002\uffff\uffff\u0000"+
		"45\u0005\u0014\u0000\u000056\u0003\u0010\b\u000067\u0005\u0015\u0000\u0000"+
		"79\u0005\u0012\u0000\u00008:\u0003\u0002\u0001\u000098\u0001\u0000\u0000"+
		"\u0000:;\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000"+
		"\u0000\u0000<=\u0001\u0000\u0000\u0000=>\u0005\u0013\u0000\u0000>?\u0006"+
		"\u0002\uffff\uffff\u0000?\u0005\u0001\u0000\u0000\u0000@A\u0005\u0003"+
		"\u0000\u0000AB\u0006\u0003\uffff\uffff\u0000BC\u0005\u0014\u0000\u0000"+
		"CD\u0003\u0010\b\u0000DE\u0005\u0015\u0000\u0000EG\u0005\u0012\u0000\u0000"+
		"FH\u0003\u0002\u0001\u0000GF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000"+
		"\u0000IG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JK\u0001\u0000"+
		"\u0000\u0000KL\u0005\u0013\u0000\u0000LX\u0006\u0003\uffff\uffff\u0000"+
		"MN\u0005\u0004\u0000\u0000NO\u0006\u0003\uffff\uffff\u0000OQ\u0005\u0012"+
		"\u0000\u0000PR\u0003\u0002\u0001\u0000QP\u0001\u0000\u0000\u0000RS\u0001"+
		"\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000"+
		"TU\u0001\u0000\u0000\u0000UV\u0005\u0013\u0000\u0000VW\u0006\u0003\uffff"+
		"\uffff\u0000WY\u0001\u0000\u0000\u0000XM\u0001\u0000\u0000\u0000XY\u0001"+
		"\u0000\u0000\u0000Y\u0007\u0001\u0000\u0000\u0000Z[\u0005\u0019\u0000"+
		"\u0000[\\\u0006\u0004\uffff\uffff\u0000\\]\u0005\u000b\u0000\u0000]c\u0006"+
		"\u0004\uffff\uffff\u0000^_\u0005\u0017\u0000\u0000_`\u0005\u000b\u0000"+
		"\u0000`b\u0006\u0004\uffff\uffff\u0000a^\u0001\u0000\u0000\u0000be\u0001"+
		"\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000"+
		"df\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000fm\u0005\u0018\u0000"+
		"\u0000gh\u0005\u0005\u0000\u0000hn\u0006\u0004\uffff\uffff\u0000ij\u0005"+
		"\u0006\u0000\u0000jn\u0006\u0004\uffff\uffff\u0000kl\u0005\u0007\u0000"+
		"\u0000ln\u0006\u0004\uffff\uffff\u0000mg\u0001\u0000\u0000\u0000mi\u0001"+
		"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000"+
		"op\u0006\u0004\uffff\uffff\u0000p\t\u0001\u0000\u0000\u0000qr\u0005\u000b"+
		"\u0000\u0000rs\u0006\u0005\uffff\uffff\u0000st\u0005\u001a\u0000\u0000"+
		"tu\u0003\u0012\t\u0000uv\u0006\u0005\uffff\uffff\u0000v\u000b\u0001\u0000"+
		"\u0000\u0000wx\u0005\b\u0000\u0000xy\u0005\u0014\u0000\u0000yz\u0005\u000b"+
		"\u0000\u0000z{\u0006\u0006\uffff\uffff\u0000{|\u0005\u0015\u0000\u0000"+
		"|\r\u0001\u0000\u0000\u0000}~\u0005\t\u0000\u0000~\u007f\u0005\u0014\u0000"+
		"\u0000\u007f\u0080\u0003\u0016\u000b\u0000\u0080\u0081\u0006\u0007\uffff"+
		"\uffff\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0015"+
		"\u0000\u0000\u0083\u000f\u0001\u0000\u0000\u0000\u0084\u008b\u0006\b\uffff"+
		"\uffff\u0000\u0085\u0086\u0003\u0012\t\u0000\u0086\u0087\u0006\b\uffff"+
		"\uffff\u0000\u0087\u008c\u0001\u0000\u0000\u0000\u0088\u0089\u0003\u0016"+
		"\u000b\u0000\u0089\u008a\u0006\b\uffff\uffff\u0000\u008a\u008c\u0001\u0000"+
		"\u0000\u0000\u008b\u0085\u0001\u0000\u0000\u0000\u008b\u0088\u0001\u0000"+
		"\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008e\u0005\u0011"+
		"\u0000\u0000\u008e\u0095\u0006\b\uffff\uffff\u0000\u008f\u0090\u0003\u0012"+
		"\t\u0000\u0090\u0091\u0006\b\uffff\uffff\u0000\u0091\u0096\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0003\u0016\u000b\u0000\u0093\u0094\u0006\b\uffff"+
		"\uffff\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u008f\u0001\u0000"+
		"\u0000\u0000\u0095\u0092\u0001\u0000\u0000\u0000\u0096\u0011\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0003\u0016\u000b\u0000\u0098\u0099\u0006\t\uffff"+
		"\uffff\u0000\u0099\u009a\u0003\u0014\n\u0000\u009a\u0013\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0005\f\u0000\u0000\u009c\u009d\u0006\n\uffff\uffff"+
		"\u0000\u009d\u009e\u0003\u0016\u000b\u0000\u009e\u009f\u0006\n\uffff\uffff"+
		"\u0000\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u009b\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a4\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u0015\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005\u000b\u0000"+
		"\u0000\u00a6\u00ae\u0006\u000b\uffff\uffff\u0000\u00a7\u00a8\u0005\r\u0000"+
		"\u0000\u00a8\u00ae\u0006\u000b\uffff\uffff\u0000\u00a9\u00aa\u0005\u000e"+
		"\u0000\u0000\u00aa\u00ae\u0006\u000b\uffff\uffff\u0000\u00ab\u00ac\u0005"+
		"\n\u0000\u0000\u00ac\u00ae\u0006\u000b\uffff\uffff\u0000\u00ad\u00a5\u0001"+
		"\u0000\u0000\u0000\u00ad\u00a7\u0001\u0000\u0000\u0000\u00ad\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u0017\u0001"+
		"\u0000\u0000\u0000\u000e\u001d&,.;ISXcm\u008b\u0095\u00a2\u00ad";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}