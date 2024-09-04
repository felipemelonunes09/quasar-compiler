package io.compiler.core.ast.expression;

public class AritmeticExpressionCommand extends ExpressionCommand {
	
	public AritmeticExpressionCommand(String term) {
		this.addTerm(term);
	}

	@Override
	public void addOperator(String operator) {
		this.addExpression(operator);
	}
}
