import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

  public List<Ticket> getAll() throws RuntimeException, IOException {

    File file = new File("tickets.csv");
    if (!file.exists()) {
      file.createNewFile();
    }

    String csvFile = "tickets.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ";";
    List<Ticket> tickets = new ArrayList<>();
    try {
      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null) {
        String[] ticket = line.split(cvsSplitBy);
        Ticket t = new Ticket(Integer.parseInt(ticket[0]), ticket[1], ticket[2], ticket[3], State.valueOf(ticket[4]));
        tickets.add(t);
      }
    } catch (IOException e) {
      throw new RuntimeException("File not found: "+csvFile);
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          throw new RuntimeException("Error closing file");
        }
      }
    }

    return tickets;
  }

  public void update(Ticket ticket) throws IOException {
    List<Ticket> tickets = this.getAll();
    boolean found = false;
    for (Ticket t : tickets) {
      if (t.getId() == ticket.getId()) {
        found = true;
        t.setId(ticket.getId());
        t.setState(ticket.getState());
        t.setResponsible(ticket.getResponsible());
        t.setRequester(ticket.getRequester());
        t.setDescription(ticket.getDescription());
      }
    }
    if (!found) {
      throw new RuntimeException("Ticket not found");
    }

    try {
      FileWriter writer = new FileWriter("tickets.csv");
      for (Ticket t : tickets) {
        writer.append(String.valueOf(t.getId())).append(";")
                .append(t.getDescription()).append(";")
                .append(t.getResponsible()).append(";")
                .append(t.getRequester()).append(";")
                .append(String.valueOf(t.getState())).append(";")
                .append(t.getState().toString()).append("\n");
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return;

  }

  public Ticket getById(int id) throws IOException {
    List<Ticket> tickets = this.getAll();
    for (Ticket t : tickets) {
      if (t.getId() == id) {
        return t;
      }
    }
    throw new RuntimeException("No ticket found");
  }

  public void create(Ticket ticket){
    try {
      List<Ticket> tickets = this.getAll();

      for (Ticket t : tickets) {
        if (t.getId() == ticket.getId()) {
          throw new RuntimeException("Ticket with id " + ticket.getId() + " already exists.");
        }
      }

      FileWriter writer = new FileWriter("tickets.csv", true);
      writer.append(String.valueOf(ticket.getId())).append(";")
              .append(ticket.getDescription()).append(";")
              .append(String.valueOf(ticket.getResponsible())).append(";")
              .append(String.valueOf(ticket.getRequester())).append(";")
              .append(String.valueOf(ticket.getState())).append(";")
              .append(ticket.getState().toString()).append("\n");
      writer.flush();
      writer.close();

    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }


  public void delete(int id) throws IOException {
    List<Ticket> tickets = this.getAll();
    boolean found = false;
    for (Ticket t : tickets) {
      if (t.getId() == id) {
        found = true;
        tickets.remove(t);
        break;
      }
    }
    if (!found) {
      throw new RuntimeException("Ticket not found");
    }

    try {
      FileWriter writer = new FileWriter("tickets.csv");
      for (Ticket t : tickets) {
        writer.append(String.valueOf(t.getId())).append(";")
                .append(t.getDescription()).append(";")
                .append(t.getResponsible()).append(";")
                .append(t.getRequester()).append(";")
                .append(String.valueOf(t.getState())).append(";")
                .append(t.getState().toString()).append("\n");
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return;
  }


}
