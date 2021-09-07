
package com.robotichand.Interprete;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.robotichand.Arduino.Create;

public class Main {

	private static final String EXTENSION = "rbg";

	public static void main(String[] args) throws IOException {
		
		
		String program = args.length > 1 ? args[1] : "test/test." + EXTENSION;

		System.out.println("Interpreting file " + program);
//		Create create;
//		create = new Create();
//		Thread thread = new Thread(create);
//		thread.start();
		RoboGrammarLexer lexer = new RoboGrammarLexer(new ANTLRFileStream(program));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		RoboGrammarParser parser = new RoboGrammarParser(tokens);

		RoboGrammarParser.ProgramContext tree = parser.program();

		RoboGrammarCustomVisitor visitor = new RoboGrammarCustomVisitor();
		visitor.visit(tree);

		System.out.println("Interpretation finished");
		

	}

}
