package com.robotichand.Interprete;

import java.util.ArrayList;
import java.util.List;
import java.util.BitSet;

import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;

public class CustomErrorListener extends DiagnosticErrorListener{
	 private final Boolean captureDiagnostics;
	 private final ArrayList<String> errorMessages = new ArrayList<String>();
	 private final ArrayList<String> warningMessages = new ArrayList<String>();

	 public CustomErrorListener(Boolean captureDiagnosticWarnings){
		 captureDiagnostics = captureDiagnosticWarnings;
	 }    
    

    public ArrayList<String> getErrorMessages() {
		return this.errorMessages;
	}

	public ArrayList<String> getWarningMessages() {
		return warningMessages;
	}

	public Boolean hasErrors() {
		return !this.errorMessages.isEmpty();
	}


	public Boolean hasWarnings() {
		return !this.warningMessages.isEmpty();
	}	

   

    public void SyntaxError(Recognizer recognizer, Token offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
    {
        this.errorMessages.add(String.format("line %1$s:%2$s %3$s at: %4$s", line, charPositionInLine, msg, offendingSymbol.getText()));
        
    }
    
    public void ReportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, Boolean exact, BitSet ambigAlts, ATNConfigSet configs)
    {
        if (captureDiagnostics)
        {
            this.warningMessages.add(String.format("reportAmbiguity d=%1$s: ambigAlts=%2$s, input='%3$s'", getDecisionDescription(recognizer, dfa), getConflictingAlts(ambigAlts, configs), ((TokenStream)recognizer.getInputStream()).getText(Interval.of(startIndex, stopIndex))));
        }
    }
    
    public void ReportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNSimulator conflictState)
    {
        if (captureDiagnostics)
        {
            this.warningMessages.add(String.format("reportAttemptingFullContext d=%1$s, input='%2$s'", getDecisionDescription(recognizer, dfa), ((TokenStream)recognizer.getInputStream()).getText(Interval.of(startIndex, stopIndex))));
        }
    }

    public void ReportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNSimulator acceptState)
    {
        if (captureDiagnostics)
        {
            this.warningMessages.add(String.format("reportContextSensitivity d=%1$s, input='%2$s'", getDecisionDescription(recognizer, dfa), ((TokenStream)recognizer.getInputStream()).getText(Interval.of(startIndex, stopIndex))));
        }
    }
    
    public void printSyntaxErrors() {
    	System.out.println(this.getErrorMessages());
    }
    
    public void printWarnings() {
    	System.out.println(this.getWarningMessages());
    }
    
}

