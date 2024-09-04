package io.compiler.core.ast.expression;

public class BooleanExpressionCommand extends ExpressionCommand {

	@Override
	public void addOperator(String operator) {
		this.addExpression(operator);
	}
}
