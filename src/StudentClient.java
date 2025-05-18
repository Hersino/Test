
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class StudentClient extends Application {
    public void start(Stage stage) {
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        Button sendButton = new Button("Send");

        sendButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                Student student = new Student(name, age);

                Socket socket = new Socket("localhost", 5400);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(student);
                out.close();
                socket.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student sendt!");
                alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, new Label("Navn:"), nameField, new Label("Alder:"), ageField, sendButton);
        layout.setStyle("-fx-padding: 20;");
        stage.setScene(new Scene(layout, 300, 200));
        stage.setTitle("Student Klient");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
