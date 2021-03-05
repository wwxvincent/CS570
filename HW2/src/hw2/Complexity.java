package hw2;

//Author: Wenxuan Wang
//For CS570 homework 2
//Date: Sep./25/2019

public class Complexity {
	
	//a method that has time complexity O(n)
	public static void method0(int n) {
		int counter = 0;
		for (int i=0; i<n;i++) {
			System.out.println("Operation "+ counter);
			counter++;
		}
	}
	
	//a method that has time complexity O(n^2)
	public static void method1(int n) {
		int counter = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n;j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
	//a method that has time complexity O(n^3)
	public static void method2(int n) {
		int counter =0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n;j++) {
				for(int q=0; q<n; q++) {
					System.out.println("Operation "+ counter);
					counter++;
				}
			}
		}
		
	}
	
	//a method that has time complexity O(log n)
	public static void method3(int n) {
		int counter = 0;
		for(int i=1; i<n; i=i*10) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	//a method that has time complexity O(n * log n)
	public static void method4(int n) {
		int counter = 0;
		for(int j=0; j<n;j++) {
			for(int i=1; i<n; i=i*2) {
				System.out.println("Operation "+counter);
				counter++;
				}
			}
		}
		
	//a method that has time complexity O(log log n)
	public static void method5(int n) {
		int counter =0;
		int index=0;
		for(int i=1; i<n;i=i*2) {
			index++;
		}
		for(int j=1; j<index; j=j*2) {
			System.out.println("Operation "+counter);
			counter++;
		}
	
	}
	
	//a method that has time complexity O(2^n)
	static int counter6 =0;
	public static int method6(int n) {
		if(n<=1) {
			System.out.println("Operation "+counter6);
			counter6++;
			return 1;
		}else {
			System.out.println("Operation "+counter6);
			counter6++;
			
			return method6(n-1) + method6(n-1);
		}
	}
	//complexity O(2^n -1)

	public static void main(String[] args) {
		
		System.out.println("test for method 0 complexity O(n)");
		method0(2);
		System.out.println("test for method 1 complexity O(n^2)");
		method1(2);
		System.out.println("test for method 2 complexity O(n^3)");
		method2(2);
		System.out.println("test for method 3 complexity O(logn)");
		method3(1000);
		System.out.println("test for method 4 complexity O(n*logn)");
		method4(4);
		System.out.println("test for method 5 complexity O(log logn)");
		method5(16);
		System.out.println("test method 5 #2");
		method5(65536);
		
		System.out.println("test for method 6 complexity O(2^n)");
		method6(2);
		counter6 =0;
		System.out.println();
		method6(3);
		
	}

}
