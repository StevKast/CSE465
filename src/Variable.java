
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

		System.out.println(op + " " + value);
		
		if(op.equals("+=")) {
			
			intValue += Integer.parseInt(value);

		} else if(op.equals("-=")) {

		} else if(op.equals("*=")) {

		}
	}
	
	public String getValue(){
		return stringValue;
	}
	
	public String getType() {
		return type;
	}



	@Override
	public String toString() {
		if(type.equals("int")) {
			return Integer.toString(intValue);
		}else {
			return stringValue;
		}
	}


}
