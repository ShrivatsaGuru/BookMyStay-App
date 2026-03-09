# BookMyStay-App

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
