{
	-var operation: text
	-var n1, n2, result: real 

	write("Write the operation (*, /, +, -)")
	input(operation);
	
	write("Input the first number: ");
	input(n1);

	write("Input the second number:");
	input(n2);

	if (operation == "+") {
		result -> nq + n2;
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

