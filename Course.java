/**
 * The Course class represents courses at a university. For example a CS class. All classes have a
 * name and a certain # of students enrolled in the course.
 *
 * @author David Tymoszenko
 */
public class Course {

  private String name; // name of the course, eg. "CS300"
  private int numStudents; // the number of students enrolled in the course, e.g. 250

  /**
   * Creates a new Course object with a name and a certain amount of students in the class.
   * 
   * @param name        - The name of the course, e.g. "CS300"
   * @param numStudents - The number of students enrolled in the course, e.g. 250
   * @throws IllegalArgumentException if the provided integer is negative (<0)
   */
  public Course(String name, int numStudents) throws IllegalArgumentException {
    if (numStudents < 0) {
      throw new IllegalArgumentException(
          "The number of students enrolled in a course cannot be negative");
    }
    this.name = name;
    this.numStudents = numStudents;
  }

  /**
   * Returns the name of the course
   * 
   * @return a String which represents the name of the course.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the number of students enrolled in this course.
   * 
   * @return an int which represents the number of students enrolled in this course.
   */
  public int getNumStudents() {
    return this.numStudents;
  }
}
