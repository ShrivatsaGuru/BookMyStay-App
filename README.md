# BookMyStay-App

## Use Case 4: Reservation Confirmation & Room Allocation

+ Use Case 4 implements the reservation confirmation and room allocation process.
+ Booking requests are dequeued from the booking queue and processed sequentially.
+ The InventoryService assigns a unique room ID for each confirmed reservation.
+ A HashSet<String> stores booked room IDs to guarantee uniqueness and prevent duplicates.
+ A HashMap<String, Set<String>> maps room types to allocated rooms for organized inventory management.
+ The system ensures no room ID is reused, preventing double-booking scenarios.
+ Room allocation is handled in a logical atomic operation, ensuring consistent inventory updates.
+ Once a room is assigned, the reservation object is updated with the allocated room ID.
+ The design supports instant synchronization between booking requests and room availability.
+ This approach ensures strong booking integrity and conflict-free room allocation.
