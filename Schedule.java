import java.util.Arrays;

/**
 * The Schedule Class contains three different arrays of Rooms, courses, and assignments. Courses
 * are assigned to rooms if it is possible and their assignment is stored in the assignments array.
 *
 * @author David Tymoszenko
 */
public class Schedule {
  private Room[] rooms; // an array of the Room objects available for exams
  private Course[] courses; // an array of the Course objects which require exam rooms
  private int[] assignments; // an array where the integer at index N is the index of the room that
  // course[N] has been assigned to

  /**
   * Creates a new Schedule object with an array of rooms and an array of courses. An array of
   * assignments is also created that is the same length as the courses array and no assignments are
   * made, meaning every element in the assignments array is -1.
   * 
   * @param rooms   - An array of the Room objects available for exams
   * @param courses - An array of the Course objects which require exam rooms.
   */
  public Schedule(Room[] rooms, Course[] courses) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = new int[courses.length];
    Arrays.fill(this.assignments, -1);
  }

  /**
   * Creates a new Schedule object which initializes the rooms and courses arrays to the provided
   * values and assignments to the provided assignments array.
   * 
   * @param rooms       - An array of the Room objects available for exams
   * @param courses     - An array of the Course objects which require exam rooms.
   * @param assignments - An array where the integer at index N is the index of the room that
   *                    course[N] has been assigned to.
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }

  /**
   * Returns the number of rooms available in this schedule
   * 
   * @return an int which represents the number of rooms available in the schedule.
   */
  public int getNumRooms() {
    return this.rooms.length;
  }

  /**
   * returns the Room object at the given index in the rooms array
   * 
   * @param roomIndex - the index to return a Room object from the Room array.
   * @return the Room object from the rooms array at the given index (roomIndex).
   * @throws IndexOutOfBoundsException with a descriptive error message if the given index is
   *                                   invalid
   */
  public Room getRoom(int roomIndex) throws IndexOutOfBoundsException {
    if (roomIndex > this.rooms.length - 1 || roomIndex < 0) {
      throw new IndexOutOfBoundsException("The room index provided is invalid because it "
          + "is either less than 0 or larger than the biggest index in the rooms "
          + "array. In other words, there is no course at this index.");
    }
    return this.rooms[roomIndex];
  }

  /**
   * Returns the number of courses in this schedule.
   * 
   * @return an int which represents the number of courses in this schedule.
   */
  public int getNumCourses() {
    return this.courses.length;
  }

  /**
   * Returns the course object at the given index in the courses array
   * 
   * @param courseIndex - The index to be accessed and returned in the Course array
   * @return a Course object from the courses array at the given index (courseIndex).
   * @throws IndexOutOfBoundsException with a descriptive error message if the given index is
   *                                   invalid
   */
  public Course getCourse(int courseIndex) throws IndexOutOfBoundsException {
    if (courseIndex > this.courses.length - 1 || courseIndex < 0) {
      throw new IndexOutOfBoundsException("The course index provided is invalid because it"
          + "is either less than 0 or larger than the biggest index in the courses "
          + "array. In other words, there is no course at this index.");
    }
    return this.courses[courseIndex];
  }

  /**
   * Returns true if and only if the course at the given index has been assigned a room, false
   * otherwise
   * 
   * @param assignmentIndex - The index where we check inside the assignment array to see if a
   *                        course has been assigned a room.
   * @return true if the course at the given index has been assigned a room (!= -1) and false
   *         otherwise
   */
  public boolean isAssigned(int assignmentIndex) {
    if (this.assignments[assignmentIndex] == -1) {
      return false;
    }
    return true;
  }

  /**
   * Returns the Room object assigned to the course at the given index.
   * 
   * @param assignmentIndex - The index where what room is assigned to the course will be found.
   * @return a Room object that is assigned to the course at the given index in the assignment
   *         array.
   * @throws IllegalArgumentException  if the course has not been assigned a room (meaning -1 in
   *                                   assignments array).
   * @throws IndexOutOfBoundsException if the given course index is invalid
   */
  public Room getAssignment(int assignmentIndex)
      throws IllegalArgumentException, IndexOutOfBoundsException {
    if (assignmentIndex > this.assignments.length - 1 || assignmentIndex < 0) {
      throw new IndexOutOfBoundsException("The given course index is invalid. This means it "
          + "is either less than 0 or greater than the length-1 of the assignments array");
    }

    if (this.assignments[assignmentIndex] == -1) {
      throw new IllegalArgumentException("This course has not been assigned a room yet so "
          + "the assignment cannot be returned.");
    }

    int roomIndex = this.assignments[assignmentIndex];
    return this.rooms[roomIndex];

  }

  /**
   * Returns true if and only if all courses have been assigned to rooms, and false otherwise.
   * 
   * @return a boolean: true if all courses have been assigned to rooms and false otherwise
   */
  public boolean isComplete() {
    for (int i = 0; i < this.assignments.length; i++) {
      if (this.assignments[i] == -1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns a new Schedule object with the course at the first argument index assigned to the room
   * at the second argument index. Also makes sure to reduce the capacity of the room that was
   * assigned.
   * 
   * @param courseIndex - the index of the course which will be assigned to the room at the second
   *                    index
   * @param roomIndex   - the index of the room where the course will be assigned
   * @return a new Schedule object with the course at the first argument index assigned to the room
   *         at the second argument index.
   */
  public Schedule assignCourse(int courseIndex, int roomIndex) {
    if (courseIndex > this.courses.length - 1 || courseIndex < 0) {
      throw new IndexOutOfBoundsException("The course index provided is invalid because "
          + "it is either negative or larger than the greatest index in the courses " + "array.");
    }

    if (roomIndex > this.rooms.length - 1 || roomIndex < 0) {
      throw new IndexOutOfBoundsException("The room index provided is invalid because "
          + "it is either negative or larger than the greatest index in the courses " + "array.");
    }

    if (this.assignments[courseIndex] != -1) {
      throw new IllegalArgumentException("The given course has already been assigned "
          + "a room and therefore another room cannot be assigned to it");
    }

    if (this.courses[courseIndex].getNumStudents() > this.rooms[roomIndex].getCapacity()) {
      throw new IllegalArgumentException(
          "The room does not have sufficient capacity " + "for this course.");
    }

    int[] assignmentCopy = new int[this.assignments.length];
    for (int i = 0; i < this.assignments.length; i++) {
      assignmentCopy[i] = this.assignments[i];
    }

    Room[] roomsCopy = new Room[this.rooms.length];
    for (int i = 0; i < this.rooms.length; i++) {
      roomsCopy[i] = this.rooms[i];
    }

    roomsCopy[roomIndex] =
        this.rooms[roomIndex].reduceCapacity(this.courses[courseIndex].getNumStudents());
    assignmentCopy[courseIndex] = roomIndex;

    return new Schedule(roomsCopy, this.courses, assignmentCopy);

  }

  /**
   * Creates and returns a String representation of the Schedule formatted as follows: {CS300: AG
   * 125, CS200: HUM 3650, CS400: Unassigned}
   * 
   * @return a String with the format shown above which represents a Schedule object.
   */
  @Override
  public String toString() {
    String representation = "";
    String assigned = "";
    for (int i = 0; i < this.assignments.length; i++) {
      if (this.assignments[i] == -1) {
        assigned = "Unassigned";
      } else {
        assigned = this.rooms[this.assignments[i]].getLocation();
      }

      if (i == this.assignments.length - 1) {
        representation = representation + this.courses[i].getName() + ": " + assigned;
      } else {
        representation = representation + this.courses[i].getName() + ": " + assigned + ", ";
      }
    }
    return representation;
  }
}
