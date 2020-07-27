package com.alextim.frontend;

import com.alextim.Pentamino;
import com.alextim.geometry.Coord;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Setter
    private Pentamino pentamino;

    @FXML
    private TextField pathFile;

    @FXML
    private ChoiceBox<Integer> width;

    @FXML
    private ChoiceBox<Integer> height;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label status;

    private GridPane coverage;

    private File file;

    @FXML
    void onCoverageCreate(ActionEvent event) {
        if(coverage != null)
            pane.getChildren().remove(coverage);
        coverage = new GridPane();

        int width = this.width.getSelectionModel().getSelectedItem();
        int height = this.height.getSelectionModel().getSelectedItem();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle rectangle = new Rectangle(50, 50, Color.WHITE);
                int finalJ = j;
                int finalI = i;
                rectangle.setOnMouseClicked(e -> {
                    for (Node node : coverage.getChildren()) {
                        if(GridPane.getRowIndex(node) == finalI && GridPane.getColumnIndex(node) == finalJ) {

                            Rectangle rec = (Rectangle) node;
                            if(rec.getFill().equals(Color.BLACK))
                                rec.setFill(Color.WHITE);
                            else
                                rec.setFill(Color.BLACK);
                            break;
                        }
                    }
                });
                coverage.add(rectangle, j, i);
            }
        }
        coverage.setGridLinesVisible(true);

        coverage.setLayoutX(5);
        coverage.setLayoutY(100);

        pane.getChildren().add(coverage);
    }

    public List<Coord> getNoBlackCell() {
        List<Coord> noBlack = new ArrayList<>();

        for (Node node : coverage.getChildren()) {
            if(node instanceof Rectangle) {
                Rectangle rec = (Rectangle) node;
                if(!rec.getFill().equals(Color.BLACK))
                    noBlack.add(new Coord(GridPane.getColumnIndex(node), GridPane.getRowIndex(node)));
            }
        }

        return noBlack;
    }

    public void setColorCell(Coord coord, Color color) {
        for (Node node : coverage.getChildren()) {
            if(node instanceof Rectangle) {
                if(GridPane.getColumnIndex(node) == coord.x && GridPane.getRowIndex(node) == coord.y) {
                    ((Rectangle) node).setFill(color);
                }
            }
        }
    }

    @FXML
    void onLinksDance(ActionEvent event) {
        status.setText("-");

        int width = this.width.getSelectionModel().getSelectedItem();
        int height = this.height.getSelectionModel().getSelectedItem();

        new Thread(()->{
            try {
                pentamino.start(width, height, file.getAbsoluteFile(), getNoBlackCell());
            }
            catch (FileNotFoundException | InterruptedException e) {
                Platform.runLater(() -> {
                    Widget.showDialog(Alert.AlertType.ERROR, e.getMessage(), "Ошибка");
                });
            }
        }).start();
    }


    @FXML
    void onNextAnswer(ActionEvent event) {
        synchronized(this) {
            this.notify();
        }
    }

    public Color getColor(int i) {
        switch (i){
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.BROWN;
            case 4:
                return Color.TOMATO;
            case 5:
                return Color.CORAL;
            case 6:
                return Color.SIENNA;
            case 7:
                return Color.VIOLET;
            case 8:
                return Color.SNOW;
            case 9:
                return Color.ROYALBLUE;
            case 10:
                return Color.PERU;
            case 11:
                return Color.BEIGE;

            default:
                throw new RuntimeException("should be less 12");
        }
    }

    @FXML
    void onChooseFile(ActionEvent event) {
        file = Widget.showOpenDialog();
        if(file != null)
            pathFile.setText(file.getName());
    }

    public void setStatus(String str) {
        status.setText(str);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        width.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12));
        width.getSelectionModel().select(0);
        height.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12));
        height.getSelectionModel().select(0);
    }


}
