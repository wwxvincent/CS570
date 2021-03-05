//Author: Wenxuan Wang
//For: CS-570 homework4- Recursion & Backtracking
//Date: Nov.11/2019
package Maze;
import java.util.ArrayList;
import java.util.Stack;

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
    // 3.1 Problems 1 Page: 250
    public boolean findMazePath(int x, int y) {
    	if(x<0||y<0||x>=maze.getNCols()||y>=maze.getNRows())
    		return false;		//Cell is out of bounds/
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND))
    		return false;		//Cell is on barrier or dead end.
    	else if(x==maze.getNCols()-1 && y==maze.getNRows()-1) {
    		maze.recolor(x,y,PATH);
    		return true;
    	}else {
    		//Attempt to find a path from each neighbor.
    		//Tentatively mark cell as on path.
    		maze.recolor(x, y,PATH);
    		if(findMazePath(x-1,y) || findMazePath(x+1,y)||findMazePath(x,y+1)
    		   ||findMazePath(x,y-1)) {
    			return true;
    		}else {
    			maze.recolor(x, y,TEMPORARY);//Dead end.
    			return false;
    		}
    	}
    }
    

    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0,0,result,trace);
    	return result;
    }

    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	//if path cell is out of bounds
    	if(x<0||y<0|| x>=maze.getNCols() || y>=maze.getNRows()) {
    		return;
        //if path cell is a barrier
    	}else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) {
    		return;
    	//if this cell is the exit, push this cell into race & add into result
    	}else if(x==maze.getNCols()-1 && y==maze.getNRows()-1) {
    		//get the end of this trace and push it into this trace
    		trace.push(new PairInt(x,y));
    		//get a new ArrayList holder to hold this trace
    		ArrayList<PairInt> holder = new ArrayList<>(trace);
    		//add this trace to (Bigger ArrayList) the all paths: result
    		result.add(holder);
    		//pop the end, and didn't change the end's color
    		//because this path's end is also other path's end;
    		trace.pop();
    		
    		
    	//Attempt to find a path from each neighbor.
    	//Tentatively mark cell as on path.
    	}else {
    		maze.recolor(x, y,PATH);
    		trace.push(new PairInt(x,y));
    		findMazePathStackBased(x+1,y,result,trace);
    		findMazePathStackBased(x,y-1,result,trace);
    		findMazePathStackBased(x-1,y,result,trace);
    		findMazePathStackBased(x,y+1,result,trace);
    		
    		//if this path's last cell is not end, pop it from this trace and recolor it to NON_BACKGROUND;
    		trace.pop();
    		maze.recolor(x, y,NON_BACKGROUND);
    	}
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	//get the whole and store it into allPath;
    	ArrayList<ArrayList<PairInt>> allPath;
    	allPath = findAllMazePaths(x,y);
    	//set the first index as temporary minIndex;
    	if(allPath.size()>0) {
    		int minIndex =0;
    		//compare the temporary minIndex'size and the next one's size
    		//if the next is smaller, pass the next Index to minIndex;
    		for(int i=1;i<allPath.size();i++) {
    			if(allPath.get(minIndex).size() > allPath.get(i).size()) {
    				minIndex = i;
    			}
    		}
    		return allPath.get(minIndex);
    	}else {
    		return null;
    	}
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
