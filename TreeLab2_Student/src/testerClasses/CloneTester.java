package testerClasses;

import labUtils.Utils;
import treeClasses.LinkedBinaryTree;
import treeClasses.LinkedTree;

public class CloneTester {
	public static void main(String args[]){
		LinkedTree<Integer> t = new LinkedTree<>();

		System.out.println("Original tree:");
		t.display();
		System.out.println();
		LinkedTree<Integer> clonedTree = null;
		try {
			clonedTree = t.clone();
			System.out.println("Cloned Tree: ");
			clonedTree.display();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t = Utils.buildExampleTreeAsLinkedTree();
		System.out.println("Original Tree;");
		System.out.println();
		try {
			clonedTree = t.clone();
			System.out.println("Cloned Tree: ");
			clonedTree.display();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LinkedBinaryTree<Integer> linkedBinaryTree = new LinkedBinaryTree<>();
		LinkedBinaryTree<Integer> clonedLinkedBinaryTree = null;
		try{ 
			clonedLinkedBinaryTree = linkedBinaryTree.clone(); 
		}catch(Exception e){ 
			e.printStackTrace(); }
		Utils.displayTree("Original tree: ", linkedBinaryTree); 
		Utils.displayTree("Cloned tree: ", clonedLinkedBinaryTree); 
		linkedBinaryTree = Utils.buildExampleTreeAsLinkedBinaryTree();
		try {
			clonedLinkedBinaryTree = linkedBinaryTree.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Utils.displayTree("The tree is: ", linkedBinaryTree); 
		Utils.displayTree("The tree is: ", clonedLinkedBinaryTree); 





	}
}
