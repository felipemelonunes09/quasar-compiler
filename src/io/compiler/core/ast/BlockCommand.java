package io.compiler.core.ast;

import java.util.ArrayList;

import io.compiler.core.ast.expression.ExpressionCommand;

public abstract class BlockCommand extends Command implements IBlockCommand {
	
	private ExpressionCommand expression;
	private ArrayList<Command> blockCommands;

	@Override
	public void setExpression(ExpressionCommand expression) {
		this.expression = expression;
	}

	@Override
	public void setBlockCommands(ArrayList<Command> commands) {
		this.blockCommands = commands;
	}

	public ExpressionCommand getExpression() {
		return expression;
	}

	public ArrayList<Command> getBlockCommands() {
		return blockCommands;
	}
	
}
