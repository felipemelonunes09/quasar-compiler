package io.compiler.core.ast;

import io.compiler.core.ast.expression.ExpressionCommand;

public interface IExpressionableCommand {
	abstract void setExpression(ExpressionCommand expression);
}
