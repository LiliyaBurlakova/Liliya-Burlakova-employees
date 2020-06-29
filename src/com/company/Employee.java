package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class Employee {
    int employeeId;
    int projectId;
    LocalDate startDate;
    LocalDate endDate;
    long daysWorkedOnProject;

    public Employee(){}

    public static Employee getEmployees(String fileLine) {
        String[] employeeData = fileLine.split(", ");
        Employee employeeProject = new Employee();
        employeeProject.setEmployeeId(Integer.parseInt(employeeData[0]));
        employeeProject.setProjectId(Integer.parseInt(employeeData[1]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (employeeData[2].equals("NULL")) {
            employeeProject.setStartDate(LocalDate.now());
        } else {
            employeeProject.setStartDate(LocalDate.parse(employeeData[2], formatter));
        }
        if (employeeData[3].equals("NULL")) {
            employeeProject.setEndDate(LocalDate.now());
        } else {
            employeeProject.setEndDate(LocalDate.parse(employeeData[3], formatter));
        }
        employeeProject.setDaysWorkedOnProject(calculateProjectWorkedDays(employeeProject.getStartDate(), employeeProject.getEndDate()));
        return employeeProject;
    }

    private static long calculateProjectWorkedDays(LocalDate startDate, LocalDate endDate) {
        long daysSpendOnProject = DAYS.between(startDate, endDate);
        return daysSpendOnProject;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    private void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    private void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    private void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    private void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getDaysWorkedOnProject() {
        return daysWorkedOnProject;
    }

    private void setDaysWorkedOnProject(long daysWorkedOnProject) {
        this.daysWorkedOnProject = daysWorkedOnProject;
    }

    @Override
    public String toString() {
        return "Employee: " +
                "employeeId = " + employeeId +
                ", projectId = " + projectId +
                ", startDate = " + startDate +
                ", endDate = " + endDate;
    }
}
