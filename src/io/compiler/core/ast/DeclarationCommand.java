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
				
		return ( this.var.getType() == Types.NUMBER ? "int": "String" ) + " " + this.var.getId() + ";";
	}
	
}
