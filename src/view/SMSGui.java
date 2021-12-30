package view;

import controller.SMSController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SMSModel;

public class SMSGui extends Application {
	private GridPane main_pane;
	private SMSModel model = new SMSModel();
	private SMSController controller = new SMSController(model);

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		makeMenu();
		Scene scene = new Scene(main_pane, 500, 300);
		stage.setScene(scene);
		stage.show();
		
	}

	private void makeMenu() {
		main_pane = new GridPane();
		Label welcome_text = new Label("Welcome to the School Managment System (SMS)");
		welcome_text.setUnderline(true);
		Button add_student = new Button("Add");
		add_student.setOnMouseClicked((event)->{
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();
			
			TextField txt_fld = new TextField();
			double length = 150;
			double height = 25;
			txt_fld.setMinSize(length, height);
			txt_fld.setMaxSize(length, height);
			txt_fld.setFocusTraversable(false);
			txt_fld.setPromptText("Enter a student's name, age");
			txt_fld.setOnKeyPressed((event2)->{
				if (event2.getCode() == KeyCode.ENTER) {
					String name = txt_fld.getText().split(",")[0].trim();
					int age = Integer.valueOf(txt_fld.getText().split(",")[1].trim());
					controller.addStudent(name, age);
					newWindow.close();
				}
			});
			
			newPane.setCenter(txt_fld);
			
			Scene newScene = new Scene(newPane, 300, 100);
			newWindow.setTitle("Add a Student");
			newWindow.setScene(newScene);
			newWindow.show();
			
		});
		Button remove_student = new Button("Remove");
		remove_student.setOnMouseClicked((event)->{
			Stage newWindow = new Stage();
			BorderPane newPane = new BorderPane();
			
			TextField txt_fld = new TextField();
			double length = 150;
			double height = 25;
			txt_fld.setMinSize(length, height);
			txt_fld.setMaxSize(length, height);
			txt_fld.setFocusTraversable(false);
			txt_fld.setPromptText("Enter a student's name, age");
			txt_fld.setOnKeyPressed((event2)->{
				if (event2.getCode() == KeyCode.ENTER) {
					String name = txt_fld.getText().split(",")[0].trim();
					controller.removeStudent(name);
					newWindow.close();
				}
			});
			
			newPane.setCenter(txt_fld);
			
			Scene newScene = new Scene(newPane, 300, 200);
			newWindow.setTitle("Remove a Student");
			newWindow.setScene(newScene);
			newWindow.show();
		});
		Insets pad = new Insets(10);
		welcome_text.setPadding(pad);
		main_pane.add(welcome_text, 0, 0);
		main_pane.add(add_student, 0, 1);
		main_pane.add(remove_student, 0, 2);
	}
	
}
