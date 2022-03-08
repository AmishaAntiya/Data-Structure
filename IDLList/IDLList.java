//Amisha Antiya CWID:10475122 Homework Assignment-3
package IDLList;
import java.util.ArrayList;

public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices= new ArrayList<>();
	
	
	//inner class declaration
	private class Node<E>{
		E Data;
		Node<E> next;
		Node<E> prev;
		
		Node(E elem){
			this.Data=elem;
		}
		
		Node (E elem, Node<E> prev, Node<E> next){
			this.Data=elem;
			this.prev = prev;
			this.next = next;
		}
		
	}
	
	
	//empty double-linked list creation
	public IDLList() {
		head=null;
		tail=null;
		size=0;
	}
	
	//to add elem at index position
	public boolean add (int index, E elem) {
		if(index<0 || index>size)
			throw new IndexOutOfBoundsException();
		// changes done as per the comment
		else if(index==0) {
			add(elem);
			return true;
		}
		else if (index==size) {
			append(elem);
			return true;
		}
		else {
			Node<E> cur=indices.get(index);
			Node<E> newCur= new Node<>(elem,cur.prev,cur);
			
			cur.prev.next=newCur;
			cur.prev=newCur;
			size++;
			return true;
		}
	}
	
	//to add elem at the head
	public boolean add (E elem) {
		if(head==null) {
			Node <E> t=new Node<E> (elem);
			t.Data=elem;
			head=t;
			tail=t;           //assign same value for head & tail 
			indices.add(head);
			size++;
			return true;
		}
		else {
			Node <E> t=new Node<E> (elem);
			t.next=head;
			head.prev=t;
			head=t;
			indices.add(0,t);
			size++;
			return true;
		}
	}
	
	//to add the elem at tail
	public boolean append (E elem) {
		if(head==null) {
			head.Data=elem;
			tail.Data=elem;
			indices.add(head);
			size++;
			return (true);
		}
		else {
			Node<E> t=new Node<E> (elem);
			tail.next=t;
			t.prev=tail;
			tail=t;
			indices.add(tail);
			size++;
			return (true);
		}
	}
	
	//returns the object which is at position index from the head.
	public E get (int index) {
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException();
		}
		else {
			return (indices.get(index).Data);
		}
	}
	
	//to get the head value
	public E getHead() {
		if(head==null) {
			throw new IllegalStateException();     //throws exception when list is empty
		}
		else
			return (head.Data);
	}
	
	//to get the tail value
	public E getLast() {
		if(tail==null) {
			throw new IllegalStateException();       //throws exception when list is empty
		}
		else
			return (tail.Data);
	}
	
	
	//to display the size of the list
	public int size() {
		return size;           
	}
	
	
	//to remove the element at head
	public E remove() {
		if(head==null) {
			throw new IndexOutOfBoundsException();   //throws exception when list is empty
		}
		else if(head==tail) {
			Node <E> t=head;
			head=null;
			tail=null;
			size--;
			indices.clear();            //removes the value of list having only one value (when head==tail)
			return (t.Data);
		}
		//empty list
		else if (size<0) {
			throw new IllegalStateException("List is empty");
		}
		else {
			Node<E> t=head;
			head=head.next;
			size--;
			indices.remove(0);     //removes the first element which is nothing but head value
			return (t.Data);
		}
	}
	
	
	//to remove tail element
	public E removeLast() {
		if(head==null)
			throw new IndexOutOfBoundsException();
		else if(head==tail) {
			Node<E> t=head;
			head=null;
			tail=null;
			size--;
			indices.clear();        //removes the value of list having only one value (when head==tail)
			return t.Data;
		}
		//empty list
		else if (size<0) {
			throw new IllegalStateException("List is empty");
		}

		else {
			Node<E> t=tail;
			tail=tail.prev;
			tail.next=null;
			size--;
			indices.remove(size-1);         //removes the last element i.e. element at size-1 index
			return t.Data;
		}
	}
	
	
	//to remove the value from given index position
	public E removeAt (int index) {
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException("List index out of bounds.");
		}
		else if (index==0) {
			return remove();        //remove method is used to remove head value
		}
		else if (index==size-1) {
			return removeLast();    //removeLast method is used to remove tail value
		}
		else {
			Node<E> cur=indices.remove(index);
			cur.prev.next=cur.next;
			cur.next.prev=cur.prev;
			size--;
			return cur.Data;
		}
	}
	
	
	//to remove first occurrence of elem from the list
	public boolean remove(E elem) {
		if(elem==head.Data) {
			remove();                  //remove method is used to remove elem if it's first occurrence is at head position
			return true;
		}
		else if(elem==tail.Data) {
			removeLast();           //removeLast method is used to remove elem if it's occurrence is at tail position
			return true;
		}
		else {
			Node<E> cur=head;
			int i=0;
			while(cur!=null) {   //checks for the elem in the entire list 
				if(cur.Data.equals(elem)) {
					//removes the elem if found and decreases the size
					cur.prev.next=cur.next;
					cur.next.prev=cur.prev;
					indices.remove(i);
					size--;
					return true;
				}
				else {
					//if elem is not found at index "i" then checks for the next node
					cur=cur.next;
					i++;
				}
			}
			return false;
		}
	}
	
	
	//to get the string representation of list
	public String toString() {
		Node<E> cur=head;
		StringBuilder str=new StringBuilder();
		while(cur!=null) {          //to append the string till the current node reaches the end of the list
			str.append(cur.Data);
			cur=cur.next;
		}
		return str.toString();
	}

}
