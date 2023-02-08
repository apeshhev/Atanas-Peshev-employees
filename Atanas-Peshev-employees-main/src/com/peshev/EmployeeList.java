package com.peshev;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;

public class EmployeeList {

    private final List<Employee> employeeList;
    private final Map<EmployeePair, Long> workedTimeTogether = new HashMap<>();

    public EmployeeList(String path) {
        employeeList = new ArrayList<>();
        Path usersFilePath = Path.of(path);

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
        employeeList.sort(Comparator.comparing(Employee::getProjectID).thenComparing(Employee::getEmpID));
    }

    public EmployeeList() {
        this("EmployeeRepo");
    }

    public EmployeePair findLongestWorkingPair() {
        int currProject;
        int nextProject;
        int currID;
        int nextID;
        long maxTime = 0;

        for (int i = 0; i < employeeList.size() - 1; i++) {
            Employee currEmp = employeeList.get(i);
            currProject = currEmp.getProjectID();
            currID = currEmp.getEmpID();
            for (int j = i + 1; ; j++) {
                if (j == employeeList.size()) {
                    break;
                }
                Employee nextEmp = employeeList.get(j);
                nextProject = nextEmp.getProjectID();
                nextID = nextEmp.getProjectID();
                if (currProject == nextProject && currID != nextID) {
                    long commonWorkTime = currEmp.getCommonWorkTime(nextEmp);
                    EmployeePair currPair = new EmployeePair(currEmp, nextEmp);

                    if (!workedTimeTogether.containsKey(currPair)) {
                        workedTimeTogether.put(currPair, commonWorkTime);
                    } else {
                        workedTimeTogether.put(currPair, workedTimeTogether.get(currPair) + commonWorkTime);
                    }
                } else {
                    break;
                }
            }
        }

        System.out.println("List with all employees: ");
        for (Employee e : employeeList) {
            System.out.println(e);
        }
        System.out.println();

        EmployeePair result = null;
        for (EmployeePair employeePair : workedTimeTogether.keySet()) {
            long currentPairTime = workedTimeTogether.get(employeePair);

            if (currentPairTime > maxTime) {
                result = employeePair;
                maxTime = currentPairTime;
            }
        }

        System.out.println("Employee pair with max time together: " + (result != null ? result.emp1().getEmpID() : 0) + " <-> " + (result != null ? result.emp2().getEmpID() : 0));
        return result;
    }
}