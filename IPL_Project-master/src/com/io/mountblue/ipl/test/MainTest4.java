package com.io.mountblue.ipl.test;

import com.io.mountblue.ipl.Delivery;
import com.io.mountblue.ipl.Main;
import com.io.mountblue.ipl.Match;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MainTest4 {
    @Test
    public void mostEconomicalBowlerIn2015Test(){
        Match match1 = new Match();
        match1.setId("1");
        match1.setSeason("2015");

        Match match2 = new Match();
        match2.setId("2");
        match2.setSeason("2015");

        Match match3 = new Match();
        match3.setId("3");
        match3.setSeason("2017");

        Match match4 = new Match();
        match4.setId("4");
        match4.setSeason("2015");

        Delivery delivery1 = new Delivery();
        delivery1.setId("1");
        delivery1.setBowlerName("AJ Tye");
        delivery1.setNoballRuns("0");
        delivery1.setWideRuns("0");
        delivery1.setByeRuns("1");
        delivery1.setLegbyeRuns("2");
        delivery1.setPenaltyRuns("2");
        delivery1.setTotalRuns("7");

        Delivery delivery2 = new Delivery();
        delivery2.setId("1");
        delivery2.setBowlerName("AJ Tye");
        delivery2.setNoballRuns("0");
        delivery2.setWideRuns("0");
        delivery2.setByeRuns("1");
        delivery2.setLegbyeRuns("1");
        delivery2.setPenaltyRuns("0");
        delivery2.setTotalRuns("3");

        Delivery delivery3 = new Delivery();
        delivery3.setId("2");
        delivery3.setBowlerName("Rashid Khan");
        delivery1.setNoballRuns("1");
        delivery1.setWideRuns("1");
        delivery1.setByeRuns("1");
        delivery1.setLegbyeRuns("0");
        delivery1.setPenaltyRuns("0");
        delivery1.setTotalRuns("7");

        Delivery delivery4 = new Delivery();
        delivery4.setId("2");
        delivery4.setBowlerName("Rashid Khan");
        delivery4.setNoballRuns("0");
        delivery4.setWideRuns("0");
        delivery4.setByeRuns("0");
        delivery4.setLegbyeRuns("2");
        delivery4.setPenaltyRuns("2");
        delivery4.setTotalRuns("7");

        Delivery delivery5 = new Delivery();
        delivery5.setId("3");
        delivery5.setBowlerName("TA Boult");
        delivery5.setNoballRuns("0");
        delivery5.setWideRuns("0");
        delivery5.setByeRuns("1");
        delivery5.setLegbyeRuns("1");
        delivery5.setPenaltyRuns("0");
        delivery5.setTotalRuns("3");

        Delivery delivery6 = new Delivery();
        delivery6.setId("4");
        delivery6.setBowlerName("HH Panday");
        delivery6.setNoballRuns("0");
        delivery6.setWideRuns("0");
        delivery6.setByeRuns("2");
        delivery6.setLegbyeRuns("2");
        delivery6.setPenaltyRuns("2");
        delivery6.setTotalRuns("7");

        List<Match> matches = Arrays.asList(match1, match2, match3, match4);
        List<Delivery> deliveries = Arrays.asList(
                delivery1, delivery2, delivery3, delivery4, delivery5,
                delivery6
        );

        HashMap<String, Double> result = Main.findTheMostEconomicalBowlerIn2015(matches,deliveries);

        assertEquals(0.0, (double) result.get("AJ Tye"),0.01);
        assertEquals(0.0,(double) result.get("Rashid Khan"),0.01);
        assertNull(result.get("TA Boult"));
        assertEquals(0.0,(double) result.get("HH Panday"),0.01);
    }
}
