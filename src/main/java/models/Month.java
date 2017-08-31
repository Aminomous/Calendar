package models;

/**
 * Thanadon Pakawatthippoyom 5810405037
 */
public class Month {
	private String name;
	private Day[] days;
	private int dayNumber;
	private int monthNumber;
	public Month(String name, int day, int monthNumber) {
		this.name = name;
		this.dayNumber = day;
		this.monthNumber = monthNumber;
		this.days = new Day[day];
		for (int i = 0 ; i < day; i++){
			this.days[i] = new Day(i+1);
		}
	}
	@Override
	public String toString(){
		return this.getName();
	}

	public String getName() {
		return this.name;
	}

	public Day[] getDays() {
		return this.days;
	}

	public int getDayNumber() {
		return this.dayNumber;
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public Day getDayByNumber(int n){
		for (Day day:days){
			if (day.getDayNumber() == n){
				return day;
			}
		}
		return null;
	}

}
