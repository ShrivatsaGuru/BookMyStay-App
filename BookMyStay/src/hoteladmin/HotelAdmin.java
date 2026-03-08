package hoteladmin;

import booking.BookingService;
import inventory.InventoryService;
import services.Service;
import services.ServiceManager;

/**
 * Facade for all hotel administration operations.
 * <p>
 * Delegates room inventory management to {@link InventoryService} and
 * booking request handling to {@link BookingService}. Provides a single
 * entry point used by the {@code Main} CLI.
 * </p>
 */
public class HotelAdmin {

    /** Shared inventory service instance used across all admin operations. */
    static InventoryService is = new InventoryService();

    /** Shared booking service instance that holds the persistent request queue. */
    static BookingService bookingService = new BookingService();

    /**
     * Adds the specified number of rooms of a given type to the inventory.
     *
     * @param roomType the category of room (e.g., "Single", "Double", "Suite")
     * @param count    the number of rooms to add
     */
    public static void addRoom(String roomType, int count) {
        is.addRoom(roomType, count);
    }

    /**
     * Sets the nightly price for a given room type.
     *
     * @param roomType the category of room
     * @param price    the price per night
     */
    public static void addPrice(String roomType, double price) {
        is.addPrice(roomType, price);
    }

    /**
     * Returns the current available count for a given room type.
     *
     * @param roomType the category of room
     * @return the number of available rooms of that type
     */
    public static int getRoom(String roomType) {
        return is.getCount(roomType);
    }

    /**
     * Returns the nightly price for a given room type.
     *
     * @param roomType the category of room
     * @return the price per night
     */
    public static double getPrice(String roomType) {
        return is.getPrice(roomType);
    }

    /**
     * Checks whether at least one room of the given type is available.
     *
     * @param roomType the category of room
     * @return {@code true} if rooms are available, {@code false} otherwise
     */
    public static boolean checkRooms(String roomType) {
        return is.isAvailable(roomType);
    }

    /**
     * Enqueues a booking request for the given guest and room type (UC3).
     * <p>
     * Requests are stored in FIFO order so they can be processed fairly.
     * </p>
     *
     * @param guest    the guest making the request
     * @param roomType the type of room the guest wants to book
     */
    public static void addBookingRequest(Guest guest, String roomType) {
        bookingService.addBookingRequest(guest, roomType);
    }

    /**
     * Processes the next pending booking request from the queue (UC3).
     * <p>
     * Dequeues the oldest request, checks availability, and confirms
     * or rejects the booking on a first-come-first-served basis.
     * </p>
     */
    public static void processBookingRequest() {
        bookingService.processBookingRequest();
    }

    /**
     * Adds an add-on service to a confirmed booking by room ID (UC5).
     * Looks up the service name in the catalog and attaches it to the room.
     *
     * @param roomId      the room ID assigned at booking (e.g., "Single-1")
     * @param serviceName name of the service (Breakfast / Spa / Airport Pickup)
     */
    public static void addService(String roomId, String serviceName) {
        Service service = ServiceManager.getServiceFromCatalog(serviceName);
        if (service == null) {
            System.out.println("Unknown service: " + serviceName + ". Available: Breakfast, Spa, Airport Pickup");
        } else {
            ServiceManager.addService(roomId, service);
        }
    }

    /**
     * Prints all add-on services and the total extra charge for a booking (UC5).
     *
     * @param roomId the room ID assigned at booking (e.g., "Single-1")
     */
    public static void printServiceBill(String roomId) {
        ServiceManager.printServiceBill(roomId);
    }
}
