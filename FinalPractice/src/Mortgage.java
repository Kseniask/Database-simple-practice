
public class Mortgage extends Loan {


private String address;

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Mortgage(int loan_num, String full_name, String email, int amount, String loan_type) {
		super(loan_num, full_name, email, amount, loan_type);
		// TODO Auto-generated constructor stub
	}
@Override
public String toString() {
	return super.toString() + "; Address: " + address;
	
}

}
