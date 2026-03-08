package inventory;

import java.util.HashMap;

/**
 * Manages the hotel's room inventory — counts and nightly prices per room type.
 * <p>
 * Uses two static {@link HashMap}s so that inventory state is shared across
 * all service instances:
 * <ul>
 *   <li>{@code roomCount} — maps room type to number of available rooms (UC1)</li>
 *   <li>{@code roomPrice} — maps room type to nightly price (UC1)</li>
 * </ul>
 * Static helper methods support read-only lookups used by guests (UC2) and
 * availability checks during booking processing (UC3/UC4).
 * </p>
 */
public class InventoryService {

    /** Maps room type (e.g., "Single") to the number of available rooms. */
    static HashMap<String, Integer> roomCount = new HashMap<>();

    /** Maps room type (e.g., "Single") to the nightly price. */
    static HashMap<String, Double> roomPrice = new HashMap<>();

    /**
     * Adds rooms of the specified type to the inventory.
     * If the room type already exists, the count is incremented.
     *
     * @param roomType the category of room (e.g., "Single", "Double", "Suite")
     * @param count    the number of rooms to add
     */
    public void addRoom(String roomType, int count) {
        roomCount.put(roomType, roomCount.getOrDefault(roomType, 0) + count);
    }

    /**
     * Sets the nightly price for the specified room type.
     *
     * @param roomType the category of room
     * @param price    the price per night
     */
    public void addPrice(String roomType, double price) {
        roomPrice.put(roomType, price);
    }

    /**
     * Returns the nightly price for the specified room type.
     *
     * @param roomType the category of room
     * @return the price per night, or throws {@link NullPointerException} if not set
     */
    public double getPrice(String roomType) {
        return roomPrice.get(roomType);
    }

    /**
     * Returns the current available count for the specified room type.
     *
     * @param roomType the category of room
     * @return the number of available rooms
     */
    public int getCount(String roomType) {
        return roomCount.get(roomType);
    }

    /**
     * Checks whether at least one room of the specified type is available.
     * <p>
     * Used by {@code BookingService} during request processing to determine
     * if a booking can be confirmed.
     * </p>
     *
     * @param roomType the category of room
     * @return {@code true} if count &gt; 0, {@code false} otherwise
     */
    public static boolean isAvailable(String roomType) {
        return roomCount.getOrDefault(roomType, 0) > 0;
    }

    /**
     * Decrements the available room count by one when a booking is confirmed.
     * <p>
     * Called by {@code BookingService} after a successful booking confirmation
     * to keep inventory in sync. Ensures that once all rooms of a type are
     * booked, subsequent requests are correctly rejected.
     * </p>
     *
     * @param roomType the category of room that was just booked
     */
    public static void bookRoom(String roomType) {
        int current = roomCount.getOrDefault(roomType, 0);
        if (current > 0) {
            roomCount.put(roomType, current - 1);
        }
    }

    /**
     * Prints all room types that currently have at least one available room,
     * along with their count and nightly price (UC2 — guest room search).
     */
    public static void checkRooms() {
        System.out.println("Available Rooms:");
        for (String roomType : roomCount.keySet()) {
            int count = roomCount.get(roomType);
            if (count > 0) {
                double price = roomPrice.getOrDefault(roomType, 5110.0);
                System.out.println(roomType + " | Count: " + count + " | Price: " + price);
            }
        }
    }
}
