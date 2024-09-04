package io.compiler.core.ast;

import io.compiler.core.ast.expression.AritmeticExpressionCommand;
import io.compiler.types.Var;

public class AttribuitionCommand extends Command {
	
	Var v;
	AritmeticExpressionCommand expression;
	
	@Override
	public String generateTarget() {
		return v.getId() + " " + expression.generateTarget() + ";";
	}

	public AttribuitionCommand(Var v, AritmeticExpressionCommand expression) {
		super();
		this.v = v;
		this.expression = expression;
	}

	public AttribuitionCommand(Var v) {
		super();
		this.v = v;
	}
}
