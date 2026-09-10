package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.model.Matrix;
import org.example.model.MatrixService;
import org.example.model.MatrixServiceImpl;
import org.example.ui.NumberField;

public class Controller {
    MatrixService matrixService = new MatrixServiceImpl();
    private Matrix matrA;
    private Matrix matrB;
    private Matrix matrM;
    int errorCount = 0;

    @FXML private Button startButton;
    @FXML private HBox center;
    @FXML private GridPane matrixA;
    @FXML private GridPane matrixB;
    @FXML private GridPane matrixM;
    @FXML private VBox bottom;
    @FXML private Label question;
    @FXML private ToggleGroup toggleGroup;
    @FXML private RadioButton radio1;
    @FXML private RadioButton radio2;

    @FXML
    private void start() {
        startButton.setVisible(false);
        matrA = new Matrix();
        matrB = new Matrix();
        showMatrix(matrixA, matrA, true);
        showMatrix(matrixB, matrB, true);
        center.setVisible(true);
        bottom.setVisible(true);
        question.setText("Чи узгоджені матриці?");
    }

    private void enterAnswer(ActionEvent event) {
        NumberField tf = (NumberField) event.getTarget();
        int i = tf.getI();
        int j = tf.getJ();
        int value = Integer.parseInt(tf.getText());

        if (matrM.isCorrect(value, i, j)) {
            tf.setEditable(false);
            errorCount = 0;
            tf.setStyle("-fx-background-color: #a5d6a7;"); // Підсвітимо зеленим правильну відповідь
        } else {
            errorCount++;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Неправильна відповідь");
            if (errorCount == 1) {
                alert.setHeaderText("Помилка");
                alert.setContentText("Спробуйте ще раз");
            } else if (errorCount == 2) {
                alert.setHeaderText("Скористайтеся формулою:");
                alert.setContentText(matrM.getFormula(i, j));
            } else {
                alert.setHeaderText("Правильна відповідь:");
                alert.setContentText(matrM.getFormula(i, j) + " = " + matrM.getValue(i, j));
            }
            alert.showAndWait();
        }
    }

    private void showMatrix(GridPane matrix, Matrix matrX, boolean showValues) {
        matrix.getChildren().clear();
        for (int i = 0; i < matrX.getN(); i++) {
            for (int j = 0; j < matrX.getM(); j++) {
                NumberField tf = new NumberField(i, j);
                matrix.add(tf, j, i);
                tf.setMaxWidth(50);
                if (showValues) {
                    tf.setText(String.valueOf(matrX.getValue(i, j)));
                    tf.setEditable(false);
                } else {
                    tf.setOnAction(this::enterAnswer);
                }
            }
        }
    }

    @FXML
    private void next() {
        if (matrixService.isMatchMultiply(matrA, matrB)) {
            if (radio1.isSelected()) {
                matrM = matrixService.multiply(matrA, matrB);
                showMatrix(matrixM, matrM, false);
                bottom.setVisible(false);
            } else {
                showError("Матриці узгоджені, якщо кількість стовпців у першій матриці рівна кількості рядків у другій");
            }
        } else {
            if (radio1.isSelected()) {
                showError("Матриці узгоджені, якщо кількість стовпців у першій матриці рівна кількості рядків у другій");
            } else {
                start();
            }
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Неправильна відповідь");
        alert.setHeaderText("Помилка логіки");
        alert.setContentText(message);
        alert.showAndWait();
    }
}