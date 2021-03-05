package CS570;
import java.util.Random;
import java.util.Stack;
//Author: Wenxuan Wang
//Date: 11/24/2019
//HW5-Treap;

public class Treap<E extends Comparable<E>> {
	//data fields;
	private Random priorityGenerator;
	private Node<E> root;
	
	//2.1 The private Node Class
	private class Node<E>{
		public E data; // key for the search
		public int priority; // random heap priority
		public Node<E> left;
		public Node<E> right;
		
		//Constructors
		public Node(E data, int priority) {
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
		}
		//Methods;
		public Node<E> rotateRight(){
			//set new temp refer new root(q,9)
			Node<E> temp;
			temp = this.left;
			//new left(r,0) became (t,8)'s left
			Node<E> newLeft = temp.right;
			temp.right = this;
			//temp left still hold (a,2)
			//old root (t,8) is this, and this's right still hold (u,7)
			//but this's left becomes temp's right (r,o)
			this.left = newLeft;
			
			return temp;
		}
		public Node<E> rotateLeft(){
			//set new temp refer new root(t,8)
			Node<E> temp;
			temp =this.right;
			Node<E> newRight = temp.left;
			//new root temp'left becomes old root(q,9), but temp's left unchanged;
			temp.left = this;
			//old root(q,9) becomes new root's left, and its left hold.
			//but its right changes to (r,0);
			this.right = newRight;
			
			return temp;
		}
	}
	
	//Constructors
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	//Mehtods;
	//2.2.1 Add operation
	 boolean add(E key, int priority) {
		//if heap is empty, add directly;
		Stack<Node<E>> trace = new Stack<>();
		if(root == null) {
			root = new Node<E>(key, priority);
			return true;
		}else {
			Node<E> temp = new Node<E>(key, priority);
			Node<E> rootNow = root;
			
			while(rootNow!=null) {
				//if new key equals exit key, return false;
				if(rootNow.data.compareTo(key) == 0) {
					return false;
				}else if(rootNow.data.compareTo(key) < 0) {
					//System.out.println("1");
					//use Stack trace to store the way for later adapt the heap
					trace.add(rootNow);
					if(rootNow.right == null) {
						//if this root's right is null;
						//then store the new data at this root's right and stop;
						rootNow.right = temp;
						//success stored the new data and restore the heap;
						reheap(temp, trace);
						
						return true;
					}else {
						//update the rootNow, and loop it
						rootNow = rootNow.right;
					}
				}else {
					//System.out.println("2");
					//when the key smaller
					//use Stack trace to store the way for later adapt the heap
					trace.add(rootNow);
					if(rootNow.left == null) {
						//the key is smaller than root and root's left is null
						//so store the new root here;
						rootNow.left = temp;
						//success stored the new data and restore the heap;
						reheap(temp, trace);
						
						return true;
					}else {
						//update the rootNow, and loop it
						rootNow = rootNow.left;
					}
				}
			}
		}
		return true;
	}
	// call the add(E key, int priority) method once it has generated
	//the random priority.
	 boolean add(E key) {
		int priority = priorityGenerator.nextInt();
		boolean result=add(key,priority);
		return result;
	}
	//helper function reheap
	public void reheap(Node<E> temp, Stack<Node<E>> trace) {
		while(!trace.isEmpty()) {
			//get the insertion's father;
			Node<E> parent = trace.pop();
			//then compare the farther and son;
			// if farther smaller than son, then change their position;
			if(temp.priority > parent.priority) {
				//if parent left at temp, then rotateLeft;
				if(parent.data.compareTo(temp.data)<0) {
					temp=parent.rotateLeft();
				}else {
					//else rotateRight;
					temp = parent.rotateRight();
				}
				//
				if(!trace.isEmpty()) {
					//update the relationship
					//if the trace.peek() (g,80)'s left equal old root(j,65)
					//then trace.peek() (g,80)'s new left will be temp
					//vice verse
					if(trace.peek().left == parent) {
						trace.peek().left = temp;
					}else {
						trace.peek().right = temp;
					}
				}else {
					//if trace is empty, indicate the temp is the top root now;
					this.root = temp;
				}
			}else {
				break;
			}
		}
	}
	boolean delete(E key) {
		if(find(key)==false || root == null) {
			return false;
		}else {
			root = delete(root,key);
			return true;
		}
	}
	//private helper for delete;
	private Node<E> delete(Node<E> rootNow, E key){
		
		if(rootNow==null) {
			return rootNow;
		}else {
			//if key is bigger than current data; then go right;
			if(rootNow.data.compareTo(key)<0) {
				rootNow.right=delete(rootNow.right,key);
			// if key is smaller than current root, then go left;
			}else if(rootNow.data.compareTo(key)>0){
				rootNow.left=delete(rootNow.left, key);
			}else {
			// the key equals current root, find the target root
				//situation1: current root has no right child, but left child;
				//			  then, replace current root with left child;
				if(rootNow.right==null) {
					rootNow = rootNow.left;
				}else if(rootNow.left == null) {
				//situation2: current root has no left child, but right child;
				//			  then, replace current root with right child;
					rootNow = rootNow.right;
			    }else {
				//situation3: current root has both left and right child;
					//Find the the min child(who has smaller priority), then swap;
					Node<E> minChild = rootNow.left;
					if(rootNow.left.priority > rootNow.right.priority) {
						//right child smaller, then change the minChild be right child;
						//then rotate current root ro right;
						minChild = rootNow.right;
						rootNow=rootNow.rotateRight();
						rootNow.right=delete(minChild,key);
					}else {
						//vice verse;
						rootNow=rootNow.rotateLeft();
						rootNow.left=delete(minChild,key);
					}
				}
			}
			return rootNow;
		}
	}
	//2.2.3 Find operation
	private boolean find(Node<E> root, E key) {
		if(root == null) {
			// empty return false;
			return false;
		}
		if(root.data.compareTo(key)==0) {
			// root equal key
			return true;
		}else if(root.data.compareTo(key)<0) {
			//key is bigger than root, recursion to root right;
			return find(root.right, key);
		}else {
			//vice verse;
			return find(root.left, key);
		}
	}
	public boolean find(E key) {
		return find(root, key);
	}
	//2.2.4 toString operation
	//reference Professor's BinaryTree.java;
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	private void preOrderTraverse(Node<E> localRoot, int depth, StringBuilder sb) {
		for(int i=1;i<depth; ++i) 
			sb.append(" ");
		if(localRoot == null)
			sb.append("null\n");
		else {
			sb.append("(key="+localRoot.data.toString()+", priority="+localRoot.priority+")");
			sb.append("\n");
			preOrderTraverse(localRoot.left, depth+1, sb);
			preOrderTraverse(localRoot.right, depth+1, sb);
		}
	}

	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19);
		testTree.add(4,19); 

//		testTree.add(2,31);
//		testTree.add(6,70); 
//		testTree.add(1,84);
//		testTree.add(3,12); 
//		testTree.add(5,83);
//		testTree.add(7,26);
		System.out.println(testTree.toString());
//		System.out.println("test the delete:");
//		System.out.println(testTree.delete(4));
//		System.out.println(testTree.delete(4));

//		System.out.println(testTree.delete(10));
//		System.out.println(testTree.delete(5));
//		System.out.println(testTree.delete(7));
//		System.out.println(testTree.find(5));
		System.out.println(testTree.toString());
	}

}
