package io.compiler.core.ast;

import java.util.ArrayList;

public class IfCommand extends Command {
	
	private ExpressionCommand expression;
	private ArrayList<Command> trueList;
	private ArrayList<Command> falseList;

	public ExpressionCommand getExpression() {
		return expression;
	}

	public void setExpression(ExpressionCommand expression) {
		this.expression = expression;
	}

	public ArrayList<Command> getTrueList() {
		return trueList;
	}

	public void setTrueList(ArrayList<Command> trueList) {
		this.trueList = trueList;
	}

	public ArrayList<Command> getFalseList() {
		return falseList;
	}

	public void setFalseList(ArrayList<Command> falseList) {
		this.falseList = falseList;
	}

	@Override
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("if ("+expression.generateTarget()+"){");
			for (Command cmd: this.trueList) {
				str.append(cmd.generateTarget());
			}
		str.append("}");
		
		if (this.falseList != null) {
			
			str.append("else {");
			for (Command cmd: this.falseList) {
				str.append(cmd.generateTarget());
			}
			str.append("}");
		}
			
		return str.toString();
	}

}
