package BinaryNumber;
import java.util.*;

public class BinaryNumber {
	private int data[];
	private boolean overflow;
	
	//Constructor to create binary number of given length consisting of only zeros
	public BinaryNumber(int length) {
		data= new int[length];
		for(int i=0; i<length-1; i++) {
				data[i]=0;
		}
	}
	
	//Constructor to create binary number of given string
	public BinaryNumber(String  str) {
		data = new int[str.length()];
		for(int i=0;i<str.length();i++) {
			data[i]= Character.getNumericValue(str.charAt(i));
		}
	}
	
	//operation for determining the length of a binary number.
	public int getLength() {
		return data.length;
    }
	
	//operation for  obtaining  a  digit  of  a  binary  number  given  an  index.
	public int getDigit(int index) {
		if(index>data.length) 
			return -1;
		else 
			return data[index];
	}
    
	//operation for shifting all digits in a binary number any number of places to the right,
    public void shiftR(int amount) {
    	int[] data1= Arrays.copyOf(data, (data.length+amount));
    	for(int i=0;i<data1.length;i++) {
    		if(i<amount)
    			data1[i]=0;
    		else
    			data1[i]=data[i-amount];
    	}
    	data=data1;
    }
    
    //operation for  adding  two  binary  numbers
    public void add(BinaryNumber aBinaryNumber) {
    	if(getLength()!=aBinaryNumber.getLength())
    		System.out.println("Length of the binary number should coincide");
    	else {
    		int carry=0;
    		for(int i=0; i<aBinaryNumber.getLength();i++) {
    			int add= getDigit(i)+ aBinaryNumber.getDigit(i)+ carry;
    			if(add==3) {
    				data[i]=1;
    				carry=1;
    			}
    			else if(add==2) {
    				data[i]=0;
    				carry=1;
    			}
    			else if(add==1){
    				data[i]=add;
    				carry=0;			
    			}
    			else {
    				data[i]=add;
    				carry=0;	
    			}
    			
    		}
    		if(carry!=0) {
    			overflow=true;
    		}
    		else {
    			clearOverflow();
    		}
    	}
    }
    
    //operation for transforming a binary number to a String
    public String toString() {
    	String str=new String();
    	if (overflow)
    		return("Overflow");
    	for(int i=0;i<data.length;i++) {
    		str+=String.valueOf(data[i]);
    	}
    	return str;	
    }
    
  //operation for transforming a binarynumber to its decimal notation 
    public int toDecimal(){
    	int Decimal=0;
    	for(int i=0;i<data.length;i++) {
    		Decimal+=data[i]*(Math.pow(2, i));
    	}
    	return Decimal;
    }
    
    //Operation to clear the overflow flag.
    public void clearOverflow() {
    	overflow=false;
    }
    
    
    

	public static void main(String args[]) {
		System.out.println("Amisha Antiya CWID:10475122 Homework Assignment-1 ");
		try {
			// object creation & passing value to get binary number consisting only zeros
			BinaryNumber ZeroBin=new BinaryNumber(3);  
			System.out.println("Binary number of assigned length having only zeros: " +ZeroBin);
		}catch (Exception e) {
			System.out.println("Enter only positive integer value.");
		}
			
		try {
			//object creation & passing string to get corresponding binary number
			BinaryNumber o1= new BinaryNumber("101011");
			System.out.println("Binary number corresponding to given string: "+ o1);
		}catch(Exception e) {
			System.out.println("Please enter valid String.");
		}
			
		try{
			//object creation to get length of binary number
			BinaryNumber lenBin= new BinaryNumber("1000");
			System.out.println("Length of binary number is: "+lenBin.getLength() );
		}catch(Exception e) {
			System.out.println("Enter only binary number.");
		}
				
		try{
			//object creation to get binary number digit at given index
			BinaryNumber Digit=new BinaryNumber("1001010");
			System.out.println("Digit of binary number at given index: "+Digit.getDigit(3));
		}catch (Exception e) {
			System.out.println("Index out of bound");
		}
		
		try {
			//object creation to convert binary to its decimal notation
			BinaryNumber BinToDec= new BinaryNumber("1011");
			System.out.println("Decimal notation of given binary number: " +BinToDec.toDecimal());
		}catch (Exception e) {
				System.out.println("Enter only binary number.");
		}
	
	    try {
	    	//object creation to right shift all digit of binary number by the given amount of place
	    	BinaryNumber Shift= new BinaryNumber("101101");
	    	Shift.shiftR(3);
	    	System.out.println("Binary number after right shift operation: " +Shift);
	    }catch (Exception e) {
	    	System.out.println("Please enter positive number as amount to right shift the binary number.");
	    }
	    
	    try {
	    	//Binary addition
	    	BinaryNumber bin1= new BinaryNumber("10110");
	    	BinaryNumber bin2= new BinaryNumber("11100");
	    	
	    	bin1.add(bin2);
	    	System.out.println("Addition of two binary number is: " +bin1.toString());
	    	
	    }catch (Exception e) {
	    	System.out.println("Enter only binary number.");
	    }
	}
}
