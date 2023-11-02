public class Ticket {

	private int id;

	private String description;

	private String responsible;

	private String requester;

	private State state;

	public Ticket(int id, String description, String responsible, String requester, State state) {
		this.id = id;
		this.description = description;
		this.responsible = responsible;
		this.requester = requester;
		this.state = state;
	}

	public Ticket() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String toString(){
		return "Ticket #" + id + ": " + description + " (" + state.getDescription() + ")" + "\nResponsible: " + responsible + "\nRequester: " + requester;
	}

}
