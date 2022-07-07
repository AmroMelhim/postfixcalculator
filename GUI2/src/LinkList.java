
public class LinkList{
	private class Node {

		public Object Data; // data item
		public Node next; // next Node in list

		public Node(Object data) // constructor
		{
			Data = data;
		}

		public void displayNode() // display ourself
		{
			System.out.print(Data + " ");
		}
	} // end class Node

	private Node first;

	public LinkList() // constructor
	{
		first = null;
	} // no items on list yet

	public boolean isEmpty() // true if list is empty
	{
		return (first == null);
	}

	public void insertFirst(Object data) // insert at start of list
	{ // make new Node
		Node newNode = new Node(data);
		newNode.next = first; // newNode = old first
		first = newNode; // first = newNode
	}

	public Object deleteFirst() // delete first item
	{
		if (first != null) {
			Node temp = first; // save reference to Node
			first = first.next; // delete it: first=old next
			return temp.Data; // return deleted Node
		}
		return 0;
	}

	public void displayList() {
		Node current = first; // start at beginning of list
		while (current != null) // until end of list,
		{
			current.displayNode(); // print data
			current = current.next; // move to next Node
		}

		System.out.println("");
	}
	public Object getFirst() {
		if(first!=null)
			return first.Data;
		return 0;
	}
}