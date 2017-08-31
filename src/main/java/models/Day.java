package models; /**
 * Thanadon Pakawatthippoyom 5810405037
 */
import java.util.ArrayList;

public class Day {
	private ArrayList<Appointment> appointments;
	private int dayNumber;
	public Day(int number) {
		this.dayNumber = number;
		this.appointments = new ArrayList<Appointment>();
	}
	public void addAppointment(String[] str){
		getAppointments().add(new Appointment(str));
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
}
