package io.compiler.core.ast;

import java.util.ArrayList;
import java.util.HashMap;
import io.compiler.types.Var;

public class Program {
	private String name;
	private HashMap<String, Var> symbolTable;
	private ArrayList<Command> commandList;
	
	
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
	
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
			str.append("public class " + name + "{ \n");
			str.append("	public static void main(String args[]) { \n");
			str.append("		Scanner _scTrx = new Scanner(System.in);\n");
				for (Command cmd: this.commandList) {
					str.append(cmd.generateTarget());
				}
			
			str.append("	}\n");
			str.append("}\n");
		return str.toString();
	}
	
}
