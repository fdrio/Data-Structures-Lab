package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import linkedLists.LinkedList;
import linkedLists.SLFLList.SNode;

public class SLFLList<E> 
implements LinkedList<E>
{

	private SNode<E> first, last; 
	int length = 0; 

	public SLFLList() { 
		first = last = null; 
	}


	public void addFirstNode(Node<E> nuevo) {
		SNode<E> newest = (SNode<E>) nuevo;
		newest.setNext(first);
		first = newest;

		if(length() == 0){
			last = newest;
		}
		length++;

		// TODO Auto-generated method stub

	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {

		SNode<E> sTarget = (SNode<E>) target, sNuevo = (SNode<E>) nuevo;
		if(target==last){
			sTarget.setNext(sNuevo);
			last = sNuevo;
		}
		else{
			sNuevo.setNext(sTarget.getNext());
			sTarget.setNext(sNuevo);
		}
		length++;

	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		SNode<E> sTarget = (SNode<E>) target;
		SNode<E> sNuevo = (SNode<E>) nuevo;
		if(target == first){
			addFirstNode(target);
		}
		else {
			SNode<E> prev = (SNode<E>)getNodeBefore(target);
			prev.setNext(sNuevo);
			sNuevo.setNext(sTarget);	
		}
		length++;

	}

	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		if(length == 0)
			 throw new NodeOutOfBoundsException();
		

		return first;
	}

	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		if(length == 0)
			 throw new NodeOutOfBoundsException();
		// TODO Auto-generated method stub
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NodeOutOfBoundsException {
		if(length == 0)
			 throw new NodeOutOfBoundsException();
		// TODO Auto-generated method stub
		SNode<E> snTarget = (SNode<E>) target;
		if(snTarget == null){
			throw new NodeOutOfBoundsException();
		}

		return snTarget.getNext();
	}

	public Node<E> getNodeBefore(Node<E> target)throws NodeOutOfBoundsException {
		if(length == 0)
			 throw new NodeOutOfBoundsException();
		SNode<E> prev = null;
		if(target == first){
			 throw new NodeOutOfBoundsException();
		}
		prev = first;
		while(prev!= null && prev.getNext()!= target){
			prev = prev.getNext();
		}
		return prev;
	}

	public int length() {
		// TODO Auto-generated method stub
		return this.length;
	}

	public void removeNode(Node<E> target) {
		if(length == 0) {return;}

		SNode<E> sTarget = (SNode<E>) target;

		if(sTarget == first)
		{
			first= sTarget.getNext();
			if(length ==1){
				last =null;
			}
			
		}

		else 
		{
			if(length == 1){
				last = first = null;
			}
			else {
				SNode<E> prev = (SNode<E>)getNodeBefore(sTarget);
				prev.setNext(sTarget.getNext());
				if (sTarget == last){
					last = prev;
				}
			}

		}
		// Clearing the object
		sTarget.setElement(null);
		sTarget.setNext(null);
		length--;
	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}


	///////////////////////////////////////////////////
	// private and static inner class that defines the 
	// type of node that this list implementation uses
	public static class SNode<T> implements Node<T> {
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
