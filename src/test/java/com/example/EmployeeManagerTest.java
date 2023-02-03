package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeManagerTest {
    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    private BankServiceDummy bankServiceDummy = mock(BankServiceDummy.class);

    private EmployeeRepositoryStub employeeRepositoryStub = new EmployeeRepositoryStub(
            List.of(new Employee( "1", 1000),
                    new Employee( "2", 1000),
                    new Employee( "3", 1000),
                    new Employee( "4", 1000)));

    BankService bankService = mock(BankService.class);
    private Employee employee = mock(Employee.class);
    private final EmployeeManager employeeManager = new EmployeeManager(employeeRepository, bankService);

    private final EmployeeManager employeeManagerTest = new EmployeeManager(employeeRepositoryStub, bankServiceDummy);

    @Test
    void payAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        int payment = employeeManager.payEmployees();
        assertEquals(1, payment);
    }

    @Test
    void testPayEmployeesShouldBeCalledOneTime() {
        bankServiceDummy.pay("1", 100);
        verify(bankServiceDummy, Mockito.times(1)).pay("1", 100);
    }

    @Test
    void payAllEmployeesWithEmployeeRepositoryStub() {
        int numberOfPayments = employeeManagerTest.payEmployees();
        assertEquals(4, numberOfPayments);
    }

    @Test
    void payEmployeesThatDoNotHaveAnException() {
       List<Employee> employees = new ArrayList<>();
       employees.add(new Employee("1",500));
       employees.add(new Employee( "2", 500));
       employees.add(new Employee("3", 500));
       employees.add(new Employee("4",500));
       when(employeeRepository.findAll()).thenReturn(employees);
       doThrow(new RuntimeException()).when(bankService).pay("1",500);
       int payments = employeeManager.payEmployees();
        Assertions.assertEquals(false,employees.get(0).isPaid());
        Assertions.assertEquals(true,employees.get(1).isPaid());
        Assertions.assertEquals(true,employees.get(2).isPaid());
        Assertions.assertEquals(true,employees.get(3).isPaid());
        assertEquals(3, payments);
    }


}