package booking;

import inventory.InventoryService;
import java.util.ArrayList;
import java.util.List;

/**
 * Keeps a record of all confirmed reservations (UC6).
 *
 * Data structure used:
 *   List<Reservation> — an ordered list so bookings are stored
 *   in the sequence they were confirmed (oldest first).
 *
 * Supports:
 *   - Recording a confirmed booking
 *   - Viewing the full booking history (operational report)
 *   - Cancelling a booking by room ID (removes it and restores inventory)
 */
public class BookingHistory {

    // Ordered list of all confirmed reservations
    private static List<Reservation> confirmedBookings = new ArrayList<>();

    /**
     * Adds a confirmed reservation to the history.
     * Called by BookingService right after a booking is confirmed.
     *
     * @param reservation the confirmed reservation to record
     */
    public static void addBooking(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    /**
     * Prints all confirmed reservations as an operational report.
     * Shows each guest's name, room type, and assigned room ID.
     */
    public static void viewHistory() {
        if (confirmedBookings.isEmpty()) {
            System.out.println("No confirmed bookings yet.");
            return;
        }

        System.out.println("--- Booking History ---");
        for (int i = 0; i < confirmedBookings.size(); i++) {
            Reservation r = confirmedBookings.get(i);
            System.out.println((i + 1) + ". Guest: " + r.getGuest().getName()
                    + " | Room Type: " + r.getRoomType()
                    + " | Room ID: " + r.getRoomId());
        }
        System.out.println("Total confirmed bookings: " + confirmedBookings.size());
    }

    /**
     * Cancels a booking by its room ID.
     * Removes it from the history and restores the room to inventory
     * so it can be booked again.
     *
     * @param roomId the room ID to cancel (e.g., "Single-1")
     */
    public static void cancelBooking(String roomId) {
        // Search the list for a booking with the given room ID
        for (int i = 0; i < confirmedBookings.size(); i++) {
            Reservation r = confirmedBookings.get(i);

            if (roomId.equals(r.getRoomId())) {
                confirmedBookings.remove(i);                     // Remove from history
                InventoryService.restoreRoom(r.getRoomType(), roomId); // Give room back to inventory
                System.out.println("Booking cancelled for: " + r.getGuest().getName()
                        + " | Room " + roomId + " is now available again.");
                return;
            }
        }

        System.out.println("No confirmed booking found with room ID: " + roomId);
    }
}
