package classes;

import interfaces.Stack;

public class StackUsingQueue<E> implements Stack<E>{
	 
	private int size; 
	private LLQueue<E> queue1; // representation of the stack
	private LLQueue<E> queue2; // auxiliary queue
	
	
	public StackUsingQueue() {
		
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
		size--;
		return answer;
	}

	@Override
	public void push(E e) {
		// adds element to the other empty queue 
		//so it is the first one in the queue or mainly at the top of the stack
		queue2.enqueue(e);
		
		// Adds the from  elements of queue1 to queue2, where queue2 is the temporary queue
		for(int i =0; i<size;i++){
			queue2.enqueue(queue1.dequeue());
		}
		// increment the size of the stack
		size++;
		
		// swap the queues such that queue2 always remains empty and queue1 represents that stack itself 
		LLQueue<E> temp = queue1;
		queue1 = queue2;
		queue2= temp;
		
	}
	

}
