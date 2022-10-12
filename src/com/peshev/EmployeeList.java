package com.peshev;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeList {

    private List<Employee> employeeList;

    public EmployeeList(String fileName) {
        employeeList = new ArrayList<>();
        Path usersFilePath = Path.of(fileName);
        if (Files.exists(usersFilePath)) {
            try (BufferedReader br = Files.newBufferedReader(usersFilePath)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parameters = line.split(", ");
                    int employeeID = Integer.parseInt(parameters[0]);
                    int projectID = Integer.parseInt(parameters[1]);
                    String dateFrom = parameters[2];
                    String dateTo = parameters[3];

                    employeeList.add(new Employee(employeeID, projectID, dateFrom, dateTo));
                }
            } catch (IOException e) {
                throw new RuntimeException("File error!", e);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format!");
            }
        }
        employeeList.sort(Comparator.comparing(Employee::getProjectID));
    }

    public boolean findLongestWorkingPair() {
        int currProject = 0;
        int nextProject = 0;
        int maxID1 = 0;
        int maxID2 = 0;
        long maxTime = 0;

        for (int i = 0; i < employeeList.size() - 1; i++) {
            Employee currEmp = employeeList.get(i);
            currProject = currEmp.getProjectID();
            for (int j = i + 1; ; j++) {
                if (j == employeeList.size()) {
                    break;
                }
                Employee nextEmp = employeeList.get(j);
                nextProject = nextEmp.getProjectID();
                if (currProject == nextProject) {
                    long commonWorkTime = currEmp.getCommonWorkTime(nextEmp);
                    if(commonWorkTime > maxTime) {
                        maxTime = commonWorkTime;
                        maxID1 = currEmp.getEmpID();
                        maxID2 = nextEmp.getEmpID();
                    }
                } else {
                    break;
                }
            }
        }

        System.out.println("List with all employees: ");
        for(Employee e : employeeList) {
            System.out.println(e);
        }

        System.out.println();
        System.out.println("Employee pair with max time together: " + maxID1 + " <-> " + maxID2);
        return true;
    }
}
