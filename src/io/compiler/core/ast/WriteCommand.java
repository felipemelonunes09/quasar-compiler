package io.compiler.core.ast;

public class WriteCommand extends Command {
	
	private String content;

	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		return  "System.out.println(" + content + ");\n";
	}
	
	@Override
	public String generateCppTarget() {
		return "std::cout << " + content + "<< std::endl;\n";
	} 
	
	public WriteCommand(String content) {
		this.content = content;
	}
	
	public WriteCommand() {
		super();
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}

}
