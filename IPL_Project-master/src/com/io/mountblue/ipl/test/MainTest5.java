package com.io.mountblue.ipl.test;

import com.io.mountblue.ipl.Main;
import com.io.mountblue.ipl.Match;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MainTest5 {
    @Test
    public void teamsWhoWinBothTossAndMatchTest(){
        Match match1 = new Match();
        match1.setId("1");
        match1.setTossWinner("Mumbai Indians");
        match1.setWinner("Mumbai Indians");

        Match match2 = new Match();
        match2.setId("2");
        match2.setTossWinner("Mumbai Indians");
        match2.setWinner("Mumbai Indians");

        Match match3 = new Match();
        match3.setId("3");
        match3.setTossWinner("Mumbai Indians");
        match3.setWinner("Chennai Super Kings");

        Match match4 = new Match();
        match4.setId("4");
        match4.setTossWinner("Chennai Super Kings");
        match4.setWinner("Chennai Super Kings");

        Match match5 = new Match();
        match5.setId("5");
        match5.setTossWinner("Chennai Super Kings");
        match5.setWinner("Chennai Super Kings");

        Match match6 = new Match();
        match6.setId("6");
        match6.setTossWinner("Chennai Super Kings");
        match6.setWinner("Delhi Daredevils");

        List<Match> matches = Arrays.asList(match1, match2, match3, match4, match5, match6);
        HashMap<String, Integer> result = Main.findTheTeamsWhoWinBothTossAndMatch(matches);

        assertEquals(2, (int) result.get("Mumbai Indians"));
        assertEquals(2, (int) result.get("Chennai Super Kings"));
        assertNull(result.get("Delhi Daredevils"));
        assertNull(result.get("Rajasthan Royals"));
    }
}
