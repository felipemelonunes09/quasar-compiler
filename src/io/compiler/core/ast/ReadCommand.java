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
	
	public ReadCommand(Var v){
		this.var = v;
	}
	
	@Override
	public String generateTarget() {
		StringBuilder sb = new StringBuilder();		
		sb.append(var.getId() + " = ");
		
		switch (var.getType()) {
			case Types.NUMBER: 		sb.append("_scTrx.nextInt();");		break; 
			case Types.REALNUMBER: 	sb.append("_scTrx.nextDlouble();");	break; 
			case Types.TEXT: 		sb.append("_scTrx.nextLine();");	break; 
		}
		
		return sb.toString() + "\n";
	}

	@Override
	public String generateCppTarget() {
		StringBuilder sb = new StringBuilder();
		switch(var.getType()) {
			case Types.NUMBER: 		sb.append("std::cin >> " + var.getId() + ";"); break;
			case Types.REALNUMBER:  sb.append("std::cin >> " + var.getId() + ";"); break;
			case Types.TEXT: 		sb.append("std::getline(std::cin," + var.getId() + ");"); break;
		
		}
		sb.append("std::cin.ignore();\n");
		return sb.toString();
	}
}
