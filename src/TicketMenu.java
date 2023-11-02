import java.util.List;
import java.util.Scanner;

public class TicketMenu {

	private final TicketService ticketService = new TicketService(new TicketDao());
	private final Scanner scanner = new Scanner(System.in);

	public void handle() {

		int option;

		do {
			menu();
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					list(State.OPEN);
					break;
				case 2:
					list(State.CLOSED);
					break;
				case 3:
					list();
					break;
				case 4:
					getById();
					break;
				case 5:
					create();
					break;
				case 6:
					update();
					break;
				case 7:
					delete();
					break;
				case 8:
					System.out.println("Exiting!");
					break;
				default:
					System.out.println("Invalid Option, Try again.");
					break;
			}

		} while (option != 8);
	}

	private void menu() {
		System.out.println("=======================================================");
		System.out.println("Menu:");
		System.out.println("1 - List all open tickets");
		System.out.println("2 - List all closed tickets");
		System.out.println("3 - List all");
		System.out.println("4 - Get by id");
		System.out.println("5 - New");
		System.out.println("6 - Update");
		System.out.println("7 - Delete");
		System.out.println("8 - Exit");
		System.out.print("Choose an option: ");
	}

	private void list(State state) {
		try{
			List<Ticket> tickets = ticketService.getAll(state);
			for (Ticket t : tickets) {
				System.out.println();
				System.out.println("=======================================================");
				System.out.println(t.toString());
				System.out.println("=======================================================");
			}
		}catch (Exception e){
			showErrorMessage("An error occurred while trying to list tickets.", e.getMessage());
    }
	}

	private void list() {
		try{
			List<Ticket> tickets = ticketService.getAll();
			for (Ticket t : tickets) {
				System.out.println();
				System.out.println("=======================================================");
				System.out.println(t.toString());
				System.out.println("=======================================================");
			}
		}catch (Exception e){
			showErrorMessage("An error occurred while trying to list tickets.", e.getMessage());
		}
	}

	private void update() {
		try{
			System.out.println("Enter the ticket ID you want to update: ");

			Ticket ticket = ticketService.get(scanner.nextInt());
			scanner.nextLine();

			System.out.printf(ticket.toString());


			System.out.println("\n\nWhat do you want to updade?");
			System.out.println("1 - Description");
      System.out.println("2 - Responsible");
      System.out.println("3 - Requester");
      System.out.println("4 - State");
      System.out.println("5 - Nothing");


      int option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1:
          System.out.print("Description: ");
          ticket.setDescription(scanner.nextLine());
          break;
        case 2:
          System.out.print("Responsible: ");
          ticket.setResponsible(scanner.nextLine());
          break;
        case 3:
          System.out.print("Requester: ");
          ticket.setRequester(scanner.nextLine());
          break;
        case 4:
          System.out.print("State: ");
          ticket.setState(ticket.getState() == State.OPEN ? State.CLOSED : State.OPEN);
          break;
      }

			System.out.println("\n\nTicket updated");
			System.out.println("=======================================================");
			System.out.println(ticket.toString());
			System.out.println("=======================================================");
			ticketService.update(ticket);
		}catch (Exception e){
			showErrorMessage("An error occurred while trying to update a ticket.", e.getMessage());
		}
	}

	private void delete(){
		try{
			System.out.println("Enter the ticket ID you want to delete: ");
			Ticket ticket = ticketService.get(scanner.nextInt());
			scanner.nextLine();

			System.out.printf(ticket.toString());

			System.out.println("\n\nAre you sure?");
			System.out.println("1 - Yes");
			System.out.println("2 - No");
			int option = scanner.nextInt();
			scanner.nextLine();
			if(option == 1) {
				ticketService.delete(ticket.getId());
				System.out.println("Ticket deleted");
				return;
			}
			System.out.println("Ticket was not deleted");
			return;
		}catch (Exception e){
			showErrorMessage("An error occurred while deleting a ticket.",e.getMessage());
		}
	}

	private void create(){
		try{
			Ticket ticket = new Ticket();

			System.out.print("Id: ");
			ticket.setId(scanner.nextInt());
			scanner.nextLine();

			System.out.print("Description: ");
			ticket.setDescription(scanner.nextLine());

			System.out.print("Responsible: ");
			ticket.setResponsible(scanner.nextLine());

			System.out.print("Requester: ");
			ticket.setRequester(scanner.nextLine());

			ticket.setState(State.OPEN);
			ticketService.create(ticket);
			System.out.println("\n\nTicket created");
			System.out.println("=======================================================");
			System.out.println(ticket.toString());
			System.out.println("=======================================================");
		}catch (Exception e){
			showErrorMessage("An error occurred while creating a ticket.",e.getMessage());
		}
	}

	public void getById(){
    try{
      System.out.println("Enter the ticket ID you want to get: ");
			int id = scanner.nextInt();
      scanner.nextLine();
      Ticket ticket = ticketService.get(id);

			System.out.println("=======================================================");
			System.out.println(ticket.toString());
			System.out.println("=======================================================");
    }catch (Exception e){
      showErrorMessage("An error occurred while getting a ticket.",e.getMessage());
    }
  }


	private void showErrorMessage(String error, String message){
		System.out.println();
		System.out.println("=======================================================");
		System.out.println("=======================================================");
		System.out.println();
		System.out.println(error);
		System.out.println(message);
		System.out.println();
		System.out.println("=======================================================");
		System.out.println("=======================================================");
		System.out.println();
	}

}
