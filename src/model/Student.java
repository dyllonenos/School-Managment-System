package model;
import java.util.*;

public class Student {
	private String name;
	private int age;
	private double gpa;
	private HashMap<String, Integer> courseInformation = new HashMap<>();
	private HashMap<String, Character> courseGrades = new HashMap<>();
	private int studentID;
	public static final int MAX_UNITS = 19;
	private int currentNumberOfUnits = 0;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param age
	 */
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
		this.gpa = 0.0;
		generateID();
	}

	/**
	 * Generates a random student ID for the student
	 */
	private void generateID() {
		Random random = new Random();
		int[] idArray = new int[8];
		idArray[0] = (random.nextInt(9) + 1) * 10_000_000;
		idArray[1] = (random.nextInt(9) + 1) * 1_000_000;
		idArray[2] = (random.nextInt(9) + 1) * 100_000;
		idArray[3] = (random.nextInt(9) + 1) * 10_000;
		idArray[4] = (random.nextInt(9) + 1) * 1_000;
		idArray[5] = (random.nextInt(9) + 1) * 100;
		idArray[6] = (random.nextInt(9) + 1) * 10;
		idArray[7] = (random.nextInt(9) + 1) * 1;

		int studentID = 0;

		for (int index = 0; index < idArray.length; index++) {
			studentID += idArray[index];
		}
		this.studentID = studentID;

	}

	/**
	 * Returns the student's name
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the student's age
	 * 
	 * @return
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Returns the student's cumulative GPA
	 * 
	 * @return
	 */
	public double getGPA() {
		return this.gpa;
	}

	/**
	 * Returns the student's ID
	 * 
	 * @return
	 */
	public int getID() {
		return this.studentID;
	}
	
	/**
	 * Returns the 
	 * @return
	 */
	public int getCurrentUnits() {
		return this.currentNumberOfUnits;
	}

	/**
	 * Returns a HashMap of all the courses the student is taking with the key being
	 * the course name and the value being the credit hour for the course.
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getCourseInformation() {
		return this.courseInformation;
	}

	/**
	 * Returns a HashMap of all the courses the student is taking with the key being
	 * the course name and the value being the current grade the student has in that
	 * course.
	 * 
	 * @return
	 */
	public HashMap<String, Character> getAllGrades() {
		return this.courseGrades;
	}

	/**
	 * Adds a 
	 * @param courseName
	 * @param creditHours
	 */
	public boolean addCourse(String courseName, int creditHours) {
		courseName = courseName.toLowerCase();
		if ((this.currentNumberOfUnits + creditHours) <= MAX_UNITS) {
			this.courseInformation.put(courseName, creditHours);
			this.courseGrades.put(courseName, 'I');
			this.currentNumberOfUnits += creditHours;
			updateGPA();
			return true;
		}
		return false;
	}

	public boolean removeCourse(String courseName) {
		courseName = courseName.toLowerCase();
		if (this.courseInformation.containsKey(courseName) && this.courseGrades.containsKey(courseName)) {
			this.currentNumberOfUnits -= this.courseInformation.get(courseName);
			this.courseInformation.remove(courseName);
			this.courseGrades.remove(courseName);
			updateGPA();
			return true;
		}
		return false;
	}

	public boolean setCourseGrade(String courseName, char grade) {
		courseName = courseName.toLowerCase();
		if (this.courseGrades.containsKey(courseName)) {
			this.courseGrades.replace(courseName, grade);
			updateGPA();
			return true;
		}
		return false;
	}

	private void updateGPA() {
		Object[] all_grades = this.courseGrades.values().toArray();
		Object[] allCreditHours = this.courseInformation.values().toArray();
		int numberOfCourses = all_grades.length;

		double totalGradePoints = 0;
		int totalCreditHours = 0;

		if (numberOfCourses > 0) {
			for (int index = 0; index < numberOfCourses; index++) {
				char currentGrade = (char) all_grades[index];
				int currentCreditHour = (int) allCreditHours[index];
				totalCreditHours += currentCreditHour;

				// A
				if (currentGrade == 'A') {
					totalGradePoints += (currentCreditHour * 4);
				}

				// B
				else if (currentGrade == 'B') {
					totalGradePoints += (currentCreditHour * 3);
				}

				// C
				else if (currentGrade == 'C') {
					totalGradePoints += (currentCreditHour * 2);
				}

				// D
				else if (currentGrade == 'D') {
					totalGradePoints += (currentCreditHour * 1);
				}

				// IP
				else if (currentGrade == 'I') {
					totalGradePoints += (currentCreditHour * 0);
					totalCreditHours -= currentCreditHour;
				}

				// E/F
				else {
					totalGradePoints += (currentCreditHour * 0);
				}
			}
			this.gpa = totalGradePoints / totalCreditHours;
			if (totalGradePoints == 0.0 && totalCreditHours == 0.0) {
				this.gpa = 0.0;
			}
		}
	}
}
