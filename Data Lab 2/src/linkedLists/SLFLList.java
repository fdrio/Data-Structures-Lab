package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import linkedLists.LinkedList;

public class SLFLList<E> 
implements LinkedList<E>
{

	private SNode<E> first, last; 
	int length = 0; 

	public SLFLList() { 
		first = last = null; 
	}


	public void addFirstNode(Node<E> nuevo) {
		// TODO Auto-generated method stub
		SNode<E>  node = (SNode<E>)nuevo;
		if(length==0){
			first = last = node;
		}
		else{
			node.setNext(first);
			first = node;
		}
		length++;

	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		SNode<E> targ = (SNode<E>) target, node = (SNode<E>) nuevo;
		if(target==last){
			targ.setNext(node);
			last = node;
		}
		else{
			node.setNext(targ.getNext());
			targ.setNext(node);
		}
		length++;

	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		SNode<E> targ = (SNode<E>) target, node = (SNode<E>) nuevo;
		if(targ == first){
			node.setNext(first);
			first = node;
			length++;
		}
		else
			addNodeAfter(getNodeBefore(target),nuevo);


	}

	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		// TODO Auto-generated method stub
		return first;
	}

	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		// TODO Auto-generated method stub
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NodeOutOfBoundsException {
		// TODO Auto-generated method stub
		SNode<E> targ = (SNode<E>) target;
		return targ.getNext();
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NodeOutOfBoundsException {
		// TODO Auto-generated method stub
		//Pre: Node target is in list
		SNode<E> targ,curr;
		targ = (SNode<E>) target;
		if(targ == first || length == 1) {
			System.out.println("There is no node before first node");
			return null;
		}
		curr = (SNode<E>) getFirstNode();
		while(curr.getNext()!=targ && curr.getNext()!=last){
			curr = curr.getNext();

		}


		return curr;
	}

	public int length() {
		// TODO Auto-generated method stub
		return length;
	}

	public void removeNode(Node<E> target) {
		// TODO Auto-generated method stub
		SNode<E> prev, targ;
		targ = (SNode<E>) target;
		if(targ == first){
			if(length==1){
				first = last = null;
			}

			else{
				first = targ.getNext();
			}

		}

		else{

			prev = (SNode<E>) getNodeBefore(target);
			if(targ == last){
				last = prev;
			}
			else{
				prev.setNext(targ.getNext());

			}
		}

		targ.setNext(null);
		targ.setElement(null);
		length--;

	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}


	///////////////////////////////////////////////////
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
		public void setNext(SNode<T> next) {
			this.next = next;
		}
	}

}
