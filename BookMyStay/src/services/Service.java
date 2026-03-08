package services;

/**
 * Represents an optional add-on service a guest can attach to their booking.
 * Examples: Breakfast, Spa, Airport Pickup.
 */
public class Service {

    private String name;   // Name of the service (e.g., "Breakfast")
    private double price;  // Extra charge for this service

    /** Creates a service with the given name and price. */
    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName()  { return name; }
    public double getPrice() { return price; }
}
