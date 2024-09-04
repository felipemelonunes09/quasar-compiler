package io.compiler.core.ast;
import io.compiler.types.Types;
import io.compiler.types.Var;

public class ReadCommand extends Command {
	
	private Var var;
	
	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}
	
	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(var.getId() + " = ");
		
		switch (var.getType()) {
			case Types.NUMBER: 		sb.append("_scTrx.nextInt();");		break; 
			case Types.REALNUMBER: 	sb.append("_scTrx.nextDlouble();");	break; 
			case Types.TEXT: 		sb.append("_scTrx.nextLine();");	break; 
		}
		
		return sb.toString() + "\n";
	}
	
	public ReadCommand(Var v)
	{
		this.var = v;
	}

}
