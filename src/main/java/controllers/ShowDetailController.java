package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentService;

import java.io.IOException;
import java.util.ArrayList;

public class ShowDetailController {

    @FXML
    private Button addButton = new Button();

    @FXML
    private Button editButton = new Button();

    @FXML
    private Button backButton = new Button();

    @FXML
    private TextArea showArea = new TextArea();

    private String currentdate;
    private AppointmentService service;
    private ArrayList<Appointment> appointments;


    @FXML
    protected void addItem(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAppointmentPanel.fxml"));

        try {
            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Add Appointment");

            InputAppointmentController controller = loader.getController();
            controller.setCurrentDate(currentdate);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void editItem() {
    }

    @FXML
    protected void redirectPanel(){
        this.backButton.getScene().getWindow().hide();
    }
    
    protected void showItems(){

        String list = String.format("%0$-15s %0$-50s %s", "Time", "Title", "Description\n");

        for (Appointment record:appointments){
            list += String.format(record.toString()+"\n");
        }
        this.showArea.setText(list);
    }
    @FXML
    protected void initialize(){
        this.showArea.setScrollLeft(2.0);
        this.showArea.setScrollTop(2.0);
        this.showArea.setEditable(false);
//        showItems();
    }

    public void setCurrentDate(String currentdate) {
        this.currentdate = currentdate;
    }
    public void setAppointments(ArrayList<Appointment> app){
        appointments = app;
    }
}
