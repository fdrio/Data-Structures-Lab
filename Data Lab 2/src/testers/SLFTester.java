package testers;
import linkedLists.*;
public class SLFTester {
	
	public static void main(String[] args){
		SLFLList<Integer> list = new SLFLList<>();
		Node<Integer> node, test;
		
		node = list.createNewNode();
		node.setElement(1);
		list.addFirstNode(node);
		test = node;
		
		node = list.createNewNode();
		node.setElement(2);
		list.addNodeAfter(list.getLastNode(), node);
		
		node = list.createNewNode();
		node.setElement(3);
		list.addNodeAfter(test, node);

		node = list.createNewNode();
		node.setElement(4);
		list.addNodeBefore(list.getLastNode(), node);
		
		
		System.out.println("Size = " + list.length());
		test = list.getFirstNode();
		for(int index = 0; index<list.length(); index++){
			System.out.println(test.getElement());
			test = list.getNodeAfter(test);
		}
		
		
		//		System.out.println(list.getFirstNode().getElement());
//		System.out.println(list.getNodeAfter(list.getFirstNode()).getElement());
//		System.out.println(list.getLastNode().getElement());
//		System.out.println(list.length());
		
	}

}
