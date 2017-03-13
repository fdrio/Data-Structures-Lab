package positionListLLDirect;

import positionInterfaces.Position;

public class IteratorMakerTester {
public static void main(String[] args) {
		
		NodePositionListIteratorMaker<Integer> w = new NodePositionListIteratorMaker<Integer> (new ForwardIteratorMakerClass<>()); 
		NodePositionListIteratorMaker<Integer> q = new NodePositionListIteratorMaker<Integer> (new BackwardIteratorMakerClass<>()); 
		q.addFirst(10); 
		q.addFirst(6);
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
		System.out.println("----");
		showElements(q);
		

	}

	private static <E> void showElements(NodePositionList<E> w) {
		for(E p : w) 
			System.out.println(p); 
	}
	private static <E> void showElements(NewNodePositionList<E> w) {
		for(E p : w) 
			System.out.println(p); 
	}
	
	private static <E> void showElements(NodePositionListIteratorMaker<E> w){
		for(E p: w){
			System.out.println(p);
		}
	}


}
