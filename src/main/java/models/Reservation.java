package models;

public class Reservation {
    public String firstName;
    public String lastName;
    public String price;
    public String isDeposit;
    public String checkIn;
    public String checkOut;

    public Reservation(String firstName, String lastName, String price, String isDeposit, String checkIn, String checkOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.isDeposit = isDeposit;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
