//Author: Wenxuan Wang;
//Project: For CS570 HW1;

package hw1;
import java.lang.Math.*;
import java.lang.*;
import java.util.Arrays;

public class BinaryNumber {
	//filed;
	private int[] data;
	private boolean overflow;
	
	//Constructor for creating a binary number of length Length and consisting only of zeros;
	public BinaryNumber(int length) {
		data = new int[length];
		for(int i=0;i<length; i++) {
			data[i] = 0;
		}
	}
	
	//Constructor for creating a binary number given a string.
	public BinaryNumber(String str) {
		int length = str.length();
		data = new int[length];
		for(int i=0; i<str.length();i++) {
			int number = Character.getNumericValue(str.charAt(i));
			if(number ==0 || number ==1) {
				data[i] = number;
			}
		}
	}
	
	//accessor getLength for determing the length of a binary number;
	public int getLength() {
		return data.length;
	}
	
	//accessor getDigit for obtaining a digit of a binary number given an index
	public int getDigit(int index) {
		if(index>=0 && index<data.length) {
		return data[index];
		}else {
			System.out.println("Out of Bounds!");
			return 0;
		}
	}
	
	//An operation int toDecimal() for transforming a binary number to its decimal notation
	public int toDecimal() {
		int length = data.length;
		int deciaml =0;
		for(int i = 0; i <length; i++) {
			int ex = length -1 - i;
			int inter = (int)Math.pow(2, ex);
			deciaml = deciaml + data[i] * inter;
		}
		return deciaml;
		//
		
	}
	
	//An operation void shiftR(int amount) for shifting all digits in a binary number
	//any number of places to the right
	void shiftR(int amount) {
		int newLength = data.length + amount;
		int newData[] = Arrays.copyOf(data, newLength);
		for(int i=0; i<data.length-1; i++) {
			newData[i+amount] = newData[i];
		}
		for(int i =0; i<amount; i++) {
			newData[i] = 0 ;
		}
		String str = "";
		for(int i=0; i<newLength; i++) {
			str= str + newData[i];
		}
		System.out.println("After shit the new binary Number: "+str);
	}
	
	//void add(BinaryNumber aBinaryNumber) for adding two binary numbers. one is the binary
	//number that receives the message and the other is given as a parameter. If the lengths
	//of the binary numbers do not coincide, then a message should be printed on the screen
	//indicating this fact. Otherwise, it modifies the receiving binary number with the result
	//of the addition.
	public void add(BinaryNumber aBinaryNumber) {
		if(data.length != aBinaryNumber.getLength()) {
			System.out.println("The length of these two binary numbers is not match!");
		}else {
			int[] original = data;
			int holderForNext =0;
			int sumForThis =0;
			for(int i=data.length-1;i>=0; i--) {
				sumForThis = data[i] + aBinaryNumber.getDigit(i) + holderForNext;
				holderForNext = sumForThis/2;
				data[i] = sumForThis % 2;
			}
			
			System.out.println( toString());
			
			if(holderForNext ==1) {
				overflow = true;
				System.out.println("The result of the addition is overflow!");
			}else {
				overflow = false;
			}
		}
	}
	
	//clearOverFLow() that clears the overflow flag;
	public void clearOverFlow() {
		overflow = false;
	}
	
	//An operation String toString() for transforming a binary number to a String.
	//If the number is the result of an overflow. the string "Overflow" should be returned.
	public String toString() {
		String str = "";
		for(int i=0; i<data.length; i++) {
			str= str + data[i];
		}
		
		if(overflow) {
			return "Overflow";
		}else {
			return str;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryNumber number1 = new BinaryNumber(4);
		BinaryNumber number2 = new BinaryNumber("1011");
		BinaryNumber number3 = new BinaryNumber("1011");
		System.out.println(number1.toString());
		System.out.println(number2.toString());
		System.out.println(number2.toDecimal());
		number2.shiftR(2);
		number2.add(number3);

	}

}
