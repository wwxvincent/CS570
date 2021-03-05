package hw3;
import java.util.ArrayList;
//Author: Wenxuan Wang
//For: CS-570 homework3- IDLList
//Date: Oct.10/2019

public class IDLList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	/**---------------**/
	//The Inner Class Node<E>
	private static class Node<E>{
		// inner class three data fields;
		E data;
		Node<E> next;
		Node<E> prev;
		//a constructor that creates a node holding elem;
		private Node(E elem){
			data = elem;
			next = null;
			prev = null;
		}
		//a constructor that creates a node holding elem, with next as next
		//and prev as prev;
		private Node(E elem, Node<E> prev, Node<E> next) {
			data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	/**---------------**/
	
	// constructor, creates an empty double-linked list
	public IDLList() {
		head = null;
		tail =null;
		size =0;
		indices = new ArrayList<>();
	}
	
	//a method to add elem at position index
	//example CS570_I5 PPT/p28
	public boolean add(int index, E elem) {
		if(index<0 || index>indices.size()) {
			throw new
				IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index==0) {
			add(elem);
			return true;
		}else {
			// get the element used to be postion index;
			Node<E> node = indices.get(index);
			Node<E> newElem = new Node<E>(elem);
			//insert the new element;
			newElem.next = node;
			newElem.prev = node.prev;
			//replace the old element;
			node.prev.next = newElem;
			node.prev = newElem;
			//update the size and indices;
			size++;
			indices.add(index, newElem);
			
			return true;			
		}
	}
	
	// adds elem at the head: becomes the first element of the list
	// ppt15
	public boolean add(E elem) {
		if(head == null) {
			Node<E> temp = new Node<E>(elem);
			head = temp;
			tail = temp;
			
			size++;
			indices.add(head);
			
			return true;
		}else {
			Node<E> temp = new Node<E>(elem);
			head.prev = temp;
			temp.next = head;
			head = temp;
			
			size++;
			indices.add(0,temp);
			
			return true;
			
		}
	}
	
	//adds elem as the new last element of the list
	public boolean append(E elem) {
		if(head == null) {
			add(elem);
			return true;
		}else {
			Node<E> temp = new Node<E>(elem);
			tail.next = temp;
			temp.prev = tail;
			tail = temp;
			
			size++;
			indices.add(temp);
			
			return true;
		}
	}
	
	public E get(int index) {
		if(index<0 || index> size) {
			throw new
				IndexOutOfBoundsException(Integer.toString(index));
		}
		return (indices.get(index)).data;
	}
	
	public E getHead() {
		return head.data;
	}
	
	public E getLast() {
		return tail.data;
	}
	// return the list size;
	public int size() {
		return size;
	}
	
	//remove and return the element at the head
	//example ppt21
	public E remove() {
		Node<E> temp = head;
		if(head != null) {
			head = head.next;
			head.prev = null;
			
			size--;
			indices.remove(0);
			return temp.data;
		}else {
			return null;
		}
	}
	
	public E removeLast() {
		Node<E> temp = tail;
		if(tail != null) {
			tail.prev.next = null;
			tail = tail.prev;
			
			size--;
			indices.remove(size);
			return temp.data;
		}else {
			return null;
		}
	}
	
	// removes and returns the element at the index. Use the index
	// for fast access;
	public E removeAt(int index) {
		if(index<0 || index>indices.size()) {
			throw new
				IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == 0) {
			return remove();
		}else if(index == size-1) {
			return removeLast();
		}else {
			Node<E> temp = indices.get(index);
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			
			size--;
			indices.remove(index);
			
			return temp.data;
		}
	}
	
	//removes the first occurrence of element of elem in the list
	//and returns true. Return false if elem was not in the list
	public boolean remove(E elem) {
		for(Node<E> list: indices) {
			if((list.data).equals(elem)) {
				int index = indices.indexOf(list);
				removeAt(index);
				return true;
			}
		}
		return false;
	}
	
	// presents a string representation of the list
	public String toString() {
		Node<E> nodeRef = head;
		StringBuilder result = new StringBuilder();
		while(nodeRef != null) {
			result.append(nodeRef.data);
			if(nodeRef.next !=null) {
				result.append(" ==> ");
			}
			nodeRef = nodeRef.next;
		}
		return result.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
