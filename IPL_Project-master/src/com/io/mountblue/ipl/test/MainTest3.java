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

public class MainTest3 {
    @Test
    public void extraRunsConcededPerTeamIn2016Test(){
        Match match1=new Match();
        match1.setId("1");
        match1.setSeason("2016");

        Match match2=new Match();
        match2.setId("2");
        match2.setSeason("2016");

        Match match3=new Match();
        match3.setId("3");
        match3.setSeason("2017");

        Match match4=new Match();
        match4.setId("4");
        match4.setSeason("2016");

        Delivery delivery1=new Delivery();
        delivery1.setId("1");
        delivery1.setBallingTeam("Mumbai Indians");
        delivery1.setExtraRuns("3");

        Delivery delivery2=new Delivery();
        delivery2.setId("1");
        delivery2.setBallingTeam("Mumbai Indians");
        delivery2.setExtraRuns("2");

        Delivery delivery3=new Delivery();
        delivery3.setId("1");
        delivery3.setBallingTeam("Mumbai Indians");
        delivery3.setExtraRuns("4");

        Delivery delivery4=new Delivery();
        delivery4.setId("2");
        delivery4.setBallingTeam("Delhi Daredevils");
        delivery4.setExtraRuns("3");

        Delivery delivery5=new Delivery();
        delivery5.setId("2");
        delivery5.setBallingTeam("Delhi Daredevils");
        delivery5.setExtraRuns("1");

        Delivery delivery6=new Delivery();
        delivery6.setId("2");
        delivery6.setBallingTeam("Delhi Daredevils");
        delivery6.setExtraRuns("0");

        Delivery delivery7=new Delivery();
        delivery7.setId("3");
        delivery7.setBallingTeam("Chennai Super Kings");
        delivery7.setExtraRuns("3");

        Delivery delivery8=new Delivery();
        delivery8.setId("3");
        delivery8.setBallingTeam("Chennai Super Kings");
        delivery8.setExtraRuns("3");

        Delivery delivery9=new Delivery();
        delivery9.setId("4");
        delivery9.setBallingTeam("Mumbai Indians");
        delivery9.setExtraRuns("2");

        Delivery delivery10=new Delivery();
        delivery10.setId("4");
        delivery10.setBallingTeam("Mumbai Indians");
        delivery10.setExtraRuns("1");

        List<Match> matches= Arrays.asList(match1,match2,match3,match4);
        List<Delivery> deliveries=Arrays.
                asList(delivery1,delivery2,delivery3,delivery4,delivery5,
                        delivery6,delivery7,delivery8,delivery9,delivery10);

        HashMap<String,Integer> result= Main.findExtraRunsConcededPerTeamIn2016(matches,deliveries);

        assertEquals(12,(int)result.get("Mumbai Indians"));
        assertEquals(4,(int)result.get("Delhi Daredevils"));
        assertNull(result.get("Chennai Super Kings"));
        assertNull(result.get("Rajasthan Royals"));
    }
}
