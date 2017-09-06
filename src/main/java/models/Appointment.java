package models;

/**
 * Thanadon Pakawatthippoyom 5810405037
 */

public class Appointment {

	private String date;
	private String time;
	private String title;
	private String desciption;

	public Appointment(String... str) {
		this.setDate(str[0]);
		this.setTime(str[1]);
		this.setTitle(str[2]);
		this.setDesciption(str[3]);

	}

	@Override
	public String toString(){
		return String.format("%0$-15s %0$-50s %s", getTime(), getTitle(), getDesciption());
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
}
