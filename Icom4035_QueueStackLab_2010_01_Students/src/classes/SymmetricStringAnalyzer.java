package classes;

public class SymmetricStringAnalyzer {
	private String s; 
	public SymmetricStringAnalyzer(String s) {
		this.s = s; 
	}

	/**
	 * Determines if the string s is symmetric
	 * @return true if it is; false, otherwise. 
	 */
	public boolean isValidContent() { 
		LLStack<Character> stack = new LLStack<Character>();
		for (int i=0;i<s.length();i++){
			char currentChar = s.charAt(i);
			if(!Character.isLetter(currentChar))
				return false;
			else{
				if(Character.isUpperCase(currentChar)){
					stack.push(currentChar);
				}
				else if(stack.isEmpty())
					return false;
				else {

					if(stack.top().equals(Character.toUpperCase(currentChar))){
						stack.pop();
					}
					else return false;
				}
			}
		}

		return stack.isEmpty();  // need to change if necessary....
	}

	public String toString() { 
		return s; 
	}

	public String parenthesizedExpression() 
			throws StringIsNotSymmetricException 
	{
		if(!this.isValidContent()) throw new StringIsNotSymmetricException("parenthesizedExpression: String is not valid.");

		String answer = "";
		for (int i=0; i < s.length(); i++){
			char c = s.charAt(i);
			if (Character.isUpperCase(c)){
				answer = answer + "<" + c + " ";
			} else {
				answer = answer + c + ">" + " ";
			}
		}

		return answer;
	}
}


