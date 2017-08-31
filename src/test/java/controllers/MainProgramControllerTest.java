package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */
import models.Day;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainProgramControllerTest {

    private static Day day1;
    private static Day day2;
    @BeforeClass
    public static void setUp(){
        day1 = new Day(1);
        String[] temp1 = new String[3];
        temp1[0] = "test1";
        temp1[1] = "test1";
        temp1[2] = "test1";

        day1.addAppointment(temp1);

        MainProgramController controller2 = new MainProgramController();

        controller2.initialize();
        controller2.setCurrentDay(1);
        controller2.helperAddAppointment(temp1);
        day2 = controller2.getcurrentDay();
    }

    @Test
    public void createAnAppointmentTest(){


        assertEquals(day1, day2);



    }

}