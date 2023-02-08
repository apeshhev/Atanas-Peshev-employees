package com.peshev;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EmployeeListTest {

    public static String file1 = "EmployeesZeroPairs.txt";
    public static String file2 = "EmployeesPairs.txt";

    public static List<String> fileList = List.of(file1,file2);

    private void loadFile(String fileName, String text) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] myBytes = text.getBytes();

            fos.write(myBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findLongestWorkingPairZeroPairs() {

        String text = """
                    143, 10, 2009-01-01, 2010-04-27
                    155, 10, 2011-05-20, 2012-01-01
                    143, 12, 2013-11-01, 2014-01-01
                    155, 15, 2015-01-01, 2016-01-01
                    160, 12, 2017-02-01, 2018-05-22
                    """;

        loadFile(file1,text);
        EmployeeList employeeList = new EmployeeList(file1);
        assertNull(employeeList.findLongestWorkingPair());
    }

    @Test
    public void findLongestWorkingPair() throws ParseException {

        String text = """
                    143, 10, 2009-01-01, 2015-04-27
                    155, 10, 2011-05-20, 2014-01-01
                    143, 12, 2016-11-01, 2018-01-01
                    155, 15, 2018-01-01, 2021-01-01
                    160, 12, 2017-02-01, 2018-05-22
                    160, 15, 2019-01-01, NULL
                    143, 15, 2019-01-01, NULL
                    """;

        loadFile(file2,text);
        EmployeeList employeeList = new EmployeeList(file2);
        Employee e1 = new Employee(143,12, "2016-11-01", "2018-01-01");
        Employee e2 = new Employee(160,12, "2017-02-01", "2018-05-22");
        EmployeePair testPair = new EmployeePair(e1,e2);
        assertEquals(testPair,employeeList.findLongestWorkingPair());
    }

    @AfterAll
    public static void cleanUp() {

        fileList.forEach(fileName -> {
            File file = new File(fileName);
            if(file.delete()) {
                System.out.println("Deleted");
            }else {
                System.out.println("Failed to delete");
            }
        });
    }
}
