
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
}
