package io.compiler.core.ast;
import java.util.ArrayList;
// if other types of loop are implemented it should have a commmom class called LoopCommand
// also in case of For implementation Declaration has to have a class derivation, into aritmetic expression, booleans, and expression composition
public class WhileCommand extends Command{
	
	private ExpressionCommand expression;
	private boolean executeFirst;
	private ArrayList<Command> loopCommands;


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
		
		str.append((this.executeFirst) ? "do { ": "while (" + this.expression + " ) {\n" );
		for (Command cmd: this.loopCommands) {
			str.append(cmd.generateTarget());
		}
		str.append((this.executeFirst) ? "} while (" + this.expression + ");\n":"}\n");
		
		return str.toString();
	}

	public ExpressionCommand getExpression() {
		return expression;
	}

	public void setExpression(ExpressionCommand expression) {
		this.expression = expression;
	}

	public ArrayList<Command> getLoopCommands() {
		return loopCommands;
	}

	public void setLoopCommands(ArrayList<Command> loopCommands) {
		this.loopCommands = loopCommands;
	}

}
