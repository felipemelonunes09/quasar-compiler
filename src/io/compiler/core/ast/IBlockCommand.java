package io.compiler.core.ast;

import java.util.ArrayList;
import io.compiler.core.ast.expression.ExpressionCommand;

public interface IBlockCommand {
	
	abstract void setExpression(ExpressionCommand expression);
	abstract void setBlockCommands(ArrayList<Command> commands);
}
