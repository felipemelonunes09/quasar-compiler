package io.compiler.core.ast.expression;

import io.compiler.core.exception.WrongExpressionOperator;

public class BooleanExpressionCommand extends ExpressionCommand {

	@Override
	public void addOperator(String operator) {
		
		if (
			operator.equals("==") ||
			operator.equals("<")  ||
			operator.equals(">")  ||
			operator.equals("<=") ||
			operator.equals("=>")  
		)
			this.addExpression(operator);
		else if (operator.equals("><")) {
			this.addExpression("!=");
		}
		
		else
			throw new WrongExpressionOperator("Operator " + operator + " it is not an boolean expression operator");
	}
}
