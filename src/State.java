public enum State {
	OPEN("Open"),
	CLOSED("Closed");

	private final String description;

	State(String description) {
		this.description = description;
	}

	public String getDescription(){
		return description;
	}
}
