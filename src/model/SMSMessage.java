package model;

public class SMSMessage {
	
	private String action;
	private Student student;
	private boolean isError;
	
	public SMSMessage(String action, Student student, boolean error) {
		this.action = action;
		this.student = student;
		this.isError = error;
	}
	
	public String getAction() {
		return this.action;
	}
	
	public Student getStudent() {
		return this.student;
	}
	
	public boolean getIsError() {
		return this.isError;
	}
}
