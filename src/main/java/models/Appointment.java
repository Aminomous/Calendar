package models;

/**
 * Thanadon Pakawatthippoyom 5810405037
 */

public class Appointment {

	private int id;
	private String date;
	private String time;
	private String title;
	private String desciption;
	private String repeatOption;
	private int repeatId;

	public Appointment(int id, String... str) {
		this.id = id;
		this.date = str[0];
		this.time = str[1];
		this.title = str[2];
		this.desciption = str[3];
		this.repeatOption = str[4];
		this.repeatId = Integer.parseInt(str[5]);
	}

	public String getDetailWithoutDate(){
		return String.format("%0$-15s %0$-50s %s", getTime(), getTitle(), getDesciption());
	}

	public String getDetailWithDate(){
		return String.format("%-20s %0$-15s %0$-50s %s", getDate(), getTime(), getTitle(), getDesciption());
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

	public int getId() {
		return id;
	}

	public String getRepeatOption() {
		return repeatOption;
	}

	public void setRepeatOption(String repeatOption) {
		this.repeatOption = repeatOption;
	}

	public int getRepeatId(){
	    return repeatId;
    }
}
