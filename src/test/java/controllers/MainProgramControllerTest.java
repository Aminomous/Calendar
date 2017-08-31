package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */
import models.Day;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainProgramControllerTest {
    @Test
    public void createAnAppointmentTest(){
        Day day1 = new Day(1);
        String[] temp1 = new String[3];
        temp1[0] = "test1";
        temp1[1] = "test1";
        temp1[2] = "test1";

        day1.addAppointment(temp1);

        MainProgramController controller2 = new MainProgramController();

        controller2.initialize();
        controller2.setCurrentDay(1);
        controller2.helperAddAppointment(temp1);
        Day day2 = controller2.getcurrentDay();

        assertEquals(day1, day2);



    }

}