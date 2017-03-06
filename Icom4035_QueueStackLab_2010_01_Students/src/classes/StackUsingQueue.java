package classes;

import interfaces.Stack;

public class StackUsingQueue<E> implements Stack<E>{
	 
	private int size; 
	private LLQueue<E> queue1;
	private LLQueue<E> queue2;// remains empty
	
	public StackUsingQueue() {
		//top = null; 
		size = 0; 
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		if(size ==0)
			throw new EmptyStackException();
		E answer = queue1.front();
		return answer;
	}

	@Override
	public E pop() throws EmptyStackException {
		if(size ==0)
			throw new EmptyStackException();
		E answer = queue1.dequeue();
		return answer;
	}

	@Override
	public void push(E e) {
		queue2.enqueue(e);
		for(int i =0; i<size;i++){
			queue2.enqueue(queue1.dequeue());
		}
		size++;
		LLQueue<E> temp = queue1;
		queue1 = queue2;
		queue2= temp;
		
	}
	

}
