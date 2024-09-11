package io.compiler.core.ast;

import java.util.ArrayList;
import io.compiler.core.ast.expression.*;

public class ElseCommand extends BlockCommand {
	

	// IBlock command its forcing else command to implement setExpression (SOLID misspratice)
	// for now it works but with other variance of command it would be nice to create those new interfaces
	// - IBlockCommand
	// - IExpressionableCommand
	// - IExpressionableBlockCommand
	@Override
	public void setExpression(ExpressionCommand expression) {
		throw new RuntimeException("It's not possible to add an expression to an else");
	}


	@Override
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("else {");
			for (Command cmd: this.getBlockCommands()) {
				str.append(cmd.generateTarget());
			}
		str.append("}");
		return str.toString();
	}


	@Override
	public String generateCppTarget() {
		StringBuilder str = new StringBuilder();
		str.append("else {");
			for (Command cmd: this.getBlockCommands()) {
				str.append(cmd.generateCppTarget());
			}
		str.append("}");
		return str.toString();
	}

	
	

}