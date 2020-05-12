
public class CarLoan extends Loan {

	private String car_plate;
	public CarLoan(int loan_num, String full_name, String email, int amount, String loan_type) {
		super(loan_num, full_name, email, amount, loan_type);
		// TODO Auto-generated constructor stub
	}
	public String getCar_plate() {
		return car_plate;
	}
	public void setCar_plate(String car_plate) {
		this.car_plate = car_plate;
	}
	@Override
	public String toString() {
		return super.toString() + "; Car plate: " + car_plate;
	}
	

}
