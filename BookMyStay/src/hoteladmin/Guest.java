package hoteladmin;

import inventory.InventoryService;

/**
 * Represents a hotel guest who can browse available rooms and make booking requests.
 * <p>
 * Acts as the primary actor in Use Case 2 (Room Search) and Use Case 3
 * (Booking Request). Guest objects are carried inside {@code Reservation}
 * instances when queued for processing.
 * </p>
 */
public class Guest {

    private String name;

    /**
     * Constructs a new Guest with the given name.
     *
     * @param name the full name of the guest
     */
    public Guest(String name) {
        this.name = name;
    }

    /**
     * Returns the guest's name.
     *
     * @return the name of the guest
     */
    public String getName() {
        return name;
    }

    /**
     * Displays all currently available rooms by delegating to {@link InventoryService}.
     * <p>
     * Used in Use Case 2 to let a guest browse room types, counts, and prices
     * without modifying the inventory.
     * </p>
     */
    public void roomCheck() {
        InventoryService.checkRooms();
    }
}
