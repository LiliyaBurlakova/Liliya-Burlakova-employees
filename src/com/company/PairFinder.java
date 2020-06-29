package com.company;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class PairFinder {

    public static LinkedHashMap<Pair, Long> getMapPair(List<Employee> employeeList) {
        LinkedHashMap<Pair, Long> pairsMap = new LinkedHashMap<>();
        for (int i = 0; i < employeeList.size(); i++) {
            for (int j = i + 1; j < employeeList.size(); j++) {
                if (employeeList.get(i).getProjectId() == employeeList.get(j).getProjectId()) {
                    long days = getOverlappingDays(employeeList.get(i).getStartDate(), employeeList.get(i).getEndDate(),
                            employeeList.get(j).getStartDate(), employeeList.get(j).getEndDate());
                    Pair pair = new Pair();
                    pair.firstId = employeeList.get(i).employeeId;
                    pair.secondId = employeeList.get(j).employeeId;
                    pairsMap.put(pair, days);
                } else {
                    break;
                }
            }
        }
        return pairsMap;
    }

    private static long getOverlappingDays(LocalDate firstStart, LocalDate firstEnd, LocalDate secondStart, LocalDate secondEnd)
    {
        LocalDate start;
        LocalDate end;
        if (firstStart.isAfter(secondStart)) {
            start = firstStart;
        } else {
            start = secondStart;
        }

        if (firstEnd.isAfter(secondEnd)) {
            end = secondEnd;
        } else {
            end = firstEnd;
        }
        return DAYS.between(start, end);
    }

    public static LinkedHashMap<Pair, Long> getPairsWorkingOnMoreThanOneProjectTogether(LinkedHashMap<Pair, Long> map) {
        List<Pair> listOfKeys = new ArrayList<Pair>(map.keySet());
        LinkedHashMap<Pair, Long> resultList = new LinkedHashMap<>();
        for (int i = 0; i < listOfKeys.size(); i++) {
            long resultDays = map.get(listOfKeys.get(i));
            boolean moreThanOnce = false;
            if (map.get(listOfKeys.get(i)) < 0) {
                continue;
            }
            for (int j = i + 1; j < listOfKeys.size(); j++) {
                if (existPair(listOfKeys.get(i), listOfKeys.get(j))) {
                    resultDays += map.get(listOfKeys.get(j));
                    moreThanOnce = true;
                }
            }
            if (moreThanOnce) {
                resultList.put(listOfKeys.get(i), resultDays);
                moreThanOnce = false;
            }
        }
        return resultList;
    }

    private static boolean existPair(Pair first, Pair second) {
        boolean existPair = false;
        if (first.firstId == second.firstId || first.firstId == second.secondId) {
            existPair = true;
        } else {
            return existPair;
        }
        if (first.secondId == second.secondId || first.secondId == second.firstId) {
            existPair = true;
        } else {
            existPair = false;
        }
        return existPair;
    }


}
