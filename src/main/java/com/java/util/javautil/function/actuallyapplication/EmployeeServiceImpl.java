package com.java.util.javautil.function.actuallyapplication;

import java.util.function.Function;

public class EmployeeServiceImpl implements EmployeeImp {
    @Override
    public void employeeMindId(Integer empId, Function<Integer, Object> employeeId) {

        employeeId.apply(empId);
    }
}
