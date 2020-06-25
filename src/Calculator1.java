import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Calculator1{
	public static boolean isboolean = false;
	public static boolean answer = false;	
	public static Stack<String> operators = new Stack<>();
	public static Stack<Integer> numbers = new Stack<>();
	public static Scanner inputStream;
	public static void main(String[] args) {
		
		try {
			inputStream = new Scanner(new FileInputStream("statements.txt"));
			int i = 1;
			while(inputStream.hasNextLine()) {
				String cal = inputStream.nextLine();
				System.out.println(i++ + ". the statement: " + cal + " = " + compute(cal) + "\n");
			}
		}catch(FileNotFoundException a) {
			System.out.println(a.getMessage());	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
			System.exit(0);
		}finally {
			if(inputStream != null)
				inputStream.close();
		}
		
		
	}
	public static String compute(String statement) throws Exception {// recursive method for parenthesis
		if(!statement.contains("(")) // if it doesn't contain a ( this is our base case we can the method defined below to get the result
			return calculate(statement);
		String subCalculation;
		Stack<Integer> parenindex = new Stack<>(); // stack to store the index of the parenthesis
		for(int i = 0; i < statement.length(); i++) { // loop over the statement until we get ( expression )
			if(statement.charAt(i) == '(')
				parenindex.push(i);
			if(statement.charAt(i) == ')') {
				int z = parenindex.pop();
				subCalculation = calculate(statement.substring(z + 1, i)); // once we get our match we get
				statement = statement.substring(0, z) + " " + subCalculation + statement.substring(i + 1, statement.length());
				//System.out.println(statement);
				break;
			}
		}
		
		return compute(statement);
		
	}
	
	public static String calculate(String statement) throws Exception{
		StringTokenizer s = new StringTokenizer(statement);
		String temp= "";
		int c = s.countTokens();
		for(int i = 0; i < c + 1; i++) {
			
			if(i == c)
				temp = "$";
			else
				temp = s.nextToken();
			
			if(isDigit(temp)) {
				numbers.push(Integer.parseInt(temp));
			}
			else if(isfactorial(temp)) {
				int x = Integer.parseInt(temp.substring(0, temp.length() - 1));
				numbers.push(factorial(x));
			}
			else {
				if(!operators.empty()) {
					while(numbers.size() > 1 && priorityOP(operators.peek()) <= priorityOP(temp)) {
						//System.out.println("test");
						if(operators.peek().equals("!"))
							numbers.push(evali(numbers.pop(), operators.pop()));
						else {
							if(!isBoolean(operators.peek()))
								numbers.push(evali(numbers.pop(), numbers.pop(), operators.pop()));
							else {
								
								isboolean = true;
								answer = evalb(numbers.pop(), numbers.pop(), operators.pop());
								return Boolean.toString(answer);
							}
						}
						
					
					}
				}
				operators.push(temp);
			}
			//System.out.println(operators);
			//System.out.println(numbers);
		}
		
		
		return String.valueOf(numbers.pop());
	}
	public static int priorityOP(String x)  {// priority of operation not including unary will be diferentiated without a space for the string tokenizer

		if(x.equals("!"))
			return 2;
		if(x.equals("^"))
			return 4;
		if(x.equals("*"))
			return 5;
		if(x.equals("/"))
			return 5;
		if(x.equals("+") || x.equals("-"))
			return 6;
		if(x.equals(">") || x.equals("<") || x.equals("=>") || x.equals("=<"))
			return 7;
		if(x.equals("==") || x.equals("!="))
			return 7;
		return 8;
		//throw new Exception("unkown operation");
		
	}
	
	public static boolean isDigit(String x) {
		try {
			Integer.parseInt(x);
			return true;
			
		}catch(Exception e) {
			return false;
		}
		
	}
	public static boolean isBoolean(String x) {
		if(x.equals(">") || x.equals("<") || x.equals(">=") || x.equals("<=") || x.equals("==") || x.equals("!="))
			return true;
		else
			return false;
		
	}
	
	public static boolean evalb(int y, int x, String op) {
		if(op.equals(">"))
			return x > y;
		if(op.equals("<"))
				return x < y;
		if(op.equals("<="))
			return x <= y;
		if(op.equals(">="))
			return x >= y;
		if(op.equals("=="))
			return x == y;
		if(op.equals("!="))
			return x != y;
		return false;
		
	}
	
	public static int evali(int x, int y, String op) {
		if(op.equals("+"))
			return x + y;
		if(op.equals("-"))
				return y - x;
		if(op.equals("*")) {
			return x * y;
		}
		if(op.equals("^")) {
			return pow(y, x);
		}
		if(op.equals("/")) {
			return y / x;
		}
		
		
			
		
		return 0;
		
	}
	public static int evali(int x, String op) {
		return factorial(x);
		
	}
	public static int factorial(int x){
		if(x == 0)
			return 1;
		else
			return x * factorial(x - 1);
		
	}
	
	public static boolean isfactorial(String s) {
		if(s.charAt(s.length() - 1) == '!')
			return true;
		return false;
		
	}
	public static int pow(int x, int n) {
		int y;
		if(n == 0)
			return 1;
		if(n > 0 && n % 2 == 1) {
			y =  pow(x, (n-1)/2);
			return x*y*y;
		}
		else {
			y = pow(x, n/2);
			return y * y;
		}
			
	}

	
		
	}
