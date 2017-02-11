package testers;
import linkedLists.DLDHDTList;
import linkedLists.Node;
public class DLDTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DLDHDTList<Integer> list = new  DLDHDTList<>();
		Node<Integer> node = list.createNewNode();
		
		node.setElement(3);
		list.addFirstNode(node);
		
		node = list.createNewNode();
		node.setElement(5);
		list.addNodeAfter(list.getFirstNode(), node);
		
		node = list.createNewNode();
		node.setElement(10);
		
		list.addNodeBefore(list.getFirstNode(), node);
		
		node = list.createNewNode();
		node.setElement(15);
		
		list.addFirstNode(node);
		for(int index=0; index<list.length(); index++){
			System.out.println(node.getElement());
			node = list.getNodeAfter(node);
		}
		System.out.println("--------------");
		list.makeEmpty();
		System.out.println("Testing making the list empty method. There should be no print after this since the list is supposed to be empty.");
		
		for(int index=0; index<list.length(); index++){
			System.out.println(node.getElement());
			node = list.getNodeAfter(node);
		}
	

	}

}
