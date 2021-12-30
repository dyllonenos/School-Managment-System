package view;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import controller.SMSController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		Button add_course = setAddCourseButton();

		VBox controls = new VBox();
		controls.getChildren().addAll(add_student, remove_student, add_course, update_grade);
		controls.setPadding(new Insets(5));
		main_pane.setTop(welcome_text);
		main_pane.setLeft(controls);
	}

	/**
	 * @return
	 */
	private Button setAddCourseButton() {
		Button add_course = new Button("Add Course");
		add_course.setOnAction((event) -> {
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();

			VBox courses_box = new VBox();
			TextField name_txt = new TextField();
			name_txt.setFocusTraversable(false);
			name_txt.setPromptText("Student's name");

			TextField course_name_txt = new TextField();
			course_name_txt.setFocusTraversable(false);
			course_name_txt.setPromptText("Course name");

			TextField course_units_txt = new TextField();
			course_units_txt.setFocusTraversable(false);
			course_units_txt.setPromptText("Course units");

			courses_box.getChildren().addAll(name_txt, course_name_txt, course_units_txt);

			newPane.setCenter(courses_box);

			Button submit_button = new Button("Submit");

			submit_button.setOnAction((event2) -> {
				controller.addStudentCourse(name_txt.getText(), course_name_txt.getText(),
						Integer.valueOf(course_units_txt.getText()));
				newWindow.close();
			});
			newPane.setBottom(submit_button);
			Scene newScene = new Scene(newPane, 300, 100);
			newWindow.setTitle("Add course");
			newWindow.setScene(newScene);
			newWindow.show();
		});
		return add_course;
	}

	/**
	 * @return
	 */
	private Button setUpdateButton() {
		Button update_grade = new Button("Set grade");
		update_grade.setOnAction((event) -> {
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();

			TextField name_txt = new TextField();
			name_txt.setFocusTraversable(false);
			name_txt.setPromptText("student's name");

			HBox courses_box = new HBox();
			TextField course_txt = new TextField();
			course_txt.setFocusTraversable(false);
			course_txt.setPromptText("student's course");

			ChoiceBox<Character> grade = new ChoiceBox<>();
			grade.getItems().addAll('A', 'B', 'C', 'D', 'E', 'I');

			HBox grades = new HBox();
			grades.getChildren().addAll(new Label("Grade "), grade);

			courses_box.getChildren().addAll(course_txt, grades);

			VBox text_fields = new VBox();
			text_fields.getChildren().addAll(name_txt, courses_box);

			Button submit_button = new Button("Submit");
			submit_button.setOnAction((event2) -> {
				String name = name_txt.getText().trim();
				String course = course_txt.getText().trim();
				char grade_letter = grade.getValue();
				controller.setStudentGrade(name, course, grade_letter);
				newWindow.close();
			});
			newPane.setBottom(submit_button);
			newPane.setCenter(text_fields);

			Scene newScene = new Scene(newPane, 300, 100);
			newWindow.setTitle("Set student's grade");
			newWindow.setScene(newScene);
			newWindow.show();
		});
		return update_grade;
	}

	/**
	 * @return
	 */
	private Button setAddButton() {
		Button add_student = new Button("Add student");
		add_student.setOnMouseClicked((event) -> {
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();

			TextField name_txt = new TextField();
			TextField age_txt = new TextField();

			VBox text_fields = new VBox();
			text_fields.getChildren().addAll(name_txt, age_txt);

			Button submit_button = new Button("Submit");

			name_txt.setFocusTraversable(false);
			name_txt.setPromptText("student's name");
			age_txt.setFocusTraversable(false);
			age_txt.setPromptText("student's age");

			submit_button.setOnAction((event2) -> {
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
		Button remove_student = new Button("Remove student");
		remove_student.setOnMouseClicked((event) -> {
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();

			TextField txt_fld = new TextField();
			Button submit_button = new Button("Submit");
			txt_fld.setFocusTraversable(false);
			txt_fld.setPromptText("Student's name");
			submit_button.setOnAction((event2) -> {
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

			else if (action.equals("updated")) {
				// Student is not found
				if (student == null) {
					str_message = "This student does not exist. Add this student first";
				}
				// Student it not taking this course
				else {
					str_message = "This student is not taking this course. Add this course first";
				}
			}

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("ERROR");
			alert.setContentText(str_message);
			alert.show();
		}

		else {
			if (action.equals("add")) {
				Button student_button = setStudentButton(student);
				this.list_pane.add(student_button, 0, row_count + 1);
			}

			else if (action.equals("remove")) {
				for (Node curr_node : this.list_pane.getChildren()) {
					Button curr_hbox = (Button) curr_node;

					String curr_name = curr_hbox.getText();

					if (curr_name.equals(student.getName())) {
						this.list_pane.getChildren().remove(curr_node);
						break;
					}
				}
			}
		}

	}

	/**
	 * @param student
	 * @return
	 */
	private Button setStudentButton(Student student) {
		Button student_button = new Button(student.getName());
		student_button.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		student_button.setOnAction((event) -> {
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();

			VBox student_info = new VBox();
			student_info.getChildren().addAll(new Label("Name: " + student.getName()),
					new Label("Age: " + student.getAge()), new Label("GPA: " + student.getGPA()),
					new Label("SID: " + student.getID()),
					new Label("Current Units: " + student.getCurrentUnits()));
			newPane.setTop(student_info);

			ScrollPane scroll = new ScrollPane();
			GridPane course_list = new GridPane();
			scroll.setContent(course_list);

			for (Map.Entry<String, Integer> entry : student.getCourseInformation().entrySet()) {
				String course_name = entry.getKey();
				int course_units = entry.getValue();
				char course_grade = student.getAllGrades().get(course_name);
				String text = "Course: " + course_name + ", Units: " + course_units + ", Grade: "
						+ course_grade;
				course_list.add(new Label(text), 0, course_list.getRowCount() + 1);
			}

			newPane.setCenter(scroll);

			Scene newScene = new Scene(newPane, 300, 300);
			newWindow.setTitle("Student Info");
			newWindow.setScene(newScene);
			newWindow.show();
		});
		return student_button;
	}

}
