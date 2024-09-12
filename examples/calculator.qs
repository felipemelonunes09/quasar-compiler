{
	-var operation: number;
	-var n1, n2, result: real;

	write("Write the operation\n");
	write("1 -> adition \n");
	write("2 -> subtraction \n");
	write("3 -> multiplication \n");
	write("4 -> division \n");
	read(operation);
	
	write("Input the first number: ");
	read(n1);

	write("Input the second number:");
	read(n2);

	if (operation == 1) {
		result -> n1 + n2;
	}
	
	if (operation == 2) {
		result -> n1 - n2;
	}

	if (operation == 3) {
		result -> n1 * n2;
	}

	if (operation == 4) {
		result -> n1/n2;
	}

	write("The result is: ");
	write(result);
}

