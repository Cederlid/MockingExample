package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EmployeeRepositoryTest {
    private EmployeeRepository employeeRepository = new EmployeeRepositoryStub(new ArrayList<>());
    private BankService bankService = new BankServiceDummy();


    @Test
    void findAllEmployees() {
        Employee employee1 = new Employee("1", 500);
        Employee employee2 = new Employee("2", 400);
        Employee employee3 = new Employee("3", 600);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        ArrayList<Employee> employees = (ArrayList<Employee>) employeeRepository.findAll();
        assertEquals(3, employees.size());
    }

    @Test
    void saveANewEmployeeShouldSucceed() {
        Employee employee1 = new Employee("1",300);
        Employee employee2 = new Employee("2",200);
        Employee employee3 = new Employee("1",400);
        Employee employee4 = new Employee("2", 600);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);

        ArrayList<Employee> employees = (ArrayList<Employee>) employeeRepository.findAll();
        assertEquals(2,employees.size());
        System.out.println(employees);
    }













}
