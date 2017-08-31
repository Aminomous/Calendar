package models;

/**
 * Thanadon Pakawatthippoyom 5810405037
 */
public class Year {
    private int yearNumber;
    private Month[] months;

    public Year(int number) {

        this.yearNumber = number;
        this.months = new Month[12];
        this.generateMonth();

    }

    private void generateMonth() {
        String[] monthHas31Day = {};

        for (int month = 1; month <= 12; month++) {
            String monthString;

            switch (month) {
                case 1:
                    monthString = "January";
                    break;
                case 2:
                    monthString = "February";
                    break;
                case 3:
                    monthString = "March";
                    break;
                case 4:
                    monthString = "April";
                    break;
                case 5:
                    monthString = "May";
                    break;
                case 6:
                    monthString = "June";
                    break;
                case 7:
                    monthString = "July";
                    break;
                case 8:
                    monthString = "August";
                    break;
                case 9:
                    monthString = "September";
                    break;
                case 10:
                    monthString = "October";
                    break;
                case 11:
                    monthString = "November";
                    break;
                case 12:
                    monthString = "December";
                    break;
                default:
                    monthString = "Invalid month";
                    break;
            }
            int day;
            if (month == 2) {
                day = 28;
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                day = 31;
            }else{
                day = 30;
            }
            months[month-1] = new Month(monthString, day, month);
        }
    }
    public void getAllMonthName(){
        for (int i = 0 ; i < 12 ; i++){
            System.out.println(months[i]);
        }
    }

    public Month getMonthByName(String name){
        for (Month month : months){
            if (month.getName().equals(name)){
                return month;
            }
        }
        return null;
    }

    public Month getMonthByNumber(int number){
        return months[number-1];
    }

    public int getYearNumber() {
        return yearNumber;
    }
}
