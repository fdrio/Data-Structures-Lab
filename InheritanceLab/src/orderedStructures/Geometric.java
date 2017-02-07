package orderedStructures;

public class Geometric extends Progression {

	private double commonFactor; 
	
	public Geometric(double firstValue, double commonFactor) { 
		super(firstValue); 
		this.commonFactor = commonFactor; 
	}
	
	@Override
	public double nextValue()throws IllegalStateException{
		if(!called){
			throw new IllegalStateException("IllegalState- First value not called");
		}
		current = current * commonFactor; 
		return current;
	}
	@Override 
	public String toString(){
		return "Arith(" +(int)firstValue()+", "+ (int)commonFactor +")";
	}
	@Override
	public double getTerm(int n){
		if (n <= 0) 
			throw new IndexOutOfBoundsException("printAllTerms: Invalid argument value = " + n); 
		return firstValue()*Math.pow(commonFactor, n-1);
	}


}
