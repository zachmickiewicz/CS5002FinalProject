import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NumRep {
	private int sBase;
	private String num;
	private HashMap<Integer, Character> toDecMap;
	private HashMap<Character, Integer> toOtherMap;
	
	
	
	/**
	 * This constructor creates a NumRep, or number representation
	 * @param num This stores the number as a string
	 * @param sBase This is the starting base
	 */
	public NumRep(String num, int sBase) {
		this.num = num;
		this.sBase =  sBase;
		
		// Each NumRep creates two hash maps for converting between letter representations and decimal
		this.toDecMap = new HashMap<Integer, Character>();
		this.toOtherMap = new HashMap<Character, Integer>();
		// For loop creates the hash maps
		for (int i = 0; i <26; i++) {
			toDecMap.put(10 + i, (char)('A' + i));
			toOtherMap.put((char)('A' + i), 10 + i);
		}
		
		
		
	}
	
	/**
	 * This simply returns the num string
	 * @return num
	 */
	public String getNum() {
		return this.num;
	}
	
	/**
	 * This returns the starting base
	 * @return sBase
	 */
	public int getSBase() {
		return sBase;
	}
	
	/**
	 * This returns the hash map that maps ints to character values
	 * @return toDecMap
	 */
	public HashMap<Integer, Character> getToDecMap() {
		return this.toDecMap;
	}
	
	/**
	 * This returns the hash map that maps characters to int values
	 * @return
	 */
	public HashMap<Character, Integer> getToOtherMap() {
		return toOtherMap;
	}
	
	/**
	 * This method converts a NumRep to decimal formatting
	 * @return A string with the decimal representation of a NumRep
	 */
	public String toDec() {
		int sum = 0;
		// Initializes a sum
		
		String temp = this.num;
		// Creates a new string that is a copy of the original num field
		
		// This for loop determines the decimal value of each char in the temp string and adds it
		// to the sum
		for (int i = 0; i < temp.length(); i++) {
			
			int value = this.getValue(i);
			value = raisePow(i, value);
			
			
			sum += value;
			// Each iteration of the for loop adds its finalized value to sum
			}
		
		return String.valueOf(sum);
		// Returns a string representation of the number in decimal
		
		
			
		}
	
	public int raisePow(int i, int value) {
		int pow = this.getNum().length() - 1 - i;
		// Initializes pow. An int that is configured to be the correct power to raise a number
		
		while (pow != 0) {
			value = value * this.sBase;
			pow--;
		// This while loop raises value to the appropriate power
		}
		return value;
	
	}
	public int getValue(int i) {
		int value = 0;
		if (this.toOtherMap.containsKey(this.getNum().charAt(i)) == true) {
			value = toOtherMap.get(this.getNum().charAt(i));
		// If a char is a letter, the value is retrieved from the hash map
			
			}
		else {
			value = Character.getNumericValue(this.getNum().charAt(i));
		// If a char is just a number, value is just that number
			}
		return value;
	}
		
	/**
	 * This is the toString method.
	 * @return String returns a string representation of the num field
	 */
	public String toString() {
		return String.valueOf(this.num);
	}
	
	/**
	 * This method converts from one base to a target base
	 * @param tBase This is target base
	 * @return String A string that is a representation of the number in the target base
	 */
	public String toBase(int tBase) {
		if (sBase != tBase) {
		// Just checks if the tBase and sBase are the same
			
		if (this.sBase != 10) {
			this.num = this.toDec();
		// If a NumRep is not already in decimal, it is converted to decimal
			
		}
		
		int temp = Integer.parseInt(num);
		// Creates an int temp from the string in the num field
		
		return this.NumBuilder(temp, tBase);
		
		}
	
		else {
			// returns the num field as is if it is already in the desired base
		 
			return this.num;
		}

	}
	
	public String NumBuilder(int temp, int tBase) {
		StringBuilder numBuild = new StringBuilder();
		// Initializes a string builder for constructing our final string to return
	
		while (temp != 0) {
			// This while loop replicates the algorithm to convert decimal numbers to other bases
		
			int mod = temp % tBase;
			// Creates int mod that is the result of the modulo operation between temp and the tBase
	
			if (mod >= 10) {
				// If mod is 10 or more, then the sBase is greater than 10 and a character will be needed to represent this number
		
				numBuild.append(this.getToDecMap().get(mod));
				// Gets the appropriate character from the hash map and appends it to the string builder
	
			}
			else {
				// Appends the mod value to the string builder
		
				numBuild.append(mod);
			}
		
			temp = (int)(temp / tBase);
			// temp is modified to be the floor of current temp divided by the target base
		}
		numBuild = numBuild.reverse();
		// The string has been building backwards, now it is reversed to correct order
		
		return numBuild.toString();
			
	
		}

	}


