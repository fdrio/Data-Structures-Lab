import java.util.Scanner;

public class Power {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the base: ");
		int base = input.nextInt();
		System.out.println("Enter the exponent: ");
		int exponent = input.nextInt();
		System.out.println("---------");
		System.out.println(pow(base,exponent));

	}
	public static int pow(int x,int n){
		int partial;
		if(n==0){
			return 1;
			}
		 partial = pow(x, n/2);
		if(n%2 ==0){
			return (partial*partial);
			}
		else{
			
				return (partial*partial*x);
			}
		}
	public static int getPower(int base, int exponent){
		/* Recursion termination condition,
		 * Anything^0 = 1
		 */
		int result;
		if(exponent == 0){
			return 1;
		}
		result = getPower(base, exponent/2);
		if(exponent%2 == 0){
			/*Exponent is even */
			System.out.println(result*result);
			return result*result;
		} else {
			/*Exponent is odd */
			System.out.println(base*result*result);
			return base*result*result;
		}
	}
		
	}


