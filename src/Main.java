import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	private static int lineNumber;
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
			currentLineString = fileIn.nextLine();
			processStatement(currentLineString);

		}


	} //End main method


	public static void print(Variable var) {
		if(var != null) {
			System.out.println(var.toString());
		}else {
			errorDisplay();
		}
	} //End print method

	private static void errorDisplay() {
		System.out.println("RUNTIME ERROR: Line " + lineNumber);
	} //End error method

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

			Variable newVar = new Variable(value);

			//Check if variable already exists
			if(varStorage.containsKey(varName)) {

				Variable tempVar = varStorage.get(varName);
				
				System.out.println(tempVar.getType());

				if(operator.equals("=")) {

					varStorage.put(varName, newVar);

				} else{
					
					if(varStorage.containsKey(value)) {
						value = varStorage.get(value).getValue();
					}

					tempVar.operate(operator, value);

				}

			} else {
				varStorage.put(varName, newVar);
			}

		}


		//Check for print statement
		if(statementItems.get(0).equals("PRINT")) {
			Variable tempVar = varStorage.get(statementItems.get(1));
			print(tempVar);
		}

	} //End processStatement

} //End class
