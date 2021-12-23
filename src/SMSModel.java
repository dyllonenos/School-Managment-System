import java.util.*;

public class SMSModel {
	ArrayList<Student> list_of_students;

	/**
	 * Constructor
	 */
	public SMSModel() {
		this.list_of_students = new ArrayList<>();
	}

	/**
	 * This method will add a new student to the school.
	 * 
	 * @param studentName
	 * @param age
	 */
	public void addStudent(String studentName, int age) {
		this.list_of_students.add(new Student(studentName, age));
	}

	/**
	 * This method will remove a student from the school.
	 * 
	 * @param studentName
	 */
	public void removeStudent(String studentName) {
		for (Student currentStudent : list_of_students) {
			String currName = currentStudent.getName();
			if (currName.equals(studentName)) {
				list_of_students.remove(currentStudent);
				break;
			}
		}
	}

	/**
	 * This will return an ArrayList of student's name.
	 * 
	 * @return
	 */
	public ArrayList<String> getStudentNames() {
		ArrayList<String> list_of_names = new ArrayList<>();
		for (Student currentStudent : list_of_students) {
			list_of_names.add(currentStudent.getName());
		}
		return list_of_names;
	}

	/**
	 * This method will return all the courses a student is taking and their current
	 * grades in that course.
	 * 
	 * @param studentName
	 * @return
	 */
	public HashMap<String, Character> getStudentCourseGrades(String studentName) {
		for (Student currentStudent : list_of_students) {
			if (currentStudent.getName().equals(studentName)) {
				return currentStudent.getAllGrades();
			}
		}
		return null;
	}

	/**
	 * This method will return all the courses a student is taking and the credit
	 * hours for each course the student is taking.
	 * 
	 * @param studentName
	 * @return
	 */
	public HashMap<String, Integer> getStudentCourseInfo(String studentName) {
		for (Student currentStudent : list_of_students) {
			if (currentStudent.getName().equals(studentName)) {
				return currentStudent.getCourseInformation();
			}
		}
		return null;
	}

	/**
	 * Sets a grade for a student in a certain course.
	 * 
	 * @param studentName
	 * @param courseName
	 * @param grade
	 * @return
	 */
	public boolean setStudentGrade(String studentName, String courseName, char grade) {
		for (Student currentStudent : list_of_students) {
			if (currentStudent.getName().equals(studentName)) {
				if (currentStudent.getCourseInformation().containsKey(courseName)) {
					currentStudent.setCourseGrade(courseName, grade);
				}
				return true;
			}
		}
		return false;
	}
}
