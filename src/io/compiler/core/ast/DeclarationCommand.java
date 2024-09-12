package io.compiler.core.ast;

import io.compiler.types.Types;
import io.compiler.types.Var;

public class DeclarationCommand extends Command{
	private Var var;
	
	public DeclarationCommand(Var v) {
		this.var = v;
	}

	@Override
	public String generateTarget() {
		
		StringBuilder sb = new StringBuilder();
		
		switch (this.var.getType()) {
			case Types.NUMBER:  	sb.append("int " + var.getId() + " = 0;\n"); 		break;
			case Types.REALNUMBER:	sb.append("double " + var.getId() + " = 0;\n"); 	break;
			case Types.TEXT:		sb.append("String " + var.getId() + " = null;\n"); 	break;
		}
		return sb.toString();
	}

	@Override
	public String generateCppTarget() {
		StringBuilder sb = new StringBuilder();
		switch(this.var.getType()) {
			case Types.NUMBER: 		sb.append("int"); break;
			case Types.REALNUMBER: 	sb.append("double"); break;
			case Types.TEXT: 		sb.append("std::string"); break;
		}
		
		sb.append(" " + var.getId() + ";\n");
		return sb.toString();
	}
}
