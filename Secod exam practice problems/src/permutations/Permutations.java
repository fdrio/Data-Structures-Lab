package permutations;

import java.util.ArrayList;
import java.util.Scanner;

public class Permutations {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the string to permute: ");
		String inputString = input.nextLine();
		PermutationGenerator permutator = new PermutationGenerator();
		ArrayList<String> permutations = permutator.permutatedStrings(inputString);
		for(String permutation : permutations)
			System.out.println(permutation);

	}

	private static class PermutationGenerator{
		//String string;
		ArrayList<String> permutations;
		public PermutationGenerator(){

			permutations = new ArrayList<>();
		}
		public ArrayList<String> getPermutations(){
			return permutations;
		}
		public ArrayList<String> permutatedStrings(String string){
			ArrayList<String> result = new ArrayList<>();
			if(string.length() == 0){
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
