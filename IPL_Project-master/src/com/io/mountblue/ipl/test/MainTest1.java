package com.io.mountblue.ipl.test;

import com.io.mountblue.ipl.Main;
import com.io.mountblue.ipl.Match;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MainTest1 {

    @Test
    public void numberOfMatchesPlayedPerYearTest(){
        Match match1=new Match();
        match1.setSeason("2018");

        Match match2=new Match();
        match2.setSeason("2018");

        Match match3=new Match();
        match3.setSeason("2019");

        Match match4=new Match();
        match4.setSeason("2019");

        Match match5=new Match();
        match5.setSeason("2020");

        List<Match> matches= Arrays.asList(match1,match2,match3,match4,match5);

        HashMap<String,Integer> result= Main.findNumberOfMatchesPlayedPerYear(matches);

        assertEquals(result,Main.findNumberOfMatchesPlayedPerYear(matches));
        assertEquals(2,(int)result.get("2018"));
        assertEquals(2,(int)result.get("2019"));
        assertEquals(1,(int)result.get("2020"));
        assertNull(result.get("2023"));
    }

}