package io.compiler.core.ast;

import java.util.ArrayList;
import java.util.HashMap;
import io.compiler.types.Var;
import io.compiler.core.exception.*;

public class Program {
	
	private String name;
	private HashMap<String, Var> symbolTable;
	private ArrayList<Command> commandList;
	
	private boolean unusedVarWarning 	= false;
	private boolean unusedVarException	= false;
	
	public void setUnusedVarWarning(boolean unusedVarWarning) {
		this.unusedVarWarning = unusedVarWarning;
	}
	public void setUnusedVarException(boolean unusedVarException) {
		this.unusedVarException = unusedVarException;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, Var> getSymbolTable() {
		return symbolTable;
	}
	public void setSymbolTable(HashMap<String, Var> symbolTable) {
		this.symbolTable = symbolTable;
	}
	public ArrayList<Command> getCommandList() {
		return this.commandList;
	}
	public void setCommandList(ArrayList<Command> commandList) {
		this.commandList = commandList;
	}
	
	// this step can remove var that is not being used
	public void verifyUnusedVar() {
		for (String key: symbolTable.keySet()) {
			
			Var v = this.symbolTable.get(key);
			if (!v.isInitialized()) {
				
				if (this.unusedVarException)
					throw new UnusedVarException(v.getId() + " was initialized but never used");
				
				if (this.unusedVarWarning) 
					System.err.println(v.getId() + " was initialized but never used");
			}
			
		}
	}
	
	public String generateTarget(String target) {

		StringBuilder str = new StringBuilder();
		if (target.equals("java")) {
			str.append("import java.util.Scanner;\n");
				str.append("public class " + name + "{ \n");
				str.append("	public static void main(String args[]) { \n");
				str.append("		Scanner _scTrx = new Scanner(System.in);\n");
					for (Command cmd: this.commandList) {
						str.append(cmd.generateTarget());
					}
				
				str.append("	}\n");
				str.append("}\n");
		}
		
		if (target.equals("cpp")) {
			str.append("#include <iostream>\n");
			str.append("#include <string>\n");
			
			str.append("int main() {\n");
				for (Command cmd: this.commandList) {
					str.append(cmd.generateCppTarget());
				}
			str.append("return 1;\n");
			str.append("}");
		}
		
		return str.toString();
	}
	
}
