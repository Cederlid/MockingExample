package com.example;

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
            List.of(new Employee("Ellinor", "1", 1000),
                    new Employee("Johannes", "2", 1000),
                    new Employee("Bashar", "3", 1000),
                    new Employee("Samuel", "4", 1000)));

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
    void payAllEmployeesWithEmployeeRepositoryStub (){
       int numberOfPayments = employeeManagerTest.payEmployees();
       assertEquals(4,numberOfPayments);
    }



}