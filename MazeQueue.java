import java.util.*;

/**
* Queue, using a custom Single Linked List, also shown in class and in the text book
*/
public class MazeQueue<E>
{

	private MazeList<E> theData;

	public MazeQueue()
	{
		theData = new MazeList<>();
	}

	/**
	* Adds an element to the queue
	*@param item, an item to be added to the queue
	*@return true if it was added, false if it wasn't
	*/
	public boolean offer(E item)
	{
		theData.add(item);
		return true;
	}
	
	/**
	 * Removes the first element of the queue
	 * @return the element that was removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E remove()
	{
		if(theData.size() == 0)
			throw new NoSuchElementException("Error: Queue is empty");
		else
		{
			E result = theData.get(0);
			theData.remove(0);
			return result;
		}
	}

	/**
	 * Removes the first element
	 * @return the removed element, or null if the queue is empty
	 */
	public E poll()
	{
		if(theData.size() == 0)
			return null;
		else
		{
			E result = theData.get(0);
			theData.remove(0);
			return result;
		}
			
	}
	
	/**
	 * Returns the first element of the queue without removing it
	 * @return the first element
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E element()
	{
		if(theData.size() == 0)
			throw new NoSuchElementException("Error: Queue is empty");
		else
			return theData.get(0);
	}

	/**
	 * Returns the first element of the queue without removing it
	 * @return the first element, or null of the queue is empty
	 */
	public E peek()
	{
		if(theData.size() == 0)
			return null;
		else
			return theData.get(0);
	}

	/**
	 * @return true if the queue is empty, false if it isn't.
	 */
	public boolean isEmpty()
	{
		return theData.size() == 0;
	}

}