package io.compiler.main;

import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;

import io.compiler.core.QuasarGrammarLexer;
import io.compiler.core.QuasarGrammarParser;

import io.compiler.core.TestGrammarLexer;
import io.compiler.core.TestGrammarParser;
import io.compiler.core.ast.*;

public class MainClass {
	public static void main(String[] args) {
		try {
			QuasarGrammarLexer lexer;
			QuasarGrammarParser parser;
			
			// creating lexer analyser from an file read
			
			String filename = "input2";
			
			lexer = new QuasarGrammarLexer(CharStreams.fromFileName((filename + ".qs")));
			
			// token flux
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// create parser from token stream
			parser = new QuasarGrammarParser(tokenStream);
			
			System.out.println("(+) Quasar Compiler");
			parser.program();
			System.out.println("(+) Compilation Successfully");
			
			// code generation
			
			Program program = parser.getProgram();
			program.setName(filename);
			
			System.out.println(program.generateTarget());
			
			try {
				File f = new File(program.getName() + ".java");
				FileWriter fr = new FileWriter(f);
				PrintWriter pr = new PrintWriter(fr);
				
				pr.println(program.generateTarget());
				pr.close();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		
		}
		catch(Exception ex) {
			System.err.println("(-) Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
