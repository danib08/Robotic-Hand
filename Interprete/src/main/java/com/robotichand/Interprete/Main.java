
package com.robotichand.Interprete;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

	private static final String EXTENSION = "rbg";

	public void compile(File program) throws IOException {

		FileInputStream fis;
		
		try {
			fis = new FileInputStream(program);
			ANTLRInputStream input = new ANTLRInputStream(fis);
			System.out.println("Interpreting file " + program.getAbsolutePath());
			RoboGrammarLexer lexer = new RoboGrammarLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			RoboGrammarParser parser = new RoboGrammarParser(tokens);

			System.out.println("\n");
			
			//Errors
			CustomErrorListener errorListener = new CustomErrorListener(false);
			parser.addErrorListener(errorListener);
			
			RoboGrammarParser.ProgramContext tree = parser.program();

			RoboGrammarCustomVisitor visitor = new RoboGrammarCustomVisitor();
			visitor.visit(tree);

			ArrayList<String> errors = errorListener.getErrorMessages();
			for (String error : errors) {
				System.out.println(error);
			}
			
			fis.close();
			System.out.println("Interpretation finished");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
