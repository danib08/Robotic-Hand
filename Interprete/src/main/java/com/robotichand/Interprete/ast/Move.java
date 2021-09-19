package com.robotichand.Interprete.ast;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.robotichand.Interprete.MainProgram;

public class Move implements ASTNode {
	
	private String finger;
	private ASTNode condition;
	private MainProgram program = new MainProgram();
	
	public Move(String finger, ASTNode condition) {
		super();
		this.finger = finger;
		this.condition = condition;		
	}

	
	@Override
	public Object execute(Map<String, Object> symbolTable) {
		// TODO Auto-generated method stub
		try {
			finger = finger.replace("\"", "");
			if ((condition.execute(symbolTable)).getClass() != Boolean.class) {
				System.out.println("The orientation parameter must be a boolean");
				return null;
			}
			
			if (finger.length() == 1 && finger.equals(finger.toUpperCase())) {
				switch(finger) {
					case "P":
						System.out.println("P");
						 if ((boolean)condition.execute(symbolTable) == true) {
							 try {
						            program.arduino.sendData("PS");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }else {
							 try {
						            program.arduino.sendData("PB");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }
						break;
					case "I":
						System.out.println("I");
						if ((boolean)condition.execute(symbolTable) == true) {
							 try {
						            program.arduino.sendData("IS");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }else {
							 try {
						            program.arduino.sendData("IB");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }
						break;
					case "M":
						System.out.println("M");
						if ((boolean)condition.execute(symbolTable) == true) {
							 try {
						            program.arduino.sendData("MS");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }else {
							 try {
						            program.arduino.sendData("MB");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }
						break;
					case "A":
						System.out.println("A");
						if ((boolean)condition.execute(symbolTable) == true) {
							 try {
						            program.arduino.sendData("AS");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }else {
							 try {
						            program.arduino.sendData("AB");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }
						break;
					case "Q":
						System.out.println("Q");
						if ((boolean)condition.execute(symbolTable) == true) {
							 try {
						            program.arduino.sendData("QS");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }else {
							 try {
						            program.arduino.sendData("QB");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }
						break;
					case "T":
						System.out.println("T");
						if ((boolean)condition.execute(symbolTable) == true) {
							 try {
						            program.arduino.sendData("TS");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }else {
							 try {
						            program.arduino.sendData("TB");
						        } catch (Exception ex) {
						            Logger.getLogger(Move.class.getName()).log(Level.SEVERE, null, ex);
						        }
						 }
						break;
					default:
						System.out.println("The finger parameter must be: P, I, M, A, Q, T");
				}
			}
			else {
				System.out.println("First parameter must be an uppercase letter.");
			}
		}catch(NullPointerException e) {
			System.out.println("The parameters must be String and Boolean");
		}
		return null;
	}

}
