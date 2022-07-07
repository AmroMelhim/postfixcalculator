public class LinkStack{

	private LinkList list;

	public LinkStack() // constructor
	{
		list = new LinkList();
	}

	public void push(Object data) // put item on top of stack
	{
		list.insertFirst(data);
	}

	public Object pop() // take item from top of stack
	{
		return  list.deleteFirst();
	}

	public boolean isEmpty() // true if stack is empty
	{
		return (list.isEmpty());
	}

	public void displayStack() {
		list.displayList();
	}
	public Object peek() {
		
		return list.getFirst();
		
	}

}