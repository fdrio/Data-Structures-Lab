package positionListLLDirect;

import java.util.Iterator;

import exceptionClasses.BoundaryViolationException;
import exceptionClasses.EmptyListException;
import exceptionClasses.InvalidPositionException;
import positionInterfaces.Position;
import positionInterfaces.PositionList;

public class NewNodePositionList<T> implements PositionList<T>{

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<T> first() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<T> last() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<T> next(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<T> prev(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove(Position<T> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T set(Position<T> p, T e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(T e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(T e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAfter(Position<T> p, T e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBefore(Position<T> p, T e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Position<T>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
