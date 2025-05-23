package com.java.func.actuallyapplication;

import java.util.function.Function;

/**
 * 使用Function的方式将对象传入另一个方法中参与计算, 这样做的好处之一可以减少不同模块之间的相互依赖
 *
 * @author meta
 */
public class FunctionClass {

    public static void main(String[] args) {
        EmployeeImp employeeImp = new EmployeeServiceImpl();
        FunctionClass functionClass = new FunctionClass();
        EmployeeRef employeeRef = new EmployeeRef();
        employeeRef.setEmpId(1000);
        employeeImp.employeeMindId(1000, functionClass.buildFunction(employeeRef));

        System.out.println("EmpId 1:" + employeeRef.getEmpId());

        employeeRef.setEmpId(null);
        employeeImp.employeeMindId(1000, functionClass.buildFunction(employeeRef));
        System.out.println("EmpId 2:" + employeeRef.getEmpId());

    }

    private Function<Integer, Object> buildFunction(EmployeeRef employeeRef) {

        return (employeeId -> {
            if (employeeRef.getEmpId() != null) {
                employeeRef.setEmpId(employeeId);
            } else {
                employeeRef.setEmpId(2048);
            }
            return employeeRef;
        });
    }

}
