/**
 * The Room class represents rooms. Courses can be assigned to be in these rooms. Every room has a
 * location and a capacity. When a class is assigned to a room, the capacity of the room is
 * decreased.
 *
 * @author David Tymoszenko
 */
public class Room {
  private String location; // the building and room number, e.g. Noland 168
  private int capacity; // the maximum number of people who can be in the room at a time

  /**
   * Creates a new Room object with a location and a capacity which represents how many students the
   * room can hold at maximum.
   * 
   * @param location - The building and room number, e.g. "Noland 168"
   * @param capacity - The maximum number of people who can be in the room at a time
   * @throws IllegalArgumentException if the provided integer (capacity) is negative (<0).
   */
  public Room(String location, int capacity) throws IllegalArgumentException {
    if (capacity < 0) {
      throw new IllegalArgumentException(
          "It is impossible for the room to have a negative capacity");
    }
    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Returns the location of this room
   * 
   * @return a String which represents the location of the Room
   */
  public String getLocation() {
    return this.location;
  }

  /**
   * Returns the capacity of this room
   * 
   * @return an int which represents the capacity of the room, or the max number of people who be in
   *         the room at a time.
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Creates and returns a new Room object with the same location as this one but with the capacity
   * reduced by a given amount.
   * 
   * @param reduceBy an int value representing what the capacity of the room will be reduced by.
   * @return a new Room object with the same location as this one, but with a capacity less than
   *         this one's by the argument reduceBy's amount.
   * @throws IllegalArgumentException if the argument (reduceBy) is greater than the given Room's
   *                                  capacity with a descriptive error message.
   */
  public Room reduceCapacity(int reduceBy) throws IllegalArgumentException {
    if (reduceBy > this.capacity) {
      throw new IllegalArgumentException("The capacity of the room cannot be reduced by"
          + " more than the original capacity. The capacity cannot be reduced to become"
          + " negative.");
    }
    return new Room(this.location, this.capacity - reduceBy);
  }
}
