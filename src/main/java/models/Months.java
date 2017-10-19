package models;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Months {

//    public static ArrayList<String> months;
    public static HashMap<Integer, String> monthsKeyInt;
    public static HashMap<String, Integer> monthsKeyString;
//
//    static {
//        months = new ArrayList<String>();
//        months.add("January");
//        months.add("February");
//        months.add("March");
//        months.add("April");
//        months.add("May");
//        months.add("June");
//        months.add("July");
//        months.add("August");
//        months.add("September");
//        months.add("October");
//        months.add("November");
//        months.add("December");
//    }

    static {
        monthsKeyInt = new HashMap<Integer, String>();
        monthsKeyString = new HashMap<String, Integer>();

        monthsKeyInt.put(1, "January");
        monthsKeyInt.put(2, "February");
        monthsKeyInt.put(3, "March");
        monthsKeyInt.put(4, "April");
        monthsKeyInt.put(5, "May");
        monthsKeyInt.put(6, "June");
        monthsKeyInt.put(7, "July");
        monthsKeyInt.put(8, "August");
        monthsKeyInt.put(9, "September");
        monthsKeyInt.put(10, "October");
        monthsKeyInt.put(11, "November");
        monthsKeyInt.put(12, "December");

        monthsKeyString.put("January", 1);
        monthsKeyString.put("February", 2);
        monthsKeyString.put("March", 3);
        monthsKeyString.put("April", 4);
        monthsKeyString.put("May", 5);
        monthsKeyString.put("June", 6);
        monthsKeyString.put("July", 7);
        monthsKeyString.put("August", 8);
        monthsKeyString.put("September", 9);
        monthsKeyString.put("October", 10);
        monthsKeyString.put("November", 11);
        monthsKeyString.put("December", 12);
    }
}
