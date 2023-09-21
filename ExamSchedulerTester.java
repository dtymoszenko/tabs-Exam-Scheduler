import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains tester methods for all classes including Course, Room, Schedule, and ExamScheduler. If
 * the class and the methods/constructors inside work as expected, return true. Otherwise, return
 * false.
 * 
 * @author David Tymoszenko
 *
 */
public class ExamSchedulerTester {

  public static void main(String[] args) {
    System.out.println("testCourse(): " + testCourse());
    System.out.println("testRoom(): " + testRoom());
    System.out.println("testScheduleAccessors(): " + testScheduleAccessors());
    System.out.println("testAssignCourse(): " + testAssignCourse());
    System.out.println("testFindSchedule(): " + testFindSchedule());
    System.out.println("testFindAllSchedules(): " + testFindAllSchedules());
  }

  /**
   * Verifies functionality of Course constructor and methods and relevant exceptions being thrown
   * 
   * @return true if all functionality is correct and false otherwise.
   */
  public static boolean testCourse() {
    // 1. Tests that an IllegalArgumentException is thrown from constructor when number of students
    // is negative
    Course test1;
    try {
      test1 = new Course("CS300", -50);
      System.out.println(
          "Error: No exception was thrown from the Course constructor when the given number of"
              + " students in a class was negative");
      return false;
    } catch (IllegalArgumentException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception that was not IllegalArgumentException was thrown from the Course"
              + " constructor when the given number of students was negative");
      return false;
    }

    // 2. Tests the functionality of the Course constructor when given valid input.
    // Also tests the functionality of Course.getName() and Course.getNumStudents().
    Course test2;
    try {
      test2 = new Course("CS252", 250);
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown from Course constructor when creating a course with a"
              + " valid name and number of students. No exception should have been thrown");
      return false;
    }

    if (!test2.getName().equals("CS252")) {
      System.out.println(
          "Error: The name of the courses does not match the name provided to the constructor."
              + " This could be an error with the Course constructor or with the getName() method");
      return false;
    }

    if (test2.getNumStudents() != 250) {
      System.out.println(
          "Error: The number of students in the course does not match the number of students"
              + " provided to the constructor. This could be an error with the Course constructor"
              + " or with the getNumStudents() method");
      return false;
    }

    return true;
  }

  /**
   * Verifies functionality of Room class including the constructor, methods, and exceptions being
   * thrown.
   * 
   * @return true if all functionality is correct and false otherwise.
   */
  public static boolean testRoom() {
    // 1.Tests that an IllegalArgumentException is thrown from the Room constructor when creating a
    // Room object with a capacity less than 0
    Room test1;
    try {
      test1 = new Room("Brogden 105", -25);
      System.out.println(
          "Error: No exception was thrown from the Room constructor when creating a room object"
              + " with a negative capacity");
      return false;
    } catch (IllegalArgumentException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: When the given capacity of a room was negative an exception was thrown from"
              + " the Room constructor that was not IllegalArgumentException ");
      return false;
    }

    // 2. Tests the correctness of the Room constructor when given valid input. Also checks the
    // correctness of getLocation() and getCapacity().
    // Also tests the functionality of getLocation(), getCapacity(), and reduceCapacity() when used
    // correctly
    Room test2;
    try {
      test2 = new Room("Brogden 105", 200);
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown from the Room constructor when creating a new Room"
              + " object with valid input");
      return false;
    }

    if (!test2.getLocation().equals("Brogden 105")) {
      System.out.println(
          "Error: the name of the room does not match the name provided to the Room constructor"
              + " when creating a new Room object. This could be an error with the Room constructor"
              + " or the getLocation() method");
      return false;
    }

    if (test2.getCapacity() != 200) {
      System.out.println(
          "Error: the capacity of the room does not match the capacity provided to the Room"
              + " constructor when creating a new Room object. This could be an error with the Room"
              + " constructor or the getCapacity() method");
      return false;
    }

    Room test3;
    try {
      test3 = test2.reduceCapacity(10);
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown when using the reduceCapacity method and reducing the"
              + " capacity of a Room object by a valid amount.");
      return false;
    }

    if (test3.getCapacity() != 190) {
      System.out.println(
          "Error: The reduceCapacity() method did not reduce the capacity of the room by the"
              + " provided amount. This means it has improper functionality.");
      return false;
    }

    if (test2.getCapacity() != 200) {
      System.out.println(
          "Error: the reduceCapacity() method reduced the capacity of the original room which"
              + " is improper functionality. The original Room object's capacity should be "
              + "unchanged");
      return false;
    }

    // 3. Tests that the getCapacity() method throws an error when trying to reduce the capacity of
    // a Room by an amount larger than the original capacity which would make the capacity negative.
    Room test4;
    try {
      test4 = new Room("Ingraham 223", 50);
    } catch (Exception e) {
      System.out
          .println("Error: An exception was thrown from the constructor when creating a Room object"
              + " with valid inputs. No exception should have been thrown");
      return false;
    }

    Room test5;
    try {
      test5 = test4.reduceCapacity(60);
      System.out.println(
          "Error: There was no exception thrown when trying to reduce the capacity of a room by"
              + " more than the original capacity using the reduceCapacity() method. An"
              + " IllegalArgumentException should have been thrown");
      return false;
    } catch (IllegalArgumentException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was NOT IllegalArgumentException when trying to"
              + " reduce the capacity of a room by an amount larger than the original capacity"
              + " using" + " the reduceCapacity() method.");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the assignCourse() method in the Schedule class.
   * 
   * @return true if all functionality is correct (all tests are passed) and false otherwise.
   */
  public static boolean testAssignCourse() {
    Room[] rooms = new Room[3];
    rooms[0] = new Room("AG 150", 200);
    rooms[1] = new Room("CS 1240", 180);
    rooms[2] = new Room("Noland 150", 5);

    Course[] courses = new Course[2];
    courses[0] = new Course("CS300", 20);
    courses[1] = new Course("CS400", 75);

    Schedule schedule = new Schedule(rooms, courses);

    // 1. Attempt to assign the first course to an invalid index room.
    try {
      schedule.assignCourse(0, 3);
      System.out.println(
          "Error: No exception was thrown when trying to assign a valid course to an invalid"
              + " index room");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was not IndexOutOfBoundsException when trying to"
              + " assign a valid course to an invalid index room");
      return false;
    }

    // 2. Attempts to assign an invalid index course to a valid index room
    try {
      schedule.assignCourse(-1, 0);
      System.out.println(
          "Error: No exception was thrown when trying to assign a invalid course to an valid"
              + " index room");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was not IndexOutOfBoundsException when trying to"
              + " assign a invalid course to an valid index room");
      return false;
    }

    // 3. Attempts to assign a valid index course to a valid index room and the course fits into the
    // room. Should work perfectly
    Schedule newSchedule;
    Schedule oldSchedule;
    try {
      newSchedule = schedule.assignCourse(0, 0);
      oldSchedule = schedule;
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown when trying to assign a valid course that fits into a"
              + " valid index room");
      return false;
    }

    // Also makes sure to test that the original schedule was unchanged
    try {
      if (schedule.getRoom(0).getCapacity() != 200) {
        System.out.println(
            "Error: The original room capacity was changed instead of creating a new room object"
                + " and changing the size of that room");
        return false;
      }
    } catch (Exception e) {
      // errors caused by other methods which will be tested later
    }


    // Also makes sure the original schedule is exactly the same
    if (oldSchedule != schedule) {
      System.out
          .println("Error: The original schedule was changed when calling assignCourse() instead of"
              + " creating a new schedule.");
      return false;
    }

    // Lastly, checks that the size of the new room has been decreased by the size of the course.
    try {
      if (newSchedule.getRoom(0).getCapacity() != 180) {
        System.out.println(
            "Error: The size of the room was not decreased by the size of the course when the"
                + " course was assigned to the room");
        return false;
      }
    } catch (Exception e) {
      // Errors caused by other methods which will be tested
    }

    // 4. Attempts to assign a course that has ALREADY been assigned to a valid index room
    Schedule newSchedule2 = null;
    try {
      newSchedule2 = newSchedule.assignCourse(0, 1);
      System.out.println("Error: the given course has already been assigned to a room and an"
          + " IllegalArgumentException should have been thrown, but no exception was thrown");
      return false;
    } catch (IllegalArgumentException e) {
      // expected output
    } catch (Exception e) {
      System.out.println("Error: the given course has already been assigned to a room so"
          + " IllegalArgumentException. However, an exception other than IllegalArgumentException"
          + " was thrown");
      return false;
    }

    if (newSchedule2 != null) {
      System.out.println(
          "When trying to assign a course that has already been assigned to a valid room, an"
              + " exception should be thrown. However, a new schedule WAS created.");
      return false;
    }

    // 5. Tests that when trying to assign a course to a room that does not have sufficient capacity
    // an IllegalArgumentException is thrown
    Schedule newSchedule3 = null;
    try {
      newSchedule3 = newSchedule.assignCourse(1, 2);
      System.out.println(
          "Error: No exception was thrown when trying to assign a course that does not fit in"
              + " a room");
      return false;
    } catch (IllegalArgumentException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was not IllegalArgumentException when trying to"
              + " assign a course that does not fit into a room");
      return false;
    }

    if (newSchedule3 != null) {
      System.out.println("assignCourse did not throw an exception but instead returned a schedule");
      return false;
    }

    // 6. Tests that we are able to assign a course to the same room if it fits. I.E. if multiple
    // courses fit in the same room we can assign 2 courses to 1 room
    Schedule newSchedule4 = null;
    try {
      newSchedule4 = newSchedule.assignCourse(1, 0);
    } catch (Exception e) {
      System.out.println(
          "Error: No exception should be thrown since this course fits into the class size and"
              + " is not already assigned to a room");
      return false;
    }

    // also check that original schedule room size was not changed
    try {
      if (newSchedule.getRoom(0).getCapacity() != 180) {
        System.out.println("Error: original room capacity was changed.");
        return false;
      }
    } catch (Exception e) {
      // ignore for now, test later.
    }

    try {
      if (newSchedule4.getRoom(0).getCapacity() != 105) {
        System.out.println(
            "Error: The capacity of the room was not correctly changed when assigning a course"
                + " to a room that already has a course assigned to it");
        return false;
      }
    } catch (Exception e) {
      // ignore for now, test later.
    }

    return true;
  }

  /**
   * Tests the functionality of all accessor methods in Schedule and the Schedule constructor
   * 
   * @return true if all functionality is correct and false otherwise.
   */
  public static boolean testScheduleAccessors() {
    // 1. Tests that the 2 parameter Schedule constructor works as expected when given correct input
    Room room1 = new Room("AG 150", 200);
    Room room2 = new Room("CS 1240", 189);
    Room[] rooms = new Room[2];
    rooms[0] = room1;
    rooms[1] = room2;

    Course course1 = new Course("CS300", 20);
    Course course2 = new Course("CS400", 75);
    Course[] courses = new Course[2];
    courses[0] = course1;
    courses[1] = course2;

    Schedule schedule;
    try {
      schedule = new Schedule(rooms, courses);
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown when creating a valid Schedule with the 2 parameter"
              + " constructor");
      return false;
    }

    // 2. Tests the functionality of getNumRooms() when called from a schedule created using
    // the 2 parameter constructor
    if (schedule.getNumRooms() != 2) {
      System.out.println("Error: When an array of 2 rooms is passed as input into the 2-parameter"
          + "Schedule the getNumRooms() method does not return 2. This could be an "
          + "error with the constructor or getNumRooms() method.");
      return false;
    }

    // 3. Tests the functionality of getRoom() method when called from a schedule created
    // with the 2 parameter Schedule constructor.
    if (!schedule.getRoom(0).equals(room1)) {
      System.out.println("Error: when an array of 2 rooms is passed as input into the 2"
          + "parameter constructor, the course at index 0 does not match the expected room object."
          + "This could be an error with the 2-parameter constructor or the getRoom() method.");
      return false;
    }
    if (!schedule.getRoom(1).equals(room2)) {
      System.out.println("Error: when an array of 2 courses is passed as input into the 2"
          + "parameter constructor, the course at index 1 does not match the expected room object."
          + "This could be an error with the 2-parameter constructor or the getRoom() method.");
      return false;
    }

    try {
      schedule.getRoom(3);
      System.out.println(
          "Error: No exception was thrown when calling getRoom() with an invalid index after"
              + " creating a schedule object with the 2 parameter constructor");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was not IndexOutOfBoundsException when calling"
              + " getRoom() with an invalid index after creating a schedule object with the 2"
              + " parameter constructor.");
      return false;
    }

    try {
      schedule.getRoom(-1);
      System.out.println(
          "Error: No exception was thrown when calling getRoom() with an invalid index after"
              + " creating a schedule object with the 2 parameter constructor.");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was not IndexOutOfBoundsException when calling"
              + " getRoom() with an invalid index after creating a schedule object with the 2"
              + " parameter constructor");
      return false;
    }

    // 4. Tests the functionality of getNumCourses() when called from a schedule created with
    // 2 parameter Schedule constructor
    if (schedule.getNumCourses() != 2) {
      System.out
          .println("Error: When an array of 2 courses is passed as input into the 2-parameter "
              + "Schedule constructor the getNumCourses() method does not return 2. This "
              + "could be an error with the constructor or the method getNumCourses().");
      return false;
    }

    // 5. Tests the functionality of getCourse() when called from a schedule created with the
    // 2 parameter Schedule constructor
    if (!schedule.getCourse(0).equals(course1)) {
      System.out.println("Error: when an array of 2 courses is passed as input into the 2"
          + "parameter constructor, the course at index 0 does not match the expected course"
          + " object. This could be an error with the 2-parameter constructor or the getCourse()"
          + " method.");
      return false;
    }
    if (!schedule.getCourse(1).equals(course2)) {
      System.out.println("Error: when an array of 2 courses is passed as input into the 2"
          + "parameter constructor, the course at index 1 does not match the expected course"
          + " object. This could be an error with the 2-parameter constructor or the getCourse()"
          + " method.");
      return false;
    }

    try {
      schedule.getCourse(3);
      System.out.println(
          "Error: No exception was thrown when calling getCourse() with an invalid index after"
              + " creating a schedule object with the 2 parameter constructor");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was not IndexOutOfBoundsException when calling"
              + " getCourse() with an invalid index after creating a schedule object with the 2"
              + " parameter constructor.");
      return false;
    }

    try {
      schedule.getCourse(-1);
      System.out.println(
          "Error: No exception was thrown when calling getCourse() with an invalid index after"
              + " creating a schedule object with the 2 parameter constructor.");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown that was not IndexOutOfBoundsException when calling"
              + " getCourse() with an invalid index after creating a schedule object with the 2"
              + " parameter constructor");
      return false;
    }

    // 6. Tests the functionality of isAssigned() when creating a Schedule object with the
    // 2-parameter constructor
    if (schedule.isAssigned(0)) {
      System.out.println(
          "Error: when creating a schedule with the 2-parameter constructor, isAssigned returned"
              + " true at index 0 when it should return false since the constructor should make"
              + " assignments array -1 at every index. This could be an error with the 2-parameter"
              + " constructor or the isAssigned() method");
      return false;
    }
    if (schedule.isAssigned(1)) {
      System.out
          .println("Error: when creating a schedule with the 2-parameter constructor, isAssigned"
              + " returned true at index 1 when it should return false since the constructor"
              + " should make assignments array -1 at every index. This could be an error with the"
              + " 2-parameter constructor or the isAssigned() method");
      return false;
    }

    // 6. Tests the functionality of getAssignment() when creating a Schedule object with
    // 2-parameter constructor
    // I am going to use assignCourse in this, however not test the functionality of it. The
    // functionality of it is tested
    // within the method testAssignCourse().

    Schedule newSchedule = null;
    Schedule newSchedule1 = null;
    try {
      newSchedule = schedule.assignCourse(0, 0);
      newSchedule1 = newSchedule.assignCourse(1, 1);
    } catch (Exception e) {
      // test in other method. avoid exceptions for now.
    }

    Room actualRoom = null;
    try {
      actualRoom = newSchedule1.getAssignment(0);
    } catch (NullPointerException e) {
      // Error with assignCourse. Test in other method
    } catch (Exception e) {
      System.out.println(
          "Error: No exception should be thrown since course 0 should be assigned to room 0"
              + " in this example.");
      return false;
    }

    try {
      if (actualRoom.getCapacity() != 180) {
        System.out.println(
            "Error: The capacity of the room was not decreased when assigned to the course.");
        return false;
      }
    } catch (NullPointerException e) {
      // Error with assignCourse. Will test in other method.
    }


    Room actualRoom1 = null;
    try {
      actualRoom1 = newSchedule1.getAssignment(1);
    } catch (Exception e) {
      System.out.println(
          "Error: No exception should be thrown since course 0 should be assigned to room 0"
              + " in this example.");
      return false;
    }

    if (actualRoom1.getCapacity() != 114) {
      System.out.println(
          "Error: The capacity of the room was not decreased when assigned to the course.");
      return false;
    }


    // 7. Tests that getAssignment() throws an IllegalArgumentException if the course has not been
    // assigned a room
    Schedule schedulerTest;

    Room[] roomsTester = new Room[1];
    roomsTester[0] = new Room("AG 140", 50);

    Course[] courseTester = new Course[1];
    courseTester[0] = new Course("Math 340", 150);

    schedulerTest = new Schedule(roomsTester, courseTester);

    try {
      schedulerTest.getAssignment(0);
      System.out.println(
          "Error: no exception was thrown when calling getAssignment when the course has not"
              + " been assigned a room");
      return false;
    } catch (IllegalArgumentException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: An exception other than IndexOutOfBoundsException was thrown when calling"
              + " getAssignment() with a course that has not been assigned a room");
      return false;
    }

    // 8. Tests that getAssignment() throws an IndexOutOfBoundsException if the given course index
    // is invalid.
    try {
      schedule.getAssignment(3);
      System.out.println(
          "Error: No exception was thrown when calling getAssignment() with an invalid index"
              + " after creating a schedule with the 2 parameter constructor");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out
          .println("Error: Exception was thrown that was not indexOutOfBoundsException when calling"
              + " getAssignment with an invalid index.");
      return false;
    }

    try {
      schedule.getAssignment(-1);
      System.out.println(
          "Error: No exception was thrown when calling getAssignment() with an invalid index"
              + " after creating a schedule with the 2 parameter constructor");
      return false;
    } catch (IndexOutOfBoundsException e) {
      // expected output
    } catch (Exception e) {
      System.out
          .println("Error: Exception was thrown that was not indexOutOfBoundsException when calling"
              + " getAssignment with an invalid index.");
      return false;
    }

    // 9. Tests the functionality of the method isComplete()

    // Checks functionality when all courses are NOT assigned to rooms
    Room[] roomsIsCompleteTest = new Room[2];
    roomsIsCompleteTest[0] = new Room("CS 1240", 200);
    roomsIsCompleteTest[1] = new Room("Ag 130", 100);

    Course[] courseIsCompleteTest = new Course[2];
    courseIsCompleteTest[0] = new Course("Math 340", 100);
    courseIsCompleteTest[1] = new Course("CS 252", 50);

    Schedule isCompleteTest = new Schedule(roomsIsCompleteTest, courseIsCompleteTest);

    boolean result;
    try {
      result = isCompleteTest.isComplete();
    } catch (Exception e) {
      System.out
          .println("Error: An exception was thrown when checking if a schedule is complete. There"
              + " should be no exception thrown");
      return false;
    }

    if (result != false) {
      System.out
          .println("Error: isComplete() should return false when given a schedule with no courses"
              + " assigned to any rooms but it instead returned true");
      return false;
    }

    // Checks functionality of isComplete() when half the courses are assigned to rooms
    Schedule isCompleteTest2 = null;
    try {
      isCompleteTest2 = isCompleteTest.assignCourse(0, 0);
    } catch (Exception e) {
      // Testing for assignCourse errors in a different testerMethod.
    }

    boolean result2 = false;
    try {
      result2 = isCompleteTest2.isComplete();
    } catch (NullPointerException e) {
      // This is a problem with assignCourse. Will test in a different method
    } catch (Exception e) {
      System.out.println(
          "Error: An exception was thrown when calling isComplete with only half the courses"
              + " assigned to rooms");
      return false;
    }

    if (result2 != false) {
      System.out.println(
          "Error: isComplete() returned true when the schedule was not actually complete and"
              + " only half the courses were assigned.");
      return false;
    }

    // Lastly, tests that isComplete() returns true when all the courses are assigned to rooms
    Schedule isCompleteTest3 = null;
    try {
      isCompleteTest3 = isCompleteTest2.assignCourse(1, 1);
    } catch (Exception e) {
      // Error with assign course, will test in other method.
    }

    boolean result3 = false;
    try {
      result3 = isCompleteTest3.isComplete();
    } catch (NullPointerException e) {
      // assignCourse method has incorrect functionality . Will check in other method
    } catch (Exception e) {
      System.out.println(
          "Error: isComplete() method threw an exception when testing a schedule that has all"
              + " courses assigned to a room");
      return false;
    }

    if (result3 != true) {
      System.out.println(
          "Error: isComplete() did not return true when given a schedule where all courses are"
              + " assigned to a room.");
      return false;
    }

    // 10. Checks the correctness of overriden toString method
    Room[] testRooms = new Room[2];
    rooms[0] = new Room("AG 150", 200);
    rooms[1] = new Room("CS 1240", 180);

    Course[] testCourses = new Course[2];
    courses[0] = new Course("CS300", 20);
    courses[1] = new Course("CS400", 75);

    Schedule testSchedule = new Schedule(testRooms, testCourses);

    // Checks the correctness of toString method when both courses are unassigned
    String toPrint;
    toPrint = schedule.toString();
    if (!(toPrint.equals("CS300: Unassigned, CS400: Unassigned"))) {
      System.out
          .println("Error: toString() does not return proper representation of schedules and the"
              + " assignments of courses to rooms");
      return false;
    }

    // Checks the correctness of toString method when both courses are assigned
    String toPrint2 = null;
    try {
      toPrint2 = isCompleteTest3.toString();
    } catch (Exception e) {
      // ignore, error with assign course.
    }

    try {
      if (!(toPrint2.equals("Math 340: CS 1240, CS 252: Ag 130"))) {
        System.out
            .println("Error: The overridden toString() method does not return the correct string"
                + " representation of the schedule when all courses are assigned to a room.");
        return false;
      }
    } catch (NullPointerException e) {
      // error with assignCourse method. Test later.
    }


    return true;
  }

  /**
   * Tests the functionality of the findSchedule() method in the ExamScheduler class.
   * 
   * @return true if findSchedule() has completely correct functionality and false otherwise.
   */
  public static boolean testFindSchedule() {

    Room[] rooms = new Room[2];
    rooms[0] = new Room("AG 150", 10);
    rooms[1] = new Room("CS 1240", 1);

    Course[] courses = new Course[2];
    courses[0] = new Course("CS300", 1);
    courses[1] = new Course("CS400", 10);

    Schedule actualSchedule = null;

    // 1. Calls findSchedule where both all courses should be assigned to rooms.
    try {
      actualSchedule = ExamScheduler.findSchedule(rooms, courses);
    } catch (Exception e) {
      System.out
          .println("Error: An exception was thrown from findSchedule when given a room and course"
              + " array where a schedule can be created");
      return false;
    }

    if (!actualSchedule.isComplete()) {
      System.out.println("Error: The schedule was not completed even though it should have been.");
      return false;
    }

    if (!actualSchedule.getAssignment(0).getLocation().equals("CS 1240")) {
      System.out.println(
          "Error: findSchedule() did not return the correct schedule because it did not assign"
              + " the proper course to the proper room");
      return false;
    }

    if (!actualSchedule.getAssignment(1).getLocation().equals("AG 150")) {
      System.out.println(
          "Error: findSchedule() did not return the correct schedule because it did not assign"
              + " the proper course to the proper room");
      return false;
    }

    // 2. Calls findSchedule where it is impossible to make a complete schedule.
    Room[] rooms2 = new Room[3];
    rooms2[0] = new Room("AG 150", 75);
    rooms2[1] = new Room("CS 1240", 100);
    rooms2[2] = new Room("Noland 150", 15);

    Course[] courses2 = new Course[3];
    courses2[0] = new Course("CS300", 20);
    courses2[1] = new Course("CS400", 110);
    courses2[2] = new Course("CS540", 80);


    try {
      ExamScheduler.findSchedule(rooms2, courses2);
      System.out.println(
          "Error: No exception was thrown when it is impossible to create a complete schedule"
              + " where all courses are assigned to rooms");
      return false;
    } catch (IllegalStateException e) {
      // expected output
    } catch (Exception e) {
      System.out.println(
          "Error: No exception was thrown when it is impossible to create a completed schedule"
              + " where all courses are assigned to a room");
      return false;
    }

    // 3. Calls findSchedule where it is impossible to assign ANY course to ANY room
    Room[] rooms3 = new Room[3];
    rooms3[0] = new Room("AG 150", 50);
    rooms3[1] = new Room("CS 1240", 50);
    rooms3[2] = new Room("Noland 150", 50);

    Course[] courses3 = new Course[3];
    courses3[0] = new Course("CS300", 100);
    courses3[1] = new Course("CS400", 150);
    courses3[2] = new Course("CS540", 200);

    try {
      ExamScheduler.findSchedule(rooms3, courses3);
      System.out.println(
          "Error: No exception was thrown when no courses cold be assigned to any room and no"
              + " valid schedule could be created");
      return false;
    } catch (IllegalStateException e) {
      // expected output
    } catch (Exception e) {
      System.out
          .println("Error: An exception was thrown that was not IllegalStateException when no valid"
              + " schedule could be created");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the findAllSchedules() method in the ExamScheduler class
   * 
   * @return true if findAllSchedules() has completely correct functionality and false otherwise.
   */
  public static boolean testFindAllSchedules() {
    ArrayList<Schedule> allSchedulesResult = new ArrayList<>();

    Room[] rooms = new Room[3];
    rooms[0] = new Room("Room1", 100);
    rooms[1] = new Room("Room2", 150);
    rooms[2] = new Room("Room3", 75);

    Course[] courses = new Course[3];
    courses[0] = new Course("CS200", 50);
    courses[1] = new Course("CS300", 110);
    courses[2] = new Course("CS400", 75);

    // 1. Tests that all schedules are displayed when there are multiple correct variations.
    try {
      allSchedulesResult = ExamScheduler.findAllSchedules(rooms, courses);
    } catch (Exception e) {
      System.out.println("Error: An exception was thrown from findAllSchedules");
      return false;
    }

    if (allSchedulesResult.size() != 2) {
      System.out
          .println("Error: findAllSchedules() did not return 2 schedules when there were 2 possible"
              + " schedule combinations");
      return false;
    }
    try {
      if (!allSchedulesResult.get(0).isComplete()) {
        System.out.println("Error: The schedule returned at index 0 was not complete ");
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      if (!allSchedulesResult.get(1).isComplete()) {
        System.out.println("Error: The schedule returned at index 1 was not complete ");
        return false;
      }
    } catch (Exception e) {
      return false;
    }


    if (!allSchedulesResult.get(0).getAssignment(0).getLocation().equals("Room1")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult.get(0).getAssignment(1).getLocation().equals("Room2")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult.get(0).getAssignment(2).getLocation().equals("Room3")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult.get(1).getAssignment(0).getLocation().equals("Room3")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult.get(1).getAssignment(1).getLocation().equals("Room2")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult.get(1).getAssignment(2).getLocation().equals("Room1")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    // 2. Tests that an empty ArrayList is returned if no possible schedules can be made
    ArrayList<Schedule> allSchedulesResult2 = new ArrayList<>();

    Room[] rooms2 = new Room[3];
    rooms2[0] = new Room("Room1", 75);
    rooms2[1] = new Room("Room2", 100);
    rooms2[2] = new Room("Room3", 15);

    Course[] courses2 = new Course[3];
    courses2[0] = new Course("CS200", 20);
    courses2[1] = new Course("CS300", 110);
    courses2[2] = new Course("CS400", 80);

    try {
      allSchedulesResult2 = ExamScheduler.findAllSchedules(rooms2, courses2);
    } catch (Exception e) {
      System.out.println("Error: an exception was thrown from findAllSchedules()");
      return false;
    }

    if (allSchedulesResult2.size() != 0) {
      System.out
          .println("Error: findAllSchedules() did not return an empty ArrayList when there were no"
              + " possible valid schedules");
      return false;
    }

    // 3. Tests that all possible schedules are returned
    ArrayList<Schedule> allSchedulesResult3 = new ArrayList<>();

    Room[] rooms3 = new Room[3];
    rooms3[0] = new Room("Room1", 75);
    rooms3[1] = new Room("Room2", 100);
    rooms3[2] = new Room("Room3", 15);

    Course[] courses3 = new Course[3];
    courses3[0] = new Course("CS200", 15);
    courses3[1] = new Course("CS300", 80);
    courses3[2] = new Course("CS400", 70);

    try {
      allSchedulesResult3 = ExamScheduler.findAllSchedules(rooms3, courses3);
    } catch (Exception e) {
      System.out.println("Error: an exception was thrown from findAllSchedules()");
      return false;
    }

    if (allSchedulesResult3.size() != 2) {
      System.out.println("Error: The incorrect amount of possible schedules was returned from"
          + " findAllSchedules()");
      return false;
    }

    if (!allSchedulesResult3.get(0).getAssignment(0).getLocation().equals("Room2")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult3.get(0).getAssignment(1).getLocation().equals("Room2")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult3.get(0).getAssignment(2).getLocation().equals("Room1")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult3.get(1).getAssignment(0).getLocation().equals("Room3")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult3.get(1).getAssignment(1).getLocation().equals("Room2")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    if (!allSchedulesResult3.get(1).getAssignment(2).getLocation().equals("Room1")) {
      System.out
          .println("Error: The incorrect schedule was returned after calling findAllSchedules");
      return false;
    }

    return true;
  }

}
