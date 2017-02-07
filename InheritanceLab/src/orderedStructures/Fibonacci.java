package orderedStructures;

public class Fibonacci extends Progression {

	private double prev; 
	
	public Fibonacci() { 
		this(1); 
		prev = 0; 
	}
	private Fibonacci(double first) {
		super(first);
	}

	@Override
	public double nextValue()throws IllegalStateException{
		if(!called){
			throw new IllegalStateException("IllegalState- First value not called");
		}
        double next = current+prev;
        prev = current;
        current = next;
		return current;
	}
	
	@Override	
	public double firstValue() { 
		double value = super.firstValue(); 
		prev = 0; 
		return value; 
	}
	@Override
	public double getTerm(int n){
		if (n <= 0) 
			throw new IndexOutOfBoundsException("printAllTerms: Invalid argument value = " + n); 
		final double additiveGoldenRatio = (1+Math.sqrt(5))/2.0;
		final double substractiveGoldenRatio = (1-Math.sqrt(5))/2.0;
		return Math.floor((1/Math.sqrt(5))*((Math.pow(additiveGoldenRatio,n)-(Math.pow(substractiveGoldenRatio,n)))));
	}


}
