package com.alextim.frontend;

import com.alextim.Pentamino;
import com.sun.javafx.stage.StageHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

@NoArgsConstructor
public class Widget extends Application {

    private static String[] args;
    private static Pentamino pentamino;

    public Widget(String[] args, Pentamino pentamino) {
        Widget.pentamino = pentamino;
        Widget.args = args;
    }

    public void showAndWait() {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dancing Links");
        primaryStage.setUserData("primaryStage");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/widget.fxml"));
        AnchorPane root = loader.load();

        dependencyInject(loader.getController(), pentamino);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dependencyInject(Controller controller, Pentamino pentamino) {
        controller.setPentamino(pentamino);
        pentamino.setController(controller);
    }

    public static File showOpenDialog() {
        AtomicReference<File> reference = new AtomicReference<>();

        StageHelper.getStages().forEach(stage -> {
            if(stage.getUserData().equals("primaryStage")) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("./pentamino"));
                reference.set(fileChooser.showOpenDialog(stage));
            }
        });
        return reference.get();
    }

    public static boolean showDialog(Alert.AlertType type, String title, String header) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);

        ButtonType buttonTypeYes = new ButtonType("Да");
        ButtonType buttonTypeNo = new ButtonType("Нет");
        ButtonType buttonTypeCancel = new ButtonType("Закрыть", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

        ButtonType buttonType = alert.showAndWait().orElse(buttonTypeCancel);
        if (buttonType.equals(buttonTypeYes)){
            return true;
        }
        else if (buttonType.equals(buttonTypeNo)) {
            return false;
        }
        else {
            return false;
        }
    }
}
