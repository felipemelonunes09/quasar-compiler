package io.compiler.core.ast;

public class ExpressionCommand extends Command {
	
	private String expression;
	
	public ExpressionCommand() {
		this.expression = "";
	}
	
	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		return this.expression;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public void addExpression(String expression) {
		this.expression += expression;
	}

}
