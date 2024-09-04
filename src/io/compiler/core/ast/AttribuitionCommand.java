package io.compiler.core.ast;

import io.compiler.core.ast.expression.ExpressionCommand;
import io.compiler.types.Var;

public class AttribuitionCommand extends Command {
	
	Var v;
	ExpressionCommand expression;
	
	@Override
	public String generateTarget() {
		return v.getId() + " = " + expression.generateTarget() + ";\n";
	}

	public AttribuitionCommand(Var v, ExpressionCommand expression) {
		super();
		this.v = v;
		this.expression = expression;
	}

	public AttribuitionCommand(Var v) {
		super();
		this.v = v;
	}

	public Var getV() {
		return v;
	}

	public void setV(Var v) {
		this.v = v;
	}

	public ExpressionCommand getExpression() {
		return expression;
	}

	public void setExpression(ExpressionCommand expression) {
		this.expression = expression;
	}
}
