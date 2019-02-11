
public class Variable {

	int intValue;
	String stringValue;
	String type;


	public Variable(String value) {
		stringValue = value;
		type = "string";
	}

	public Variable(int value) {
		intValue = value;
		type = "int";
	}

	public void operate(String op, String value) {

		//System.out.println(op + " " + value);

		if(type.equals("int")) {							//If of type int
			
			if(op.equals("+=")) {

				intValue += Integer.parseInt(value);

			} else if(op.equals("-=")) {
				
				intValue -= Integer.parseInt(value);

			} else if(op.equals("*=")) {
				
				intValue *= Integer.parseInt(value);

			}
		} else {											//If of type string
			
			stringValue += value;
			
		}
	}

	public String getStringValue(){
		return stringValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public String getType() {
		return type;
	}
	



	@Override
	public String toString() {
		if(type.equals("int")) {
			return Integer.toString(intValue);
		}else {
			return stringValue.replaceAll("\"", "");
		}
	}


}
