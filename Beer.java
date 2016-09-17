package beerdiary;

public class Beer {

	private String name;
	private String location;
	private String type;
	private String form;
	private double rating;

	/*
	 * private boolean draft; private boolean bottle; private boolean can;
	 */

	public Beer(String name, String location, String type, String form, double rating) {
		this.name = name;
		this.location = location;
		this.type = type;
		this.form = form;
		this.rating = rating;
	}

	// getters

	public String getName() {
		return this.name;
	}

	public String getLocation() {
		return this.location;
	}

	public String getType() {
		return this.type;
	}

	public String getForm() {
		return this.form;
	}

	public double getRating() {
		return this.rating;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setLocation(String newLocation) {
		this.location = newLocation;
	}

	public void setType(String newType) {
		this.type = newType;
	}

	public void setRating(double newRating) {
		this.rating = newRating;
	}
}
