package booking;

import hoteladmin.Guest;
import inventory.InventoryService;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Service responsible for managing hotel booking requests using a FIFO queue.
 * <p>
 * Implements Use Case 3 &amp; 4: Booking requests are accepted sequentially and
 * processed in the order they arrive (first-come-first-served), ensuring
 * fairness during peak demand. Each request is stored as a {@link Reservation}
 * that pairs the guest with their desired room type. On confirmation, a unique
 * room ID is assigned via {@link inventory.InventoryService#assignRoom} (UC4).
 * </p>
 *
 * <p>Data structure used: {@code Queue<Reservation>} backed by a {@link LinkedList},
 * providing O(1) enqueue and dequeue operations.</p>
 */
public class BookingService {

    /**
     * Queue of pending booking requests in arrival order (FIFO).
     * Stores {@link Reservation} objects so each request carries its own
     * guest and room type — preventing the bug of a shared roomType field
     * being overwritten by subsequent requests.
     */
    Queue<Reservation> bookingRequests = new LinkedList<>();

    /**
     * Enqueues a new booking request for the given guest and room type.
     * <p>
     * The request is added to the tail of the queue, preserving FIFO order.
     * Multiple requests can be queued without overwriting each other.
     * </p>
     *
     * @param guest    the guest making the booking request
     * @param roomType the type of room the guest wants to book
     */
    public void addBookingRequest(Guest guest, String roomType) {
        Reservation reservation = new Reservation(guest, roomType);
        bookingRequests.add(reservation);
        System.out.println("Booking request queued for: " + guest.getName() + " (" + roomType + ")");
    }

    /**
     * Processes the next booking request at the head of the queue (FIFO).
     * <p>
     * Dequeues the oldest pending {@link Reservation}, checks room availability
     * via {@link InventoryService}, and confirms or rejects the booking accordingly.
     * A simulated processing delay of 3 seconds is applied to represent
     * real-world booking confirmation time.
     * </p>
     */
    public void processBookingRequest() {
        if (bookingRequests.isEmpty()) {
            System.out.println("No booking requests to process.");
            return;
        }

        Reservation reservation = bookingRequests.poll();
        Guest guest = reservation.getGuest();
        String roomType = reservation.getRoomType();

        System.out.println("Processing booking request for: " + guest.getName() + " | Room type: " + roomType);

        if (InventoryService.isAvailable(roomType)) {
            try {
                // Simulate booking confirmation processing time
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Booking process interrupted: " + e.getMessage());
            }
            // UC4: Assign a unique room ID and mark it as booked (prevents reuse)
            String roomId = InventoryService.assignRoom(roomType);
            reservation.setRoomId(roomId); // Store the assigned room ID on the reservation

            // UC6: Save the confirmed reservation to booking history
            BookingHistory.addBooking(reservation);

            System.out.println("Booking confirmed for: " + guest.getName() + " | Assigned Room: " + roomId);
        } else {
            System.out.println("No " + roomType + " rooms available. Booking request for " + guest.getName() + " cannot be processed.");
        }
    }
}
