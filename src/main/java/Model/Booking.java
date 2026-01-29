package Model;

public class Booking {
    private int bookingId;
    private Customer customer;
    private TimeMachine timeMachine;
    private TimePeriod timePeriod;
    private Guide guide;

    //Konstruktør
    public Booking(int bookingId, Customer customer, TimeMachine timeMachine, TimePeriod timePeriod, Guide guide) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.timeMachine = timeMachine;
        this.timePeriod = timePeriod;
        this.guide = guide;
    }

    //getter
    public int getBookingId() {
        return bookingId;
    }



}
