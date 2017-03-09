package positionListLLDirect;

import java.util.Iterator;

import exceptionClasses.BoundaryViolationException;
import exceptionClasses.EmptyListException;
import exceptionClasses.InvalidPositionException;
import positionInterfaces.Position;
import positionInterfaces.PositionList;






public class NewNodePositionList<T> implements PositionList<T>{
	private DNode<T> header, trailer; 
	private int count; 
	public NewNodePositionList(){
		header = new DNode<T>(null, null, null, this); 
		trailer = new DNode<T>(null, header, null, this);
		header.setNext(trailer); 
		count = 0; 
	}
	private DNode<T> checkPosition(Position<T> p) throws InvalidPositionException { 
		// in order to be valid: p must represent a valid data position inside
		// the list where it is applied. Since in this case the positions are 
		// implemented as DNode, p must also be of type DNode.
		if (p==null) 
			throw new InvalidPositionException("Invalid position: null reference."); 
	
		try {
			DNode<T> node = (DNode<T>) p;
			// check if position is header or trailer
			if (node.getPrev()==null || node.getNext()==null)    
				throw new InvalidPositionException("Not a valid data position."); 
			if (node.getList()!=this) 
				throw new InvalidPositionException
				  ("Position does not belong to the right list.");
			return node; 			
		}
		catch (ClassCastException e) { 
			throw new InvalidPositionException("Position is not of type DNode"); 
		}
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<T> first() throws EmptyListException {
		if(this.isEmpty())
			throw new EmptyListException("List is empty");
		return header.getNext();
	}

	@Override
	public Position<T> last() throws EmptyListException {
		// TODO Auto-generated method stub 
		if(this.isEmpty())
			throw new EmptyListException("List is empty");
		return trailer.getPrev();
	}

	@Override
	public Position<T> next(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<T> node = checkPosition(p);
		DNode<T> next = node.getNext();
		if (next==trailer)
			throw new BoundaryViolationException
			 ("Cannot advance past the end of the list.");
		return next;
		
	}

	@Override
	public Position<T> prev(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<T> node = checkPosition(p); 
		DNode<T> prev = node.getPrev(); 
		if (prev==header)
			throw new BoundaryViolationException
			 ("Cannot advance past the beginning of the list.");
		return prev;
	}

	@Override
	public T remove(Position<T> p) throws InvalidPositionException {
		DNode<T> node = checkPosition(p); 
		T e2r = node.element(); 
		count--; 
		DNode<T> pNode = node.getPrev(); 
		DNode<T> aNode = node.getNext(); 
		pNode.setNext(aNode); 
		aNode.setPrev(pNode); 
		
		// disconnect from rest of list the position to remove ....
		node.cleanLinks(); 
		return e2r; 
	}

	@Override
	public T set(Position<T> p, T e) throws InvalidPositionException {
		DNode<T> node = checkPosition(p); 
		T e2r = node.element(); 
		node.setElement(e); 
		return e2r;
	}

	@Override
	public void addFirst(T e) {
		// TODO Auto-generated method stub
		DNode<T> nuevo = new DNode<T>(e, header, header.getNext(), this); 
		header.getNext().setPrev(nuevo); 
		header.setNext(nuevo); 
		count++; 
		
	}

	@Override
	public void addLast(T e) {
		DNode<T> nuevo = new DNode<T>(e, trailer.getPrev(), trailer, this); 
		trailer.getPrev().setNext(nuevo); 
		trailer.setPrev(nuevo); 
		count++; 
		
	}

	@Override
	public void addAfter(Position<T> p, T e) throws InvalidPositionException {
		DNode<T> nodo = checkPosition(p); 
		DNode<T> nuevo = new DNode<T>(e, nodo, nodo.getNext(), this); 
		nodo.getNext().setPrev(nuevo); 
		nodo.setNext(nuevo); 
		count++;
		
	}

	@Override
	public void addBefore(Position<T> p, T e) throws InvalidPositionException {
		DNode<T> nodo = checkPosition(p); 
		DNode<T> nuevo = new DNode<T>(e, nodo.getPrev(), nodo, this); 
		nodo.getPrev().setNext(nuevo); 
		nodo.setPrev(nuevo); 
		count++; 
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (count == 0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Iterable<Position<T>> positions() {
		NodePositionList<Position<T>> pList = new NodePositionList<Position<T>>(); 
		try { 
			if (!this.isEmpty()) { 
				Position<T> current = this.first(); 
				while (current != this.last()) { 
					pList.addLast(current); 
					current = this.next(current); 
				}			
			}
		} catch (Exception e) { 
			System.out.println("NodePositionList - positions(): Invalid list"); 
			e.printStackTrace(); 
		}
		return pList;
	}
	/****************************************************************************************/
	/*  Private class implementing Position<>  For this implementation of the PositionList  */
	/*  it has to be a DNode<>. Note that under this implementation each node has a         */
	/*  reference to the list that it belongs to. It must be at most one.                   */
	/****************************************************************************************/
	private static class DNode<T> implements Position<T> {
        private T element; 
        private DNode<T> next, prev; 
        private NewNodePositionList<T> myList;     // list it belongs to
        
        public DNode(T e, DNode<T> p, DNode<T> n, NewNodePositionList<T> newNodePositionList) { 
        	element = e; 
        	next = n; 
        	prev = p; 
        	myList = newNodePositionList; 
        }
		public T element() {
			return element;
		} 
		public void setElement(T e) { 
			element = e; 
		}
		public void setNext(DNode<T> n) { 
			next = n; 
		}
		public DNode<T> getNext() { 
			return next; 
		}
		public void setPrev(DNode<T> p) { 
			prev = p; 
		}
		public DNode<T> getPrev() { 
			return prev; 
		}
		public void setList(NewNodePositionList<T> n) { 
			myList = n; 
		}
		public NewNodePositionList<T> getList() { 
			return myList; 
		}
		
		public void cleanLinks() { 
			next = prev = null; 
		}
		
		public void cleanAll() { 
			element = null; 
			cleanLinks(); 
		}
		
	}

	

}
