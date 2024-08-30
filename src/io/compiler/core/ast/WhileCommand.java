package io.compiler.core.ast;
import java.util.ArrayList;

public class WhileCommand extends Command{
	
	private String expression;
	private boolean executeFirst;
	private ArrayList<Command> loopCommands;
	
	public boolean isExecuteFirst() {
		return executeFirst;
	}

	public void setExecuteFirst(boolean executeFirst) {
		this.executeFirst = executeFirst;
	}

	
	@Override
	public String generateTarget() {
		
		StringBuilder str = new StringBuilder();
		
		str.append((this.executeFirst) ? "do { ": "while (" + this.expression + " ) {\n" );
		for (Command cmd: this.loopCommands) {
			str.append(cmd.generateTarget());
		}
		str.append((this.executeFirst) ? "while (" + this.expression + ");\n":"}\n");
		
		return str.toString();
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public ArrayList<Command> getLoopCommands() {
		return loopCommands;
	}

	public void setLoopCommands(ArrayList<Command> loopCommands) {
		this.loopCommands = loopCommands;
	}

}
