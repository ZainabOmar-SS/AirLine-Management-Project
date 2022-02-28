package entity.user;

import entity.booking.Booking;

public class BookingAgent extends User {

    private Booking booking;

    public BookingAgent(int id, UserRole role, String givenName, String familyName, String username, String email, String password, String phone) {
        super(id, role, givenName, familyName, username, email, password, phone);
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }
}
