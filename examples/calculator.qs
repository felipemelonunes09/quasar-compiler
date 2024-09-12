{
	-var operation: text;
	-var n1, n2, result: real;

	write("Write the operation (*, /, +, -)");
	read(operation);
	
	write("Input the first number: ");
	read(n1);

	write("Input the second number:");
	read(n2);

	if (operation == "+") {
		result -> n1 + n2;
	}
	
	if (operation == "-") {
		result -> n1 - n2;
	}

	if (operation == "*") {
		result -> n1 * n2;
	}

	if (operation == "/") {
		result -> n1/n2;
	}

	write("The result is: ");
	write(result);
}

