package com.java.func.supplier;

public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public String getDepartment(Long uuid) {
        System.out.println("=========================3333=======================");
        return uuid.toString() + "Test";
    }
}
