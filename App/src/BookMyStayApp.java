import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * MAIN CLASS - UseCase3InventorySetup
 * ============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This class demonstrates how room availability
 * is managed using a centralized inventory.
 *
 * Room objects are used to retrieve pricing
 * and room characteristics.
 *
 * No booking or search logic is introduced here.
 *
 * @author Priankshi
 * @version 3.0
 */
public class UseCase3InventorySetup {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        System.out.println("Hotel Room Inventory Status");
        System.out.println();

        displayRoomDetails(singleRoom, inventory);
        displayRoomDetails(doubleRoom, inventory);
        displayRoomDetails(suiteRoom, inventory);
    }

    /**
     * Displays room details along with current availability.
     *
     * @param room      room object
     * @param inventory centralized inventory
     */
    private static void displayRoomDetails(Room room, RoomInventory inventory) {
        System.out.println(room.getRoomType() + ":");
        System.out.println("Beds: " + room.getBeds());
        System.out.println("Size: " + room.getSize() + " sqft");
        System.out.println("Price per night: " + room.getPricePerNight());
        System.out.println("Available Rooms: " +
                inventory.getRoomAvailability().get(room.getRoomType()));
        System.out.println();
    }
}

/**
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This class acts as the single source of truth
 * for room availability in the hotel.
 *
 * Room pricing and characteristics are obtained
 * from Room objects, not duplicated here.
 *
 * This avoids multiple sources of truth and
 * keeps responsibilities clearly separated.
 *
 * @version 3.1
 */
class RoomInventory {

    /**
     * Stores available room count for each room type.
     *
     * Key   -> Room type name
     * Value -> Available room count
     */
    private Map<String, Integer> roomAvailability;

    /**
     * Constructor initializes the inventory
     * with default availability values.
     */
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    /**
     * Initializes room availability data.
     *
     * This method centralizes inventory setup
     * instead of using scattered variables.
     */
    private void initializeInventory() {
        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 2);
    }

    /**
     * Returns the current availability map.
     *
     * @return map of room type to available count
     */
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    /**
     * Updates availability for a specific room type.
     *
     * @param roomType the room type to update
     * @param count    new availability count
     */
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**
 * ============================================================
 * ABSTRACT CLASS - Room
 * ============================================================
 *
 * Use Case 3 Support Class
 *
 * Description:
 * This class represents the common structure
 * of a hotel room.
 *
 * Inventory is not managed here.
 * Only room characteristics are defined here.
 *
 * Since this is a supporting class newly added
 * for the refactored setup, version is kept as 3.0.
 *
 * @version 3.1
 */
abstract class Room {

    /**
     * Returns room type name.
     *
     * @return room type
     */
    public abstract String getRoomType();

    /**
     * Returns number of beds.
     *
     * @return number of beds
     */
    public abstract int getBeds();

    /**
     * Returns room size in square feet.
     *
     * @return room size
     */
    public abstract int getSize();

    /**
     * Returns room price per night.
     *
     * @return room price
     */
    public abstract double getPricePerNight();
}

/**
 * ============================================================
 * CLASS - SingleRoom
 * ============================================================
 *
 * Description:
 * Represents a single room and its characteristics.
 *
 * @version 3.1
 */
class SingleRoom extends Room {

    @Override
    public String getRoomType() {
        return "Single Room";
    }

    @Override
    public int getBeds() {
        return 1;
    }

    @Override
    public int getSize() {
        return 250;
    }

    @Override
    public double getPricePerNight() {
        return 1500.0;
    }
}

/**
 * ============================================================
 * CLASS - DoubleRoom
 * ============================================================
 *
 * Description:
 * Represents a double room and its characteristics.
 *
 * @version 3.1
 */
class DoubleRoom extends Room {

    @Override
    public String getRoomType() {
        return "Double Room";
    }

    @Override
    public int getBeds() {
        return 2;
    }

    @Override
    public int getSize() {
        return 400;
    }

    @Override
    public double getPricePerNight() {
        return 2500.0;
    }
}

/**
 * ============================================================
 * CLASS - SuiteRoom
 * ============================================================
 *
 * Description:
 * Represents a suite room and its characteristics.
 *
 * @version 3.1
 */
class SuiteRoom extends Room {

    @Override
    public String getRoomType() {
        return "Suite Room";
    }

    @Override
    public int getBeds() {
        return 3;
    }

    @Override
    public int getSize() {
        return 750;
    }

    @Override
    public double getPricePerNight() {
        return 5000.0;
    }
}
