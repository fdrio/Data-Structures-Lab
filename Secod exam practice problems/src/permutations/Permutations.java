package permutations;

import java.util.ArrayList;
import java.util.Scanner;

public class Permutations {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the index of the alphabet to permute: ");
		int stringIndex = input.nextInt();
		String alphabet ="abcdefghijklmnopqrstuvwxyz";
		String substring = alphabet.substring(0, stringIndex);
		//PermutationGenerator permutator = new PermutationGenerator();
		ArrayList<String> permutations = subsets(substring);//permutator.permutatedStrings(substring);
		for(String permutation : permutations)
			System.out.println(permutation);
//		int[] input ={1,2,3,4,5};
//		permutate(1,input);
	}
	private static ArrayList<String> subsets(String s) {
		ArrayList<String> ss = new ArrayList<>();
		if (s.length() == 0){
			ss.add(s);
			
		}
		else {
			String st = s.substring(1); // remove the first element in set (first char in string)
			String removed = s.substring(0, 1);

			ArrayList<String> tSS = subsets(st);
			for (String r : tSS){
				if(!ss.contains(r)){
					ss.add(r);
				}
			}

			for (String r : tSS){
				if(!ss.contains(removed+r)){
					ss.add(removed+r);
				}
			}

		}
		return ss;
	}
	public static void permutate(int i,int[] input){
		  //starts  
		      //  n is the size of input.
		     int n = input.length;
		    if(i==n){
		        System.out.println(input[i-1]);

		    }
		    else {
		        for(int j=i; j<n; j++){

		            swap(i,j,  input);
		            permutate( i+1,input);
		            swap( i,j, input);

		        }
		    }
		    //end
		}
	public static void swap(int index1, int index2,int []a){
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	private static class PermutationGenerator{
		
		ArrayList<String> permutations;
		public PermutationGenerator(){

			permutations = new ArrayList<>();
		}
		public ArrayList<String> getPermutations(){
			return permutations;
		}
		public ArrayList<String> permutatedStrings(String string){
			ArrayList<String> result = new ArrayList<>();
			if(string.length() == 1){
				result.add(string);
				return result;

			}

			
				for(int i=0; i<string.length();i++){
					char removed = string.charAt(i);
					String substring = string.substring(0,i)+string.substring(i+1);
					ArrayList<String> shorterPermutation = permutatedStrings(substring);
					for(String permute: shorterPermutation){
						result.add(removed + permute);
					}
					
				}
			
			return result;
		}

	}

}
