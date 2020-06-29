package com.company;

import com.company.exceptions.UnexceptedFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static com.company.constants.Constants.FILE_MESSAGE;
import static com.company.constants.Constants.WELCOME_MESSAGE;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(WELCOME_MESSAGE);
        System.out.println(FILE_MESSAGE);
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        try {
            FileValidator.checkIfFileIsEmpty(file);
            FileValidator.checkIfFileIsEmpty(file);
        } catch (UnexceptedFileException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<Employee> list = FileReader.convertFileToListOfItems(filePath);

            Comparator<Employee> comparator = Comparator.comparing(employee -> employee.projectId);
            comparator = comparator.thenComparing((employee -> employee.daysWorkedOnProject)).reversed();

            list = list.stream().sorted(comparator).collect(Collectors.toList());

            LinkedHashMap<Pair, Long> map = PairFinder.getMapPair(list);

            LinkedHashMap<Pair, Long> resultList = PairFinder.getPairsWorkingOnMoreThanOneProjectTogether(map);

            getPairWorkedOnMoreThanOneProject(list, map, resultList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void getPairWorkedOnMoreThanOneProject(List<Employee> list, LinkedHashMap<Pair, Long> map, LinkedHashMap<Pair, Long> resultList) {
        if (resultList.size() != 0) {
            Map.Entry<Pair, Long> maxEntry = null;
            for (Map.Entry<Pair, Long> entry : map.entrySet())
            {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    maxEntry = entry;
                }
            }
            Pair bestPair = maxEntry.getKey();

            List<Employee> finalPair = getUniqueEmployeeEntries(list, bestPair);

            printPair(finalPair);
        }
    }

    private static List<Employee> getUniqueEmployeeEntries(List<Employee> list, Pair finalParir) {
        List<Employee> finalPair = new ArrayList<>();
        for (Employee employeeProject: list) {
            if (employeeProject.getEmployeeId() == finalParir.getFirstId() || employeeProject.getEmployeeId() == finalParir.getSecondId()) {
                boolean exist = false;
                for (Employee em: finalPair) {
                    if (em.getEmployeeId() == employeeProject.getEmployeeId()) {
                        exist = true;
                    }
                }
                if (!exist) {
                    finalPair.add(employeeProject);
                }
            }
        }
        return finalPair;
    }

    private static void printPair(List<Employee> finalPair) {
        for (Employee employee: finalPair) {
            System.out.println(employee);
        }
    }

}
