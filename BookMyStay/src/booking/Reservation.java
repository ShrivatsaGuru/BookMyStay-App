package booking;

import hoteladmin.Guest;

/**
 * Represents a hotel room booking request made by a guest.
 * <p>
 * Encapsulates the guest and their desired room type into a single unit
 * so that booking requests can be stored together in a queue (UC3).
 * </p>
 */
public class Reservation {

    private final Guest guest;
    private final String roomType;

    /**
     * Constructs a new Reservation for the given guest and room type.
     *
     * @param guest    the guest making the booking request
     * @param roomType the type of room requested (e.g., "Single", "Double", "Suite")
     */
    public Reservation(Guest guest, String roomType) {
        this.guest = guest;
        this.roomType = roomType;
    }

    /**
     * Returns the guest associated with this reservation.
     *
     * @return the guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * Returns the room type requested in this reservation.
     *
     * @return the room type string
     */
    public String getRoomType() {
        return roomType;
    }
}
