package io.compiler.core.ast.expression;

import io.compiler.types.Var;

public interface IExpression {
	
	abstract void addTerm(String term);
	abstract void addOperator(String operator);
	abstract void addExpression(ExpressionCommand expression);
}
