// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}
	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	public static double endBalance(double loan, double rate, int n, double payment) {
		double balance = loan;
		
		// Iterate through each year of the loan
		for (int i = 0; i < n; i++) {
			// 1. Subtract the payment
			balance = balance - payment;
			
			// 2. Apply interest to the remaining balance
			balance = balance * (1 + rate / 100);
		}
		
		return balance;
	}
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
   // Uses sequential search to compute an approximation of the periodical payment
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		// 1. Set the initial guess to loan / n (assuming no interest)
		double payment = loan / n;
		
		// 2. Reset the iteration counter (as per instructions)
		iterationCounter = 0;
		
		// 3. Loop: calculate endBalance with the current payment guess.
		//    If the balance is positive (we still owe money), increase the payment.
		while (endBalance(loan, rate, n, payment) > 0) {
			payment = payment + epsilon;
			iterationCounter++;
		}
		
		// 4. Return the payment that finally cleared the loan
		return payment;
	}
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        double H = loan ;
		double  L= loan / n;
		iterationCounter = 0;
		
		while(H - L > epsilon)
		{
			if (endBalance(loan, rate, n, (H+L)/2) > 0)
			{
				L=(H+L)/2;
			}
			else 
			{
				H=(H+L)/2;
			}
			iterationCounter ++;
		}
		return ((H+L)/2);
    }
}