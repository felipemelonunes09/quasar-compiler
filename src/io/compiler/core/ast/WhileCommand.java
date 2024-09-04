package io.compiler.core.ast;

import java.util.ArrayList;

import io.compiler.core.ast.expression.*;

// if other types of loop are implemented it should have a commmom class called LoopCommand
// also in case of For implementation Declaration has to have a class derivation, into aritmetic expression, booleans, and expression composition
public class WhileCommand extends BlockCommand {
	
	private boolean executeFirst;

	public WhileCommand(boolean executeFirst) {
		this.executeFirst = executeFirst;
	}
	
	public boolean isExecuteFirst() {
		return executeFirst;
	}
	

	public void setExecuteFirst(boolean executeFirst) {
		this.executeFirst = executeFirst;
	}

	
	@Override
	public String generateTarget() {
		
		StringBuilder str = new StringBuilder();
		
		str.append((this.executeFirst) ? "do { ": "while (" + this.getExpression().generateTarget() + " ) {\n" );
		for (Command cmd: this.getBlockCommands()) {
			str.append(cmd.generateTarget());
		}
		str.append((this.executeFirst) ? "} while (" + this.getExpression().generateTarget() + ");\n":"}\n");
		
		return str.toString();
	}

}
