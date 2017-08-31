package models;

/**
 * Thanadon Pakawatthippoyom 5810405037
 */
public class Appointment {
	private String time;
	private String title;
	private String desciption;
	public Appointment(String[] str) {
		this.time = str[0];
		this.title = str[1];
		this.desciption = str[2];
	}

	public String toString(){
		return String.format("%0$-15s %0$-50s %s", time, title, desciption);
	}
}
