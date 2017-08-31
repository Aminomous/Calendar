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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Day;
import models.Month;
import models.Year;

import java.io.IOException;
import java.util.ArrayList;


public class MainProgramController {

    @FXML
    private Label monthLabel = new Label();

    @FXML
    private Label yearLabel = new Label();

    @FXML
    private Button nextMonth = new Button();

    @FXML
    private Button prevMonth = new Button();

    @FXML
    private Button nextYear = new Button();

    @FXML
    private Button prevYear = new Button();

    @FXML
    private Button addButton = new Button();

    @FXML
    private Button removeButton = new Button();

    @FXML
    private Button showDetailButton = new Button();

    @FXML
    private GridPane menuPanel = new GridPane();

    @FXML
    private GridPane daysPanel = new GridPane();

    private Year currentYear;
    private ArrayList<Year> years;
    private int currentMonthNumber;
    private Month currentMonth;
    private Day[] days;
    private boolean isDaySelected = false;
    private int currentDay;

    @FXML
    protected void addAppointment(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAppointmentPanel.fxml"));

        try {
            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Add Appointment");
            stage.showAndWait();

            InputAppointmentController controller = loader.getController();
            String[] appointment = controller.getAppointment();
            helperAddAppointment(appointment);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void helperAddAppointment(String[] appointment) {
        if (!(appointment == null)) {
            if (!(appointment[0].trim().equals("") && appointment[1].trim().equals("") && appointment[2].trim().equals(""))) {
//                currentMonth.getDayByNumber(currentDay).addAppointment(appointment);
                this.days[getCurrentDay() - 1].addAppointment(appointment);
            }
        }
    }

    @FXML
    protected void removeAppointment(ActionEvent event) {
    }

    @FXML
    protected void showAppointment(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/detailPanel.fxml"));

        try {
            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Appointment list");

            ShowDetailController controller = loader.getController();
            controller.setCurrentDay(days[getCurrentDay() - 1]);

            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void changeMonthNext(ActionEvent event) {
        if (this.currentMonth.getMonthNumber() != 12) {
            setCurrentMonth(this.currentMonth.getMonthNumber() + 1);
            setDays();
        }
        isDaySelected = false;
        menuButtonVisibilityUpdate();
    }

    @FXML
    protected void changeMonthPrev(ActionEvent event) {
        if (this.currentMonth.getMonthNumber() != 1) {
            setCurrentMonth(this.currentMonth.getMonthNumber() - 1);
            setDays();
        }
        isDaySelected = false;
        menuButtonVisibilityUpdate();
    }

    @FXML
    protected void changeYearNext(ActionEvent event) {
        setCurrentYear(currentYear.getYearNumber() + 1);
        isDaySelected = false;
        menuButtonVisibilityUpdate();
    }

    @FXML
    protected void changeYearPrev(ActionEvent event) {
        setCurrentYear(currentYear.getYearNumber() - 1);
        isDaySelected = false;
        menuButtonVisibilityUpdate();
    }

    @FXML
    public void initialize() {

        years = new ArrayList<Year>();
        setCurrentYear(2017);
        setCurrentMonth(1);
        setDays();
        menuButtonVisibilityUpdate();

    }

    private void menuButtonVisibilityUpdate() {
        this.addButton.setVisible(isDaySelected);
        this.addButton.setDisable(!isDaySelected);
        this.removeButton.setVisible(isDaySelected);
        this.removeButton.setDisable(!isDaySelected);
        this.showDetailButton.setVisible(isDaySelected);
        this.showDetailButton.setDisable(!isDaySelected);
    }

    private void setCurrentYear(int number) {
        boolean isThereAYear = false;
        for (Year year : years) {
            if (year.getYearNumber() == number) {
                isThereAYear = true;
                currentYear = year;
                break;
            }
        }

        if (!isThereAYear) {
            Year year = new Year(number);
            currentYear = year;
            years.add(year);
        }
        this.yearLabel.setText(String.valueOf(currentYear.getYearNumber()));
    }

    private void setCurrentMonth(int monthNumber) {
        this.currentMonth = this.currentYear.getMonthByNumber(monthNumber);
        this.currentMonthNumber = monthNumber;
        this.monthLabel.setText(currentMonth.getName());

    }

    private void setDays() {

        this.days = this.currentMonth.getDays();
        daysPanel.getChildren().clear();
        int counter = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 7; col++) {
                if (counter < days.length) {
                    Button day = new Button(String.valueOf(days[counter].getDayNumber()));
                    day.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            isDaySelected = true;
                            menuButtonVisibilityUpdate();
                            String temp1 = event.getSource().toString();
                            String temp2 = temp1.substring(temp1.indexOf("'") + 1, temp1.length() - 1);
                            setCurrentDay(Integer.parseInt(temp2));
                        }
                    });
                    day.setPrefSize(100, 100);

                    this.daysPanel.add(day, col, row);
                    counter++;
                }
            }
        }
    }

    protected Day getcurrentDay(){
        return days[getCurrentDay() -1];
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }
}
