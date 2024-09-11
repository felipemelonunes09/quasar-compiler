package io.compiler.core.ast;

public abstract class Command {
	
	// the default target is java
	public abstract String generateTarget();
	public abstract String generateCppTarget();
}
