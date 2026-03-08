package booking;

import hoteladmin.Guest;

/**
 * Represents a hotel booking — first as a pending request (UC3),
 * then as a confirmed reservation once a room ID is assigned (UC4).
 *
 * roomId is null while the booking is still in the queue,
 * and is set to the assigned room ID (e.g., "Single-1") on confirmation.
 */
public class Reservation {

    private final Guest guest;    // The guest who made this booking
    private final String roomType; // The type of room requested
    private String roomId;         // Unique room ID assigned on confirmation (UC4)

    /** Creates a pending booking request for a guest and room type. */
    public Reservation(Guest guest, String roomType) {
        this.guest = guest;
        this.roomType = roomType;
    }

    public Guest getGuest()      { return guest; }
    public String getRoomType()  { return roomType; }
    public String getRoomId()    { return roomId; }

    /** Called after booking is confirmed to record the assigned room ID. */
    public void setRoomId(String roomId) { this.roomId = roomId; }
}
