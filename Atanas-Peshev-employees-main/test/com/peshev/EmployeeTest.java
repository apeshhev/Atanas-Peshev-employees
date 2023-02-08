package com.peshev;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    @Test
    void getCommonWorkTimeNoIntersection() throws ParseException {
        Employee employee = new Employee(1,10,"2010-01-01","2015-01-01");
        Employee employee2 = new Employee(2,10,"2016-01-01","2020-01-01");

        assertEquals(0, employee.getCommonWorkTime(employee2));
    }

    @Test
    void getCommonWorkTimeWithIntersectionCase1() throws ParseException {
        Employee employee = new Employee(1,10,"2015-01-01","2018-01-01");
        Employee employee2 = new Employee(2,10,"2015-01-01","2018-01-01");

        assertEquals(94694400000L, employee.getCommonWorkTime(employee2));
    }

    @Test
    void getCommonWorkTimeWithIntersectionCase2() throws ParseException {
        Employee employee = new Employee(1,10,"2010-01-01","2015-01-01");
        Employee employee2 = new Employee(2,10,"2009-01-01","2020-01-01");

        assertEquals(157766400000L, employee.getCommonWorkTime(employee2));
    }

    @Test
    void getCommonWorkTimeWithIntersectionCase3() throws ParseException {
        Employee employee = new Employee(1,10,"2010-01-01","2017-01-01");
        Employee employee2 = new Employee(2,10,"2013-01-01","2016-01-01");

        assertEquals(94608000000L, employee.getCommonWorkTime(employee2));
    }

    @Test
    void getCommonWorkTimeWithIntersectionCase4() throws ParseException {
        Employee employee = new Employee(1,10,"2010-01-01","2015-01-01");
        Employee employee2 = new Employee(2,10,"2013-01-01","2018-01-01");

        assertEquals(63072000000L, employee.getCommonWorkTime(employee2));
    }

    @Test
    void getCommonWorkTimeWithIntersectionCase5() throws ParseException {
        Employee employee = new Employee(1,10,"2012-01-01","2013-01-01");
        Employee employee2 = new Employee(2,10,"2010-01-01","2014-01-01");

        assertEquals(31622400000L, employee.getCommonWorkTime(employee2));
    }
}