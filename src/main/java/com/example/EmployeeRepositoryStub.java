package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmployeeRepositoryStub implements EmployeeRepository {
    private List<Employee> employees;

    public EmployeeRepositoryStub() {
        employees = new ArrayList<>();
    }

    public EmployeeRepositoryStub(List<Employee> employee) {
        this.employees = employee;
    }


    @Override
    public List<Employee> findAll() {
        new Employee("5", 1000);
        return employees;
    }

    @Override
    public Employee save(Employee employee) {
        int index = findEmployeeIndex(employee.getId());
        if (index != -1) {
            employees.remove(index);
        }
        employees.add(employee);
        return employee;
    }

    private int findEmployeeIndex(String id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

}
