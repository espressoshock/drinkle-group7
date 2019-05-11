package com.espressoshock.drinkle.controllers.print;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Print {
    @FXML
    AnchorPane printView;

    public void buttonPrint(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        printDialog(printView, stage);

    }

    private void printDialog(Node subject, Stage window) {
        PrinterJob newPrint = PrinterJob.createPrinterJob();
        if (newPrint == null) {
            return;
        }

        boolean isAvailable = newPrint.showPrintDialog(window);

        if (isAvailable) {
            print(newPrint, subject);
        }
    }

    private void print(PrinterJob job, Node subject) {
        System.out.println(job.jobStatusProperty().asString());

        boolean jobDone = job.printPage(subject);

        if (jobDone) {
            job.endJob();
        }
    }
}


