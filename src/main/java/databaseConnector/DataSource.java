package databaseConnector;

import models.Appointment;

import java.util.ArrayList;

public interface DataSource {
    ArrayList<Appointment> getAllAppointment();
    void addAppointment(Appointment app);
    void updateAppointment(Appointment app);
    void removeAppointment(int removeType, Object app);
    int getLatestId();

}
