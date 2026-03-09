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
