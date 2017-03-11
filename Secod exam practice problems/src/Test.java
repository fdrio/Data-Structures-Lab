
public class Test {

	public static void main(String[] args) {
		for(int i=0; i<11; i++){
			System.out.println(i+": "+v(i));
		}
		System.out.println("--------");
		for(int i=0; i<11; i++){
			System.out.println(i+": "+v2(i));
		}
		

	}
	
	public static int v(int n){
		int r = 0;
		for (int i=0; i<n; i++){
			for (int j=i; j<n; j++)
				r+=1;
			r+=1;
		}
		
		return r;
	}
	public static int v2(int n){
		return ((n*n) +3*n)/2  ;
	}
	

}
