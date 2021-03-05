package hw3;
//Author: Wenxuan Wang
//For: CS-570 homework3- IDLListTest
//Date: Oct.13/2019

public class IDLListTest {

	public static void main(String[] args) {
		// 
		IDLList<String> testList = new IDLList<>();
		
		// test the method add(E elem)
		//System.out.println("1. test the method add(E elem)");
		//testList.add("vincent");
		//testList.add("Iris");
		//System.out.println(testList.toString());
		
		// test the method add(int index, E elem)
		System.out.println();
		System.out.println("2. test the method add(int index, E elem)");
		testList.add(0, "LZhen");
		System.out.println(testList.toString());
		testList.add(1, "CJ");
		System.out.println(testList.toString());
		// add an element use index out of bound
		//testList.add(-1,"nono");
		
		//test the method append(E elem)
		System.out.println();
		System.out.println("3. test the method append(E elem)");
		System.out.println(testList.toString());
		testList.append("MJ");
		System.out.println(testList.toString());
		
		//test the method get(int index)
		System.out.println();
		System.out.println("4. test the method get(int index");
		System.out.print("Index 0: ");
		System.out.println(testList.get(0));
		System.out.print("Index 1: ");
		System.out.println(testList.get(1));
		
		//test the method getHead()
		System.out.println();
		System.out.println("5. test the method getHead()");
		System.out.println(testList.toString());
		System.out.println(testList.getHead());
		//test the method getLast()
		System.out.println("6. test the method getLast()");
		System.out.println(testList.getLast());
		
		// test the method size()
		System.out.println();
		System.out.println("7. test the method size()");
		System.out.println(testList.size());
		
		//test the method remove()
		System.out.println();
		System.out.println("8. test the method public E remove()");
		System.out.println(testList.toString());
		System.out.println(testList.remove());
		System.out.println(testList.toString());
		
		//test the method removeLast()
		System.out.println();
		System.out.println("9. test the method public E removeLast()");
		System.out.println(testList.toString());
		System.out.println(testList.removeLast());
		System.out.println(testList.toString());
		
		//test the method removeAt(int index)
		System.out.println();
		System.out.println("10. test the method public E removeAt()");
		System.out.println(testList.toString());
		System.out.println("remove the index: 1");
		System.out.println(testList.removeAt(1));
		System.out.println(testList.toString());
		
		//test the method public boolean remove(E elem)
		System.out.println();
		System.out.println("11. test the method public boolean remove()");
		testList.add("Mike");
		testList.add("Iris");
		System.out.println(testList.toString());
		System.out.println("remove element vincent");
		System.out.println(testList.remove("vincent"));
		System.out.println(testList.toString());
		
		//test the method public String toString()
		System.out.println();
		System.out.println("12. test the method public String toString()");
		System.out.println(testList.toString());

	}

}
