package labUtils;

import treeClasses.LinkedBinaryTree;
import treeClasses.LinkedTree;
import treeInterfaces.Position;
import treeInterfaces.Tree;
//
public class Utils {
	public static <E> void displayTree(String msg, Tree<E> t) { 
		System.out.println("\n\n" + msg); 
		t.display(); 
	}

	public static <E> void displayTreeElements(String msg, Tree<E> t) {
		System.out.println("\n\n" + msg); 
		for (E element : t)
			System.out.println(element); 
		
	}
	
	public static LinkedTree<Integer> buildExampleTreeAsLinkedTree() { 
		LinkedTree<Integer> t = new LinkedTree<>(); 
		t.addRoot(4); // root according to the image
		Position<Integer> leftChildOfRoot = t.addChild(t.root(),9);
		Position<Integer> rightChidlOfRoot = t.addChild(t.root(), 20);
	
		t.addChild(leftChildOfRoot,7);
		t.addChild(leftChildOfRoot, 10);
		Position<Integer> fifthteen = t.addChild(rightChidlOfRoot, 15);
		Position<Integer> twentyOne = t.addChild(rightChidlOfRoot, 21);
		t.addChild(fifthteen, 12);
		Position<Integer> sevenTeen= t.addChild(fifthteen,17);
		t.addChild(sevenTeen, 19);
		Position<Integer> forty =t.addChild(twentyOne, 40);
		t.addChild(forty, 30);
		t.addChild(forty, 45);
		
		
		
		// ADD CODE AS SPECIFIED IN EXERCISE 2
		
		
		
		return t; 
	}
	
	public static LinkedBinaryTree<Integer> buildExampleTreeAsLinkedBinaryTree() { 
		LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>(); 
		// ADD CODE AS SPECIFIED IN EXERCISE 2
		t.addRoot(4);
		Position<Integer> leftChildOfRoot = t.addLeft(t.root(), 9);
		Position<Integer> rightChildOfRoot = t.addRight(t.root(), 20);
		t.addLeft(leftChildOfRoot, 7);
		t.addRight(leftChildOfRoot, 10);
		Position<Integer> fifthteen  = t.addLeft(rightChildOfRoot, 15);
		Position<Integer> twentyOne = t.addRight(rightChildOfRoot, 21);
		t.addLeft(fifthteen, 12);
		Position<Integer> sevenTeen = t.addRight(fifthteen, 17);
		t.addLeft(sevenTeen, 19);
		Position<Integer> forty = t.addRight(twentyOne, 40);
		t.addLeft(forty, 30);
		t.addRight(forty,45);
		
		
		
		
		return t; 
	}


}
