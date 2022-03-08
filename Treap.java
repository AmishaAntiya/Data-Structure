//Amisha Antiya CWID:10475122   Homework Assignment-5
package Treap;
import java.util.*;

public class Treap<E extends Comparable <E>> {
	//Data fields
	private Random priorityGenerator;
	private Node <E> root;
	
	//Node constructor
	private static class Node<E>{
		public E data;                   // key for the search
		public int priority;             // random heap priority
		public Node <E > left;
		public Node <E > right;
	
		public Node (E data ,int priority) {
			if(data==null) {
				throw new IllegalArgumentException();
			}
			else{
				this.data= data;
				this.priority=priority;
				this.left=null;
				this.right=null;
			}
		}
	
		//Rotation to rearrange node as per their priority after updating BST (to be in coordination of heap property)
		public Node <E> rotateRight() {
			Node <E> head=this.left;
			Node <E> left= head.right;
			head.right=this;
			this.left=left;
			return head;
		}
	
		public Node <E> rotateLeft(){
			Node <E> head=this.right;
			Node <E> right= head.left;
			head.left=this;
			this.right=right;
			return head;
		}

	}
	
	//To create an empty treap
	public Treap () {
		root = null;
		priorityGenerator= new Random();
	}
	
	public Treap (long seed) {
		root = null;
		priorityGenerator= new Random(seed);
	}
	
	//Add operation
	public boolean add (E key) {
		return add(key,priorityGenerator.nextInt());
	}
	
	//To restore the heap invariant
	public void reheap(Node<E> current,Stack<Node<E>> path) {
		while(!path.isEmpty()) {                         
			Node<E> p=path.pop();
			if(p.priority>current.priority)
				break;
			else {
				if(!path.isEmpty()) {
					if(path.peek().left==p) 
						path.peek().left=current;
					else
						path.peek().right=current;
				}
				else
					this.root=current;
				if(p.data.compareTo(current.data)>0)
					current=p.rotateRight();   
				else
					current=p.rotateLeft();
			}
		}
	}
	
	//To insert new key with priority
	public boolean add (E key ,int priority) {
		if(root!=null) {
			Node<E> T=new Node <E> (key,priority);
			Stack <Node<E>> ST= new Stack<Node<E>>();
			Node<E> cur=root;
			while(cur!=null) {
				int c=cur.data.compareTo(key);
				if(c==0) {
					return false;
				}
				else {
					if(c>0) {
						ST.push(cur);
						if(cur.left==null) {
							cur.left=T;
							reheap(T,ST);
							return true;
						}
						else
							cur=cur.left;
					}
					else {
						ST.push(cur);
						if(cur.right==null) {
							cur.right=T;
							reheap(T,ST);
							return true;
						}
						else
							cur=cur.right;
					}
				}
			}
			return false;
		}
		else {
			root=new Node<E> (key,priority);
			return true;
		}
	}
	
	//Delete operation
	public boolean delete (E key) {
		if(find(key)==false || root == null) {   //To check if key is not there or the root is null
			return false;
		}
		else {
			root=delete(key,root);   //if key is there then calls the delete function with key & root node to delete the key
			return true;
		}
	}
	
	private Node<E> delete(E key, Node<E> r){
		if(r!=null) {
			if(r.data.compareTo(key)<0)
				r.right=delete(key,r.right);
			else {
				if(r.data.compareTo(key)>0) 
					r.left=delete(key,r.left);
				else {
					if(r.left==null)
						r=r.right;
					else if(r.right==null)
						r=r.left;
					else {
						if(r.left.priority < r.right.priority) {
							r=r.rotateLeft();
							r.left=delete(key,r.left);
						}
						else {
							r=r.rotateRight();
							r.right=delete(key,r.right);
						}
					}
				}
			}
		}
		else
			return r;
		return r;
	}
	
	
	//Find operation
	private boolean find (Node <E> root ,E key) {
		if(root==null) 
			return false;
		else {
			int c=root.data.compareTo(key);
			if(c==0)           
				return true;       //returns true if the key matches with the key of root node
			else
				return (find(root.left,key) || find(root.right,key));    //checks the left or right child node of root node
		}
	}
	
	public boolean find ( E key ) {
		return (find(root,key));
	}
	
	
	//toString operation
	public String toString() {
		return toString(root,0); //starts with root node
	}
	
	private String toString(Node<E> r,int d) {
		StringBuilder s=new StringBuilder();
		
		//To print the String with proper indentation
		for(int i=0;i<d;i++) {
			s.append("  ");
		}
		if(r==null) 
			s.append("null");
		else {
			s.append("( key =" + r.data+ " , priority =" +r.priority+ ")");
			s.append("\n");
			s.append(toString(r.left,d+1));
			s.append("\n");
			s.append(toString(r.right,d+1));
		}
		return s.toString();
	}
	
	//Test cases
	public static void main(String[] args) {
		Treap <Integer> testTree = new Treap <Integer>();
		testTree.add (4 ,19);
		testTree.add (2 ,31);
		testTree.add (6 ,70);
		testTree.add (1 ,84);
		testTree.add (3 ,12);
		testTree.add (5 ,83);
		System.out.println(testTree.add (7 ,26));        //returns true as the node is added successfully
		System.out.println(testTree.add (7 ,20));        //returns false for duplicate priority
		System.out.println(testTree.delete(50));         //returns false as there is no node with key 50
		System.out.println(testTree.find(6));            //returns true as the node having key 6 is present
		System.out.println(testTree.toString());         //To return the nodes as a string
	}

}
