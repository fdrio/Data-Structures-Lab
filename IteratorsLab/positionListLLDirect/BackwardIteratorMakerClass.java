package positionListLLDirect;

import java.util.Iterator;

import positionInterfaces.PositionList;

public class BackwardIteratorMakerClass<T> implements PositionListIteratorMaker<T>{

	@Override
	public Iterator<T> makeIterator(PositionList<T> positionList) {
		// TODO Auto-generated method stub
		return new PositionListElementsIterator<T>(positionList);
	}

}
