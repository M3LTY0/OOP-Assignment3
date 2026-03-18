package sait.sll.utility;


public interface LinkedListADT 
{
	boolean isEmpty();

	void clear();
	
	void append(Object data);

	void prepend(Object data);
	
	void insert(Object data, int index) throws IndexOutOfBoundsException;

	void replace(Object data, int index) throws IndexOutOfBoundsException;

	int size();

	void delete(int index) throws IndexOutOfBoundsException;
	
	Object retrieve(int index) throws IndexOutOfBoundsException;

	int indexOf(Object data);

	boolean contains(Object data);
}