// Amisha Antiya CWID:10475122 Homework Assignment-3
package IDLList;

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> idll= new IDLList<>();
		
		//to add the value in the list
		idll.add(5);
		idll.add(4);
		idll.add(3);
		idll.add(2);
		idll.add(1);
		
		System.out.println("The removed element of head position: " +idll.remove());
		
		try{
			System.out.println("Element at the index '3': " +idll.get(3));
		}catch(Exception e) {
			System.out.println("Index value is out of bound");
		}
		
		
		try{
			idll.add(2, 8);
		}catch(Exception e) {
			System.out.println("Index value is out of bound");
		}
		
		
		idll.append(6);
		
		try {
		System.out.println("Element at the head position: " +idll.getHead());
		System.out.println("Element at the tail position: " +idll.getLast());
		} catch(Exception e) {
			System.out.println("Empty list");
		}
		
		
		System.out.println("Size of a list: " +idll.size());
		
		try{
			System.out.println("The removed element of tail position: " +idll.removeLast());
		}catch (Exception e) {
			System.out.println("Index value is out of bound");
		}
		
		
		try {
		System.out.println("The removed element of index '2': " +idll.removeAt(2));
		}catch (Exception e) {
			System.out.println("Index value is out of bound");
		}
		
		
		try{
			System.out.println("Status of removing the given element from the list: " +idll.remove(4));
		}catch (Exception e) {
			System.out.println("Index value is out of bound");
		}
		
		
		System.out.println("String representation of list: " +idll.toString());
	}

}
