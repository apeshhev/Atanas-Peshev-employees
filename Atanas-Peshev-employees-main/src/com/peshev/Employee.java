package com.peshev;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Employee {
    private final int empID;
    private final int projectID;
    private final Date dateFrom;
    private final Date dateTo;


    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Employee(int empID, int projectID, String dateFrom, String dateTo) throws ParseException {
        this.empID = empID;
        this.projectID = projectID;
        this.dateFrom = formatter.parse(dateFrom);
        this.dateTo = dateTo.equals("NULL") ? new Date() : formatter.parse(dateTo);
    }

    public int getEmpID() {
        return empID;
    }

    public int getProjectID() {
        return projectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empID == employee.empID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empID);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", projectID=" + projectID +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

/*eID  pID dateFrom    DateTo
  218, 10, 2012-05-16, NULL
  143, 10, 2009-01-01, 2011-04-27
  155, 10, 2010-05-20, 2018-01-01
  143, 12, 2013-11-01, NULL
  160, 12, 2005-02-01, 2020-05-22
  155, 15, 2014-07-02, 2016-11-23
  132, 15, 2015-07-02, 2017-11-23
*/

    /*
        case1 -> 218, 10, 2012-05-06, 2015-05-06
                 143, 10, 2012-05-06, 2015-05-06

        case2 -> 218, 10, 2013-05-06, 2015-05-06
                 143, 10, 2012-05-06, 2014-05-06

        case3 -> 218, 10, 2012-05-06, 2016-05-06
                 143, 10, 2013-05-06, 2015-05-06

        case4 -> 218, 10, 2012-05-06, 2014-05-06
                 143, 10, 2013-05-06, 2015-05-06

        case5 -> 218, 10, 2013-05-06, 2015-05-06
                 143, 10, 2012-05-06, 2014-05-06
     */

    public long getCommonWorkTime(Employee otherEmp) {
        final long thisDateFrom = this.dateFrom.getTime();
        final long thisDateTo = this.dateTo.getTime();
        final long otherDateFrom = otherEmp.dateFrom.getTime();
        final long otherDateTo = otherEmp.dateTo.getTime();

        if (thisDateFrom == otherDateFrom && thisDateTo == otherDateTo) {
            return thisDateTo - thisDateFrom; //case1: equals
        } else if (thisDateFrom > otherDateFrom && thisDateTo < otherDateTo) {
            return thisDateTo - thisDateFrom; //case2: this between other
        } else if (thisDateFrom < otherDateFrom && thisDateTo > otherDateTo) {
            return otherDateTo - otherDateFrom; //case3: other between this
        } else if (thisDateFrom <= otherDateFrom && thisDateTo <= otherDateTo && thisDateTo > otherDateFrom) {
            return thisDateTo - otherDateFrom; //case4: begin(otherFrom) -> end(thisTo)
        } else if (thisDateFrom >= otherDateFrom && thisDateTo >= otherDateTo && otherDateTo > thisDateFrom) {
            return otherDateTo - thisDateFrom; //case5: begin(thisFrom) -> end(otherTo)
        }

        return 0;
    }
}
