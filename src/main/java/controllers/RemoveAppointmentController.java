package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RemoveAppointmentController {
    @FXML
    FlowPane tempPanel = new FlowPane();

    public void initialize(){
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.println("" + calendar.get(Calendar.MONTH) + calendar.get(Calendar.YEAR));
//        calendar.
    }
}
