package controllers; /**
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
import models.Day;

import java.io.IOException;
import java.util.ArrayList;

public class ShowDetailController {

    @FXML
    private Button addButton = new Button();

    @FXML
    private Button removeButton = new Button();

    @FXML
    private Button backButton = new Button();

    @FXML
    private TextArea showArea = new TextArea();

    private Day currentDay;


    @FXML
    protected void addItem(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAppointmentPanel.fxml"));

        try {
            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Add Appointment");
            stage.showAndWait();

            AddAppointmentController controller = loader.getController();
            String[] appointment = controller.getAppointment();
            if (!(appointment == null)) {
                if (!(appointment[0].trim().equals("") && appointment[1].trim().equals("") && appointment[2].trim().equals(""))) {
//                currentMonth.getDayByNumber(currentDay).addAppointment(appointment);
                    this.currentDay.addAppointment(appointment);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void removeItem() {
    }

    @FXML
    protected void redirectPanel(){
        this.backButton.getScene().getWindow().hide();
    }
    
    protected void showItems(){
        ArrayList<Appointment> appointments = currentDay.getAppointments();
        String list = String.format("%0$-15s %0$-50s %s", "Time", "Title", "Description\n");
        for (Appointment app : appointments){
            list += app.toString()+"\n";
        }
        this.showArea.setText(list);
    }
    @FXML
    protected void initialize(){
        this.showArea.setScrollLeft(2.0);
        this.showArea.setEditable(false);
    }

    protected void setCurrentDay(Day currentDay) {

        this.currentDay = currentDay;
        showItems();
    }
}
