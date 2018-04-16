package sample.Tickets;

import java.util.ArrayList;
import java.util.List;

public class TicketList {

    private List<Ticket> ticketList;

    public TicketList()
    {
        this.ticketList = new ArrayList<>();
    }

    public void add(Ticket ticket) {
        this.ticketList.add(ticket);
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
