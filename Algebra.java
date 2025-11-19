// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		 
			// If x2 is negative, we need to subtract 1 from x1, |x2| times
			if (x2 < 0) {
				for (int i = 0; i > x2; i--) {
					x1--;
				}
			} 
			// If x2 is positive, we add 1 to x1, x2 times
			else {
				for (int i = 0; i < x2; i++) {
					x1++;
				}
			}
			return x1;
	 
	}
	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2 < 0) {
			// Subtracting a negative number is the same as adding
			for (int i = 0; i > x2; i--) {
				x1++;
			}
		} else {
			// Subtracting a positive number
			for (int i = 0; i < x2; i++) {
				x1--;
			}
		}
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int result = 0;
    
		// If x2 is positive, we add x1 to the result, x2 times
		if (x2 > 0) {
			for (int i = 0; i < x2; i++) {
				result = plus(result, x1);
			}
		}
		// If x2 is negative, we subtract x1 from the result, |x2| times
		else {
			for (int i = 0; i > x2; i--) {
				result = minus(result, x1);
			}
		}
		
		return result;
	}

	// Returns x^n (for n >= 0)no
	public static int pow(int x, int n) {

		int result =1;

		for (int i = 0; i < n; i++)
		{
		result= times(result,x);
		}
		
		return result;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {

		int result= 0;

		while(x1>0) {

		if( minus (x1,x2)>0)
		{
			result++;
			
		}
		x1=minus (x1,x2); 
		}
		return result;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		
		return minus (x1,times(x2,div(x1,x2))) ;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int i = 0;
		
		while (true) {
			// Calculate square using your times function
			int square = times(i, i);
	
			// Check if we found the exact root
			if (square == x) {
				return i;
			}
			
			// Check if we went too far
			if (square > x) {
				return i - 1; // Return the previous number
			}
			
			i++;
		}
	}
}