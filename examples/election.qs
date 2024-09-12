{
	-var candidateA: number;
	-var candidateB: number;
	-var vote: number;
	
	vote -> 0;
	while(vote >< 3) {
		write("Vote: \n");		
		write("1 -> Candidate A \n");
		write("2 -> Candidate B \n");
		write("3 -> Exit ");
		read(vote);
		
	if (vote == 1) { candidateA -> candidateA + 1; }
		if (vote == 2) { candidateB -> candidateB + 1; }
	}

	if (candidateA > candidateB) {
		write("Candidate A wins");
	}
	else {
		if (candidateB > candidateA) {
			write("Candidate B wins");
		}
		else {
			write("Candidate A votes == Candidate B votes");
		}
	}
}
