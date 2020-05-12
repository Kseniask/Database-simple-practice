
public class Candidate {
	
	private int id;
	private String full_name;
	private String department;
	private String email;
	private int match;

	public Candidate(String full_name, String department,String email, int match) {
		this.full_name = full_name;
		this.department = department;
		this.email = email;
		this.match = match;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getMatch() {
		return match;
	}
	public void setMatch(int match) {
		this.match = match;
	}


}
