package io.compiler.core.ast;

import java.util.ArrayList;
import io.compiler.core.ast.expression.*;

public class IfCommand extends BlockCommand {
	


	@Override
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("if ("+this.getExpression().generateTarget()+"){");
			for (Command cmd: this.getBlockCommands()){
				str.append(cmd.generateTarget());
			}
		str.append("}");

			
		return str.toString();
	}


}
