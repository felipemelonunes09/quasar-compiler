grammar QuasarGrammar;

@header {
	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Stack;
	
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
	
}
@members {
	
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
	( 
		atribuition_command |
		if_command			| 
		declaration_command | 
		read_command 		|
		write_command 
	) END_COMMAND 
	{ 
		leftType = null; 
		rightType = null; 
	}			
					;

//* --------     Commands       -------- *//

if_command			:
	'if' {
		stack.push(new ArrayList<Command>());
		strExpr = "";
		currentIfCommand = new IfCommand();
	}
	OPEN_P 
		expression 
			RELATIONAL_OPERATOR  { strExpr += _input.LT(-1).getText(); }
		expression 
	CLOSE_P
	START_BLOCK
		command+
	END_BLOCK {
		//change this should have a target generation to Expressions
		currentIfCommand.setExpression(strExpr);
		currentIfCommand.setTrueList(stack.pop()); 
	}
	(		
	'else' { 
		stack.push(new ArrayList<Command>());
	}
		START_BLOCK
			command+
		END_BLOCK {
			currentIfCommand.setFalseList(stack.pop());
		}
	)? {
		stack.peek().add(currentIfCommand);
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

atribuition_command:
	IDENTIFIER {
		setVarInitializated(_input.LT(-1).getText());
		leftType = symbolTable.get(_input.LT(-1).getText()).getType();
	}
	ATRIBUITION_OPERATOR 
	expression {
		
		System.out.println("Left Side Expression type = " + leftType);
		System.out.println("Right Side Expression type = " + rightType);
	
		if (leftType.getValue() < rightType.getValue()) {
			throw new QuasarSemanticException("Type Missmatch on Assigment"); 
		}
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

					
expression			:
	term { strExpr += _input.LT(-1).getText(); } expression_line	
					;

expression_line		:
	( OPERATOR { strExpr += _input.LT(-1).getText(); } term { strExpr += _input.LT(-1).getText(); } )*
					;

term				:
	IDENTIFIER		{ setTypeIdentifier( _input.LT(-1).getText()); } | 
	NUMBER			{ setType(Types.NUMBER); }						  | 
	TEXT			{ setType(Types.TEXT);   }
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
					
RELATIONAL_OPERATOR : '>' | '<' | '>=' | '<=' | '!=' | '=='
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
ATRIBUITION_OPERATOR: '='		;