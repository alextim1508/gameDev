package com.alextim.frontend;

import com.sun.javafx.stage.StageHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class Widget extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dancing Links");
        primaryStage.setUserData("primaryStage");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/widget.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static File showOpenDialog() {
        AtomicReference<File> reference = new AtomicReference<>();

        StageHelper.getStages().forEach(stage -> {
            if(stage.getUserData().equals("primaryStage")) {
                reference.set(new FileChooser().showOpenDialog(stage));
            }
        });
        return reference.get();
    }
}
