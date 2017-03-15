package positionListLLDirect;

import positionInterfaces.Position;

public class NodePositionListTester1 {
	public static void main(String[] args) {
		
		NodePositionList<Integer> w = new NodePositionList<Integer> (); 
		NodePositionList<Integer> q = new NodePositionList<Integer> (); 
		
		q.addFirst(10); 
		
		w.addFirst(5); 
		w.addFirst(3); 
		
		w.addLast(10); 
		w.addLast(13); 
		try {
			Position<Integer> p = w.first();
			w.addAfter(p, 2); 
			p = w.next(p); 
			w.addAfter(p, 34); 
			w.addBefore(p, 40); 
			//p = q.first(); 
			w.addAfter(p, 89); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		showElements(w); 
		System.out.println("--------");
		showElementsBackwards(w);

	}

	private static <E> void showElements(NodePositionList<E> w) {
		for(E p : w) 
			System.out.println(p); 
	}
//	private static <E> void showElements(NewNodePositionList<E> w) {
//		for(E p : w) 
//			System.out.println(p); 
//	}
//	
	private static <E> void showElementsBackwards(NodePositionList<E> w){
		
		for( PositionListElementsBackwardIterator<E> iter = new PositionListElementsBackwardIterator<E>(w); iter.hasNext(); ){
			E current = iter.next();
			System.out.println(current);
		}
	}
	

}
