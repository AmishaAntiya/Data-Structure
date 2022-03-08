//AMISHA ANTIYA   CWID:10475122   Homework Assignment-4
package Maze;

public class PairInt {         //Class to encode pairs of integers that represent coordinates
	private int x;
	private int y;
	
	public PairInt(int x, int y) {         
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;             //returns the value of x coordinate
	}
	
	public int getY() {
		return y;             //returns the value of y coordinate
	}
	
	public void setX(int x) {
		this.x=x;                 //assigns the given parameter to x coordinate
	}
	
	public void setY(int y) {
		this.y=y;               //assigns the given parameter to y coordinate
	}
	
	public boolean equals(PairInt p) {
		if(p.getX()==this.x && p.getY()==this.y)    //checks for the values of x and y coordinate
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "(" +x+ "," +y+ ")";           //to convert the x & y values in coordinate format
	}
	
	public PairInt copy() {
		return new PairInt(x,y);           //returns new copy of PairInt
	}
}
