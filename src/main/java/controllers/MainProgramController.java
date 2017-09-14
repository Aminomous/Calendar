package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Appointment;
import databaseConnector.AppointmentService;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainProgramController {

    @FXML
    private Label dateSelectStatusLabel;

    @FXML
    private Label monthLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button showDetailButton;

    @FXML
    private GridPane daysPanel;

    private boolean isDaySelected = false;
    private int currentDay, currentMonth, currentYear; //currentMonth is base 0
    private AppointmentService service;
    private ArrayList<Appointment> appointments;
    private GregorianCalendar calendar;
    private String selectedDate;


    @FXML
    protected void changeMonth(ActionEvent event) {
        Button temp = (Button) event.getSource();
        if (temp.getText().equals(">") && this.currentMonth != 11) {
            setCurrentMonth(this.currentMonth + 1);
            initDays();

        } else if (temp.getText().equals("<") && this.currentMonth != 0) {
            setCurrentMonth(this.currentMonth - 1);
            initDays();
        }

        isDaySelected = false;
        menuUpdate();
    }

    @FXML
    protected void changeYear(ActionEvent event) {
        Button temp = (Button) event.getSource();
        if (temp.getText().equals(">")) {
            setCurrentYear(this.currentYear + 1);
        } else {
            setCurrentYear(this.currentYear - 1);
        }

        setCurrentMonth(this.currentMonth);
        isDaySelected = false;
        menuUpdate();
    }

    @FXML
    protected void addAppointment(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAppointmentPanel.fxml"));

        try {

            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Add Appointment");

            InputAppointmentController controller = loader.getController();
            if (isDaySelected) {
                controller.setCurrentDate(selectedDate);
            } else {
                controller.setCurrentDate("EMPTY");
            }
            controller.setTitleName("Add Appointment");
            stage.showAndWait();

            setAppointments(service.getAllAppointment());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void removeAppointment(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        if (isDaySelected) {
            alert.setHeaderText("Remove all appointments in " + selectedDate);
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.removeAppointmentByDate(selectedDate);
            }

        } else {
            alert.setHeaderText("Remove all appointment in this program");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.removeAllAppointment();
            }

        }
        appointments = service.getAllAppointment();


    }

    @FXML
    protected void showAppointment(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/detailPanel.fxml"));

        try {

            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Appointment list");

            ShowAppointmentController controller = loader.getController();
            if (!isDaySelected) {
                controller.setCurrentDate("EMPTY");
                controller.setAppointments(appointments);

            } else {
                controller.setCurrentDate(selectedDate);
                controller.setAppointments(filterAppointmentsByDate(selectedDate));
            }

            controller.showItems(isDaySelected?"NOTSHOWDATE":"SHOWDATE");

            stage.showAndWait();

            appointments = service.getAllAppointment();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Appointment> filterAppointmentsByDate(String filter) {
        ArrayList<Appointment> filtered = new ArrayList<Appointment>();

        for (Appointment app : appointments) {
            if (app.getDate().equals(filter)) {

                filtered.add(app);
            }
        }
        return filtered;
    }

    private void initDays() {
        daysPanel.getChildren().clear();
        int dayCounter = 1;
        SimpleDateFormat sim = new SimpleDateFormat("u");
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        boolean firstWeek = true;

        for (int row = 0; row < 6; row++) {
            for (int col = firstWeek ? Integer.valueOf(sim.format(calendar.getTime())) % 7 : 0; col < 7; col++) {
                if (dayCounter <= calendar.getActualMaximum(Calendar.DATE)) {
                    Button day = new Button();
                    day.setText(String.valueOf(dayCounter));
                    day.setFont(Font.font(18));
                    day.setPrefSize(80, 80);
                    day.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            isDaySelected = true;

                            Button temp1 = (Button) event.getSource();
                            String temp2 = temp1.getText();

                            SimpleDateFormat spdf = new SimpleDateFormat("yyyy/MMMM/dd");
                            calendar.set(Calendar.DATE, Integer.parseInt(temp2));
                            setSelectedDate(spdf.format(calendar.getTime()));
                            resetCalendarData();
                            menuUpdate();
                        }
                    });

                    daysPanel.add(day, col, row);
                    dayCounter++;
                }
            }
            firstWeek = false;
        }
        resetCalendarData();
    }

    private void menuUpdate() {

        if (isDaySelected) {
            dateSelectStatusLabel.setText(selectedDate);
            removeButton.setText("REMOVE");
            showDetailButton.setText("SHOW");
        } else {
            dateSelectStatusLabel.setText("---");
            removeButton.setText("REMOVE ALL");
            showDetailButton.setText("SHOW ALL");

            setSelectedDate("EMPTY");
        }
    }

    @FXML
    public void initialize() {
        appointments = new ArrayList<Appointment>();
        service = new AppointmentService();
        calendar = new GregorianCalendar();
        setCurrentYear(calendar.get(Calendar.YEAR));
        setCurrentMonth(calendar.get(Calendar.MONTH));
        setCurrentDay(calendar.get(Calendar.DATE));

        initDays();
        menuUpdate();
        setAppointments(service.getAllAppointment());

        daysPanel.setStyle("-fx-background-color:black;");
    }

    private void setCurrentYear(int year) {
        currentYear = year;
        calendar.set(Calendar.YEAR, year);
        yearLabel.setText("" + currentYear);

    }

    private void setCurrentMonth(int month) {
        currentMonth = month;
        calendar.set(Calendar.MONTH, month);
        monthLabel.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));

    }

    private void setCurrentDay(int day) {
        currentDay = day;
    }

    private void setSelectedDate(String date) {
        selectedDate = date;
    }

    private void setAppointments(ArrayList<Appointment> app) {
        appointments = app;
    }

    private void resetCalendarData() {
        calendar.set(currentYear, currentMonth, currentDay);
    }

    public String getSelectedDate() {
        return selectedDate;
    }

}
