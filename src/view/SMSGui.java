package view;

import java.util.Observable;
import java.util.Observer;

import controller.SMSController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SMSMessage;
import model.SMSModel;
import model.Student;

@SuppressWarnings("deprecation")
public class SMSGui extends Application implements Observer {
	private BorderPane main_pane = new BorderPane();
	private GridPane list_pane = new GridPane();
	private SMSModel model = new SMSModel();
	private SMSController controller = new SMSController(model);

	@Override
	public void start(Stage stage) throws Exception {
		model.addObserver(this);
		makeMenu();
		Scene scene = new Scene(main_pane, 500, 300);
		stage.setScene(scene);
		stage.show();
	}

	private void makeMenu() {
		main_pane.setCenter(list_pane);
		Label welcome_text = new Label("Welcome to the School Managment System (SMS)");
		welcome_text.setUnderline(true);
		
		Button add_student = setAddButton();
		Button remove_student = setRemoveButton();
		
		Button update_grade = setUpdateButton();
		
		VBox controls = new VBox();
		controls.getChildren().add(add_student);
		controls.getChildren().add(remove_student);
		controls.getChildren().add(update_grade);
		controls.setPadding(new Insets(5));
		main_pane.setTop(welcome_text);
		main_pane.setLeft(controls);
	}

	/**
	 * @return
	 */
	private Button setUpdateButton() {
		Button update_grade = new Button("Set grade");
		update_grade.setOnAction((event)->{
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();
			
			TextField name_txt = new TextField();
			TextField age_txt = new TextField();
			
			HBox courses_box = new HBox();
			TextField course_txt = new TextField();
			TextField grade_txt = new TextField();
			courses_box.getChildren().add(course_txt);
			courses_box.getChildren().add(grade_txt);
			
			VBox text_fields = new VBox();
			text_fields.getChildren().add(name_txt);
			text_fields.getChildren().add(age_txt);
			text_fields.getChildren().add(courses_box);
			
			Button submit_button = new Button("Submit");
			
			name_txt.setFocusTraversable(false);
			name_txt.setPromptText("student's name");
			age_txt.setFocusTraversable(false);
			age_txt.setPromptText("student's age");
			course_txt.setFocusTraversable(false);
			course_txt.setPromptText("student's course");
			grade_txt.setFocusTraversable(false);
			grade_txt.setPromptText("student's course grade");
			
			submit_button.setOnAction((event2)->{
				String name = name_txt.getText().trim();
				String course = course_txt.getText().trim();
				char grade = Character.valueOf(grade_txt.getText().trim().charAt(0));
				controller.setStudentGrade(name, course, grade);
				newWindow.close();
			});
			newPane.setBottom(submit_button);
			newPane.setCenter(text_fields);
			
			Scene newScene = new Scene(newPane, 300, 100);
			newWindow.setTitle("Add a Student");
			newWindow.setScene(newScene);
			newWindow.show();
		});
		return update_grade;
	}

	/**
	 * @return
	 */
	private Button setAddButton() {
		Button add_student = new Button("Add");
		add_student.setOnMouseClicked((event)->{
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();
			
			TextField name_txt = new TextField();
			TextField age_txt = new TextField();
			
			VBox text_fields = new VBox();
			text_fields.getChildren().add(name_txt);
			text_fields.getChildren().add(age_txt);
			
			Button submit_button = new Button("Submit");
			
			name_txt.setFocusTraversable(false);
			name_txt.setPromptText("student's name");
			age_txt.setFocusTraversable(false);
			age_txt.setPromptText("student's age");
			
			submit_button.setOnAction((event2)->{
				String name = name_txt.getText().trim();
				int age = Integer.valueOf(age_txt.getText().trim());
				controller.addStudent(name, age);
				newWindow.close();
			});
			newPane.setBottom(submit_button);
			newPane.setCenter(text_fields);
			
			Scene newScene = new Scene(newPane, 300, 100);
			newWindow.setTitle("Add a Student");
			newWindow.setScene(newScene);
			newWindow.show();
			
		});
		return add_student;
	}

	/**
	 * @return
	 */
	private Button setRemoveButton() {
		Button remove_student = new Button("Remove");
		remove_student.setOnMouseClicked((event)->{
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();
			
			TextField txt_fld = new TextField();
			Button submit_button = new Button("Submit");
			txt_fld.setFocusTraversable(false);
			txt_fld.setPromptText("Student's name");
			submit_button.setOnAction((event2)->{
				String name = txt_fld.getText().trim();
				controller.removeStudent(name);
				newWindow.close();
			});
			newPane.setBottom(submit_button);
			newPane.setCenter(txt_fld);
			
			Scene newScene = new Scene(newPane, 300, 100);
			newWindow.setTitle("Remove a Student");
			newWindow.setScene(newScene);
			newWindow.show();
		});
		return remove_student;
	}

	@Override
	public void update(Observable o, Object arg) {
		SMSMessage message = (SMSMessage) arg;
		String action = message.getAction();
		Student student = message.getStudent();
		boolean isError = message.getIsError();
		int row_count = this.list_pane.getRowCount();
		
		if (isError == true) {
			// Alerts
			String str_message = "Error";
			if (action.equals("add")) {
				str_message = "You can not add, this student already exists!";
			}
			
			else if (action.equals("remove")) {
				str_message = "You can not remove a student that does not exist!";
			}
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("ERROR");
			alert.setContentText(str_message);
			alert.show();
		}
		
		else {
			if (action.equals("add")) {
				HBox curr_hbox = new HBox();
				curr_hbox.getChildren().add(new Label(student.getName()));
				curr_hbox.getChildren().add(new Label(" - "));
				curr_hbox.getChildren().add(new Label("" + student.getAge()));
				curr_hbox.getChildren().add(new Label(" - "));
				curr_hbox.getChildren().add(new Label("" + student.getGPA()));
				this.list_pane.add(curr_hbox, 0, row_count+1);
			}
			
			else if (action.equals("remove")) {
				for (Node curr_node : this.list_pane.getChildren()) {
					HBox curr_hbox = (HBox) curr_node;
					
					Label name_label = (Label) curr_hbox.getChildren().get(0);
					Label age_label = (Label) curr_hbox.getChildren().get(2);
					
					String curr_name = name_label.getText();
					int curr_age = Integer.valueOf(age_label.getText());
					
					if (curr_name.equals(student.getName()) && curr_age == student.getAge()) {
						this.list_pane.getChildren().remove(curr_node);
						break;
					}
				}
			}
			
			else if (action.equals("updated")) {
				
			}
		}
		
	}
	
}
