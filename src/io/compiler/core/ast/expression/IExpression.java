package io.compiler.core.ast.expression;

public interface IExpression {
	
	abstract void addTerm(String term);
	abstract void addOperator(String operator);
	abstract void addExpression(ExpressionCommand expression);
}
