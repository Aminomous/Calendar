package models;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import java.sql.*;
import java.util.ArrayList;

public class AppointmentService {
    private String dbUrl = "jdbc:sqlite:AppointmentDatabase.db";
    private int latestId;

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
                        result.getInt(1),
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
                        result.getInt(1),
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

    public void addAppointment(Appointment arg) {
        String query = "insert into Appointment(date, time, title, description) values(?, ?, ?, ?)";
        try {
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

    public void removeAppointmentByDate(String date) {
        System.out.println(date);
        String query = "delete from Appointment where date=" + "\"" + date + "\"";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(dbUrl);

            Statement statement = conn.createStatement();
            statement.execute(query);

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeAllAppointment() {
        String query = "delete from Appointment";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(dbUrl);

            Statement statement = conn.createStatement();
            statement.execute(query);

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAppointment(Appointment app){
        String query = "update Appointment set time=?, title=?, description=? where id=?";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(dbUrl);

            PreparedStatement p = conn.prepareStatement(query);
            p.setString(1, app.getTime());
            p.setString(2, app.getTitle());
            p.setString(3, app.getDesciption());
            p.setInt(4, app.getId());
            p.executeUpdate();

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLatestId() {

        String query = "select * from sqlite_sequence";
        try {

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next())
                latestId = result.getInt(2);
            conn.close();
            return latestId ;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
