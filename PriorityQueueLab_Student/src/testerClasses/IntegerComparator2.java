package testerClasses;

import java.util.Comparator;
// Integer Comparator
public class IntegerComparator2 implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		 return -o1.compareTo(o2);
	}

}
