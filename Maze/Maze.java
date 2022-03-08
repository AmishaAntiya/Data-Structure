//AMISHA ANTIYA   CWID:10475122   Homework Assignment-4
package Maze;
import java.util.*;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
    	findAllMazePaths(0, 0);
    	findMazePathMin(0,0);
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
    	if(x>maze.getNCols()-1 || y>maze.getNRows()-1 || x<0 ||y<0)           //checks if current cell is outside the grid
    			return false;                                                 	//returns false since there is no possible path
    	
    	else if(maze.getColor(x, y)!= NON_BACKGROUND )                        //checks if current cell is not a part of path
    		return false;                                                        //returns false since there is no possible path
    	
    	else if (x==maze.getNCols()-1 && y==maze.getNRows()-1) {              //checks if current cell is exit cell
    		maze.recolor(x, y, PATH);                                         	//cell must be colored in green color as it's a part of path
    		return true;                                                      	//returns true as path is found
    	}
    	
    	else {                                                                //current cell is assumed to be a part of path
    		maze.recolor(x, y, PATH);                                            //cell must be colored in green color
    		boolean p1,p2,p3,p4;
    			//checks if the neighbor cell is also a part of path
    		p1=findMazePath(x-1,y);                                           
    		p2=findMazePath(x+1,y);
    		p3=findMazePath(x,y-1);
    		p4=findMazePath(x,y+1);
    		
    		if((p1||p2||p3||p4)==false) {                                   //checks if cell is not a part of path                                   
    			maze.recolor(x, y, TEMPORARY);                              //cell is marked as as dead end by color TEMPORARY
    			return false;
    		}
    		else
    			return true;                                                 //cell is a part of path

    	}
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    //To get the list of all the possible solutions to the maze
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	//(x,y) are the coordinates currently being visited
    	//result is the list of successful paths recorded up to now
    	//trace is the trace of the current path being explored
    	if(x>maze.getNCols()-1 || y>maze.getNRows()-1 || x<0 ||y<0)                   //checks if current cell is outside the grid    
			return ;
    	else if(maze.getColor(x, y)!= NON_BACKGROUND )                                //checks if current cell is not a part of path
    		return ;
    	else if (x==maze.getNCols()-1 && y==maze.getNRows()-1) {        //checks if current cell is exit cell
    		PairInt p1=new PairInt(x,y);
    		ArrayList<PairInt> path= new ArrayList<PairInt>();
    		trace.push(p1);
    		path.addAll(trace);                 //add all the content of stack to arraylist
    		result.add(path);                   //all the content of arraylist path is added in result
    		trace.pop();
    		return ;
    	}
    	else {
    		PairInt p1=new PairInt(x,y);
    		maze.recolor(x, y, PATH);              //cell must be colored in green color as it's a part of path
    		trace.push(p1);
    		
    		//checks if the neighbor cell is also a part of path
    		findMazePathStackBased(x-1,y,result,trace);
    		findMazePathStackBased(x+1,y,result,trace);
    		findMazePathStackBased(x,y-1,result,trace);
    		findMazePathStackBased(x,y+1,result,trace);
    		
    		trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);      //remove the mark so that cell may be visited again after backtracking
    	}
    }
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths (int x ,int y) {
    	ArrayList <ArrayList<PairInt>> result = new ArrayList <>();
    	Stack <PairInt> trace = new Stack <>();
    	findMazePathStackBased (0 ,0 , result , trace);
    	if(result.size()!=0) {                                //checks if result arraylist is not empty
    		System.out.println("All the possible paths are: ");
    		System.out.println(result.get(0));
    		for(int i=1;i<result.size();i++) {                 //loop over entire size of an arraylist result
    			System.out.println(result.get(i));             
    		}
    	}
    	else if (result.size()==0)
    		return (new ArrayList <ArrayList<PairInt>>());
    	return result ;
    }
    
    
    // ADD METHOD FOR PROBLEM 3 HERE
    //To get the shortest path from all the possible paths
    public boolean findMin(int x, int y, Stack<PairInt> fpath, ArrayList<PairInt> result ){
    	if(x>maze.getNCols()-1 || y>maze.getNRows()-1 || x<0 ||y<0)                         //checks if current cell is outside the grid 
			return false;                                                                        //returns false since there is no possible path
    	else if(maze.getColor(x, y)!= NON_BACKGROUND )                                      //checks if current cell is not a part of path                                  
    		return false;                                                                        //returns false since there is no possible path
    	else if (x==maze.getNCols()-1 && y==maze.getNRows()-1) {                           //checks if current cell is exit cell
    		fpath.push(new PairInt(x,y));                                         //insert the new pair of x & y value to the stack
    		
    		//if stack size is less than result size or result is empty then add all the values of x & y from stack in result
    		if(fpath.size()<result.size() || result.size()==0) {                          
    			result.clear();                                           
    			result.addAll(fpath);
    		}
    		fpath.pop();
    		return true;
    	}
    	else {
    		maze.recolor(x, y, PATH);                                 //cell must be colored in green color as it's a part of path
    		fpath.push(new PairInt(x,y));
    		boolean p1,p2,p3,p4;
    		
    		//checks if the neighbor cell is also a part of path
    		p1=findMin(x-1,y,fpath,result);
    		p2=findMin(x+1,y,fpath,result);
    		p3=findMin(x,y-1,fpath,result);
    		p4=findMin(x,y+1,fpath,result);
    		
    		maze.recolor(x, y, NON_BACKGROUND);                 //remove the mark so that cell may be visited again after backtracking
    		fpath.pop();
    		if((p1||p2||p3||p4)==false) {                      
    			return true;
    		}
    		else
    			return false;
    	}
    }
    
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList<PairInt> result= new ArrayList<PairInt>();
    	Stack<PairInt> fpath= new Stack<PairInt>();
    	findMin(0,0,fpath,result);             //starts by 0 index value of x & y to get shortest path
    	if(result.size()!=0) {
    		System.out.println("The shortest path among all the possible path is: "+result);
    	}
    	return result;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
