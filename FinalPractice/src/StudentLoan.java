
public class StudentLoan extends Loan {
private int stud_id;

public StudentLoan(int loan_num, String full_name, String email, int amount,String loan_type) {
		super(loan_num, full_name, email, amount, loan_type);
		// TODO Auto-generated constructor stub
	}
public void setStud_id(int stud_id) {
	this.stud_id = stud_id;
}
public void setLoan_type(String loan_type) {
	
	super.setLoan_type(loan_type);
}
@Override 
public String toString() {
	return super.toString()+ "; Loan type: " + super.loan_type + ";Student id: "+ stud_id;
	
}



}
