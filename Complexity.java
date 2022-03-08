//Amisha Antiya CWID:10475122 Homework Assignment-2
package Complexity;

public class Complexity {
	
	//method having time complexity of O(n^2)
	public static void method1(int n) {
		if (n<=0)
			System.out.println("Enter positive value of n");
		int counter=1;
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				//for every value of i, inner loop will run "n" times
				System.out.println("Operation "+counter);
				counter++;
			}
		}
		
	}
	
	//method having time complexity of O(n^3)
	public static void method2 (int n) {
		if (n<=0)
			System.out.println("Enter positive value of n");
		int counter=1;
		for(int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					//for every value of i and j, inner loop will run "n" times
					System.out.println("Operation "+counter);
					counter++;
				}
			}
		}
		
	}
	
	//method having time complexity of O(logn)
	public static void method3 (int n) {
		if (n<=0)
			System.out.println("Enter positive value of n");
		int counter=1;
		for(int i=1; i<n;i*=2) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	//method having time complexity of O(nlogn)
	public static void method4 (int n) {
		if (n<=0)
			System.out.println("Enter positive value of n");
		int counter=1;
		for(int i=0;i<n;i++) {
			for(int j=n;j>1;j/=2) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
	}
	
	//method having time complexity of O(loglogn)
	public static void method5 (int n) {
		if (n<=0)
			System.out.println("Enter positive value of n");
		int counter=1;
		for(int i=n;i>2;i=(int)Math.sqrt(i)) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	//method having time complexity of O(2^n)
	public static int method6(int n) {
		if (n<=0)
			System.out.println("Enter positive value of n");
		int counter=1;
		for(int i=1;i<=Math.pow(2,n);i++) {
			System.out.println("Operation "+i);
			counter++;
		}
		return (counter-1);
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Amisha Antiya CWID:10475122 Homework Assignment-2");
		
		System.out.println("Method having time complexity of O(n^2)");
		Complexity.method1(3);
		
		System.out.println("Method having time complexity of O(n^3)");
		Complexity.method2(2);
		
		System.out.println("Method having time complexity of O(logn)");
		Complexity.method3(32);
		
		System.out.println("Method having time complexity of O(nlogn)");
		Complexity.method4(8);
		
		System.out.println("Method having complexity of O(loglogn)");
		Complexity.method5(256);
		
		System.out.println("Method having complexity of O(2^n)");
		int result= Complexity.method6(4);
		System.out.println("Total operation "+result);
	}

}
