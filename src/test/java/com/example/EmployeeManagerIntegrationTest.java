package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class EmployeeManagerIntegrationTest {
    @Test
    public void payAllEmployees (){
        BankService bankService = new BankServiceDummy();
        EmployeeRepository employeeRepository = new EmployeeRepositoryStub(new ArrayList<>());
        EmployeeManager employeeManager = new EmployeeManager(employeeRepository,bankService);
        employeeRepository.save(new Employee("1", 100));
        employeeRepository.save(new Employee("2", 200));
        int payment = employeeManager.payEmployees();
        assertEquals(2, payment);
    }

}
