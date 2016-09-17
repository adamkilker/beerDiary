package beerdiary;

public class User {

	private String userName;
	private String fName;
	private String lName;

	public User(String userName, String fName, String lName) {
		this.userName = userName;
		this.fName = fName;
		this.lName = lName;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getFName() {
		return this.fName;
	}

	public String getLName() {
		return this.lName;
	}
}
