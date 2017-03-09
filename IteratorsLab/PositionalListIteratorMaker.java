import java.util.Iterator;

import positionInterfaces.PositionList;

public interface PositionalListIteratorMaker<E> {
	 Iterator<E> makeIterator(PositionList<E> pl); 
}
