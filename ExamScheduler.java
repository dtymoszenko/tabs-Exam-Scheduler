import java.util.ArrayList;

/**
 * ExamScheduler is a class that is a collection of static recursive utility methods that are used
 * to help create the Schedule. This includes all variations of a schedule or the first schedule
 * that can be found.
 *
 * @author David Tymoszenko
 */
public class ExamScheduler {

  /**
   * Returns a valid Schedule for the given set of rooms and courses, or throws an
   * IllegalStateException if no such schedule exists. This method makes use of and calls the find
   * ScheduleHelperMethod for its functionality.
   * 
   * @param rooms   an array of Room objects which will be used to create a valid schedule
   * @param courses an array of Course objects which will be used to create a valid schedule
   * @return a valid Schedule for the given set of rooms and courses
   * @throws IllegalStateException if no such valid schedule exists from the rooms and courses
   *                               arrays provided
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) throws IllegalStateException {
    Schedule schedule = new Schedule(rooms, courses);
    return findScheduleHelper(schedule, 0);
  }


  /**
   * A recursive helper method for findSchedule which assigns all unassigned courses in a Schedule
   * beginning from the index provided as an argument
   * 
   * @param schedule    A schedule which contains courses, assignments, and rooms.
   * @param courseIndex the index which represents the course that is trying to be assigned to a
   *                    room.
   * @return a Schedule object which represents a valid Schedule where all courses can be assigned
   *         to rooms.
   * @throws IllegalStateException if the schedule is invalid (meaning it is not complete but at the
   *                               final index)
   */
  private static Schedule findScheduleHelper(Schedule schedule, int courseIndex)
      throws IllegalStateException {
    Schedule newSchedule = null;
    Schedule firstSchedule;

    // 1. If the provided index is equal to the number of courses, check to see if the Schedule is
    // complete. If so, return the schedule; otherwise throw an IllegalStateException indicating
    // that this Schedule is invalid.
    if (courseIndex == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        return schedule;
      } else {
        throw new IllegalStateException("The Schedule is invalid");
      }
    }

    // 2.If the provided index corresponds to a course that has already been assigned to a room,
    // recursively assign the courses at the following indexes and return the resulting schedule.
    if (schedule.isAssigned(courseIndex)) {
      firstSchedule = findScheduleHelper(schedule, courseIndex + 1);
      return firstSchedule;
    }
    // 3.If the provided index corresponds to a course that has NOT already been assigned to a
    // room, iteratively try to assign it to each possible valid Room and recursively assign the
    // courses at the following indexes. If this course cannot be assigned to that room, try the
    // next one; return the resulting schedule.
    else {
      for (int i = 0; i < schedule.getNumRooms(); i++) {
        if (schedule.getCourse(courseIndex).getNumStudents() <= schedule.getRoom(i).getCapacity()) {
          try {
            newSchedule = schedule.assignCourse(courseIndex, i);
            firstSchedule = findScheduleHelper(newSchedule, courseIndex + 1);
            return firstSchedule;
          } catch (Exception e) {
            // do nothing
          }
        }
      }
    }

    throw new IllegalStateException("There are no schedules that can be created.");
  }

  /**
   * Returns an ArrayList containing all possible Schedules for the given set of rooms and courses
   * If none can be created, then this ArrayList will be empty. This method calls the helper method
   * findAllSchedulesHelper()
   * 
   * @param rooms   an array of Room objects which will be used to create an ArrayList of all valid
   *                schedules
   * @param courses an array of Course objects which will be used to create an ArrayList of all
   *                valid schedules
   * @return an ArrayList of type Schedule which contains all possible schedules for the given set
   *         of rooms and courses.
   */
  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    Schedule schedule = new Schedule(rooms, courses);
    return findAllSchedulesHelper(schedule, 0);
  }

  /**
   * A private recursive helper method which assigns all unassigned courses in a Schedule in ALL
   * POSSIBLE ways, beginning from the index provided as an argument.
   * 
   * @param schedule    A schedule which contains an array of Courses, Rooms, and the corresponding
   *                    assignments of each course to each room.
   * @param courseIndex The index of the course. In order to find ALL schedules, the initial value
   *                    should always be 0.
   * @return an ArrayList of type Schedule which contains all possible schedules
   */
  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int courseIndex) {
    ArrayList<Schedule> allSchedules = new ArrayList<>();
    Schedule newSchedule = null;

    // 1.If the provided index is equal to the number of courses, check to see if the Schedule is
    // complete. If so, add it to an ArrayList of possible schedules and return the ArrayList.
    if (courseIndex == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        allSchedules.add(schedule);
        return allSchedules;
      }
    }

    // 2.If the provided index corresponds to a course that has already been assigned to a room,
    // recursively add all possible valid schedules from the following indexes to an ArrayList of
    // Schedules and return this ArrayList
    if (schedule.isAssigned(courseIndex)) {
      ArrayList<Schedule> tempSchedules =
          new ArrayList<>(findAllSchedulesHelper(schedule, courseIndex + 1));
      allSchedules.addAll(tempSchedules);
      return allSchedules;
    }
    // 3.If the provided index corresponds to a course that has NOT already been assigned to a
    // room, iteratively try to assign it to each possible valid Room and recursively add all
    // possible valid schedules from the following indexes to an ArrayList of Schedules and
    // return this ArrayList.
    else {
      for (int i = 0; i < schedule.getNumRooms(); i++) {
        if (schedule.getCourse(courseIndex).getNumStudents() <= schedule.getRoom(i).getCapacity()) {
          try {
            newSchedule = schedule.assignCourse(courseIndex, i);
            allSchedules.addAll(findAllSchedulesHelper(newSchedule, courseIndex + 1));
          } catch (Exception e) {
            // do nothing
          }

        }
      }
    }

    return allSchedules;
  }
}
