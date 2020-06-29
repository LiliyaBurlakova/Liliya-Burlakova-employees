package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Employee> convertFileToListOfItems(String filePath) throws FileNotFoundException {
        List<Employee> listOfEmployees = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filePath));

        try {
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                Employee employeeProject = Employee.getEmployees(fileLine);
                listOfEmployees.add(employeeProject);
                fileLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfEmployees;
    }
}
