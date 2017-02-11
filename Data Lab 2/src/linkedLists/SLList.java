/**
 * 
 */
package linkedLists;

/**
 * @author pirvos
 *
 */
public class SLList<E> implements LinkedList<E> {
	private SNode<E> head; 
	private int length; 

	public SLList() { 
		head = null; 
		length = 0; 
	}
	
	public void addFirstNode(Node<E> nuevo) {
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(head); 
		head = (SNode<E>) nuevo; 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());  //nuevo goes before the node that is after target
		((SNode<E>) target).setNext((SNode<E>) nuevo);  //sets nuevo as the next element after target
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list

		if (target == head) //if target is the first element
			this.addFirstNode(nuevo); //adds nuevo as head
		else { 
			Node<E> prevNode = findNodePrevTo(target);  //finds the node that comes before the target
			this.addNodeAfter(prevNode, nuevo); //ads nuevo before target and after pevNode
		}
	}

	private Node<E> findNodePrevTo(Node<E> target) {
		// Pre: target is a node in the list
		if (target == head) 
			return null; //returns null if there is no node before target (target is head)
		else { 
			SNode<E> prev = head; //prev is the first element at first
			while (prev != null && prev.getNext() != target) //while prev is not null (which happens when prev takes the node after the last node) and prev equals not the target
				prev = prev.getNext();  //prev equals the next node
			return prev;
		}
	}

	public Node<E> getLastNode() 
	throws NodeOutOfBoundsException 
	{
		if (head == null)
			throw new NodeOutOfBoundsException("getLastNode(): Empty list.");  //if empty is list there is no final node
		else { 
			SNode<E> curr = head; //current equals the first node
			while (((SNode<E>) curr).getNext() != null) //while current is not a reference to the node that goes after last (which is null)
				curr = curr.getNext();  
			return curr; 
		}
	}

	public Node<E> getNodeAfter(Node<E> target) 
	throws NodeOutOfBoundsException 
	{
		// Pre: target is a node in the list
		SNode<E> aNode = ((SNode<E>) target).getNext(); //aNode takes the link to the following node
		if (aNode == null)  //if aNode is null, target is the last element
			throw new NodeOutOfBoundsException("getNextNode(...) : target is the last node."); 
		else 
			return aNode;
	}


	public Node<E> getNodeBefore(Node<E> target) 
	throws NodeOutOfBoundsException 
	{
		// Pre: target is a node in the list
		if (target == head)  //if target is the first element in the list it doesn't has any Node before it
			throw new NodeOutOfBoundsException("getPrevNode(...) : target is the first node."); 
		else 
			return findNodePrevTo(target); //this useful method will do it all
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		// Pre: target is a node in the list
		
		if (target == head) { //if target node to be removed is head, the first element
			// the list is not empty....
			SNode<E> ntr = head;  //take it
			head = head.getNext(); //make the next node be head now
			ntr.setNext(null);      // notice that the node keeps its data.. but cuts reference to nodes in the list
		}
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); //takes th8e previous node
			prevNode.setNext(((SNode<E>) target).getNext()); //cut it reference to target and point it to the node after target
		}
		length--; 
	}

	
	public Node<E> getFirstNode() 
	throws NodeOutOfBoundsException 
	{
		if (head == null)
			throw new NodeOutOfBoundsException("getFirstNode() : linked list is empty..."); 
		
		// the linked list is not empty....
		return head;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (head != null) { 
			SNode<E> nnode = head.getNext(); 
			head.setElement(null); 
			head.setNext(null); 
			head = nnode; 
			//cuts each element begining with head
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes
	 * from the current instance of the list. 
	 */
	public void makeEmpty() { 
		// TODO
		//well, this is Empty XD
	}
		
	protected void finalize() throws Throwable {
		//System.out.println("GC is WORKING!");
		//System.out.println("Number of nodes to remove is: "+ this.length); 
		try {
			this.destroy(); //if it cannot destroy, try upper finalize()
		} finally {
			super.finalize();
		}
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

	// private and static inner class that defines the 
	// type of node that this list implementation uses
	private static class SNode<T> implements Node<T> {
		private T element; 
		private SNode<T> next; 
		public SNode() { 
			element = null; 
			next = null; 
		}
		public SNode(T data, SNode<T> next) { 
			this.element = data; 
			this.next = next; 
		}
		public SNode(T data)  { 
			this.element = data; 
			next = null; 
		}
		public T getElement() {
			return element;
		}
		public void setElement(T data) {
			this.element = data;
		}
		public SNode<T> getNext() {
			return next;
		}
		public void setNext(SNode<T> next) { //this works because you don't need to find the element, since they are giving you reference to it
			this.next = next;
		}
	}

}
