
public class LoanTypeException extends Exception {
public String[] types = {"student","car"};

public LoanTypeException() {
	super("The Loan type is invalid");
}
}
