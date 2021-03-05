//import Java.utils.Arrays;
package hw3;
public class testingFromCA{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IDLList<Integer> test = new IDLList<Integer>();
		
		System.out.println("The test1 of adding");
		//System.out.println(test.add(1,80));
		System.out.println(test.add(0,0));
		System.out.println(test.add(1,1));
		System.out.println(test.add(1,2));
		System.out.println(test.add(2,3));
		System.out.println(test.add(4,4));
		System.out.println(test.add(5,5));
		System.out.println(test.add(3,6));
		System.out.println(test.add(1,7));
		System.out.println(test.add(8));
		System.out.println(test.append(9));
		System.out.println("should be 8072361459");
		System.out.println(test.toString());
		System.out.println();
		
		System.out.println("The test1 of getting");
		System.out.println(test.get(4));
		System.out.println(test.getHead());
		System.out.println(test.getLast());
		System.out.println(test.size());
		System.out.println();
		
		System.out.println("The test1 of removing");
		System.out.println(test.remove());
		System.out.println(test.removeLast());
		//System.out.println(test.removeAt(80));
		System.out.println(test.removeAt(0));
		System.out.println(test.removeAt(6));
		System.out.println(test.removeAt(3));
		System.out.println(test.toString());
		System.out.println(test.remove(5));
		System.out.println(test.remove(1));
		System.out.println("should be 7234");
		System.out.println(test.toString());
		System.out.println();
		
		System.out.println("The test2 of adding");
		System.out.println(test.add(20));
		System.out.println("should be 207234");
		System.out.println(test.toString());
	}

}