class Node<E>
{
	
	protected Node<E> next;
	protected E data;
	
	public Node(E data)
	{
		this.data = data;
		next = null;
	}
	
	public Node(E data, Node<E> next)
	{
		this.data = data;
		this.next = next;
	}
	
	
}

public class MazeList<E>
{
	
	private Node<E> head = null;
	private int size = 0;
	
	
	//	Add
	public void add(E item)
	{
		if(head == null)
		{
			head = new Node<>(item);
			size++;
		}
		
		else
		{
			Node<E> newNode = getNode(size-1);
			newNode.next = new Node<>(item);
			size++;
		}
	}
	
	//	Get
	private Node<E> getNode(int index)
	{
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException(Integer.toString(index));
		

		if(index == 0)
			return head;
		else
		{
			Node<E> temp = head;
			for(int i = 0; i < index && temp != null; i++)
				temp = temp.next;
			
			return temp;
		}
	}
	
	public E get(int index)
	{
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException(Integer.toString(index));
		
		Node<E> node = getNode(index);
		return node.data;
	}
	
	// Set
	public void set(int index, E newData)
	{
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException(Integer.toString(index));
	
		Node<E> node = getNode(index);
		node.data = newData;
	}
	

	//	Remove
	public boolean remove(int index)
	{
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException(Integer.toString(index));
		
		if(index == 0) { return removeFirst(getNode(index)); }
		else { return removeAfter(getNode(index-1)); }
		
	}
	
	//	private methods
	private boolean removeAfter(Node<E> node)
	{
		Node<E> temp = node.next;
		if(temp!=null) 
		{
			node.next = temp.next;
			size--;
			return true;
		}
		else
		{
			return false;			
		}
	}
	
	private boolean removeFirst(Node<E> node)
	{
		Node<E> temp = head;
		if(head != null)
		{
			head = head.next;
		}
		
		if(temp != null)
		{
			size--;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	//	Some accessors
	public boolean isEmpty() { return size==0; }
	public int size() { return size; }
	
}






















