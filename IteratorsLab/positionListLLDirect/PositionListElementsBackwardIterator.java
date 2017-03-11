package positionListLLDirect;

import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptionClasses.EmptyListException;
import positionInterfaces.Position;
import positionInterfaces.PositionList;

public class PositionListElementsBackwardIterator<T> implements Iterator<T>, NodePositionListIteratoMaker<T>{
	private Position<T> current; 
	private PositionList<T> theList; 
	
	public PositionListElementsBackwardIterator(PositionList<T> list) { 
		theList = list; 
		try { 
			current = theList.last(); 
		}
	    catch (EmptyListException e) { 
	    	current = null; 
	    }
	}
	
	

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return (current != null);
	}

	@Override
	public T next() {
		if (!hasNext()) 
			throw new NoSuchElementException("Iterator has past the end.");
		Position<T> ptr = current; 
		try { 
			current = theList.prev(current); 
		}
		catch (Exception e) { 
			current = null; 
		}
		return ptr.element();
	
		
	}

}
