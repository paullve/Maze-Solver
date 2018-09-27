import java.util.*;

/**
* Stack.java
* Using a custom Linked List in a stack, as shown in class/our textbook
*/
public class MazeStack<E>
{

	//	A list to hold our data
	private MazeList<E> theData;

	/*
	* Constructor, which simply instantiates the list
	*/
	public MazeStack()
	{
		theData = new MazeList<>();
	}


	/**
	* Puts an object on to the top of the stack
	*@param obj, an object to be placed on the stack
	*@return the object pushed on to the stack
	*/
	public E push(E obj)
	{
		theData.add(obj);
		return obj;
	}

	/**
	* Shows the top element of the stack without removing it
	*@throws NoSuchElementException if the stack is empty
	*@return the element on top of the stack
	*/
	public E peek()
	{
		if(isEmpty())
			throw new NoSuchElementException("Error: Stack is empty");

		return theData.get(theData.size() - 1);
	}

	/**
	* Shows the top element of the stack, THEN removes it
	*@throws NoSuchElementException if the stack is empty
	*@return the element on top of the stack
	*/
	public E pop()
	{
		if(isEmpty())
			throw new NoSuchElementException("Error: Stack is empty");

		E result = theData.get(theData.size() - 1);
		theData.remove(theData.size() - 1);
		return result;
	
	}

	/**
	* Whether or not the stack is empty
	*@return true if stack is empty, false if it isn't
	*/
	public boolean isEmpty()
	{
		return theData.isEmpty();
	}
	
	public int size() { return theData.size(); }


}