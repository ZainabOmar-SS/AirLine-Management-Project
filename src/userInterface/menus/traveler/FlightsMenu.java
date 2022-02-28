package userInterface.menus.traveler;

import entity.flight.Flight;
import userInterface.menus.Menu;

public class FlightsMenu extends Menu {

    public FlightsMenu(Menu invokingMenu) {
        super(invokingMenu);
    }

    @Override
    public void start() {
        Flight flight = getHelper().printFlights(this);

        BookTicketMenu bookTicketMenu = new BookTicketMenu(this, flight);
        bookTicketMenu.setUser(getUser());
        bookTicketMenu.start();
    }
}
