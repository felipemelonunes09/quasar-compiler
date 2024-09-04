package io.compiler.core.ast.expression;

import io.compiler.core.ast.Command;
import io.compiler.types.Var;

public class ExpressionCommand extends Command implements IExpression {
	
	private String expression = "";

	public ExpressionCommand() {
		super();
	}

	public ExpressionCommand(String term) {
		this.addTerm(term);
	}


	@Override
	public String generateTarget() {
		return this.expression;
	}

	@Override
	public void addTerm(String term) {
		this.expression += term;
	}

	@Override
	public void addOperator(String operator) {
		this.expression += operator;
	}

	@Override
	public void addExpression(ExpressionCommand expression) {
		this.expression += expression.getExpression();
	}
	
	public void addExpression(String expression) {
		this.expression += expression;
	}
	
	public String getExpression() {
		return this.expression;
	}
	
}
