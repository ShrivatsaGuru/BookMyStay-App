package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages add-on services for confirmed bookings (UC5).
 *
 * Data structure used:
 *   Map<String, List<Service>>
 *   Key   = room ID (e.g., "Single-1") assigned during booking confirmation (UC4)
 *   Value = list of services the guest has selected for that room
 *
 * One room can have multiple services attached to it (one-to-many relationship).
 */
public class ServiceManager {

    // Maps each booked room ID to the list of services selected for it
    private static Map<String, List<Service>> reservationServices = new HashMap<>();

    // -----------------------------------------------------------------------
    // Available service catalog — fixed menu of services guests can choose from
    // -----------------------------------------------------------------------
    public static final Service BREAKFAST       = new Service("Breakfast",       500.0);
    public static final Service SPA             = new Service("Spa",            1500.0);
    public static final Service AIRPORT_PICKUP  = new Service("Airport Pickup",  800.0);

    /**
     * Attaches a service to a booking identified by its room ID.
     * If no services exist yet for this room, a new list is created first.
     *
     * @param roomId  the unique room ID assigned at booking (e.g., "Single-1")
     * @param service the Service object to attach
     */
    public static void addService(String roomId, Service service) {
        // Create an empty list for this room if it doesn't already have one
        reservationServices.putIfAbsent(roomId, new ArrayList<>());

        reservationServices.get(roomId).add(service);
        System.out.println("Service '" + service.getName() + "' added to room " + roomId);
    }

    /**
     * Prints all services attached to a booking and the total extra charge.
     *
     * @param roomId the unique room ID to look up
     */
    public static void printServiceBill(String roomId) {
        List<Service> services = reservationServices.get(roomId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services found for room " + roomId);
            return;
        }

        System.out.println("Add-on services for room " + roomId + ":");

        double total = 0;
        for (Service s : services) {
            System.out.println("  - " + s.getName() + " : Rs. " + s.getPrice());
            total += s.getPrice();
        }

        System.out.println("Total add-on charges: Rs. " + total);
    }

    /**
     * Returns the Service object from the catalog that matches the given name.
     * Returns null if the name doesn't match any catalog entry.
     *
     * @param name service name entered by the user
     * @return matching Service, or null
     */
    public static Service getServiceFromCatalog(String name) {
        if (name.equalsIgnoreCase("Breakfast"))      return BREAKFAST;
        if (name.equalsIgnoreCase("Spa"))            return SPA;
        if (name.equalsIgnoreCase("Airport Pickup")) return AIRPORT_PICKUP;
        return null;
    }
}
