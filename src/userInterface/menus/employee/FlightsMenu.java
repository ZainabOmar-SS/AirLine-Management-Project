package userInterface.menus.employee;


import entity.flight.Flight;
import userInterface.menus.Menu;

public class FlightsMenu extends Menu {

    public FlightsMenu(Menu invokingMenu) {
        super(invokingMenu);
    }

    @Override
    public void start() {
        Flight flight = getHelper().printFlights(this);

        FlightOptionsMenu flightOptionsMenu = new FlightOptionsMenu(this, flight);
        flightOptionsMenu.start();
    }

}