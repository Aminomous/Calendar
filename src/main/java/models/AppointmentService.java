package models;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */
import java.sql.*;
import java.util.ArrayList;

public class AppointmentService {
    private String dbUrl = "jdbc:sqlite:AppointmentDatabase.db";

    public ArrayList<Appointment> getAllAppointment() {
        try {
            Class.forName("org.sqlite.JDBC");
            String query = "select * from Appointment";
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Appointment> list = new ArrayList<Appointment>();

            while (result.next()) {
                Appointment temp = new Appointment(
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5)
                );
                list.add(temp);
            }
            conn.close();

            return list;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Appointment> getAppointmentByFilter(String filterField, String filterValue) {
        String query = "select * from Appointment where " + filterField + " = " + filterValue;
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(dbUrl);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Appointment> list = new ArrayList<Appointment>();

            while (result.next()) {
                Appointment temp = new Appointment(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)
                );
                list.add(temp);
            }
            conn.close();

            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void addAppointment(Appointment arg){
        String query = "insert into Appointment(date, time, title, description) values(?, ?, ?, ?)";
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(this.dbUrl);
            PreparedStatement p = conn.prepareStatement(query);

            p.setString(1, arg.getDate());
            p.setString(2, arg.getTime());
            p.setString(3, arg.getTitle());
            p.setString(4, arg.getDesciption());
            p.executeUpdate();

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
