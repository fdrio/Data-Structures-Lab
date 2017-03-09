package arrayIterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardArrayIterator<E> implements Iterator<E> {

	private E[] arr;    // the array to iterate over
	private int current;// other internal fields...
	private int size;
	
	public ForwardArrayIterator(E[] arr) { 
		this.arr = arr; 
		this.current = 0;
		//this.size = 0;
		
		// initialize other internal fields as needed
	}
	
	public boolean hasNext() {
		
		return (current <arr.length);   
	}

	public E next() throws NoSuchElementException {
		if (!hasNext())
			throw new 
				NoSuchElementException("No more elements to iterate over."); 
		int currentPos = current;
		current++;
		return arr[currentPos];
	}

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
				"Remove peration not implemented.");
		// do not implement
	}
}
