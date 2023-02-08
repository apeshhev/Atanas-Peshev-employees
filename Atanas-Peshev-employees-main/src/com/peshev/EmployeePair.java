package com.peshev;

import java.util.Objects;

public record EmployeePair(Employee emp1, Employee emp2) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return Objects.equals(emp1, that.emp1) && Objects.equals(emp2, that.emp2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emp1, emp2);
    }
}
