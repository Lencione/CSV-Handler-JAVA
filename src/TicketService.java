import java.util.List;

public class TicketService {
  private final TicketDao ticketDao;
  public TicketService(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }
  public List<Ticket> getAll() {
    List<Ticket> tickets;
    try {
      tickets = this.ticketDao.getAll();
      if (tickets.isEmpty()) {
        throw new RuntimeException("No tickets found");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return tickets;
  }

  public Ticket get(int id) {
    Ticket ticket;
    try {
      ticket = this.ticketDao.getById(id);
      if (ticket == null) {
        throw new RuntimeException("No ticket found");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return ticket;
  }


  public void update(Ticket ticket) {
    try{
      ticketDao.update(ticket);
    }catch (Exception e){
      throw new RuntimeException(e);
    }
    return;
  }

  public void create(Ticket ticket) {
    try{
      ticketDao.create(ticket);
    }catch (Exception e){
      throw new RuntimeException(e);
    }
    return;
  }

  public void delete(int id) {
    try{
      ticketDao.delete(id);
    }catch (Exception e){
      throw new RuntimeException(e);
    }
    return;
  }
}
