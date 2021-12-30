package controller;

import java.util.*;

import model.SMSModel;

public class SMSController {
	private SMSModel model;
	public SMSController(SMSModel model) {
		this.model = model;
	}
	
	public void addStudent(String studentName, int age) {
		this.model.addStudent(studentName, age);
	}
	
	public void removeStudent(String studentName) {
		this.model.removeStudent(studentName);
	}
	
	public ArrayList<String> getStudentNames(){
		return this.model.getStudentNames();
	}
	
	public HashMap<String, Character> getStudentCourseGrades(String studentName){
		return this.model.getStudentCourseGrades(studentName);
	}
	
	public HashMap<String, Integer> getStudentCourseInfo(String studentName){
		return this.model.getStudentCourseInfo(studentName);
	}
	
	public boolean setStudentGrade(String studentName, String courseName, char grade) {
		return this.model.setStudentGrade(studentName, courseName, grade);
	}
	
	public boolean addStudentCourse(String studentName, String courseName, int courseUnits) {
		return this.model.addStudentCourse(studentName, courseName, courseUnits);
	}
}
