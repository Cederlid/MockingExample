package com.example;

import java.util.List;

public class EmployeeRepositoryStub implements EmployeeRepository{
    private List<Employee> employees;


    public EmployeeRepositoryStub(List<Employee> employee){
        this.employees = employee;
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee save(Employee e) {
        employees.add(e);
       return e;
    }
}
