package com.io.mountblue.ipl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static final String DELIVERY_FILE_PATH= "Data/deliveries.csv";
    public static final String MATCH_FILE_PATH= "Data/matches.csv";
    public static final int MATCH_ID=0;
    public static final int MATCH_SEASON=1;
    public static final int MATCH_CITY=2;
    public static final int MATCH_DATE=3;
    public static final int MATCH_TEAM1=4;
    public static final int MATCH_TEAM2=5;
    public static final int MATCH_TOSS_WINNER=6;
    public static final int MATCH_TOSS_DECISION=7;
    public static final int MATCH_WINNER=10;
    public static final int MATCH_WIN_BY_RUNS=11;
    public static final int MATCH_VENUE=14;
    public static final int DELIVERY_ID=0;
    public static final int DELIVERY_INNING=1;
    public static final int DELIVERY_BATTING_TEAM=2;
    public static final int DELIVERY_BALLING_TEAM=3;
    public static final int DELIVERY_OVER=4;
    public static final int DELIVERY_BALL=5;
    public static final int DELIVERY_BATSMAN=6;
    public static final int DELIVERY_BOWLER_NAME=7;
    public static final int DELIVERY_WIDE_RUNS=10;
    public static final int DELIVERY_BYE_RUNS=11;
    public static final int DELIVERY_LEGBYE_RUNS=12;
    public static final int DELIVERY_NOBALL_RUNS=13;
    public static final int DELIVERY_PENALTY_RUNS=14;
    public static final int DELIVERY_EXTRA_RUNS=16;
    public static final int DELIVERY_TOTAL_RUNS=17;

    public static void main(String[] args) {
        List<Match> matches=getMatchesData();
        List<Delivery> deliveries=getDeliveriesData();

        findNumberOfMatchesPlayedPerYear(matches);
        findNumberOfMatchesWonOfAllTeamsOverAllYears(matches);
        findExtraRunsConcededPerTeamIn2016(matches,deliveries);
        findTheMostEconomicalBowlerIn2015(matches,deliveries);
        findTheTeamsWhoWinBothTossAndMatch(matches);
    }

    public static String[] customSplit(String data) {
        ArrayList<String> columns = new ArrayList<>();
        StringBuilder currentColumn = new StringBuilder();
        boolean inQuotes = false;

        for (char c : data.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                columns.add(currentColumn.toString().replace("\"", ""));
                currentColumn.setLength(0);
            } else {
                currentColumn.append(c);
            }
        }

        columns.add(currentColumn.toString().replace("\"", ""));

        return columns.toArray(new String[0]);
    }

    public static HashMap<String,Integer> findTheTeamsWhoWinBothTossAndMatch(List<Match> matches) {
        HashMap<String,Integer> numberOfteamsWinBothTossAndMatch=new HashMap<>();

        for(Match match:matches){
            String tossWinnerTeam=match.getTossWinner();
            String matchWinnerTeam=match.getWinner();

            if(tossWinnerTeam.equals(matchWinnerTeam)) {
                int numberOfMatchesPlayedYearly = numberOfteamsWinBothTossAndMatch
                        .getOrDefault(tossWinnerTeam, 0) + 1;
                numberOfteamsWinBothTossAndMatch.put(tossWinnerTeam, numberOfMatchesPlayedYearly);
            }
        }

        for(Map.Entry<String,Integer> teamWinTossAndMatch:numberOfteamsWinBothTossAndMatch.entrySet()){
            System.out.println("Teams: "+teamWinTossAndMatch.getKey());
            System.out.println("Number of Matches Won: "+teamWinTossAndMatch.getValue());
            System.out.println();
        }

        return numberOfteamsWinBothTossAndMatch;
    }

    public static HashMap<String,Double> findTheMostEconomicalBowlerIn2015(List<Match> matches, List<Delivery> deliveries) {
        HashMap<String, Integer> bowlerTotalBalls = new HashMap<>();
        HashMap<String, Integer> bowlerTotalRuns = new HashMap<>();
        HashSet<String> matchIdFor2015 = new HashSet<>();

        for (Match match : matches) {
            String matchDate = match.getSeason();

            if (matchDate.contains("2015")) {
                matchIdFor2015.add(match.getId());
            }
        }

        for (Delivery delivery : deliveries) {
            String deliveryMatchId = delivery.getId();
            String bowlerName = delivery.getBowlerName();
            int noBallsRuns = 0;
            int wideRuns = 0;
            int byeRuns = 0;
            int legByeRuns = 0;
            int penaltyRuns = 0;
            int totalRunsPerBall = 0;

            if(delivery.getNoballRuns()!=null){
                noBallsRuns=Integer.parseInt(delivery.getNoballRuns());
            }
            if(delivery.getWideRuns()!=null){
                wideRuns=Integer.parseInt(delivery.getWideRuns());
            }
            if(delivery.getByeRuns()!=null){
                wideRuns=Integer.parseInt(delivery.getByeRuns());
            }
            if(delivery.getLegbyeRuns()!=null){
                wideRuns=Integer.parseInt(delivery.getLegbyeRuns());
            }
            if(delivery.getPenaltyRuns()!=null){
                wideRuns=Integer.parseInt(delivery.getPenaltyRuns());
            }
            if(delivery.getTotalRuns()!=null){
                wideRuns=Integer.parseInt(delivery.getTotalRuns());
            }
            if (matchIdFor2015.contains(deliveryMatchId)) {
                int matchRunsConceded =totalRunsPerBall - legByeRuns - byeRuns - penaltyRuns;

                int totalRunsConceded = bowlerTotalRuns.getOrDefault(bowlerName, 0);
                totalRunsConceded += matchRunsConceded;
                bowlerTotalRuns.put(bowlerName, totalRunsConceded);

                int totalBallsBowled = bowlerTotalBalls.getOrDefault(bowlerName, 0);

                if(wideRuns==0 || noBallsRuns==0){
                    totalBallsBowled++;
                }
                bowlerTotalBalls.put(bowlerName, totalBallsBowled);
            }
        }

        HashMap<String, Double> economyRate = new HashMap<>();
        for (Map.Entry<String, Integer> entry : bowlerTotalRuns.entrySet()) {
            String bowler = entry.getKey();
            int runs = entry.getValue();
            int balls = bowlerTotalBalls.get(bowler);
            double economy = (double) (runs*6.0) / balls;
            economyRate.put(bowler, economy);
        }

        List<Map.Entry<String, Double>> entries = new ArrayList<>(economyRate.entrySet());
        entries.sort(Comparator.comparing(Map.Entry::getValue));

        System.out.println("Most economical bowler in 2015");
        for (Map.Entry<String, Double> bowlerWithRuns: entries) {
            String bowler=bowlerWithRuns.getKey();
            double economy=bowlerWithRuns.getValue();
            if(economy!=0)
            System.out.println(bowler + " " + economy);
        }

        return economyRate;
    }

    public static HashMap<String,Integer> findExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        HashMap<String,Integer> extraRunsConcededPerTeam=new HashMap<>();
        HashSet<String> matchIdFor2016=new HashSet<>();

        for(Match match:matches){
            String matchDate=match.getSeason();

            if(matchDate.contains("2016")){
                matchIdFor2016.add(match.getId());
            }
        }

        for(Delivery delivery:deliveries){
            String deliveryMatchId=delivery.getId();
            String bowlingTeam=delivery.getBallingTeam();
            int extraRuns=Integer.parseInt(delivery.getExtraRuns());

            if(matchIdFor2016.contains(deliveryMatchId)){
                extraRunsConcededPerTeam.
                        put(bowlingTeam,extraRunsConcededPerTeam.getOrDefault(bowlingTeam,0)+extraRuns);
            }
        }

        System.out.println("Extra runs conceded per team in 2016");
        for(Map.Entry<String,Integer> extraRunPerYear:extraRunsConcededPerTeam.entrySet()){
            System.out.println("Year: "+extraRunPerYear.getKey()+" "+" Matches: "+extraRunPerYear.getValue());
        }

        return extraRunsConcededPerTeam;
    }

    public static HashMap<String,Integer> findNumberOfMatchesWonOfAllTeamsOverAllYears(List<Match> matches) {
        HashMap<String,Integer> matchesWonByTeam=new HashMap<>();

        for(Match match:matches){
            String winnerTeam=match.getWinner();

            if(winnerTeam!="") {
                int numberOfMatchesWonByTeamYearly = matchesWonByTeam.getOrDefault(winnerTeam, 0) + 1;
                matchesWonByTeam.put(match.getWinner(), numberOfMatchesWonByTeamYearly);
            }
        }

        for(Map.Entry<String,Integer> matchesPerYear:matchesWonByTeam.entrySet()){
            System.out.println("Team: "+matchesPerYear.getKey());
            System.out.println("Matches Won by that team: "+matchesPerYear.getValue());
            System.out.println();
        }

        return matchesWonByTeam;
    }

    public static HashMap<String,Integer> findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        HashMap<String,Integer> matchesPlayedPerYear=new HashMap<>();

        for(Match match:matches){
            int numberOfMatchesPlayedYearly=matchesPlayedPerYear.getOrDefault(match.getSeason(),0)+1;
            matchesPlayedPerYear.put(match.getSeason(),numberOfMatchesPlayedYearly);
        }

        System.out.println("Number of Matches Played per Year");
        for(Map.Entry<String,Integer> matchesPerYear:matchesPlayedPerYear.entrySet()){
            System.out.println("Year: "+matchesPerYear.getKey()+" "+" Matches: "+matchesPerYear.getValue());
        }
        return matchesPlayedPerYear;
    }

    private static List<Delivery> getDeliveriesData() {
        List<Delivery> deliveries=new ArrayList<>();

        FileReader fileReader=null;
        BufferedReader reader=null;

        try{
            fileReader = new FileReader(DELIVERY_FILE_PATH);
            reader = new BufferedReader(fileReader);

            reader.readLine();

            String line;
            while((line=reader.readLine())!= null){
                String[] data=customSplit(line);

                Delivery delivery=new Delivery();
                delivery.setId(data[DELIVERY_ID]);
                delivery.setInning(data[DELIVERY_INNING]);
                delivery.setBattingTeam(data[DELIVERY_BATTING_TEAM]);
                delivery.setBallingTeam(data[DELIVERY_BALLING_TEAM]);
                delivery.setOver(data[DELIVERY_OVER]);
                delivery.setBall(data[DELIVERY_BALL]);
                delivery.setBatsman(data[DELIVERY_BATSMAN]);
                delivery.setBowlerName(data[DELIVERY_BOWLER_NAME]);
                delivery.setWideRuns(data[DELIVERY_WIDE_RUNS]);
                delivery.setByeRuns(data[DELIVERY_BYE_RUNS]);
                delivery.setLegbyeRuns(data[DELIVERY_LEGBYE_RUNS]);
                delivery.setNoballRuns(data[DELIVERY_NOBALL_RUNS]);
                delivery.setPenaltyRuns(data[DELIVERY_PENALTY_RUNS]);
                delivery.setExtraRuns(data[DELIVERY_EXTRA_RUNS]);
                delivery.setTotalRuns(data[DELIVERY_TOTAL_RUNS]);

                deliveries.add(delivery);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return deliveries;
    }

    private static List<Match> getMatchesData() {
        List<Match> matches=new ArrayList<>();

        FileReader fileReader=null;
        BufferedReader reader=null;

        try{
            fileReader = new FileReader(MATCH_FILE_PATH);
            reader = new BufferedReader(fileReader);

            reader.readLine();

            String line;
            while((line=reader.readLine())!= null){
                String[] data=customSplit(line);

                Match match=new Match();
                match.setId(data[MATCH_ID]);
                match.setSeason(data[MATCH_SEASON]);
                match.setCity(data[MATCH_CITY]);
                match.setDate(data[MATCH_DATE]);
                match.setTeam1(data[MATCH_TEAM1]);
                match.setTeam2(data[MATCH_TEAM2]);
                match.setTossWinner(data[MATCH_TOSS_WINNER]);
                match.setTossDecision(data[MATCH_TOSS_DECISION]);
                match.setWinner(data[MATCH_WINNER]);
                match.setWinByRuns(data[MATCH_WIN_BY_RUNS]);
                match.setVenue(data[MATCH_VENUE]);

                matches.add(match);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return matches;
    }
}