package com.peshev;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Employee e1 = new Employee(143, 12, "2013-11-01", "NULL");
//        Employee e2 = new Employee(218, 10, "2012-05-16", "NULL");
//        Employee e3 = new Employee(143, 10, "2009-01-01", "2011-04-27");
//        Employee e4 = new Employee(155, 15, "2013-07-02", "2016-11-23");
//        Employee e5 = new Employee(155, 10, "2010-05-20", "2018-01-01");
//        Employee e6 = new Employee(160,12,"2005-02-01","2020-05-22");
//
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(e1);
//        employeeList.add(e2);
//        employeeList.add(e3);
//        employeeList.add(e4);
//        employeeList.add(e5);
//        employeeList.add(e6);
//
//        employeeList.sort(Comparator.comparing(Employee::getProjectID));
//
//        for (Employee e : employeeList) {
//            System.out.println(e);
//        }
//
//        System.out.println();
//
//        findLongestWorkingPair(employeeList);

        EmployeeList employeeList = new EmployeeList("EmployeeRepo");
        System.out.println(employeeList.findLongestWorkingPair());
    }
}
