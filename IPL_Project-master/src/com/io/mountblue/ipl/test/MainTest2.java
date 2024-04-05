package com.io.mountblue.ipl.test;

import com.io.mountblue.ipl.Main;
import com.io.mountblue.ipl.Match;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest2 {

    @Test
    public void numberOfMatchesWonOfAllTeamsOverAllYearsTest(){
        Match match1=new Match();
        match1.setWinner("Mumbai Indians");

        Match match2=new Match();
        match2.setWinner("Delhi Daredevils");

        Match match3=new Match();
        match3.setWinner("Chennai Super Kings");

        Match match4=new Match();
        match4.setWinner("Mumbai Indians");

        Match match5=new Match();
        match5.setWinner("Chennai Super Kings");

        Match match6=new Match();
        match6.setWinner("Mumbai Indians");

        List<Match> matches= Arrays.asList(match1,match2,match3,match4,match5,match6);

        HashMap<String,Integer> result=Main.findNumberOfMatchesWonOfAllTeamsOverAllYears(matches);

        assertEquals(3,result.get("Mumbai Indians"));
        assertEquals(2,result.get("Chennai Super Kings"));
        assertEquals(1,result.get("Delhi Daredevils"));
        assertNull(result.get("Kolkata Knight Riders"));
    }
}