package sample.Interface;


import sample.Coins.CoinBox;
import sample.Controllers.MainController;
import sample.Tickets.TicketList;


public interface IMethods {
    void setTicketList(TicketList ticketList);
    void setCoinBox(CoinBox coinBox);
    void setSession(MainController mainController, CoinBox coinBox);
    void setClockAndDate(MainController mainController);
}
