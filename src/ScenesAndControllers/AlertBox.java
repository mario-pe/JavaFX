package ScenesAndControllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by domowy on 2016-08-04.
 */
public class AlertBox {

    public static void display(String title, String message) {
        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(250);
        alertWindow.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction (e-> alertWindow.close());

        VBox vbox =new VBox();
        vbox.getChildren().addAll(label, closeButton);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();

    }
}
