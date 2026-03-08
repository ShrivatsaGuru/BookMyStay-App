package inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages the hotel's room inventory — counts, prices, and room ID allocation.
 * <p>
 * Uses static data structures so that inventory state is shared across
 * all service instances:
 * <ul>
 *   <li>{@code roomCount} — available room count per type (UC1)</li>
 *   <li>{@code roomPrice} — nightly price per type (UC1)</li>
 *   <li>{@code availableRoomIds} — set of unbooked room IDs per type (UC4)</li>
 *   <li>{@code bookedRoomIds} — all confirmed room IDs, prevents reuse (UC4)</li>
 * </ul>
 * </p>
 */
public class InventoryService {

    /** Maps room type (e.g., "Single") to the number of currently available rooms. */
    static HashMap<String, Integer> roomCount = new HashMap<>();

    /** Maps room type (e.g., "Single") to the nightly price. */
    static HashMap<String, Double> roomPrice = new HashMap<>();

    /**
     * Maps each room type to the set of its available (unbooked) room IDs.
     * Room IDs are generated when rooms are added (UC4).
     */
    static HashMap<String, Set<String>> availableRoomIds = new HashMap<>();

    /**
     * Tracks all room IDs that have been confirmed and allocated.
     * A Set guarantees uniqueness — no room ID can be booked twice (UC4).
     */
    static Set<String> bookedRoomIds = new HashSet<>();

    /** Per-type counter used to generate sequential, unique room IDs. */
    static HashMap<String, Integer> roomIdCounter = new HashMap<>();

    /**
     * Adds rooms of the specified type to the inventory and generates a unique
     * room ID for each one (e.g., "Single-1", "Single-2").
     * <p>
     * The counter persists across calls so IDs are always unique even if
     * {@code addRoom} is called multiple times for the same type.
     * </p>
     *
     * @param roomType the category of room (e.g., "Single", "Double", "Suite")
     * @param count    the number of rooms to add
     */
    public void addRoom(String roomType, int count) {
        roomCount.put(roomType, roomCount.getOrDefault(roomType, 0) + count);

        // Initialise the available ID set for this type if not already present
        availableRoomIds.putIfAbsent(roomType, new HashSet<>());

        int nextId = roomIdCounter.getOrDefault(roomType, 0);
        for (int i = 0; i < count; i++) {
            nextId++;
            availableRoomIds.get(roomType).add(roomType + "-" + nextId);
        }
        roomIdCounter.put(roomType, nextId);
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
     * @return the price per night
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
     *
     * @param roomType the category of room
     * @return {@code true} if count &gt; 0, {@code false} otherwise
     */
    public static boolean isAvailable(String roomType) {
        return roomCount.getOrDefault(roomType, 0) > 0;
    }

    /**
     * Assigns a unique room ID to a confirmed booking (UC4).
     * <p>
     * Picks one available room ID from the type's set, moves it to
     * {@code bookedRoomIds} (preventing reuse), and decrements the
     * available count atomically.
     * </p>
     *
     * @param roomType the category of room being booked
     * @return the assigned room ID (e.g., "Single-2"), or {@code null} if none available
     */
    public static String assignRoom(String roomType) {
        Set<String> available = availableRoomIds.getOrDefault(roomType, new HashSet<>());
        if (available.isEmpty()) {
            return null;
        }

        // Pick the first available room ID
        String roomId = available.iterator().next();

        // Move from available → booked (Set enforces no duplicate bookings)
        available.remove(roomId);
        bookedRoomIds.add(roomId);

        // Decrement the count to keep roomCount in sync
        roomCount.put(roomType, roomCount.get(roomType) - 1);

        return roomId;
    }

    /**
     * Restores a cancelled room back to the available inventory (UC6).
     * Moves the room ID from bookedRoomIds back to availableRoomIds
     * and increments the count so it can be booked again.
     *
     * @param roomType the category of the cancelled room
     * @param roomId   the specific room ID being returned (e.g., "Single-1")
     */
    public static void restoreRoom(String roomType, String roomId) {
        // Remove from booked set
        bookedRoomIds.remove(roomId);

        // Add back to the available set for this room type
        availableRoomIds.putIfAbsent(roomType, new HashSet<>());
        availableRoomIds.get(roomType).add(roomId);

        // Increment the available count
        roomCount.put(roomType, roomCount.getOrDefault(roomType, 0) + 1);
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
