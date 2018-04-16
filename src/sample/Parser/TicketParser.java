package sample.Parser;


import sample.Tickets.Ticket;

public class TicketParser {
    private String typeValue;
    private String brand;
    private Ticket ticket;
    public TicketParser(String typeValue, String brand)
    {
        this.brand = brand;
        this.typeValue = typeValue;
    }

    public Ticket setTicketParams()
    {
        String type = this.typeValue.split("\n")[0];
        String brand = this.brand.replace("\n", "");
        double value = Double.valueOf(this.typeValue.substring(typeValue.indexOf("\n"),typeValue.indexOf(" ")));
        int amount = 1;
        this.ticket = new Ticket(type,brand,value,amount);
        return ticket;
    }
}
