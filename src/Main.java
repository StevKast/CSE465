import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	private static int lineNumber = 0;
	private static HashMap<String, Variable> varStorage;

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Begin Run!");

		varStorage = new HashMap<String, Variable>();
		String currentLineString = "";

		File zpmCode = null;
		if(args.length > 0) {
			zpmCode = new File(args[0]);
		} else {
			System.out.println("Valid z+- file not found!");
		}

		Scanner fileIn = new Scanner(zpmCode);
		Scanner currentLine = new Scanner(currentLineString);

		while(fileIn.hasNext()) {

			lineNumber++;
			//System.out.println("Current line number: " + lineNumber);
			currentLineString = fileIn.nextLine();
			processStatement(currentLineString);

		}
		
		fileIn.close();
		


	} //End main method


	//Displays a variable if possible
	public static void print(Variable var) {
		if(var != null) {
			System.out.println(var.toString());
		}else {
			errorDisplay();
		}
	} //End print method

	//Displays a runtime error with line number
	private static void errorDisplay() {
		System.out.println("RUNTIME ERROR: Line " + lineNumber);
	} //End error method


	//Takes in a line of zpm code and interprets it
	private static void processStatement(String statement) {

		Scanner readStatement = new Scanner(statement);
		ArrayList<String> statementItems = new ArrayList<String>();

		while(readStatement.hasNext()) {
			statementItems.add(readStatement.next());
		}

		//Check for variable assignment
		if(statementItems.size() == 4) {
			String varName = statementItems.get(0);
			String operator = statementItems.get(1);
			String value = statementItems.get(2);
			
			String typeFlag = "";

			//Variable newVar = new Variable(value);
			Variable newVar = null;
			
			//If new value is an integer or string or already existing var
			if(isNumeric(value)) {								//New value is an int
				
				typeFlag = "int";
				newVar = new Variable(Integer.parseInt(value));
				
				
			} else if(value.charAt(0) == '"'){					//New value is a String
				
				typeFlag = "string";
				newVar = new Variable(value);

			} else {											//New value is a var

				if(varStorage.get(value).getType().equals("int")) {
					typeFlag = "int";
					newVar = new Variable(varStorage.get(value).getIntValue());
				} else {
					typeFlag = "string";
					newVar = new Variable(varStorage.get(value).getStringValue());
				}
				
			}			

			//Check if variable already exists
			if(varStorage.containsKey(varName)) {				//Variable already exists and needs changed

				Variable tempVar = varStorage.get(varName);

				if(operator.equals("=")) {

					varStorage.put(varName, newVar);

				} else {
					
					if(typeFlag.equals("int")) {
						tempVar.operate(operator, Integer.toString(newVar.getIntValue()));
					} else {
						tempVar.operate(operator, newVar.getStringValue());
					}
				}
			} else {											//This is a new variable being added

					varStorage.put(varName, newVar);

			}

		}


		//Check for print statement
		if(statementItems.get(0).equals("PRINT")) {
			Variable tempVar = varStorage.get(statementItems.get(1));
			print(tempVar);
		}
		
		if(statementItems.get(0).equals("FOR")) {
			System.out.println("For loop detected");
		}

		readStatement.close();

	} //End processStatement

	public static boolean isNumeric(String str){
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

} //End class
