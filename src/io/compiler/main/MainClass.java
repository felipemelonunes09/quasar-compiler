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
			
			String path = args[0];
			String filename = args[1];
			String target = args[2];
			
			path = path + filename + ".qs";
			lexer = new QuasarGrammarLexer(CharStreams.fromFileName((path)));
			
			// token flux
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// create parser from token stream
			parser = new QuasarGrammarParser(tokenStream);
			
			System.out.println("(+) Quasar Compiler");
			parser.program();
			System.out.println("(+) Compilation Successfully"); 
			
			// code generation
			//program.setUnusedVarException(true);
			Program program = parser.getProgram();
			program.setUnusedVarWarning(true);
			program.setName(filename);
			program.verifyUnusedVar(); 
			
			System.out.println(program.generateTarget(target));
			
			try {
				File f = new File(program.getName() + "." + target);
				FileWriter fr = new FileWriter(f);
				PrintWriter pr = new PrintWriter(fr);
				
				pr.println(program.generateTarget(target));
				pr.close();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			System.err.println("A path, filename and target must be informed");
		}
		catch(Exception ex) {
			System.err.println("(-) Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}
}
