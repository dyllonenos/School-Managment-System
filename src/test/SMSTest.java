package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.Student;

public class SMSTest {
	
	// -------------------- Test Student class --------------------
	@Test
	public void testGPA() {
		Student student = new Student("Test", 0);
		assertEquals(0.0, student.getGPA(), 0.01);
		// Add Courses
		student.addCourse("Math", 4);
		student.addCourse("Bio", 3);
		student.addCourse("CS", 3);
		student.addCourse("Engl", 3);
		student.addCourse("Anth", 3);
		student.addCourse("GenEd", 3);
		
		// Set grades for all courses
		student.setCourseGrade("Math", 'B');
		student.setCourseGrade("Bio", 'C');
		student.setCourseGrade("CS", 'A');
		student.setCourseGrade("Engl", 'D');
		student.setCourseGrade("Anth", 'F');
		student.setCourseGrade("GenEd", 'I');
		student.setCourseGrade("NotAClass", 'F');
		assertEquals(2.06, student.getGPA(), 0.01);
		
	}
	
	@Test
	public void testID() {
		Student student = new Student("Test", 0);
		assertEquals(8, String.valueOf(student.getID()).length());
	}
	
	@Test
	public void testCourseAdd() {
		Student student = new Student("Test", 0);
		assertTrue(student.getCourseInformation().isEmpty());
		assertTrue(student.getAllGrades().isEmpty());
		student.addCourse("Math", 4);
		assertFalse(student.getCourseInformation().isEmpty());
		assertFalse(student.getAllGrades().isEmpty());
		
	}
	
	@Test
	public void testAge() {
		Student student = new Student("Test", 100);
		assertEquals(100, student.getAge());
	}
	
	@Test
	public void testName() {
		Student student = new Student("Test3", 0);
		assertEquals("Test3", student.getName());
	}
	
	@Test
	public void testRemove() {
		Student student = new Student("Test", 0);
		assertTrue(student.getCourseInformation().isEmpty());
		student.addCourse("Science", 4);
		assertFalse(student.getCourseInformation().isEmpty());
		assertTrue(student.removeCourse("Science"));
		assertTrue(student.getCourseInformation().isEmpty());
		assertFalse(student.removeCourse("Science"));
	}
	
	@Test
	public void testUnits() {
		Student student = new Student("Test", 0);
		assertEquals(0, student.getCurrentUnits());
		assertTrue(student.addCourse("Math", 19));
		assertEquals(19, student.getCurrentUnits());
		assertFalse(student.addCourse("Science", 1));
		assertEquals(19, student.getCurrentUnits());
		assertTrue(student.removeCourse("Math"));
		assertEquals(0, student.getCurrentUnits());
		assertTrue(student.addCourse("CSC", 3));
		assertTrue(student.addCourse("Science", 4));
		assertEquals(7,student.getCurrentUnits());
		assertTrue(student.removeCourse("Science"));
		assertEquals(3, student.getCurrentUnits());
	}
}
