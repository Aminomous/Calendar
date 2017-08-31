package controllers; /**
 * Thanadon Pakawatthippoyom 5810405037
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddAppointmentController {

    @FXML
    private TextField timeInput = new TextField();

    @FXML
    private TextField titleInput = new TextField();

    @FXML
    private TextArea descriptionInput = new TextArea();

    @FXML
    private Button okButton = new Button();

    @FXML
    private Button cancelButton = new Button();

    private String[] appointment;

    @FXML
    protected void initialize(){
        this.descriptionInput.setScrollLeft(2);
        this.descriptionInput.setScrollTop(2);

    }

    @FXML
    private void createAppointment() {
        appointment = new String[3];
        appointment[0] = this.timeInput.getText();
        appointment[1] = this.titleInput.getText();
        appointment[2] = this.descriptionInput.getText();
        redirectPanel();

    }

    @FXML
    private void redirectPanel() {
        okButton.getScene().getWindow().hide();

    }

    protected String[] getAppointment() {

        return appointment;
    }

}
