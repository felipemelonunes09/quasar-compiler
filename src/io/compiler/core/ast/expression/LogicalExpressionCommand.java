package io.compiler.core.ast.expression;
import io.compiler.core.exception.WrongExpressionOperator;

public class LogicalExpressionCommand extends ExpressionCommand {
	
	@Override
	public void addOperator(String operator) {
		
		if (operator.equals("and")) { this.addExpression("&&"); }
		else if (operator.equals("or")) { this.addExpression("||"); }
		else
			throw new WrongExpressionOperator("Operator " + operator + " it is not an logical expression operator");

	}
}
