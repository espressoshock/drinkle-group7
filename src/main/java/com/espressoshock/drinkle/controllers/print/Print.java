package com.espressoshock.drinkle.controllers.print;

import com.espressoshock.drinkle.appState.Current;
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

    //TODO: Add real data.
    public void onSendByEmail() {
        String date = Current.environment.currentDate;
        try {
            composeEmail("play4freesead@gmail.com", "Drinkle!",
                "Hello Drinkle user,\r\n Here is you drink: \r\n" + date);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void composeEmail(String receiver, String subject, String body) throws Exception {

        String mailto = "mailTo:" + receiver;
        mailto += "?subject=" + uriEncode(subject);
        mailto += "^&body=" + uriEncode(body);

        String cmd = "";
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            cmd = "cmd.exe /c start " + mailto;
        } else if (os.contains("osx")) {
            cmd = "open " + mailto;
        } else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
            cmd = "xdg-open " + mailto;
        }
        Runtime.getRuntime().exec(cmd);
    }

    private String uriEncode(String in) {
        String out = new String();
        for (char ch : in.toCharArray()) {
            out += Character.isLetterOrDigit(ch) ? ch : String.format("%%%02X", (int) ch);
        }
        return out;
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


