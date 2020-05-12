
public abstract class Loan {
protected int loan_num;
protected String full_name;
protected String email;
protected int  amount;
protected String loan_type;

public String getLoan_type() {
	return loan_type;
}
public void setLoan_type(String loan_type) {
	this.loan_type = loan_type;
}
public Loan(int loan_num, String full_name, String email, int amount,String loan_type) {
	this.loan_num = loan_num;
	this.full_name = full_name;
	this.email = email;
	this.amount = amount;
	this.loan_type = loan_type;
}
public int getLoan_num() {
		return loan_num;
	}
	public void setLoan_num(int loan_num) {
		this.loan_num = loan_num;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
public String toString() {
		return "Loan number: "+loan_num + "; Full name: " + full_name + "; Email: "+ email + "; Amount: $" + amount + "; Loan type: " + loan_type ;
	}


}
