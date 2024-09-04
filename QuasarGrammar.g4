grammar QuasarGrammar;

@header {
	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Stack;
	
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
	import io.compiler.core.ast.expression.*;
}

@members {
	
	private HashMap<String, Var> symbolTable = new HashMap<String, Var>();
	private ArrayList<Var> currentDeclaration = new ArrayList<Var>();
	private Program program = new Program();
	
	private Stack<ArrayList<Command>> stack 		 = new Stack<ArrayList<Command>>();
	private Stack<IfCommand> ifStack 				 = new Stack<IfCommand>();
	private Stack<WhileCommand> loopStack 			 = new Stack<WhileCommand>();
	
	private Stack<ExpressionCommand> expressionStack = new Stack<ExpressionCommand>();
	
	private IfCommand currentIfCommand;
	private WhileCommand currentWhileCommand;
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
		
			if (rightType.getValue() < type.getValue()) {
				rightType = type;
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
}

//* -------- ------------------  --------*//
//* --------     Derivations     --------*//
//* -------- ------------------  --------*//

program				:
	START_BLOCK {
		stack.push(new ArrayList<Command>());
	}
		command+
	END_BLOCK {
		program.setSymbolTable(symbolTable);
		program.setCommandList(stack.pop());
	}
					;

command				:
	((( 
		atribuition_command |
		declaration_command | 
		read_command 		|
		write_command 
	) END_COMMAND
	)	
	|
	(
		if_command			|
		while_command		
	))
	{ 
		resetTypes();
	}			
					;

//* --------     Commands       -------- *//

while_command		:
	('while'|'do:while') {
	
	
		stack.push(new ArrayList<Command>());
		loopStack.push(new WhileCommand((_input.LT(-1).getText().equals("while") ? false : true)));
	}
		OPEN_P
			boolean_expression
		CLOSE_P
		START_BLOCK
			command+
		END_BLOCK {
			loopStack.peek().setExpression(expressionStack.pop());
			loopStack.peek().setLoopCommands(stack.pop());
			stack.peek().add(loopStack.pop());
		}
					;

if_command			:
	'if' {
		stack.push(new ArrayList<Command>());
		ifStack.push(new IfCommand());
		
	}
	OPEN_P 
		boolean_expression
	CLOSE_P
	START_BLOCK
		command+
	END_BLOCK {
		ifStack.peek().setExpression(expressionStack.pop());
		ifStack.peek().setTrueList(stack.pop()); 
	}
	(		
	'else' { 
		stack.push(new ArrayList<Command>());
	}
		START_BLOCK
			command+
		END_BLOCK {
			ifStack.peek().setFalseList(stack.pop());
		}
	)? {
		stack.peek().add(ifStack.pop());
	}
		
					;

declaration_command :  
	VAR {
		currentDeclaration.clear();
	}
	IDENTIFIER {
		currentDeclaration.add(new Var(_input.LT(-1).getText()));
	}
	( 
		COMMA 
		IDENTIFIER  {
			currentDeclaration.add(new Var(_input.LT(-1).getText())); 
		}
	)* 
	COLLON 
	(
		'number'  	{ currentType = Types.NUMBER; } | 
		'text'		{ currentType = Types.TEXT; }
	) {
		updateType();
	}
					;

atribuition_command	:
	IDENTIFIER {
		setVarInitializated(_input.LT(-1).getText());
		Var v = symbolTable.get(_input.LT(-1).getText());
		currentAttribuitionCommand = new AttribuitionCommand(v);
		leftType = v.getType();
	}
	ATRIBUITION_OPERATOR 
	aritmetic_expression {
		
		System.out.println("Left Side Expression type = " + leftType);
		System.out.println("Right Side Expression type = " + rightType);
		
		// Consider creating a better type hierarchy than < or > for type mismatch
		if (leftType.getValue() != rightType.getValue()) {
			throw new QuasarSemanticException("Type Missmatch on Assigment"); 
		}
		
		currentAttribuitionCommand.setExpression(expressionStack.pop());
		stack.peek().add(currentAttribuitionCommand);
	}
					;

read_command		:
	'read' OPEN_P IDENTIFIER {
		setVarInitializated(_input.LT(-1).getText());
		Command cmdRead = new ReadCommand( symbolTable.get(_input.LT(-1).getText()));
		stack.peek().add(cmdRead);
	}
	CLOSE_P
					;
					
write_command		:
	'write' OPEN_P (
		 term {
		 	Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
		 	stack.peek().add(cmdWrite);
		 }
	) 
	CLOSE_P
					;
					

boolean_expression				: {
		expressionStack.push(new BooleanExpressionCommand());
	}
	(
		aritmetic_expression 		{ 
			ExpressionCommand command = expressionStack.pop();
			expressionStack.peek().addExpression(command);
		}
		| 
		term { 
			addExpressionTerm(_input.LT(-1).getText()); 
		}
	)
	RELATIONAL_OPERATOR  { addExpressionOperator(_input.LT(-1).getText()); }
	(
		aritmetic_expression		{
			ExpressionCommand command = expressionStack.pop();
			expressionStack.peek().addExpression(command);
	} 
	|
		term { 
			addExpressionTerm(_input.LT(-1).getText()); 
		}
	)
								;
		
aritmetic_expression			:
	term { 
		expressionStack.push(new AritmeticExpressionCommand(_input.LT(-1).getText()));
	} 
	a_expression_line	
								;

a_expression_line				:
	( OPERATOR { addExpressionOperator(_input.LT(-1).getText()); } term { addExpressionTerm(_input.LT(-1).getText()); } )*
								;

term				:
	IDENTIFIER		{ setTypeIdentifier( _input.LT(-1).getText());  }  | 
	NUMBER			{ setType(Types.NUMBER);}						   | 
	TEXT			{ setType(Types.TEXT); }
					;	

TEXT				:  '"' ([a-z] | [A-Z] | [0-9] | ' ')* '"'  
					;

IDENTIFIER			: ([a-z] | [A-Z]) ([a-z] | [A-Z] | [0-9])*
					;
					
OPERATOR			: '+' | '-' | '/' | '*' 
					;

NUMBER				: [0-9]+ ('.'[0-9]+)?
					;

WS					: (' '| '\n' | '\r' | '\t') -> skip	
					;
					
RELATIONAL_OPERATOR : '>' | '<' | '>=' | '<=' | '><' | '=='
					; 

//* -------- ------------------  --------*//
//* -------- TERMINAL CHARACTER  --------*//
//* -------- ------------------  --------*//

START_BLOCK			: '{' 		;
END_BLOCK			: '}' 		;

OPEN_P				: '('		;
CLOSE_P				: ')'		;


END_COMMAND			: ';' 		;
COMMA				: ','   	;
COLLON				: ':'   	;

VAR 				: '-var'	;

ATRIBUITION_OPERATOR: '->'		;