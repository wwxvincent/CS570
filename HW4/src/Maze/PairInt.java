//Author: Wenxuan Wang
//For: CS-570 homework4- Recursion & Backtracking
//Date: Nov.11/2019
package Maze;

public class PairInt {
	//fields;
	private int x;
	private int y;
	
	//constructor
	public PairInt(int x, int y) {
		this.x=x;
		this.y=y;
	}
	//accessor;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	//mutator
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	
	//method
	public boolean euqals(Object p) {
		PairInt pi= new PairInt(x,y);
		return pi.equals(p);
	}
	
	public String toString() {
		return ("( "+x+", "+y+" )");
	}
	
	public PairInt copy() {
		return new PairInt(x,y);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
