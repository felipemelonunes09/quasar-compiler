package io.compiler.types;

public enum Types {
	NUMBER(1), 
	REALNUMBER(2),
	TEXT(3);
	
	public Integer value;
	
	private Types(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
}
