
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import java.io.*;

public class JavaFXFileComp extends Application {
    TextField tfFirst;
    TextField tfSecond;
    Button btn;
    Label labFirst, labSecond;
    Label result;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Compare files");

        FlowPane flow = new FlowPane(10, 10);
        flow.setAlignment(Pos.CENTER);

        Scene scene = new Scene(flow, 200, 200);
        stage.setScene(scene);

        tfFirst = new TextField();
        tfSecond = new TextField();
        tfFirst.setPrefColumnCount(12);
        tfSecond.setPrefColumnCount(12);
        tfFirst.setPromptText("Enter the file name");
        tfSecond.setPromptText("Enter the file name");

        btn = new Button("Compare");
        labFirst = new Label("First file: ");
        labSecond = new Label("Second file: ");
        result = new Label("");

        tfFirst.setOnAction(ae -> btn.fire());
        tfSecond.setOnAction(ae -> btn.fire());

        btn.setOnAction(ae -> {
            int i = 0, j = 0;
            if (tfFirst.getText().isEmpty()) {
                result.setText("First file name missing.");
                return;
            }
            if (tfSecond.getText().isEmpty()) {
                result.setText("Second file name missing.");
                return;
            }
            try (FileInputStream f1 = new FileInputStream(tfFirst.getText());
                 FileInputStream f2 = new FileInputStream(tfSecond.getText())) {
                do {
                    i = f1.read();
                    j = f2.read();
                    if (i != j) break;
                } while (i != -1 && j != -1);
                if (i != j)
                    result.setText("Files are different");
                else
                    result.setText("Files are the same.");

            } catch (IOException e) {
                result.setText("File error");
            }
        });
        flow.getChildren().addAll(labFirst, tfFirst, labSecond, tfSecond, btn, result);

        stage.show();
    }
}
