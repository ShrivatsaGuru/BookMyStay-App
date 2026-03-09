# BookMyStayApp
A hotel booking app that lets guests search and reserve rooms/services on a first-come, first-served basis. It manages live inventory (room types, counts, prices, amenities), enforces availability holds, processes bookings, and tracks add-on services (e.g., breakfast, airport pickup, spa).

## Use Case 1: Room Inventory Setup & Management 

+ Implements the initial room inventory management module of the BookMyStay Hotel Booking System.
+ Demonstrates the use of HashMap data structures to store room types, available counts, and price per night.
+ Initializes common room categories such as Single, Double, and Suite.
+ Provides O(1) lookup performance for room availability using HashMap.
+ Allows the hotel admin to view, update room counts, and modify room prices.
+ Includes a console-based interactive menu for basic system interaction.
+ Ensures a centralized inventory service acting as the single source of truth for room data.
+ Uses a layered architecture with packages for app, model, service, manager, and utility classes.
+ Demonstrates core Java concepts such as encapsulation, modular design, and separation of concerns.
+ Serves as the foundation for future features like booking management, availability holds, and add-on services.

## Use Case 2: Room Search & Availability Check

+ Implements the guest room search functionality for the BookMyStay Hotel Booking System.
+ Uses existing HashMap-based inventory from Use Case 1 for fast room lookup.
+ Allows guests to view available room types without modifying inventory data.
+ Displays room type, price per night, availability count, and amenities.
+ Filters rooms using an availability check (count > 0) to prevent unavailable rooms from appearing.
+ Introduces a SearchService layer that performs read-only access to inventory.
+ Uses SearchManager to handle user interaction and display search results.
+ Demonstrates defensive checks to ensure guests cannot attempt to book unavailable rooms.
+ Maintains data integrity by preventing inventory mutation during search operations.
+ Provides fast response time through HashMap lookups and efficient filtering.

## Use Case 3: Booking Request (First-Come-First-Served)

+ Use Case 3 implements a booking request system that processes reservations using the First-Come-First-Served (FCFS) principle.
+ The system stores booking requests inside a Queue<Reservation> implemented using LinkedList.
+ When a Guest submits a booking request, the request is added to the queue (enqueue).
+ Requests are processed strictly in arrival order, ensuring fair allocation of booking slots.
+ This approach prevents race conditions and unfair prioritization during high-traffic periods.
+ The BookingQueueService manages all queue operations such as adding and processing reservations.
+ Each Reservation object stores essential details like guest name and requested room type.
+ The queue ensures that the earliest request is always processed first (FIFO behavior).
+ This model closely reflects real-world booking systems used in ticketing, hotels, and reservations.
+ The design demonstrates the use of Queue data structures, service classes, and ordered request handling in Java.

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

## Use Case 5: Add-On Service Selection

+ Use Case 5: Add-On Service Selection allows guests to attach optional services (like Breakfast Service, Spa Service, or Airport Pickup Service) to an existing reservation.
+ The system maintains a Map<String, List<Service>> that links a reservation ID to multiple selected services.
+ Each reservation can have multiple add-on services, enabling flexible booking customization.
+ A Service model is used to represent each service with attributes like service name, description, and price.
+ When a guest selects a service, it is added to a list and mapped to the reservation ID.
+ The system automatically calculates the total additional cost based on selected services.
+ The design uses composition, where a reservation contains a list of services.
+ This approach keeps the reservation-service mapping clean and scalable.
+ New services can easily be added in the future without changing existing reservation logic.
+ Overall, this use case enhances the booking experience by allowing customizable hotel services with dynamic pricing.

## Use Case 6: Booking History & Reporting

+ Use Case 6: Booking History & Reporting maintains a complete record of all confirmed reservations in the system.
+ The core data structure used is a List<Reservation>, which stores reservations in chronological order.
+ When a booking is confirmed by the booking service, the reservation is added to the booking history list.
+ Each reservation record includes details such as guest name, room type, room ID, number of nights, and booking status.
+ The system supports historical tracking, allowing both confirmed and cancelled bookings to remain in the history.
+ A dedicated ReportManager retrieves reservation data from the history service.
+ The reporting feature allows administrators to generate booking history reports directly from stored records.
+ This design ensures a reliable audit trail for all booking activities.
+ The history list can also be used for future analytics such as revenue reports or occupancy statistics.
+ Overall, this use case improves system transparency, reporting capabilities, and customer support operations.
