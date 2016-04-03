package algorithm;

/**
 *  @author Jihun Nam
 *  Stack data structure 
 *  
 */

public class Stack {

	private int head = 0;
	private int MAX = 10;
	private int[] Stack;
	
	public Stack(int arrayLength)
	{
		this.MAX = arrayLength;
		this.Stack = new int[arrayLength];
	}
 
	public void push(int num)
	{
		if(head>=MAX)
		{
			System.out.println("Stack Overflow");
			System.exit(1);
		}
		
		Stack[head++] = num;
	}
	
	public int pop()
	{
		if(!isEmpty())
		{
			return Stack[--head];
		}
		
		System.out.println("Stack Underflow");
		System.exit(1);
		
		return -1;
	}
	
	public int viewPop()
	{
		if(!isEmpty())
		{
			int tmp = head;
			
			return Stack[--tmp];
		}
		
		System.out.println("Stack Underflow");
		System.exit(1);		
		
		return -1;
	}
	
	public void removeStack()
	{
		this.head = 0;
	}
	
	public boolean isEmpty()
	{
		return head == 0 ? true : false;
	}
	
	public void printStack()
	{
		for(int i=0;i<MAX;i++)
		{
			System.out.println(Stack[i] + "\n");
		}
	}

}
