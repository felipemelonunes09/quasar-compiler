package io.compiler.core.ast.expression;

import io.compiler.core.exception.WrongExpressionOperator;

public class AritmeticExpressionCommand extends ExpressionCommand {
	
	public AritmeticExpressionCommand(String term) {
		this.addTerm(term);
	}

	@Override
	public void addOperator(String operator) {
		

		if (
			operator.equals("/") ||
			operator.equals("*") ||
			operator.equals("+") ||
			operator.equals("-") 
		)
			this.addExpression(operator);
		else
			throw new WrongExpressionOperator("Operator " + operator + " it is not an aritmetic expression operator");
	}
}
