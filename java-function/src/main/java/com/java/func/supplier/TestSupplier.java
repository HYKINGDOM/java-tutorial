package com.java.func.supplier;

import java.util.function.Supplier;

public class TestSupplier {

    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> 10 + 1;
        System.out.println(supplier);
        int b = supplier.get() + 1;
        System.out.println(b);


        DepartmentService departmentService = new DepartmentServiceImpl();
        Long uid = 1L;
        LazyUser user = new LazyUser();
        user.setUid(uid);
// departmentService 是一个rpc调用
        user.setDepartment(Lazy.of(() -> departmentService.getDepartment(uid)));
        System.out.println("=========================1111=======================");
        System.out.println(user);
        System.out.println(user.getDepartment());
        System.out.println("=========================2222=======================");
    }
}
